package com.squareup.apiparser;

import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ConnectFieldTest {

  @Test
  public void testToJson() throws Exception {
    ConnectField field = new ConnectField("name", "object", 3, "");
    assertThat(field.toJson().getString("name"), equalTo("name"));
  }
}
