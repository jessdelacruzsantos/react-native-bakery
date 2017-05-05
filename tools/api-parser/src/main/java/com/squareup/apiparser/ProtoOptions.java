package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.squareup.wire.schema.Field;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.OptionElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

import static com.google.common.base.Preconditions.checkState;

/**
 * See https://stash.corp.squareup.com/projects/SQ/repos/java/browse/common-protos/src/main/proto/squareup/common/validation.proto
 * for supported validations.
 */
public class ProtoOptions {

  public static final String RELEASE_STATUS_PUBLIC = "PUBLIC";
  public static final String RELEASE_STATUS_BETA = "BETA";
  public static final String RELEASE_STATUS_UPCOMING = "UPCOMING";
  public static final String RELEASE_STATUS_INTERNAL = "INTERNAL";

  private static final Map<String, Function<OptionElement, Optional<Pair<String, Object>>>>
      TRANSFORMERS = ImmutableMap.of(
      "squareup.validation.length", ProtoOptions::length,
      "squareup.validation.not_empty", ProtoOptions::notEmpty,
      "squareup.validation.range", ProtoOptions::range,
      "squareup.validation.matches_pattern", ProtoOptions::matchesPattern,

      // required is a special case; see isRequired method
      "squareup.validation.required", option -> Optional.empty());

  public static Optional<String> exampleFilename(Collection<OptionElement> options) {
    return getStringValue(options, "common.json_example_path");
  }

  public static Optional<String> exampleType(Collection<OptionElement> options) {
    return getStringValue(options, "common.json_example_type");
  }

  public static Optional<String> sdkSampleDirectory(Collection<OptionElement> options) {
    return getStringValue(options, "common.sdk_sample_directory");
  }

  public static Map<String, Object> validations(Collection<OptionElement> options) {
    ImmutableMap.Builder<String, Object> validations = ImmutableMap.builder();
    options.stream()
        .map(ProtoOptions::validation)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(validation -> validations.put(validation.getLeft(), validation.getRight()));
    return validations.build();
  }

  public static boolean isRequired(FieldElement field) {
    if (field.label() == Field.Label.REQUIRED) {
      return true;
    }
    return getBooleanValueOrDefault(field.options(), "squareup.validation.required", false)
        || getBooleanValueOrDefault(field.options(), "squareup.validation.not_empty", false)
        || getIntegerValue(field.options(), "(squareup.validation.length).min_length").orElse(0) > 0;
  }

  public static boolean isPathParam(FieldElement field) {
    return getBooleanValueOrDefault(field.options(), "common.path_param", false);
  }

  public static boolean getBooleanValueOrDefault(Collection<OptionElement> options, String optionName, Boolean defaultIfMissing) {
    return options.stream()
        .filter(option -> optionName.endsWith(option.name()))
        .findFirst()
        .map(option -> Boolean.parseBoolean((String) option.value()))
        .orElse(defaultIfMissing);
  }

  public static Optional<String> getStringValue(Collection<OptionElement> options,
      String optionName) {
    return options.stream()
        .filter(option -> optionName.endsWith(option.name()))
        .findFirst()
        .map(option -> (String) option.value());
  }

  public static Optional<Integer> getIntegerValue(Collection<OptionElement> options,
      String optionName) {
    return options.stream()
        .filter(option -> optionName.endsWith(option.name()))
        .findFirst()
        .map(option -> Integer.parseInt((String) option.value()));
  }

  public static String getReleaseStatus(Collection<OptionElement> options, String optionName) {
      return getStringValue(options, optionName).orElse(RELEASE_STATUS_PUBLIC);
  }

  public static Set<String> getOAuthPermissions(RpcElement rpcElement) {
    return rpcElement.options().stream()
        .filter(option -> option.name().endsWith("common.oauth_permissions"))
        .findFirst()
        .map(option -> {
          @SuppressWarnings("unchecked")
          List<Object> permissions = (List<Object>) ((Map<String, Object>) option.value())
              .getOrDefault("value", ImmutableList.of());
          return permissions.stream().map(Object::toString).collect(Collectors.toSet());
        })
        .orElse(ImmutableSet.of());
  }

  /**
   * Attempt to find a Swagger validation from a proto option.
   *
   * @return the Swagger validation name and value, if the option represents a supported validation.
   */
  public static Optional<Pair<String, Object>> validation(OptionElement option) {
    if (!option.name().startsWith("squareup.validation.")) {
      return Optional.empty();
    }

    Function<OptionElement, Optional<Pair<String, Object>>> transformer =
        TRANSFORMERS.get(option.name());
    checkState(transformer != null, "%s is not a supported validation", option.name());

    return transformer.apply(option);
  }

  private static Optional<Pair<String, Object>> length(OptionElement option) {
    OptionElement actual = (OptionElement) option.value();

    if ("max".equals(actual.name())) {
      return Optional.of(Pair.of("maxLength", Integer.parseInt((String) actual.value())));
    } else if ("min".equals(actual.name())) {
      return Optional.of(Pair.of("maxLength", Integer.parseInt((String) actual.value())));
    } else {
      throw new IllegalStateException("Unsupported length validation " + actual.name());
    }
  }

  private static Optional<Pair<String, Object>> notEmpty(OptionElement option) {
    if (Boolean.parseBoolean((String) option.value())) {
      return Optional.of(Pair.of("minLength", 1));
    } else {
      return Optional.empty();
    }
  }

  private static Optional<Pair<String, Object>> range(OptionElement option) {
    OptionElement actual = (OptionElement) option.value();

    if ("max".equals(actual.name())) {
      return Optional.of(Pair.of("maximum", Integer.parseInt((String) actual.value())));
    } else if ("min".equals(actual.name())) {
      return Optional.of(Pair.of("minimum", Integer.parseInt((String) actual.value())));
    } else {
      throw new IllegalStateException("Unsupported range validation " + actual.name());
    }
  }

  private static Optional<Pair<String, Object>> matchesPattern(OptionElement option) {
    return Optional.of(Pair.of("pattern", option.value()));
  }

  private ProtoOptions() {
  }
}
