package com.squareup.apiparser;

import com.google.common.io.Resources;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProtoIndexerTest {
  @Test
  public void testProtoLoading() throws Exception {
    final ProtoIndexer indexer = new ProtoIndexer();
    final URL url = Resources.getResource("actions.proto");
    final Path path = Paths.get(url.getFile());
    final ProtoIndex index = indexer.indexProtos(new String[]{String.valueOf(path.getParent())});
    assertThat(index.getEndpoints().size(), equalTo(2));
    assertThat(index.getDatatypes().size(), equalTo(10));
    assertThat(index.getEnums().size(), equalTo(4));
  }
}
