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
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Name");
    when(e.documentation()).thenReturn("@desc a mock");
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
    MessageElement me = mock(MessageElement.class);
    when(me.name()).thenReturn("Name");
    when(me.documentation()).thenReturn("");
    when(me.oneOfs()).thenReturn(ImmutableList.of());
    FieldElement fe = mock(FieldElement.class);
    when(fe.documentation()).thenReturn(" //@pathparam\n");
    when(fe.type()).thenReturn("string");
    when(me.fields()).thenReturn(ImmutableList.of(fe));
    when(me.fields()).thenReturn(ImmutableList.of(fe));

    ConnectDatatype dataType = new ConnectDatatype(me, "packageName", null);
    dataType.populateFields(new ProtoIndex());
    assertFalse(dataType.hasBodyParameters());
  }

  @Test
  public void testHasBodyParameters_TwoFields() throws Exception {
    MessageElement me = mock(MessageElement.class);
    when(me.name()).thenReturn("Name");
    when(me.documentation()).thenReturn("");
    when(me.oneOfs()).thenReturn(ImmutableList.of());
    FieldElement fe1 = mock(FieldElement.class);
    FieldElement fe2 = mock(FieldElement.class);
    when(fe1.documentation()).thenReturn(" //@pathparam\n");
    when(fe1.type()).thenReturn("string");
    when(fe2.documentation()).thenReturn(" //@required\n");
    when(fe2.type()).thenReturn("string");
    when(me.fields()).thenReturn(ImmutableList.of(fe1, fe2));

    ConnectDatatype dataType = new ConnectDatatype(me, "packageName", null);
    dataType.populateFields(new ProtoIndex());
    assertTrue(dataType.hasBodyParameters());
  }
}

