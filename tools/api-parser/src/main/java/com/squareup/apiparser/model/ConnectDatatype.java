package com.squareup.apiparser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.squareup.apiparser.Json.GSON;

class ConnectDatatype extends ConnectType {
  private List<ConnectField> fields = new ArrayList<>();
  private final Optional<JsonObject> example;
  private final Optional<String> exampleType;
  private final Optional<JsonElement> sdkSamples;
  private final boolean ignoreOneofs;
  private ProtoIndexer index;

  ConnectDatatype(
      Group defaultGroup,
      TypeElement rootType,
      String packageName,
      Optional<ConnectType> parentType,
      ExampleResolver exampleResolver,
      boolean ignoreOneofs) {
    super(rootType, packageName, parentType);

    this.group.status = ProtoOptions.getReleaseStatus(rootType.options(), "common.message_status", defaultGroup.status);
    this.group.namespace = ProtoOptions.getStringValue(rootType.options(), "common.message_namespace").orElse(defaultGroup.namespace);

    this.example = ProtoOptions.exampleFilename(rootType.options())
        .map(exampleResolver::loadExample);
    this.exampleType = ProtoOptions.exampleType(rootType.options());
    this.sdkSamples = ProtoOptions.sdkSampleDirectory(rootType.options())
        .map(SdkSampleDirectoryResolver.resolveSamplePath(rootType.name()));
    this.ignoreOneofs = ignoreOneofs;
  }

  void validate() {
    if (!this.group.isCustomerFacing()){
      return;
    }

    Validator.validateDescription(this.identifier, this.description);

    this.fields.
      stream().
      filter(field -> group.shouldInclude(field.getGroup())).
      forEach(field -> {
        List<String> enumValues = field.getEnumValues(group);

        if (enumValues.isEmpty()) {
          String typeName = field.isMap() ? field.mapValueType() : field.getType();
          Validator.validateDefinitionExists(this.identifier, typeName, this.index);
        }
    });
  }

  List<ConnectField> getFields() {
    return this.fields;
  }

  boolean hasBodyParameters() {
    return fields.stream().anyMatch(f -> !f.isPathParam());
  }

  void populateFields(ProtoIndexer index) throws IllegalUseOfOneOfException {
    MessageElement rootMessage = (MessageElement) this.rootType;
    this.index = index;
    this.fields = rootMessage.fields()
        .stream()
        .map(field -> new ConnectField(
            field,
            this.group,
            getType(index, field),
            index.getEnumType(field.type()),
            this.name))
        .collect(Collectors.toList());

    if (!ignoreOneofs && !rootMessage.oneOfs().isEmpty()) {
      throw new IllegalUseOfOneOfException(rootMessage);
    }
  }

  private String getType(ProtoIndexer index, FieldElement f) {
    return index.getDatatypes().values().stream()
        .filter(connectDatatype -> connectDatatype.getType().equals(f.type()))
        .findFirst()
        .map(ConnectType::getName)
        .orElse(Protos.cleanName(f.type()));
  }

  // Converts the Datatype to a format that conforms to the Swagger 2.0 specification
  JsonObject toJson(Group group) {
    JsonObject root = new JsonObject();
    root.addProperty("type", "object");
    JsonObject properties = new JsonObject();

    fields.stream()
        .filter(field -> !field.isPathParam() && group.shouldInclude(field.getGroup()))
        .forEach(field -> {
          properties.add(field.getName(), toJsonField(field, group));
        });

    List<ConnectField> requiredFields = fields.stream()
        .filter(f -> !f.isPathParam() && f.isRequired())
        .collect(Collectors.toList());

    // Check that only visible fields can be required
    List<ConnectField> requiredInvisibleFields = requiredFields.stream()
        .filter(f -> !this.group.shouldInclude(f.getGroup()))
        .collect(Collectors.toList());

    if (!requiredInvisibleFields.isEmpty()) {
      String message =
          String.format("%s types cannot have required fields with less visibility: %s",
              this.group.status,
              String.join(", ", requiredInvisibleFields.stream()
                  .map(f -> String.format("%s (%s)", f.getName(), f.getGroup().status))
                  .collect(Collectors.toList())));
      throw new InvalidSpecException.Builder(message).build();
    }

    JsonArray requiredNames = new JsonArray();
    requiredFields.stream()
        .map(ConnectField::getName)
        .forEach(requiredNames::add);

    if (requiredNames.size() > 0) {
      root.add("required", requiredNames);
    }

    root.add("properties", properties);
    root.addProperty("description", this.description);
    root.addProperty("x-release-status", this.group.status.name());

    // (TODO) we may want to add Square-Version header to examples too.
    this.example.ifPresent(e -> root.add("example", e));

    this.exampleType.ifPresent(e -> root.addProperty("example_type", e));

    this.sdkSamples.ifPresent(e -> root.add("x-sq-sdk-sample-code", e));

    return root;
  }

  private JsonObject toJsonField(ConnectField field, Group group) {
    JsonObject json = GSON.toJsonTree(field.getValidations()).getAsJsonObject();
    //Generate json for current field
    // We need to declare enums locally to work around swagger-codegen
    List<String> enumValues = field.getEnumValues(group);
    if (!enumValues.isEmpty()) {
      json.addProperty("type", "string");
      json.add("enum", GSON.toJsonTree(enumValues));
    }
    else if (field.isMap()) {
      json.addProperty("type", "object");

      JsonObject nested = new JsonObject();

      String mapValueType = field.mapValueType();

      String propertyKey = (TYPE_MAP.containsKey(mapValueType)) ? "type" : "$ref";
      String propertyValue = TYPE_MAP.getOrDefault(mapValueType, "#/definitions/" + mapValueType);
      nested.addProperty(propertyKey, propertyValue);
      json.add("additionalProperties", nested);
    }
    else {
      String type = field.getType();
      String value = TYPE_MAP.getOrDefault(type, "#/definitions/" + type);
      String key = (TYPE_MAP.containsKey(type)) ? "type" : "$ref";
      json.addProperty(key, value);
      if (key.equals("type") && FORMAT_MAP.containsKey(type)) {
        json.addProperty("format", FORMAT_MAP.get(type));
      }
    }

    // Add another layer if it is an array
    if (field.isArray()){
      JsonObject arrayJson = new JsonObject();
      arrayJson.addProperty("type", "array");
      arrayJson.add("items", json);
      json = arrayJson;
    }

    json.addProperty("description", field.getDescription());
    return json;
  }
}
