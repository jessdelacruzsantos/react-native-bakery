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
  protected final TypeElement rootType;
  protected final String packageName;
  protected final Optional<ConnectType> parentType;
  protected final Map<String, String> docAnnotations;
  private final String name;
  private final String releaseStatusOptionName;

  public static final Map<String, String> TYPE_MAP = ImmutableMap.<String, String>builder()
      .put("int32", "integer")
      .put("int64", "integer")
      .put("bool", "boolean")
      .put("string", "string")
      .build();

  protected ConnectType(TypeElement rootType, String packageName,
      Optional<ConnectType> parentType) {
    this.rootType = checkNotNull(rootType);
    this.packageName = checkNotNull(packageName);
    this.parentType = checkNotNull(parentType);
    this.docAnnotations = new DocString(rootType.documentation()).getAnnotations();
    this.name = this.generateName();
    this.releaseStatusOptionName = getReleaseStatusOptionName(rootType);
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

  public boolean isInternal() {
    return ProtoOptions.isReleaseStatusInternal(getRootType().options(), releaseStatusOptionName);
  }

  public String generateName() {
    return getParentType().map(ConnectType::generateName).orElse("") + getRootType().name();
  }
}
