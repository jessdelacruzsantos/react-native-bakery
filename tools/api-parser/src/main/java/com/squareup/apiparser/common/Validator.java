package com.squareup.apiparser;
import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableList;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import com.squareup.wire.schema.internal.parser.OptionElement;
import java.util.Collection;
import java.util.stream.Collectors;

class Validator {
    // See http://swagger.io/specification/#pathItemObject
    private static final ImmutableSet<String> VALID_HTTP_METHODS =
      ImmutableSet.of("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD", "PATCH");

    private static List<String> errors = new ArrayList<>();

    //Description must not be empty when status is greater than ALPHA
    public static void validateDescription(String name, String description){
        if (description.equals("")){
            //TODO: add the following line back once all description has been fixed
            //errors.add(name + " is missing description");
        }
    }

    public static List<String> getErrors(){
        return ImmutableList.copyOf(errors);
    }

    public static void validateHttpMethod(String httpMethod){
        if (!httpMethod.equals("") && !VALID_HTTP_METHODS.contains(httpMethod)){
            throw new InvalidSpecException.Builder("Unrecognized HTTP method '" + httpMethod + "'")
            .build();
        }
    }

    public static void validateRequestType(String identifier, String endPointName, ConnectDatatype type){
        String typeName = type.getName();
        String properName = endPointName + "Request";
        if(!properName.equals(typeName)){
            errors.add("ERROR: Request type '" + typeName + "' is not " + properName + " for " + identifier);
        }
    }

    public static void validateResponseType(String identifier, String endPointName, ConnectDatatype type){
        String typeName = type.getName();
        String properName = endPointName + "Response";
        if(!properName.equals(typeName)){
            errors.add("ERROR: Response type '" + typeName + "' is not " + properName + " for " + identifier);
        }
    }

    public static void validateDefinitionExists(String identifier, String typeName, ProtoIndexer index){
        Optional<ConnectDatatype> dataType = index.getDataType(typeName);
        Optional<ConnectEnum> enumType = index.getEnumType(typeName);

        if (!ConnectType.TYPE_MAP.containsKey(typeName) && !dataType.isPresent() && !enumType.isPresent()){
            errors.add("ERROR: " + typeName + " is not defined for " + identifier);
        }
    }

    public static void validateAuthenticationMethods(String identifier, Collection<OptionElement> options){
        Optional<List<String>> methods_list =
            ProtoOptions.getStringListValue(options, "common.authentication_methods");

        if (!methods_list.isPresent()){
            errors.add("ERROR: No common.authentication_methods option found for " + identifier);
        }

        Set<String> methods = methods_list.map(ImmutableSet::copyOf).orElse(ImmutableSet.of());
        Set<String> invalidMethods = Sets.difference(methods,ConnectEndpoint.VALID_AUTHENTICATION_METHODS);

        if (!invalidMethods.isEmpty()) {
            errors.add("ERROR: Unrecognized authentication methods " + invalidMethods + " for " + identifier);
        }

        Set<String> oauthPermissions = ProtoOptions.getOAuthPermissions(options);

        // OAuth permission rules
        // If the endpoint has OAuth as the authentication method, then the OAuth permissions must be a non-empty set.
        // Otherwise the OAuth permissions must be empty.

        Boolean oauthEnabled = methods.contains(ConnectEndpoint.AUTHENTICATION_METHOD_OAUTH2_ACCESS_TOKEN);
        Boolean oauthScopeRequired = ProtoOptions.getBooleanValueOrDefault(options, "common.oauth_scope_required", true);
        if (oauthEnabled && oauthPermissions.isEmpty() && oauthScopeRequired) {
            errors.add("ERROR: Empty OAuth permissions on OAuth enabled endpoint for " + identifier);
        }

        if (!oauthEnabled && !oauthPermissions.isEmpty()) {
            errors.add("ERROR: Cannot specify OAuth permissions with common.oauth_credential_required = false for " + identifier);
        }
    }

    public static void validateInvisibleFields(String identifier, List<ConnectField> fields, Group group){
        List<ConnectField> requiredInvisibleFields = fields.stream()
            .filter(field -> !field.isPathParam() && field.isRequired() && !group.shouldInclude(field.getGroup()))
            .collect(Collectors.toList());

        if (!requiredInvisibleFields.isEmpty()) {
            String fieldsText =String.join(", ", requiredInvisibleFields.stream()
                    .map(f -> String.format("%s (%s)", f.getName(), f.getGroup().status))
                    .collect(Collectors.toList()));

            errors.add("ERROR: Required fields `" + fieldsText + "` cannot be hidden for " + identifier);
        }
    }
}
