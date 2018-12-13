# API specification generator
This repo contains a Java tool that parses our internal protocol buffers that define our APIs and generates Swagger/Open API specifications from them. These specification files are used to [generate our documentation](https://stash.corp.squareup.com/projects/CAD/repos/connect-documentation-website/browse) and to [generate our public SDKs](https://github.com/square/connect-api-specification).

## Usage
    script/api-parser-all
This command handles the build, running, and file-moving that the below instructions mention. Provided arguments will be passed along to the JAR file. Specification files are output to 'api.json.d/'.  For more usage notes, see tools/api-parser/README.md.
The output 'api.json.d' folder can be used for [SDK generation](https://git.sqcorp.co/projects/XP/repos/connect-api-specification/browse) & [Documentation generation](https://git.sqcorp.co/projects/CAD/repos/connectv2-docs/browse)

### Return codes
Zero for success, non-zero for failure.

## Details
The parser uses Square's wire library to parse proto files. This was done to gain access to comments in protos which, apparently, were not made available to protoc plugins. As currently implemented the tool does not attempt to fully resolve proto references which makes the tool fast but may limit future extension without addressing this.

## Proto Options
Please refer to [Proto options documentation](https://pages.sqcorp.co/pages/XP/internal-docs/master/browse/build/proto-options.html). This covers status, namespace, comment, httpMethod...etc.

## Grouping with Status and Namespace
| Value | Namespace|File Exported to | Description |
| ----- | ------| ----------- | ----------- |
| `PUBLIC`, `BETA` | N/A | `api.json` | Part of the public API for external developers while `BETA` features will be denoted as `BETA`|
| `ALPHA` | N/A | `api_alpha.json` | The endpoint is in development but pre-beta, i.e. not exposed to partners |
| `ALPHA` | `marketplaces` | `api_alpha_marketplaces.json` | The endpoint is in development but not contains non-marketplaces ALPHA |
| `INTERNAL` | N/A | `api_internal.json` | A private endpoint only to be used with Square |
