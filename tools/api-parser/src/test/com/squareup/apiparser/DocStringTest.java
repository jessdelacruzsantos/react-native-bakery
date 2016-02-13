package com.squareup.apiparser;

import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DocStringTest {
  @Test
  public void testParseNoComments() throws Exception {
    final String doc = "";
    final ImmutableMap<String, String> actual = DocString.parse(doc);
    final Map<String, String> expected = Collections.emptyMap();
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParsePathParam() throws Exception {
    final String doc = "  //@pathparam\n";
    final ImmutableMap<String, String> actual = DocString.parse(doc);
    final ImmutableMap<String, String> expected = ImmutableMap.of("//", "", "pathparam", "");
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParseMultiline() throws Exception {
    final String doc = "  /*--\n"
        + "    @entity Transaction\n"
        + "    @path /v2/locations\n"
        + "    @httpmethod POST\n"
        + "    @oauthpermissions PAYMENTS_WRITE\n"
        + "    @desc Description here.\n"
        + "  --*/";
    final ImmutableMap<String, String> actual = DocString.parse(doc);
    final Map<String, String> expected = ImmutableMap.<String, String>builder().put("", "")
        .put("entity", "Transaction")
        .put("path", "/v2/locations")
        .put("httpmethod", "POST")
        .put( "oauthpermissions", "PAYMENTS_WRITE")
        .put("desc", "Description here.")
        .build();
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParseSingleLine() throws Exception {
    final String doc = "  //@entity Transaction @path /v2/locations \n";
    final ImmutableMap<String, String> actual = DocString.parse(doc);
    final Map<String, String> expected = ImmutableMap.of("//", "", "entity", "Transaction", "path", "/v2/locations");
    assertThat(actual, equalTo(expected));
  }
}
