package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.EnumElement;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ConnectEnum extends ConnectType {
  private final List<ConnectField> values;

  public ConnectEnum(EnumElement enumm, String packageName, Optional<ConnectType> parentType)
      throws AnnotationException {
    super(enumm, packageName, parentType);
    this.values = enumm.constants()
        .stream()
        .map(f -> new ConnectField(f.name(), "", f.tag(), f.documentation()))
        .collect(collectingAndThen(toList(), ImmutableList::copyOf));
  }

  public List<ConnectField> getValues() {
    return values;
  }

  public JsonObject toJson() {
    JsonArray enumValues = new JsonArray();
    this.values.stream().map(ConnectField::getName).forEach(enumValues::add);

    JsonObject json = new JsonObject();
    json.addProperty("type", "string");
    json.add("enum", enumValues);
    json.addProperty("description", docAnnotations.getOrDefault("desc", ""));
    return json;
  }
}
