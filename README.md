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

* [Status](#Status)
* [Namespace](#namespace)
* [Comments](#comments)
* [Endpoints](#endpoints)
* [Datatypes](#datatypes)
* [Fields](#fields)
* [Enums](#Enums)

## Status
**Default** `PUBLIC`
Controls which specification each individual endpoint, datatype, field, or enum value is exported to. The tool generates multiple versions of `api.json` under `api.json.d/` and this option controls which sets the endpoint is included in. A concrete example: `api_alpha.json` includes all `ALPHA`, `BETA` and `PUBLIC` endpoints. After introducing versioning, a standard release process for a big feature release (e.g. exposing a new endpoint.) should be `ALPHA` -> `BETA` -> `PUBLIC`. By ending life of a feature, the status goes from `BETA` or `PUBLIC` to `DEPRECATED`. See [go/buildapis](https://pages.corp.squareup.com/pages/XP/internal-docs/master/browse/build/index.html) for more details.

The option with smaller scope overwrites the bigger scope.  `PUBLIC` == `BETA` < `ALPHA` < `INTERNAL`
| proto option | Description | 
| ------------ | ----------- | 
| `common.file_status` | API visibility of entire proto file. |
| `common.endpoint_status` | API visibility of endpoint. |
| `common.message_status` | API visibility of message. |
| `common.field_status` | API visibility of field. |
| `common.enum_status` | API visibility of enum. |
| `common.enum_value_status` | API visibility of enum value. |

## Namespace
After we introduced releasable ALPHA products, we needed a way to group ALPHA contents by product type such as marketplaces. Namespace was introduced. This option will be ignored for non-ALPHA status.
The option with smaller scope overwrites the bigger scope.
| proto option | Description | 
| ------------ | ----------- | 
| `common.file_namespace` | Namespace of entire proto file. |
| `common.endpoint_namespace` |Namespace of  endpoint. |
| `common.message_namespace` | Namespace of  message. |
| `common.field_namespace` | Namespace of field. |
| `common.enum_namespace` | Namespace of enum. |
| `common.enum_value_namespace` | Namespace of  enum value. |

## Grouping with Status and Namespace
| Value | Namespace|File Exported to | Description |
| ----- | ------| ----------- | ----------- |
| `PUBLIC`, `BETA` | N/A | `api.json` | Part of the public API for external developers while `BETA` features will be denoted as `BETA`|
| `ALPHA` | N/A | `api_alpha.json` | The endpoint is in development but pre-beta, i.e. not exposed to partners |
| `ALPHA` | `marketplaces` | `api_alpha_marketplaces.json` | The endpoint is in development but not contains non-marketplaces ALPHA |
| `INTERNAL` | N/A | `api_internal.json` | A private endpoint only to be used with Square |

## Comments

The parser will look for single or multiline comment blocks _above_ a proto definition (service, endpoint, message or field). Multiline comment blocks should start and end with `--`. With each suitable comment block the parser will extract all annotations. An annotation is defined as `@` immediately followed by a lowercase name, e.g. `@desc`. All text following up to the `@` of the next annotation or the end of the comment block will be associated with the annotation. The text can span multiple lines and all trailing whitespace (including newlines) will be stripped. With annotation text the documentation pipeline supports links in Markdown format `[link text](https://squareup.com)`.

Currently annotation text is not addressable and some annotations names have specific meaning.

| annotation | Description |
| ---------- | ----------- |
| `@desc`    | The endpoint description. This is stored in the `description` property of the endpoint in the Swagger document. |

## Endpoints

API endpoints are defined by marking up service RPC endpoints in proto files. A trivial example

```proto
  service ChatService {
    /*--
     * @desc Broadcast a message to one or more recipients

     What more needs to be said?
     --*/
    rpc Say(actions.SayRequest) returns (actions.SayResponse) {
      option (comment.entity) = "Sentence";
      option (comment.oauth_permissions) = [SPEAK];
    }
  }
```

The endpoint request parameters, validations and response schema are defined as proto messages and are discussed in [Datatypes](#datatypes). To tie this together with the above example the `SayRequest` proto message defines the request parameters to the `Say` endpoint including required parameters and validations to be performed and `SayResponse` defines the response schema.

### Endpoint proto options
| proto option | Description | Affected Swagger endpoint property | Example |
| ------------ | ----------- | ---------------------------------- | ------- |
| `common.entity` | The entity type operated on by this endpoint | `tag` | `option (common.entity) = "Refund";` |
| `common.http_method` | The HTTP method used on this endpoint. Required. Can be any of `GET, PUT, POST, DELETE, OPTIONS, HEAD, PATCH`. Case-sensitive. | Endpoint method property: `head`, `get`, etc | `option (common.http_method) = POST;` |
| `common.method_status` | API visibility of endpoint. | See below | `option (common.method_status) = INTERNAL;` |
| `common.method_namespace` | The namespace of endpoint. This only applies to ALPHA status. | N/A | `option (common.method_namespace) = "marketplaces";` |
| `common.oauth_credential_required` | Explicitly disable OAuth authentication. Use with caution, avoid on public endpoints | `security`, `x-oauthpermissions` | `option (common.oauth_credential_required) = false;` |
| `common.oauth_permissions` | The OAuth permissions required to access this endpoint | `security`, `x-oauthpermissions` | `option (common.oauth_permissions) = { value: [PAYMENTS_WRITE] };` |
| `common.path` | The HTTP relative path to the endpoint. This is passed directly through to Swagger, no validation is currently performed. See [Paths Object](http://swagger.io/specification/#paths-object-29) in the Swagger spec for more information about what forms a valid path. | Path is a property of `paths` object | `option (common.path) = "/v2/locations";` |

#### `common.oauth_credential_required`
**Default** `true`
Set this option to `false` when the endpoint does not use OAuth authentication. This option can only be used on endpoints with `method_status` of `INTERNAL` and should be used sparingly. If this is set to `false` it is invalid to specify any OAuth permissions, e.g. the following is considered invalid by the parser

```proto
  // This is invalid and will cause a parser error!
  option (common.oauth_permissions) = { value: [ITEMS_READ] }
  option (common.oauth_credential_required) = false
```

#### `common.oauth_permissions`
**Default** `[]`
The set of OAuth permissions on this endpoint. It is invalid to have this as an empty array unless `common.oauth_credential_required = false`, in which case it is required that this be empty. NOTE: At this time the parser does _not_ validate the OAuth permissions, it will accept any string put here. It is currently your responsibility to make sure the permissions are valid.

## Datatypes

Datatypes are the entities used by the API, e.g. customers, locations, payments and also describe request parameters and response data for endpoints.

```proto
/*--
  @desc Defines the parameters that can be included in the body of
  a request to the [Charge](#endpoint-charge) endpoint.
--*/
message ChargeRequest {
  option (common.json_example_path) = "/examples/actions/charge_request.json";
  option (common.sdk_sample_directory) = "/sdk_samples/Charge";

  // @desc The ID of the location to associate the created transaction with.
  optional string location_id = 1 [
    (squareup.validation.required) = true,
    (common.path_param) = true
  ];

  ...
}
```

### Datatype proto options

| proto option | Description | Affected Swagger datatype property | Example |
| ------------ | ----------- | ---------------------------------- | ------- |
| `common.json_example_path` | Relative path to a JSON example | `example` | `option (common.json_example_path) = "/examples/actions/charge_request.json"` |
| `common.sdk_sample_directory` | Relative path to code samples | `x-sq-sdk-sample-code` | `option (common.sdk_sample_directory) = "/sdk_samples/Charge"` |
| `common.message_status` | API visibility of datatype | N/A | `option (common.message_status) = PUBLIC;` |
| `common.message_namespace` | The namespace of datatype. This only applies to ALPHA status. | N/A | `option (common.message_namespace) = "marketplaces";` |

#### `common.json_example_path`

This optional field points at a JSON file that describes an example of the datatype. The contents of this JSON object are inlined under the `example` property in the Swagger document.

```proto
  option (common.json_example_path) = "/examples/actions/charge_request.json";
```

On encountering this option the parser will load the `charge_request.json` file and then inline it's contents into Swagger doc under `example`. This will be used in the documentation pipeline. Contents of `charge_request.json`:

```json
{
  "request_url": "/v2/locations/LOCATION_ID/transactions",
  "request_body": {
    "idempotency_key": "74ae1696-b1e3-4328-af6d-f1e04d947a13",
    "shipping_address": {
      "address_line_1": "123 Main St",
      "locality": "San Francisco",
      "administrative_district_level_1": "CA",
      "postal_code": "94114",
      "country": "US"
    },
    "billing_address": {
      "address_line_1": "500 Electric Ave",
      "address_line_2": "Suite 600",
      "administrative_district_level_1": "NY",
      "locality": "New York",
      "postal_code": "10003",
      "country": "US"
    },
    "amount_money": {
      "amount": 5000,
      "currency": "USD"
    },
    "card_nonce": "card_nonce_from_square_123",
    "reference_id": "some optional reference id",
    "note": "some optional note",
    "delay_capture": false
  }
}
```

#### `common.sdk_sample_directory`

A relative path to a directory that contains code samples in one or more languages that demonstrates how this datatype would be used. The option only makes sense on datatypes that represent endpoint request objects. The documentation pipeline will read in any found samples for supported languages and include them in the documentation.

```proto
  option (common.sdk_sample_directory) = "/sdk_samples/Charge";
```

On disk there is a `sdk_samples/Charge` directory and the filename extension indicates the language.

```bash
$ ls sdk_samples/Charge
ChargeRequest.php    ChargeRequest.ruby
```

Currently the only languages supported by the pipeline are PHP and Ruby although more languages will be added over time. The parser will add the relative path in the Swagger doc in the datatype's `x-sq-sdk-sample-code` property and is currently only consumed by the documentation generator.

### Field proto options

| proto option | Description | Affected Swagger datatype property | Example |
| ------------ | ----------- | ---------------------------------- | ------- |
| `common.field_status` | API visibility of field | See below | `(common.field_status) = INTERNAL` |
| `common.field_namespace` | namespace of field. This only applies to ALPHA status. | See below | `(common.field_namespace) = 'marketplaces'` |
| `common.path_param` | Indicates that the parameter is in the URL path | `parameters` | `(common.path_param) = true` |
| `squareup.validation.required` | The field must be set | `required` | `(squareup.validation.required) = true` |
| `squareup.validation.not_empty` | The field must be set to a 'non-empty' value | `required`, field property | `(squareup.validation.not_empty) = true |
| `(squareup.validation.length).min` | The length of the field contents must be >= this value | `required`, field property | `(squareup.validation.length).min = 10` |
| `(squareup.validation.length).max` | The length of the field contents must be <= this value | field property | `(squareup.validation.length).max = 20` |
| `(squareup.validation.range).min` | The value of the field must be >= this value | field property | `(squareup.validation.range).min = 10` |
| `(squareup.validation.range).max` | The value of the field must be <= this value | field property | `(squareup.validation.range).max = 100` |
| `squareup.validation.matches_pattern` | A regex pattern that the field contents must match | field property | `(squareup.validation.matches_pattern) = "a+b?"` |
#### `common.path_param`

**Default** `false`

Setting this parameter to `true` indicates the parameter is defined as part of the HTTP path to the endpoint. This only makes sense on request objects. The parameter's place in the path is specified by a path segment wrapped in `{}` with the same name as the parameter. This allows multiple request parameters to be in the path. For example the path `/v2/{location_id}/transactions/{transaction_id}` would have two fields in the request datatype with `common.path_param` set to `true`:

```proto
  optional string location_id = 1 [(common.path_param) = true];
  ...
  optional string transaction_id = 7 [(common.path_param) = true];
  ...
```

#### `squareup.validation.required`

**Default** `false`

The field must be set. In the Swagger doc the field name will be added under the datatype's `required` property.

#### `(squareup.validation.length).min`

**Default** 0

If present the field's property will include a `minLength` property field name. This is used in SDK generation to enforce a minimum length on the contents. If the value >=0 the field will be added under the datatype's `required` property.

#### `(squareup.validation.length).max`

**Default** N/A

If present the field's property will include a `maxLength` property field name. This is used in SDK generation to enforce a maximum length on the contents.

#### `(squareup.validation.range).min`

**Default** N/A

If present the field's property will include a `minimum` property. This is used in SDK generation to enforce a minimum value validation.

#### `(squareup.validation.range).max`

**Default** N/A

If present the field's property will include a `maximimum` property. This is used in SDK generation to enforce a maximum value validation.

## Enums

Enums are the entities used by the API, e.g. customers, locations, payments and also describe request parameters and response data for endpoints.

```proto
enum Product {
    PRODUCT_DO_NOT_USE = -1 [(common.enum_value_status) = INTERNAL];

    // @desc Square Point of Sale application.
    SQUARE_POS = 0;
    // @desc Square Connect APIs (Transactions API, Checkout API).
    EXTERNAL_API = 1;
    // @desc A Square subscription (various products).
    BILLING = 2;
    // @desc Square Appointments.
    APPOINTMENTS = 3;
    // @desc Square Invoices.
    INVOICES = 4;
    // @desc Square Online Store.
    ONLINE_STORE = 5;
    // @desc Square Payroll.
    PAYROLL = 6;
    // @desc Square Dashboard
    DASHBOARD = 8;
    // @desc Item Library Import 
    ITEM_LIBRARY_IMPORT = 9;
    // @desc A Square product that does not match any other value.
    OTHER = 7;
    // Next available id: 10
}
```

### Enum proto options

| proto option | Description | Affected Swagger datatype property | Example |
| ------------ | ----------- | ---------------------------------- | ------- |
| `common.enum_status` | API visibility of enum | N/A | `option (common.enum_status) = PUBLIC;` |
| `common.enum_namespace` | The namespace of enum. This only applies to ALPHA status. | N/A | `option (common.enum_namespace) = "marketplaces";` |

### Enum Value proto options

| proto option | Description | Affected Swagger datatype property | Example |
| ------------ | ----------- | ---------------------------------- | ------- |
| `common.enum_value_status` | API visibility of enum value | N/A | `option (common.enum_value_status) = PUBLIC;` |
| `common.enum_value_namespace` | The namespace of enum value. This only applies to ALPHA status. | N/A | `option (common.enum_value_namespace) = "marketplaces";` |
