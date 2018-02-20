package com.squareup.apiparser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReleaseStatusTest {

  @Test public void shouldInclude() {
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.BETA)).isFalse();
    assertThat(ReleaseStatus.PUBLIC.shouldInclude(ReleaseStatus.INTERNAL)).isFalse();

    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.BETA)).isTrue();
    assertThat(ReleaseStatus.BETA.shouldInclude(ReleaseStatus.INTERNAL)).isFalse();

    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.BETA)).isTrue();
    assertThat(ReleaseStatus.INTERNAL.shouldInclude(ReleaseStatus.INTERNAL)).isTrue();
  }
}
