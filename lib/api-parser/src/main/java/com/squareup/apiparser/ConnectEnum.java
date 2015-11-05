package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class ConnectEnum {
  private String name = "";
  private String description = "";
  private String id = "";
  private List<ConnectField> values;

  public ConnectEnum(EnumElement enumm, String id) {
    this.name = enumm.name();
    this.values = new ArrayList<ConnectField>();
    this.id = id;
    for (EnumConstantElement field : enumm.constants()) {
      values.add(new ConnectField(field.name(), "", field.documentation()));
    }
    this.parseDocumentationString(enumm.documentation());
  }

  public JSONObject toJson() {
    JSONObject root = new JSONObject();
    root.put("name", this.name);
    root.put("description", this.description);
    root.put("id", this.id);
    root.put("values", this.arrayifyFields(this.values));
    return root;
  }

  private JSONArray arrayifyFields(List<ConnectField> fields) {
    JSONArray array = new JSONArray();
    for (ConnectField field : fields) {
      array.put(field.toJson());
    }
    return array;
  }

  private void parseDocumentationString(String docString) {
    String[] docComponents = ConnectAPIParser.parseDocString(docString);
    for (String entry : docComponents) {
      String keyword = entry.split(" ")[0];
      switch (keyword) {
        case "desc":
          this.setDescription(entry.replaceFirst("desc", "").trim());
        default:
          break;
      }
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
