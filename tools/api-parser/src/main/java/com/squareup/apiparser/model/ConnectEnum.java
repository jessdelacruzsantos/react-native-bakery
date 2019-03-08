package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        .map(constantElement -> new ConnectEnumConstant(constantElement, this.group, this.name))
        .collect(toList()));
    this.identifier = "(Enum)"+this.name;
  }

  void validate() {
    Validator.validateDescription(this.identifier, this.description);
  }

  List<ConnectEnumConstant> getValues() {
    return values;
  }

  JsonObject toJson(Group group) {
    List<ConnectEnumConstant> enumValues = this.values.stream()
        .filter(enumValue -> group.shouldInclude(enumValue.getGroup()))
        .collect(Collectors.toList());


    JsonArray enumValueArray = new JsonArray();
    // Constructing APIMatic compatible enum extension/description
    JsonArray enumElementsArray = new JsonArray();

    enumValues.stream()
        .forEach(enumValue ->{
          enumValueArray.add(enumValue.getName());
          JsonObject enumElement = new JsonObject();
          enumElement.addProperty("name", enumValue.getName());
          enumElement.addProperty("description", enumValue.getDescription());
          enumElementsArray.add(enumElement);
        });

    JsonObject json = new JsonObject();
    json.addProperty("type", "string");
    json.add("enum", enumValueArray);
    json.add("x-enum-elements", enumElementsArray);
    json.addProperty("description", this.getDescription());
    json.addProperty("x-release-status", this.getGroup().status.name());

    if(visibility != Visibility.NORMAL){
      json.addProperty("x-visibility", visibility.name());
    }

    return json;
  }
}
