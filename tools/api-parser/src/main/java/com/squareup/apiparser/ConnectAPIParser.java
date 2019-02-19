package com.squareup.apiparser;

import com.beust.jcommander.JCommander;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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
  public static void main(String argv[]) {

    // Validate configurable inputs
    Configuration configuration = new Configuration();
    new JCommander(configuration, argv);
    Preconditions.checkArgument(!configuration.getProtobufLocations().isEmpty(),
        "At least one protobuf location is required");

    String sqVersion = configuration.getSqVersion();
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

    // generate all json files
    try {
      ProtoIndexer index = new ProtoIndexer(configuration);

      // Generate INTERNAL
      Group group = new Group();
      group.status = ReleaseStatus.INTERNAL;
      JsonObject enumMap = index.toJsonEnumMap(configuration, group);
      Path enumOutputPath = outputPath.resolve("enum_mapping.json");
      writeJson(GSON.toJson(enumMap), enumOutputPath);

      // Generate PUBLIC
      group.status = ReleaseStatus.PUBLIC;
      JsonObject apiSpec = index.toJsonAPISpec(configuration, group);
      Path allOutputPath = outputPath.resolve("api.json");
      writeJson(GSON.toJson(apiSpec), allOutputPath);

      // Generate BETA
      group.status = ReleaseStatus.BETA;
      apiSpec = index.toJsonAPISpec(configuration, group);
      allOutputPath = outputPath.resolve("api_beta.json");
      writeJson(GSON.toJson(apiSpec), allOutputPath);

      // Generate ALPHA with namespaces
      group.status = ReleaseStatus.ALPHA;
      apiSpec = index.toJsonAPISpec(configuration, group);
      allOutputPath = outputPath.resolve("api_alpha.json");
      writeJson(GSON.toJson(apiSpec), allOutputPath);

      for (String currentNamespace : Configuration.NAMESPACES) {
        group.namespace = currentNamespace;
        apiSpec = index.toJsonAPISpec(configuration, group);
        allOutputPath = outputPath.resolve("api_alpha_" + currentNamespace + ".json");
        writeJson(GSON.toJson(apiSpec), allOutputPath);
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

  private static void writeJson(String json, Path path) {
    System.out.println("Writing " + path);
    try (PrintWriter writer = new PrintWriter(path.toString(), "UTF-8")) {
      writer.println(json);
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }
}
