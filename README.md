# Connect v2 API specification generator

This repo contains a Java tool that parses our internal protocol buffers that
[define our v2
API](https://stash.corp.squareup.com/projects/GO/repos/square/browse/xp/connect-public-protos/protos/squareup/connect/v2)
and generates Swagger/Open API specifications from them. These specification
files are used to [generate our
documentation](https://stash.corp.squareup.com/projects/CAD/repos/connect-documentation-website/browse)
and to [generate our public
SDKs](https://github.com/square/connect-api-specification).

## Usage

    script/api-parser $GOPATH/src/square/up/xp/connect-public-protos/squareup/connect/v2

This command handles the build, running, and file-moving that the below
instructions mention. Provided arguments will be passed along to the JAR file.
Specification files are output to 'api.json.d/'.  For more usage notes, see
tools/api-parser/README.md.
