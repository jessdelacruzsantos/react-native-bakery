<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/resources/customer.proto
//   Date: 2015-11-12 23:33:06

namespace squareup\connect\v3\resources {

  class Customer extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $id = null;
    
    /**  @var string */
    public $business_id = null;
    
    /**  @var string */
    public $location_id = null;
    
    /**  @var string */
    public $created_at = null;
    
    /**  @var string */
    public $updated_at = null;
    
    /**  @var \squareup\connect\v3\resources\Card[]  */
    public $cards = array();
    
    /**  @var string */
    public $given_name = null;
    
    /**  @var string */
    public $family_name = null;
    
    /**  @var string */
    public $nickname = null;
    
    /**  @var string */
    public $email_address = null;
    
    /**  @var \squareup\connect\v3\resources\Address */
    public $address = null;
    
    /**  @var string */
    public $phone_number = null;
    
    /**  @var string */
    public $memo = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.resources.Customer');

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

      // OPTIONAL STRING updated_at = 5
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 5;
      $f->name      = "updated_at";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // REPEATED MESSAGE cards = 6
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 6;
      $f->name      = "cards";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Card';
      $descriptor->addField($f);

      // OPTIONAL STRING given_name = 7
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 7;
      $f->name      = "given_name";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING family_name = 8
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 8;
      $f->name      = "family_name";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING nickname = 9
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 9;
      $f->name      = "nickname";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING email_address = 10
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 10;
      $f->name      = "email_address";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL MESSAGE address = 11
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 11;
      $f->name      = "address";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $f->reference = '\squareup\connect\v3\resources\Address';
      $descriptor->addField($f);

      // OPTIONAL STRING phone_number = 12
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 12;
      $f->name      = "phone_number";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING memo = 13
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 13;
      $f->name      = "memo";
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
     * @return \squareup\connect\v3\resources\Customer
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
     * @return \squareup\connect\v3\resources\Customer
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
     * @return \squareup\connect\v3\resources\Customer
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
     * @return \squareup\connect\v3\resources\Customer
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
     * @return \squareup\connect\v3\resources\Customer
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
     * @return \squareup\connect\v3\resources\Customer
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
     * @return \squareup\connect\v3\resources\Customer
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
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setCreatedAt( $value){
      return $this->_set(4, $value);
    }
    
    /**
     * Check if <updated_at> has a value
     *
     * @return boolean
     */
    public function hasUpdatedAt(){
      return $this->_has(5);
    }
    
    /**
     * Clear <updated_at> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearUpdatedAt(){
      return $this->_clear(5);
    }
    
    /**
     * Get <updated_at> value
     *
     * @return string
     */
    public function getUpdatedAt(){
      return $this->_get(5);
    }
    
    /**
     * Set <updated_at> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setUpdatedAt( $value){
      return $this->_set(5, $value);
    }
    
    /**
     * Check if <cards> has a value
     *
     * @return boolean
     */
    public function hasCards(){
      return $this->_has(6);
    }
    
    /**
     * Clear <cards> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearCards(){
      return $this->_clear(6);
    }
    
    /**
     * Get <cards> value
     *
     * @param int $idx
     * @return \squareup\connect\v3\resources\Card
     */
    public function getCards($idx = NULL){
      return $this->_get(6, $idx);
    }
    
    /**
     * Set <cards> value
     *
     * @param \squareup\connect\v3\resources\Card $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setCards(\squareup\connect\v3\resources\Card $value, $idx = NULL){
      return $this->_set(6, $value, $idx);
    }
    
    /**
     * Get all elements of <cards>
     *
     * @return \squareup\connect\v3\resources\Card[]
     */
    public function getCardsList(){
     return $this->_get(6);
    }
    
    /**
     * Add a new element to <cards>
     *
     * @param \squareup\connect\v3\resources\Card $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function addCards(\squareup\connect\v3\resources\Card $value){
     return $this->_add(6, $value);
    }
    
    /**
     * Check if <given_name> has a value
     *
     * @return boolean
     */
    public function hasGivenName(){
      return $this->_has(7);
    }
    
    /**
     * Clear <given_name> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearGivenName(){
      return $this->_clear(7);
    }
    
    /**
     * Get <given_name> value
     *
     * @return string
     */
    public function getGivenName(){
      return $this->_get(7);
    }
    
    /**
     * Set <given_name> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setGivenName( $value){
      return $this->_set(7, $value);
    }
    
    /**
     * Check if <family_name> has a value
     *
     * @return boolean
     */
    public function hasFamilyName(){
      return $this->_has(8);
    }
    
    /**
     * Clear <family_name> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearFamilyName(){
      return $this->_clear(8);
    }
    
    /**
     * Get <family_name> value
     *
     * @return string
     */
    public function getFamilyName(){
      return $this->_get(8);
    }
    
    /**
     * Set <family_name> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setFamilyName( $value){
      return $this->_set(8, $value);
    }
    
    /**
     * Check if <nickname> has a value
     *
     * @return boolean
     */
    public function hasNickname(){
      return $this->_has(9);
    }
    
    /**
     * Clear <nickname> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearNickname(){
      return $this->_clear(9);
    }
    
    /**
     * Get <nickname> value
     *
     * @return string
     */
    public function getNickname(){
      return $this->_get(9);
    }
    
    /**
     * Set <nickname> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setNickname( $value){
      return $this->_set(9, $value);
    }
    
    /**
     * Check if <email_address> has a value
     *
     * @return boolean
     */
    public function hasEmailAddress(){
      return $this->_has(10);
    }
    
    /**
     * Clear <email_address> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearEmailAddress(){
      return $this->_clear(10);
    }
    
    /**
     * Get <email_address> value
     *
     * @return string
     */
    public function getEmailAddress(){
      return $this->_get(10);
    }
    
    /**
     * Set <email_address> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setEmailAddress( $value){
      return $this->_set(10, $value);
    }
    
    /**
     * Check if <address> has a value
     *
     * @return boolean
     */
    public function hasAddress(){
      return $this->_has(11);
    }
    
    /**
     * Clear <address> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearAddress(){
      return $this->_clear(11);
    }
    
    /**
     * Get <address> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function getAddress(){
      return $this->_get(11);
    }
    
    /**
     * Set <address> value
     *
     * @param \squareup\connect\v3\resources\Address $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setAddress(\squareup\connect\v3\resources\Address $value){
      return $this->_set(11, $value);
    }
    
    /**
     * Check if <phone_number> has a value
     *
     * @return boolean
     */
    public function hasPhoneNumber(){
      return $this->_has(12);
    }
    
    /**
     * Clear <phone_number> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearPhoneNumber(){
      return $this->_clear(12);
    }
    
    /**
     * Get <phone_number> value
     *
     * @return string
     */
    public function getPhoneNumber(){
      return $this->_get(12);
    }
    
    /**
     * Set <phone_number> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setPhoneNumber( $value){
      return $this->_set(12, $value);
    }
    
    /**
     * Check if <memo> has a value
     *
     * @return boolean
     */
    public function hasMemo(){
      return $this->_has(13);
    }
    
    /**
     * Clear <memo> value
     *
     * @return \squareup\connect\v3\resources\Customer
     */
    public function clearMemo(){
      return $this->_clear(13);
    }
    
    /**
     * Get <memo> value
     *
     * @return string
     */
    public function getMemo(){
      return $this->_get(13);
    }
    
    /**
     * Set <memo> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Customer
     */
    public function setMemo( $value){
      return $this->_set(13, $value);
    }
  }
}

