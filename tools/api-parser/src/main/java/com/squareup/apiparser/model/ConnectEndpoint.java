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
  public static final String AUTHENTICATION_METHOD_OAUTH2_ACCESS_TOKEN = "OAUTH2_ACCESS_TOKEN";
  public static final String AUTHENTICATION_METHOD_OAUTH2_CLIENT_SECRET = "OAUTH2_CLIENT_SECRET";
  public static final String AUTHENTICATION_METHOD_MULTIPASS = "MULTIPASS";
  public static final Set<String> VALID_AUTHENTICATION_METHODS = ImmutableSet.of(
      AUTHENTICATION_METHOD_OAUTH2_ACCESS_TOKEN,
      AUTHENTICATION_METHOD_OAUTH2_CLIENT_SECRET,
      AUTHENTICATION_METHOD_MULTIPASS
  );

  private ConnectDatatype inputDataType;
  private ConnectDatatype outputDataType;
  private final RpcElement element;
  private final Group group = new Group();
  private String sqVersion;
  private String httpMethod;
  private String name;
  private String path;
  private String description;
  private String identifier;

  ConnectEndpoint(RpcElement element, Group defaultGroup, String sqVersion) {
    checkNotNull(defaultGroup);
    this.sqVersion = sqVersion;
    this.element = checkNotNull(element);
    this.description = new DocString(element.documentation()).getDescription();
    this.group.status = ProtoOptions.getReleaseStatus(element.options(), "common.method_status", defaultGroup.status);
    this.group.namespace = ProtoOptions.getStringValue(element.options(), "common.method_namespace").orElse(defaultGroup.namespace);
    this.httpMethod = ProtoOptions.getStringValue(element.options(), "common.http_method").orElse("");
    this.path = ProtoOptions.getStringValue(element.options(), "common.path").orElse("");
    this.name = this.element.name();
    this.identifier = "(RPC)"+this.name;
  }

  public void validate() {
    Validator.validateDescription(this.identifier, this.description);
    Validator.validateHttpMethod(this.httpMethod);
    Validator.validateRequestType(this.identifier, this.name, this.inputDataType);
    Validator.validateResponseType(this.identifier, this.name, this.outputDataType);
    Validator.validateAuthenticationMethods(this.identifier, this.element.options());
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
    return this.group;
  }

  //populateFields() has to be called once before toJson() is called
  //It retreives the request & response types.
  void populateFields(ProtoIndexer index) {
    String inputType = element.requestType();
    String outputType = element.responseType();
    Optional<ConnectDatatype> requestType = index.getDataType(inputType);
    checkState(requestType.isPresent(), "Request type %s could not be found for rpc=%s.", inputType, name);
    this.inputDataType = requestType.get();

    Optional<ConnectDatatype> responseType = index.getDataType(outputType);
    checkState(responseType.isPresent(), "Response type %s could not be found for rpc=%s.", outputType, name);
    this.outputDataType = responseType.get();
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
    root.addProperty("description", this.description);
    root.addProperty("x-release-status", this.group.status.name());

    Set<String> authenticationMethods = ProtoOptions.getStringListValue(element.options(), "common.authentication_methods")
      .map(ImmutableSet::copyOf)
      .orElse(ImmutableSet.of());

    Set<String> oauthPermissions = ProtoOptions.getOAuthPermissions(element.options());

    JsonArray permissionsArray = new JsonArray();
    for (String permission : oauthPermissions) {
      permissionsArray.add(permission);
    }

    Boolean oauthEnabled = authenticationMethods.contains(AUTHENTICATION_METHOD_OAUTH2_ACCESS_TOKEN);
    Boolean oauthClientSecretEnabled = authenticationMethods.contains(AUTHENTICATION_METHOD_OAUTH2_CLIENT_SECRET);

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

        List<ConnectField> inputFields = inputDataType.getNonPathFields();
        // V1 has a different API spec convention
        if(inputDataType.getName().startsWith(Configuration.V1_TYPE_PREFIX) && inputFields.size() == 1){
          ConnectField requestType = inputFields.get(0);

          paramJson.addProperty("name", requestType.getName());
          paramJson.addProperty("in", "body");
          paramJson.addProperty("required", true);
          paramJson.addProperty("description", requestType.getDescription());

          JsonObject schemaRef = new JsonObject();
          schemaRef.addProperty("$ref", "#/definitions/" + Protos.cleanName(requestType.getType()));
          paramJson.add("schema", schemaRef);
        }
        else{
          paramJson.addProperty("name", "body");
          paramJson.addProperty("in", "body");
          paramJson.addProperty("required", true);
          paramJson.addProperty("description",
              "An object containing the fields to POST for the request.\n\n"
                  + "See the corresponding object definition for field details.");

          JsonObject schemaRef = new JsonObject();
          schemaRef.addProperty("$ref", "#/definitions/" + Protos.cleanName(this.inputDataType.getName()));
          paramJson.add("schema", schemaRef);
        }

        swaggerParameters.add(paramJson);
      }
    }
    root.add("parameters", swaggerParameters);

    JsonObject swaggerSuccessResponse = new JsonObject();
    swaggerSuccessResponse.addProperty("description", "Success");

    JsonObject swaggerSuccessResponseSchema = new JsonObject();
    List<ConnectField> outputFields = outputDataType.getFields();

    // V1 has a different API spec convention
    if(outputDataType.getName().startsWith(Configuration.V1_TYPE_PREFIX) && outputFields.size() == 0){
      // If V1response type has no fields, return `object`.
      swaggerSuccessResponseSchema.addProperty("type", "object");
    }
    else if(outputDataType.getName().startsWith(Configuration.V1_TYPE_PREFIX) &&
            outputFields.size() == 1 &&
            outputFields.get(0).isArray()
            ){
      // If V1response is an array, return an array.
      String typeName = outputFields.get(0).getType();
      JsonObject swaggerItemsJson = new JsonObject();
      swaggerItemsJson.addProperty("$ref", "#/definitions/" + typeName);
      swaggerSuccessResponseSchema.addProperty("type", "array");
      swaggerSuccessResponseSchema.add("items", swaggerItemsJson);
    }
    else{
      String typeName = this.outputDataType.getName();
      // When specifying the name of the resource, get rid of pointless proto prefixes
      typeName = typeName.replaceFirst("resources\\.", "");
      typeName = typeName.replaceFirst("actions\\.", "");

      swaggerSuccessResponseSchema.addProperty("$ref", "#/definitions/" + typeName);
    }

    swaggerSuccessResponse.add("schema", swaggerSuccessResponseSchema);

    JsonObject swaggerResponses = new JsonObject();
    swaggerResponses.add("200", swaggerSuccessResponse);

    root.add("responses", swaggerResponses);

    return root;
  }
}
