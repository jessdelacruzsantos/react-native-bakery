package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Represents the details of an HTTP endpoint as defined by an rpc in a proto file.
 */
public class ConnectEndpoint {
  private String id = "";
  private String entity = "";
  private String action = "";
  private String description = "";
  private String path = "";
  private String inputType = "";
  private String outputType = "";
  private List<ConnectField> params = new ArrayList<ConnectField>();
  private List<String> oauthpermissions = new ArrayList<String>();
  private List<ConnectField> responsefields = new ArrayList<ConnectField>();
  private String httpmethod = "";
  private boolean nogenerate = false;

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<ConnectField> getParams() {
    return params;
  }

  public void setParams(List<ConnectField> params) {
    this.params = params;
  }

  public List<ConnectField> getResponsefields() {
    return responsefields;
  }

  public void setResponsefields(List<ConnectField> responsefields) {
    this.responsefields = responsefields;
  }

  public List<String> getOauthpermissions() {
    return oauthpermissions;
  }

  public void setOauthpermissions(List<String> permissions) {
    this.oauthpermissions = permissions;
  }

  public String getHttpmethod() {
    return httpmethod;
  }

  public void setHttpmethod(String httpmethod) {
    this.httpmethod = httpmethod;
  }

  public boolean isNogenerate() {
    return nogenerate;
  }

  public void setNogenerate(boolean nogenerate) {
    this.nogenerate = nogenerate;
  }

  public ConnectEndpoint(RpcElement rpc, ProtoIndex index) {

    String[] docComponents = ConnectAPIParser.parseDocString(rpc.documentation());

    this.id = rpc.name();
    for (String entry : docComponents) {
      String keyword = entry.split(" ")[0];
      switch (keyword) {
        case "entity":
          this.setEntity(entry.replaceFirst("entity", "").trim());
          break;
        case "action":
          this.setAction(entry.replaceFirst("action", "").trim());
          break;
        case "path":
          this.setPath(entry.replaceFirst("path", "").trim());
          break;
        case "oauthpermissions":
          this.setOauthpermissions(
              Arrays.asList(entry.replaceFirst("oauthpermissions", "").trim().split("\\s+")));
          break;
        case "httpmethod":
          this.setHttpmethod(entry.replaceFirst("httpmethod", "").trim());
          break;
        case "desc":
          this.setDescription(entry.replaceFirst("desc", "").trim());
          break;
        default:
          break;
      }
    }
    this.generateFields(rpc, index);
  }

  public void generateFields(RpcElement rpc, ProtoIndex index) {
    this.inputType = rpc.requestType();
    this.outputType = rpc.responseType();

    TypeElement requestType = null, responseType = null;
    for (String qualifiedName : index.getDatatypes().keySet()) {
      if (qualifiedName.contains(this.inputType)) {
        requestType = index.getDatatypes().get(qualifiedName);
      } else if (qualifiedName.contains(this.outputType)) {
        responseType = index.getDatatypes().get(qualifiedName);
      }
    }

    // Request fields
    if (requestType != null && requestType instanceof MessageElement) {
      for (FieldElement fe : ((MessageElement)requestType).fields()) {
        this.params.add(new ConnectField(fe));
      }
      for (OneOfElement ooe : ((MessageElement)requestType).oneOfs()) {
        for (FieldElement fe : ooe.fields()) {
          this.params.add(new ConnectField(fe));
        }
      }
    } else {

    }

    // Response fields
    if (responseType != null && responseType instanceof MessageElement) {
      List<ConnectField> responseFields = new ArrayList<ConnectField>();
      for (FieldElement fe : ((MessageElement)responseType).fields()) {
        ConnectField field = new ConnectField(fe);
        responseFields.add(field);
      }
      this.setResponsefields(responseFields);
    }
  }


  // Builds out endpoint JSON in the format expected by the Swagger 2.0 specification.
  public JSONObject toJson() {
    JSONObject root = new JSONObject();

    JSONArray swaggerTags = new JSONArray();
    swaggerTags.put(this.entity);

    root.put("tags", swaggerTags);
    root.put("summary", this.id);
    root.put("description", this.description);

    JSONArray swaggerParameters = new JSONArray();


    // POST and PUT requests list a single "body" parameter in Swagger, regardless
    // of how many fields that body parameter includes.
    if (this.httpmethod.equals("POST") || this.httpmethod.equals("PUT")) {
      JSONObject swaggerBodyParameter = new JSONObject();
      swaggerBodyParameter.put("name", "body");
      swaggerBodyParameter.put("in", "body");


      String typeName = this.inputType;

      // When specifying the name of the resource, get rid of pointless proto prefixes
      typeName = typeName.replaceFirst("resources.", "");
      typeName = typeName.replaceFirst("actions.", "");

      JSONObject swaggerBodyParameterSchema = new JSONObject();
      swaggerBodyParameterSchema.put("$ref", "#/definitions/" + typeName);

      swaggerBodyParameter.put("schema", swaggerBodyParameterSchema);
      swaggerParameters.put(swaggerBodyParameter);
    }

    for (ConnectField param : this.params) {
      if (param.getIsPathParam()) {
        JSONObject swaggerPathParameter = new JSONObject();
        swaggerPathParameter.put("name", param.getName());
        swaggerPathParameter.put("in", "path");
        swaggerPathParameter.put("type", "string");
        swaggerPathParameter.put("required", true);
        swaggerParameters.put(swaggerPathParameter);
      } else if (this.httpmethod.equals("GET") || this.httpmethod.equals("DELETE")) {
        JSONObject swaggerQueryParameter = new JSONObject();
        swaggerQueryParameter.put("name", param.getName());
        swaggerQueryParameter.put("in", "query");
        swaggerQueryParameter.put("type", param.getType());
        swaggerQueryParameter.put("required", param.getRequired());
        swaggerParameters.put(swaggerQueryParameter);
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

  private JSONArray arrayifyFields(List<ConnectField> fields) {
    JSONArray array = new JSONArray();
    for (ConnectField field : fields) {
      array.put(field.toJson());
    }
    return array;
  }
}
