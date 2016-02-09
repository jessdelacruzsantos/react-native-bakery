package com.squareup.apiparser;

import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.FieldElement;

import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.json.JSONObject;


public class ConnectField {
  private String name = "";
  private String type = "";
  private int value = 0;
  private Boolean required = false;
  private Boolean isArray = false;
  private Boolean isPathParam = false;
  private List<String> enumValues = new ArrayList<String>();
  private Map<String, String> docAnnotations = new HashMap<String, String>();
  private FieldElement rootField;


  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  public String getDescription() {
    if (this.docAnnotations.containsKey("desc")) {
      return docAnnotations.get("desc");
    } else {
      return "";
    }
  }

  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }

  public boolean isPathParam() {
    return this.docAnnotations.containsKey("pathparam");
  }

  public Boolean isArray() {
    return (this.rootField.label() == Field.Label.REPEATED);
  }

  public List<String> getEnumValues() {
    return enumValues;
  }

  public ConnectField(FieldElement field) {
    this.name = field.name();
    this.rootField = field;
    this.type = this.processType(rootField.type());
    this.parseDocumentationString(field.documentation());
    if (field.label() == Field.Label.REQUIRED) {
      this.setRequired(true);
    }
  }

  // This constructor is called ONLY for fields that represent a request parameter for an endpoint
  // that is ALSO an enum. It exists because in swagger, enum request parameters MUST be defined
  // in-line, and cannot refer to a schema.
  public ConnectField(FieldElement field, ConnectEnum enumm) {
    this.name = field.name();
    this.rootField = field;
    this.type = this.processType(rootField.type());
    this.parseDocumentationString(field.documentation());
    this.isArray = (field.label() == Field.Label.REPEATED);
    if (field.label() == Field.Label.REQUIRED) {
      this.setRequired(true);
    }

    for (ConnectField enumValue : enumm.getValues()) {
      this.enumValues.add(enumValue.getName());
    }
  }

  // This constructor is called ONLY for fields that represent a value of an enum, such as USD.
  public ConnectField(String name, String type, int value, String documentation) {
    this.name = name;
    this.type = this.processType(type);
    this.value = value;
    this.parseDocumentationString(documentation);
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
        System.err.println("ERROR! Multiple doc annotations of same type found for field " + this.getName());
      }

      docAnnotations.put(keyword, entry.replaceFirst(keyword, "").trim());
    }
  }

  private String processType(String type) {
    type = type.replaceFirst("resources.", "");
    type = type.replaceFirst("actions.", "");
    type = type.replaceAll("\\.", "");
    return type;
  }

  public JSONObject toJson() {
    JSONObject fieldJson = new JSONObject();
    fieldJson.put("name", this.name);
    fieldJson.put("type", this.type);
    if (this.docAnnotations.containsKey("desc")) {
      fieldJson.put("description", this.docAnnotations.get("desc"));
    } else {
      fieldJson.put("description", "");
    }

    fieldJson.put("required", this.required);
    fieldJson.put("isarray", this.isArray);
    fieldJson.put("value", this.value);
    fieldJson.put("ispathparam", this.isPathParam);
    return fieldJson;
  }
}
