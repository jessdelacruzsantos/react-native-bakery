# api-parser

This is a java application that parses the protos in the `connect-public-protos`
repo to generate a JSON representation of the Connect API.

## Quick start

    script/api-parser $GOPATH/src/square/up/xp/connect-public-protos/squareup/connect/v2

This command handles the build, running, and file-moving that the below
instructions mention. Provided arguments will be passed along to the JAR file.

## Building
Run `mvn install` from this directory. That should be it!

## Running
From this directory, run:

    java -jar target/apiparser-1.0-SNAPSHOT.jar ~/Development/go/src/square/up/xp/connect-public-protos/protos/squareup/connect/v2

    mv *.json ../sdk-gen/

This will overwrite the `api.json` file in `tools/sdk-gen`. `sdk-gen` uses this
file to generate core classes for each language's SDK. See its README for more
information.

You can also override some of the basic values for your API definition. These are:

    version (default 2.0)
    title (default Square Connect API)
    host (default connect.squareup.com)

An example of that is doing this for the Caviar/Delivery API.

    java -jar target/apiparser-1.0-SNAPSHOT.jar -version 1.0 -title "Caviar API" -host "api.trycaviar.com" ~/Development/go/src/square/up/xp/connect-public-protos/protos/squareup/delivery/v1
