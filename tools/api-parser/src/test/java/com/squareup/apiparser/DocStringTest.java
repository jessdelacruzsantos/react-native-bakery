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
    final ImmutableMap<String, String> actual = new DocString(doc).getAnnotations();
    final Map<String, String> expected = Collections.emptyMap();
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParsePathParam() throws Exception {
    final String doc = "  //@pathparam\n";
    final ImmutableMap<String, String> actual = new DocString(doc).getAnnotations();
    final ImmutableMap<String, String> expected = ImmutableMap.of("pathparam", "");
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParseMultiline() throws Exception {
    final String doc = "  /*--\n"
        + "    @foo Bar\n"
        + "    @desc Description here.\n"
        + "  --*/";
    final ImmutableMap<String, String> actual = new DocString(doc).getAnnotations();
    final Map<String, String> expected = ImmutableMap.<String, String>builder()
        .put("foo", "Bar")
        .put("desc", "Description here.")
        .build();
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParseSingleLine() throws Exception {
    final String doc = "  //@desc Description here. @foo Bar \n";
    final ImmutableMap<String, String> actual = new DocString(doc).getAnnotations();
    final Map<String, String> expected = ImmutableMap.of("desc", "Description here.", "foo", "Bar");
    assertThat(actual, equalTo(expected));
  }
}
