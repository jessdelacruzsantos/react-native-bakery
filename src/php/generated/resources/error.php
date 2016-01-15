<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/resources/error.proto
//   Date: 2016-01-15 23:06:23

namespace squareup\connect\v3\resources\Error {

  class Type extends \DrSlump\Protobuf\Enum {
    const INTERNAL_SERVER_ERROR = 0;
    const UNAUTHORIZED = 40100;
    const ACCESS_TOKEN_EXPIRED = 40101;
    const ACCESS_TOKEN_REVOKED = 40102;
    const FORBIDDEN = 40300;
    const INSUFFICIENT_SCOPES = 40301;
    const APPLICATION_DISABLED = 40302;
    const V1_APPLICATION = 40303;
    const V1_ACCESS_TOKEN = 40304;
    const CARD_PROCESSING_NOT_ENABLED = 40305;
    const BAD_REQUEST = 40000;
    const MISSING_REQUIRED_PARAMETER = 40001;
    const INCORRECT_TYPE = 40002;
    const INVALID_TIME = 40003;
    const INVALID_TIME_RANGE = 40004;
    const INVALID_VALUE = 40005;
    const INVALID_CURSOR = 40006;
    const UNKNOWN_QUERY_PARAMETER = 40007;
    const CONFLICTING_PARAMETERS = 40008;
    const EXPECTED_JSON_BODY = 40009;
    const INVALID_SORT_ORDER = 40010;
    const VALUE_TOO_LONG = 40011;
    const VALUE_TOO_LOW = 40012;
    const EXPECTED_BOOLEAN = 40013;
    const EXPECTED_INTEGER = 40014;
    const EXPECTED_FLOAT = 40015;
    const EXPECTED_STRING = 40016;
    const EXPECTED_OBJECT = 40017;
    const EXPECTED_ARRAY = 40018;
    const INVALID_ARRAY_VALUE = 40019;
    const INVALID_ENUM_VALUE = 40020;
    const INVALID_CONTENT_TYPE = 40021;
    const INVALID_FORM_VALUE = 40022;
    const CARD_DECLINED = 40023;
    const CARD_EXPIRED = 40024;
    const VERIFY_CVV_FAILURE = 40025;
    const VERIFY_AVS_FAILURE = 40026;
    const INVALID_EXPIRATION = 40027;
    const INVALID_EXPIRATION_YEAR = 40028;
    const INVALID_EXPIRATION_DATE = 40039;
    const INVALID_CARD = 40040;
    const DELAYED_TRANSACTION_EXPIRED = 40041;
    const DELAYED_TRANSACTION_CANCELED = 40042;
    const DELAYED_TRANSACTION_CAPTURED = 40043;
    const DELAYED_TRANSACTION_FAILED = 40044;
    const CARD_TOKEN_EXPIRED = 40045;
    const CARD_TOKEN_USED = 40046;
    const AMOUNT_TOO_HIGH = 40047;
    const REFUND_AMOUNT_INVALID = 40048;
    const REFUND_ALREADY_PENDING = 40049;
    const PAYMENT_NOT_REFUNDABLE = 40050;
    const INVALID_CARD_DATA = 40051;
    const NOT_FOUND = 40400;
    const REQUEST_TIMEOUT = 40800;
    const REQUEST_ENTITY_TOO_LARGE = 41300;
    const UNSUPPORTED_MEDIA_TYPE = 41500;
    const RATE_LIMITED = 42900;
    const NOT_IMPLEMENTED = 50100;
    const SERVICE_UNAVAILABLE = 50300;
  }
}
namespace squareup\connect\v3\resources {

  class Error extends \DrSlump\Protobuf\Message {

    /**  @var int - \squareup\connect\v3\resources\Error\Type */
    public $type = null;
    
    /**  @var string */
    public $detail = null;
    
    /**  @var string */
    public $field = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.resources.Error');

      // OPTIONAL ENUM type = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "type";
      $f->type      = \DrSlump\Protobuf::TYPE_ENUM;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Error\Type';
      $descriptor->addField($f);

      // OPTIONAL STRING detail = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "detail";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING field = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "field";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <type> has a value
     *
     * @return boolean
     */
    public function hasType(){
      return $this->_has(1);
    }
    
    /**
     * Clear <type> value
     *
     * @return \squareup\connect\v3\resources\Error
     */
    public function clearType(){
      return $this->_clear(1);
    }
    
    /**
     * Get <type> value
     *
     * @return int - \squareup\connect\v3\resources\Error\Type
     */
    public function getType(){
      return $this->_get(1);
    }
    
    /**
     * Set <type> value
     *
     * @param int - \squareup\connect\v3\resources\Error\Type $value
     * @return \squareup\connect\v3\resources\Error
     */
    public function setType( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <detail> has a value
     *
     * @return boolean
     */
    public function hasDetail(){
      return $this->_has(2);
    }
    
    /**
     * Clear <detail> value
     *
     * @return \squareup\connect\v3\resources\Error
     */
    public function clearDetail(){
      return $this->_clear(2);
    }
    
    /**
     * Get <detail> value
     *
     * @return string
     */
    public function getDetail(){
      return $this->_get(2);
    }
    
    /**
     * Set <detail> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Error
     */
    public function setDetail( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <field> has a value
     *
     * @return boolean
     */
    public function hasField(){
      return $this->_has(3);
    }
    
    /**
     * Clear <field> value
     *
     * @return \squareup\connect\v3\resources\Error
     */
    public function clearField(){
      return $this->_clear(3);
    }
    
    /**
     * Get <field> value
     *
     * @return string
     */
    public function getField(){
      return $this->_get(3);
    }
    
    /**
     * Set <field> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Error
     */
    public function setField( $value){
      return $this->_set(3, $value);
    }
  }
}

