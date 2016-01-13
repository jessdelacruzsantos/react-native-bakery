package com.squareup.apiparser;

import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.FieldElement;
import org.json.JSONObject;


public class ConnectField {
  private String name = "";
  private String type = "";
  private int value = 0;
  private String description = "";
  private Boolean required = false;
  private Boolean isArray = false;

  private String typeId = "";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }

  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }

  public ConnectField(String name, String type, int value, String documentation) {
    this.name = name;
    this.type = type;
    this.value = value;
    this.parseDocumentationString(documentation);
  }

  public ConnectField(FieldElement field) {
    this.name = field.name();
    this.type = field.type().toString();
    this.parseDocumentationString(field.documentation());
    this.isArray = (field.label() == Field.Label.REPEATED);
    if (field.label() == Field.Label.REQUIRED) {
      this.setRequired(true);
    }
  }

  private void parseDocumentationString(String docString) {

    String[] docComponents = ConnectAPIParser.parseDocString(docString);
    for (String entry : docComponents) {
      String keyword = entry.split(" ")[0];
      switch (keyword) {
        case "desc":
          this.setDescription(entry.replaceFirst("desc", "").trim());
          break;
        default:
          break;
      }
    }
  }

  public JSONObject toJson() {
    JSONObject fieldJson = new JSONObject();
    fieldJson.put("name", this.name);
    fieldJson.put("type", this.type);
    fieldJson.put("description", this.description);
    fieldJson.put("required", this.required);
    fieldJson.put("isarray", this.isArray);
    fieldJson.put("value", this.value);
    return fieldJson;
  }
}
