package com.squareup.apiparser;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.text.SimpleDateFormat;

class Configuration {
  @Parameter(names = "-version", description = "The Connect API Major version - in the URI and used for vast, breaking changes")
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

  @Parameter(names = "-sqversion", description = "Square Connect V2 API Version in YYYY-MM-DD. It is in a HTTP header and used for changes within a major version.")
  private String sqVersion = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

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

  String getSqVersion() {
    return sqVersion;
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
