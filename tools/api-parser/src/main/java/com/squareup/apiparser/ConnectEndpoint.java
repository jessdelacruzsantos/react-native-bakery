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

  public JSONObject toJson() {
    JSONObject root = new JSONObject();
    root.put("id", this.id);
    root.put("entity", this.entity);
    root.put("action", this.action);
    root.put("httpmethod", this.httpmethod);
    root.put("path", this.path);
    root.put("description", this.description);
    root.put("oauthpermissions", this.oauthpermissions.toArray(new String[this.oauthpermissions.size()]));
    root.put("inputtype", this.inputType);
    root.put("outputtype", this.outputType);

    if (!this.params.isEmpty()) {
      root.put("params", this.arrayifyFields(this.params));
    }

    root.put("responsefields", this.arrayifyFields(this.responsefields));
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
