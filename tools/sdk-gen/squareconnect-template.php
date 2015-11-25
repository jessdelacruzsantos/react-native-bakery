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

    private static $connectRoot = 'https://connect.squareupstaging.com';

    {{#each this.endpoints}}
    public static function {{this.id}}($context, $requestArray) {
      $requestPath = '/services/squareup.connect.v3.SquareConnectV3/{{this.id}}';
      {{#populateRequestObject}}{{this.inputtype}}{{/populateRequestObject}}
      $responseWrapper = new \squareup\connect\v3\actions{{#backslash}}{{/backslash}}{{this.id}}Response();
      return self::sendRequest($requestPath, $context, $request->serialize(), $responseWrapper);
    }

    {{/each}}

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
