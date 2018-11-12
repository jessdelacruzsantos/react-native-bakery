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
import static com.squareup.apiparser.Json.GSON;
public class ConfigurationTest {
  @Test
  public void swaggerBase() {
    Configuration config = new Configuration();
    JsonObject swagger = GSON.toJsonTree(config.swaggerBase()).getAsJsonObject();

    assertThat(swagger.get("swagger").getAsString(), equalTo("2.0"));

    JsonObject info = swagger.getAsJsonObject("info");
    assertThat(info, not(nullValue()));
    assertThat(info.get("version").getAsString(), equalTo("2.0"));
    assertThat(info.get("title").getAsString(), equalTo("Square Connect API"));
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

    assertThat(swagger.get("host").getAsString(), equalTo("connect.squareup.com"));
    assertThat(swagger.getAsJsonArray("schemes").getAsString(), equalTo("https"));
    assertThat(swagger.getAsJsonArray("consumes").getAsString(), equalTo("application/json"));
    assertThat(swagger.getAsJsonArray("produces").getAsString(), equalTo("application/json"));


    JsonObject secDef = swagger.getAsJsonObject("securityDefinitions");
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
}
