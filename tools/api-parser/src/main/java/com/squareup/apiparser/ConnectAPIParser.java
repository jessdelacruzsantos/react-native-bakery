package com.squareup.apiparser;

import com.beust.jcommander.JCommander;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.Map;

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

  public JsonAPI parseAPI(ProtoIndex index, Configuration configuration, boolean includeInternal) {
    // Transform all the symbols to JSON and write out to file
    JsonObject root = GSON.toJsonTree(createSwaggerBase(configuration)).getAsJsonObject();
    final ImmutableMap.Builder<String, String> enumMapBuilder = ImmutableMap.builder();

    JsonObject jsonEndpoints = new JsonObject();

    for (ConnectEndpoint endpoint : index.getEndpoints()) {
      if (!endpoint.isInternal() || includeInternal){
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
      if (!enumm.isInternal() || includeInternal) {
        jsonTypes.add(enumm.getName(), enumm.toJson());
        enumm.getValues().forEach(v ->
            enumMapBuilder.put(join.join(enumm.getName(), v.getName()), v.getDescription()));
      }
    }

    for (ConnectDatatype datatype : index.getDatatypes().values()) {
      if (!datatype.isInternal() || includeInternal) {
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
      ProtoIndex index = new ProtoIndexer().indexProtos(ImmutableList.copyOf(configuration.getProtobufLocations()));
      JsonAPI publicApi = new ConnectAPIParser().parseAPI(index, configuration, false);
      JsonAPI internalApi = new ConnectAPIParser().parseAPI(index, configuration, true);
      final String internalAPIOutputPath = System.getProperty("user.dir") + "/api_internal.json";
      final String publicAPIOutputPath = System.getProperty("user.dir") + "/api.json";
      final String enumOutputPath = System.getProperty("user.dir") + "/enum_mapping.json";
      writeJson(GSON.toJson(internalApi.swagger), internalAPIOutputPath);
      writeJson(GSON.toJson(publicApi.swagger), publicAPIOutputPath);
      writeJson(GSON.toJson(publicApi.enumMap), enumOutputPath);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to index protos!");
      System.exit(2);
    }
  }
}
