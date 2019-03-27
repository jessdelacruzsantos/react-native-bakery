package com.squareup.apiparser;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;

class Configuration {
  @Parameter(description = "Locations of protobufs")
  public List<String> protobufLocations = new ArrayList<>();

  @Parameter(names = {"--output", "-o"}, description = "Output path, defaults to current directory", converter = PathConverter.class)
  public Path outputPath;

  // This option is only to support development of unreleased features.
  // No protos for released APIs should contain oneofs.
  @Parameter(names = "--ignoreoneofs", description = "If set, ignore oneofs instead of failing to create a specification")
  public boolean ignoreOneofs = false;

  @Parameter(names = "-sqversion", description = "Square Connect V2 API Version in YYYY-MM-DD. It is in a HTTP header and used for changes within a major version.")
  public String sqVersion = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

  @Parameter(names = {"--validate", "-v"}, description = "If set, validation error will break spec generation. It is always off for INTERNAL spec.")
  public boolean validatorEnabled = false;

  List<String> getProtobufLocations() {
    return protobufLocations;
  }

  String getSqVersion() {
    return sqVersion;
  }

  Optional<Path> getOutputPath() {
    return Optional.ofNullable(outputPath);
  }

  boolean isIgnoreOneofs() {
    return ignoreOneofs;
  }

  public static final ImmutableList<String> NAMESPACES = ImmutableList.of("marketplaces");
  public static final String V1_TYPE_PREFIX = "V1";
  public static final String OAUTH_PERMISSION_TYPE = "OAuthPermission";
  public static final ImmutableList<String> V1_TYPE_EXCEPTION_LIST = ImmutableList.of("V1UploadItemImageRequest");

  static boolean isV1Compatible(String name){
      return name.startsWith(V1_TYPE_PREFIX) && !V1_TYPE_EXCEPTION_LIST.contains(name);
  }

  // Hardcoded swaggerBase for all API spec
  Map<String, Object> swaggerBase() {
    ImmutableMap.Builder<String, String> contact = ImmutableMap.<String, String>builder()
        .put("name", "Square Developer Platform")
        .put("email", "developers@squareup.com")
        .put("url", "https://squareup.com/developers");

    ImmutableMap.Builder<String, String> license = ImmutableMap.<String, String>builder()
        .put("name", "Apache 2.0")
        .put("url", "http://www.apache.org/licenses/LICENSE-2.0.html");

    ImmutableMap.Builder<String, Object> info = ImmutableMap.<String, Object>builder()
        .put("version", "2.0")
        .put("title", "Square Connect API")
        .put("description", "Client library for accessing the Square Connect APIs")
        .put("termsOfService", "https://connect.squareup.com/tos")
        .put("contact", contact.build())
        .put("license", license.build());

    ImmutableMap.Builder<String, String> docs = ImmutableMap.<String, String>builder()
        .put("description", "Read the official documentation here:")
        .put("url", "https://docs.connect.squareup.com/");

    ImmutableMap.Builder<String, Object> oauth = ImmutableMap.<String, Object>builder()
        .put("type", "oauth2")
        .put("authorizationUrl", "https://connect.squareup.com/oauth2/authorize")
        .put("flow", "accessCode")
        .put("tokenUrl", "https://connect.squareup.com/oauth2/token");

    ImmutableMap.Builder<String, Object> clientAuth = ImmutableMap.<String, Object>builder()
        .put("type", "apiKey")
        .put("in", "header")
        .put("name", "Authorization");

    ImmutableMap.Builder<String, Object> securityDefinitions = ImmutableMap.<String, Object>builder()
        .put("oauth2", oauth.build())
        .put("oauth2ClientSecret", clientAuth.build());

    return ImmutableMap.<String, Object>builder()
        .put("swagger", "2.0")
        .put("info", info.build())
        .put("externalDocs", docs.build())
        .put("host", "connect.squareup.com")
        .put("schemes", ImmutableList.of("https"))
        .put("consumes", ImmutableList.of("application/json"))
        .put("produces", ImmutableList.of("application/json"))
        .put("securityDefinitions", securityDefinitions.build())
        .build();
  }
}
