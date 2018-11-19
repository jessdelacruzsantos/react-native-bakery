package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

class ConnectEnum extends ConnectType {
  private final List<ConnectEnumConstant> values;

  ConnectEnum(Group defaultGroup, EnumElement enumm, String packageName,
              Optional<ConnectType> parentType) {
    super(enumm, packageName, parentType);
    this.group.status = ProtoOptions.getReleaseStatus(enumm.options(), "common.enum_status", defaultGroup.status);
    this.group.namespace = ProtoOptions.getStringValue(enumm.options(), "common.enum_namespace").orElse(defaultGroup.namespace);

    this.values = ImmutableList.copyOf(enumm.constants()
        .stream()
        .map(constantElement -> new ConnectEnumConstant(constantElement, this.group))
        .collect(toList()));
  }

  List<ConnectEnumConstant> getValues() {
    return values;
  }

  JsonObject toJson(Group group) {
    JsonArray enumValues = new JsonArray();
    this.values.stream()
        .filter(enumValue -> group.shouldInclude(enumValue.getGroup()))
        .map(ConnectEnumConstant::getName)
        .forEach(enumValues::add);

    JsonObject json = new JsonObject();
    json.addProperty("type", "string");
    json.add("enum", enumValues);
    json.addProperty("description", this.getDescription());
    json.addProperty("x-release-status", this.getGroup().status.name());

    return json;
  }

  private String getDescription() {
    return docAnnotations.getOrDefault("desc", "");
  }
}
