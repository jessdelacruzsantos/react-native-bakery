package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.squareup.wire.schema.Location;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.OneOfElement;
import com.squareup.wire.schema.internal.parser.OptionElement;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectDatatypeTest {
  private final ExampleResolver resolver = new ExampleResolver(ImmutableList.of());

  @Test
  public void testToJson() throws Exception {
    MessageElement e = stubMessage("@desc a mock");
    when(e.options()).thenReturn(ImmutableList.of());
    ConnectDatatype datatype = new ConnectDatatype(e, "packageName", Optional.empty(), resolver);
    assertThat(datatype.toJson().get("description").getAsString(), equalTo("a mock"));
    assertThat(datatype.toJson().get("type").getAsString(), equalTo("object"));
    assertThat(datatype.toJson().get("properties"), isA(JsonElement.class));
  }

  @Test
  public void testToJson_requiredField() throws Exception {
    final MessageElement e = stubMessage("@desc a mock");

    final OptionElement o = mock(OptionElement.class);
    when(o.name()).thenReturn("squareup.validation.required");
    when(o.value()).thenReturn("true");

    final FieldElement fe1 = mock(FieldElement.class);
    when(fe1.name()).thenReturn("FakeField");
    when(fe1.documentation()).thenReturn("i should be required");
    when(fe1.type()).thenReturn("string");
    when(fe1.options()).thenReturn(ImmutableList.of(o));
    when(e.fields()).thenReturn(ImmutableList.of(fe1));
    when(e.options()).thenReturn(ImmutableList.of());

    final ConnectDatatype datatype = new ConnectDatatype(e, "packageName", Optional.empty(), resolver);
    final ProtoIndex index = mock(ProtoIndex.class);
    when(index.getEnumType("string")).thenReturn(Optional.empty());
    datatype.populateFields(index);
    assertThat(datatype.toJson().get("description").getAsString(), equalTo("a mock"));
    assertThat(datatype.toJson().get("type").getAsString(), equalTo("object"));
    assertThat(datatype.toJson().get("properties"), isA(JsonElement.class));
    final JsonArray required = datatype.toJson().get("required").getAsJsonArray();
    assertThat(required.get(0).getAsString(), equalTo("FakeField"));
  }

  @Test
  public void testHasBodyParameters_NoFields() throws Exception {
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Name");
    when(e.documentation()).thenReturn("");
    when(e.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType = new ConnectDatatype(e, "packageName", Optional.empty(), resolver);
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_OneField() throws Exception {
    final String value = "";
    MessageElement me = stubMessage(value);
    FieldElement fe = stubField(" //@pathparam\n");
    when(me.fields()).thenReturn(ImmutableList.of(fe));
    when(me.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType = new ConnectDatatype(me, "packageName", Optional.empty(), resolver);
    dataType.populateFields(new ProtoIndex(resolver));
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_TwoFields() throws Exception {
    MessageElement me = stubMessage("");
    FieldElement fe1 = stubField(" //@pathparam\n");
    FieldElement fe2 = stubField(" //@required\n");
    when(me.fields()).thenReturn(ImmutableList.of(fe1, fe2));
    when(me.options()).thenReturn(ImmutableList.of());

    ConnectDatatype dataType = new ConnectDatatype(me, "packageName", Optional.empty(), resolver);
    dataType.populateFields(new ProtoIndex(resolver));
    assertTrue(dataType.hasBodyParameters());
  }

  @Test(expected = IllegalUseOfOneOfException.class)
  public void testOneOfInProtosFailsGeneration() throws Exception {
    MessageElement m = stubMessage("I have a oneOf");
    when(m.fields()).thenReturn(ImmutableList.of());
    when(m.options()).thenReturn(ImmutableList.of());
    when(m.name()).thenReturn("Name");
    when(m.location()).thenReturn(mock(Location.class));
    final OneOfElement oe = mock(OneOfElement.class);
    when(m.oneOfs()).thenReturn(ImmutableList.of(oe));

    final ConnectDatatype datatype = new ConnectDatatype(m, "packageName", Optional.empty(), resolver);
    datatype.populateFields(new ProtoIndex(resolver));
  }

  private FieldElement stubField(String documentation) {
    FieldElement fe1 = mock(FieldElement.class);
    when(fe1.documentation()).thenReturn(documentation);
    when(fe1.type()).thenReturn("string");
    when(fe1.options()).thenReturn(ImmutableList.of());
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

