package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectDatatype {
  private String id = "";
  private String name = "";
  private String description = "";
  private List<ConnectField> fields;

  public ConnectDatatype(MessageElement message, String id) {
    this.name = message.name();
    this.fields = new ArrayList<ConnectField>();
    this.id = id;
    for(FieldElement field : message.fields()) {
      this.fields.add(new ConnectField(field));
    }
    for(OneOfElement oneof : message.oneOfs()) {
      for (FieldElement field : oneof.fields()) {
        this.fields.add(new ConnectField(field));
      }
    }

    String[] comments = {""};
    if (message.documentation().contains("--")) {
      String publicDocString = message.documentation().split("--")[1];
      comments = publicDocString.split("\n@");
      for (String entry : comments) {
        String keyword = entry.split(" ")[0];
        switch (keyword) {
          case "desc":
            this.setDescription(entry.replaceFirst("desc", "").trim());
            break;
        }
      }
    }
  }

  public JSONObject toJson() {
    JSONObject root = new JSONObject();
    root.put("name", this.name);
    root.put("description", this.description);
    root.put("id", this.id);
    root.put("fields", this.arrayifyFields(this.fields));
    return root;
  }

  private JSONArray arrayifyFields(List<ConnectField> fields) {
    JSONArray array = new JSONArray();
    for (ConnectField field : fields) {
      array.put(field.toJson());
    }
    return array;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
