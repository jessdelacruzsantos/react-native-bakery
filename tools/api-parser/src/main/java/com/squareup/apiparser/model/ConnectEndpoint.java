package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
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
  // See http://swagger.io/specification/#pathItemObject
  private static final ImmutableSet<String> VALID_HTTP_METHODS =
      ImmutableSet.of("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD", "PATCH");

  private static final String AUTHENTICATION_METHOD_OAUTH2_ACCESS_TOKEN = "OAUTH2_ACCESS_TOKEN";
  private static final String AUTHENTICATION_METHOD_OAUTH2_CLIENT_SECRET = "OAUTH2_CLIENT_SECRET";
  private static final String AUTHENTICATION_METHOD_MULTIPASS = "MULTIPASS";
  private static final Set<String> VALID_AUTHENTICATION_METHODS = ImmutableSet.of(
      AUTHENTICATION_METHOD_OAUTH2_ACCESS_TOKEN,
      AUTHENTICATION_METHOD_OAUTH2_CLIENT_SECRET,
      AUTHENTICATION_METHOD_MULTIPASS
  );

  private ConnectDatatype inputDataType;
  private ConnectDatatype outputDataType;
  private final Map<String, String> docAnnotations;
  private final RpcElement element;
  private ProtoIndexer index;
  private final Group group = new Group();
  private String sqVersion;
  private String httpMethod;
  private String name;
  private String path;

  ConnectEndpoint(RpcElement element, Group defaultGroup, String sqVersion) {
    checkNotNull(defaultGroup);
    this.sqVersion = sqVersion;
    this.element = checkNotNull(element);
    this.docAnnotations = new DocString(element.documentation()).getAnnotations();
    this.group.status = ProtoOptions.getReleaseStatus(element.options(), "common.method_status", defaultGroup.status);
    this.group.namespace = ProtoOptions.getStringValue(element.options(), "common.method_namespace").orElse(defaultGroup.namespace);
    this.httpMethod = ProtoOptions.getStringValue(element.options(), "common.http_method").orElse("");

    if (!httpMethod.equals("") && !VALID_HTTP_METHODS.contains(httpMethod)){
      throw new InvalidSpecException.Builder("Unrecognized HTTP method '" + httpMethod +"'")
          .build();
    }

    this.path = ProtoOptions.getStringValue(element.options(), "common.path").orElse("");
    this.name = this.element.name();
  }

  public String getPath() {
    return this.path;
  }

  public String getHttpMethod(){
    return this.httpMethod;
  }

  public String getName() {
    return this.name;
  }

  public Group getGroup() {
    return group;
  }

  //populateFields() has to be called once before toJson() is called
  //It retreives the request & response types.
  void populateFields(ProtoIndexer index) {
    String inputType = element.requestType();
    String outputType = element.responseType();
    Optional<ConnectDatatype> requestType = index.getDataType(inputType);
    checkState(requestType.isPresent(), "Request type %s could not be found for rpc=%s.", inputType, name);
    //noinspection OptionalGetWithoutIsPresent
    this.inputDataType = requestType.get();

    Optional<ConnectDatatype> responseType = index.getDataType(outputType);
    checkState(responseType.isPresent(), "Response type %s could not be found for rpc=%s.", outputType, name);
    this.outputDataType = responseType.get();
  }

  Set<String> getAuthenticationMethods() throws InvalidSpecException {
    Set<String> methods =
        ProtoOptions.getStringListValue(element.options(), "common.authentication_methods")
            .map(ImmutableSet::copyOf)
            .orElseThrow(() -> new InvalidSpecException.Builder(
                "No common.authentication_methods option found")
                .setContext(this.element)
                .build());

    Set<String> invalidMethods = Sets.difference(methods, VALID_AUTHENTICATION_METHODS);
    if (!invalidMethods.isEmpty()) {
      throw new InvalidSpecException.Builder(
          String.format("Unrecognized authentication methods: %s",
              String.join(",", invalidMethods)))
          .setContext(this.element)
          .build();
    }

    return methods;
  }

  // Builds out endpoint JSON in the format expected by the Swagger 2.0 specification.
  JsonObject toJson() throws InvalidSpecException {
    JsonObject root = new JsonObject();

    Optional<String> entityOptional =
        ProtoOptions.getStringValue(element.options(), "common.entity");
    if (entityOptional.isPresent()) {
      JsonArray swaggerTags = new JsonArray();
      swaggerTags.add(entityOptional.get());
      root.add("tags", swaggerTags);
    }

    root.addProperty("summary", this.name);
    root.addProperty("operationId", this.name);
    root.addProperty("description", docAnnotations.getOrDefault("desc", ""));
    root.addProperty("x-release-status", this.group.status.name());

    Set<String> authenticationMethods = getAuthenticationMethods();
    Set<String> oauthPermissions = ProtoOptions.getOAuthPermissions(element);
    JsonArray permissionsArray = new JsonArray();

    // OAuth permission rules
    // If the endpoint has OAuth as the authentication method, then the OAuth permissions must be a non-empty set.
    // Otherwise the OAuth permissions must be empty.
    Boolean oauthEnabled =
        authenticationMethods.contains(AUTHENTICATION_METHOD_OAUTH2_ACCESS_TOKEN);
    Boolean oauthScopeRequired =
        ProtoOptions.getBooleanValueOrDefault(element.options(), "common.oauth_scope_required",
            true);
    if (oauthEnabled) {
      if (oauthPermissions.isEmpty() && oauthScopeRequired) {
        throw new InvalidSpecException.Builder(
            String.format("Empty OAuth permissions on OAuth enabled endpoint '%s'", this.path))
            .build();
      }

      for (String permission : oauthPermissions) {
        permissionsArray.add(permission);
      }
    } else {
      if (!oauthPermissions.isEmpty()) {
        throw new InvalidSpecException.Builder(String.format(
            "Cannot specify OAuth permissions with common.oauth_credential_required = false, endpoint '%s'",
            this.path))
            .build();
      }

      // Use empty permissions array further down to disable oauth security on the endpoint
    }

    // OAuth client secret.
    // This is the auth method for some endpoints in OAuth flow.
    Boolean oauthClientSecretEnabled = authenticationMethods.contains(
        AUTHENTICATION_METHOD_OAUTH2_CLIENT_SECRET);

    // Add security sections.
    JsonArray secList = new JsonArray();
    if (oauthEnabled) {
      JsonObject oauth2 = new JsonObject();
      oauth2.add("oauth2", permissionsArray);
      secList.add(oauth2);

      // TODO(killpack) - Remove 'x-oauthpermissions' once documentation generation pipeline is
      // parsing the security section
      root.add("x-oauthpermissions", permissionsArray);
    }

    // Add API version (Square Version)
    root.addProperty("x-sq-version", sqVersion);

    if (oauthClientSecretEnabled) {
      JsonObject clientAuth = new JsonObject();
      clientAuth.add("oauth2ClientSecret", new JsonArray());
      secList.add(clientAuth);
    }

    root.add("security", secList);

    JsonArray swaggerParameters = new JsonArray();

    for (ConnectField param : this.inputDataType.getFields()) {
      JsonObject swaggerParameter = new JsonObject();
      swaggerParameter.addProperty("name", param.getName());
      swaggerParameter.addProperty("description", param.getDescription());
      List<String> enumValues = param.getEnumValues(this.group);
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
      } else if (this.httpMethod.equals("GET") || this.httpMethod.equals("DELETE")) {
        if (this.group.shouldInclude(param.getGroup())) {
          swaggerParameter.addProperty("in", "query");
          swaggerParameter.addProperty("required", param.isRequired());
          swaggerParameters.add(swaggerParameter);
        }
      }
    }

    // POST and PUT requests list a single "body" parameter in Swagger, regardless
    // of how many fields that body parameter includes.
    if (this.httpMethod.equals("POST") || this.httpMethod.equals("PUT")) {
      if (this.inputDataType.hasBodyParameters()) {
        JsonObject paramJson = new JsonObject();

        paramJson.addProperty("name", "body");
        paramJson.addProperty("in", "body");
        paramJson.addProperty("required", true);
        paramJson.addProperty("description",
            "An object containing the fields to POST for the request.\n\n"
                + "See the corresponding object definition for field details.");

        JsonObject schemaRef = new JsonObject();
        schemaRef.addProperty("$ref", "#/definitions/" + Protos.cleanName(this.inputDataType.getName()));
        paramJson.add("schema", schemaRef);
        swaggerParameters.add(paramJson);
      }
    }

    root.add("parameters", swaggerParameters);

    JsonObject swaggerSuccessResponse = new JsonObject();
    swaggerSuccessResponse.addProperty("description", "Success");

    String typeName = this.outputDataType.getName();

    // When specifying the name of the resource, get rid of pointless proto prefixes
    typeName = typeName.replaceFirst("resources\\.", "");
    typeName = typeName.replaceFirst("actions\\.", "");

    JsonObject swaggerSuccessResponseSchema = new JsonObject();
    swaggerSuccessResponseSchema.addProperty("$ref", "#/definitions/" + typeName);

    swaggerSuccessResponse.add("schema", swaggerSuccessResponseSchema);

    JsonObject swaggerResponses = new JsonObject();
    swaggerResponses.add("200", swaggerSuccessResponse);

    root.add("responses", swaggerResponses);

    return root;
  }
}
