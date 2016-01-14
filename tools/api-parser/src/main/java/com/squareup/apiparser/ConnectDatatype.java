package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectDatatype {
  private String id = "";
  private String name = "";
  private String description = "";
  private List<ConnectField> fields;
  private static final Map<String, String> typeMap;
  static {
    Map<String, String> aMap = new HashMap<String, String>();
    aMap.put("int32", "integer");
    aMap.put("int64", "integer");
    aMap.put("bool", "boolean");
    aMap.put("string", "string");
    typeMap = aMap;
  }

  public ConnectDatatype(MessageElement message, String id) {
    this.name = message.name();
    this.fields = new ArrayList<ConnectField>();
    this.id = id;
    for(FieldElement field : message.fields()) {
      this.fields.add(new ConnectField(field));
    }
    for(OneOfElement oneof : message.oneOfs()) {
      for (FieldElement field : oneof.fields()) {
        this.fields.add(new ConnectField(field));
      }
    }

    String[] comments = {""};
    if (message.documentation().contains("--")) {
      String publicDocString = message.documentation().split("--")[1];
      comments = publicDocString.split("\n@");
      for (String entry : comments) {
        String keyword = entry.split(" ")[0];
        switch (keyword) {
          case "desc":
            this.setDescription(entry.replaceFirst("desc", "").trim());
            break;
        }
      }
    }
  }

  // Converts the Datatype to a format that conforms to the Swagger 2.0 specification
  public JSONObject toJson() {

    JSONObject root = new JSONObject();
    JSONObject datatypeName = new JSONObject();
    datatypeName.put("type", "object");
    JSONObject datatypeProperties = new JSONObject();

    for (ConnectField property : this.fields) {
      JSONObject datatypeProperty = new JSONObject();

      // If the field has a proto base type, assign it the corresponding swagger base type
      if (typeMap.containsKey(property.getType())) {
        datatypeProperty.put("type", typeMap.get(property.getType()));

        // Otherwise, assign it the appropriate custom swagger resource
      } else {
        String typeName = property.getType();

        // When specifying the name of the resource, get rid of pointless proto prefixes
        typeName = typeName.replaceFirst("resources.", "");
        typeName = typeName.replaceFirst("actions.", "");

        datatypeProperty.put("$ref", "#/definitions/" + typeName);
      }
      datatypeProperties.put(property.getName(), datatypeProperty);
    }

    datatypeName.put("properties", datatypeProperties);
    datatypeName.put("description", this.description);
    root.put(this.name, datatypeName);
    return root;
  }

  private JSONArray arrayifyFields(List<ConnectField> fields) {
    JSONArray array = new JSONArray();
    for (ConnectField field : fields) {
      array.put(field.toJson());
    }
    return array;
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
