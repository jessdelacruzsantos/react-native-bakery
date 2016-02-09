package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by barlow on 2/2/16.
 */
public class ConnectType {
  protected final TypeElement rootType;
  protected final String packageName;
  protected final Optional<ConnectType> parentType;
  protected final Map<String, String> docAnnotations;
  private final String name;

  public static final Map<String, String> typeMap;
  static {
    Map<String, String> aMap = new HashMap<String, String>();
    aMap.put("int32", "integer");
    aMap.put("int64", "integer");
    aMap.put("bool", "boolean");
    aMap.put("string", "string");
    typeMap = aMap;
  }

  public ConnectType(TypeElement rootType, String packageName, Optional<ConnectType> parentType) {
    this.rootType = rootType;
    this.packageName = packageName;
    this.parentType = parentType;
    this.docAnnotations = new HashMap<>();
    this.name = this.generateName();
  }

  public TypeElement getRootType() {
    return rootType;
  }

  public String getPackageName() {
    return packageName;
  }

  public Optional<ConnectType> getParentType() {
    return parentType;
  }

  public String getName() {
    return this.name;
  }

  protected void parseDocumentationString(String docString) {
    List<String> components = DocString.parse(docString);
    for (String entry : components) {
      String keyword = entry.split(" ")[0];

      if (this.docAnnotations.containsKey(keyword)) {
        System.err.println("ERROR! Multiple doc annotations of same type found for type " + this.generateName());
      }

      docAnnotations.put(keyword, entry.replaceFirst(keyword, "").trim());
    }
  }

  public String generateName() {
    final Optional<ConnectType> parent = getParentType();
    if (parent.isPresent()) {
      return parent.get().generateName() + this.rootType.name();
    }
    return this.rootType.name();
  }
}
