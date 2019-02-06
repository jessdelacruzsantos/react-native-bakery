package com.squareup.apiparser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class GroupTest {

  @Test public void shouldIncludeStatus() {

    Group group = new Group();
    group.status = ReleaseStatus.PUBLIC;
    assertThat(group.shouldIncludeStatus(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.BETA)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.DEPRECATED)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.ALPHA)).isFalse();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.INTERNAL)).isFalse();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.EXCLUDED)).isFalse();

    group.status = ReleaseStatus.BETA;
    assertThat(group.shouldIncludeStatus(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.BETA)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.DEPRECATED)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.ALPHA)).isFalse();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.INTERNAL)).isFalse();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.EXCLUDED)).isFalse();

    group.status = ReleaseStatus.ALPHA;
    assertThat(group.shouldIncludeStatus(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.BETA)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.DEPRECATED)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.ALPHA)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.INTERNAL)).isFalse();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.EXCLUDED)).isFalse();

    group.status = ReleaseStatus.INTERNAL;
    assertThat(group.shouldIncludeStatus(ReleaseStatus.PUBLIC)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.BETA)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.DEPRECATED)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.ALPHA)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.INTERNAL)).isTrue();
    assertThat(group.shouldIncludeStatus(ReleaseStatus.EXCLUDED)).isFalse();
  }

  @Test public void shouldIncludeAlphaNamespace() {
    Group groupA = new Group();
    Group groupB = new Group();
    groupA.status = ReleaseStatus.PUBLIC;

    // None ALPHA always returns true
    groupB.status = ReleaseStatus.BETA;
    assertThat(groupA.shouldIncludeAlphaNamespace(groupB)).isTrue();

    // None ALPHA returns true if no namespace is defined
    groupB.status = ReleaseStatus.ALPHA;
    assertThat(groupA.shouldIncludeAlphaNamespace(groupB)).isTrue();

    // ALPHA returns true if no namespace is defined
    groupB.namespace = "testing";
    assertThat(groupA.shouldIncludeAlphaNamespace(groupB)).isTrue();

    // ALPHA returns true if namespace matches
    groupA.namespace = "testing";
    assertThat(groupA.shouldIncludeAlphaNamespace(groupB)).isTrue();

    // ALPHA returns true if namespace does not match
    groupA.namespace = "easy";
    assertThat(groupA.shouldIncludeAlphaNamespace(groupB)).isFalse();
  }
}
