package com.squareup.apiparser;

/**
 * Visibility of an API element. Enum values are in order of increasing visibility.
 */
enum ReleaseStatus {
  EXCLUDED,
  INTERNAL,
  UPCOMING,
  BETA,
  PUBLIC;

  public boolean shouldInclude(ReleaseStatus releaseStatus) {
    return releaseStatus.ordinal() >= this.ordinal();
  }
}
