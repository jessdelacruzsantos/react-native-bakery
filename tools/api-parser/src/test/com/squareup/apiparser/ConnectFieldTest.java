package com.squareup.apiparser;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ConnectFieldTest {

  @Test
  public void testToJson() throws Exception {
    ConnectField field = new ConnectField("name", "object", 3, "");
    assertThat(field.toJson().getString("name"), equalTo("name"));
  }
}
