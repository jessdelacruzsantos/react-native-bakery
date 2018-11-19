package com.squareup.apiparser;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import java.util.List;
import java.util.Set;

class Protos {

  private static final Set<String> PACKAGE_SEPARATORS = ImmutableSet.of(
      "actions", "catalog_v1", "common", "model", "resources", "resources_internal", "service", "v1");

  static String cleanName(String name) {
    Preconditions.checkNotNull(name);
    List<String> strings = Splitter.on('.').splitToList(name);

    int splitIndex = Iterables.indexOf(strings, PACKAGE_SEPARATORS::contains);
    if (splitIndex >= 0) {
      strings = strings.subList(splitIndex + 1, strings.size());
    }

    return Joiner.on("").join(strings);
  }

  private Protos() {
  }
}
