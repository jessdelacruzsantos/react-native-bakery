package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import static com.google.common.base.Preconditions.checkNotNull;

public class ConnectEnumConstant {
  private Group group = new Group();
  private String name;
  private String description;

  ConnectEnumConstant(EnumConstantElement element, Group defaultGroup) {
    this.group.status = ProtoOptions.getReleaseStatus(element.options(), "common.enum_value_status", defaultGroup.status);
    this.group.namespace = ProtoOptions.getStringValue(element.options(), "common.enum_value_namespace").orElse(defaultGroup.namespace);
    this.description = new DocString(element.documentation()).getAnnotations().getOrDefault("desc", "");
    this.name = element.name();
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Group getGroup() {
    return group;
  }
}
