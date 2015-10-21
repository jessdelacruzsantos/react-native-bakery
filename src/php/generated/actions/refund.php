<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/actions/refund.proto
//   Date: 2015-10-21 21:23:58

namespace squareup\connect\v3\actions\ListRefundsRequest\Params {

  class Sort extends \DrSlump\Protobuf\Enum {
    const CREATED_AT_DESC = 0;
    const CREATED_AT_ASC = 1;
  }
}
namespace squareup\connect\v3\actions\ListRefundsRequest {

  class Params extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $begin_time = null;
    
    /**  @var string */
    public $end_time = null;
    
    /**  @var int - \squareup\connect\v3\actions\ListRefundsRequest\Params\Sort */
    public $sort = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.ListRefundsRequest.Params');

      // REQUIRED STRING location_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // REQUIRED STRING begin_time = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "begin_time";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // REQUIRED STRING end_time = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "end_time";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // REQUIRED ENUM sort = 4
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 4;
      $f->name      = "sort";
      $f->type      = \DrSlump\Protobuf::TYPE_ENUM;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $f->reference = '\squareup\connect\v3\actions\ListRefundsRequest\Params\Sort';
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <location_id> has a value
     *
     * @return boolean
     */
    public function hasLocationId(){
      return $this->_has(1);
    }
    
    /**
     * Clear <location_id> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function clearLocationId(){
      return $this->_clear(1);
    }
    
    /**
     * Get <location_id> value
     *
     * @return string
     */
    public function getLocationId(){
      return $this->_get(1);
    }
    
    /**
     * Set <location_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function setLocationId( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <begin_time> has a value
     *
     * @return boolean
     */
    public function hasBeginTime(){
      return $this->_has(2);
    }
    
    /**
     * Clear <begin_time> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function clearBeginTime(){
      return $this->_clear(2);
    }
    
    /**
     * Get <begin_time> value
     *
     * @return string
     */
    public function getBeginTime(){
      return $this->_get(2);
    }
    
    /**
     * Set <begin_time> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function setBeginTime( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <end_time> has a value
     *
     * @return boolean
     */
    public function hasEndTime(){
      return $this->_has(3);
    }
    
    /**
     * Clear <end_time> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function clearEndTime(){
      return $this->_clear(3);
    }
    
    /**
     * Get <end_time> value
     *
     * @return string
     */
    public function getEndTime(){
      return $this->_get(3);
    }
    
    /**
     * Set <end_time> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function setEndTime( $value){
      return $this->_set(3, $value);
    }
    
    /**
     * Check if <sort> has a value
     *
     * @return boolean
     */
    public function hasSort(){
      return $this->_has(4);
    }
    
    /**
     * Clear <sort> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function clearSort(){
      return $this->_clear(4);
    }
    
    /**
     * Get <sort> value
     *
     * @return int - \squareup\connect\v3\actions\ListRefundsRequest\Params\Sort
     */
    public function getSort(){
      return $this->_get(4);
    }
    
    /**
     * Set <sort> value
     *
     * @param int - \squareup\connect\v3\actions\ListRefundsRequest\Params\Sort $value
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function setSort( $value){
      return $this->_set(4, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class ListRefundsRequest extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\actions\ListRefundsRequest\Params */
    public $params = null;
    
    /**  @var string */
    public $cursor = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.ListRefundsRequest');

      // OPTIONAL MESSAGE params = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "params";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\actions\ListRefundsRequest\Params';
      $descriptor->addField($f);

      // OPTIONAL STRING cursor = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "cursor";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <params> has a value
     *
     * @return boolean
     */
    public function hasParams(){
      return $this->_has(1);
    }
    
    /**
     * Clear <params> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsRequest
     */
    public function clearParams(){
      return $this->_clear(1);
    }
    
    /**
     * Get <params> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsRequest\Params
     */
    public function getParams(){
      return $this->_get(1);
    }
    
    /**
     * Set <params> value
     *
     * @param \squareup\connect\v3\actions\ListRefundsRequest\Params $value
     * @return \squareup\connect\v3\actions\ListRefundsRequest
     */
    public function setParams(\squareup\connect\v3\actions\ListRefundsRequest\Params $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <cursor> has a value
     *
     * @return boolean
     */
    public function hasCursor(){
      return $this->_has(2);
    }
    
    /**
     * Clear <cursor> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsRequest
     */
    public function clearCursor(){
      return $this->_clear(2);
    }
    
    /**
     * Get <cursor> value
     *
     * @return string
     */
    public function getCursor(){
      return $this->_get(2);
    }
    
    /**
     * Set <cursor> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\ListRefundsRequest
     */
    public function setCursor( $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class ListRefundsResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var \squareup\connect\v3\resources\Refund[]  */
    public $refunds = array();
    
    /**  @var string */
    public $cursor = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.ListRefundsResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // REPEATED MESSAGE refunds = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "refunds";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Refund';
      $descriptor->addField($f);

      // OPTIONAL STRING cursor = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "cursor";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <errors> has a value
     *
     * @return boolean
     */
    public function hasErrors(){
      return $this->_has(1);
    }
    
    /**
     * Clear <errors> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function clearErrors(){
      return $this->_clear(1);
    }
    
    /**
     * Get <errors> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Error
     */
    public function getErrors($idx = NULL){
      return $this->_get(1, $idx);
    }
    
    /**
     * Set <errors> value
     *
     * @param \squareup\connect\v3\resources\Error $value
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function setErrors(\squareup\connect\v3\resources\Error $value, $idx = NULL){
      return $this->_set(1, $value, $idx);
    }
    
    /**
     * Get all elements of <errors>
     *
     * @return \squareup\connect\v3\resources\Error[]
     */
    public function getErrorsList(){
     return $this->_get(1);
    }
    
    /**
     * Add a new element to <errors>
     *
     * @param \squareup\connect\v3\resources\Error $value
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <refunds> has a value
     *
     * @return boolean
     */
    public function hasRefunds(){
      return $this->_has(2);
    }
    
    /**
     * Clear <refunds> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function clearRefunds(){
      return $this->_clear(2);
    }
    
    /**
     * Get <refunds> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Refund
     */
    public function getRefunds($idx = NULL){
      return $this->_get(2, $idx);
    }
    
    /**
     * Set <refunds> value
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function setRefunds(\squareup\connect\v3\resources\Refund $value, $idx = NULL){
      return $this->_set(2, $value, $idx);
    }
    
    /**
     * Get all elements of <refunds>
     *
     * @return \squareup\connect\v3\resources\Refund[]
     */
    public function getRefundsList(){
     return $this->_get(2);
    }
    
    /**
     * Add a new element to <refunds>
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function addRefunds(\squareup\connect\v3\resources\Refund $value){
     return $this->_add(2, $value);
    }
    
    /**
     * Check if <cursor> has a value
     *
     * @return boolean
     */
    public function hasCursor(){
      return $this->_has(3);
    }
    
    /**
     * Clear <cursor> value
     *
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function clearCursor(){
      return $this->_clear(3);
    }
    
    /**
     * Get <cursor> value
     *
     * @return string
     */
    public function getCursor(){
      return $this->_get(3);
    }
    
    /**
     * Set <cursor> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\ListRefundsResponse
     */
    public function setCursor( $value){
      return $this->_set(3, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class CreateRefundRequest extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $idempotency_key = null;
    
    /**  @var \squareup\connect\v3\resources\Refund */
    public $refund = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.CreateRefundRequest');

      // REQUIRED STRING location_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // OPTIONAL STRING idempotency_key = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "idempotency_key";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // REQUIRED MESSAGE refund = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "refund";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $f->reference = '\squareup\connect\v3\resources\Refund';
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <location_id> has a value
     *
     * @return boolean
     */
    public function hasLocationId(){
      return $this->_has(1);
    }
    
    /**
     * Clear <location_id> value
     *
     * @return \squareup\connect\v3\actions\CreateRefundRequest
     */
    public function clearLocationId(){
      return $this->_clear(1);
    }
    
    /**
     * Get <location_id> value
     *
     * @return string
     */
    public function getLocationId(){
      return $this->_get(1);
    }
    
    /**
     * Set <location_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\CreateRefundRequest
     */
    public function setLocationId( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <idempotency_key> has a value
     *
     * @return boolean
     */
    public function hasIdempotencyKey(){
      return $this->_has(2);
    }
    
    /**
     * Clear <idempotency_key> value
     *
     * @return \squareup\connect\v3\actions\CreateRefundRequest
     */
    public function clearIdempotencyKey(){
      return $this->_clear(2);
    }
    
    /**
     * Get <idempotency_key> value
     *
     * @return string
     */
    public function getIdempotencyKey(){
      return $this->_get(2);
    }
    
    /**
     * Set <idempotency_key> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\CreateRefundRequest
     */
    public function setIdempotencyKey( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <refund> has a value
     *
     * @return boolean
     */
    public function hasRefund(){
      return $this->_has(3);
    }
    
    /**
     * Clear <refund> value
     *
     * @return \squareup\connect\v3\actions\CreateRefundRequest
     */
    public function clearRefund(){
      return $this->_clear(3);
    }
    
    /**
     * Get <refund> value
     *
     * @return \squareup\connect\v3\resources\Refund
     */
    public function getRefund(){
      return $this->_get(3);
    }
    
    /**
     * Set <refund> value
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\actions\CreateRefundRequest
     */
    public function setRefund(\squareup\connect\v3\resources\Refund $value){
      return $this->_set(3, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class CreateRefundResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var \squareup\connect\v3\resources\Refund */
    public $refund = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.CreateRefundResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // OPTIONAL MESSAGE refund = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "refund";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Refund';
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <errors> has a value
     *
     * @return boolean
     */
    public function hasErrors(){
      return $this->_has(1);
    }
    
    /**
     * Clear <errors> value
     *
     * @return \squareup\connect\v3\actions\CreateRefundResponse
     */
    public function clearErrors(){
      return $this->_clear(1);
    }
    
    /**
     * Get <errors> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Error
     */
    public function getErrors($idx = NULL){
      return $this->_get(1, $idx);
    }
    
    /**
     * Set <errors> value
     *
     * @param \squareup\connect\v3\resources\Error $value
     * @return \squareup\connect\v3\actions\CreateRefundResponse
     */
    public function setErrors(\squareup\connect\v3\resources\Error $value, $idx = NULL){
      return $this->_set(1, $value, $idx);
    }
    
    /**
     * Get all elements of <errors>
     *
     * @return \squareup\connect\v3\resources\Error[]
     */
    public function getErrorsList(){
     return $this->_get(1);
    }
    
    /**
     * Add a new element to <errors>
     *
     * @param \squareup\connect\v3\resources\Error $value
     * @return \squareup\connect\v3\actions\CreateRefundResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <refund> has a value
     *
     * @return boolean
     */
    public function hasRefund(){
      return $this->_has(2);
    }
    
    /**
     * Clear <refund> value
     *
     * @return \squareup\connect\v3\actions\CreateRefundResponse
     */
    public function clearRefund(){
      return $this->_clear(2);
    }
    
    /**
     * Get <refund> value
     *
     * @return \squareup\connect\v3\resources\Refund
     */
    public function getRefund(){
      return $this->_get(2);
    }
    
    /**
     * Set <refund> value
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\actions\CreateRefundResponse
     */
    public function setRefund(\squareup\connect\v3\resources\Refund $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class RetrieveRefundRequest extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $refund_id = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.RetrieveRefundRequest');

      // REQUIRED STRING location_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // REQUIRED STRING refund_id = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "refund_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <location_id> has a value
     *
     * @return boolean
     */
    public function hasLocationId(){
      return $this->_has(1);
    }
    
    /**
     * Clear <location_id> value
     *
     * @return \squareup\connect\v3\actions\RetrieveRefundRequest
     */
    public function clearLocationId(){
      return $this->_clear(1);
    }
    
    /**
     * Get <location_id> value
     *
     * @return string
     */
    public function getLocationId(){
      return $this->_get(1);
    }
    
    /**
     * Set <location_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\RetrieveRefundRequest
     */
    public function setLocationId( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <refund_id> has a value
     *
     * @return boolean
     */
    public function hasRefundId(){
      return $this->_has(2);
    }
    
    /**
     * Clear <refund_id> value
     *
     * @return \squareup\connect\v3\actions\RetrieveRefundRequest
     */
    public function clearRefundId(){
      return $this->_clear(2);
    }
    
    /**
     * Get <refund_id> value
     *
     * @return string
     */
    public function getRefundId(){
      return $this->_get(2);
    }
    
    /**
     * Set <refund_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\RetrieveRefundRequest
     */
    public function setRefundId( $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class RetrieveRefundResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var \squareup\connect\v3\resources\Refund */
    public $refund = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.RetrieveRefundResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // OPTIONAL MESSAGE refund = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "refund";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Refund';
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <errors> has a value
     *
     * @return boolean
     */
    public function hasErrors(){
      return $this->_has(1);
    }
    
    /**
     * Clear <errors> value
     *
     * @return \squareup\connect\v3\actions\RetrieveRefundResponse
     */
    public function clearErrors(){
      return $this->_clear(1);
    }
    
    /**
     * Get <errors> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Error
     */
    public function getErrors($idx = NULL){
      return $this->_get(1, $idx);
    }
    
    /**
     * Set <errors> value
     *
     * @param \squareup\connect\v3\resources\Error $value
     * @return \squareup\connect\v3\actions\RetrieveRefundResponse
     */
    public function setErrors(\squareup\connect\v3\resources\Error $value, $idx = NULL){
      return $this->_set(1, $value, $idx);
    }
    
    /**
     * Get all elements of <errors>
     *
     * @return \squareup\connect\v3\resources\Error[]
     */
    public function getErrorsList(){
     return $this->_get(1);
    }
    
    /**
     * Add a new element to <errors>
     *
     * @param \squareup\connect\v3\resources\Error $value
     * @return \squareup\connect\v3\actions\RetrieveRefundResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <refund> has a value
     *
     * @return boolean
     */
    public function hasRefund(){
      return $this->_has(2);
    }
    
    /**
     * Clear <refund> value
     *
     * @return \squareup\connect\v3\actions\RetrieveRefundResponse
     */
    public function clearRefund(){
      return $this->_clear(2);
    }
    
    /**
     * Get <refund> value
     *
     * @return \squareup\connect\v3\resources\Refund
     */
    public function getRefund(){
      return $this->_get(2);
    }
    
    /**
     * Set <refund> value
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\actions\RetrieveRefundResponse
     */
    public function setRefund(\squareup\connect\v3\resources\Refund $value){
      return $this->_set(2, $value);
    }
  }
}

