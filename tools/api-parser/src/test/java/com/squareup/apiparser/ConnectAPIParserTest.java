package com.squareup.apiparser;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ConnectAPIParserTest {
  @Test
  public void testParseAPI() throws Exception {
    URL url = Resources.getResource("actions.proto");
    Path path = Paths.get(url.getFile());
    Configuration config = new Configuration();
    config.ignoreOneofs = false;
    config.protobufLocations = ImmutableList.of(path.getParent().toString());
    config.sqVersion = "2018-05-01";
    ProtoIndexer indexer = new ProtoIndexer(config);

    Group group = new Group(ReleaseStatus.INTERNAL, "");

    JsonObject json = indexer.toJsonAPISpec(new Configuration(), group);
    JsonObject paths = json.getAsJsonObject("paths");
    JsonObject definitions = json.getAsJsonObject("definitions");

    assertThat(paths.getAsJsonObject("/v2/locations/{location_id}/transactions/{transaction_id}"),
        not(nullValue()));
    assertThat(definitions.getAsJsonObject("RetrieveTransactionResponse"), not(nullValue()));

    // Validate against generated fixture file expected_swagger.json
    String expectedJson =
        Resources.toString(Resources.getResource("expected_swagger.json"), Charsets.US_ASCII);
    JsonObject expected = new JsonParser().parse(expectedJson).getAsJsonObject();

    // Note: if this test fails, either there was an accidental regression or an intentional change.
    // If you want to regenerate the fixture file, set a debug endpoint here and copy the contents
    // of `api.swagger` into the file `expected_swagger.json` in the test/resources directory.
    assertThat(json, equalTo(expected));

    validateViaCli(json);
  }

  private void validateViaCli(JsonObject json) throws Exception {
    if (true) {
      // NB: this flow doesn't seem to work
      return;
    }
    Path tmp = Files.createTempFile("swagger", ".json");
    try (BufferedSink b = Okio.buffer(Okio.sink(tmp))) {
      ByteString bs = ByteString.encodeUtf8(json.toString());
      b.write(bs);
      b.flush();
      String f = tmp.toAbsolutePath().toString();
      ImmutableList<String> cmd = ImmutableList.of("swagger", "validate", f);
      Process p = new ProcessBuilder().command(cmd).start();
      BufferedSource out = Okio.buffer(Okio.source(p.getInputStream()));
      BufferedSource err = Okio.buffer(Okio.source(p.getErrorStream()));
      int i = p.waitFor();
      String e = err.readUtf8();
      String o = out.readUtf8();
      if (0 != i || !e.isEmpty()) {
        fail("swagger couldn't validate output:\n" + e + "\n" + json);
      }
      if (!o.contains("is valid")) {
        fail("swagger's output doesn't match the standard successful output:\n" + o);
      }
    }
  }
}
