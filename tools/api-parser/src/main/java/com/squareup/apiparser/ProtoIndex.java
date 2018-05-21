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

class ProtoIndex {
  private final ExampleResolver exampleResolver;
  private final Map<String, ConnectDatatype> datatypes = new TreeMap<>();
  private final Map<String, ConnectEnum> enums = new TreeMap<>();
  private final List<ConnectEndpoint> endpoints = new ArrayList<>();
  private final boolean ignoreOneofs;
  private final String sqVersion;

  ProtoIndex(ExampleResolver exampleResolver, boolean ignoreOneofs, String sqVersion) {
    this.exampleResolver = checkNotNull(exampleResolver);
    this.ignoreOneofs = ignoreOneofs;
    this.sqVersion = sqVersion;
  }

  String getSqVersion() {
    return sqVersion;
  }

  void populate(List<ConnectType> types, List<ConnectService> services)
      throws IllegalArgumentException {
    // This does datatypes + enums, need to do endpoints
    for (ConnectType type : types) {
      TypeElement rootType = type.getRootType();
      if (rootType instanceof EnumElement) {
        ConnectEnum ce = new ConnectEnum(
            type.getReleaseStatus(), (EnumElement) rootType, type.getPackageName(),
            type.getParentType());
        checkArgument(!enums.containsKey(ce.getName()), "Already seen %s", ce.getName());
        this.enums.put(type.getName(), ce);
      } else if (rootType instanceof MessageElement) {
        ConnectDatatype cd = new ConnectDatatype(
            type.getReleaseStatus(),
            rootType, type.getPackageName(), type.getParentType(), exampleResolver, ignoreOneofs);
        checkArgument(!datatypes.containsKey(cd.getName()), "Already seen %s", cd.getName());
        this.datatypes.put(type.getName(), cd);
      } else {
        throw new IllegalArgumentException("Encountered a malformed proto");
      }
    }

    // After done creating entities for every symbol, populate datatypes
    datatypes.values().forEach(d -> d.populateFields(this));
    for (final ConnectService service : services) {
      endpoints.addAll(service.getRootService()
          .rpcs()
          .stream()
          .map(rpc -> new ConnectEndpoint(rpc, this,
              ProtoOptions.getExplicitReleaseStatus(rpc.options(), "common.method_status")
              .orElse(service.getReleaseStatus())))
          .collect(Collectors.toList()));
    }
  }

  Map<String, ConnectDatatype> getDatatypes() {
    return this.datatypes;
  }

  Map<String, ConnectEnum> getEnums() {
    return this.enums;
  }

  List<ConnectEndpoint> getEndpoints() {
    return this.endpoints;
  }

  Optional<ConnectEnum> getEnumType(String typeName) {
    final String type = Protos.cleanName(typeName);
    final Optional<String> enumType =
        enums.keySet().stream().filter(e -> e.equals(type) || e.endsWith("." + type)).findFirst();
    return enumType.map(enums::get);
  }

  Optional<ConnectDatatype> getDataType(String typeName) {
    final String type = Protos.cleanName(typeName);
    final Optional<String> dataType = datatypes.keySet()
        .stream()
        .filter(d -> d.equals(type) || d.endsWith("." + type))
        .findFirst();
    return dataType.map(datatypes::get);
  }
}
