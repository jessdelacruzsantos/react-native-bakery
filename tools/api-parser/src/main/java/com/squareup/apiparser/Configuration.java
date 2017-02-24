package com.squareup.apiparser;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

class Configuration {
  @Parameter(names = "-version", description = "Your API version")
  private String version = "2.0";

  @Parameter(names = "-title", description = "Title of your API")
  private String title = "Square Connect API";

  @Parameter(names = "-host", description = "Host of your API (ex: connect.squareup.com)")
  private String host = "connect.squareup.com";

  @Parameter(description = "Locations of protobufs")
  private List<String> protobufLocations = new ArrayList<>();

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
}
