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

  private List<ConnectDatatype> datatypes;
  private List<ConnectEnum> enums;
  private List<ConnectEndpoint> endpoints;

  public ConnectAPIParser() {
    this.datatypes = new ArrayList<ConnectDatatype>();
    this.enums = new ArrayList<ConnectEnum>();
    this.endpoints = new ArrayList<ConnectEndpoint>();
  }

  public void parseAPI(ProtoIndex index) {

    Map<String, TypeElement> types = index.getDatatypes();

    /* Generate enums and datatypes */
    for (String key : types.keySet()) {
      this.processType(types.get(key), key, null);
    }

    /* Generate endpoints */
    Map<String, ServiceElement> services = index.getServices();
    for (ServiceElement service : services.values()) {
      for (RpcElement rpc : service.rpcs()) {
        endpoints.add(new ConnectEndpoint(rpc, index));
      }
    }

    // Transform all the symbols to JSON and write out to file
    JSONObject root = new JSONObject();
    JSONArray jsonEnums = new JSONArray();
    for (ConnectEnum enumm : this.enums) {
      jsonEnums.put(enumm.toJson());
    }
    root.put("enums", jsonEnums);

    JSONArray jsonDatatypes = new JSONArray();
    for (ConnectDatatype datatype : this.datatypes) {
      jsonDatatypes.put(datatype.toJson());
    }
    root.put("datatypes", jsonDatatypes);

    JSONArray jsonEndpoints = new JSONArray();
    for (ConnectEndpoint endpoint : this.endpoints) {
      jsonEndpoints.put(endpoint.toJson());
    }
    root.put("endpoints", jsonEndpoints);

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
      components = publicDocString.split("\n@");
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

  private void processType(TypeElement type, String id, TypeElement parent) {
    if (type instanceof MessageElement) {
      ConnectDatatype datatype = new ConnectDatatype((MessageElement)type, id);

      // If a type is defined inside another type, include the parent type's name in the child's
      if (parent != null) {
        datatype.setName(parent.name() + "." + type.name());
      }
      datatypes.add(datatype);

    } else if (type instanceof EnumElement) {
      ConnectEnum enumm = new ConnectEnum((EnumElement)type, id);

      // If a type is defined inside another type, include the parent type's name in the child's
      if (parent != null) {
        enumm.setName(parent.name() + "." + type.name());
      }
      enums.add(enumm);
    }

    for (TypeElement subtype : type.nestedTypes()) {
      this.processType(subtype, id + "." + subtype.name(), type);
    }
  }

  public static void main(String argv[]) {
    ProtoIndex index = ProtoIndexer.indexProtos(argv);
    APIParser generator = new ConnectAPIParser();
    generator.parseAPI(index);
  }
}
