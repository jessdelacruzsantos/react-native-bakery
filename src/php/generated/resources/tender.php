<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/resources/tender.proto
//   Date: 2015-11-12 23:33:11

namespace squareup\connect\v3\resources\Tender\Charge {

  class Status extends \DrSlump\Protobuf\Enum {
    const UNKNOWN = 0;
    const AUTHORIZED = 1;
    const CAPTURED = 2;
    const VOIDED = 3;
    const FAILED = 4;
  }
}
namespace squareup\connect\v3\resources\Tender {

  class Charge extends \DrSlump\Protobuf\Message {

    /**  @var int - \squareup\connect\v3\resources\Tender\Charge\Status */
    public $status = null;
    
    /**  @var \squareup\connect\v3\resources\Card */
    public $card = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.resources.Tender.Charge');

      // OPTIONAL ENUM status = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "status";
      $f->type      = \DrSlump\Protobuf::TYPE_ENUM;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Tender\Charge\Status';
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
     * Check if <status> has a value
     *
     * @return boolean
     */
    public function hasStatus(){
      return $this->_has(1);
    }
    
    /**
     * Clear <status> value
     *
     * @return \squareup\connect\v3\resources\Tender\Charge
     */
    public function clearStatus(){
      return $this->_clear(1);
    }
    
    /**
     * Get <status> value
     *
     * @return int - \squareup\connect\v3\resources\Tender\Charge\Status
     */
    public function getStatus(){
      return $this->_get(1);
    }
    
    /**
     * Set <status> value
     *
     * @param int - \squareup\connect\v3\resources\Tender\Charge\Status $value
     * @return \squareup\connect\v3\resources\Tender\Charge
     */
    public function setStatus( $value){
      return $this->_set(1, $value);
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
     * @return \squareup\connect\v3\resources\Tender\Charge
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
     * @return \squareup\connect\v3\resources\Tender\Charge
     */
    public function setCard(\squareup\connect\v3\resources\Card $value){
      return $this->_set(2, $value);
    }
  }
}

namespace squareup\connect\v3\resources {

  class Tender extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $id = null;
    
    /**  @var string */
    public $business_id = null;
    
    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $transaction_id = null;
    
    /**  @var string */
    public $created_at = null;
    
    /**  @var string */
    public $note = null;
    
    /**  @var \squareup\connect\v3\resources\Money */
    public $amount_money = null;
    
    /**  @var string */
    public $customer_id = null;
    
    /**  @var \squareup\connect\v3\resources\Tender\Charge */
    public $charge = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.resources.Tender');

      // OPTIONAL STRING id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING business_id = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "business_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING location_id = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "location_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING transaction_id = 4
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 4;
      $f->name      = "transaction_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING created_at = 5
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 5;
      $f->name      = "created_at";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING note = 6
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 6;
      $f->name      = "note";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL MESSAGE amount_money = 7
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 7;
      $f->name      = "amount_money";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Money';
      $descriptor->addField($f);

      // OPTIONAL STRING customer_id = 8
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 8;
      $f->name      = "customer_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL MESSAGE charge = 9
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 9;
      $f->name      = "charge";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Tender\Charge';
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <id> has a value
     *
     * @return boolean
     */
    public function hasId(){
      return $this->_has(1);
    }
    
    /**
     * Clear <id> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearId(){
      return $this->_clear(1);
    }
    
    /**
     * Get <id> value
     *
     * @return string
     */
    public function getId(){
      return $this->_get(1);
    }
    
    /**
     * Set <id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setId( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <business_id> has a value
     *
     * @return boolean
     */
    public function hasBusinessId(){
      return $this->_has(2);
    }
    
    /**
     * Clear <business_id> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearBusinessId(){
      return $this->_clear(2);
    }
    
    /**
     * Get <business_id> value
     *
     * @return string
     */
    public function getBusinessId(){
      return $this->_get(2);
    }
    
    /**
     * Set <business_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setBusinessId( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <location_id> has a value
     *
     * @return boolean
     */
    public function hasLocationId(){
      return $this->_has(3);
    }
    
    /**
     * Clear <location_id> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearLocationId(){
      return $this->_clear(3);
    }
    
    /**
     * Get <location_id> value
     *
     * @return string
     */
    public function getLocationId(){
      return $this->_get(3);
    }
    
    /**
     * Set <location_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setLocationId( $value){
      return $this->_set(3, $value);
    }
    
    /**
     * Check if <transaction_id> has a value
     *
     * @return boolean
     */
    public function hasTransactionId(){
      return $this->_has(4);
    }
    
    /**
     * Clear <transaction_id> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearTransactionId(){
      return $this->_clear(4);
    }
    
    /**
     * Get <transaction_id> value
     *
     * @return string
     */
    public function getTransactionId(){
      return $this->_get(4);
    }
    
    /**
     * Set <transaction_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setTransactionId( $value){
      return $this->_set(4, $value);
    }
    
    /**
     * Check if <created_at> has a value
     *
     * @return boolean
     */
    public function hasCreatedAt(){
      return $this->_has(5);
    }
    
    /**
     * Clear <created_at> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearCreatedAt(){
      return $this->_clear(5);
    }
    
    /**
     * Get <created_at> value
     *
     * @return string
     */
    public function getCreatedAt(){
      return $this->_get(5);
    }
    
    /**
     * Set <created_at> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setCreatedAt( $value){
      return $this->_set(5, $value);
    }
    
    /**
     * Check if <note> has a value
     *
     * @return boolean
     */
    public function hasNote(){
      return $this->_has(6);
    }
    
    /**
     * Clear <note> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearNote(){
      return $this->_clear(6);
    }
    
    /**
     * Get <note> value
     *
     * @return string
     */
    public function getNote(){
      return $this->_get(6);
    }
    
    /**
     * Set <note> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setNote( $value){
      return $this->_set(6, $value);
    }
    
    /**
     * Check if <amount_money> has a value
     *
     * @return boolean
     */
    public function hasAmountMoney(){
      return $this->_has(7);
    }
    
    /**
     * Clear <amount_money> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearAmountMoney(){
      return $this->_clear(7);
    }
    
    /**
     * Get <amount_money> value
     *
     * @return \squareup\connect\v3\resources\Money
     */
    public function getAmountMoney(){
      return $this->_get(7);
    }
    
    /**
     * Set <amount_money> value
     *
     * @param \squareup\connect\v3\resources\Money $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setAmountMoney(\squareup\connect\v3\resources\Money $value){
      return $this->_set(7, $value);
    }
    
    /**
     * Check if <customer_id> has a value
     *
     * @return boolean
     */
    public function hasCustomerId(){
      return $this->_has(8);
    }
    
    /**
     * Clear <customer_id> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearCustomerId(){
      return $this->_clear(8);
    }
    
    /**
     * Get <customer_id> value
     *
     * @return string
     */
    public function getCustomerId(){
      return $this->_get(8);
    }
    
    /**
     * Set <customer_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setCustomerId( $value){
      return $this->_set(8, $value);
    }
    
    /**
     * Check if <charge> has a value
     *
     * @return boolean
     */
    public function hasCharge(){
      return $this->_has(9);
    }
    
    /**
     * Clear <charge> value
     *
     * @return \squareup\connect\v3\resources\Tender
     */
    public function clearCharge(){
      return $this->_clear(9);
    }
    
    /**
     * Get <charge> value
     *
     * @return \squareup\connect\v3\resources\Tender\Charge
     */
    public function getCharge(){
      return $this->_get(9);
    }
    
    /**
     * Set <charge> value
     *
     * @param \squareup\connect\v3\resources\Tender\Charge $value
     * @return \squareup\connect\v3\resources\Tender
     */
    public function setCharge(\squareup\connect\v3\resources\Tender\Charge $value){
      return $this->_set(9, $value);
    }
  }
}

