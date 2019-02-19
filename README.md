# API specification generator

This repo contains a Java tool to parse the public protocol buffers that define
our public APIs. The tool generates a Swagger/Open API specification that is used
to generate our public
[Connect Technical Reference](https://docs.connect.squareup.com/api/connect/v2)
and [Connect SDKs](https://docs.connect.squareup.com/sdks).

The parser uses the Square wire library to parse proto files to gain access to
comments in protos which, apparently, were not made available to `protoc`
plugins.
It is important to note that the API parser only _parses_ protos, it does not
_compile_ protos or fully resolve proto references. It will not recognize or
respect import statements or verify references to proto elements outside the
public proto repo.

Please refer to the
[Public Proto](https://plathome.sqprod.co/docs/public-protos)
documentation on PlatHome for more information


## Usage

```
script/api-parser-all
```

The `api-parser-all` command handles the tool build, proto parsing, and spec-file
placement for tech ref and SDK generation.

Square public protos are stored in the
[square-public-apis](https://git.sqcorp.co/projects/XP/repos/square-public-apis/browse)
repo. Specification files are written to the `api.json.d/` directory then copied
to the following local repos:

* `connect-api-specification` - used for
  [SDK generation](https://git.sqcorp.co/projects/XP/repos/connect-api-specification/browse)
* `connectv2-docs` - used for
  [Technical Reference generation](https://git.sqcorp.co/projects/CAD/repos/connectv2-docs/browse)

### Return codes

Zero for success, non-zero for failure.


## Using status and namespace

| Value      | Namespace    | Exported to                 | Use                                           |
| ---------- | ------------ | --------------------------- | --------------------------------------------- |
| PUBLIC     | none         | api.json                    | Publicly available in the API                 |
| BETA       | none         | api.json                    | Publicly available but denoted as `BETA`      |
| DEPRECATED | none         | api.json                    | Publicly available but denoted as `DEPRECATED`|
| ALPHA      | marketplaces | api_alpha_marketplaces.json | Shared with partners through feature flag     |
| ALPHA      | none         | api_alpha.json              | Not valid for public proto files              |
| INTERNAL   | none         | api_internal.json           | Not valid for public proto files              |
| EXCLUDED   | none         | none                        | Maintenance; removes entry from spec          |
