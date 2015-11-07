<?php

set_include_path(get_include_path . PATH_SEPARATOR .
                 dirname(__FILE__) . '/Protobuf-PHP/library' . PATH_SEPARATOR .
                 dirname(__FILE__) . '/generated');

require_once 'DrSlump/Protobuf.php';
require_once 'DrSlump/Protobuf/Message.php';
require_once 'DrSlump/Protobuf/Enum.php';
foreach(glob(dirname(__FILE__) . '/generated/actions/*.php') as $filename) {
  require_once $filename;
}
foreach(glob(dirname(__FILE__) . '/generated/resources/*.php') as $filename) {
  require_once $filename;
}

\DrSlump\Protobuf::autoload();

class SquareConnect {

  private static $connectRoot = 'https://connect.squareup.com';

  public static function Charge($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/Charge';
    $responseWrapper = new \squareup\connect\v3\actions\ChargeResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function Refund($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/Refund';
    $responseWrapper = new \squareup\connect\v3\actions\RefundResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function ListLocations($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListLocations';
    $responseWrapper = new \squareup\connect\v3\actions\ListLocationsResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function CreateCard($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCard';
    $responseWrapper = new \squareup\connect\v3\actions\CreateCardResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function UpsertCustomer($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/UpsertCustomer';
    $responseWrapper = new \squareup\connect\v3\actions\UpsertCustomerResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function ListCustomers($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListCustomers';
    $responseWrapper = new \squareup\connect\v3\actions\ListCustomersResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function RetrieveCustomer($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveCustomer';
    $responseWrapper = new \squareup\connect\v3\actions\RetrieveCustomerResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function CreateCustomerCard($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCustomerCard';
    $responseWrapper = new \squareup\connect\v3\actions\CreateCustomerCardResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function UpdateCustomerCard($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/UpdateCustomerCard';
    $responseWrapper = new \squareup\connect\v3\actions\UpdateCustomerCardResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function DeleteCustomerCard($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/DeleteCustomerCard';
    $responseWrapper = new \squareup\connect\v3\actions\DeleteCustomerCardResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function CaptureTransaction($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/CaptureTransaction';
    $responseWrapper = new \squareup\connect\v3\actions\CaptureTransactionResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function VoidTransaction($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/VoidTransaction';
    $responseWrapper = new \squareup\connect\v3\actions\VoidTransactionResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function ListTransactions($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListTransactions';
    $responseWrapper = new \squareup\connect\v3\actions\ListTransactionsResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }

  public static function RetrieveTransaction($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveTransaction';
    $responseWrapper = new \squareup\connect\v3\actions\RetrieveTransactionResponse();
    return self::sendRequest($requestPath, $context, $requestObject, $responseWrapper);
  }


  public static function sendRequest($path, $context, $body, $responseWrapper) {
    $ch = curl_init();

    $requestHeaders = array(
      'Content-Type: application/x-protobuf',
      'Authorization: Bearer ' . $context->accessToken
    );

    curl_setopt($ch, CURLOPT_HTTPHEADER, $requestHeaders);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST'); // Every v2 request is a POST
    curl_setopt($ch, CURLOPT_POSTFIELDS, $body);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, 1);
    curl_setopt($ch, CURLOPT_URL, self::$connectRoot . $path);

    $response = curl_exec($ch);
    $information = curl_getinfo($ch);
    $httpcode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    $header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
    $responseHeaders = substr($response, 0, $header_size);
    $responseBody = substr($response, $header_size);
    curl_close ($ch);

    $responseWrapper->parse($responseBody);
    return $responseWrapper;
  }
}

class RequestContext {
  public $accessToken;
}

?>
