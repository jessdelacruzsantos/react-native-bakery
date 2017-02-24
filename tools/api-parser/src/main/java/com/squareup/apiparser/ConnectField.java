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
import static java.util.stream.Collectors.toList;

public class ConnectField {
  private final boolean required;
  private final boolean isArray;
  private final boolean isPathParam;
  private final String name;
  private final String type;
  private final List<String> enumValues;
  private final Map<String, String> docAnnotations;
  private final Map<String, Object> validations;

  ConnectField(FieldElement field, Optional<ConnectEnum> enumm) {
    checkNotNull(field);
    checkNotNull(enumm);
    this.name = field.name();
    this.type = Protos.cleanName(field.type());
    this.isArray = field.label() == Field.Label.REPEATED;
    this.required = field.label() == Field.Label.REQUIRED || ProtoOptions.isRequired(field);
    this.isPathParam = ProtoOptions.isPathParam(field);
    this.docAnnotations = new DocString(field.documentation()).getAnnotations();
    final List<ConnectField> values =
        enumm.map(ConnectEnum::getValues).orElse(Collections.emptyList());
    this.enumValues = ImmutableList.copyOf(values.stream()
        .map(ConnectField::getName)
        .collect(toList()));
    this.validations = ImmutableMap.copyOf(ProtoOptions.validations(field.options()));
  }

  // This constructor is called ONLY for fields that represent a value of an enum, such as USD.
  ConnectField(String name, String type, String documentation) {
    this.name = checkNotNull(name);
    this.type = Protos.cleanName(checkNotNull(type));
    this.required = false;
    this.isArray = isArray();
    this.enumValues = ImmutableList.of();
    this.validations = ImmutableMap.of();
    this.docAnnotations = new DocString(checkNotNull(documentation)).getAnnotations();
    this.isPathParam = false;
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  String getDescription() {
    return docAnnotations.getOrDefault("desc", "");
  }

  Map<String, Object> getValidations() {
    return validations;
  }

  Boolean isRequired() {
    return required;
  }

  boolean isPathParam() {
    return this.isPathParam;
  }

  Boolean isArray() {
    return this.isArray;
  }

  List<String> getEnumValues() {
    return enumValues == null ? Collections.emptyList() : enumValues;
  }
}
