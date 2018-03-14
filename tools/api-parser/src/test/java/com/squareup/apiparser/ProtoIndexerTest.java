package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProtoIndexerTest {
  @Test
  public void testProtoLoading() throws Exception {
    ProtoIndexer indexer = new ProtoIndexer();
    URL url = Resources.getResource("actions.proto");
    Path path = Paths.get(url.getFile());
    ProtoIndex index = indexer.indexProtos(ImmutableList.of(path.getParent().toString()));
    assertThat(index.getEndpoints().size(), equalTo(2));
    assertThat(index.getDatatypes().size(), equalTo(12));
    assertThat(index.getEnums().size(), equalTo(4));
  }
}
