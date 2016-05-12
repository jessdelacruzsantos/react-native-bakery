package com.squareup.apiparser;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

class Configuration {
  @Parameter(names = "-version", description = "Your API version")
  private String version = "2.0";

  @Parameter(names = "-title", description = "Title of your API")
  private String title = "Square Connect API";

  @Parameter(names = "-host", description = "Host of your api (ex: connect.squareup.com)")
  private String host = "connect.squareup.com";

  @Parameter(description = "Locations of protobufs")
  private List<String> protobufLocations = new ArrayList<>();

  public List<String> getProtobufLocations() {
    return protobufLocations;
  }

  public String getHost() {
    return host;
  }

  public String getTitle() {
    return title;
  }

  public String getVersion() {
    return version;
  }
}
