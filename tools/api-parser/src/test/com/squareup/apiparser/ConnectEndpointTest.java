package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.wire.schema.internal.parser.RpcElement;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectEndpointTest {
  @Test
  public void testToJson() throws Exception {
    final String doc = "  /*--\n"
        + "    @entity Transaction\n"
        + "    @path /v2/locations/{location_id}/transactions/{transaction_id}/capture\n"
        + "    @httpmethod POST\n"
        + "    @oauthpermissions PAYMENTS_WRITE\n"
        + "    @desc For executing delayed capture.\n"
        + "  --*/\n";
    final RpcElement rpc = mock(RpcElement.class);
    when(rpc.documentation()).thenReturn(doc);
    when(rpc.requestType()).thenReturn("actions.CaptureTransactionRequest");
    when(rpc.responseType()).thenReturn("actions.CaptureTransactionResponse");
    final ProtoIndexer indexer = new ProtoIndexer();
    final URL url = Resources.getResource("resources/actions.proto");
    final Path path = Paths.get(url.getFile());
    final ProtoIndex index = indexer.indexProtos(ImmutableList.of(path.getParent().toString()));
    final ConnectEndpoint endpoint = new ConnectEndpoint(rpc, index);
    final JsonObject json = endpoint.toJson();
    final JsonObject responses = json.get("responses").getAsJsonObject();
    assertThat(responses.get("200"), notNullValue());
    final JsonArray params = json.get("parameters").getAsJsonArray();

    Optional<JsonObject> authHeader =
        StreamSupport.stream(params.getAsJsonArray().<JsonElement>spliterator(), false)
            .map(JsonElement::getAsJsonObject)
            .filter(o -> "Authorization".equals(o.get("name").getAsString()))
            .findFirst();

    assertTrue(authHeader.isPresent());
    assertTrue(authHeader.get().get("required").getAsBoolean());
  }
}
