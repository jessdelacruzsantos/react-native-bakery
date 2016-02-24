package com.squareup.apiparser;

import com.google.common.io.Resources;
import com.squareup.wire.schema.internal.parser.RpcElement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    final URL url = Resources.getResource("actions.proto");
    final Path path = Paths.get(url.getFile());
    final ProtoIndex index = indexer.indexProtos(new String[]{String.valueOf(path.getParent())});
    final ConnectEndpoint endpoint = new ConnectEndpoint(rpc, index);
    final JSONObject json = endpoint.toJson();
    final JSONObject responses = json.getJSONObject("responses");
    assertThat(responses.getJSONObject("200"), notNullValue());
    final JSONArray params = json.getJSONArray("parameters");
    JSONObject authHeader = null;
    for (int i = 0; i < params.length(); i++) {
      authHeader = params.getJSONObject(i);
      if (authHeader.getString("name") == "Authorization")
        break;
    }
    assertThat(authHeader, notNullValue());
    assertTrue(authHeader.getBoolean("required"));
  }
}
