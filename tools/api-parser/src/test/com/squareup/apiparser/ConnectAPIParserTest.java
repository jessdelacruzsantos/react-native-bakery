package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ConnectAPIParserTest {
  @Test
  public void testParseAPI() throws Exception {
    final ProtoIndexer indexer = new ProtoIndexer();
    final URL url = Resources.getResource("resources/actions.proto");
    final Path path = Paths.get(url.getFile());
    final ProtoIndex index = indexer.indexProtos(new String[]{String.valueOf(path.getParent())});

    final JSONObject json = new ConnectAPIParser().parseAPI(index);
    final JSONObject paths = json.getJSONObject("paths");
    final JSONObject definitions = json.getJSONObject("definitions");

    assertThat(paths.getJSONObject("/v2/locations/{location_id}/transactions/{transaction_id}"), not(nullValue()));
    assertThat(definitions.getJSONObject("RetrieveTransactionResponse"), not(nullValue()));

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
        fail("swagger couldn't validate output:\n" + e + "\n" + json.toString(2));
      }
      if (!o.contains("is valid")) {
        fail("swagger's output doesn't match the standard successful output:\n" + o);
      }
    }
  }
}
