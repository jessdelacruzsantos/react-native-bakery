package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.FieldElement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ConnectField {
  private final boolean required;
  private final boolean isArray;
  private final int value;
  private final String name;
  private final String type;
  private final List<String> enumValues;
  private final Map<String, String> docAnnotations;
  private final Map<String, Object> validations;

  public ConnectField(FieldElement field, Optional<ConnectEnum> enumm) {
    checkNotNull(field);
    checkNotNull(enumm);
    this.name = field.name();
    this.type = Protos.cleanName(field.type());
    this.isArray = field.label() == Field.Label.REPEATED;
    this.required = field.label() == Field.Label.REQUIRED || ProtoOptions.isRequired(field);
    this.docAnnotations = new DocString(field.documentation()).getAnnotations();
    this.value = 0;
    final List<ConnectField> values =
        enumm.map(ConnectEnum::getValues).orElse(Collections.emptyList());
    this.enumValues = values.stream()
        .map(ConnectField::getName)
        .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    this.validations = ImmutableMap.copyOf(ProtoOptions.validations(field.options()));
  }

  // This constructor is called ONLY for fields that represent a value of an enum, such as USD.
  public ConnectField(String name, String type, int value, String documentation) {
    this.name = checkNotNull(name);
    this.type = Protos.cleanName(checkNotNull(type));
    this.value = value;
    this.required = false;
    this.isArray = isArray();
    this.enumValues = ImmutableList.of();
    this.validations = ImmutableMap.of();
    this.docAnnotations = new DocString(checkNotNull(documentation)).getAnnotations();
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

  public Map<String, Object> getValidations() {
    return validations;
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
}
