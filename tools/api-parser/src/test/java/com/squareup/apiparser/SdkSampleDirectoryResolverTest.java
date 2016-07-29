package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SdkSampleDirectoryResolverTest {
  @Test
  public void testResolveSamplePath() {
    Iterable<SdkLanguage> supportedLanguages = ImmutableList.of(SdkLanguage.RUBY, SdkLanguage.PHP);
    JsonElement sdkJsonElement =
        SdkSampleDirectoryResolver.resolveSamplePath("EndpointRequest", supportedLanguages)
            .apply("/samples/Endpoint");
    JsonObject sdkJsonObject = sdkJsonElement.getAsJsonObject();
    assertThat(sdkJsonObject.entrySet().size(), equalTo(2));
    assertThat(sdkJsonObject.getAsJsonPrimitive("ruby").getAsString(),
        equalTo("/samples/Endpoint/EndpointRequest.ruby"));
    assertThat(sdkJsonObject.getAsJsonPrimitive("php").getAsString(),
        equalTo("/samples/Endpoint/EndpointRequest.php"));
  }
}