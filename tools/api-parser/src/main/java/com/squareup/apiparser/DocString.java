package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.List;

public class DocString {
  private final String docString;
  private ImmutableMap<String, String> annotations;

  private DocString(String docString) {
    Preconditions.checkNotNull(docString);
    this.docString = docString;
    this.annotations = ImmutableMap.copyOf(Collections.emptyMap());
  }

  public static ImmutableMap<String, String> parse(String docString) {
    return new DocString(docString).parse().getAnnotations();
  }

  public ImmutableMap<String, String> getAnnotations() {
    return annotations;
  }

  public DocString parse() {
    if (!hasAnnotations())
      return this;

    String parseableDocString = (isMultiline()) ? getMultiline() : getSingleline();
    final List<String> splits = Splitter.on("@").trimResults().splitToList(parseableDocString);
    ImmutableMap.Builder<String, String> b = ImmutableMap.<String, String>builder();
    splits.forEach(s -> {
      String k = s.split(" ")[0];
      b.put(k, s.replaceFirst(k, "").trim());
    });
    this.annotations = b.build();
    return this;
  }

  private boolean isMultiline() {
    // Public doc strings can be bounded by two hyphens to support multiline annotations.
    return docString.contains("--");
  }

  private boolean hasAnnotations() {
    return docString.contains("@");
  }

  private String getMultiline() {
    return docString.split("--")[1];
  }

  private String getSingleline() {
    return docString;
  }
}
