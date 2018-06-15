# api-parser

This is a java application that parses the protos in the `connect-public-protos`
repo to generate a JSON representation of the Connect API.

## Quick start

    script/api-parser $GOPATH/src/square/up/xp/connect-public-protos/protos/squareup/connect/v2/

This command handles the build, running, and file-moving that the below
instructions mention. Provided arguments will be passed along to the JAR file.
`-sqversion` has to be passed in with a valid api version date to generate valid SDKs.
See [here](https://stash.corp.squareup.com/projects/GO/repos/square/browse/oauth/config/api-versions.yaml)

## Manually running the tool

Run `mvn install` from this directory to build.

From this directory, run:

    java -jar target/apiparser-1.0-SNAPSHOT.jar ~/Development/go/src/square/up/xp/connect-public-protos/protos/squareup/connect/v2
    mv *.json ../../api.json.d

You can also override some of the basic values for your API definition. These are:

    version (default 2.0)
    title (default Square Connect API)
    host (default connect.squareup.com)

An example of that is doing this for the Caviar/Delivery API.

    java -jar target/apiparser-1.0-SNAPSHOT.jar -version 1.0 -title "Caviar API" -host "api.trycaviar.com" ~/Development/go/src/square/up/xp/connect-public-protos/protos/squareup/delivery/v1
