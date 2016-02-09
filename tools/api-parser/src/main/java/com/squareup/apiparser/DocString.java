package com.squareup.apiparser;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class DocString {
  private final @NotNull String docString;
  private List<String> components;

  private DocString(String docString) {
    Preconditions.checkNotNull(docString);
    this.docString = docString;
    this.components = Collections.emptyList();
  }

  public static ImmutableList<String> parse(String docString) {
    return new DocString(docString).parse().getComponents();
  }

  public ImmutableList<String> getComponents() {
    return ImmutableList.copyOf(components);
  }

  public DocString parse() {
    if (!hasAnnotations())
      return this;

    String parseableDocString = (isMultiline()) ? getMultiline() : getSingleline();
    components = Splitter.on("@").trimResults().splitToList(parseableDocString);
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
