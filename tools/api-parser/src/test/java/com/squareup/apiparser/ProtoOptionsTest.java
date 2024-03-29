package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.OptionElement;
import java.util.List;
import java.util.Optional;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ProtoOptionsTest {

  //TODO (da3mon) - add tests for the option validation parsing
  @Test @Ignore
  public void testValidations() {
  }

  @Test @Ignore
  public void testIsRequired() {
  }

  @Test @Ignore
  public void testValidation() {
  }

  @Test
  public void testGetBooleanValueOrDefault() {
    List<OptionElement> options = ImmutableList.of(
        OptionElement.create("common.boolean", OptionElement.Kind.BOOLEAN, "true"),
        OptionElement.create("common.string", OptionElement.Kind.STRING, "string"),
        OptionElement.create("namespace.common.another.boolean", OptionElement.Kind.BOOLEAN, "true")
    );
    assertThat(ProtoOptions.getBooleanValueOrDefault(options, "common.boolean", false),
        equalTo(true));
    assertThat(ProtoOptions.getBooleanValueOrDefault(options, "non.existent", false),
        equalTo(false));
    assertThat(ProtoOptions.getBooleanValueOrDefault(options, "common.another.boolean", false),
        equalTo(true));
  }

  @Test
  public void testGetStringValue() {
    List<OptionElement> options = ImmutableList.of(
        OptionElement.create("common.boolean", OptionElement.Kind.BOOLEAN, "true"),
        OptionElement.create("common.string", OptionElement.Kind.STRING, "string"),
        OptionElement.create("namespace.common.another.string", OptionElement.Kind.STRING,
            "another.string")
    );
    assertThat(ProtoOptions.getStringValue(options, "common.string"),
        equalTo(Optional.of("string")));
    assertThat(ProtoOptions.getStringValue(options, "non.existent"),
        equalTo(Optional.empty()));
    assertThat(ProtoOptions.getStringValue(options, "common.another.string"),
        equalTo(Optional.of("another.string")));
  }

  @Test
  public void testGetReleaseStatus() {
    List<OptionElement> options = ImmutableList.of(
        OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"),
        OptionElement.create("common.message_status", OptionElement.Kind.STRING, "ALPHA")
    );
    // default status is INTERNAL
    assertThat(ProtoOptions.getReleaseStatus(options, "common.method_status"), equalTo(ReleaseStatus.PUBLIC));
    assertThat(ProtoOptions.getReleaseStatus(options, "common.file_status"), equalTo(ReleaseStatus.INTERNAL));
    assertThat(ProtoOptions.getReleaseStatus(options, "common.message_status"), equalTo(ReleaseStatus.ALPHA));
  }

  @Test
  public void testGetIntegerValue() {
    List<OptionElement> options = ImmutableList.of(
        OptionElement.create("common.integer", OptionElement.Kind.NUMBER, "1"),
        OptionElement.create("common.string", OptionElement.Kind.STRING, "string"),
        OptionElement.create("namespace.common.another.integer", OptionElement.Kind.NUMBER,
            "2")
    );
    assertThat(ProtoOptions.getIntegerValue(options, "common.integer"),
        equalTo(Optional.of(1)));
    assertThat(ProtoOptions.getIntegerValue(options, "non.existent"),
        equalTo(Optional.empty()));
    assertThat(ProtoOptions.getIntegerValue(options, "common.another.integer"),
        equalTo(Optional.of(2)));
  }
}
