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

  {{#each this.endpoints}}
  public static function {{this.id}}($context, $requestObject) {
    $requestPath = '/services/squareup.connect.v3.SquareConnectV3/{{this.id}}';
    $responseWrapper = new \squareup\connect\v3\actions{{#backslash}}{{/backslash}}{{this.id}}Response();
    return self::sendRequest($requestPath, $context, $requestObject);
  }

  {{/each}}

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

?>
