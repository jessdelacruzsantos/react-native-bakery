package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.OptionElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import java.util.ArrayList;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectEndpointTest {
  @Test
  public void testSecurity() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(defaultOptions());
    JsonObject json = endpoint.toJson();

    JsonArray security = json.getAsJsonArray("security");
    assertThat(security, notNullValue());
    assertThat(security.size(), equalTo(1));
    JsonObject oauth2 = security.get(0).getAsJsonObject();
    assertThat(oauth2.getAsJsonArray("oauth2").get(0).getAsString(), equalTo("PAYMENTS_WRITE"));
  }

  @Test
  public void testToJson() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(defaultOptions());
    JsonObject json = endpoint.toJson();

    JsonObject responses = json.get("responses").getAsJsonObject();
    assertThat(responses.get("200"), notNullValue());

    assertThat(json.get("description").getAsString(), equalTo("For executing delayed capture."));

    JsonArray params = json.get("parameters").getAsJsonArray();
    List<String> names = StreamSupport.stream(params.getAsJsonArray().spliterator(), false)
        .map(o -> o.getAsJsonObject().get("name").getAsString())
        .collect(Collectors.toList());
    assertThat(names, equalTo(Arrays.asList("location_id", "transaction_id", "body")));

    JsonArray perms = json.get("x-oauthpermissions").getAsJsonArray();
    assertThat(perms.size(), equalTo(1));
    assertThat(perms.getAsString(), equalTo("PAYMENTS_WRITE"));
  }

  @Test
  public void testOnlyInternalCanDisableOAuth() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(publicEndpointDisabledOauth());
    assertThatThrownBy(endpoint::toJson)
        .isInstanceOf(InvalidSpecException.class)
        .hasMessage("OAuth can only be disabled on INTERNAL endpoints");
  }

  @Test
  public void testDisallowEmptyOAuthPermissions() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(publicEndpointMissingOAuthPermissions());
    assertThatThrownBy(endpoint::toJson)
        .isInstanceOf(InvalidSpecException.class)
        .hasMessage("Empty OAuth permissions on an OAuth enabled endpoint");
  }

  @Test
  public void testUnauthenticatedEndpoint() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(internalEndpointDisabledOauth());
    JsonObject json = endpoint.toJson();

    assertTrue(json.has("tags"));
    JsonArray security = json.getAsJsonArray("security");
    assertThat(security.size(), equalTo(0));
  }

  private ConnectEndpoint createEndpoint(ImmutableList<OptionElement> options) throws Exception {
    String doc = "  /*--\n"
        + "    @desc For executing delayed capture.\n"
        + "  --*/\n";
    RpcElement rpc = mock(RpcElement.class);
    when(rpc.documentation()).thenReturn(doc);
    when(rpc.requestType()).thenReturn("actions.CaptureTransactionRequest");
    when(rpc.responseType()).thenReturn("actions.CaptureTransactionResponse");
    when(rpc.options()).thenReturn(options);

    ProtoIndexer indexer = new ProtoIndexer();
    URL url = Resources.getResource("actions.proto");
    Path path = Paths.get(url.getFile());
    ProtoIndex index = indexer.indexProtos(ApiReleaseType.ALL, ImmutableList.of(path.getParent().toString()));
    return new ConnectEndpoint(rpc, index);
  }

  private List<OptionElement> baseOptions() {
    OptionElement entityOpt = OptionElement.create("common.entity", OptionElement.Kind.STRING, "Transaction");
    OptionElement pathOpt = OptionElement.create("common.path", OptionElement.Kind.STRING, "/v2/locations/{location_id}/transactions/{transaction_id}/capture");
    OptionElement httpMethodOpt = OptionElement.create("common.http_method", OptionElement.Kind.STRING, "POST");
    return new ArrayList<>(Arrays.asList(entityOpt, pathOpt, httpMethodOpt));
  }

  private ImmutableList<OptionElement> defaultOptions() {
    List<OptionElement> base = baseOptions();
    base.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of("PAYMENTS_WRITE"))));
    base.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(base);
  }

  private ImmutableList<OptionElement> publicEndpointMissingOAuthPermissions() {
    List<OptionElement> base = baseOptions();
    base.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of())));
    base.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(base);
  }

  private ImmutableList<OptionElement> publicEndpointDisabledOauth() {
    List<OptionElement> base = baseOptions();
    base.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of())));
    base.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    base.add(OptionElement.create("common.oauth_credential_required", OptionElement.Kind.STRING, "false"));
    return ImmutableList.copyOf(base);
  }

  private ImmutableList<OptionElement> internalEndpointDisabledOauth() {
    List<OptionElement> base = baseOptions();
    base.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of())));
    base.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "INTERNAL"));
    base.add(OptionElement.create("common.oauth_credential_required", OptionElement.Kind.STRING, "false"));
    return ImmutableList.copyOf(base);
  }
}
