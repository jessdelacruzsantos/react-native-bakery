package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.JsonObject;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ConnectAPIParserTest {
  @Test
  public void testSwaggerAndInfoObjects() throws Exception {
    ExampleResolver resolver = new ExampleResolver(ImmutableList.of(""));
    ProtoIndex index = new ProtoIndex(resolver, false);
    Configuration config = new Configuration();
    ConnectAPIParser.JsonAPI api =
        new ConnectAPIParser().parseAPI(index, ApiReleaseType.ALL, config);

    JsonObject swagger = api.swagger;
    assertThat(swagger.get("swagger").getAsString(), equalTo("2.0"));

    JsonObject info = api.swagger.getAsJsonObject("info");
    assertThat(info, not(nullValue()));
    assertThat(info.get("version").getAsString(), equalTo(config.getVersion()));
    assertThat(info.get("title").getAsString(), equalTo(config.getTitle()));
    assertThat(info.get("description").getAsString(),
        equalTo("Client library for accessing the Square Connect APIs"));
    assertThat(info.get("termsOfService").getAsString(),
        equalTo("https://connect.squareup.com/tos"));

    JsonObject contact = info.getAsJsonObject("contact");
    assertThat(contact, not(nullValue()));
    assertThat(contact.get("name").getAsString(), equalTo("Square Developer Platform"));
    assertThat(contact.get("email").getAsString(), equalTo("developers@squareup.com"));
    assertThat(contact.get("url").getAsString(), equalTo("https://squareup.com/developers"));

    JsonObject license = info.getAsJsonObject("license");
    assertThat(license, not(nullValue()));
    assertThat(license.get("name").getAsString(), equalTo("Apache 2.0"));
    assertThat(license.get("url").getAsString(),
        equalTo("http://www.apache.org/licenses/LICENSE-2.0.html"));

    JsonObject docs = swagger.getAsJsonObject("externalDocs");
    assertThat(docs, not(nullValue()));
    assertThat(docs.get("description").getAsString(),
        equalTo("Read the official documentation here:"));
    assertThat(docs.get("url").getAsString(), equalTo("https://docs.connect.squareup.com/"));

    assertThat(api.swagger.get("host").getAsString(), equalTo(config.getHost()));
    assertThat(api.swagger.getAsJsonArray("schemes").getAsString(), equalTo("https"));
    assertThat(api.swagger.getAsJsonArray("consumes").getAsString(), equalTo("application/json"));
    assertThat(api.swagger.getAsJsonArray("produces").getAsString(), equalTo("application/json"));
  }

  @Test
  public void testSecurityDefinitions() throws Exception {
    ExampleResolver resolver = new ExampleResolver(ImmutableList.of(""));
    ProtoIndex index = new ProtoIndex(resolver, false);
    Configuration config = new Configuration();
    ConnectAPIParser.JsonAPI api =
        new ConnectAPIParser().parseAPI(index, ApiReleaseType.ALL, config);

    JsonObject secDef = api.swagger.getAsJsonObject("securityDefinitions");
    assertThat(secDef, not(nullValue()));

    JsonObject oauth = secDef.getAsJsonObject("oauth2");
    assertThat(oauth, not(nullValue()));

    assertThat(oauth.get("type").getAsString(), equalTo("oauth2"));
    // TODO - Complete this

    JsonObject clientAuth = secDef.getAsJsonObject("oauth2ClientSecret");
    assertThat(clientAuth, not(nullValue()));
    assertThat(clientAuth.get("type").getAsString(), equalTo("apiKey"));
    assertThat(clientAuth.get("in").getAsString(), equalTo("header"));
    assertThat(clientAuth.get("name").getAsString(), equalTo("Authorization"));
  }

  @Ignore
  @Test
  public void testParseAPI() throws Exception {
    ProtoIndexer indexer = new ProtoIndexer();
    URL url = Resources.getResource("actions.proto");
    Path path = Paths.get(url.getFile());
    ProtoIndex index = indexer.indexProtos(ImmutableList.of(path.getParent().toString()));

    ConnectAPIParser.JsonAPI api =
        new ConnectAPIParser().parseAPI(index, ApiReleaseType.ALL, new Configuration());
    JsonObject json = api.swagger;
    JsonObject paths = json.getAsJsonObject("paths");
    JsonObject definitions = json.getAsJsonObject("definitions");

    assertThat(paths.getAsJsonObject("/v2/locations/{location_id}/transactions/{transaction_id}"),
        not(nullValue()));
    assertThat(definitions.getAsJsonObject("RetrieveTransactionResponse"), not(nullValue()));

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
