package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Represents the details of an HTTP endpoint as defined by an rpc in a proto file.
 */
public class ConnectEndpoint {
  private String inputType = "";
  private String outputType = "";
  private List<ConnectField> params = new ArrayList<ConnectField>();
  private boolean nogenerate = false;
  private Map<String, String> docAnnotations;
  private RpcElement rootRpc;
  private ProtoIndex index;


  public String getPath() {
    if (this.docAnnotations.containsKey("path")) {
      return this.docAnnotations.get("path");
    } else {
      return "";
    }
  }

  public String getHttpmethod() {
    if (this.docAnnotations.containsKey("httpmethod")) {
      return this.docAnnotations.get("httpmethod");
    } else {
      return "";
    }
  }

  public boolean isNogenerate() {
    return nogenerate;
  }

  public String getName() {
    return this.rootRpc.name();
  }

  public ConnectEndpoint(RpcElement rpc, ProtoIndex index) {
    this.docAnnotations = new HashMap<String, String>();
    this.rootRpc = rpc;
    this.index = index;
    this.parseDocumentationString(rpc.documentation());
    this.generateFields(rpc, index);
  }

  public void generateFields(RpcElement rpc, ProtoIndex index) {
    this.inputType = rpc.requestType();
    this.outputType = rpc.responseType();

    ConnectType requestType = index.getType(this.inputType);

    // Request fields
    for (ConnectField cf : ((ConnectDatatype)requestType).getFields()) {
      this.params.add(cf);
    }
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
      } else {

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

    String[] components;
    if (docString.equals("")) {
      return;

      // Public doc strings can be bounded by two hyphens to support multiline annotations.
    } else if (docString.contains("--")) {
      String publicDocString = docString.split("--")[1];
      components = publicDocString.split("\\s+@");
      if (components[0].trim().startsWith("@")) {
        components[0] = components[0].replaceFirst("@", "");
      }

      // If there is no two-hyphen boundary, it's assumed each annotation is exactly one line.
    } else {
      int annotationIndex = 0;
      int newlineIndex = 0;
      List<String> componentList = new ArrayList<String>();
      while (true) {
        annotationIndex = docString.indexOf("@", annotationIndex);
        newlineIndex = docString.indexOf("\n", annotationIndex);
        if (annotationIndex == -1) {
          break;
        }
        if (newlineIndex == -1) {
          newlineIndex = docString.length();
        }
        componentList.add(docString.substring(annotationIndex + 1, newlineIndex));
        annotationIndex = newlineIndex;
      }
      components = new String[componentList.size()];
      components = componentList.toArray(components);
    }

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
