package com.squareup.apiparser;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Configuration {
  @Parameter(names = "-version", description = "Your API version")
  private String version = "2.0";

  @Parameter(names = "-title", description = "Title of your API")
  private String title = "Square Connect API";

  @Parameter(names = "-host", description = "Host of your API (ex: connect.squareup.com)")
  private String host = "connect.squareup.com";

  @Parameter(description = "Locations of protobufs")
  private List<String> protobufLocations = new ArrayList<>();

  @Parameter(names = {"--output", "-o"}, description = "Output path, defaults to current directory", converter = PathConverter.class)
  private Path outputPath;

  @Parameter(names = "-mergev1", description = "Location of v1 api.json to merge in")
  private String v1APISchemaFile = "";

  // This option is only to support development of unreleased features.
  // No protos for released APIs should contain oneofs.
  @Parameter(names = "--ignoreoneofs", description = "If set, ignore oneofs instead of failing to create a specification")
  private boolean ignoreOneofs = false;

  List<String> getProtobufLocations() {
    return protobufLocations;
  }

  String getHost() {
    return host;
  }

  String getTitle() {
    return title;
  }

  String getVersion() {
    return version;
  }

  Optional<Path> getOutputPath() {
    return Optional.ofNullable(outputPath);
  }

  String getV1APISchemaFile() {
    return v1APISchemaFile;
  }

  boolean isIgnoreOneofs() {
    return ignoreOneofs;
  }
}
