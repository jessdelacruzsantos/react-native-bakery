package com.squareup.apiparser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
import static com.squareup.apiparser.Json.GSON;
import static com.squareup.apiparser.ConnectType.FORMAT_MAP;
import static com.squareup.apiparser.ConnectType.TYPE_MAP;

public class ConnectField {
  private final boolean required;
  private final boolean isArray;
  private final boolean isMap;
  private final boolean isPathParam;
  private final String name;
  private final String type;
  private final List<ConnectEnumConstant> enumValues;
  private final Map<String, Object> validations;
  private final String description;
  private Group group = new Group();

  ConnectField(FieldElement element, Group defaultGroup, String type, Optional<ConnectEnum> enumm) {
    checkNotNull(element);
    checkNotNull(enumm);
    this.group.status = ProtoOptions.getReleaseStatus(element.options(), "common.field_status", defaultGroup.status);
    this.group.namespace = ProtoOptions.getStringValue(element.options(), "common.field_namespace").orElse(defaultGroup.namespace);
    this.description = new DocString(element.documentation()).getDescription();
    this.name = element.name();
    this.type = type;
    this.isArray = element.label() == Field.Label.REPEATED;
    this.isMap = type.startsWith("map<");
    this.required = element.label() == Field.Label.REQUIRED || ProtoOptions.isRequired(element);
    this.isPathParam = ProtoOptions.isPathParam(element);
    this.enumValues =
        enumm.map(ConnectEnum::getValues).orElse(Collections.emptyList());
    this.validations = ImmutableMap.copyOf(ProtoOptions.validations(element.options()));
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  String getDescription() {
    return this.description;
  }

  Map<String, Object> getValidations() {
    return this.validations;
  }

  Boolean isRequired() {
    return this.required;
  }

  boolean isPathParam() {
    return this.isPathParam;
  }

  Boolean isArray() {
    return this.isArray;
  }

  boolean isMap() {
    return this.isMap;
  }

  String mapValueType() {
    return findMapKeyAndValueTypes(type).getRight();
  }

  List<String> getEnumValues(Group group) {
    return this.enumValues.stream().filter(v -> group.shouldInclude(v.getGroup()))
        .map(ConnectEnumConstant::getName)
        .collect(Collectors.toList());
  }

  public Group getGroup() {
    return group;
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
