package com.squareup.apiparser;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;

import java.io.PrintWriter;
import java.util.Map;

import static com.squareup.apiparser.Json.GSON;

public class ConnectAPIParser implements APIParser {
  static class JsonAPI {
    final JsonObject swagger;
    final JsonObject enumMap;

    JsonAPI(JsonObject swagger, JsonObject enumMap) {
      this.swagger = swagger;
      this.enumMap = enumMap;
    }
  }

  private static final Map<String, Object> SWAGGER_BASE = ImmutableMap.<String, Object>builder()
      .put("swagger", "2.0")
      .put("info", ImmutableMap.of(
          "version", "2.0",
          "title", "Square Connect API"))
      .put("host", "connect.squareup.com")
      .put("schemes", ImmutableList.of("https"))
      .put("consumes", ImmutableList.of("application/json"))
      .put("produces", ImmutableList.of("application/json"))
      .build();

  public JsonAPI parseAPI(ProtoIndex index) {
    // Transform all the symbols to JSON and write out to file
    JsonObject root = GSON.toJsonTree(SWAGGER_BASE).getAsJsonObject();
    final ImmutableMap.Builder<String, String> enumMapBuilder = ImmutableMap.builder();

    JsonObject jsonEndpoints = new JsonObject();
    for (ConnectEndpoint endpoint : index.getEndpoints()) {
      if (!jsonEndpoints.has(endpoint.getPath())) {
        jsonEndpoints.add(endpoint.getPath(), new JsonObject());
      }
      jsonEndpoints.getAsJsonObject(endpoint.getPath())
          .add(endpoint.getHttpmethod().toLowerCase(), endpoint.toJson());
    }
    root.add("paths", jsonEndpoints);

    JsonObject jsonTypes = new JsonObject();
    final Joiner join = Joiner.on(".");
    for (ConnectEnum enumm : index.getEnums().values()) {
      jsonTypes.add(enumm.getName(), enumm.toJson());
      enumm.getValues().stream().forEach(v -> enumMapBuilder.put(join.join(enumm.getName(), v.getName()), v.getDescription()));
    }

    for (ConnectDatatype datatype : index.getDatatypes().values()) {
      if (datatype.hasBodyParameters()) {
        jsonTypes.add(datatype.getName(), datatype.toJson());
      }
    }
    root.add("definitions", jsonTypes);

    final JsonObject map = GSON.toJsonTree(enumMapBuilder.build()).getAsJsonObject();
    return new JsonAPI(root, map);
  }

  public static void main(String argv[]) {
    try {
      ProtoIndex index = new ProtoIndexer().indexProtos(ImmutableList.copyOf(argv));
      JsonAPI api = new ConnectAPIParser().parseAPI(index);
      final String APIOutputPath = System.getProperty("user.dir") + "/api.json";
      final String enumOutputPath = System.getProperty("user.dir") + "/enum_mapping.json";

      try (PrintWriter writer = new PrintWriter(APIOutputPath, "UTF-8")) {
        writer.println(GSON.toJson(api.swagger));
      }
      System.out.println("Wrote api.json to " + APIOutputPath);
      try (PrintWriter writer = new PrintWriter(enumOutputPath, "UTF-8")) {
        writer.println(GSON.toJson(api.enumMap));
      }
      System.out.println("Wrote enum mapping to " + enumOutputPath);
    } catch (Exception e) {
      System.out.println("Failed to write api.json!");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
