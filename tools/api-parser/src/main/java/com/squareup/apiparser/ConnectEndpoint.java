package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.RpcElement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.squareup.apiparser.ConnectType.FORMAT_MAP;
import static com.squareup.apiparser.ConnectType.TYPE_MAP;

/**
 * Represents the details of an HTTP endpoint as defined by an rpc in a proto file.
 */
public class ConnectEndpoint {
  private final String inputType;
  private final String outputType;
  private final List<ConnectField> params;
  private final Map<String, String> docAnnotations;
  private final RpcElement rootRpc;
  private final ProtoIndex index;
  private final ApiReleaseType releaseType;

  // See http://swagger.io/specification/#pathItemObject
  private static final ImmutableSet<String> VALID_HTTP_METHODS = ImmutableSet.of("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD", "PATCH");

  ConnectEndpoint(RpcElement rpc, ProtoIndex index, ApiReleaseType releaseType) {
    this.rootRpc = checkNotNull(rpc);
    this.inputType = rpc.requestType();
    this.outputType = rpc.responseType();
    this.index = checkNotNull(index);
    this.docAnnotations = new DocString(rpc.documentation()).getAnnotations();
    this.releaseType = releaseType;
    Optional<ConnectDatatype> requestType = index.getDataType(inputType);
    checkState(requestType.isPresent());
    //noinspection OptionalGetWithoutIsPresent
    this.params = ImmutableList.copyOf(requestType.get().getFields());
  }

  public String getPath() {
    return ProtoOptions.getStringValue(rootRpc.options(), "common.path").orElse("");
  }

  String getHttpMethod() throws InvalidSpecException {
    String method = ProtoOptions.getStringValue(rootRpc.options(), "common.http_method")
        .orElseThrow(() -> new InvalidSpecException.Builder("No common.http_method option found").setContext(this.rootRpc).build());

    if (!VALID_HTTP_METHODS.contains(method)) {
      throw new InvalidSpecException.Builder(String.format("Unrecognized HTTP method '%s'", method))
        .setContext(this.rootRpc)
        .build();
    }

    return method;
  }

  public String getName() {
    return this.rootRpc.name();
  }

  String getReleaseStatus() {
    return ProtoOptions.getReleaseStatus(rootRpc.options(), "common.method_status");
  }

  // Builds out endpoint JSON in the format expected by the Swagger 2.0 specification.
  JsonObject toJson() throws InvalidSpecException {
    JsonObject root = new JsonObject();

    Optional<String> entityOptional =
        ProtoOptions.getStringValue(rootRpc.options(), "common.entity");
    if (entityOptional.isPresent()) {
      JsonArray swaggerTags = new JsonArray();
      swaggerTags.add(entityOptional.get());
      root.add("tags", swaggerTags);
    }

    root.addProperty("summary", this.getName());
    root.addProperty("operationId", this.getName());
    root.addProperty("description", docAnnotations.getOrDefault("desc", ""));

    Set<String> oauthPermissions = ProtoOptions.getOAuthPermissions(rootRpc);
    JsonArray permissionsArray = new JsonArray();

    // OAuth permission rules
    // If the endpoint has OAuth enabled (default) then the OAuth permissions must be a non-empty set
    // If the endpoint has disabled OAuth via common.oauth_credential_required = false then
    //   - It must be an INTERNAL endpoint, AND
    //   - The OAuth permissions set is empty
    Boolean oauthEnabled = ProtoOptions.getBooleanValueOrDefault(rootRpc.options(), "common.oauth_credential_required", true);
    Boolean oauthScopeRequired = ProtoOptions.getBooleanValueOrDefault(rootRpc.options(), "common.oauth_scope_required", true);
    if (oauthEnabled) {
      if (oauthPermissions.isEmpty() && oauthScopeRequired) {
        throw new InvalidSpecException.Builder(String.format("Empty OAuth permissions on OAuth enabled endpoint '%s'", this.getPath()))
          .build();
      }

      for (String permission : oauthPermissions) {
        permissionsArray.add(permission);
      }
    } else {
      if (!getReleaseStatus().equals(ProtoOptions.RELEASE_STATUS_INTERNAL)) {
        throw new InvalidSpecException.Builder(String.format("OAuth can only be disabled on INTERNAL endpoints, endpoint '%s'", this.getPath()))
          .build();
      }

      if (!oauthPermissions.isEmpty()) {
        throw new InvalidSpecException.Builder(String.format("Cannot specify OAuth permissions with common.oauth_credential_required = false, endpoint '%s'", this.getPath()))
          .build();
      }

      // Use empty permissions array further down to disable oauth security on the endpoint
    }

    // Add the swagger OAuth2 security section that specifies required OAuth permissions
    JsonArray secList = new JsonArray();
    if (!oauthPermissions.isEmpty()) {
      JsonObject oauth2 = new JsonObject();
      oauth2.add("oauth2", permissionsArray);
      secList.add(oauth2);

      // TODO(killpack) - Remove 'x-oauthpermissions' once documentation generation pipeline is
      // parsing the security section
      root.add("x-oauthpermissions", permissionsArray);
    }
    root.add("security", secList);

    JsonArray swaggerParameters = new JsonArray();

    for (ConnectField param : this.params) {
      JsonObject swaggerParameter = new JsonObject();
      swaggerParameter.addProperty("name", param.getName());
      swaggerParameter.addProperty("description", param.getDescription());
      List<String> enumValues = param.getEnumValues(releaseType);
      if (!enumValues.isEmpty()) {
        JsonArray enumArray = new JsonArray();
        enumValues.forEach(enumArray::add);
        swaggerParameter.add("enum", enumArray);
        swaggerParameter.addProperty("type", "string");
      } else {
        final String type = param.getType();
        swaggerParameter.addProperty("type", TYPE_MAP.getOrDefault(type, type));
        if (FORMAT_MAP.containsKey(type)) {
          swaggerParameter.addProperty("format", FORMAT_MAP.get(type));
        }
      }
      if (param.isPathParam()) {
        swaggerParameter.addProperty("in", "path");
        swaggerParameter.addProperty("required", true);
        swaggerParameters.add(swaggerParameter);
      } else if (this.getHttpMethod().equals("GET") || this.getHttpMethod().equals("DELETE")) {
        swaggerParameter.addProperty("in", "query");
        swaggerParameter.addProperty("required", param.isRequired());
        swaggerParameters.add(swaggerParameter);
      }
    }

    // POST and PUT requests list a single "body" parameter in Swagger, regardless
    // of how many fields that body parameter includes.
    if (this.getHttpMethod().equals("POST") || this.getHttpMethod().equals("PUT")) {
      Optional<ConnectDatatype> requestDataType = this.index.getDataType(this.inputType);
      if (requestDataType.map(ConnectDatatype::hasBodyParameters).orElse(false)) {
        JsonObject paramJson = new JsonObject();

        paramJson.addProperty("name", "body");
        paramJson.addProperty("in", "body");
        paramJson.addProperty("required", true);
        paramJson.addProperty("description",
            "An object containing the fields to POST for the request.\n\n"
                + "See the corresponding object definition for field details.");

        JsonObject schemaRef = new JsonObject();
        schemaRef.addProperty("$ref", "#/definitions/" + Protos.cleanName(this.inputType));
        paramJson.add("schema", schemaRef);
        swaggerParameters.add(paramJson);
      }
    }

    root.add("parameters", swaggerParameters);

    JsonObject swaggerSuccessResponse = new JsonObject();
    swaggerSuccessResponse.addProperty("description", "Success");

    String typeName = this.outputType;

    // When specifying the name of the resource, get rid of pointless proto prefixes
    typeName = typeName.replaceFirst("resources.", "");
    typeName = typeName.replaceFirst("actions.", "");

    JsonObject swaggerSuccessResponseSchema = new JsonObject();
    swaggerSuccessResponseSchema.addProperty("$ref", "#/definitions/" + typeName);

    swaggerSuccessResponse.add("schema", swaggerSuccessResponseSchema);

    JsonObject swaggerResponses = new JsonObject();
    swaggerResponses.add("200", swaggerSuccessResponse);

    root.add("responses", swaggerResponses);

    return root;
  }

  public ApiReleaseType getReleaseType() {
    return releaseType;
  }
}
