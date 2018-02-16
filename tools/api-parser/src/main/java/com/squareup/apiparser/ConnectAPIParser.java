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

import static com.squareup.apiparser.Json.GSON;

public class ConnectAPIParser {

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

  private static Map<String, Object> swaggerBase(Configuration configuration) {
    ImmutableMap.Builder<String, String> contact = ImmutableMap.<String, String>builder()
        .put("name", "Square Developer Platform")
        .put("email", "developers@squareup.com")
        .put("url", "https://squareup.com/developers");

    ImmutableMap.Builder<String, String> license = ImmutableMap.<String, String>builder()
        .put("name", "Apache 2.0")
        .put("url", "http://www.apache.org/licenses/LICENSE-2.0.html");

    ImmutableMap.Builder<String, Object> info = ImmutableMap.<String, Object>builder()
        .put("version", configuration.getVersion())
        .put("title", configuration.getTitle())
        .put("description", "Client library for accessing the Square Connect APIs")
        .put("termsOfService", "https://connect.squareup.com/tos")
        .put("contact", contact.build())
        .put("license", license.build());

    ImmutableMap.Builder<String, String> docs = ImmutableMap.<String, String>builder()
        .put("description", "Read the official documentation here:")
        .put("url", "https://docs.connect.squareup.com/");

    return ImmutableMap.<String, Object>builder()
        .put("swagger", "2.0")
        .put("info", info.build())
        .put("externalDocs", docs.build())
        .put("host", configuration.getHost())
        .put("schemes", ImmutableList.of("https"))
        .put("consumes", ImmutableList.of("application/json"))
        .put("produces", ImmutableList.of("application/json"))
        .build();
  }

  private static Map<String, Object> securityDefinitions() {
    // TODO - These should be extracted from protos directly
    ImmutableMap.Builder<String, String> scopes = ImmutableMap.<String, String>builder()
        .put("MERCHANT_PROFILE_READ", "GET endpoints related to a merchant's business and location entities. Almost all Connect API applications need this permission in order to obtain a merchant's location IDs")
        .put("PAYMENTS_READ", "GET endpoints related to transactions and refunds")
        .put("PAYMENTS_WRITE", "POST, PUT, and DELETE endpoints related to transactions and refunds. E-commerce applications must request this permission")
        .put("CUSTOMERS_READ", " GET endpoints related to customer management")
        .put("CUSTOMERS_WRITE", "POST, PUT, and DELETE endpoints related to customer management")
        .put("SETTLEMENTS_READ", "GET endpoints related to settlements (deposits)")
        .put("BANK_ACCOUNTS_READ", "GET endpoints related to a merchant's bank accounts")
        .put("ITEMS_READ", "GET endpoints related to a merchant's item library")
        .put("ITEMS_WRITE", "POST, PUT, and DELETE endpoints related to a merchant's item library")
        .put("ORDERS_READ", "GET endpoints related to a merchant\u0027s orders")
        .put("ORDERS_WRITE", "POST, PUT, and DELETE endpoints related to a merchant\u0027s orders")
        .put("EMPLOYEES_READ", "GET endpoints related to employee management")
        .put("EMPLOYEES_WRITE", "POST, PUT, and DELETE endpoints related to employee management")
        .put("TIMECARDS_READ", "GET endpoints related to employee timecards")
        .put("TIMECARDS_WRITE", "POST, PUT, and DELETE endpoints related to employee timecards")
        .put("PAYMENTS_WRITE_ADDITIONAL_RECIPIENTS", "Allow third party applications to deduct a portion of each transaction amount.");

    ImmutableMap.Builder<String, Object> oauth = ImmutableMap.<String, Object>builder()
        .put("type", "oauth2")
        .put("authorizationUrl", "https://connect.squareup.com/oauth2/authorize")
        .put("flow", "accessCode")
        .put("tokenUrl", "https://connect.squareup.com/oauth2/token")
        .put("scopes", scopes.build());

    ImmutableMap.Builder<String, Object> clientAuth = ImmutableMap.<String, Object>builder()
        .put("type", "apiKey")
        .put("in", "header")
        .put("name", "Authorization");

    return ImmutableMap.<String, Object>builder()
        .put("oauth2", oauth.build())
        .put("oauth2ClientSecret", clientAuth.build())
        .build();
  }

  JsonAPI parseAPI(ProtoIndex index, ApiReleaseType apiReleaseType, Configuration configuration)
      throws InvalidSpecException {
    // Transform all the symbols to JSON and write out to file
    JsonObject root = GSON.toJsonTree(swaggerBase(configuration)).getAsJsonObject();
    root.add("securityDefinitions", GSON.toJsonTree(securityDefinitions()));

    final ImmutableMap.Builder<String, String> enumMapBuilder = ImmutableMap.builder();

    JsonObject jsonEndpoints = new JsonObject();

    List<ConnectEndpoint> endpoints = ENDPOINT_ORDERING.sortedCopy(index.getEndpoints());
    for (ConnectEndpoint endpoint : endpoints) {
      if (apiReleaseType.shouldInclude(endpoint.getReleaseType())) {
        if (!jsonEndpoints.has(endpoint.getPath())) {
          jsonEndpoints.add(endpoint.getPath(), new JsonObject());
        }
        jsonEndpoints.getAsJsonObject(endpoint.getPath())
            .add(endpoint.getHttpMethod().toLowerCase(), endpoint.toJson());
      }
    }
    root.add("paths", jsonEndpoints);

    JsonObject jsonTypes = new JsonObject();
    final Joiner join = Joiner.on(".");
    for (ConnectEnum enumm : index.getEnums().values()) {
      if (apiReleaseType.shouldInclude(enumm.getReleaseType())) {
        jsonTypes.add(enumm.getName(), enumm.toJson(apiReleaseType));
        enumm.getValues()
            .stream()
            .filter(e -> apiReleaseType.shouldInclude(e.getReleaseType()))
            .forEach(v ->
            enumMapBuilder.put(join.join(enumm.getName(), v.getName()), v.getDescription()));
      }
    }

    for (ConnectDatatype datatype : index.getDatatypes().values()) {
      if (apiReleaseType.shouldInclude(datatype.getReleaseType())) {
        jsonTypes.add(datatype.getName(), datatype.toJson(apiReleaseType));
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

      ProtoIndex index = new ProtoIndexer(configuration.isIgnoreOneofs()).indexProtos(protoPaths);

      JsonAPI api = getJsonAPI(configuration, ApiReleaseType.ALL, index);
      Path allAPIOutputPath = outputPath.resolve("api_internal.json");
      writeJson(GSON.toJson(api.swagger), allAPIOutputPath);
      Path enumOutputPath = outputPath.resolve("enum_mapping.json");
      writeJson(GSON.toJson(api.enumMap), enumOutputPath);

      api = getJsonAPI(configuration, ApiReleaseType.PUBLIC, index);
      if (!configuration.getV1APISchemaFile().isEmpty()) {
        // Because the incoming api.json lacks visibility information we only merge it into the
        // public definitions. This is not the best way to handle v1 endpoints.
        JsonParser parser = new JsonParser();
        JsonObject v1API = parser.parse(new FileReader(configuration.getV1APISchemaFile())).getAsJsonObject();

        JsonObject v2API = api.swagger;

        // Merge v1 endpoints into v2 schema
        mergeJsonObjectsUnderKey(v1API, v2API, "paths");

        // Merge v1 definitions into v2 definitions
        mergeJsonObjectsUnderKey(v1API, v2API, "definitions");
      }
      Path publicAPIOutputPath = outputPath.resolve("api.json");
      writeJson(GSON.toJson(api.swagger), publicAPIOutputPath);

      api = getJsonAPI(configuration, ApiReleaseType.BETA, index);
      Path betaAPIOutputPath = outputPath.resolve("api_beta.json");
      writeJson(GSON.toJson(api.swagger), betaAPIOutputPath);

      api = getJsonAPI(configuration, ApiReleaseType.UPCOMING, index);
      Path upcomingAPIOutputPath = outputPath.resolve("api_upcoming.json");
      writeJson(GSON.toJson(api.swagger), upcomingAPIOutputPath);
    } catch (InvalidSpecException e) {
      String errorMsg;
      if (e.getContext().isPresent()) {
        errorMsg = String.format("Error occurred in %s: %s", e.getContext().get().location().toString(), e.getMessage());
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
        throw new InvalidSpecException.Builder(String.format("Key '%s' exists in both schemas with a different value", path)).build();
      }

      b.add(path, v1Endpoint.getValue());
    }
  }

  private static JsonAPI getJsonAPI(Configuration configuration,
      ApiReleaseType apiReleaseType, ProtoIndex index) {
    return new ConnectAPIParser().parseAPI(index, apiReleaseType, configuration);
  }
}
