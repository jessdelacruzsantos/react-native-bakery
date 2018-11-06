package com.squareup.apiparser;

import com.beust.jcommander.JCommander;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import static com.squareup.apiparser.Json.GSON;

public class ConnectAPIParser {
  // TODO: ssung to move to a configuration file once we start refactoring/rewriting the generator
  private static final ImmutableList<String> NAMESPACES = ImmutableList.of("marketplaces");

  // Compound ordering of ConnectEndpoint by (path, method).
  private static final Ordering<ConnectEndpoint> ENDPOINT_ORDERING = Ordering.natural()
      .onResultOf(ConnectEndpoint::getPath)
      .compound(Ordering.natural()
          .onResultOf(ConnectEndpoint::getHttpMethod));

  static class JsonAPI {
    final JsonObject swagger;
    final JsonObject enumMap;

    JsonAPI(JsonObject swagger, JsonObject enumMap) {
      this.swagger = swagger;
      this.enumMap = enumMap;
    }
  }

  public static JsonAPI parseAPI(ProtoIndex index, ReleaseStatus releaseStatus, String namespace, Configuration configuration)
      throws InvalidSpecException {
    // Transform all the symbols to JSON and write out to file
    JsonObject root = GSON.toJsonTree(configuration.swaggerBase()).getAsJsonObject();

    final ImmutableMap.Builder<String, String> enumMapBuilder = ImmutableMap.builder();

    JsonObject jsonEndpoints = new JsonObject();

    // Endpoint
    index.getEndpoints().stream()
        .filter(connectEndpoint -> releaseStatus.shouldInclude(connectEndpoint.getReleaseStatus()) && Namespace.isMatched(connectEndpoint.getReleaseStatus(), namespace, connectEndpoint.getNamespace()))
        .sorted(ENDPOINT_ORDERING)
        .forEach(endpoint -> {
          if (!jsonEndpoints.has(endpoint.getPath())) {
            jsonEndpoints.add(endpoint.getPath(), new JsonObject());
          }
          jsonEndpoints.getAsJsonObject(endpoint.getPath())
              .add(endpoint.getHttpMethod().toLowerCase(), endpoint.toJson());
        });
    root.add("paths", jsonEndpoints);

    // Enum
    JsonObject jsonTypes = new JsonObject();
    final Joiner join = Joiner.on(".");
    for (ConnectEnum enumm : index.getEnums().values()) {
      if (releaseStatus.shouldInclude(enumm.getReleaseStatus()) && Namespace.isMatched(enumm.getReleaseStatus(), namespace, enumm.getNamespace())) {
        jsonTypes.add(enumm.getName(), enumm.toJson(releaseStatus));
        enumm.getValues()
            .stream()
            .filter(e -> releaseStatus.shouldInclude(e.getReleaseStatus()))
            .forEach(v ->
                enumMapBuilder.put(join.join(enumm.getName(), v.getName()), v.getDescription()));
      }
    }

    //Datatype
    for (ConnectDatatype datatype : index.getDatatypes().values()) {
      if (releaseStatus.shouldInclude(datatype.getReleaseStatus()) && Namespace.isMatched(datatype.getReleaseStatus(), namespace, datatype.getNamespace())) {
        jsonTypes.add(datatype.getName(), datatype.toJson(releaseStatus, namespace));
      }
    }
    root.add("definitions", jsonTypes);

    final JsonObject map = GSON.toJsonTree(enumMapBuilder.build()).getAsJsonObject();
    return new JsonAPI(root, map);
  }

  private static void writeJson(String json, Path path) {
    System.out.println("Writing " + path);
    try (PrintWriter writer = new PrintWriter(path.toString(), "UTF-8")) {
      writer.println(json);
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }

  public static void main(String argv[]) {
    try {
      Configuration configuration = new Configuration();
      new JCommander(configuration, argv);
      Preconditions.checkArgument(!configuration.getProtobufLocations().isEmpty(),
          "At least one protobuf location is required");

      String sqVersion = configuration.getSqVersion();
      // Validate Square Version. Date format has to be YYYY-MM-DD.
      SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
      sdfrmt.setLenient(false);
      try{
        sdfrmt.parse(sqVersion);
      }
      catch (ParseException e){
        throw new RuntimeException(sqVersion + " is an invalid date format");
      }

      Optional<Path> outputArg = configuration.getOutputPath();
      Path outputPath;
      if (!outputArg.isPresent()) {
        outputPath = Paths.get(System.getProperty("user.dir"));
      } else {
        outputPath = outputArg.get();
        if (!outputPath.isAbsolute()) {
          outputPath = Paths.get(System.getProperty("user.dir")).resolve(outputArg.get());
        }
      }
      if (!Files.exists(outputPath)) {
        if (!outputPath.toFile().mkdirs()) {
          throw new RuntimeException("Unable to create output directory " + outputPath.toString());
        }
      }

      ImmutableList<String> protoPaths = ImmutableList.copyOf(configuration.getProtobufLocations());

      ProtoIndex index = new ProtoIndexer(configuration.isIgnoreOneofs(), sqVersion).indexProtos(protoPaths);

      JsonAPI api = getJsonAPI(configuration, ReleaseStatus.INTERNAL, "", index);
      Path allAPIOutputPath = outputPath.resolve("api_internal.json");
      writeJson(GSON.toJson(api.swagger), allAPIOutputPath);
      Path enumOutputPath = outputPath.resolve("enum_mapping.json");
      writeJson(GSON.toJson(api.enumMap), enumOutputPath);

      api = getJsonAPI(configuration, ReleaseStatus.PUBLIC,  "", index);
      if (!configuration.getV1APISchemaFile().isEmpty()) {
        // Because the incoming api.json lacks visibility information we only merge it into the
        // public definitions. This is not the best way to handle v1 endpoints.
        JsonParser parser = new JsonParser();
        JsonObject v1API =
            parser.parse(new FileReader(configuration.getV1APISchemaFile())).getAsJsonObject();

        JsonObject v2API = api.swagger;

        // Merge v1 endpoints into v2 schema
        mergeJsonObjectsUnderKey(v1API, v2API, "paths");

        // Merge v1 definitions into v2 definitions
        mergeJsonObjectsUnderKey(v1API, v2API, "definitions");
      }
      Path publicAPIOutputPath = outputPath.resolve("api.json");
      writeJson(GSON.toJson(api.swagger), publicAPIOutputPath);

      // (TODO) (DF-157) remove it once migrate beta to tags in api.json
      api = getJsonAPI(configuration, ReleaseStatus.BETA,  "", index);
      Path betaAPIOutputPath = outputPath.resolve("api_beta.json");
      writeJson(GSON.toJson(api.swagger), betaAPIOutputPath);

      api = getJsonAPI(configuration, ReleaseStatus.ALPHA,  "", index);
      Path alphaAPIOutputPath = outputPath.resolve("api_alpha.json");
      writeJson(GSON.toJson(api.swagger), alphaAPIOutputPath);

      // alpha json with namespaces
      for (String currentNamespace : NAMESPACES) {
        api = getJsonAPI(configuration, ReleaseStatus.ALPHA, currentNamespace, index);
        alphaAPIOutputPath = outputPath.resolve("api_alpha_" + currentNamespace + ".json");
        writeJson(GSON.toJson(api.swagger), alphaAPIOutputPath);
      }
    } catch (InvalidSpecException e) {
      String errorMsg;
      if (e.getContext().isPresent()) {
        errorMsg =
            String.format("Error occurred in %s: %s", e.getContext().get().location().toString(),
                e.getMessage());
      } else {
        errorMsg = e.getMessage();
      }
      System.out.println(errorMsg);
      System.exit(2);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      System.out.println("Failed to generate JSON APIs!");
      System.exit(2);
    }
  }

  // Merges all elements in a into b
  private static void mergeJsonObjectsUnderKey(JsonObject objA, JsonObject objB, String key) {
    JsonObject a = objA.getAsJsonObject(key);
    JsonObject b = objB.getAsJsonObject(key);
    if (a == null || b == null) {
      return;
    }

    for (Map.Entry<String, JsonElement> v1Endpoint : a.entrySet()) {
      String path = v1Endpoint.getKey();
      // If the same key exists in the v2 schema with a different value then halt with error
      if (b.has(path) && !b.equals(v1Endpoint.getValue())) {
        throw new InvalidSpecException.Builder(
            String.format("Key '%s' exists in both schemas with a different value", path)).build();
      }

      b.add(path, v1Endpoint.getValue());
    }
  }

  private static JsonAPI getJsonAPI(Configuration configuration,
      ReleaseStatus releaseStatus, String namespace, ProtoIndex index) {
    return parseAPI(index, releaseStatus, namespace, configuration);
  }
}
