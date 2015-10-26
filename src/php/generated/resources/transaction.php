<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/resources/transaction.proto
//   Date: 2015-10-26 23:23:57

namespace squareup\connect\v3\resources {

  class TransactionGroup extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $id = null;
    
    /**  @var string */
    public $business_id = null;
    
    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $created_at = null;
    
    /**  @var \squareup\connect\v3\resources\Transaction[]  */
    public $transactions = array();
    
    /**  @var string */
    public $reference_id = null;
    
    /**  @var \squareup\connect\v3\resources\Tender[]  */
    public $tenders = array();
    
    /**  @var \squareup\connect\v3\resources\Refund[]  */
    public $refunds = array();
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.resources.TransactionGroup');

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

      // OPTIONAL STRING created_at = 4
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 4;
      $f->name      = "created_at";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // REPEATED MESSAGE transactions = 5
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 5;
      $f->name      = "transactions";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Transaction';
      $descriptor->addField($f);

      // OPTIONAL STRING reference_id = 6
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 6;
      $f->name      = "reference_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // REPEATED MESSAGE tenders = 7
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 7;
      $f->name      = "tenders";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Tender';
      $descriptor->addField($f);

      // REPEATED MESSAGE refunds = 8
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 8;
      $f->name      = "refunds";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Refund';
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
     * @return \squareup\connect\v3\resources\TransactionGroup
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
     * @return \squareup\connect\v3\resources\TransactionGroup
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
     * @return \squareup\connect\v3\resources\TransactionGroup
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
     * @return \squareup\connect\v3\resources\TransactionGroup
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
     * @return \squareup\connect\v3\resources\TransactionGroup
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
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function setLocationId( $value){
      return $this->_set(3, $value);
    }
    
    /**
     * Check if <created_at> has a value
     *
     * @return boolean
     */
    public function hasCreatedAt(){
      return $this->_has(4);
    }
    
    /**
     * Clear <created_at> value
     *
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function clearCreatedAt(){
      return $this->_clear(4);
    }
    
    /**
     * Get <created_at> value
     *
     * @return string
     */
    public function getCreatedAt(){
      return $this->_get(4);
    }
    
    /**
     * Set <created_at> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function setCreatedAt( $value){
      return $this->_set(4, $value);
    }
    
    /**
     * Check if <transactions> has a value
     *
     * @return boolean
     */
    public function hasTransactions(){
      return $this->_has(5);
    }
    
    /**
     * Clear <transactions> value
     *
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function clearTransactions(){
      return $this->_clear(5);
    }
    
    /**
     * Get <transactions> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function getTransactions($idx = NULL){
      return $this->_get(5, $idx);
    }
    
    /**
     * Set <transactions> value
     *
     * @param \squareup\connect\v3\resources\Transaction $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function setTransactions(\squareup\connect\v3\resources\Transaction $value, $idx = NULL){
      return $this->_set(5, $value, $idx);
    }
    
    /**
     * Get all elements of <transactions>
     *
     * @return \squareup\connect\v3\resources\Transaction[]
     */
    public function getTransactionsList(){
     return $this->_get(5);
    }
    
    /**
     * Add a new element to <transactions>
     *
     * @param \squareup\connect\v3\resources\Transaction $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function addTransactions(\squareup\connect\v3\resources\Transaction $value){
     return $this->_add(5, $value);
    }
    
    /**
     * Check if <reference_id> has a value
     *
     * @return boolean
     */
    public function hasReferenceId(){
      return $this->_has(6);
    }
    
    /**
     * Clear <reference_id> value
     *
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function clearReferenceId(){
      return $this->_clear(6);
    }
    
    /**
     * Get <reference_id> value
     *
     * @return string
     */
    public function getReferenceId(){
      return $this->_get(6);
    }
    
    /**
     * Set <reference_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function setReferenceId( $value){
      return $this->_set(6, $value);
    }
    
    /**
     * Check if <tenders> has a value
     *
     * @return boolean
     */
    public function hasTenders(){
      return $this->_has(7);
    }
    
    /**
     * Clear <tenders> value
     *
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function clearTenders(){
      return $this->_clear(7);
    }
    
    /**
     * Get <tenders> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Tender
     */
    public function getTenders($idx = NULL){
      return $this->_get(7, $idx);
    }
    
    /**
     * Set <tenders> value
     *
     * @param \squareup\connect\v3\resources\Tender $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function setTenders(\squareup\connect\v3\resources\Tender $value, $idx = NULL){
      return $this->_set(7, $value, $idx);
    }
    
    /**
     * Get all elements of <tenders>
     *
     * @return \squareup\connect\v3\resources\Tender[]
     */
    public function getTendersList(){
     return $this->_get(7);
    }
    
    /**
     * Add a new element to <tenders>
     *
     * @param \squareup\connect\v3\resources\Tender $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function addTenders(\squareup\connect\v3\resources\Tender $value){
     return $this->_add(7, $value);
    }
    
    /**
     * Check if <refunds> has a value
     *
     * @return boolean
     */
    public function hasRefunds(){
      return $this->_has(8);
    }
    
    /**
     * Clear <refunds> value
     *
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function clearRefunds(){
      return $this->_clear(8);
    }
    
    /**
     * Get <refunds> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Refund
     */
    public function getRefunds($idx = NULL){
      return $this->_get(8, $idx);
    }
    
    /**
     * Set <refunds> value
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function setRefunds(\squareup\connect\v3\resources\Refund $value, $idx = NULL){
      return $this->_set(8, $value, $idx);
    }
    
    /**
     * Get all elements of <refunds>
     *
     * @return \squareup\connect\v3\resources\Refund[]
     */
    public function getRefundsList(){
     return $this->_get(8);
    }
    
    /**
     * Add a new element to <refunds>
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\resources\TransactionGroup
     */
    public function addRefunds(\squareup\connect\v3\resources\Refund $value){
     return $this->_add(8, $value);
    }
  }
}

namespace squareup\connect\v3\resources\Transaction {

  class Type extends \DrSlump\Protobuf\Enum {
    const UNKNOWN = 0;
    const PAYMENT_BOOKED = 1;
    const PAYMENT_PENDING = 2;
    const PAYMENT_FAILED = 3;
    const PAYMENT_CANCELED = 4;
    const REFUND = 5;
  }
}
namespace squareup\connect\v3\resources {

  class Transaction extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $id = null;
    
    /**  @var string */
    public $business_id = null;
    
    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $transaction_group_id = null;
    
    /**  @var int - \squareup\connect\v3\resources\Transaction\Type */
    public $type = null;
    
    /**  @var string */
    public $created_at = null;
    
    /**  @var \squareup\connect\v3\resources\Tender[]  */
    public $tenders = array();
    
    /**  @var \squareup\connect\v3\resources\Refund[]  */
    public $refunds = array();
    
    /**  @var string */
    public $reference_id = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.resources.Transaction');

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

      // OPTIONAL STRING transaction_group_id = 4
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 4;
      $f->name      = "transaction_group_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL ENUM type = 5
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 5;
      $f->name      = "type";
      $f->type      = \DrSlump\Protobuf::TYPE_ENUM;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Transaction\Type';
      $descriptor->addField($f);

      // OPTIONAL STRING created_at = 6
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 6;
      $f->name      = "created_at";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // REPEATED MESSAGE tenders = 7
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 7;
      $f->name      = "tenders";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Tender';
      $descriptor->addField($f);

      // REPEATED MESSAGE refunds = 8
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 8;
      $f->name      = "refunds";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Refund';
      $descriptor->addField($f);

      // OPTIONAL STRING reference_id = 9
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 9;
      $f->name      = "reference_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
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
     * @return \squareup\connect\v3\resources\Transaction
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
     * @return \squareup\connect\v3\resources\Transaction
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
     * @return \squareup\connect\v3\resources\Transaction
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
     * @return \squareup\connect\v3\resources\Transaction
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
     * @return \squareup\connect\v3\resources\Transaction
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
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function setLocationId( $value){
      return $this->_set(3, $value);
    }
    
    /**
     * Check if <transaction_group_id> has a value
     *
     * @return boolean
     */
    public function hasTransactionGroupId(){
      return $this->_has(4);
    }
    
    /**
     * Clear <transaction_group_id> value
     *
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function clearTransactionGroupId(){
      return $this->_clear(4);
    }
    
    /**
     * Get <transaction_group_id> value
     *
     * @return string
     */
    public function getTransactionGroupId(){
      return $this->_get(4);
    }
    
    /**
     * Set <transaction_group_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function setTransactionGroupId( $value){
      return $this->_set(4, $value);
    }
    
    /**
     * Check if <type> has a value
     *
     * @return boolean
     */
    public function hasType(){
      return $this->_has(5);
    }
    
    /**
     * Clear <type> value
     *
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function clearType(){
      return $this->_clear(5);
    }
    
    /**
     * Get <type> value
     *
     * @return int - \squareup\connect\v3\resources\Transaction\Type
     */
    public function getType(){
      return $this->_get(5);
    }
    
    /**
     * Set <type> value
     *
     * @param int - \squareup\connect\v3\resources\Transaction\Type $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function setType( $value){
      return $this->_set(5, $value);
    }
    
    /**
     * Check if <created_at> has a value
     *
     * @return boolean
     */
    public function hasCreatedAt(){
      return $this->_has(6);
    }
    
    /**
     * Clear <created_at> value
     *
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function clearCreatedAt(){
      return $this->_clear(6);
    }
    
    /**
     * Get <created_at> value
     *
     * @return string
     */
    public function getCreatedAt(){
      return $this->_get(6);
    }
    
    /**
     * Set <created_at> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function setCreatedAt( $value){
      return $this->_set(6, $value);
    }
    
    /**
     * Check if <tenders> has a value
     *
     * @return boolean
     */
    public function hasTenders(){
      return $this->_has(7);
    }
    
    /**
     * Clear <tenders> value
     *
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function clearTenders(){
      return $this->_clear(7);
    }
    
    /**
     * Get <tenders> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Tender
     */
    public function getTenders($idx = NULL){
      return $this->_get(7, $idx);
    }
    
    /**
     * Set <tenders> value
     *
     * @param \squareup\connect\v3\resources\Tender $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function setTenders(\squareup\connect\v3\resources\Tender $value, $idx = NULL){
      return $this->_set(7, $value, $idx);
    }
    
    /**
     * Get all elements of <tenders>
     *
     * @return \squareup\connect\v3\resources\Tender[]
     */
    public function getTendersList(){
     return $this->_get(7);
    }
    
    /**
     * Add a new element to <tenders>
     *
     * @param \squareup\connect\v3\resources\Tender $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function addTenders(\squareup\connect\v3\resources\Tender $value){
     return $this->_add(7, $value);
    }
    
    /**
     * Check if <refunds> has a value
     *
     * @return boolean
     */
    public function hasRefunds(){
      return $this->_has(8);
    }
    
    /**
     * Clear <refunds> value
     *
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function clearRefunds(){
      return $this->_clear(8);
    }
    
    /**
     * Get <refunds> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Refund
     */
    public function getRefunds($idx = NULL){
      return $this->_get(8, $idx);
    }
    
    /**
     * Set <refunds> value
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function setRefunds(\squareup\connect\v3\resources\Refund $value, $idx = NULL){
      return $this->_set(8, $value, $idx);
    }
    
    /**
     * Get all elements of <refunds>
     *
     * @return \squareup\connect\v3\resources\Refund[]
     */
    public function getRefundsList(){
     return $this->_get(8);
    }
    
    /**
     * Add a new element to <refunds>
     *
     * @param \squareup\connect\v3\resources\Refund $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function addRefunds(\squareup\connect\v3\resources\Refund $value){
     return $this->_add(8, $value);
    }
    
    /**
     * Check if <reference_id> has a value
     *
     * @return boolean
     */
    public function hasReferenceId(){
      return $this->_has(9);
    }
    
    /**
     * Clear <reference_id> value
     *
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function clearReferenceId(){
      return $this->_clear(9);
    }
    
    /**
     * Get <reference_id> value
     *
     * @return string
     */
    public function getReferenceId(){
      return $this->_get(9);
    }
    
    /**
     * Set <reference_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Transaction
     */
    public function setReferenceId( $value){
      return $this->_set(9, $value);
    }
  }
}

