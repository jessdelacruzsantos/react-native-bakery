package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.FieldElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

public class ConnectField {
  private final String name;
  private final String type;
  private final Boolean required;
  @Nullable private final FieldElement rootField;
  private final Boolean isArray;
  private List<String> enumValues;
  private Boolean isPathParam = false;
  private int value = 0;
  private final Map<String, String> docAnnotations = new HashMap<>();
  private final Map<String, Object> validations;

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  public String getDescription() {
    if (this.docAnnotations.containsKey("desc")) {
      return docAnnotations.get("desc");
    } else {
      return "";
    }
  }

  public Boolean getRequired() {
    return required;
  }

  public boolean isPathParam() {
    return this.docAnnotations.containsKey("pathparam");
  }

  public Boolean isArray() {
    return (this.rootField.label() == Field.Label.REPEATED);
  }

  public List<String> getEnumValues() {
    return enumValues == null ? Collections.emptyList() : enumValues;
  }

  public Map<String, Object> getValidations() {
    return validations;
  }

  public ConnectField(FieldElement field) {
    this.name = field.name();
    this.rootField = field;
    this.type = this.processType(rootField.type());
    this.required = field.label() == Field.Label.REQUIRED;
    this.isArray = field.label() == Field.Label.REPEATED;
    this.parseDocumentationString(field.documentation());
    this.validations = ProtoOptions.validations(field.options());
  }

  // This constructor is called ONLY for fields that represent a request parameter for an endpoint
  // that is ALSO an enum. It exists because in swagger, enum request parameters MUST be defined
  // in-line, and cannot refer to a schema.
  public ConnectField(FieldElement field, ConnectEnum enumm) {
    this(field);
    this.enumValues = enumm.getValues().stream().map(ConnectField::getName).collect(Collectors.toList());
  }

  // This constructor is called ONLY for fields that represent a value of an enum, such as USD.
  public ConnectField(String name, String type, int value, String documentation) {
    this.name = name;
    this.type = this.processType(type);
    this.value = value;
    this.required = false;
    this.rootField = null;
    this.isArray = false;
    this.enumValues = ImmutableList.of();
    this.parseDocumentationString(documentation);
    this.validations = ImmutableMap.of();
  }

  private void parseDocumentationString(@NotNull String docString) {
    Preconditions.checkNotNull(docString);
    List<String> components = DocString.parse(docString);
    for (String entry : components) {
      String keyword = entry.split(" ")[0];

      if (this.docAnnotations.containsKey(keyword)) {
        System.err.println("ERROR! Multiple doc annotations of same type found for field " + this.getName());
      }

      docAnnotations.put(keyword, entry.replaceFirst(keyword, "").trim());
    }
  }

  private String processType(String type) {
    type = type.replaceFirst("resources.", "");
    type = type.replaceFirst("actions.", "");
    type = type.replaceAll("\\.", "");
    return type;
  }

  // NB(alec): why isn't this called?
  public JSONObject toJson() {
    Optional<String> desc = Optional.ofNullable(this.docAnnotations.get("desc"));
    JSONObject json = new JSONObject();
    this.validations.entrySet().forEach(v -> json.put(v.getKey(), v.getValue()));
    return json.put("name", this.name)
        .put("type", this.type)
        .put("required", this.required)
        .put("isarray", this.isArray)
        .put("value", this.value)
        .put("description", desc.orElse(""))
        .put("ispathparam", this.isPathParam);
  }
}
