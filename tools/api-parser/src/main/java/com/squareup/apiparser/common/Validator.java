package com.squareup.apiparser;
import com.google.common.collect.ImmutableSet;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

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

    public static void printErrors(boolean canThrowException){
        for (String error : errors){
            System.out.println(error);
        }
        if (canThrowException && errors.size() > 0){
            throw new InvalidSpecException.Builder("There are " + errors.size() +" errors. Please resolve them.")
            .build();
        }
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
}
