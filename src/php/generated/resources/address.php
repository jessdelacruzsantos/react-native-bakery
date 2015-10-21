<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/resources/address.proto
//   Date: 2015-10-21 00:07:16

namespace squareup\connect\v3\resources {

  class Address extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $line_1 = null;
    
    /**  @var string */
    public $line_2 = null;
    
    /**  @var string */
    public $line_3 = null;
    
    /**  @var string */
    public $locality = null;
    
    /**  @var string */
    public $sublocality = null;
    
    /**  @var string */
    public $sublocality_2 = null;
    
    /**  @var string */
    public $sublocality_3 = null;
    
    /**  @var string */
    public $region = null;
    
    /**  @var string */
    public $region_2 = null;
    
    /**  @var string */
    public $region_3 = null;
    
    /**  @var string */
    public $postal_code = null;
    
    /**  @var string */
    public $country_code = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.resources.Address');

      // OPTIONAL STRING line_1 = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "line_1";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING line_2 = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "line_2";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING line_3 = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "line_3";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING locality = 6
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 6;
      $f->name      = "locality";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING sublocality = 7
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 7;
      $f->name      = "sublocality";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING sublocality_2 = 8
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 8;
      $f->name      = "sublocality_2";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING sublocality_3 = 9
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 9;
      $f->name      = "sublocality_3";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING region = 13
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 13;
      $f->name      = "region";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING region_2 = 14
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 14;
      $f->name      = "region_2";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING region_3 = 15
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 15;
      $f->name      = "region_3";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING postal_code = 16
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 16;
      $f->name      = "postal_code";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL STRING country_code = 17
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 17;
      $f->name      = "country_code";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <line_1> has a value
     *
     * @return boolean
     */
    public function hasLine1(){
      return $this->_has(1);
    }
    
    /**
     * Clear <line_1> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearLine1(){
      return $this->_clear(1);
    }
    
    /**
     * Get <line_1> value
     *
     * @return string
     */
    public function getLine1(){
      return $this->_get(1);
    }
    
    /**
     * Set <line_1> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setLine1( $value){
      return $this->_set(1, $value);
    }
    
    /**
     * Check if <line_2> has a value
     *
     * @return boolean
     */
    public function hasLine2(){
      return $this->_has(2);
    }
    
    /**
     * Clear <line_2> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearLine2(){
      return $this->_clear(2);
    }
    
    /**
     * Get <line_2> value
     *
     * @return string
     */
    public function getLine2(){
      return $this->_get(2);
    }
    
    /**
     * Set <line_2> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setLine2( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <line_3> has a value
     *
     * @return boolean
     */
    public function hasLine3(){
      return $this->_has(3);
    }
    
    /**
     * Clear <line_3> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearLine3(){
      return $this->_clear(3);
    }
    
    /**
     * Get <line_3> value
     *
     * @return string
     */
    public function getLine3(){
      return $this->_get(3);
    }
    
    /**
     * Set <line_3> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setLine3( $value){
      return $this->_set(3, $value);
    }
    
    /**
     * Check if <locality> has a value
     *
     * @return boolean
     */
    public function hasLocality(){
      return $this->_has(6);
    }
    
    /**
     * Clear <locality> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearLocality(){
      return $this->_clear(6);
    }
    
    /**
     * Get <locality> value
     *
     * @return string
     */
    public function getLocality(){
      return $this->_get(6);
    }
    
    /**
     * Set <locality> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setLocality( $value){
      return $this->_set(6, $value);
    }
    
    /**
     * Check if <sublocality> has a value
     *
     * @return boolean
     */
    public function hasSublocality(){
      return $this->_has(7);
    }
    
    /**
     * Clear <sublocality> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearSublocality(){
      return $this->_clear(7);
    }
    
    /**
     * Get <sublocality> value
     *
     * @return string
     */
    public function getSublocality(){
      return $this->_get(7);
    }
    
    /**
     * Set <sublocality> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setSublocality( $value){
      return $this->_set(7, $value);
    }
    
    /**
     * Check if <sublocality_2> has a value
     *
     * @return boolean
     */
    public function hasSublocality2(){
      return $this->_has(8);
    }
    
    /**
     * Clear <sublocality_2> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearSublocality2(){
      return $this->_clear(8);
    }
    
    /**
     * Get <sublocality_2> value
     *
     * @return string
     */
    public function getSublocality2(){
      return $this->_get(8);
    }
    
    /**
     * Set <sublocality_2> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setSublocality2( $value){
      return $this->_set(8, $value);
    }
    
    /**
     * Check if <sublocality_3> has a value
     *
     * @return boolean
     */
    public function hasSublocality3(){
      return $this->_has(9);
    }
    
    /**
     * Clear <sublocality_3> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearSublocality3(){
      return $this->_clear(9);
    }
    
    /**
     * Get <sublocality_3> value
     *
     * @return string
     */
    public function getSublocality3(){
      return $this->_get(9);
    }
    
    /**
     * Set <sublocality_3> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setSublocality3( $value){
      return $this->_set(9, $value);
    }
    
    /**
     * Check if <region> has a value
     *
     * @return boolean
     */
    public function hasRegion(){
      return $this->_has(13);
    }
    
    /**
     * Clear <region> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearRegion(){
      return $this->_clear(13);
    }
    
    /**
     * Get <region> value
     *
     * @return string
     */
    public function getRegion(){
      return $this->_get(13);
    }
    
    /**
     * Set <region> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setRegion( $value){
      return $this->_set(13, $value);
    }
    
    /**
     * Check if <region_2> has a value
     *
     * @return boolean
     */
    public function hasRegion2(){
      return $this->_has(14);
    }
    
    /**
     * Clear <region_2> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearRegion2(){
      return $this->_clear(14);
    }
    
    /**
     * Get <region_2> value
     *
     * @return string
     */
    public function getRegion2(){
      return $this->_get(14);
    }
    
    /**
     * Set <region_2> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setRegion2( $value){
      return $this->_set(14, $value);
    }
    
    /**
     * Check if <region_3> has a value
     *
     * @return boolean
     */
    public function hasRegion3(){
      return $this->_has(15);
    }
    
    /**
     * Clear <region_3> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearRegion3(){
      return $this->_clear(15);
    }
    
    /**
     * Get <region_3> value
     *
     * @return string
     */
    public function getRegion3(){
      return $this->_get(15);
    }
    
    /**
     * Set <region_3> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setRegion3( $value){
      return $this->_set(15, $value);
    }
    
    /**
     * Check if <postal_code> has a value
     *
     * @return boolean
     */
    public function hasPostalCode(){
      return $this->_has(16);
    }
    
    /**
     * Clear <postal_code> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearPostalCode(){
      return $this->_clear(16);
    }
    
    /**
     * Get <postal_code> value
     *
     * @return string
     */
    public function getPostalCode(){
      return $this->_get(16);
    }
    
    /**
     * Set <postal_code> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setPostalCode( $value){
      return $this->_set(16, $value);
    }
    
    /**
     * Check if <country_code> has a value
     *
     * @return boolean
     */
    public function hasCountryCode(){
      return $this->_has(17);
    }
    
    /**
     * Clear <country_code> value
     *
     * @return \squareup\connect\v3\resources\Address
     */
    public function clearCountryCode(){
      return $this->_clear(17);
    }
    
    /**
     * Get <country_code> value
     *
     * @return string
     */
    public function getCountryCode(){
      return $this->_get(17);
    }
    
    /**
     * Set <country_code> value
     *
     * @param string $value
     * @return \squareup\connect\v3\resources\Address
     */
    public function setCountryCode( $value){
      return $this->_set(17, $value);
    }
  }
}

