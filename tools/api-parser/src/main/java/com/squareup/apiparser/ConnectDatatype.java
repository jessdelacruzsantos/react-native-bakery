package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

  public void populateFields(ProtoIndex index) throws IllegalUseOfOneOfException {
    MessageElement rootMessage = (MessageElement)this.rootType;
    final Consumer<FieldElement> addField = f -> fields.add(new ConnectField(f, index.getEnumType(f.type())));
    rootMessage.fields().stream().forEach(addField);

    if (!rootMessage.oneOfs().isEmpty()) {
      throw new IllegalUseOfOneOfException(rootMessage);
    }
  }

  // Converts the Datatype to a format that conforms to the Swagger 2.0 specification
  public JSONObject toJson() {
    JSONObject root = new JSONObject();
    root.put("type", "object");
    JSONObject properties = new JSONObject();

    fields.stream().filter(f -> !f.isPathParam()).forEach(f -> {
      JSONObject property = f.isArray() ? handleArray(f) : handleProperty(f);
      property.put("description", f.getDescription());
      properties.put(f.getName(), property);
    });

    final List<String> requiredNames = fields.stream()
        .filter(f -> !f.isPathParam() && f.isRequired())
        .map(ConnectField::getName).collect(Collectors.toList());

    if (!requiredNames.isEmpty()) {
      root.put("required", new JSONArray(requiredNames));
    }
    return root.put("properties", properties)
        .put("description", docAnnotations.getOrDefault("desc", ""));
  }

  private JSONObject handleArray(ConnectField field) {
    return new JSONObject().put("type", "array").put("items", handleProperty(field));
  }

  private JSONObject handleProperty(ConnectField field) {
    Preconditions.checkNotNull(field);
    JSONObject json = new JSONObject();
    field.getValidations().entrySet().forEach(v-> json.put(v.getKey(),v.getValue()));

    // We need to declare enums locally to work around swagger-codegen
    if (!field.getEnumValues().isEmpty()) {
      return json.put("type", "string")
          .put("enum", new JSONArray(field.getEnumValues().toArray()));
    }

    final String type = field.getType();
    final String value = TYPE_MAP.getOrDefault(type, "#/definitions/" + type);
    final String key = (TYPE_MAP.containsKey(type)) ? "type" : "$ref";
    return json.put(key, value);
  }
}
