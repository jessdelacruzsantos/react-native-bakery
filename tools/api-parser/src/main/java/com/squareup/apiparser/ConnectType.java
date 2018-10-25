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

  final Map<String, String> docAnnotations;
  final TypeElement rootType;

  private final String packageName;
  private final Optional<ConnectType> parentType;
  private final String name;
  private ReleaseStatus releaseStatus;
  private String namespace;

  ConnectType(
      ReleaseStatus releaseStatus,
      String namespace,
      TypeElement rootType,
      String packageName,
      Optional<ConnectType> parentType) {
    this.rootType = checkNotNull(rootType);
    this.packageName = checkNotNull(packageName);
    this.parentType = checkNotNull(parentType);
    this.docAnnotations = new DocString(rootType.documentation()).getAnnotations();
    this.name = this.generateName();
    this.releaseStatus = ProtoOptions.getExplicitReleaseStatus(
        getRootType().options(), getReleaseStatusOptionName(rootType))
        .orElse(releaseStatus);
    this.namespace = ProtoOptions.getStringValue(
        getRootType().options(), getNamespaceOptionName(rootType))
        .orElse(namespace);
  }

  private static String getReleaseStatusOptionName(TypeElement rootType) {
    if (rootType instanceof EnumElement) {
      return "common.enum_status";
    } else if (rootType instanceof MessageElement) {
      return "common.message_status";
    } else {
      throw new IllegalArgumentException(
          String.format("Encountered a malformed proto: rootType=%s", rootType));
    }
  }

  private static String getNamespaceOptionName(TypeElement rootType) {
    if (rootType instanceof EnumElement) {
      return "common.enum_namespace";
    } else if (rootType instanceof MessageElement) {
      return "common.message_namespace";
    } else {
      throw new IllegalArgumentException(
          String.format("Encountered a malformed proto: rootType=%s", rootType));
    }
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

  public ReleaseStatus getReleaseStatus() {
    return releaseStatus;
  }

  public String getNamespace() {
    return namespace;
  }
}
