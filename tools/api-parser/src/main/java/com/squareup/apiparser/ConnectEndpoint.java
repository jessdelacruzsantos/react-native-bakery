package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.RpcElement;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

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

  public ConnectEndpoint(RpcElement rpc, ProtoIndex index) {
    this.rootRpc = checkNotNull(rpc);
    this.inputType = rpc.requestType();
    this.outputType = rpc.responseType();
    this.index = checkNotNull(index);
    this.docAnnotations = new DocString(rpc.documentation()).parse().getAnnotations();
    Optional<ConnectDatatype> requestType = index.getDataType(inputType);
    checkState(requestType.isPresent());
    this.params = requestType.get()
        .getFields()
        .stream()
        .collect(collectingAndThen(toList(), ImmutableList::copyOf));
  }

  public String getPath() {
    return this.docAnnotations.getOrDefault("path", "");
  }

  public String getHttpmethod() {
    return this.docAnnotations.getOrDefault("httpmethod", "");
  }

  public String getName() {
    return this.rootRpc.name();
  }

  // Builds out endpoint JSON in the format expected by the Swagger 2.0 specification.
  public JsonObject toJson() {
    JsonObject root = new JsonObject();

    if (this.docAnnotations.containsKey("entity")) {
      JsonArray swaggerTags = new JsonArray();
      swaggerTags.add(this.docAnnotations.get("entity"));
      root.add("tags", swaggerTags);
    }

    root.addProperty("summary", this.getName());
    root.addProperty("operationId", this.getName());
    root.addProperty("description", docAnnotations.getOrDefault("desc", ""));

    JsonArray swaggerParameters = new JsonArray();

    // Every API endpoint requires an Authorization header for the request's access token
    JsonObject authorizationParameter = new JsonObject();
    authorizationParameter.addProperty("name", "Authorization");
    authorizationParameter.addProperty("in", "header");
    authorizationParameter.addProperty("type", "string");
    authorizationParameter.addProperty("required", true);
    authorizationParameter.addProperty("description",
        "The value to provide in the Authorization header of\n"
            + "your request. This value should follow the format `Bearer YOUR_ACCESS_TOKEN_HERE`.");

    swaggerParameters.add(authorizationParameter);

    for (ConnectField param : this.params) {
      JsonObject swaggerParameter = new JsonObject();
      swaggerParameter.addProperty("name", param.getName());
      swaggerParameter.addProperty("description", param.getDescription());
      if (!param.getEnumValues().isEmpty()) {
        JsonArray enumArray = new JsonArray();
        param.getEnumValues().forEach(enumArray::add);
        swaggerParameter.add("enum", enumArray);
        swaggerParameter.addProperty("type", "string");
      } else {
        swaggerParameter.addProperty("type", param.getType());
      }
      if (param.isPathParam()) {
        swaggerParameter.addProperty("in", "path");
        swaggerParameter.addProperty("required", true);
        swaggerParameters.add(swaggerParameter);
      } else if (this.getHttpmethod().equals("GET") || this.getHttpmethod().equals("DELETE")) {
        swaggerParameter.addProperty("in", "query");
        swaggerParameter.addProperty("required", param.isRequired());
        swaggerParameters.add(swaggerParameter);
      }
    }

    // POST and PUT requests list a single "body" parameter in Swagger, regardless
    // of how many fields that body parameter includes.
    if (this.getHttpmethod().equals("POST") || this.getHttpmethod().equals("PUT")) {
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
}
