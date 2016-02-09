package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.RpcElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

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
    this.docAnnotations = new HashMap<>();
    this.rootRpc = rpc;
    this.inputType = rpc.requestType();
    this.outputType = rpc.responseType();
    this.index = index;
    this.parseDocumentationString(rpc.documentation());
    ConnectType requestType = index.getType(inputType);
    Preconditions.checkNotNull(requestType);
    this.params = (((ConnectDatatype) requestType).getFields().stream().collect(Collectors.toList()));
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
  public JSONObject toJson() {
    JSONObject root = new JSONObject();

    if (this.docAnnotations.containsKey("entity")) {
      JSONArray swaggerTags = new JSONArray();
      swaggerTags.put(this.docAnnotations.get("entity"));
      root.put("tags", swaggerTags);
    }

    root.put("summary", this.getName());
    root.put("operationId", this.getName());

    if (this.docAnnotations.containsKey("desc"))  {
      root.put("description", this.docAnnotations.get("desc"));
    } else {
      root.put("description", "");
    }

    JSONArray swaggerParameters = new JSONArray();

    // Every API endpoint requires an Authorization header for the request's access token
    JSONObject authorizationParameter = new JSONObject();
    authorizationParameter.put("name", "Authorization");
    authorizationParameter.put("in", "header");
    authorizationParameter.put("type", "string");
    authorizationParameter.put("required", true);
    authorizationParameter.put("description", "The value to provide in the Authorization header of\n"
            + "your request. This value should follow the format `Bearer YOUR_ACCESS_TOKEN_HERE`.");

    swaggerParameters.put(authorizationParameter);

    for (ConnectField param : this.params) {
      JSONObject swaggerParameter = new JSONObject();
      swaggerParameter.put("name", param.getName());
      swaggerParameter.put("description", param.getDescription());
      if (!param.getEnumValues().isEmpty()) {
        JSONArray enumArray = new JSONArray();
        List<String> enumValues = param.getEnumValues();
        for (String enumValue : enumValues) {
          enumArray.put(enumValue);
        }
        swaggerParameter.put("enum", enumArray);
        swaggerParameter.put("type", "string");
      } else {
        swaggerParameter.put("type", param.getType());
      }
      if (param.isPathParam()) {
        swaggerParameter.put("in", "path");
        swaggerParameter.put("required", true);
        swaggerParameters.put(swaggerParameter);
      } else if (this.getHttpmethod().equals("GET") || this.getHttpmethod().equals("DELETE")) {
        swaggerParameter.put("in", "query");
        swaggerParameter.put("required", param.getRequired());
        swaggerParameters.put(swaggerParameter);
      }
    }

    // POST and PUT requests list a single "body" parameter in Swagger, regardless
    // of how many fields that body parameter includes.
    if (this.getHttpmethod().equals("POST") || this.getHttpmethod().equals("PUT")) {
      String typeName = this.inputType;
      ConnectType type = this.index.getType(typeName);
      if (!(type instanceof ConnectDatatype)) {
        System.err.println("ERROR: Endpoint request type is not a datatype");
        return null;
      }
      ConnectDatatype requestDatatype = (ConnectDatatype)type;
      if (requestDatatype.hasFields()) {
        JSONObject swaggerBodyParameter = new JSONObject();
        swaggerBodyParameter.put("name", "body");
        swaggerBodyParameter.put("in", "body");
        swaggerBodyParameter.put("required", true);
        swaggerBodyParameter.put("description", "An object containing the fields to POST for the request.\n\n"
            + "See the corresponding object definition for field details.");

        // When specifying the name of the resource, get rid of pointless proto prefixes
        typeName = typeName.replaceFirst("resources.", "");
        typeName = typeName.replaceFirst("actions.", "");

        JSONObject swaggerBodyParameterSchema = new JSONObject();
        swaggerBodyParameterSchema.put("$ref", "#/definitions/" + typeName);

        swaggerBodyParameter.put("schema", swaggerBodyParameterSchema);
        swaggerParameters.put(swaggerBodyParameter);
      }
    }

    root.put("parameters", swaggerParameters);

    JSONObject swaggerResponses = new JSONObject();
    JSONObject swaggerSuccessResponse = new JSONObject();

    swaggerSuccessResponse.put("description", "Success");

    String typeName = this.outputType;

    // When specifying the name of the resource, get rid of pointless proto prefixes
    typeName = typeName.replaceFirst("resources.", "");
    typeName = typeName.replaceFirst("actions.", "");

    JSONObject swaggerSuccessResponseSchema = new JSONObject();
    swaggerSuccessResponseSchema.put("$ref", "#/definitions/" + typeName);

    swaggerSuccessResponse.put("schema", swaggerSuccessResponseSchema);
    swaggerResponses.put("200", swaggerSuccessResponse);

    root.put("responses", swaggerResponses);

    return root;
  }

  private void parseDocumentationString(String docString) {
    final ImmutableList<String> components = DocString.parse(docString);
    for (String entry : components) {
      String keyword = entry.split(" ")[0];

      if (this.docAnnotations.containsKey(keyword)) {
        System.err.println("ERROR! Multiple doc annotations of same type found for endpoint " + this.getName());
      }

      docAnnotations.put(keyword, entry.replaceFirst(keyword, "").trim());
    }
  }

  private JSONArray arrayifyFields(List<ConnectField> fields) {
    JSONArray array = new JSONArray();
    for (ConnectField field : fields) {
      array.put(field.toJson());
    }
    return array;
  }
}
