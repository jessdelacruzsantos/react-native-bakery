package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.OptionElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectEndpointTest {
  @Test
  public void testSecurity() throws Exception {
    final ConnectEndpoint endpoint = createEndpoint();
    final JsonObject json = endpoint.toJson();

    JsonArray security = json.getAsJsonArray("security");
    assertThat(security, notNullValue());
    assertThat(security.size(), equalTo(1));
    JsonObject oauth2 = security.get(0).getAsJsonObject();
    assertThat(oauth2.getAsJsonArray("oauth2").get(0).getAsString(), equalTo("PAYMENTS_WRITE"));
  }

  @Test
  public void testToJson() throws Exception {
    final ConnectEndpoint endpoint = createEndpoint();
    final JsonObject json = endpoint.toJson();

    final JsonObject responses = json.get("responses").getAsJsonObject();
    assertThat(responses.get("200"), notNullValue());

    assertThat(json.get("description").getAsString(), equalTo("For executing delayed capture."));

    final JsonArray params = json.get("parameters").getAsJsonArray();
    List<String> names = StreamSupport.stream(params.getAsJsonArray().spliterator(), false)
        .map(o -> o.getAsJsonObject().get("name").getAsString())
        .collect(Collectors.toList());
    assertThat(names, equalTo(Arrays.asList("location_id", "transaction_id", "body")));

    JsonArray perms = json.get("x-oauthpermissions").getAsJsonArray();
    assertThat(perms.size(), equalTo(1));
    assertThat(perms.getAsString(), equalTo("PAYMENTS_WRITE"));
  }

  @Test
  public void testUnauthenticatedEndpoint() throws Exception {
    final ConnectEndpoint endpoint = createEndpointWithOAuthPerms(ImmutableList.of());
    final JsonObject json = endpoint.toJson();

    assertTrue(json.has("tags"));
    JsonArray security = json.getAsJsonArray("security");
    assertThat(security.size(), equalTo(0));
  }

  private OptionElement mockOptionElement(String name, Object value) {
    OptionElement opt = mock(OptionElement.class);
    when(opt.name()).thenReturn(name);
    when(opt.value()).thenReturn(value);
    return opt;
  }

  private ConnectEndpoint createEndpoint() throws Exception {
    return createEndpointWithOAuthPerms(ImmutableList.of("PAYMENTS_WRITE"));
  }

  private ConnectEndpoint createEndpointWithOAuthPerms(List<String> perms) throws Exception {
    final String doc = "  /*--\n"
        + "    @desc For executing delayed capture.\n"
        + "  --*/\n";
    final RpcElement rpc = mock(RpcElement.class);
    when(rpc.documentation()).thenReturn(doc);
    when(rpc.requestType()).thenReturn("actions.CaptureTransactionRequest");
    when(rpc.responseType()).thenReturn("actions.CaptureTransactionResponse");

    OptionElement entityOpt = mockOptionElement("entity", "Transaction");
    OptionElement pathOpt = mockOptionElement(
        "path", "/v2/locations/{location_id}/transactions/{transaction_id}/capture");
    OptionElement httpMethodOpt = mockOptionElement("http_method", "POST");

    OptionElement oauthPermissionsOpt = mockOptionElement("common.oauth_permissions",
        ImmutableMap.of("value", perms));
    when(rpc.options()).thenReturn(
        ImmutableList.of(entityOpt, pathOpt, httpMethodOpt, oauthPermissionsOpt));

    final ProtoIndexer indexer = new ProtoIndexer();
    final URL url = Resources.getResource("actions.proto");
    final Path path = Paths.get(url.getFile());
    final ProtoIndex index = indexer.indexProtos(
        ApiReleaseType.ALL, ImmutableList.of(path.getParent().toString()));
    return new ConnectEndpoint(rpc, index);
  }
}
