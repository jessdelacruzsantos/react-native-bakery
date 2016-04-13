# api-parser

This is a java application that parses the protos in the `connect-public-protos`
repo to generate a JSON representation of the Connect API.


## Building
Run `mvn install` from this directory. That should be it!

## Running
From this directory, run:

    java -jar target/apiparser-1.0-SNAPSHOT.jar ~/Development/go/src/square/up/xp/connect-public-protos/protos/squareup/connect/v2

    mv *.json ../sdk-gen/

This will overwrite the `api.json` file in `tools/sdk-gen`. `sdk-gen` uses this
file to generate core classes for each language's SDK. See its README for more
information.
