package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectDatatype extends ConnectType {
  private final List<ConnectField> fields = new ArrayList<>();

  protected ConnectDatatype(TypeElement rootType, String packageName, ConnectType parentType) {
    super(rootType, packageName, parentType);
  }

  public List<ConnectField> getFields() {
    return this.fields;
  }

  public boolean hasBodyParameters() {
    return fields.stream().anyMatch(f -> !f.isPathParam());
  }

  public boolean populateFields(ProtoIndex index) {
    MessageElement rootMessage = (MessageElement)this.rootType;
    rootMessage.fields().stream().forEach(f -> populateField(f, index));
    for(OneOfElement oneof : rootMessage.oneOfs()) {
      oneof.fields().stream().forEach(f -> populateField(f, index));
    }
    return true;
  }

  private void populateField(FieldElement field, ProtoIndex index) {
    String typeName = field.type();
    Optional<ConnectEnum> ce = index.getEnumType(typeName);
    this.fields.add(new ConnectField(field, ce));
  }

  // Converts the Datatype to a format that conforms to the Swagger 2.0 specification
  public JSONObject toJson() {
    JSONObject root = new JSONObject();
    root.put("type", "object");
    JSONObject datatypeProperties = new JSONObject();
    JSONArray datatypeRequireds = new JSONArray();

    for (ConnectField property : this.fields) {
      // Don't add include URL path parameters in datatype definitions
      if (property.isPathParam()) {
        continue;
      }

      if (property.isRequired()) {
        datatypeRequireds.put(property.getName());
      }

      System.out.println(property.getName() + " " + property.isArray());
      JSONObject datatypeProperty;
      if (property.isArray()) {
        datatypeProperty.put("type", "array");
        JSONObject arrayItems = new JSONObject();
        arrayItems = handleProperty(property, arrayItems);
        datatypeProperty.put("items", arrayItems);
        datatypeProperties.put(property.getName(), datatypeProperty);

        // THE FIELD IS NOT AN ARRAY
      } else {
        datatypeProperty = handleProperty(property, new JSONObject());
      }
      datatypeProperty.put("description", property.getDescription());
      datatypeProperties.put(property.getName(), datatypeProperty);
    }

    root.put("properties", datatypeProperties);
    if (datatypeRequireds.length() > 0) {
      root.put("required", datatypeRequireds);
    }
    root.put("description", docAnnotations.getOrDefault("desc", ""));

    return root;
  }

  private JSONObject handleProperty(ConnectField property, JSONObject rootObject) {
    Preconditions.checkNotNull(property);
    property.getValidations().entrySet().forEach(v-> rootObject.put(v.getKey(),v.getValue()));
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
    if (TYPE_MAP.containsKey(property.getType())) {
      return rootObject.put("type", TYPE_MAP.get(property.getType()));

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
