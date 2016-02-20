package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectDatatypeTest {
  @Test
  public void testToJson() throws Exception {
    MessageElement e = stubMessage("@desc a mock");
    ConnectDatatype datatype = new ConnectDatatype(e, "packageName", null);
    assertThat(datatype.toJson().getString("description"), equalTo("a mock"));
    assertThat(datatype.toJson().getString("type"), equalTo("object"));
    assertThat(datatype.toJson().getJSONObject("properties"), isA(JSONObject.class));
  }

  @Test
  public void testHasBodyParameters_NoFields() throws Exception {
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Name");
    when(e.documentation()).thenReturn("");

    ConnectDatatype dataType = new ConnectDatatype(e, "packageName", null);
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_OneField() throws Exception {
    final String value = "";
    MessageElement me = stubMessage(value);
    FieldElement fe = stubField(" //@pathparam\n");
    when(me.fields()).thenReturn(ImmutableList.of(fe));
    when(me.fields()).thenReturn(ImmutableList.of(fe));

    ConnectDatatype dataType = new ConnectDatatype(me, "packageName", null);
    dataType.populateFields(new ProtoIndex());
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_TwoFields() throws Exception {
    MessageElement me = stubMessage("");
    FieldElement fe1 = stubField(" //@pathparam\n");
    FieldElement fe2 = stubField(" //@required\n");
    when(me.fields()).thenReturn(ImmutableList.of(fe1, fe2));

    ConnectDatatype dataType = new ConnectDatatype(me, "packageName", null);
    dataType.populateFields(new ProtoIndex());
    assertTrue(dataType.hasBodyParameters());
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

