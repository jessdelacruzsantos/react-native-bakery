package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.squareup.wire.schema.internal.parser.OptionElement;
import java.util.List;
import java.util.Optional;
import org.junit.Ignore;
import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ValidatorTest {

  @Test
  public void validateAuthenticationMethods() {
    // test missing authentication_methods option
    List<OptionElement> missingAuthenticationMethods = new ArrayList<>();
    missingAuthenticationMethods.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    Validator.validateAuthenticationMethods("ListLocations", missingAuthenticationMethods);

    // test invalid authentication_methods
    List<OptionElement> invalidAuthenticationMethods = new ArrayList<>();
    invalidAuthenticationMethods.add(OptionElement.create("common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of("value", ImmutableList.of("MULTIPASS", "INVALID"))));
    Validator.validateAuthenticationMethods("ListLocations", invalidAuthenticationMethods);

    // test missing oAuth missing authentication methods
    List<OptionElement> issingOAuthPermissions = new ArrayList<>();
    issingOAuthPermissions.add(OptionElement.create(
        "common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of(
            "value", ImmutableList.of("OAUTH2_ACCESS_TOKEN"))));
    issingOAuthPermissions.add(OptionElement.create("common.oauth_permissions", OptionElement.Kind.MAP,
        ImmutableMap.of("value", ImmutableList.of())));
    issingOAuthPermissions.add(OptionElement.create("common.method_status", OptionElement.Kind.STRING, "PUBLIC"));
    Validator.validateAuthenticationMethods("ListLocations", issingOAuthPermissions);

    // test valid Authentication options
    List<OptionElement> goodOpts = new ArrayList<>();
    goodOpts.add(OptionElement.create("common.entity", OptionElement.Kind.STRING, "Transaction"));
    goodOpts.add(OptionElement.create("common.path", OptionElement.Kind.STRING,
        "/v2/locations/{location_id}/transactions/{transaction_id}/capture"));
    goodOpts.add(OptionElement.create("common.http_method", OptionElement.Kind.STRING, "POST"));
    goodOpts.add(OptionElement.create(
        "common.authentication_methods", OptionElement.Kind.MAP, ImmutableMap.of(
            "value", ImmutableList.of())));
    Validator.validateAuthenticationMethods("ListLocations", goodOpts);

    List<String> errors = ImmutableList.of(
        "ERROR: No common.authentication_methods option found for ListLocations",
        "ERROR: Unrecognized authentication methods [INVALID] for ListLocations",
        "ERROR: Empty OAuth permissions on OAuth enabled endpoint for ListLocations"
    );
    assertThat(Validator.getErrors(), equalTo(errors));
  }
}
