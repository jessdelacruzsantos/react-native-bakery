package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DocStringTest {
  @Test
  public void testParseNoComments() throws Exception {
    final String doc = "";
    final List<String> actual = DocString.parse(doc);
    final List<String> expected = Collections.emptyList();
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParsePathParam() throws Exception {
    final String doc = "  //@pathparam";
    final List<String> actual = DocString.parse(doc);
    final List<String> expected = ImmutableList.of("//", "pathparam");
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
    final List<String> actual = DocString.parse(doc);
    final ImmutableList<String> expected = ImmutableList.of("", "entity Transaction", "path /v2/locations", "httpmethod POST", "oauthpermissions PAYMENTS_WRITE", "desc Description here.");
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParseSingleLine() throws Exception {
    final String doc = "  //@entity Transaction @path /v2/locations \n";
    final List<String> actual = DocString.parse(doc);
    final List<String> expected = ImmutableList.of("//", "entity Transaction", "path /v2/locations");
    assertThat(actual, equalTo(expected));
  }
}
