# Caviar API Conventions

## Swagger specification
Version 2 of the Caviar API is defined with the
[Swagger specification](http://swagger.io/getting-started/). The definition is
available on [Github](https://github.com/trycaviar/api-specification). You
can use this definition to simplify certain development tasks, such as by
generating custom client libraries.

See [Generating client libraries and other tools with Swagger](/articles/client-libraries/#generatingwithswagger)
for more information.

## Endpoint paths

Caviar API endpoints are hosted from the base URL `https://api.trycaviar.com`.
For example, the [ListDeliveries](#endpoint-listdeliveries) endpoint's full
path is:

    https://api.trycaviar.com/public/api/v1/deliveries

## API versions

An endpoint's API version is included in its path. Bug fixes and minor feature
additions might be made to an endpoint's behavior without advancing its version
number. This can include adding optional parameters or response fields. To
prevent future compatibility issues, your application should be prepared to
receive response fields beyond those currently returned by a given endpoint.

Functionality is never *removed* from a particular version of an endpoint, nor
do field names or types change.

The most recent version of the the Caviar API is `v1`. Caviar API applications
can communicate with endpoints from all available versions.

## Endpoint names and return values

An endpoint's name indicates the type of data it handles and the action it
performs on that data. The most common actions are:

|Action  |HTTP Method|Description|
|--------|-----------|-----------|
|Create  |`POST`     |Creates and persists an entity of the corresponding type.|
|List    |`GET`      |Returns all instances of a particular entity that match query parameters you provide.|
|Retrieve|`GET`      |Returns the single instance of an entity that matches the identifier you provide.|
|Update  |`PUT`      |Modifies the existing entity that matches the identifier you provide.|
|Delete  |`DELETE`   |Deletes the existing entity that matches the identifier you provide. **Deleted entities cannot be retrieved or undeleted.**|

For example, the [ListDeliveries](#endpoint-listdeliveries) endpoint returns
an array of processed payments, and the [CreateDelivery](#endpoint-createdelivery)
endpoint creates and persists a customer.

## Request and response headers

Requests to Caviar API endpoints must include the following HTTP headers:

    Authorization: Bearer YOUR_ACCESS_TOKEN
    Accept: application/json

In the place of `YOUR_ACCESS_TOKEN`, provide your application's personal access token.

`POST` and `PUT` requests must include one additional header:

    Content-Type: application/json

By default, all endpoint responses provide data as JSON in the response body and include a `Content-Type: application/json` header.

## Providing parameters

The way you provide parameters to a Caviar API request depends on the HTTP method of the request.

### `GET` and `DELETE` requests

### `POST` and `PUT` requests

For `POST` and `PUT` requests, you instead provide parameters as JSON in the body of your request.

For example, the body of a request to the [CreateDelivery](#endpoint-creatdelivery)
endpoint looks like this:

    {
      "delivery_quote_id": "some-token"
    }

## Working with monetary amounts

All monetary amounts in the Caviar API are represented by the `Money` object, which has the following structure:

    {
      "amount": 400,
      "currency_code": "USD"
    }

The `amount` field is always in the **smallest denomination** of the currency indicated by `currency_code`. When `currency_code` is `USD` (US dollars), `amount` is in cents. The object shown above represents $4.00.

The `currency_code` field is in **ISO 4217** format.

## Working with dates

All representations of dates are strings in **RFC 3339 format**.

You can provide date strings that are either UTC (for example, `2016-01-15T00:00:00Z`) or offset from UTC to indicate time zone (for example, `2016-01-15T00:00:00-08:00` for eight hours behind UTC). If you provide offset dates, be sure to account for daylight saving time correctly, if applicable.

**Date strings returned by the Caviar API are always UTC.**

## Idempotency keys

Certain Caviar API endpoints require an `idempotency_key` string
parameter. Any time you want to initiate a new card transaction or refund,
you should provide a new, unique value for this parameter.

Virtually all popular programming languages provide a function for generating
unique strings. For example:

|Language|Function|
|--------|--------|
|Ruby    |[`SecureRandom.uuid`](http://ruby-doc.org/stdlib-1.9.3/libdoc/securerandom/rdoc/SecureRandom.html#uuid-method)|
|PHP     |[`uniqid`](http://php.net/manual/en/function.uniqid.php)|
|Java    |[`UUID.randomUUID`](http://docs.oracle.com/javase/7/docs/api/java/util/UUID.html)|
|Python  |[`uuid`](https://docs.python.org/2/library/uuid.html)|

Idempotency keys must be unique per _business_, not per application. They cannot
exceed 128 characters.

If you're unsure whether a particular transaction succeeded (for example, if you
don't receive a response from an endpoint for some reason), you can reattempt it
with the same idempotency key without worrying about creating a duplicate transaction.


## Rate limiting

If Caviar API endpoints receive too many requests associated with the same application or access token in a short time window, they might respond with a **429 Too Many Requests** error. If this occurs, try your request again at a later time.


## Handling errors

Caviar API endpoints use HTTP protocol status codes to indicate errors. Error code values range from 400 to 599.

All Caviar v2 endpoints include an `errors` array in their response body if any errors occurred during a request. The response body has the following structure:

    {
      "errors": [
        {
          "category": "AUTHENTICATION_ERROR",
          "code": "UNAUTHORIZED",
          "detail": "This request could not be authorized."
        }
      ]
    }

Each error in the array has the following fields:

* `category` indicates which high-level category the error falls into.
This value never changes for a particular error. See [ErrorCategory](#type-errorcategory)
for possible values.
* `code` indicates the exact type of error that occurred. This value never
changes for a particular error. See [ErrorCode](#type-errorcode) for possible values.
* `detail` is a human-readable string that will help you diagnose the error. This value **can** change for a particular error.
