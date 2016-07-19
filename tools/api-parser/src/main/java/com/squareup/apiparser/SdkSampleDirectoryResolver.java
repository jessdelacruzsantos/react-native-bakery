package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The {@code SdkSampleDirectoryResolver} class contains static functions that resolves a
 * combination of datatype names (e.g. ChargeRequest, ListLocationsResponse), sample directory names
 * (which correspond to endpoint names, e.g. Charge, ListLocations), and a list of supported
 * languages (e.g. [RUBY, CSHARP, PHP] etc.) into a JSON object that will be placed under the
 * custom {@code x-sq-sdk-sample-code} field in the "definitions" portion of the Swagger spec.
 *
 * In particular, the static functions help you output a JSON object that looks like this:
 *
 * <code>
 * {
 *   "ruby": "/sdk_samples/ListLocations/ListLocationsRequest.ruby",
 *   "php": "/sdk_samples/ListLocations/ListLocationsRequest.php",
 *   "csharp": "/sdk_samples/ListLocations/ListLocationsRequest.csharp",
 * }
 * </code>
 */
public class SdkSampleDirectoryResolver {
  /**
   * Returns a function that resolves a sample directory path into an SDK JSON object.
   *
   * @param datatypeName Data type name (e.g. ListLocationsRequest, ChargeResponse)
   * @param supportedLanguages Supported languages (chosen from {@link SdkLanguage})
   * @return A function that takes a sample directory path (e.g. /sdk_samples/Charge) and resolves
   *         it into a JSON object.
   */
  public static Function<String, ? extends JsonElement> resolveSamplePath(
      String datatypeName, Iterable<SdkLanguage> supportedLanguages) {
    return (sampleDirPath) -> {
      Map<String, String> samplePaths =
          resolveSamplePathsToMap(datatypeName, supportedLanguages, sampleDirPath);
      Gson gson = new GsonBuilder().create();
      return gson.toJsonTree(samplePaths);
    };
  }

  /**
   * Convenience function for {@link #resolveSamplePath(String, Iterable)} that specifies
   * {@link SdkLanguage#values()} for the {@code supportedLanguages} parameter.
   */
  public static Function<String, ? extends JsonElement> resolveSamplePath(
      String datatypeName) {
    return resolveSamplePath(datatypeName, ImmutableList.copyOf(SdkLanguage.values()));
  }

  private static Map<String, String> resolveSamplePathsToMap(String datatypeName,
      Iterable<SdkLanguage> supportedLanguages, String sampleDirPath) {
    Map<String, String> sampleMap = new HashMap<>();

    for (SdkLanguage sdkLanguage : supportedLanguages) {
      // Example: "/sdk_samples/Charge/ChargeResponse.ruby"
      String language = sdkLanguage.toString().toLowerCase();
      String samplePath =
          String.format("%s/%s.%s", sampleDirPath, datatypeName, language);
      sampleMap.put(language, samplePath);
    }

    return sampleMap;
  }
}
