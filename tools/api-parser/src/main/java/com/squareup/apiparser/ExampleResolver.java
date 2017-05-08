package com.squareup.apiparser;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import okio.BufferedSource;
import okio.Okio;

import static com.squareup.apiparser.Json.GSON;
import static java.lang.String.format;

class ExampleResolver {
  private final List<String> rootPaths;

  ExampleResolver(List<String> rootPaths) {
    this.rootPaths = ImmutableList.copyOf(rootPaths);
  }

  /**
   * Given a relative path to an example file, find the first existing file and returns its
   * contents.
   *
   * @throws IllegalArgumentException thrown if no example file with the given path is found under
   * any of the root paths
   * @throws JsonSyntaxException thrown if the example file cannot be parsed as a JSON object
   */
  JsonObject loadExample(String examplePath) {
    File exampleFile = rootPaths.stream()
        .map(rootPath -> Paths.get(rootPath, examplePath).toFile())
        .filter(File::exists)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(
            format("Cannot find example file at path %s", examplePath)));

    try (BufferedSource buffer = Okio.buffer(Okio.source(exampleFile))) {
      return GSON.fromJson(buffer.readUtf8(), JsonObject.class);
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }
}
