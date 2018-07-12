package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.FieldElement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import static com.google.common.base.Preconditions.checkNotNull;

public class ConnectField {
  private final boolean required;
  private final boolean isArray;
  private final boolean isPathParam;
  private final String name;
  private final String type;
  private final List<ConnectField> enumValues;
  private final Map<String, String> docAnnotations;
  private final Map<String, Object> validations;
  private ReleaseStatus releaseStatus;

  ConnectField(ReleaseStatus releaseStatus, FieldElement field, String type,
      Optional<ConnectEnum> enumm) {
    checkNotNull(field);
    checkNotNull(enumm);
    this.releaseStatus = releaseStatus;
    this.name = field.name();
    this.type = type;
    this.isArray = field.label() == Field.Label.REPEATED;
    this.required = field.label() == Field.Label.REQUIRED || ProtoOptions.isRequired(field);
    this.isPathParam = ProtoOptions.isPathParam(field);
    this.docAnnotations = new DocString(field.documentation()).getAnnotations();
    this.enumValues =
        enumm.map(ConnectEnum::getValues).orElse(Collections.emptyList());
    this.validations = ImmutableMap.copyOf(ProtoOptions.validations(field.options()));
  }

  // This constructor is called ONLY for fields that represent a value of an enum, such as USD.
  ConnectField(ReleaseStatus releaseStatus, String name, String type, String documentation) {
    this.releaseStatus = checkNotNull(releaseStatus);
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

  boolean isMap() {
    return type.startsWith("map<");
  }

  String mapValueType() {
    return findMapKeyAndValueTypes(type).getRight();
  }

  List<String> getEnumValues(ReleaseStatus releaseStatus) {
    return this.enumValues.stream().filter(v -> releaseStatus.shouldInclude(v.getReleaseStatus()))
        .map(ConnectField::getName)
        .collect(Collectors.toList());
  }

  public ReleaseStatus getReleaseStatus() {
    return releaseStatus;
  }

  private Pair<String, String> findMapKeyAndValueTypes(String type) {
    Matcher matcher = Pattern.compile("map<(.*),(.*)>").matcher(type);
    Preconditions.checkState(matcher.find(), "Type %s did not match map<> pattern.", type);
    String mapKeyType = StringUtils.strip(matcher.group(1));
    Preconditions.checkState(mapKeyType.equals("string"),
        "Only string keys supported in swagger maps, but got %s. full type %s, fieldName=%s",
        mapKeyType, type, name);
    return Pair.of(StringUtils.strip(matcher.group(1)), StringUtils.strip(matcher.group(2)));
  }
}
