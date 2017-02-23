package com.squareup.apiparser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApiReleaseTypeTest {
  @Test
  public void testReleaseTypeInclusion() throws Exception {
    ApiReleaseType type = ApiReleaseType.PUBLIC;
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_PUBLIC));
    assertFalse(type.shouldInclude(ProtoOptions.RELEASE_STATUS_BETA));
    assertFalse(type.shouldInclude(ProtoOptions.RELEASE_STATUS_UPCOMING));
    assertFalse(type.shouldInclude(ProtoOptions.RELEASE_STATUS_INTERNAL));

    type = ApiReleaseType.BETA;
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_PUBLIC));
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_BETA));
    assertFalse(type.shouldInclude(ProtoOptions.RELEASE_STATUS_UPCOMING));
    assertFalse(type.shouldInclude(ProtoOptions.RELEASE_STATUS_INTERNAL));

    type = ApiReleaseType.UPCOMING;
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_PUBLIC));
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_BETA));
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_UPCOMING));
    assertFalse(type.shouldInclude(ProtoOptions.RELEASE_STATUS_INTERNAL));

    type = ApiReleaseType.ALL;
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_PUBLIC));
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_BETA));
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_UPCOMING));
    assertTrue(type.shouldInclude(ProtoOptions.RELEASE_STATUS_INTERNAL));
  }

  @Test
  public void testCatchNewEnumValues() throws Exception {
    Set<String> types = Arrays.stream(ApiReleaseType.values())
        .map(Enum::name)
        .collect(Collectors.toSet());
    Set<String> expected = new HashSet<String>(Arrays.asList("PUBLIC", "BETA", "UPCOMING", "ALL"));
    // If this fails then update test coverage to include the new values
    assertThat(types).isEqualTo(expected);
  }
}
