package com.squareup.apiparser;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProtosTest {
  @Test
  public void cleanName() {
    assertThat(Protos.cleanName("a.b.c.d"), equalTo("abcd"));

    // Top-level groupings
    assertThat(Protos.cleanName("squareup.connect.v2.actions.Thing"), equalTo("squareupconnectv2Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.actions.Thing.Is.Nested"), equalTo("squareupconnectv2ThingIsNested"));
    assertThat(Protos.cleanName("squareup.connect.v2.common.Thing"), equalTo("squareupconnectv2Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.resources.Thing"), equalTo("squareupconnectv2Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.resources_internal.Thing"), equalTo("squareupconnectv2Thing"));
    assertThat(Protos.cleanName("squareup.connect.v2.service.Thing"), equalTo("squareupconnectv2serviceThing"));

    // Product groupings
    assertThat(Protos.cleanName("squareup.connect.v2.someproduct.service.Thing"), equalTo("squareupconnectv2someproductserviceThing"));
    assertThat(Protos.cleanName("squareup.connect.v2.someproduct.service.Thing.Is.Nested"), equalTo("squareupconnectv2someproductserviceThingIsNested"));
    assertThat(Protos.cleanName("squareup.connect.v2.someproduct.service.Thing.action.Is.Nested"), equalTo("squareupconnectv2someproductserviceThingactionIsNested"));
  }
}
