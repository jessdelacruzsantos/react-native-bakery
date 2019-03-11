package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.Location;
import com.squareup.wire.schema.internal.parser.ProtoFileElement;
import com.squareup.wire.schema.internal.parser.ProtoParser;
import com.squareup.wire.schema.internal.parser.ServiceElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import okio.BufferedSource;
import okio.Okio;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import com.google.common.collect.Ordering;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.squareup.apiparser.Json.GSON;
import com.google.common.base.Joiner;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

class ProtoIndexer {
  private static final ImmutableList<String> IGNORED_PROTOS = ImmutableList.of(
      "squareup/connect/v2/common/options.proto"
  );
  private final boolean ignoreOneofs;
  private final String sqVersion;
  private final ExampleResolver exampleResolver;

  private final Map<String, ConnectDatatype> datatypes = new TreeMap<>();
  private final Map<String, ConnectEnum> enums = new TreeMap<>();
  private final List<ConnectEndpoint> endpoints = new ArrayList<>();

  // Compound ordering of ConnectEndpoint by (path, method).
  private static final Ordering<ConnectEndpoint> ENDPOINT_ORDERING = Ordering.natural()
      .onResultOf(ConnectEndpoint::getPath)
      .compound(Ordering.natural()
          .onResultOf(ConnectEndpoint::getHttpMethod));

  public JsonObject toJsonEnumMap(Configuration configuration, Group group){
    final ImmutableMap.Builder<String, String> enumMapBuilder = ImmutableMap.builder();
    final Joiner join = Joiner.on(".");
    for (ConnectEnum enumType : enums.values()) {
      if (group.shouldInclude(enumType.getGroup())) {
        enumType.getValues()
            .stream()
            .filter(enumConstant -> group.shouldInclude(enumConstant.getGroup()))
            .forEach(enumConstant ->
                enumMapBuilder.put(join.join(enumType.getName(), enumConstant.getName()), enumConstant.getDescription()));
      }
    }

    return GSON.toJsonTree(enumMapBuilder.build()).getAsJsonObject();
  }

  public JsonObject toJsonAPISpec(Configuration configuration, Group group, Visibility visibility)
      throws InvalidSpecException {
    // Transform all the symbols to JSON and write out to file
    JsonObject root = GSON.toJsonTree(configuration.swaggerBase()).getAsJsonObject();

    // Endpoint
    JsonObject jsonEndpoints = new JsonObject();
    endpoints.stream()
        .filter(endpoint -> group.shouldInclude(endpoint.getGroup()))
        .filter(endpoint -> (endpoint.getVisibility() == Visibility.NORMAL) || (endpoint.getVisibility() == visibility))
        .sorted(ENDPOINT_ORDERING)
        .forEach(endpoint -> {
          // If endpoint path doesn't exist, create one.
          if (!jsonEndpoints.has(endpoint.getPath())) {
            jsonEndpoints.add(endpoint.getPath(), new JsonObject());
          }

          jsonEndpoints.getAsJsonObject(endpoint.getPath()).add(endpoint.getHttpMethod().toLowerCase(), endpoint.toJson());
        });
    root.add("paths", jsonEndpoints);

    // Enum
    JsonObject jsonTypes = new JsonObject();
    for (ConnectEnum enumType : enums.values()) {
      if (group.shouldInclude(enumType.getGroup()) && (enumType.getVisibility() == Visibility.NORMAL) || (enumType.getVisibility() == visibility)) {
        jsonTypes.add(enumType.getName(), enumType.toJson(group));
      }
    }

    //Datatype
    for (ConnectDatatype datatype : datatypes.values()) {
      if (group.shouldInclude(datatype.getGroup()) && (datatype.getVisibility() == Visibility.NORMAL) || (datatype.getVisibility() == visibility)) {
        jsonTypes.add(datatype.getName(), datatype.toJson(group));
      }
    }
    root.add("definitions", jsonTypes);

    return root;
  }

  ProtoIndexer(Configuration config) throws IllegalArgumentException {
    this.ignoreOneofs = config.isIgnoreOneofs();
    this.sqVersion = config.getSqVersion();
    ImmutableList<String> protoPaths = ImmutableList.copyOf(config.getProtobufLocations());

    this.exampleResolver = new ExampleResolver(protoPaths);

    // Index every suitable proto file
    for (String path : protoPaths) {
      try {
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
          @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (shouldIgnoreProto(file.toString())) {
              return FileVisitResult.CONTINUE;
            }
            if (Pattern.matches(".*\\.proto\\Z", file.toString())) {
              try{
                populate(file.toFile());
              }
              catch(IOException e){
                System.out.println("Error loading " + file.toString());
                System.exit(2);
              }
            }
            return FileVisitResult.CONTINUE;
          }
        });
      }
      catch(IOException e){
        System.out.println("Error loading " + path);
        System.exit(2);
      }
    }
    this.datatypes.values().forEach(current -> current.populateFields(this));
    this.endpoints.forEach(current -> current.populateFields(this));

    if(config.validatorEnabled){
      // Run the validator
      this.datatypes.values().forEach(current -> {
        current.validate();
        current.getFields().forEach(field -> field.validate());
      });
      this.enums.values().forEach(current -> {
        current.validate();
        current.getValues().forEach(enumValue -> enumValue.validate());
      });
      this.endpoints.forEach(current -> current.validate());

      // Print errors
      List<String> errors = Validator.getErrors();

      for (String error : errors){
          System.out.println(error);
      }
      if (errors.size() > 0){
          throw new InvalidSpecException.Builder("There are " + errors.size() +" errors. Please resolve them.")
          .build();
      }
    }
  }

  // Index enums, datatypes, and endpoints
  private void populate(File file) throws IOException, IllegalArgumentException, InvalidSpecException  {
    try (BufferedSource buffer = Okio.buffer(Okio.source(file))) {
      final Location location = Location.get(file.getCanonicalPath());
      final ProtoFileElement proto = ProtoParser.parse(location, buffer.readUtf8());

      // Retreive file level status and namespace
      Group defaultGroup = new Group();
      defaultGroup.status = ProtoOptions.getReleaseStatus(proto.options(), "common.file_status");
      defaultGroup.namespace = ProtoOptions.getStringValue(proto.options(), "common.file_namespace").orElse("");

      // Index datatypes + enums
      proto.types()
          .forEach(type -> addType(defaultGroup, type, proto.packageName(), Optional.empty()));

      // Index endpoints
      for (ServiceElement service : proto.services()) {
        for (RpcElement endpoint : service.rpcs()){
          ConnectEndpoint current = new ConnectEndpoint(
                endpoint,
                defaultGroup,
                sqVersion
            );
          if(current.getGroup().isVisible()){
            this.endpoints.add(current);
          }
        }
      }
    }
  }

  private void addType(Group defaultGroup, TypeElement type, String packageName,
      Optional<ConnectType> parent) throws IllegalArgumentException {

    ConnectType nextParent;
    if (type instanceof EnumElement) {
      ConnectEnum current = new ConnectEnum(
          defaultGroup,
          (EnumElement) type,
          packageName,
          parent);
      if(current.getGroup().isVisible()){
        checkArgument(!enums.containsKey(current.getName()), "Already seen %s", current.getName());
        this.enums.put(current.getName(), current);
      }
      nextParent = (ConnectType) current;
    } else if (type instanceof MessageElement) {
      ConnectDatatype current = new ConnectDatatype(
          defaultGroup,
          type,
          packageName,
          parent,
          exampleResolver,
          ignoreOneofs);
      if(current.getGroup().isVisible()){
        checkArgument(!datatypes.containsKey(current.getName()), "Already seen %s", current.getName());
        this.datatypes.put(current.getName(), current);
      }
      nextParent = (ConnectType) current;
    }
    else {
      throw new IllegalArgumentException("Encountered a malformed proto");
    }

    for (TypeElement subType : type.nestedTypes()) {
      addType(nextParent.getGroup(), subType, packageName, Optional.of(nextParent));
    }
  }

  private boolean shouldIgnoreProto(String protoFile) {
    for (String ignored : IGNORED_PROTOS) {
      if (protoFile.endsWith(ignored)) {
        return true;
      }
    }
    return false;
  }
  Map<String, ConnectDatatype> getDatatypes() {
    return this.datatypes;
  }

  Optional<ConnectEnum> getEnumType(String typeName) {
    final String type = Protos.cleanName(typeName);
    return Optional.ofNullable(enums.get(type));
  }

  Optional<ConnectDatatype> getDataType(String typeName) {
    final String type = Protos.cleanName(typeName);
    return Optional.ofNullable(datatypes.get(type));
  }
}
