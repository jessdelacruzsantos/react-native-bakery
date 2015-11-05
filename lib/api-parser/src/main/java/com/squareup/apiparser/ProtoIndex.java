package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.ServiceElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.HashMap;
import java.util.Map;

public class ProtoIndex {

  private Map<String, TypeElement> datatypes;
  private Map<String, ServiceElement> services;

  public ProtoIndex() {
    this.datatypes = new HashMap<String, TypeElement>();
    this.services = new HashMap<String, ServiceElement>();
  }

  public boolean addDatatype(TypeElement e, String packageName) {
    String qualifiedName = packageName + "." + e.name();
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

  public Map<String, TypeElement> getDatatypes() {
    return this.datatypes;
  }

  public Map<String, ServiceElement> getServices() {
    return this.services;
  }

}
