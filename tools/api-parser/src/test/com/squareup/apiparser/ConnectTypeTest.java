package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectTypeTest {
  @Test
  public void testGenerateName() throws Exception {
    final TypeElement tender = mock(TypeElement.class);
    when(tender.name()).thenReturn("Tender");
    when(tender.documentation()).thenReturn("");

    final EnumConstantElement enum1 = mock(EnumConstantElement.class);
    when(enum1.name()).thenReturn("SUCCESS");
    when(enum1.tag()).thenReturn(1);
    when(enum1.documentation()).thenReturn("");

    final EnumElement status = mock(EnumElement.class);
    when(status.name()).thenReturn("Status");
    when(status.documentation()).thenReturn("");
    when(status.constants()).thenReturn(ImmutableList.of(enum1));

    final TypeElement cardDetails = mock(TypeElement.class);
    when(cardDetails.name()).thenReturn("CardDetails");
    when(cardDetails.documentation()).thenReturn("");

    final ConnectType root = new ConnectType(tender, "actions.", null);
    final ConnectType parent = new ConnectType(cardDetails, "actions.", root);
    final ConnectEnum child = new ConnectEnum(status, "actions.", parent);
    assertThat(parent.generateName(), equalTo("TenderCardDetails"));
    assertThat(child.generateName(), equalTo("TenderCardDetailsStatus"));
  }
}
