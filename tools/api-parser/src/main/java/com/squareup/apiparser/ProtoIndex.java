package com.squareup.apiparser;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class ProtoIndex {
  private Map<String, ConnectDatatype> dtypes;
  private Map<String, ConnectEnum> enums;
  private List<ConnectEndpoint> endpoints;

  public ProtoIndex() {
    this.enums = new HashMap<>();
    this.dtypes = new HashMap<>();
    this.endpoints = new ArrayList<>();
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
        System.out.println("Entity with unknown type encountered: " + rootType.name());
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

  public ConnectType getType(@NotNull String typeName) {
    Preconditions.checkNotNull(typeName);
    final LinkedList<String> strings = new LinkedList<>(Splitter.on('.').splitToList(typeName));
    Iterables.removeAll(strings, ImmutableList.of("actions", "resources"));
    final String type = Joiner.on("").join(strings);
    final List<String> enumtypes = enums.keySet().stream().filter(e -> e.endsWith(type)).collect(Collectors.toList());
    final List<String> datatypes = dtypes.keySet().stream().filter(d -> d.endsWith(type)).collect(Collectors.toList());

    final boolean bothEmpty = enumtypes.isEmpty() && datatypes.isEmpty();
    if (datatypes.size() > 1 || enumtypes.size() > 1 || bothEmpty) {
      System.err.println("Either no matches or multiple matches found for requested type " + typeName);
      return null;
    }

    return (!enumtypes.isEmpty()) ? enums.get(enumtypes.get(0)) : dtypes.get(datatypes.get(0));
  }
}
