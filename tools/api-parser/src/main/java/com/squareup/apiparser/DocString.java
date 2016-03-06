package com.squareup.apiparser;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class DocString {
  private final String docString;
  private boolean hasParsed;
  private ImmutableMap<String, String> annotations;

  public DocString(String docString) {
    this.docString = checkNotNull(docString);
    ;
    this.annotations = ImmutableMap.of();
  }

  public ImmutableMap<String, String> getAnnotations() {
    if (!hasParsed) { parse(); }

    return annotations;
  }

  public DocString parse() {
    if (!hasAnnotations() || hasParsed) { return this; }

    String parseableDocString = (isMultiline()) ? getMultiline() : getSingleline();
    final List<String> splits = Splitter.on("@").trimResults().splitToList(parseableDocString);
    ImmutableMap.Builder<String, String> b = ImmutableMap.<String, String>builder();
    splits.forEach(s -> {
      String k = s.split(" ")[0];
      b.put(k, s.replaceFirst(k, "").trim());
    });
    this.annotations = b.build();
    this.hasParsed = true;
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
