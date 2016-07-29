package com.squareup.apiparser;

import com.beust.jcommander.JCommander;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.Map;
import javax.annotation.Nullable;

import static com.squareup.apiparser.Json.GSON;

public class ConnectAPIParser {

  static class JsonAPI {
    final JsonObject swagger;
    final JsonObject enumMap;

    JsonAPI(JsonObject swagger, JsonObject enumMap) {
      this.swagger = swagger;
      this.enumMap = enumMap;
    }
  }

  private static Map<String, Object> createSwaggerBase(Configuration configuration) {
    return ImmutableMap.<String, Object>builder()
        .put("swagger", "2.0")
        .put("info", ImmutableMap.of(
            "version", configuration.getVersion(),
            "title", configuration.getTitle()))
        .put("host", configuration.getHost())
        .put("schemes", ImmutableList.of("https"))
        .put("consumes", ImmutableList.of("application/json"))
        .put("produces", ImmutableList.of("application/json"))
        .build();
  }

  public JsonAPI parseAPI(ProtoIndex index, Configuration configuration) {
    // Transform all the symbols to JSON and write out to file
    JsonObject root = GSON.toJsonTree(createSwaggerBase(configuration)).getAsJsonObject();
    final ImmutableMap.Builder<String, String> enumMapBuilder = ImmutableMap.builder();

    JsonObject jsonEndpoints = new JsonObject();

    for (ConnectEndpoint endpoint : index.getEndpoints()) {
      if (index.getApiReleaseType().shouldInclude(endpoint.getReleaseStatus())) {
        if (!jsonEndpoints.has(endpoint.getPath())) {
          jsonEndpoints.add(endpoint.getPath(), new JsonObject());
        }
        jsonEndpoints.getAsJsonObject(endpoint.getPath())
            .add(endpoint.getHttpmethod().toLowerCase(), endpoint.toJson());
      }
    }
    root.add("paths", jsonEndpoints);

    JsonObject jsonTypes = new JsonObject();
    final Joiner join = Joiner.on(".");
    for (ConnectEnum enumm : index.getEnums().values()) {
      if (index.getApiReleaseType().shouldInclude(enumm.getReleaseStatus())) {
        jsonTypes.add(enumm.getName(), enumm.toJson());
        enumm.getValues().forEach(v ->
            enumMapBuilder.put(join.join(enumm.getName(), v.getName()), v.getDescription()));
      }
    }

    for (ConnectDatatype datatype : index.getDatatypes().values()) {
      if (index.getApiReleaseType().shouldInclude(datatype.getReleaseStatus())) {
        jsonTypes.add(datatype.getName(), datatype.toJson());
      }
    }
    root.add("definitions", jsonTypes);

    final JsonObject map = GSON.toJsonTree(enumMapBuilder.build()).getAsJsonObject();
    return new JsonAPI(root, map);
  }

  private static void writeJson(String json, String path) {
    try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
      writer.println(json);
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
    System.out.println("Successfully wrote to " + path);
  }

  public static void main(String argv[]) {
    try {
      Configuration configuration = new Configuration();
      new JCommander(configuration, argv);
      Preconditions.checkArgument(!configuration.getProtobufLocations().isEmpty(),
          "At least one protobuf location is required");

      ImmutableList<String> protoPaths = ImmutableList.copyOf(configuration.getProtobufLocations());

      String allAPIOutputPath = System.getProperty("user.dir") + "/api_internal.json";
      generateJsonAPI(configuration, protoPaths, ApiReleaseType.ALL, allAPIOutputPath, null);

      String publicAPIOutputPath = System.getProperty("user.dir") + "/api.json";
      String enumOutputPath = System.getProperty("user.dir") + "/enum_mapping.json";
      generateJsonAPI(
          configuration, protoPaths, ApiReleaseType.PUBLIC, publicAPIOutputPath, enumOutputPath);

      String betaAPIOutputPath = System.getProperty("user.dir") + "/api_beta.json";
      generateJsonAPI(configuration, protoPaths, ApiReleaseType.BETA, betaAPIOutputPath, null);

      String upcomingAPIOutputPath = System.getProperty("user.dir") + "/api_upcoming.json";
      generateJsonAPI(
          configuration, protoPaths, ApiReleaseType.UPCOMING, upcomingAPIOutputPath, null);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to generate JSON APIs!");
      System.exit(2);
    }
  }

  private static void generateJsonAPI(Configuration configuration, ImmutableList<String> protoPaths,
      ApiReleaseType apiReleaseType, String apiOutputPath, @Nullable String enumOutputPath)
      throws Exception {
    ProtoIndex index = new ProtoIndexer().indexProtos(apiReleaseType, protoPaths);
    JsonAPI api = new ConnectAPIParser().parseAPI(index, configuration);
    writeJson(GSON.toJson(api.swagger), apiOutputPath);
    if (enumOutputPath != null) {
      writeJson(GSON.toJson(api.enumMap), enumOutputPath);
    }
  }
}
