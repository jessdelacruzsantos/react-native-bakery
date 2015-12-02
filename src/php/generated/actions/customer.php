<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/actions/customer.proto
//   Date: 2015-12-02 23:30:20

namespace squareup\connect\v3\actions\ListCustomersRequest {

  class Params extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $location_id = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.ListCustomersRequest.Params');

      // OPTIONAL STRING location_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
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
     * @return \squareup\connect\v3\actions\ListCustomersRequest\Params
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
     * @return \squareup\connect\v3\actions\ListCustomersRequest\Params
     */
    public function setLocationId( $value){
      return $this->_set(1, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class ListCustomersRequest extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\actions\ListCustomersRequest\Params */
    public $params = null;
    
    /**  @var string */
    public $cursor = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.ListCustomersRequest');

      // OPTIONAL MESSAGE params = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "params";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\actions\ListCustomersRequest\Params';
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
     * @return \squareup\connect\v3\actions\ListCustomersRequest
     */
    public function clearParams(){
      return $this->_clear(1);
    }
    
    /**
     * Get <params> value
     *
     * @return \squareup\connect\v3\actions\ListCustomersRequest\Params
     */
    public function getParams(){
      return $this->_get(1);
    }
    
    /**
     * Set <params> value
     *
     * @param \squareup\connect\v3\actions\ListCustomersRequest\Params $value
     * @return \squareup\connect\v3\actions\ListCustomersRequest
     */
    public function setParams(\squareup\connect\v3\actions\ListCustomersRequest\Params $value){
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
     * @return \squareup\connect\v3\actions\ListCustomersRequest
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
     * @return \squareup\connect\v3\actions\ListCustomersRequest
     */
    public function setCursor( $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class ListCustomersResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var \squareup\connect\v3\resources\Customer[]  */
    public $customers = array();
    
    /**  @var string */
    public $cursor = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.ListCustomersResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // REPEATED MESSAGE customers = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "customers";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Customer';
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
     * @return \squareup\connect\v3\actions\ListCustomersResponse
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
     * @return \squareup\connect\v3\actions\ListCustomersResponse
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
     * @return \squareup\connect\v3\actions\ListCustomersResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <customers> has a value
     *
     * @return boolean
     */
    public function hasCustomers(){
      return $this->_has(2);
    }
    
    /**
     * Clear <customers> value
     *
     * @return \squareup\connect\v3\actions\ListCustomersResponse
     */
    public function clearCustomers(){
      return $this->_clear(2);
    }
    
    /**
     * Get <customers> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Customer
     */
    public function getCustomers($idx = NULL){
      return $this->_get(2, $idx);
    }
    
    /**
     * Set <customers> value
     *
     * @param \squareup\connect\v3\resources\Customer $value
     * @return \squareup\connect\v3\actions\ListCustomersResponse
     */
    public function setCustomers(\squareup\connect\v3\resources\Customer $value, $idx = NULL){
      return $this->_set(2, $value, $idx);
    }
    
    /**
     * Get all elements of <customers>
     *
     * @return \squareup\connect\v3\resources\Customer[]
     */
    public function getCustomersList(){
     return $this->_get(2);
    }
    
    /**
     * Add a new element to <customers>
     *
     * @param \squareup\connect\v3\resources\Customer $value
     * @return \squareup\connect\v3\actions\ListCustomersResponse
     */
    public function addCustomers(\squareup\connect\v3\resources\Customer $value){
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
     * @return \squareup\connect\v3\actions\ListCustomersResponse
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
     * @return \squareup\connect\v3\actions\ListCustomersResponse
     */
    public function setCursor( $value){
      return $this->_set(3, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class UpsertCustomerRequest extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Customer */
    public $customer = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.UpsertCustomerRequest');

      // REQUIRED MESSAGE customer = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "customer";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $f->reference = '\squareup\connect\v3\resources\Customer';
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <customer> has a value
     *
     * @return boolean
     */
    public function hasCustomer(){
      return $this->_has(1);
    }
    
    /**
     * Clear <customer> value
     *
     * @return \squareup\connect\v3\actions\UpsertCustomerRequest
     */
    public function clearCustomer(){
      return $this->_clear(1);
    }
    
    /**
     * Get <customer> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function getCustomer(){
      return $this->_get(1);
    }
    
    /**
     * Set <customer> value
     *
     * @param \squareup\connect\v3\resources\Customer $value
     * @return \squareup\connect\v3\actions\UpsertCustomerRequest
     */
    public function setCustomer(\squareup\connect\v3\resources\Customer $value){
      return $this->_set(1, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class UpsertCustomerResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var \squareup\connect\v3\resources\Customer */
    public $customer = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.UpsertCustomerResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // OPTIONAL MESSAGE customer = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "customer";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Customer';
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
     * @return \squareup\connect\v3\actions\UpsertCustomerResponse
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
     * @return \squareup\connect\v3\actions\UpsertCustomerResponse
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
     * @return \squareup\connect\v3\actions\UpsertCustomerResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <customer> has a value
     *
     * @return boolean
     */
    public function hasCustomer(){
      return $this->_has(2);
    }
    
    /**
     * Clear <customer> value
     *
     * @return \squareup\connect\v3\actions\UpsertCustomerResponse
     */
    public function clearCustomer(){
      return $this->_clear(2);
    }
    
    /**
     * Get <customer> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function getCustomer(){
      return $this->_get(2);
    }
    
    /**
     * Set <customer> value
     *
     * @param \squareup\connect\v3\resources\Customer $value
     * @return \squareup\connect\v3\actions\UpsertCustomerResponse
     */
    public function setCustomer(\squareup\connect\v3\resources\Customer $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class RetrieveCustomerRequest extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $customer_id = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.RetrieveCustomerRequest');

      // OPTIONAL STRING location_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // REQUIRED STRING customer_id = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "customer_id";
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
     * @return \squareup\connect\v3\actions\RetrieveCustomerRequest
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
     * @return \squareup\connect\v3\actions\RetrieveCustomerRequest
     */
    public function setLocationId( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <customer_id> has a value
     *
     * @return boolean
     */
    public function hasCustomerId(){
      return $this->_has(2);
    }
    
    /**
     * Clear <customer_id> value
     *
     * @return \squareup\connect\v3\actions\RetrieveCustomerRequest
     */
    public function clearCustomerId(){
      return $this->_clear(2);
    }
    
    /**
     * Get <customer_id> value
     *
     * @return string
     */
    public function getCustomerId(){
      return $this->_get(2);
    }
    
    /**
     * Set <customer_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\RetrieveCustomerRequest
     */
    public function setCustomerId( $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class RetrieveCustomerResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var \squareup\connect\v3\resources\Customer */
    public $customer = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.RetrieveCustomerResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // OPTIONAL MESSAGE customer = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "customer";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Customer';
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
     * @return \squareup\connect\v3\actions\RetrieveCustomerResponse
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
     * @return \squareup\connect\v3\actions\RetrieveCustomerResponse
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
     * @return \squareup\connect\v3\actions\RetrieveCustomerResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <customer> has a value
     *
     * @return boolean
     */
    public function hasCustomer(){
      return $this->_has(2);
    }
    
    /**
     * Clear <customer> value
     *
     * @return \squareup\connect\v3\actions\RetrieveCustomerResponse
     */
    public function clearCustomer(){
      return $this->_clear(2);
    }
    
    /**
     * Get <customer> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function getCustomer(){
      return $this->_get(2);
    }
    
    /**
     * Set <customer> value
     *
     * @param \squareup\connect\v3\resources\Customer $value
     * @return \squareup\connect\v3\actions\RetrieveCustomerResponse
     */
    public function setCustomer(\squareup\connect\v3\resources\Customer $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class CreateCustomerCardRequest extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $customer_id = null;
    
    /**  @var string */
    public $card_nonce = null;
    
    /**  @var \squareup\connect\v3\resources\CardData */
    public $card_data = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.CreateCustomerCardRequest');

      // REQUIRED STRING location_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // REQUIRED STRING customer_id = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "customer_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // OPTIONAL STRING card_nonce = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "card_nonce";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL MESSAGE card_data = 4
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 4;
      $f->name      = "card_data";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\CardData';
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
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
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
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
     */
    public function setLocationId( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <customer_id> has a value
     *
     * @return boolean
     */
    public function hasCustomerId(){
      return $this->_has(2);
    }
    
    /**
     * Clear <customer_id> value
     *
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
     */
    public function clearCustomerId(){
      return $this->_clear(2);
    }
    
    /**
     * Get <customer_id> value
     *
     * @return string
     */
    public function getCustomerId(){
      return $this->_get(2);
    }
    
    /**
     * Set <customer_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
     */
    public function setCustomerId( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <card_nonce> has a value
     *
     * @return boolean
     */
    public function hasCardNonce(){
      return $this->_has(3);
    }
    
    /**
     * Clear <card_nonce> value
     *
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
     */
    public function clearCardNonce(){
      return $this->_clear(3);
    }
    
    /**
     * Get <card_nonce> value
     *
     * @return string
     */
    public function getCardNonce(){
      return $this->_get(3);
    }
    
    /**
     * Set <card_nonce> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
     */
    public function setCardNonce( $value){
      return $this->_set(3, $value);
    }
    
    /**
     * Check if <card_data> has a value
     *
     * @return boolean
     */
    public function hasCardData(){
      return $this->_has(4);
    }
    
    /**
     * Clear <card_data> value
     *
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
     */
    public function clearCardData(){
      return $this->_clear(4);
    }
    
    /**
     * Get <card_data> value
     *
     * @return \squareup\connect\v3\resources\CardData
     */
    public function getCardData(){
      return $this->_get(4);
    }
    
    /**
     * Set <card_data> value
     *
     * @param \squareup\connect\v3\resources\CardData $value
     * @return \squareup\connect\v3\actions\CreateCustomerCardRequest
     */
    public function setCardData(\squareup\connect\v3\resources\CardData $value){
      return $this->_set(4, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class CreateCustomerCardResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var \squareup\connect\v3\resources\Card */
    public $card = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.CreateCustomerCardResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // OPTIONAL MESSAGE card = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "card";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Card';
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
     * @return \squareup\connect\v3\actions\CreateCustomerCardResponse
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
     * @return \squareup\connect\v3\actions\CreateCustomerCardResponse
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
     * @return \squareup\connect\v3\actions\CreateCustomerCardResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <card> has a value
     *
     * @return boolean
     */
    public function hasCard(){
      return $this->_has(2);
    }
    
    /**
     * Clear <card> value
     *
     * @return \squareup\connect\v3\actions\CreateCustomerCardResponse
     */
    public function clearCard(){
      return $this->_clear(2);
    }
    
    /**
     * Get <card> value
     *
     * @return \squareup\connect\v3\resources\Card
     */
    public function getCard(){
      return $this->_get(2);
    }
    
    /**
     * Set <card> value
     *
     * @param \squareup\connect\v3\resources\Card $value
     * @return \squareup\connect\v3\actions\CreateCustomerCardResponse
     */
    public function setCard(\squareup\connect\v3\resources\Card $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class DeleteCustomerCardRequest extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $customer_id = null;
    
    /**  @var string */
    public $card_id = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.DeleteCustomerCardRequest');

      // REQUIRED STRING location_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // REQUIRED STRING customer_id = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "customer_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // REQUIRED STRING card_id = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "card_id";
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
     * @return \squareup\connect\v3\actions\DeleteCustomerCardRequest
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
     * @return \squareup\connect\v3\actions\DeleteCustomerCardRequest
     */
    public function setLocationId( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <customer_id> has a value
     *
     * @return boolean
     */
    public function hasCustomerId(){
      return $this->_has(2);
    }
    
    /**
     * Clear <customer_id> value
     *
     * @return \squareup\connect\v3\actions\DeleteCustomerCardRequest
     */
    public function clearCustomerId(){
      return $this->_clear(2);
    }
    
    /**
     * Get <customer_id> value
     *
     * @return string
     */
    public function getCustomerId(){
      return $this->_get(2);
    }
    
    /**
     * Set <customer_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\DeleteCustomerCardRequest
     */
    public function setCustomerId( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <card_id> has a value
     *
     * @return boolean
     */
    public function hasCardId(){
      return $this->_has(3);
    }
    
    /**
     * Clear <card_id> value
     *
     * @return \squareup\connect\v3\actions\DeleteCustomerCardRequest
     */
    public function clearCardId(){
      return $this->_clear(3);
    }
    
    /**
     * Get <card_id> value
     *
     * @return string
     */
    public function getCardId(){
      return $this->_get(3);
    }
    
    /**
     * Set <card_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\DeleteCustomerCardRequest
     */
    public function setCardId( $value){
      return $this->_set(3, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class DeleteCustomerCardResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.DeleteCustomerCardResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
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
     * @return \squareup\connect\v3\actions\DeleteCustomerCardResponse
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
     * @return \squareup\connect\v3\actions\DeleteCustomerCardResponse
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
     * @return \squareup\connect\v3\actions\DeleteCustomerCardResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
  }
}

