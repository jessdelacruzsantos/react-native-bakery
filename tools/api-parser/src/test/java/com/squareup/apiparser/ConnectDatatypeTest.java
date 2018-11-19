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

  @Test
  public void testToJson() {
    MessageElement e = stubMessage("@desc a mock");
    when(e.options()).thenReturn(ImmutableList.of());

    Group group = new Group(ReleaseStatus.INTERNAL, "");
    ConnectDatatype datatype =
        new ConnectDatatype(group, e, "packageName", Optional.empty(), resolver, false);
    JsonObject json = datatype.toJson(group);
    assertThat(json.get("description").getAsString(),
        equalTo("a mock"));
    assertThat(json.get("type").getAsString(), equalTo("object"));
    assertThat(json.get("properties"), isA(JsonElement.class));
    assertThat(json.get("x-release-status").getAsString(), equalTo("INTERNAL"));
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

    Group group = new Group(ReleaseStatus.INTERNAL, "");
    ConnectDatatype datatype =
        new ConnectDatatype(group, e, "packageName", Optional.empty(), resolver, false);
    final ProtoIndexer index = mock(ProtoIndexer.class);
    when(index.getEnumType("string")).thenReturn(Optional.empty());
    datatype.populateFields(index);
    JsonObject json = datatype.toJson(group);
    assertThat(json.get("description").getAsString(),
        equalTo("a mock"));
    assertThat(json.get("type").getAsString(), equalTo("object"));
    assertThat(json.get("properties"), isA(JsonElement.class));
    JsonArray required = json.get("required").getAsJsonArray();
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

    Group group = new Group();
    final ConnectDatatype fakeDataType = new ConnectDatatype(group, fakeTypeElement,
            "squareup.connect.v2.fakes.service",
        Optional.of(new ConnectDatatype(group, fakeParentTypeElement,
            "squareup.connect.v2.fakes.service", Optional.empty(), resolver, false)),
            resolver, false);

    final ConnectDatatype datatype =
        new ConnectDatatype(group, e, "packageName", Optional.empty(), resolver, false);
    final ProtoIndexer index = mock(ProtoIndexer.class);
    when(index.getEnumType(fakeFieldType)).thenReturn(Optional.empty());
    when(index.getDatatypes()).thenReturn(ImmutableMap.of("FakeRequestFake", fakeDataType));

    datatype.populateFields(index);
    assertThat(datatype.toJson(group).get("properties"), isA(JsonElement.class));
    String fakeType = datatype.toJson(group)
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
        new ConnectDatatype(new Group(), e, "packageName", Optional.empty(), resolver, false);
    JsonObject sdkSamples =
        datatype.toJson(datatype.getGroup()).get("x-sq-sdk-sample-code").getAsJsonObject();
    assertThat(sdkSamples.getAsJsonPrimitive("ruby").getAsString(),
        equalTo("/samples/Endpoint/Message.ruby"));
  }

  @Test
  public void testToJson_noSdkSampleCode() {
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Message");
    when(e.documentation()).thenReturn("");
    when(e.options()).thenReturn(ImmutableList.of());
    when(e.fields()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType =
        new ConnectDatatype(new Group(), e, "packageName", Optional.empty(), resolver, true);
    dataType.populateFields(new ProtoIndexer(getConfig()));
    assertNull(dataType.toJson(dataType.getGroup()).get("x-sq-sdk-sample-code"));
  }

  @Test
  public void testHasBodyParameters_NoFields() {
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Name");
    when(e.documentation()).thenReturn("");
    when(e.options()).thenReturn(ImmutableList.of());
    when(e.fields()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType =
        new ConnectDatatype(new Group(), e, "packageName", Optional.empty(), resolver, true);
    dataType.populateFields(new ProtoIndexer(getConfig()));
    assertFalse(dataType.hasBodyParameters());
  }

  private ConnectDatatype createDatatype(MessageElement me){
    ConnectDatatype dataType =
        new ConnectDatatype(new Group(), me, "packageName", Optional.empty(), resolver, false);
    dataType.populateFields(new ProtoIndexer(getConfig()));
    return dataType;
  }

  @Test
  public void testHasBodyParameters_OneField() {
    MessageElement me = stubMessage("");
    FieldElement fe = stubField(" //@desc a random path element field\n", true);
    when(me.fields()).thenReturn(ImmutableList.of(fe));
    when(me.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType = createDatatype(me);
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_TwoFields() {
    MessageElement me = stubMessage("");
    FieldElement fe1 = stubField(" //@desc a ramdom path param\n", true);
    FieldElement fe2 = stubField(" //@required\n", false);
    when(me.fields()).thenReturn(ImmutableList.of(fe1, fe2));
    when(me.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType = createDatatype(me);
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

    ConnectDatatype dataType = createDatatype(m);
  }

  private Configuration getConfig(){
      Configuration config = new Configuration();
      config.sqVersion = "2018-05-01";
      config.ignoreOneofs = false;
      return config;
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
