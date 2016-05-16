package com.squareup.apiparser;

import com.google.common.collect.ImmutableSet;
import com.squareup.wire.schema.internal.parser.OptionElement;
import java.util.Collection;

enum ApiReleaseType {
  PUBLIC(ProtoOptions.RELEASE_STATUS_PUBLIC),
  BETA(ProtoOptions.RELEASE_STATUS_PUBLIC, ProtoOptions.RELEASE_STATUS_BETA),
  UPCOMING(ProtoOptions.RELEASE_STATUS_PUBLIC, ProtoOptions.RELEASE_STATUS_BETA,
      ProtoOptions.RELEASE_STATUS_UPCOMING),
  /** Include API definitions in all release statuses. */
  ALL;

  /** Allowed release status set. Allow all if it's empty. */
  private final ImmutableSet<String> allowedStatusSet;

  private ApiReleaseType(String... releaseStatuses) {
    this.allowedStatusSet = ImmutableSet.copyOf(releaseStatuses);
  }

  public boolean shouldInclude(String releaseStatus) {
    return allowedStatusSet.isEmpty() || allowedStatusSet.contains(releaseStatus);
  }

  public boolean shouldInclude(Collection<OptionElement> options, String optionName) {
    return shouldInclude(ProtoOptions.getReleaseStatus(options, optionName));
  }
}
