package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectDatatype extends ConnectType {
  private String description = "";
  private List<ConnectField> fields;


  public ConnectDatatype(MessageElement message, String packageName, ConnectType parentType) {
    super(message, packageName, parentType);
    this.parseDocumentationString(message.documentation());
    this.fields = new ArrayList<ConnectField>();
  }

  public List<ConnectField> getFields() {
    return this.fields;
  }

  // Indicates whether the datatype has any fields that AREN'T just path parameters.
  public boolean hasFields() {
    for (ConnectField field : this.fields) {
      if (!field.isPathParam()) {
        return true;
      }
    }
    return false;
  }

  public boolean populateFields(ProtoIndex index) {
    MessageElement rootMessage = (MessageElement)this.rootType;
    for (FieldElement fe : rootMessage.fields()) {
      this.populateField(fe, index);
    }
    for(OneOfElement oneof : rootMessage.oneOfs()) {
      for (FieldElement fe : oneof.fields()) {
        this.populateField(fe, index);
      }
    }
    return true;
  }

  private void populateField(FieldElement field, ProtoIndex index) {
    String typeName = field.type();
    if (!ConnectType.typeMap.containsKey(typeName)) {
      ConnectType ct = index.getType(typeName);

      // If the field's type IS an enum...
      if (ct != null && ct instanceof ConnectEnum) {
        this.fields.add(new ConnectField(field, (ConnectEnum)ct));
      } else {
        this.fields.add(new ConnectField(field));
      }
    } else {
      this.fields.add(new ConnectField(field));
    }
  }

  // Converts the Datatype to a format that conforms to the Swagger 2.0 specification
  public JSONObject toJson() {

    JSONObject root = new JSONObject();
    root.put("type", "object");
    JSONObject datatypeProperties = new JSONObject();

    for (ConnectField property : this.fields) {

      // Don't add include URL path parameters in datatype definitions
      if (property.isPathParam()) {
        continue;
      }

      JSONObject datatypeProperty = new JSONObject();

      datatypeProperty.put("description", property.getDescription());

      // THE FIELD IS AN ARRAY. DECLARATION CHANGES SOMEWHAT
      if (property.isArray()) {
        datatypeProperty.put("type", "array");
        JSONObject arrayItems = new JSONObject();
        arrayItems = handleProperty(property, arrayItems);
        datatypeProperty.put("items", arrayItems);
        datatypeProperties.put(property.getName(), datatypeProperty);

        // THE FIELD IS NOT AN ARRAY
      } else {
        datatypeProperty = handleProperty(property, datatypeProperty);
        datatypeProperties.put(property.getName(), datatypeProperty);
      }
    }

    root.put("properties", datatypeProperties);
    if (this.docAnnotations.containsKey("desc")) {
      root.put("description", this.docAnnotations.get("desc"));
    } else {
      root.put("description", "");
    }

    return root;
  }

  private JSONObject handleProperty(ConnectField property, JSONObject rootObject) {

    // This field's type is an enum. We need to declare it locally.
    if (!property.getEnumValues().isEmpty()) {
      rootObject.put("type", "string");
      JSONArray enumValues = new JSONArray();
      for (String enumValue : property.getEnumValues()) {
        enumValues.put(enumValue);
      }
      rootObject.put("enum", enumValues);
      return rootObject;
    }

    // If the field has a proto base type, assign it the corresponding swagger base type
    if (typeMap.containsKey(property.getType())) {
      rootObject.put("type", typeMap.get(property.getType()));
      return rootObject;

      // Otherwise, assign it the appropriate custom swagger resource
    } else {
      String typeName = property.getType();

      // When specifying the name of the resource, get rid of pointless proto prefixes
      typeName = typeName.replaceFirst("resources.", "");
      typeName = typeName.replaceFirst("actions.", "");

      rootObject.put("$ref", "#/definitions/" + typeName.replace("\\.", ""));
      return rootObject;
    }
  }
}
