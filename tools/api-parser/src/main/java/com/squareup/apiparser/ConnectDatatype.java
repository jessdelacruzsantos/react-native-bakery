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
  private static final String SDK_SAMPLE_FIELD_NAME = "x-sq-sdk-sample-code";

  private List<ConnectField> fields = new ArrayList<>();
  private final Optional<JsonObject> example;
  private final Optional<String> exampleType;
  private final Optional<JsonElement> sdkSamples;
  private final boolean ignoreOneofs;

  ConnectDatatype(
      ReleaseStatus releaseStatus,
      String namespace,
      TypeElement rootType,
      String packageName,
      Optional<ConnectType> parentType,
      ExampleResolver exampleResolver,
      boolean ignoreOneofs) {
    super(releaseStatus, namespace, rootType, packageName, parentType);

    this.example = ProtoOptions.exampleFilename(rootType.options())
        .map(exampleResolver::loadExample);
    this.exampleType = ProtoOptions.exampleType(rootType.options());
    this.sdkSamples = ProtoOptions.sdkSampleDirectory(rootType.options())
        .map(SdkSampleDirectoryResolver.resolveSamplePath(rootType.name()));
    this.ignoreOneofs = ignoreOneofs;
  }

  List<ConnectField> getFields() {
    return this.fields;
  }

  boolean hasBodyParameters() {
    return fields.stream().anyMatch(f -> !f.isPathParam());
  }

  void populateFields(ProtoIndex index) throws IllegalUseOfOneOfException {
    MessageElement rootMessage = (MessageElement) this.rootType;

    this.fields = rootMessage.fields()
        .stream()
        .map(f -> new ConnectField(
            getApiReleaseStatus(f),
            getNamespace(f),
            f,
            getType(index, f),
            index.getEnumType(f.type())))
        .collect(Collectors.toList());

    if (!ignoreOneofs && !rootMessage.oneOfs().isEmpty()) {
      throw new IllegalUseOfOneOfException(rootMessage);
    }
  }

  private ReleaseStatus getApiReleaseStatus(FieldElement f) {
    return ProtoOptions.getExplicitReleaseStatus(f.options(), "common.field_status")
        .orElse(this.getReleaseStatus());
  }

  private String getNamespace(FieldElement f) {
    return ProtoOptions.getStringValue(f.options(), "common.field_namespace").orElse(this.getNamespace());
  }

  private String getType(ProtoIndex index, FieldElement f) {
    return index.getDatatypes().values().stream()
        .filter(connectDatatype -> connectDatatype.getType().equals(f.type()))
        .findFirst()
        .map(ConnectType::getName)
        .orElse(Protos.cleanName(f.type()));
  }

  // Converts the Datatype to a format that conforms to the Swagger 2.0 specification
  JsonObject toJson(ReleaseStatus releaseStatus, String namespace) {
    JsonObject root = new JsonObject();
    root.addProperty("type", "object");
    JsonObject properties = new JsonObject();

    fields.stream()
        .filter(f -> !f.isPathParam())
        .filter(f -> releaseStatus.shouldInclude(f.getReleaseStatus()) && Namespace.isMatched(f.getReleaseStatus(), namespace, f.getNamespace()))
        .forEach(f -> {
          JsonObject property = f.isArray()
              ? handleArray(f, releaseStatus)
              : handleProperty(f, releaseStatus);
          property.addProperty("description", f.getDescription());
          properties.add(f.getName(), property);
        });

    List<ConnectField> requiredFields = fields.stream()
        .filter(f -> !f.isPathParam() && f.isRequired())
        .collect(Collectors.toList());

    // Check that only visible fields can be required
    List<ConnectField> requiredInvisibleFields = requiredFields.stream()
        .filter(f -> !this.getReleaseStatus().shouldInclude(f.getReleaseStatus()))
        .collect(Collectors.toList());
    if (!requiredInvisibleFields.isEmpty()) {
      String message =
          String.format("%s types cannot have required fields with less visibility: %s",
              this.getReleaseStatus(),
              String.join(", ", requiredInvisibleFields.stream()
                  .map(f -> String.format("%s (%s)", f.getName(), f.getReleaseStatus()))
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
    root.addProperty("description", docAnnotations.getOrDefault("desc", ""));
    root.addProperty("x-release-status", this.getReleaseStatus().name());

    // (TODO) we may want to add Square-Version header to examples too.
    this.example.ifPresent(e -> root.add("example", e));

    this.exampleType.ifPresent(e -> root.addProperty("example_type", e));

    this.sdkSamples.ifPresent(e -> root.add(SDK_SAMPLE_FIELD_NAME, e));

    return root;
  }

  private JsonObject handleArray(ConnectField field, ReleaseStatus releaseStatus) {
    checkNotNull(field);

    JsonObject json = new JsonObject();
    json.addProperty("type", "array");
    json.add("items", handleProperty(field, releaseStatus));
    return json;
  }

  private JsonObject handleProperty(ConnectField field, ReleaseStatus releaseStatus) {
    checkNotNull(field);
    JsonObject json = GSON.toJsonTree(field.getValidations()).getAsJsonObject();

    // We need to declare enums locally to work around swagger-codegen
    List<String> enumValues = field.getEnumValues(releaseStatus);
    if (!enumValues.isEmpty()) {
      json.addProperty("type", "string");
      json.add("enum", GSON.toJsonTree(enumValues));
      return json;
    }

    if (field.isMap()) {
      json.addProperty("type", "object");

      JsonObject nested = new JsonObject();

      String mapValueType = field.mapValueType();

      String propertyKey = (TYPE_MAP.containsKey(mapValueType)) ? "type" : "$ref";
      String propertyValue = TYPE_MAP.getOrDefault(mapValueType, "#/definitions/" + mapValueType);
      nested.addProperty(propertyKey, propertyValue);
      json.add("additionalProperties", nested);
      return json;
    }

    String type = field.getType();
    String value = TYPE_MAP.getOrDefault(type, "#/definitions/" + type);
    String key = (TYPE_MAP.containsKey(type)) ? "type" : "$ref";
    json.addProperty(key, value);
    if (key.equals("type") && FORMAT_MAP.containsKey(type)) {
      json.addProperty("format", FORMAT_MAP.get(type));
    }
    return json;
  }
}
