package com.squareup.apiparser;
import com.google.common.collect.ImmutableSet;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.Optional;

class Validator {
    // See http://swagger.io/specification/#pathItemObject
    private static final ImmutableSet<String> VALID_HTTP_METHODS =
      ImmutableSet.of("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD", "PATCH");

    //Description must not be empty when status is greater than ALPHA
    public static void validateDescription(String name, String description){
        if (description.equals("")){
            //TODO: add the following line back once all description has been fixed
            //System.out.println(name + " is missing description");
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
            System.out.println("ERROR: Request type '" + typeName + "' is not " + properName + " for endpoint " + endPointName);
        }
    }

    public static void validateResponseType(String endPointName, ConnectDatatype type){
        String typeName = type.getName();
        String properName = endPointName + "Response";
        if(!properName.equals(typeName)){
            System.out.println("ERROR: Response type '" + typeName + "' is not " + properName + " for endpoint " + endPointName);
        }
    }

    public static void validateDefinitionExists(String identifier, String typeName, ProtoIndexer index){
        Optional<ConnectDatatype> dataType = index.getDataType(typeName);
        Optional<ConnectEnum> enumType = index.getEnumType(typeName);

        if (!ConnectType.TYPE_MAP.containsKey(typeName) && !dataType.isPresent() && !enumType.isPresent()){
            System.out.println("ERROR: " + typeName + " is not defined for " + identifier);
        }
    }
}
