# Connect API v2 Conventions

## Swagger specification
Version 2 of the Connect API is defined with the
[Swagger specification](http://swagger.io/getting-started/). The definition is
available on [Github](https://github.com/square/connect-api-specification). You
can use this definition to simplify certain development tasks, such as by
generating custom client libraries.

See [Generating client libraries and other tools with Swagger](/articles/client-libraries/#generatingwithswagger)
for more information.

## Endpoint paths

Connect API endpoints are hosted from the base URL `https://connect.squareup.com`.
For example, the [ListTransactions](#endpoint-listtransactions) endpoint's full
path is:

    https://connect.squareup.com/v2/locations/{location_id}/transactions

Most endpoint paths include a `location_id` parameter that indicates which of a
business's locations your application is acting on behalf of. You can get a
business's location IDs with the [ListLocations](#endpoint-listlocations) endpoint.

## API versions

An endpoint's API version is included in its path. Bug fixes and minor feature
additions might be made to an endpoint's behavior without advancing its version
number. This can include adding optional parameters or response fields. To
prevent future compatibility issues, your application should be prepared to
receive response fields beyond those currently returned by a given endpoint.

Functionality is never *removed* from a particular version of an endpoint, nor
do field names or types change.

The most recent version of the the Connect API is `v2`. Connect API applications
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

For example, the [ListTransactions](#endpoint-listtransactions) endpoint returns
an array of processed payments, and the [CreateCustomer](#endpoint-createcustomer)
endpoint creates and persists a customer.

## Request and response headers

Requests to Connect API endpoints must include the following HTTP headers:

    Authorization: Bearer YOUR_ACCESS_TOKEN
    Accept: application/json

In the place of `YOUR_ACCESS_TOKEN`, provide either your application's personal access token (available from the application dashboard) or an access token you generated with the OAuth API.

`POST` and `PUT` requests must include one additional header:

    Content-Type: application/json

By default, all endpoint responses provide data as JSON in the response body and include a `Content-Type: application/json` header.

## Providing parameters

The way you provide parameters to a Connect API request depends on the HTTP method of the request.

### `GET` and `DELETE` requests

For `GET` and `DELETE` requests, you provide parameters in a query string you
append to your request's URL. For example, you provide the `sort_order` parameter
to the [ListTransactions](#endpoint-listtransactions) endpoint like so:

    https://connect.squareup.com/v2/locations/LOCATION_ID/transactions?sort_order=ASC

Values for query parameters must be URL-escaped. For example, to provide the value `2016-01-15T00:00:00+02:00` as the `begin_time` parameter of the
[ListTransactions](#endpoint-listtransactions) endpoint, you specify the following:

    https://connect.squareup.com/v2/locations/LOCATION_ID/transactions?begin_time=2016-01-15T00%3A00%3A00%2B02%3A00


### `POST` and `PUT` requests

For `POST` and `PUT` requests, you instead provide parameters as JSON in the body of your request.

For example, the body of a request to the [CreateCustomer](#endpoint-createcustomer)
endpoint looks like this:

    {
      "given_name": "Amelia",
      "family_name": "Earhart"
    }

## Working with monetary amounts

All monetary amounts in the Connect API are represented by the `Money` object, which has the following structure:

    {
      "amount": 400,
      "currency_code": "USD"
    }

**Important:** Unlike version 1 of the Connect API, **all monetary amounts returned by v2 endpoints are positive.** (In v1, monetary amounts are negative if they represent money being paid _by_ a merchant, instead of money being paid _to_ a merchant.)

The `amount` field is always in the **smallest denomination** of the currency indicated by `currency_code`. When `currency_code` is `USD` (US dollars), `amount` is in cents. The object shown above represents $4.00.

The `currency_code` field is in **ISO 4217** format.

## Working with dates

All representations of dates are strings in **RFC 3339 format**.

You can provide date strings that are either UTC (for example, `2016-01-15T00:00:00Z`) or offset from UTC to indicate time zone (for example, `2016-01-15T00:00:00-08:00` for eight hours behind UTC). If you provide offset dates, be sure to account for daylight saving time correctly, if applicable.

**Date strings returned by the Connect API are always UTC.**

### Date ranges

**List** endpoints such as [ListTransactions](#endpoint-listtransactions) often
accept an optional date range with the `begin_time` and `end_time` parameters.
They also accept an optional `sort_order` parameter, which indicates whether results are returned in chronological order (oldest first) or reverse-chronological order (newest first).

Regardless of `sort_order`, `begin_time` is the earlier date and `end_time` is the later date.

* When `sort_order` is `DESC` (newest-first), `begin_time` is **exclusive** and
`end_time` is **inclusive**.
* When `sort_order` is `ASC` (oldest-first), `begin_time` is **inclusive** and
`end_time` is **exclusive**.


## Idempotency keys

Certain Connect API endpoints (currently [Charge](#endpoint-charge) and
[CreateRefund](#endpoint-createrefund)) require an `idempotency_key` string
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


## Paginating results

**List** endpoints such as [ListTransactions](#endpoint-listtransactions) might
paginate the results they return. This means that instead of returning all results
in a single response, these endpoints might return *some* of the results, along
with a `cursor` in the response body that links to the next set of results.

Send a followup request to the same endpoint, providing the `cursor` value
returned in the previous response as a query parameter, to fetch the next set of
results. Repeat this process until you receive a response without a `cursor`.


## Handling duplicate results

**List** endpoints might return duplicate results. Use the `id` attribute of the returned objects to identify
any such duplicates.


## Handling the enum value `OTHER`

Some Connect API enums include the value `OTHER`. If you retrieve an object that currently has the value `OTHER`
for one of its fields, that field might have a *different* value if you retrieve the object again at a later
date, when an appropriate value has been added to the enum.

Enum values besides `OTHER` never change retroactively.


## Replacing application credentials

You can replace your application's personal access token or application secret from the application dashboard.

* Click **Replace Token** next to the Personal Access Token field to generate a new token for your application.
* Click **Replace Secret** next to the Application Secret field to generate a new secret for your application.

Replacing an application credential immediately invalidates the previous credential. Make sure you update your
application accordingly.


## Rate limiting

If Connect API endpoints receive too many requests associated with the same application or access token in a short time window, they might respond with a **429 Too Many Requests** error. If this occurs, try your request again at a later time.


## Handling errors

Connect API endpoints use HTTP protocol status codes to indicate errors. Error code values range from 400 to 599.

All Connect v2 endpoints include an `errors` array in their response body if any errors occurred during a request. The response body has the following structure:

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


## Using OAuth

If you are developing a Connect v2 application for multiple merchants to use,
you must request specific permissions from those merchants with the OAuth API.
See the [OAuth API Reference](/api/oauth/) for more information.
