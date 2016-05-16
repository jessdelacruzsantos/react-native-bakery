package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.EnumConstantElement;
import com.squareup.wire.schema.internal.parser.EnumElement;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectEnumTest {

  @Test
  public void testToJson() throws Exception {
    EnumElement element = mock(EnumElement.class);
    when(element.name()).thenReturn("Name");
    when(element.constants()).thenReturn(ImmutableList.<EnumConstantElement>of());
    when(element.documentation()).thenReturn("");
    when(element.options()).thenReturn(ImmutableList.of());
    ConnectEnum connectEnum = new ConnectEnum(
        ApiReleaseType.ALL, element, "packageName", Optional.empty());
    assertThat(connectEnum.toJson().get("type").getAsString(), equalTo("string"));
  }
}
