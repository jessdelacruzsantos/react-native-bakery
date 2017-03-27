package com.squareup.apiparser;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class Protos {
  private static final Set<String> LAST_GROUPING_NAMESPACE_PART =
      ImmutableSet.of("actions", "common", "resources", "resources_internal", "service");

  /**
   * Removes all internal namespace parts.
   *
   * Examples:
   * - "squareup.connect.v2.actions.Foo.Bar" returns "FooBar"
   * - "squareup.connect.v2.hello.actions.Foo.Bar" returns "FooBar"
   *
   * @param name A fully-qualified proto name.
   * @return Short alphanumeric name with internal namespace parts removed.
   */
  public static String cleanName(String name) {
    checkNotNull(name);

    List<String> parts = Splitter.on('.').splitToList(name);

    int firstGroupIndex =
        Iterables.indexOf(parts, s -> LAST_GROUPING_NAMESPACE_PART.contains((s)));
    if (firstGroupIndex >= 0) {
      parts = Lists.partition(parts, firstGroupIndex + 1).get(1);
    }

    return Joiner.on("").join(parts);
  }

  private Protos() {
  }
}
