package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.FieldElement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ConnectField {
  private final String name;
  private final String type;
  private final Boolean required;
  private final Boolean isArray;
  private final List<String> enumValues;
  private final int value;
  private final Map<String, String> docAnnotations;
  private final Map<String, Object> validations;

  public ConnectField(FieldElement field, Optional<ConnectEnum> enumm) {
    this.name = field.name();
    this.type = Protos.cleanName(field.type());
    this.required = field.label() == Field.Label.REQUIRED;
    this.isArray = field.label() == Field.Label.REPEATED;
    this.docAnnotations = DocString.parse(field.documentation());
    this.value = 0;
    final List<ConnectField> values = enumm.map(ConnectEnum::getValues).orElse(Collections.emptyList());
    this.enumValues = values.stream().map(ConnectField::getName).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    this.validations = ProtoOptions.validations(field.options());
  }

  // This constructor is called ONLY for fields that represent a value of an enum, such as USD.
  public ConnectField(String name, String type, int value, String documentation) {
    this.name = name;
    this.type = Protos.cleanName(type);
    this.value = value;
    this.required = false;
    this.isArray = isArray();
    this.enumValues = ImmutableList.of();
    this.validations = ImmutableMap.of();
    this.docAnnotations = DocString.parse(documentation);
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  public String getDescription() {
    return docAnnotations.getOrDefault("desc", "");
  }

  public Boolean isRequired() {
    return required;
  }

  public boolean isPathParam() {
    return this.docAnnotations.containsKey("pathparam");
  }

  public Boolean isArray() {
    return this.isArray;
  }

  public List<String> getEnumValues() {
    return enumValues == null ? Collections.emptyList() : enumValues;
  }

  // NB(alec): why isn't this called?
  public JSONObject toJson() {
    Optional<String> desc = Optional.ofNullable(this.docAnnotations.get("desc"));
    JSONObject json = new JSONObject();
    this.validations.entrySet().forEach(v -> json.put(v.getKey(), v.getValue()));
    return json.put("name", name)
        .put("type", type)
        .put("required", required)
        .put("isarray", isArray)
        .put("value", value)
        .put("description", desc.orElse(""))
        .put("ispathparam", isPathParam());
  }
}
