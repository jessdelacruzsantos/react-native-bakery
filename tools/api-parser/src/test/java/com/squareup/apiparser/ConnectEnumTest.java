package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.internal.parser.EnumElement;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectEnumTest {
  @Test
  public void toJson() throws Exception {
    EnumElement element = mock(EnumElement.class);
    when(element.name()).thenReturn("Name");
    when(element.constants()).thenReturn(ImmutableList.of());
    when(element.documentation()).thenReturn("");
    when(element.options()).thenReturn(ImmutableList.of());

    Group group = new Group();
    group.status = ReleaseStatus.INTERNAL;

    ConnectEnum connectEnum = new ConnectEnum(group, element, "packageName", Optional.empty());
    assertThat(connectEnum.toJson(group).get("type").getAsString(),
        equalTo("string"));
    assertThat(connectEnum.toJson(group).get("x-release-status").getAsString(),
            equalTo("INTERNAL"));
  }
}
