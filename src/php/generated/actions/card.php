<?php
// DO NOT EDIT! Generated by Protobuf-PHP protoc plugin @package_version@
// Source: squareup/connect/v3/actions/card.proto
//   Date: 2015-10-27 19:47:00

namespace squareup\connect\v3\actions {

  class CreateCardRequest extends \DrSlump\Protobuf\Message {

    /**  @var string */
    public $client_id = null;
    
    /**  @var string */
    public $business_id = null;
    
    /**  @var \squareup\connect\v3\resources\CardData */
    public $card_data = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.CreateCardRequest');

      // REQUIRED STRING client_id = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "client_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $descriptor->addField($f);

      // OPTIONAL STRING business_id = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "business_id";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // REQUIRED MESSAGE card_data = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
      $f->name      = "card_data";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REQUIRED;
      $f->reference = '\squareup\connect\v3\resources\CardData';
      $descriptor->addField($f);

      foreach (self::$__extensions as $cb) {
        $descriptor->addField($cb(), true);
      }

      return $descriptor;
    }

    /**
     * Check if <client_id> has a value
     *
     * @return boolean
     */
    public function hasClientId(){
      return $this->_has(1);
    }
    
    /**
     * Clear <client_id> value
     *
     * @return \squareup\connect\v3\actions\CreateCardRequest
     */
    public function clearClientId(){
      return $this->_clear(1);
    }
    
    /**
     * Get <client_id> value
     *
     * @return string
     */
    public function getClientId(){
      return $this->_get(1);
    }
    
    /**
     * Set <client_id> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\CreateCardRequest
     */
    public function setClientId( $value){
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
     * @return \squareup\connect\v3\actions\CreateCardRequest
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
     * @return \squareup\connect\v3\actions\CreateCardRequest
     */
    public function setBusinessId( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <card_data> has a value
     *
     * @return boolean
     */
    public function hasCardData(){
      return $this->_has(3);
    }
    
    /**
     * Clear <card_data> value
     *
     * @return \squareup\connect\v3\actions\CreateCardRequest
     */
    public function clearCardData(){
      return $this->_clear(3);
    }
    
    /**
     * Get <card_data> value
     *
     * @return \squareup\connect\v3\resources\CardData
     */
    public function getCardData(){
      return $this->_get(3);
    }
    
    /**
     * Set <card_data> value
     *
     * @param \squareup\connect\v3\resources\CardData $value
     * @return \squareup\connect\v3\actions\CreateCardRequest
     */
    public function setCardData(\squareup\connect\v3\resources\CardData $value){
      return $this->_set(3, $value);
    }
  }
}

namespace squareup\connect\v3\actions {

  class CreateCardResponse extends \DrSlump\Protobuf\Message {

    /**  @var \squareup\connect\v3\resources\Error[]  */
    public $errors = array();
    
    /**  @var string */
    public $card_nonce = null;
    
    /**  @var \squareup\connect\v3\resources\Card */
    public $card = null;
    

    /** @var \Closure[] */
    protected static $__extensions = array();

    public static function descriptor()
    {
      $descriptor = new \DrSlump\Protobuf\Descriptor(__CLASS__, 'squareup.connect.v3.actions.CreateCardResponse');

      // REPEATED MESSAGE errors = 1
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 1;
      $f->name      = "errors";
      $f->type      = \DrSlump\Protobuf::TYPE_MESSAGE;
      $f->rule      = \DrSlump\Protobuf::RULE_REPEATED;
      $f->reference = '\squareup\connect\v3\resources\Error';
      $descriptor->addField($f);

      // OPTIONAL STRING card_nonce = 2
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 2;
      $f->name      = "card_nonce";
      $f->type      = \DrSlump\Protobuf::TYPE_STRING;
      $f->rule      = \DrSlump\Protobuf::RULE_OPTIONAL;
      $descriptor->addField($f);

      // OPTIONAL MESSAGE card = 3
      $f = new \DrSlump\Protobuf\Field();
      $f->number    = 3;
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
     * @return \squareup\connect\v3\actions\CreateCardResponse
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
     * @return \squareup\connect\v3\actions\CreateCardResponse
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
     * @return \squareup\connect\v3\actions\CreateCardResponse
     */
    public function addErrors(\squareup\connect\v3\resources\Error $value){
     return $this->_add(1, $value);
    }
    
    /**
     * Check if <card_nonce> has a value
     *
     * @return boolean
     */
    public function hasCardNonce(){
      return $this->_has(2);
    }
    
    /**
     * Clear <card_nonce> value
     *
     * @return \squareup\connect\v3\actions\CreateCardResponse
     */
    public function clearCardNonce(){
      return $this->_clear(2);
    }
    
    /**
     * Get <card_nonce> value
     *
     * @return string
     */
    public function getCardNonce(){
      return $this->_get(2);
    }
    
    /**
     * Set <card_nonce> value
     *
     * @param string $value
     * @return \squareup\connect\v3\actions\CreateCardResponse
     */
    public function setCardNonce( $value){
      return $this->_set(2, $value);
    }
    
    /**
     * Check if <card> has a value
     *
     * @return boolean
     */
    public function hasCard(){
      return $this->_has(3);
    }
    
    /**
     * Clear <card> value
     *
     * @return \squareup\connect\v3\actions\CreateCardResponse
     */
    public function clearCard(){
      return $this->_clear(3);
    }
    
    /**
     * Get <card> value
     *
     * @return \squareup\connect\v3\resources\Card
     */
    public function getCard(){
      return $this->_get(3);
    }
    
    /**
     * Set <card> value
     *
     * @param \squareup\connect\v3\resources\Card $value
     * @return \squareup\connect\v3\actions\CreateCardResponse
     */
    public function setCard(\squareup\connect\v3\resources\Card $value){
      return $this->_set(3, $value);
    }
  }
}

