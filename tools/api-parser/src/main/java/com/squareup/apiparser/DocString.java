package com.squareup.apiparser;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class DocString {
  private static final Splitter BODY_SPLITTER = Splitter.on("--").omitEmptyStrings().trimResults();
  private static final Splitter AT_SPLITTER = Splitter.on("@").omitEmptyStrings().trimResults();
  private static final Splitter SPACE_SPLITTER =
      Splitter.on(CharMatcher.WHITESPACE).limit(2).trimResults();

  private final ImmutableMap<String, String> annotations;

  public DocString(String docString) {
    checkNotNull(docString);

    String parseableDocString = Iterables.getLast(
        Iterables.limit(BODY_SPLITTER.split(docString), 2), "");
    ImmutableMap.Builder<String, String> annotationsBuilder =
        ImmutableMap.<String, String>builder();

    int firstIndex = parseableDocString.indexOf("@");
    if (firstIndex >= 0) {
      AT_SPLITTER.splitToList(parseableDocString.substring(firstIndex)).stream().forEach(s -> {
        List<String> keyValue = SPACE_SPLITTER.splitToList(s);
        checkState(keyValue.size() > 0 && keyValue.size() <= 2, "%s", keyValue);
        String value = keyValue.size() == 2
            ? keyValue.get(1)
            : "";
        annotationsBuilder.put(keyValue.get(0), value);
      });
    }

    annotations = annotationsBuilder.build();
  }

  public ImmutableMap<String, String> getAnnotations() {
    return annotations;
  }
}
