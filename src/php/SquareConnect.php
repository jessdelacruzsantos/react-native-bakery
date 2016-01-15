<?php

namespace squareup\connect {

  set_include_path(get_include_path . PATH_SEPARATOR .
                   dirname(__FILE__) . '/vendor/datto/protobuf-php/library' . PATH_SEPARATOR .
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

    public static function Charge($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/Charge';
      $request = new \squareup\connect\v3\actions\ChargeRequest();
$request->location_id = self::checkValue('location_id', $requestArray['location_id'], true);
$request->idempotency_key = self::checkValue('idempotency_key', $requestArray['idempotency_key'], true);
if ($requestArray['amount_money']) {
  $request->amount_money = new \squareup\connect\v3\resources\Money();
  $request->amount_money->amount = self::checkValue('amount', $requestArray['amount_money']['amount'], false);
  $request->amount_money->currency = self::checkValue('currency', $requestArray['amount_money']['currency'], false);
}
else {
  throw new \Exception('Missing required field amount_money');
}

$request->delay_capture = self::checkValue('delay_capture', $requestArray['delay_capture'], false);
$request->reference_id = self::checkValue('reference_id', $requestArray['reference_id'], false);
$request->note = self::checkValue('note', $requestArray['note'], false);
$request->customer_id = self::checkValue('customer_id', $requestArray['customer_id'], false);
if ($requestArray['billing_address']) {
  $request->billing_address = new \squareup\connect\v3\resources\Address();
  $request->billing_address->line_1 = self::checkValue('line_1', $requestArray['billing_address']['line_1'], false);
  $request->billing_address->line_2 = self::checkValue('line_2', $requestArray['billing_address']['line_2'], false);
  $request->billing_address->line_3 = self::checkValue('line_3', $requestArray['billing_address']['line_3'], false);
  $request->billing_address->locality = self::checkValue('locality', $requestArray['billing_address']['locality'], false);
  $request->billing_address->sublocality = self::checkValue('sublocality', $requestArray['billing_address']['sublocality'], false);
  $request->billing_address->sublocality_2 = self::checkValue('sublocality_2', $requestArray['billing_address']['sublocality_2'], false);
  $request->billing_address->sublocality_3 = self::checkValue('sublocality_3', $requestArray['billing_address']['sublocality_3'], false);
  $request->billing_address->region = self::checkValue('region', $requestArray['billing_address']['region'], false);
  $request->billing_address->region_2 = self::checkValue('region_2', $requestArray['billing_address']['region_2'], false);
  $request->billing_address->region_3 = self::checkValue('region_3', $requestArray['billing_address']['region_3'], false);
  $request->billing_address->postal_code = self::checkValue('postal_code', $requestArray['billing_address']['postal_code'], false);
  $request->billing_address->country = self::checkValue('country', $requestArray['billing_address']['country'], false);
}

if ($requestArray['shipping_address']) {
  $request->shipping_address = new \squareup\connect\v3\resources\Address();
  $request->shipping_address->line_1 = self::checkValue('line_1', $requestArray['shipping_address']['line_1'], false);
  $request->shipping_address->line_2 = self::checkValue('line_2', $requestArray['shipping_address']['line_2'], false);
  $request->shipping_address->line_3 = self::checkValue('line_3', $requestArray['shipping_address']['line_3'], false);
  $request->shipping_address->locality = self::checkValue('locality', $requestArray['shipping_address']['locality'], false);
  $request->shipping_address->sublocality = self::checkValue('sublocality', $requestArray['shipping_address']['sublocality'], false);
  $request->shipping_address->sublocality_2 = self::checkValue('sublocality_2', $requestArray['shipping_address']['sublocality_2'], false);
  $request->shipping_address->sublocality_3 = self::checkValue('sublocality_3', $requestArray['shipping_address']['sublocality_3'], false);
  $request->shipping_address->region = self::checkValue('region', $requestArray['shipping_address']['region'], false);
  $request->shipping_address->region_2 = self::checkValue('region_2', $requestArray['shipping_address']['region_2'], false);
  $request->shipping_address->region_3 = self::checkValue('region_3', $requestArray['shipping_address']['region_3'], false);
  $request->shipping_address->postal_code = self::checkValue('postal_code', $requestArray['shipping_address']['postal_code'], false);
  $request->shipping_address->country = self::checkValue('country', $requestArray['shipping_address']['country'], false);
}

$request->customer_browser_ip_address = self::checkValue('customer_browser_ip_address', $requestArray['customer_browser_ip_address'], false);
if ($requestArray['card_nonce']) {
  $request->card_nonce = new \squareup\connect\v3\actions\ChargeRequest\CardNonceInstrument();
  $request->card_nonce->card_nonce = self::checkValue('card_nonce', $requestArray['card_nonce']['card_nonce'], true);
}

if ($requestArray['customer_card']) {
  $request->customer_card = new \squareup\connect\v3\actions\ChargeRequest\CustomerCardInstrument();
  $request->customer_card->customer_card_id = self::checkValue('customer_card_id', $requestArray['customer_card']['customer_card_id'], false);
}


      $responseWrapper = new \squareup\connect\v3\actions\ChargeResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function RefundTender($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/RefundTender';
      $request = new \squareup\connect\v3\actions\RefundTenderRequest();
$request->location_id = self::checkValue('location_id', $requestArray['location_id'], true);
$request->idempotency_key = self::checkValue('idempotency_key', $requestArray['idempotency_key'], true);
$request->source_transaction_id = self::checkValue('source_transaction_id', $requestArray['source_transaction_id'], true);
$request->source_tender_id = self::checkValue('source_tender_id', $requestArray['source_tender_id'], true);
$request->reason = self::checkValue('reason', $requestArray['reason'], true);
if ($requestArray['amount_money']) {
  $request->amount_money = new \squareup\connect\v3\resources\Money();
  $request->amount_money->amount = self::checkValue('amount', $requestArray['amount_money']['amount'], false);
  $request->amount_money->currency = self::checkValue('currency', $requestArray['amount_money']['currency'], false);
}
else {
  throw new \Exception('Missing required field amount_money');
}


      $responseWrapper = new \squareup\connect\v3\actions\RefundTenderResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function ListTenderRefunds($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListTenderRefunds';
      $request = new \squareup\connect\v3\actions\ListTenderRefundsRequest();
if ($requestArray['params']) {
  $request->params = new \squareup\connect\v3\actions\ListTenderRefundsRequest\Params();
  $request->params->location_id = self::checkValue('location_id', $requestArray['params']['location_id'], true);
  if ($requestArray['params']['ordered_time_range']) {
  $request->params->ordered_time_range = new \squareup\connect\v3\actions\OrderedTimeRange();
  $request->params->ordered_time_range->begin_time = self::checkValue('begin_time', $requestArray['params']['ordered_time_range']['begin_time'], true);
  $request->params->ordered_time_range->end_time = self::checkValue('end_time', $requestArray['params']['ordered_time_range']['end_time'], true);
  $request->params->ordered_time_range->sort = self::checkValue('sort', $requestArray['params']['ordered_time_range']['sort'], true);
}
else {
  throw new \Exception('Missing required field ordered_time_range');
}

}

$request->cursor = self::checkValue('cursor', $requestArray['cursor'], false);

      $responseWrapper = new \squareup\connect\v3\actions\ListTenderRefundsResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function ListLocations($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListLocations';
      $request = new \squareup\connect\v3\actions\ListLocationsRequest();

      $responseWrapper = new \squareup\connect\v3\actions\ListLocationsResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function CreateCardNonce($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCardNonce';
      $request = new \squareup\connect\v3\actions\CreateCardNonceRequest();
$request->client_id = self::checkValue('client_id', $requestArray['client_id'], true);
$request->business_id = self::checkValue('business_id', $requestArray['business_id'], false);
if ($requestArray['card_data']) {
  $request->card_data = new \squareup\connect\v3\resources\CardData();
  $request->card_data->number = self::checkValue('number', $requestArray['card_data']['number'], false);
  $request->card_data->exp_month = self::checkValue('exp_month', $requestArray['card_data']['exp_month'], false);
  $request->card_data->exp_year = self::checkValue('exp_year', $requestArray['card_data']['exp_year'], false);
  $request->card_data->cvv = self::checkValue('cvv', $requestArray['card_data']['cvv'], false);
}
else {
  throw new \Exception('Missing required field card_data');
}

$request->fingerprint = self::checkValue('fingerprint', $requestArray['fingerprint'], false);
$request->fingerprint_components_json = self::checkValue('fingerprint_components_json', $requestArray['fingerprint_components_json'], false);

      $responseWrapper = new \squareup\connect\v3\actions\CreateCardNonceResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function UpsertCustomer($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/UpsertCustomer';
      $request = new \squareup\connect\v3\actions\UpsertCustomerRequest();
if ($requestArray['customer']) {
  $request->customer = new \squareup\connect\v3\resources\Customer();
  $request->customer->id = self::checkValue('id', $requestArray['customer']['id'], false);
  $request->customer->business_id = self::checkValue('business_id', $requestArray['customer']['business_id'], false);
  $request->customer->created_at = self::checkValue('created_at', $requestArray['customer']['created_at'], false);
  $request->customer->updated_at = self::checkValue('updated_at', $requestArray['customer']['updated_at'], false);
  if ($requestArray['customer']['cards']) {
  $request->customer->cards = new \squareup\connect\v3\resources\Card();
  $request->customer->cards->id = self::checkValue('id', $requestArray['customer']['cards']['id'], false);
  $request->customer->cards->card_brand = self::checkValue('card_brand', $requestArray['customer']['cards']['card_brand'], false);
  $request->customer->cards->last_4 = self::checkValue('last_4', $requestArray['customer']['cards']['last_4'], false);
  $request->customer->cards->exp_month = self::checkValue('exp_month', $requestArray['customer']['cards']['exp_month'], false);
  $request->customer->cards->exp_year = self::checkValue('exp_year', $requestArray['customer']['cards']['exp_year'], false);
  $request->customer->cards->cardholder_name = self::checkValue('cardholder_name', $requestArray['customer']['cards']['cardholder_name'], false);
  if ($requestArray['customer']['cards']['billing_address']) {
  $request->customer->cards->billing_address = new \squareup\connect\v3\resources\Address();
  $request->customer->cards->billing_address->line_1 = self::checkValue('line_1', $requestArray['customer']['cards']['billing_address']['line_1'], false);
  $request->customer->cards->billing_address->line_2 = self::checkValue('line_2', $requestArray['customer']['cards']['billing_address']['line_2'], false);
  $request->customer->cards->billing_address->line_3 = self::checkValue('line_3', $requestArray['customer']['cards']['billing_address']['line_3'], false);
  $request->customer->cards->billing_address->locality = self::checkValue('locality', $requestArray['customer']['cards']['billing_address']['locality'], false);
  $request->customer->cards->billing_address->sublocality = self::checkValue('sublocality', $requestArray['customer']['cards']['billing_address']['sublocality'], false);
  $request->customer->cards->billing_address->sublocality_2 = self::checkValue('sublocality_2', $requestArray['customer']['cards']['billing_address']['sublocality_2'], false);
  $request->customer->cards->billing_address->sublocality_3 = self::checkValue('sublocality_3', $requestArray['customer']['cards']['billing_address']['sublocality_3'], false);
  $request->customer->cards->billing_address->region = self::checkValue('region', $requestArray['customer']['cards']['billing_address']['region'], false);
  $request->customer->cards->billing_address->region_2 = self::checkValue('region_2', $requestArray['customer']['cards']['billing_address']['region_2'], false);
  $request->customer->cards->billing_address->region_3 = self::checkValue('region_3', $requestArray['customer']['cards']['billing_address']['region_3'], false);
  $request->customer->cards->billing_address->postal_code = self::checkValue('postal_code', $requestArray['customer']['cards']['billing_address']['postal_code'], false);
  $request->customer->cards->billing_address->country = self::checkValue('country', $requestArray['customer']['cards']['billing_address']['country'], false);
}

}

  $request->customer->given_name = self::checkValue('given_name', $requestArray['customer']['given_name'], false);
  $request->customer->family_name = self::checkValue('family_name', $requestArray['customer']['family_name'], false);
  $request->customer->nickname = self::checkValue('nickname', $requestArray['customer']['nickname'], false);
  $request->customer->email_address = self::checkValue('email_address', $requestArray['customer']['email_address'], false);
  if ($requestArray['customer']['address']) {
  $request->customer->address = new \squareup\connect\v3\resources\Address();
  $request->customer->address->line_1 = self::checkValue('line_1', $requestArray['customer']['address']['line_1'], false);
  $request->customer->address->line_2 = self::checkValue('line_2', $requestArray['customer']['address']['line_2'], false);
  $request->customer->address->line_3 = self::checkValue('line_3', $requestArray['customer']['address']['line_3'], false);
  $request->customer->address->locality = self::checkValue('locality', $requestArray['customer']['address']['locality'], false);
  $request->customer->address->sublocality = self::checkValue('sublocality', $requestArray['customer']['address']['sublocality'], false);
  $request->customer->address->sublocality_2 = self::checkValue('sublocality_2', $requestArray['customer']['address']['sublocality_2'], false);
  $request->customer->address->sublocality_3 = self::checkValue('sublocality_3', $requestArray['customer']['address']['sublocality_3'], false);
  $request->customer->address->region = self::checkValue('region', $requestArray['customer']['address']['region'], false);
  $request->customer->address->region_2 = self::checkValue('region_2', $requestArray['customer']['address']['region_2'], false);
  $request->customer->address->region_3 = self::checkValue('region_3', $requestArray['customer']['address']['region_3'], false);
  $request->customer->address->postal_code = self::checkValue('postal_code', $requestArray['customer']['address']['postal_code'], false);
  $request->customer->address->country = self::checkValue('country', $requestArray['customer']['address']['country'], false);
}

  $request->customer->phone_number = self::checkValue('phone_number', $requestArray['customer']['phone_number'], false);
  $request->customer->memo = self::checkValue('memo', $requestArray['customer']['memo'], false);
}
else {
  throw new \Exception('Missing required field customer');
}


      $responseWrapper = new \squareup\connect\v3\actions\UpsertCustomerResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function ListCustomers($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListCustomers';
      $request = new \squareup\connect\v3\actions\ListCustomersRequest();
if ($requestArray['params']) {
  $request->params = new \squareup\connect\v3\actions\ListCustomersRequest\Params();
}

$request->cursor = self::checkValue('cursor', $requestArray['cursor'], false);

      $responseWrapper = new \squareup\connect\v3\actions\ListCustomersResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function RetrieveCustomer($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveCustomer';
      $request = new \squareup\connect\v3\actions\RetrieveCustomerRequest();
$request->customer_id = self::checkValue('customer_id', $requestArray['customer_id'], true);

      $responseWrapper = new \squareup\connect\v3\actions\RetrieveCustomerResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function DeleteCustomer($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/DeleteCustomer';
      $request = new \squareup\connect\v3\actions\DeleteCustomerRequest();
$request->customer_id = self::checkValue('customer_id', $requestArray['customer_id'], true);

      $responseWrapper = new \squareup\connect\v3\actions\DeleteCustomerResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function CreateCustomerCard($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCustomerCard';
      $request = new \squareup\connect\v3\actions\CreateCustomerCardRequest();
$request->customer_id = self::checkValue('customer_id', $requestArray['customer_id'], true);
$request->card_nonce = self::checkValue('card_nonce', $requestArray['card_nonce'], true);
if ($requestArray['billing_address']) {
  $request->billing_address = new \squareup\connect\v3\resources\Address();
  $request->billing_address->line_1 = self::checkValue('line_1', $requestArray['billing_address']['line_1'], false);
  $request->billing_address->line_2 = self::checkValue('line_2', $requestArray['billing_address']['line_2'], false);
  $request->billing_address->line_3 = self::checkValue('line_3', $requestArray['billing_address']['line_3'], false);
  $request->billing_address->locality = self::checkValue('locality', $requestArray['billing_address']['locality'], false);
  $request->billing_address->sublocality = self::checkValue('sublocality', $requestArray['billing_address']['sublocality'], false);
  $request->billing_address->sublocality_2 = self::checkValue('sublocality_2', $requestArray['billing_address']['sublocality_2'], false);
  $request->billing_address->sublocality_3 = self::checkValue('sublocality_3', $requestArray['billing_address']['sublocality_3'], false);
  $request->billing_address->region = self::checkValue('region', $requestArray['billing_address']['region'], false);
  $request->billing_address->region_2 = self::checkValue('region_2', $requestArray['billing_address']['region_2'], false);
  $request->billing_address->region_3 = self::checkValue('region_3', $requestArray['billing_address']['region_3'], false);
  $request->billing_address->postal_code = self::checkValue('postal_code', $requestArray['billing_address']['postal_code'], false);
  $request->billing_address->country = self::checkValue('country', $requestArray['billing_address']['country'], false);
}

$request->cardholder_name = self::checkValue('cardholder_name', $requestArray['cardholder_name'], false);

      $responseWrapper = new \squareup\connect\v3\actions\CreateCustomerCardResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function DeleteCustomerCard($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/DeleteCustomerCard';
      $request = new \squareup\connect\v3\actions\DeleteCustomerCardRequest();
$request->customer_id = self::checkValue('customer_id', $requestArray['customer_id'], true);
$request->card_id = self::checkValue('card_id', $requestArray['card_id'], true);

      $responseWrapper = new \squareup\connect\v3\actions\DeleteCustomerCardResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function CaptureTransaction($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/CaptureTransaction';
      $request = new \squareup\connect\v3\actions\CaptureTransactionRequest();
$request->location_id = self::checkValue('location_id', $requestArray['location_id'], true);
$request->transaction_id = self::checkValue('transaction_id', $requestArray['transaction_id'], true);

      $responseWrapper = new \squareup\connect\v3\actions\CaptureTransactionResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function VoidTransaction($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/VoidTransaction';
      $request = new \squareup\connect\v3\actions\VoidTransactionRequest();
$request->location_id = self::checkValue('location_id', $requestArray['location_id'], true);
$request->transaction_id = self::checkValue('transaction_id', $requestArray['transaction_id'], true);

      $responseWrapper = new \squareup\connect\v3\actions\VoidTransactionResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function ListTransactions($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListTransactions';
      $request = new \squareup\connect\v3\actions\ListTransactionsRequest();
if ($requestArray['params']) {
  $request->params = new \squareup\connect\v3\actions\ListTransactionsRequest\Params();
  $request->params->location_id = self::checkValue('location_id', $requestArray['params']['location_id'], true);
  if ($requestArray['params']['ordered_time_range']) {
  $request->params->ordered_time_range = new \squareup\connect\v3\actions\OrderedTimeRange();
  $request->params->ordered_time_range->begin_time = self::checkValue('begin_time', $requestArray['params']['ordered_time_range']['begin_time'], true);
  $request->params->ordered_time_range->end_time = self::checkValue('end_time', $requestArray['params']['ordered_time_range']['end_time'], true);
  $request->params->ordered_time_range->sort = self::checkValue('sort', $requestArray['params']['ordered_time_range']['sort'], true);
}
else {
  throw new \Exception('Missing required field ordered_time_range');
}

}

$request->cursor = self::checkValue('cursor', $requestArray['cursor'], false);

      $responseWrapper = new \squareup\connect\v3\actions\ListTransactionsResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    public static function RetrieveTransaction($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveTransaction';
      $request = new \squareup\connect\v3\actions\RetrieveTransactionRequest();
$request->location_id = self::checkValue('location_id', $requestArray['location_id'], true);
$request->transaction_id = self::checkValue('transaction_id', $requestArray['transaction_id'], true);

      $responseWrapper = new \squareup\connect\v3\actions\RetrieveTransactionResponse();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }


    private static function sendRequest($path, $context, $body, $responseWrapper) {
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
      curl_setopt($ch, CURLOPT_USERAGENT, 'square-connect-php-0.1');

      $response = curl_exec($ch);
      $information = curl_getinfo($ch);
      $httpcode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
      $headerSize = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
      $responseHeaders = substr($response, 0, $headerSize);
      $responseBody = substr($response, $headerSize);
      curl_close ($ch);

      $responseWrapper->parse($responseBody);
      return $responseWrapper;
    }

    private static function checkValue($fieldName, $value, $required) {
      if ($value === NULL && $required) {
        throw new \Exception('Missing required field ' . $fieldName);
      }
      return $value;
    }

    public static function ObtainToken($clientId, $clientSecret, $code, $redirectUri = '') {
      $ch = curl_init();
      $requestHeaders = array(
        'Content-Type: application/json'
      );
      $requestBody = array (
        'client_id' => $clientId,
        'client_secret' => $clientSecret,
        'code' => $code
      );
      if ($redirectUri != '') {
        $requestBody['redirect_uri'] = $redirectUri;
      }
      curl_setopt($ch, CURLOPT_HTTPHEADER, $requestHeaders);
      curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
      curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($requestBody));
      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
      curl_setopt($ch, CURLOPT_HEADER, 1);
      curl_setopt($ch, CURLOPT_URL, self::$connectRoot . '/oauth2/token');

      $response = curl_exec($ch);
      $responseSummary = new OAuthResponse();
      $responseSummary->code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
      $headerSize = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
      $responseSummary->headers = substr($response, 0, $headerSize);
      $responseSummary->body = substr($response, $headerSize);

      curl_close($ch);
      return $responseSummary;
    }

    public static function RenewToken($clientId, $clientSecret, $accessToken) {
      $ch = curl_init();
      $requestHeaders = array(
        'Content-Type: application/json',
        'Authorization: Client ' . $clientSecret
      );
      $requestBody = array (
        'access_token' => $accessToken
      );
      curl_setopt($ch, CURLOPT_HTTPHEADER, $requestHeaders);
      curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
      curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($requestBody));
      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
      curl_setopt($ch, CURLOPT_HEADER, 1);
      curl_setopt($ch, CURLOPT_URL, self::$connectRoot . '/oauth2/clients/' . $clientId . '/access-token/renew');

      $response = curl_exec($ch);
      $responseSummary = new OAuthResponse();
      $responseSummary->code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
      $headerSize = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
      $responseSummary->headers = substr($response, 0, $headerSize);
      $responseSummary->body = substr($response, $headerSize);

      curl_close($ch);
      return $responseSummary;
    }

    public static function RevokeToken($clientId, $clientSecret, $accessToken, $merchantId = '') {
      $ch = curl_init();
      $requestHeaders = array(
        'Content-Type: application/json',
        'Authorization: Client ' . $clientSecret
      );
      $requestBody = array (
        'client_id' => $clientId
      );
      if ($merchantId == '') {
        $requestBody['access_token'] = $accessToken;
      } else {
        $requestBody['merchant_id'] = $merchantId;
      }
      curl_setopt($ch, CURLOPT_HTTPHEADER, $requestHeaders);
      curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
      curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($requestBody));
      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
      curl_setopt($ch, CURLOPT_HEADER, 1);
      curl_setopt($ch, CURLOPT_URL, self::$connectRoot . '/oauth2/revoke');

      $response = curl_exec($ch);
      $responseSummary = new OAuthResponse();
      $responseSummary->code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
      $headerSize = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
      $responseSummary->headers = substr($response, 0, $headerSize);
      $responseSummary->body = substr($response, $headerSize);

      curl_close($ch);
      return $responseSummary;
    }
  }

  class RequestContext {
    public $accessToken;
  }

  class OAuthResponse {
    public $headers;
    public $body;
    public $code;
  }
}

?>
