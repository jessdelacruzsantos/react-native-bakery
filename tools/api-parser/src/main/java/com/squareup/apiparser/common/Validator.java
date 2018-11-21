package com.squareup.apiparser;
import com.google.common.collect.ImmutableSet;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

class Validator {
    // See http://swagger.io/specification/#pathItemObject
    private static final ImmutableSet<String> VALID_HTTP_METHODS =
      ImmutableSet.of("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD", "PATCH");

    //Description must not be empty when status is greater than ALPHA
    public static void validateDescription(String name, String description, Group group){
        if (group.isCustomerFacing() && description.equals("")){
            //TODO: add the following line back once all description has been fixed
            //checkState(!description.equals(""), "'%s' is missing description", name);
            System.out.println(name + " is missing description");
        }
    }

    public static void validateHttpMethod(String httpMethod){
        if (!httpMethod.equals("") && !VALID_HTTP_METHODS.contains(httpMethod)){
            throw new InvalidSpecException.Builder("Unrecognized HTTP method '" + httpMethod + "'")
            .build();
        }
    }

    public static void validateRequestType(String endPointName, ConnectDatatype type){
        String typeName = type.getName();
        String properName = endPointName + "Request";
        if(!properName.equals(typeName)){
            System.out.println("Request type '" + typeName + "' is not " + properName + " for endpoint " + endPointName);
        }
    }

    public static void validateResponseType(String endPointName, ConnectDatatype type){
        String typeName = type.getName();
        String properName = endPointName + "Response";
        if(!properName.equals(typeName)){
            System.out.println("Response type '" + typeName + "' is not " + properName + " for endpoint " + endPointName);
        }
    }
}
