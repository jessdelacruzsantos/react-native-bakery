package com.squareup.apiparser;

/**
 * Visibility of an API element. Enum values are in order of increasing visibility.
 */
enum ReleaseStatus {
  DEPRECATED,
  EXCLUDED,
  INTERNAL,
  UPCOMING, // (TODO) (DF-157) Upcoming will be removed and replaced by ALPHA.
  ALPHA,
  BETA,
  PUBLIC;

  public boolean shouldInclude(ReleaseStatus releaseStatus) {
    // After introducing versioning, BETA and PUBLIC will both be shown on the
    // API reference site while BETA fields will have a `beta` tag on them.
    return releaseStatus.ordinal() >= this.ordinal() || releaseStatus == ReleaseStatus.BETA;
  }
}
