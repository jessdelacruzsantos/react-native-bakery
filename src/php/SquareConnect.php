<?php

namespace SquareConnect;

class SquareConnect {
  public static $accessToken;
  public static $connectRoot = 'https://connect.squareup.com';

  public static function setAccessToken($accessToken) {
    self::$accessToken = $accessToken;
  }

  public static function setConnectRoot($root) {
    self::$connectRoot = $root;
  }

  public static function sendRequest($path, $body, $responseWrapper) {
    $ch = curl_init();

    $requestHeaders = array(
      'Content-Type: application/x-protobuf',
      'Authorization: Bearer ' . self::$accessToken
    );

    curl_setopt($ch, CURLOPT_HEADER, $requestHeaders);
    // Every v2 request is a POST
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $body);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_URL, self::$connectRoot . $path);

    $response = curl_exec($ch);
    curl_close ($ch);

    $responseWrapper->parse($response);
    return $responseWrapper;
  }
}
