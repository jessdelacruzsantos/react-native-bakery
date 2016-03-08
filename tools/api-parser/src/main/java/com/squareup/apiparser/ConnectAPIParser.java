package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.Map;

import static com.squareup.apiparser.Json.GSON;

public class ConnectAPIParser implements APIParser {
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

  public JsonObject parseAPI(ProtoIndex index) {
    // Transform all the symbols to JSON and write out to file
    JsonObject root = GSON.toJsonTree(SWAGGER_BASE).getAsJsonObject();

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
    for (ConnectEnum enumm : index.getEnums().values()) {
      jsonTypes.add(enumm.getName(), enumm.toJson());
    }

    for (ConnectDatatype datatype : index.getDatatypes().values()) {
      if (datatype.hasBodyParameters()) {
        jsonTypes.add(datatype.getName(), datatype.toJson());
      }
    }
    root.add("definitions", jsonTypes);

    return root;
  }

  public static void main(String argv[]) {
    try {
      ProtoIndex index = new ProtoIndexer().indexProtos(ImmutableList.copyOf(argv));
      JsonObject root = new ConnectAPIParser().parseAPI(index);
      String outputPath = System.getProperty("user.dir") + "/api.json";

      try (PrintWriter writer = new PrintWriter(outputPath, "UTF-8")) {
        writer.println(GSON.toJson(root));
      }
      System.out.println("Wrote api.json to " + outputPath);
    } catch (Exception e) {
      System.out.println("Failed to write api.json!");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
