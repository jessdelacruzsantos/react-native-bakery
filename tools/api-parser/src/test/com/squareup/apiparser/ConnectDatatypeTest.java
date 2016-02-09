package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.MessageElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectDatatypeTest {
  @Test
  public void testToJson() throws Exception {
    MessageElement e = mock(MessageElement.class);
    when(e.name()).thenReturn("Name");
    ConnectDatatype datatype = new ConnectDatatype(e, "packageName", null);

    assertThat(datatype.toJson().getString("string"), equalTo("Name"));
  }
}

