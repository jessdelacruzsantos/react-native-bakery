package com.squareup.apiparser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReleaseStatusTest {

  @Test public void shouldInclude() {
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.BETA)).isTrue();
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.UPCOMING)).isFalse();
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.INTERNAL)).isFalse();
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.EXCLUDED)).isFalse();

    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.BETA)).isTrue();
    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.UPCOMING)).isFalse();
    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.INTERNAL)).isFalse();
    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.EXCLUDED)).isFalse();

    assertThat(ReleaseStatus.UPCOMING.shouldInclude(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(ReleaseStatus.UPCOMING.shouldInclude(ReleaseStatus.BETA)).isTrue();
    assertThat(ReleaseStatus.UPCOMING.shouldInclude(ReleaseStatus.UPCOMING)).isTrue();
    assertThat(ReleaseStatus.UPCOMING.shouldInclude(ReleaseStatus.INTERNAL)).isFalse();
    assertThat(ReleaseStatus.UPCOMING.shouldInclude(ReleaseStatus.EXCLUDED)).isFalse();

    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.BETA)).isTrue();
    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.UPCOMING)).isTrue();
    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.INTERNAL)).isTrue();
    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.EXCLUDED)).isFalse();
  }
}
