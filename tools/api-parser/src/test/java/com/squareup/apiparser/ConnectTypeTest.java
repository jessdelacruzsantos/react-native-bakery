package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectTypeTest {
  @Test
  public void testGenerateName() throws Exception {
    TypeElement tender = mock(MessageElement.class);
    when(tender.name()).thenReturn("Tender");
    when(tender.documentation()).thenReturn("");
    when(tender.options()).thenReturn(ImmutableList.of());

    EnumConstantElement enum1 = mock(EnumConstantElement.class);
    when(enum1.name()).thenReturn("SUCCESS");
    when(enum1.tag()).thenReturn(1);
    when(enum1.documentation()).thenReturn("");
    when(enum1.options()).thenReturn(ImmutableList.of());

    EnumElement status = mock(EnumElement.class);
    when(status.name()).thenReturn("Status");
    when(status.documentation()).thenReturn("");
    when(status.constants()).thenReturn(ImmutableList.of(enum1));
    when(status.options()).thenReturn(ImmutableList.of());

    TypeElement cardDetails = mock(MessageElement.class);
    when(cardDetails.name()).thenReturn("CardDetails");
    when(cardDetails.documentation()).thenReturn("");
    when(cardDetails.options()).thenReturn(ImmutableList.of());

    ConnectType root = new ConnectType(ReleaseStatus.INTERNAL, tender, "actions.", Optional.empty());
    ConnectType parent =
        new ConnectType(ReleaseStatus.INTERNAL, cardDetails, "actions.", Optional.of(root));
    ConnectEnum child = new ConnectEnum(
        ReleaseStatus.INTERNAL, status, "actions.", Optional.of(parent));
    assertThat(parent.generateName(), equalTo("TenderCardDetails"));
    assertThat(child.generateName(), equalTo("TenderCardDetailsStatus"));
  }
}
