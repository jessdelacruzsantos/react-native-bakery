package com.squareup.apiparser;

import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.Map;
import java.util.Optional;

/**
 * Created by barlow on 2/2/16.
 */
public class ConnectType {
  protected final TypeElement rootType;
  protected final String packageName;
  protected final Optional<ConnectType> parentType;
  protected final Map<String, String> docAnnotations;
  private final String name;

  public static final Map<String, String> TYPE_MAP = ImmutableMap.<String, String>builder()
      .put("int32", "integer")
      .put("int64", "integer")
      .put("bool", "boolean")
      .put("string", "string")
      .build();

  protected ConnectType(TypeElement rootType, String packageName, ConnectType parentType) {
    this.rootType = rootType;
    this.packageName = packageName;
    this.parentType = Optional.ofNullable(parentType);
    this.docAnnotations = DocString.parse(rootType.documentation());
    this.name = this.generateName();
  }

  public TypeElement getRootType() {
    return rootType;
  }

  public String getPackageName() {
    return packageName;
  }

  public Optional<ConnectType> getParentType() {
    return parentType;
  }

  public String getName() {
    return this.name;
  }

  public String generateName() {
    return getParentType().map(ConnectType::generateName).orElse("") + getRootType().name();
  }
}
