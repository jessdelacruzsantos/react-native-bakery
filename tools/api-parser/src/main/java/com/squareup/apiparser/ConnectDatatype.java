package com.squareup.apiparser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.squareup.apiparser.Json.GSON;

public class ConnectDatatype extends ConnectType {
  private final List<ConnectField> fields = new ArrayList<>();
  private final Optional<JsonObject> example;

  ConnectDatatype(TypeElement rootType, String packageName, Optional<ConnectType> parentType,
      ExampleResolver exampleResolver) {
    super(rootType, packageName, parentType);

    this.example = ProtoOptions.exampleFilename(rootType.options())
        .map(exampleResolver::loadExample);
  }

  public List<ConnectField> getFields() {
    return this.fields;
  }

  public boolean hasBodyParameters() {
    return fields.stream().anyMatch(f -> !f.isPathParam());
  }

  public void populateFields(ProtoIndex index) throws IllegalUseOfOneOfException {
    MessageElement rootMessage = (MessageElement) this.rootType;
    final Consumer<FieldElement> addField =
        f -> fields.add(new ConnectField(f, index.getEnumType(f.type())));
    rootMessage.fields().stream().forEach(addField);

    if (!rootMessage.oneOfs().isEmpty()) {
      throw new IllegalUseOfOneOfException(rootMessage);
    }
  }

  // Converts the Datatype to a format that conforms to the Swagger 2.0 specification
  public JsonObject toJson() {
    JsonObject root = new JsonObject();
    root.addProperty("type", "object");
    JsonObject properties = new JsonObject();

    fields.stream()
        .filter(f -> !f.isPathParam())
        .forEach(f -> {
          JsonObject property = f.isArray()
              ? handleArray(f)
              : handleProperty(f);
          property.addProperty("description", f.getDescription());
          properties.add(f.getName(), property);
        });

    JsonArray requiredNames = new JsonArray();
    fields.stream()
        .filter(f -> !f.isPathParam() && f.isRequired())
        .map(ConnectField::getName)
        .forEach(requiredNames::add);

    if (requiredNames.size() > 0) {
      root.add("required", requiredNames);
    }

    root.add("properties", properties);
    root.addProperty("description", docAnnotations.getOrDefault("desc", ""));

    this.example.ifPresent(e -> root.add("example", e));

    return root;
  }

  private JsonObject handleArray(ConnectField field) {
    checkNotNull(field);

    JsonObject json = new JsonObject();
    json.addProperty("type", "array");
    json.add("items", handleProperty(field));
    return json;
  }

  private JsonObject handleProperty(ConnectField field) {
    checkNotNull(field);
    JsonObject json = GSON.toJsonTree(field.getValidations()).getAsJsonObject();

    // We need to declare enums locally to work around swagger-codegen
    if (!field.getEnumValues().isEmpty()) {
      json.addProperty("type", "string");
      json.add("enum", GSON.toJsonTree(field.getEnumValues()));
      return json;
    }

    final String type = field.getType();
    final String value = TYPE_MAP.getOrDefault(type, "#/definitions/" + type);
    final String key = (TYPE_MAP.containsKey(type)) ? "type" : "$ref";
    json.addProperty(key, value);
    return json;
  }
}
