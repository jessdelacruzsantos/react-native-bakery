package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barlow on 2/2/16.
 */
public class ConnectType {

  protected TypeElement rootType;
  protected String packageName;
  protected ConnectType parentType;
  protected Map<String, String> docAnnotations;
  private String name;

  public static final Map<String, String> typeMap;
  static {
    Map<String, String> aMap = new HashMap<String, String>();
    aMap.put("int32", "integer");
    aMap.put("int64", "integer");
    aMap.put("bool", "boolean");
    aMap.put("string", "string");
    typeMap = aMap;
  }

  public ConnectType(TypeElement rootType, String packageName, ConnectType parentType) {
    this.rootType = rootType;
    this.packageName = packageName;
    this.parentType = parentType;
    this.docAnnotations = new HashMap<String, String>();
    this.name = this.generateName();
  }

  public TypeElement getRootType() {
    return rootType;
  }

  public void setRootType(TypeElement rootType) {
    this.rootType = rootType;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public ConnectType getParentType() {
    return parentType;
  }

  public void setParentType(ConnectType parentType) {
    this.parentType = parentType;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  protected void parseDocumentationString(String docString) {
    String[] components;
    if (docString.equals("")) {
      return;

      // Public doc strings can be bounded by two hyphens to support multiline annotations.
    } else if (docString.contains("--")) {
      String publicDocString = docString.split("--")[1];
      components = publicDocString.split("\\s+@");
      if (components[0].trim().startsWith("@")) {
        components[0] = components[0].replaceFirst("@", "");
      }

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
      components = new String[componentList.size()];
      components = componentList.toArray(components);
    }

    for (String entry : components) {
      String keyword = entry.split(" ")[0];

      if (this.docAnnotations.containsKey(keyword)) {
        System.err.println("ERROR! Multiple doc annotations of same type found for type " + this.generateName());
      }

      docAnnotations.put(keyword, entry.replaceFirst(keyword, "").trim());
    }
  }

  public String generateName() {
    String parentName = "";

    if (this.parentType != null) {
      parentName = this.parentType.generateName();
    }

    return parentName + this.rootType.name();
  }
}
