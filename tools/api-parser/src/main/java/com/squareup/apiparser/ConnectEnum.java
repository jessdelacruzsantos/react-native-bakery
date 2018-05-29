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
  private final List<ConnectField> values;

  ConnectEnum(ReleaseStatus releaseStatus, EnumElement enumm, String packageName,
              Optional<ConnectType> parentType) {
    super(releaseStatus, enumm, packageName, parentType);
    this.values = ImmutableList.copyOf(enumm.constants()
        .stream()
        .map(v -> new ConnectField(
            getApiReleaseStatus(releaseStatus, v),
            v.name(), "", v.documentation()))
        .collect(toList()));
  }

  private ReleaseStatus getApiReleaseStatus(ReleaseStatus releaseStatus, EnumConstantElement v) {
    return ProtoOptions.getExplicitReleaseStatus(v.options(), "common.enum_value_status")
        .orElse(releaseStatus);
  }

  List<ConnectField> getValues() {
    return values;
  }

  JsonObject toJson(ReleaseStatus releaseStatus) {
    JsonArray enumValues = new JsonArray();
    this.values.stream()
        .filter(v -> releaseStatus.shouldInclude(v.getReleaseStatus()))
        .map(ConnectField::getName)
        .forEach(enumValues::add);

    JsonObject json = new JsonObject();
    json.addProperty("type", "string");
    json.add("enum", enumValues);
    json.addProperty("description", this.getDescription());
    json.addProperty("x-release-status", this.getReleaseStatus().name());

    return json;
  }

  private String getDescription() {
    return docAnnotations.getOrDefault("desc", "");
  }
}
