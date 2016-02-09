package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumElement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectEnum extends ConnectType {
  private final List<ConnectField> values;

  public ConnectEnum(EnumElement enumm, String packageName, Optional<ConnectType> parentType) {
    super(enumm, packageName, parentType);
    this.values = enumm.constants().stream()
        .map(f -> new ConnectField(f.name(), "", f.tag(), f.documentation())).collect(Collectors.toList());
    this.parseDocumentationString(enumm.documentation());
  }

  public List<ConnectField> getValues() {
    return values;
  }

  public JSONObject toJson() {
    final Optional<String> desc = Optional.ofNullable(this.docAnnotations.get("desc"));
    JSONArray enumValues = new JSONArray();
    this.values.stream().forEach(e -> enumValues.put(e.getName()));
    return new JSONObject().put("type", "string")
        .put("enum", enumValues)
        .put("description", desc.orElse(""));
  }
}
