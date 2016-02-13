package com.squareup.apiparser;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.LinkedList;

public class Protos {
  public static String cleanName(String name) {
    Preconditions.checkNotNull(name);
    final Iterable<String> strings = new LinkedList<>(Splitter.on('.').splitToList(name));
    Iterables.removeAll(strings, ImmutableList.of("actions", "resources"));
    return Joiner.on("").join(strings);
  }
}
