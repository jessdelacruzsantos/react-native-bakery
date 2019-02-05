package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.FieldElement;
import com.squareup.wire.schema.Field;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectFieldTest {
  @Test
  public void testMapFields() throws Exception {
    FieldElement element = mock(FieldElement.class);
    when(element.name()).thenReturn("Order");
    when(element.label()).thenReturn(Field.Label.OPTIONAL);
    when(element.documentation()).thenReturn("");
    when(element.options()).thenReturn(ImmutableList.of());

    // When field is a map field
    String mapType = "map<string, squareup.connect.v2.actions.CreateCustomerRequest>";
    ConnectField field1 = new ConnectField(element, new Group(), mapType, Optional.empty(), "");
    assertThat(field1.getType(), equalTo("CreateCustomerRequest"));
    assertThat(field1.isMap(), equalTo(true));

    // When field is not a map field
    String noneMapType = "squareup.connect.v2.actions.CreateCustomerResponse";
    ConnectField field2 = new ConnectField(element, new Group(), noneMapType, Optional.empty(), "");
    assertThat(field2.getType(), equalTo("CreateCustomerResponse"));
    assertThat(field2.isMap(), equalTo(false));
  }
}
