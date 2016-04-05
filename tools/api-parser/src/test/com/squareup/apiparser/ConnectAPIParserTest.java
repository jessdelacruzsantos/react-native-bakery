package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.JsonObject;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ConnectAPIParserTest {
  @Ignore
  @Test
  public void testParseAPI() throws Exception {
    final ProtoIndexer indexer = new ProtoIndexer();
    final URL url = Resources.getResource("actions.proto");
    final Path path = Paths.get(url.getFile());
    final ProtoIndex index = indexer.indexProtos(ImmutableList.of(path.getParent().toString()));

    final ConnectAPIParser.JsonAPI api = new ConnectAPIParser().parseAPI(index);
    final JsonObject json = api.swagger;
    final JsonObject paths = json.getAsJsonObject("paths");
    final JsonObject definitions = json.getAsJsonObject("definitions");

    assertThat(paths.getAsJsonObject("/v2/locations/{location_id}/transactions/{transaction_id}"), not(nullValue()));
    assertThat(definitions.getAsJsonObject("RetrieveTransactionResponse"), not(nullValue()));

    final Path tmp = Files.createTempFile("swagger", ".json");
    try (BufferedSink b = Okio.buffer(Okio.sink(tmp))) {
      final ByteString bs = ByteString.encodeUtf8(json.toString());
      b.write(bs);
      b.flush();
      final String f = tmp.toAbsolutePath().toString();
      final ImmutableList<String> cmd = ImmutableList.of("swagger", "validate", f);
      final Process p = new ProcessBuilder().command(cmd).start();
      final BufferedSource out = Okio.buffer(Okio.source(p.getInputStream()));
      final BufferedSource err = Okio.buffer(Okio.source(p.getErrorStream()));
      final int i = p.waitFor();
      final String e = err.readUtf8();
      final String o = out.readUtf8();
      if (0 != i || !e.isEmpty()) {
        fail("swagger couldn't validate output:\n" + e + "\n" + json);
      }
      if (!o.contains("is valid")) {
        fail("swagger's output doesn't match the standard successful output:\n" + o);
      }
    }
  }
}
