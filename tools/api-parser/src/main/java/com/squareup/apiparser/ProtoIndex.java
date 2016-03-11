package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ProtoIndex {
  private final ExampleResolver exampleResolver;
  private final Map<String, ConnectDatatype> dtypes = new TreeMap<>();
  private final Map<String, ConnectEnum> enums = new TreeMap<>();
  private final List<ConnectEndpoint> endpoints = new ArrayList<>();

  public ProtoIndex(ExampleResolver exampleResolver) {
    this.exampleResolver = checkNotNull(exampleResolver);
  }

  public void populate(List<ConnectType> types, List<ConnectService> services)
      throws IllegalArgumentException, AnnotationException {
    // This does datatypes + enums, need to do endpoints
    for (ConnectType type : types) {
      TypeElement rootType = type.getRootType();
      if (rootType instanceof EnumElement) {
        ConnectEnum ce = new ConnectEnum((EnumElement) rootType, type.getPackageName(),
            type.getParentType());
        checkArgument(!enums.containsKey(ce.getName()), "Already seen %s", ce.getName());
        this.enums.put(type.getName(), ce);
      } else if (rootType instanceof MessageElement) {
        ConnectDatatype cd = new ConnectDatatype(
            rootType, type.getPackageName(), type.getParentType(), exampleResolver);
        checkArgument(!dtypes.containsKey(cd.getName()), "Already seen %s", cd.getName());
        this.dtypes.put(type.getName(), cd);
      } else {
        throw new IllegalArgumentException("Encountered a malformed proto");
      }
    }

    // After done creating entities for every symbol, populate datatypes
    dtypes.values().stream().forEach(d -> d.populateFields(this));
    for (final ConnectService service : services) {
      endpoints.addAll(service.getRootService()
          .rpcs()
          .stream()
          .map(rpc -> new ConnectEndpoint(rpc, this))
          .collect(Collectors.toList()));
    }
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

  public Optional<ConnectEnum> getEnumType(String typeName) {
    final String type = Protos.cleanName(typeName);
    final Optional<String> enumType =
        enums.keySet().stream().filter(e -> e.endsWith(type)).findFirst();
    return enumType.map(enums::get);
  }

  public Optional<ConnectDatatype> getDataType(String typeName) {
    final String type = Protos.cleanName(typeName);
    final Optional<String> dataType =
        dtypes.keySet().stream().filter(d -> d.endsWith(type)).findFirst();
    return dataType.map(dtypes::get);
  }
}
