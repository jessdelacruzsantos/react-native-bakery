package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.Location;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import com.squareup.wire.schema.internal.parser.OptionElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectDatatypeTest {
  private final ExampleResolver resolver = new ExampleResolver(ImmutableList.of());
  private final String sqVersion = "2018-05-01";

  @Test
  public void testToJson() {
    MessageElement e = stubMessage("@desc a mock");
    when(e.options()).thenReturn(ImmutableList.of());
    ConnectDatatype datatype =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", e, "packageName", Optional.empty(), resolver, false);
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("description").getAsString(),
        equalTo("a mock"));
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("type").getAsString(), equalTo("object"));
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("properties"), isA(JsonElement.class));
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("x-release-status").getAsString(), equalTo("INTERNAL"));
  }

  @Test
  public void testToJson_requiredField() {
    MessageElement e = stubMessage("@desc a mock");

    OptionElement o = OptionElement.create("squareup.validation.required", OptionElement.Kind.STRING, "true");

    final FieldElement fe1 = mock(FieldElement.class);
    when(fe1.name()).thenReturn("FakeField");
    when(fe1.documentation()).thenReturn("i should be required");
    when(fe1.type()).thenReturn("string");
    when(fe1.options()).thenReturn(ImmutableList.of(o));
    when(e.fields()).thenReturn(ImmutableList.of(fe1));
    when(e.options()).thenReturn(ImmutableList.of());

    final ConnectDatatype datatype =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", e, "packageName", Optional.empty(), resolver, false);
    final ProtoIndex index = mock(ProtoIndex.class);
    when(index.getEnumType("string")).thenReturn(Optional.empty());
    datatype.populateFields(index);
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("description").getAsString(),
        equalTo("a mock"));
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("type").getAsString(), equalTo("object"));
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("properties"), isA(JsonElement.class));
    JsonArray required = datatype.toJson(ReleaseStatus.INTERNAL).get("required").getAsJsonArray();
    assertThat(required.get(0).getAsString(), equalTo("FakeField"));
  }

  @Test
  public void testPopulateField_withNestedObject() {
    MessageElement e = stubMessage("@desc a mock");

    OptionElement o = OptionElement.create("squareup.validation.required", OptionElement.Kind.STRING, "true");

    final FieldElement fe1 = mock(FieldElement.class);
    when(fe1.name()).thenReturn("fake");
    when(fe1.documentation()).thenReturn("i should be required");
    String fakeFieldType = "squareup.connect.v2.fakes.service.FakeRequest.Fake";
    when(fe1.type()).thenReturn(fakeFieldType);
    when(fe1.options()).thenReturn(ImmutableList.of(o));
    when(e.fields()).thenReturn(ImmutableList.of(fe1));
    when(e.options()).thenReturn(ImmutableList.of());

    final TypeElement fakeTypeElement = mock(MessageElement.class);
    when(fakeTypeElement.documentation()).thenReturn("doc");
    when(fakeTypeElement.options()).thenReturn(ImmutableList.of());
    when(fakeTypeElement.name()).thenReturn("Fake");
    final TypeElement fakeParentTypeElement = mock(MessageElement.class);
    when(fakeParentTypeElement.documentation()).thenReturn("doc");
    when(fakeParentTypeElement.options()).thenReturn(ImmutableList.of());
    when(fakeParentTypeElement.name()).thenReturn("FakeRequest");

    final ConnectDatatype fakeDataType = new ConnectDatatype(ReleaseStatus.INTERNAL, "", fakeTypeElement,
            "squareup.connect.v2.fakes.service",
        Optional.of(new ConnectDatatype(ReleaseStatus.INTERNAL, "", fakeParentTypeElement,
            "squareup.connect.v2.fakes.service", Optional.empty(), resolver, false)),
            resolver, false);

    final ConnectDatatype datatype =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", e, "packageName", Optional.empty(), resolver, false);
    final ProtoIndex index = mock(ProtoIndex.class);
    when(index.getEnumType(fakeFieldType)).thenReturn(Optional.empty());
    when(index.getDatatypes()).thenReturn(ImmutableMap.of("FakeRequestFake", fakeDataType));

    datatype.populateFields(index);
    assertThat(datatype.toJson(ReleaseStatus.INTERNAL).get("properties"), isA(JsonElement.class));
    String fakeType = datatype.toJson(ReleaseStatus.INTERNAL)
        .get("properties")
        .getAsJsonObject()
        .get("fake")
        .getAsJsonObject()
        .get("$ref")
        .getAsString();

    assertThat(fakeType, equalTo("#/definitions/FakeRequestFake"));
  }

  @Test
  public void testToJson_sdkSampleCode() {
    OptionElement o = OptionElement.create("common.sdk_sample_directory", OptionElement.Kind.STRING, "/samples/Endpoint");

    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Message");
    when(e.documentation()).thenReturn("");
    when(e.options()).thenReturn(ImmutableList.of(o));

    ConnectDatatype datatype =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", e, "packageName", Optional.empty(), resolver, false);
    JsonObject sdkSamples =
        datatype.toJson(ReleaseStatus.INTERNAL).get("x-sq-sdk-sample-code").getAsJsonObject();
    assertThat(sdkSamples.getAsJsonPrimitive("ruby").getAsString(),
        equalTo("/samples/Endpoint/Message.ruby"));
  }

  @Test
  public void testToJson_noSdkSampleCode() {
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Message");
    when(e.documentation()).thenReturn("");
    when(e.options()).thenReturn(ImmutableList.of());

    ConnectDatatype datatype =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", e, "packageName", Optional.empty(), resolver, false);
    assertNull(datatype.toJson(ReleaseStatus.INTERNAL).get("x-sq-sdk-sample-code"));
  }

  @Test
  public void testHasBodyParameters_NoFields() {
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Name");
    when(e.documentation()).thenReturn("");
    when(e.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", e, "packageName", Optional.empty(), resolver, false);
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_OneField() {
    MessageElement me = stubMessage("");
    FieldElement fe = stubField(" //@desc a random path element field\n", true);
    when(me.fields()).thenReturn(ImmutableList.of(fe));
    when(me.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", me, "packageName", Optional.empty(), resolver, false);
    dataType.populateFields(new ProtoIndex(resolver, false, sqVersion));
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_TwoFields() {
    MessageElement me = stubMessage("");
    FieldElement fe1 = stubField(" //@desc a ramdom path param\n", true);
    FieldElement fe2 = stubField(" //@required\n", false);
    when(me.fields()).thenReturn(ImmutableList.of(fe1, fe2));
    when(me.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", me, "packageName", Optional.empty(), resolver, false);
    dataType.populateFields(new ProtoIndex(resolver, false, sqVersion));
    assertTrue(dataType.hasBodyParameters());
  }

  @Test(expected = IllegalUseOfOneOfException.class)
  public void testOneOfInProtosFailsGeneration() {
    MessageElement m = stubMessage("I have a oneOf");
    when(m.fields()).thenReturn(ImmutableList.of());
    when(m.options()).thenReturn(ImmutableList.of());
    when(m.name()).thenReturn("Name");
    when(m.location()).thenReturn(mock(Location.class));
    OneOfElement oe = mock(OneOfElement.class);
    when(m.oneOfs()).thenReturn(ImmutableList.of(oe));

    final ConnectDatatype datatype =
        new ConnectDatatype(ReleaseStatus.INTERNAL, "", m, "packageName", Optional.empty(), resolver, false);
    datatype.populateFields(new ProtoIndex(resolver, false, sqVersion));
  }

  private FieldElement stubField(String documentation, boolean isPathParam) {
    FieldElement fe1 = mock(FieldElement.class);
    when(fe1.documentation()).thenReturn(documentation);
    when(fe1.type()).thenReturn("string");
    if (isPathParam) {
      OptionElement opt = OptionElement.create("common.path_param", OptionElement.Kind.STRING, "true");
      when(fe1.options()).thenReturn(ImmutableList.of(opt));
    } else {
      when(fe1.options()).thenReturn(ImmutableList.of());
    }
    return fe1;
  }

  private MessageElement stubMessage(String value) {
    MessageElement me = mock(MessageElement.class);
    when(me.name()).thenReturn("Name");
    when(me.documentation()).thenReturn(value);
    when(me.oneOfs()).thenReturn(ImmutableList.of());
    return me;
  }
}
