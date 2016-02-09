package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import com.squareup.wire.schema.internal.parser.ServiceElement;
import com.squareup.wire.schema.internal.parser.TypeElement;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class ConnectAPIParser implements APIParser {

  public ConnectAPIParser() {
  }

  public void parseAPI(ProtoIndex index) {

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
      if (endpoint.isNogenerate()) {
        continue;
      }
      if (!jsonEndpoints.has(endpoint.getPath())) {
        jsonEndpoints.put(endpoint.getPath(), new JSONObject());
      }
      jsonEndpoints.getJSONObject(endpoint.getPath()).put(endpoint.getHttpmethod().toLowerCase(), endpoint.toJson());
    }
    root.put("paths", jsonEndpoints);

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

  /*
    Each string in the returned array maps to a different annotation in a symbol's doc string.
    The '@' symbol of the annotation is omitted, so:
    // @desc blah blah blah
    becomes:
    desc blah blah blah
   */
  public static String[] parseDocString(String docString) {
    String[] components;
    if (docString.equals("")) {
      return new String[0];

      // Public doc strings can be bounded by two hyphens to support multiline annotations.
    } else if (docString.contains("--")) {
      String publicDocString = docString.split("--")[1];
      components = publicDocString.split("\\s+@");
      if (components[0].trim().startsWith("@")) {
        components[0] = components[0].replaceFirst("@", "");
      }
      return components;

      // If there is no two-hyphen boundary, it's assumed each annotation is exactly one line.
    } else {
      int annotationIndex = 0;
      int newlineIndex = 0;
      List<String> componentList = new ArrayList<String>();
      while (true) {
        annotationIndex = docString.indexOf("@", annotationIndex);
        newlineIndex = docString.indexOf("\n", annotationIndex);
        if (annotationIndex == -1) {
          break;
        }
        if (newlineIndex == -1) {
          newlineIndex = docString.length();
        }
        componentList.add(docString.substring(annotationIndex + 1, newlineIndex));
        annotationIndex = newlineIndex;
      }
      String[] componentArray = new String[componentList.size()];
      componentArray = componentList.toArray(componentArray);
      return componentArray;
    }
  }

  public static void main(String argv[]) {
    ProtoIndex index = new ProtoIndexer().indexProtos(argv);
    new ConnectAPIParser().parseAPI(index);
  }
}
