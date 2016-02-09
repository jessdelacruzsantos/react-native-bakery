package com.squareup.apiparser;

import java.io.PrintWriter;
import org.json.JSONObject;

public class ConnectAPIParser implements APIParser {
  public ConnectAPIParser() {};

  public JSONObject parseAPI(ProtoIndex index) {
    // Transform all the symbols to JSON and write out to file
    JSONObject root = new JSONObject(
        "{"
        + "\"swagger\": \"2.0\","
        + "\"info\": {"
        + "  \"version\": \"2.0\","
        + "  \"title\": \"Square Connect API\""
        + "},"
        + "\"host\": \"connect.squareup.com\","
        + "\"schemes\": [\"https\"],"
        + "\"consumes\": [\"application/json\"],"
        + "\"produces\": [\"application/json\"]"
      + "}");

    JSONObject jsonTypes = new JSONObject();
    for (ConnectEnum enumm : index.getEnums().values()) {
      jsonTypes.put(enumm.getName(), enumm.toJson());
    }

    for (ConnectDatatype datatype : index.getDatatypes().values()) {
      if (datatype.hasFields()) {
        jsonTypes.put(datatype.getName(), datatype.toJson());
      }
    }
    root.put("definitions", jsonTypes);

    JSONObject jsonEndpoints = new JSONObject();
    for (ConnectEndpoint endpoint : index.getEndpoints()) {
      if (!jsonEndpoints.has(endpoint.getPath())) {
        jsonEndpoints.put(endpoint.getPath(), new JSONObject());
      }
      jsonEndpoints.getJSONObject(endpoint.getPath()).put(endpoint.getHttpmethod().toLowerCase(), endpoint.toJson());
    }
    root.put("paths", jsonEndpoints);
    return root;
  }

  public static void main(String argv[]) {
    ProtoIndex index = new ProtoIndexer().indexProtos(argv);
    JSONObject root = new ConnectAPIParser().parseAPI(index);

    try {
      String outputPath = System.getProperty("user.home") + "/Development/connect-sdks/tools/sdk-gen/api.json";
      PrintWriter writer = new PrintWriter(outputPath, "UTF-8");
      writer.println(root.toString(2));
      writer.close();
      System.out.println("Wrote api.json to " + outputPath);
    } catch (Exception e) {
      System.out.println("Failed to write api.json! Exception occurred during write.");
      System.exit(1);
    }
  }
}
