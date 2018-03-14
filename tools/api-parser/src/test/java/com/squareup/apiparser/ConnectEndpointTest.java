package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.OptionElement;
import com.squareup.wire.schema.internal.parser.RpcElement;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectEndpointTest {
  private String rpcRequestType;
  private String rpcResponseType;

  @Before
  public void setUp() {
    rpcRequestType = "actions.CaptureTransactionRequest";
    rpcResponseType = "actions.CaptureTransactionResponse";
  }

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

    JsonObject successSchema = responses.getAsJsonObject("200").getAsJsonObject("schema");
    Assertions.assertThat(successSchema.get("$ref").getAsString())
        .isEqualTo("#/definitions/CaptureTransactionResponse");

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
  public void testToJson_stripsResources() throws Exception {
    // NB: "actions" in "transactions" is not affected
    rpcRequestType = "resources.ListTransactionsRequest";
    rpcResponseType = "resources.ListTransactionsResponse";
    ConnectEndpoint endpoint = createEndpoint(defaultOptions());
    JsonObject json = endpoint.toJson();

    JsonObject responses = json.get("responses").getAsJsonObject();
    assertThat(responses.get("200"), notNullValue());

    JsonObject successSchema = responses.getAsJsonObject("200").getAsJsonObject("schema");
    Assertions.assertThat(successSchema.get("$ref").getAsString())
        .isEqualTo("#/definitions/ListTransactionsResponse");
  }

  @Test
  public void testPublicEndpointCanDisableOAuth() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(publicEndpointDisabledOauth());
    endpoint.toJson();
  }

  @Test
  public void testDisallowEmptyOAuthPermissions() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(publicEndpointMissingOAuthPermissions());
    assertThatThrownBy(endpoint::toJson)
        .isInstanceOf(InvalidSpecException.class)
        .hasMessageStartingWith("Empty OAuth permissions on OAuth enabled endpoint");
  }

  @Test
  public void testUnauthenticatedEndpoint() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(internalEndpointDisabledOauth());
    JsonObject json = endpoint.toJson();

    assertTrue(json.has("tags"));
    JsonArray security = json.getAsJsonArray("security");
    assertThat(security.size(), equalTo(0));
  }

  @Test
  public void testUnrecognizedHttpMethod() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(invalidHttpMethodOptions());
    assertThatThrownBy(endpoint::toJson)
        .isInstanceOf(InvalidSpecException.class)
        .hasMessage("Unrecognized HTTP method 'INVALID'");
  }

  @Test
  public void testMissingAuthenticationMethod() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(missingAuthenticationMethod());
    assertThatThrownBy(endpoint::toJson)
        .isInstanceOf(InvalidSpecException.class)
        .hasMessage(
            "No common.authentication_methods option found");
  }

  @Test
  public void testInvalidAuthenticationMethods() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(authenticationMethods(
        Optional.of(ImmutableList.of("MULTIPASS", "INVALID"))));
    assertThatThrownBy(endpoint::toJson)
        .isInstanceOf(InvalidSpecException.class)
        .hasMessage("Unrecognized authentication methods: INVALID");
  }

  @Test
  public void testOAuth2ClientSecret() throws Exception {
    ConnectEndpoint endpoint = createEndpoint(publicEndpointOAuth2ClientSecret());
    JsonObject json = endpoint.toJson();

    JsonArray security = json.getAsJsonArray("security");
    assertThat(security, notNullValue());
    boolean foundAuth = false;
    for (int i = 0; i < security.size(); i++) {
      JsonObject section = security.get(i).getAsJsonObject();
      if (section.has("oauth2ClientSecret")) {
        foundAuth = true;
      }
    }
    assertTrue(foundAuth);
  }

  private ConnectEndpoint createEndpoint(ImmutableList<OptionElement> options) throws Exception {
    String doc = "  /*--\n"
        + "    @desc For executing delayed capture.\n"
        + "  --*/\n";
    RpcElement rpc = mock(RpcElement.class);
    when(rpc.documentation()).thenReturn(doc);
    when(rpc.requestType()).thenReturn(rpcRequestType);
    when(rpc.responseType()).thenReturn(rpcResponseType);
    when(rpc.options()).thenReturn(options);

    ProtoIndexer indexer = new ProtoIndexer();
    URL url = Resources.getResource("actions.proto");
    Path path = Paths.get(url.getFile());
    ProtoIndex index = indexer.indexProtos(ImmutableList.of(path.getParent().toString()));
    return new ConnectEndpoint(rpc, index, ReleaseStatus.INTERNAL);
  }

  private List<OptionElement> baseOptions() {
    List<OptionElement> opts = new ArrayList<>();
    opts.add(OptionElement.create("common.entity", OptionElement.Kind.STRING, "Transaction"));
    opts.add(OptionElement.create("common.path", OptionElement.Kind.STRING,
        "/v2/locations/{location_id}/transactions/{transaction_id}/capture"));
    opts.add(OptionElement.create("common.http_method", OptionElement.Kind.STRING, "POST"));
    return opts;
  }

  private ImmutableList<OptionElement> defaultOptions() {
    List<OptionElement> opts = baseOptions();
    opts.add(OptionElement.create("common.authentication_methods", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of("MULTIPASS", "OAUTH2_ACCESS_TOKEN"))));
    opts.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of("PAYMENTS_WRITE"))));
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(opts);
  }

  private ImmutableList<OptionElement> defaultOptionsWithDeprecatedAuthenticationMethod() {
    return ImmutableList.<OptionElement>builder()
        .addAll(defaultOptions())
        .add(OptionElement.create("common.authentication_method", OptionElement.Kind.STRING,
            "OAUTH2_ACCESS_TOKEN"))
        .build();
  }

  private ImmutableList<OptionElement> publicEndpointMissingOAuthPermissions() {
    List<OptionElement> opts = baseOptions();
    opts.add(OptionElement.create(
        "common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of(
            "value", ImmutableList.of("OAUTH2_ACCESS_TOKEN"))));
    opts.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of())));
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(opts);
  }

  private ImmutableList<OptionElement> publicEndpointDisabledOauth() {
    List<OptionElement> opts = baseOptions();
    opts.add(OptionElement.create(
        "common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of()));
    opts.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of())));
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(opts);
  }

  private ImmutableList<OptionElement> internalEndpointDisabledOauth() {
    List<OptionElement> opts = baseOptions();
    opts.add(OptionElement.create(
        "common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of()));
    opts.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of())));
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "INTERNAL"));
    return ImmutableList.copyOf(opts);
  }

  private ImmutableList<OptionElement> invalidHttpMethodOptions() {
    List<OptionElement> opts = new ArrayList<>();
    opts.add(OptionElement.create(
        "common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of(
            "value", ImmutableList.of("OAUTH2_ACCESS_TOKEN"))));
    opts.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of("PAYMENTS_WRITE"))));
    opts.add(OptionElement.create("common.entity", OptionElement.Kind.STRING, "Transaction"));
    opts.add(OptionElement.create("common.path", OptionElement.Kind.STRING,
        "/v2/locations/{location_id}/transactions/{transaction_id}/capture"));
    opts.add(OptionElement.create("common.http_method", OptionElement.Kind.STRING, "INVALID"));
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(opts);
  }

  private ImmutableList<OptionElement> missingAuthenticationMethod() {
    List<OptionElement> opts = baseOptions();
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(opts);
  }

  private ImmutableList<OptionElement> authenticationMethods(
      Optional<List<String>> maybeAuthMethods) {
    ImmutableList.Builder<OptionElement> opts = ImmutableList.<OptionElement>builder()
        .addAll(baseOptions());
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));

    maybeAuthMethods.ifPresent(authMethods ->
        opts.add(OptionElement.create("common.authentication_methods",
            OptionElement.Kind.MAP, ImmutableMap.of("value", ImmutableList.copyOf(authMethods)))));

    return opts.build();
  }

  private ImmutableList<OptionElement> publicEndpointOAuth2ClientSecret() {
    List<OptionElement> opts = baseOptions();
    opts.add(OptionElement.create(
        "common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of(
            "value", ImmutableList.of("OAUTH2_CLIENT_SECRET"))));
    opts.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    return ImmutableList.copyOf(opts);
  }
}
