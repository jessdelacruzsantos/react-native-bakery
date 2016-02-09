package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import com.squareup.wire.schema.internal.parser.ServiceElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtoIndex {

  private Map<String, ConnectDatatype> dtypes;
  private Map<String, ConnectEnum> enums;
  private List<ConnectEndpoint> endpoints;


  public ProtoIndex() {
    this.enums = new HashMap<String, ConnectEnum>();
    this.dtypes = new HashMap<String, ConnectDatatype>();
    this.endpoints = new ArrayList<ConnectEndpoint>();
  }

  public boolean populate(List<ConnectType> types, List<ConnectService> services) {

    // This does datatypes + enums, need to do endpoints
    for (ConnectType type : types) {
      TypeElement rootType = type.getRootType();
      if (rootType instanceof EnumElement) {
        ConnectEnum ce = new ConnectEnum((EnumElement)rootType, type.getPackageName(), type.getParentType());
        if (this.enums.containsKey(ce.getName())) {
          System.err.println("ERROR: Identical enum name detected: " + type.getName());
          return false;
        }
        this.enums.put(type.getName(), ce);
      } else if (rootType instanceof MessageElement) {
        ConnectDatatype cd = new ConnectDatatype((MessageElement)rootType, type.getPackageName(), type.getParentType());
        if (this.dtypes.containsKey(cd.getName())) {
          System.err.println("ERROR: Identical datatype name detected");
          return false;
        }
        this.dtypes.put(type.getName(), cd);
      } else {
        // Shouldn't happen
        System.out.println("Entity with unknown type encountered!");
        System.out.println(rootType.name());
        return false;
      }
    }

    // After done creating entities for every symbol, populate datatypes
    for (ConnectDatatype datatype : this.dtypes.values()) {
      datatype.populateFields(this);
    }

    for (ConnectService service : services) {
      // Index those endpoints yo
      for (RpcElement rpc : service.getRootService().rpcs()) {
        this.endpoints.add(new ConnectEndpoint(rpc, this));
      }
    }

    return true;
  }

  public Map<String, ConnectDatatype> getDatatypes() {
    return this.dtypes;
  }

  public Map<String, ConnectEnum> getEnums() {
    return this.enums;
  }

  public List<ConnectEndpoint> getEndpoints() {
    return this.endpoints;
  }


  public ConnectType getType(String typeName) {
    typeName = typeName.replaceFirst("actions.", "");
    typeName = typeName.replaceFirst("resources.", "");
    typeName = typeName.replaceAll("\\.", "");
    ConnectType typeToReturn = null;
    for (String enumName : this.enums.keySet()) {
      if (enumName.endsWith(typeName)) {
        if (typeToReturn != null) {
          System.err.println("Multiple matches found for requested type " + typeName);
          return null;
        } else {
          typeToReturn = this.enums.get(enumName);
        }
      }
    }

    for (String datatypeName : this.dtypes.keySet()) {
      if (datatypeName.endsWith(typeName)) {
        if (typeToReturn != null) {
          System.err.println("Multiple matches found for requested type " + typeName);
          return null;
        } else {
          typeToReturn = this.dtypes.get(datatypeName);
        }
      }
    }
    return typeToReturn;
  }
 /*
  public boolean addDatatype(TypeElement e, String packageName, TypeElement parent) {
    String qualifiedName;
    if (parent == null) {
      qualifiedName = packageName + "." + e.name();
    } else {
      qualifiedName = packageName + "." + parent.name() + "." + e.name();
    }
    if (!this.datatypes.containsKey(qualifiedName)) {
      this.datatypes.put(qualifiedName, e);
      return true;
    } else {
      return false;
    }
  }

  public boolean addService(ServiceElement e, String packageName) {
    String qualifiedName = packageName + "." + e.name();
    if (!this.services.containsKey(qualifiedName)) {
      this.services.put(qualifiedName, e);
      return true;
    } else {
      return false;
    }
  }

  public TypeElement getDatatype(String name) {
    for (String qualifiedName : this.datatypes.keySet()) {
      if (qualifiedName.endsWith(name)) {
        return this.datatypes.get(qualifiedName);
      }
    }
    return null;
  }

  public String getQualifiedName(String name) {
    for (String qualifiedName : this.datatypes.keySet()) {
      if (qualifiedName.endsWith(name)) {
        return qualifiedName;
      }
    }
    return null;
  }

  public Map<String, TypeElement> getDatatypes() {
    return this.datatypes;
  }

  public Map<String, ServiceElement> getServices() {
    return this.services;
  }       */

}
