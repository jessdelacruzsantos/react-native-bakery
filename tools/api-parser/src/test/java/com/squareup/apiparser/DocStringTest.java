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
    ImmutableMap<String, String> actual = new DocString("").getAnnotations();
    assertThat(actual, equalTo(Collections.emptyMap()));
  }

  @Test
  public void testParseSingleline() throws Exception {
    String doc = "  //@pathparam\n";
    ImmutableMap<String, String> expected = ImmutableMap.of("pathparam", "");
    assertThat(new DocString(doc).getAnnotations(), equalTo(expected));
  }

  @Test
  public void testParseMultiline() throws Exception {
    String doc = "  /*--\n"
        + "    @foo Bar\nSome\n\nmore\ntext\n\n\n   "
        + "    @desc Description here.\n"
        + "  --*/";
    ImmutableMap<String, String> actual = new DocString(doc).getAnnotations();
    Map<String, String> expected = ImmutableMap.<String, String>builder()
        .put("foo", "Bar\nSome\n\nmore\ntext")
        .put("desc", "Description here.")
        .build();
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void testParseSingleLine() throws Exception {
    String doc = "  //@desc Description here. @foo Bar \n";
    ImmutableMap<String, String> actual = new DocString(doc).getAnnotations();
    Map<String, String> expected = ImmutableMap.of("desc", "Description here.", "foo", "Bar");
    assertThat(actual, equalTo(expected));
  }
}
