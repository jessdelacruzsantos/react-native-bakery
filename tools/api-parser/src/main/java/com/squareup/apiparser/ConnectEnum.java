package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.EnumElement;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ConnectEnum extends ConnectType {
  private final List<ConnectField> values;

  public ConnectEnum(EnumElement enumm, String packageName, ConnectType parentType) throws AnnotationException {
    super(enumm, packageName, parentType);
    this.values = enumm.constants().stream()
        .map(f -> new ConnectField(f.name(), "", f.tag(), f.documentation())).collect(collectingAndThen(toList(), ImmutableList::copyOf));
  }

  public List<ConnectField> getValues() {
    return values;
  }

  public JSONObject toJson() {
    JSONArray enumValues = new JSONArray();
    this.values.stream().forEach(e -> enumValues.put(e.getName()));
    return new JSONObject().put("type", "string")
        .put("enum", enumValues)
        .put("description", docAnnotations.getOrDefault("desc", ""));
  }
}
