package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

enum ApiReleaseType {
  PUBLIC(ProtoOptions.RELEASE_STATUS_PUBLIC),
  BETA(ProtoOptions.RELEASE_STATUS_PUBLIC, ProtoOptions.RELEASE_STATUS_BETA),
  UPCOMING(ProtoOptions.RELEASE_STATUS_PUBLIC, ProtoOptions.RELEASE_STATUS_BETA,
      ProtoOptions.RELEASE_STATUS_UPCOMING),
  /** Include API definitions in all release statuses. */
  ALL;

  /** Allowed release status set. Allow all if it's empty. */
  private final ImmutableSet<String> allowedStatusSet;

  public static ApiReleaseType from(String status) {
    return ImmutableList.of(PUBLIC, BETA, UPCOMING, ALL).stream()
        .filter(apiReleaseType -> apiReleaseType.shouldInclude(status))
        .findFirst().orElse(ALL);
  }

  ApiReleaseType(String... releaseStatuses) {
    this.allowedStatusSet = ImmutableSet.copyOf(releaseStatuses);
  }

  public boolean shouldInclude(String releaseStatus) {
    return allowedStatusSet.isEmpty() || allowedStatusSet.contains(releaseStatus);
  }

  public boolean shouldInclude(ApiReleaseType releaseStatus) {
    return allowedStatusSet.isEmpty() || allowedStatusSet.contains(releaseStatus.toString());
  }
}
