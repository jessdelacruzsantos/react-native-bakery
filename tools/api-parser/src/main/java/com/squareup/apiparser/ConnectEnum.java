package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class ConnectEnum extends ConnectType {
  private List<ConnectField> values;


  public ConnectEnum(EnumElement enumm, String packageName, ConnectType parentType) {
    super(enumm, packageName, parentType);
    this.values = new ArrayList<ConnectField>();
    for (EnumConstantElement field : enumm.constants()) {
      values.add(new ConnectField(field.name(), "", field.tag(), field.documentation()));
    }
    this.parseDocumentationString(enumm.documentation());
  }

  public List<ConnectField> getValues() {
    return values;
  }

  public JSONObject toJson() {
    JSONObject root = new JSONObject();
    root.put("type", "string");

    JSONArray enumValues = new JSONArray();
    for (ConnectField value : this.values) {
      enumValues.put(value.getName());
    }

    root.put("enum", enumValues);

    if (this.docAnnotations.containsKey("desc")) {
      root.put("description", this.docAnnotations.get("desc"));
    } else {
      root.put("description", "");
    }

    return root;
  }

  public String getDescription() {
    if (this.docAnnotations.containsKey("desc")) {
      return this.docAnnotations.get("desc");
    } else {
      return "";
    }
  }
}
