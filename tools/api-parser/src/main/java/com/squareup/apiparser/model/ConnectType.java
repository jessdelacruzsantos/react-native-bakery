package com.squareup.apiparser;

import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by barlow on 2/2/16.
 */
public class ConnectType {

  static final Map<String, String> TYPE_MAP = ImmutableMap.<String, String>builder()
      .put("int32", "integer")
      .put("int64", "integer")
      .put("uint32", "integer")
      .put("uint64", "integer")
      .put("bool", "boolean")
      .put("string", "string")
      .build();

  static final Map<String, String> FORMAT_MAP = ImmutableMap.<String, String>builder()
      .put("int64", "int64")
      .build();

  final TypeElement rootType;

  private final String packageName;
  private final Optional<ConnectType> parentType;
  protected final String name;
  protected Group group = new Group();
  protected String description;
  protected String identifier;

  ConnectType(
      TypeElement rootType,
      String packageName,
      Optional<ConnectType> parentType) {
    this.rootType = checkNotNull(rootType);
    this.packageName = checkNotNull(packageName);
    this.parentType = checkNotNull(parentType);
    this.description = new DocString(rootType.documentation()).getDescription();
    this.name = this.generateName();
  }

  TypeElement getRootType() {
    return rootType;
  }

  String getPackageName() {
    return packageName;
  }

  Optional<ConnectType> getParentType() {
    return parentType;
  }

  public String getName() {
    return this.name;
  }

  String generateName() {
    return getParentType().map(ConnectType::generateName).orElse("") + getRootType().name();
  }

  String getType() {
    String prefixType = this.parentType.map(ConnectType::getType).orElse(packageName);
    return String.format("%s.%s", prefixType, this.rootType.name());
  }

  public Group getGroup() {
    return this.group;
  }

  public String getDescription() {
    return this.description;
  }
}
