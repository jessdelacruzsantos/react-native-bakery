package com.squareup.apiparser;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProtosTest {
  @Test
  public void cleanName() {
    assertThat(Protos.cleanName("a.b.c.d"), equalTo("abcd"));

    // Top-level groupings
    assertThat(Protos.cleanName("squareup.connect.v2.actions.Thing"), equalTo("Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.actions.Thing.Is.Nested"), equalTo("ThingIsNested"));
    assertThat(Protos.cleanName("squareup.connect.v2.common.Thing"), equalTo("Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.resources.Thing"), equalTo("Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.resources_internal.Thing"), equalTo("Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.service.Thing"), equalTo("Thing"));

    // Product groupings
    assertThat(Protos.cleanName("squareup.connect.v2.someproduct.service.Thing"), equalTo("Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.someproduct.service.Thing.Is.Nested"), equalTo("ThingIsNested"));
    assertThat(Protos.cleanName("squareup.connect.v2.someproduct.service.Thing.action.Is.Nested"), equalTo("ThingactionIsNested"));
  }
}
