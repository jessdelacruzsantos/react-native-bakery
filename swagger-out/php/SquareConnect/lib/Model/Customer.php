<?php
/**
 * Customer
 *
 * PHP version 5
 *
 * @category Class
 * @package  SquareConnect
 * @author   http://github.com/swagger-api/swagger-codegen
 * @license  http://www.apache.org/licenses/LICENSE-2.0 Apache Licene v2
 * @link     https://github.com/swagger-api/swagger-codegen
 */
/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */

namespace SquareConnect\Model;

use \ArrayAccess;
/**
 * Customer Class Doc Comment
 *
 * @category    Class
 * @description 
 * @package     SquareConnect
 * @author      http://github.com/swagger-api/swagger-codegen
 * @license     http://www.apache.org/licenses/LICENSE-2.0 Apache Licene v2
 * @link        https://github.com/swagger-api/swagger-codegen
 */
class Customer implements ArrayAccess
{
    /**
      * Array of property to type mappings. Used for (de)serialization 
      * @var string[]
      */
    static $swaggerTypes = array(
        'id' => 'string',
        'phone_number' => 'string',
        'updated_at' => 'string',
        'address' => '\SquareConnect\Model\Address',
        'nickname' => 'string',
        'reference_id' => 'string',
        'family_name' => 'string',
        'created_at' => 'string',
        'given_name' => 'string',
        'note' => 'string',
        'cards' => '\SquareConnect\Model\Card[]',
        'email_address' => 'string'
    );
  
    /** 
      * Array of attributes where the key is the local name, and the value is the original name
      * @var string[] 
      */
    static $attributeMap = array(
        'id' => 'id',
        'phone_number' => 'phone_number',
        'updated_at' => 'updated_at',
        'address' => 'address',
        'nickname' => 'nickname',
        'reference_id' => 'reference_id',
        'family_name' => 'family_name',
        'created_at' => 'created_at',
        'given_name' => 'given_name',
        'note' => 'note',
        'cards' => 'cards',
        'email_address' => 'email_address'
    );
  
    /**
      * Array of attributes to setter functions (for deserialization of responses)
      * @var string[]
      */
    static $setters = array(
        'id' => 'setId',
        'phone_number' => 'setPhoneNumber',
        'updated_at' => 'setUpdatedAt',
        'address' => 'setAddress',
        'nickname' => 'setNickname',
        'reference_id' => 'setReferenceId',
        'family_name' => 'setFamilyName',
        'created_at' => 'setCreatedAt',
        'given_name' => 'setGivenName',
        'note' => 'setNote',
        'cards' => 'setCards',
        'email_address' => 'setEmailAddress'
    );
  
    /**
      * Array of attributes to getter functions (for serialization of requests)
      * @var string[]
      */
    static $getters = array(
        'id' => 'getId',
        'phone_number' => 'getPhoneNumber',
        'updated_at' => 'getUpdatedAt',
        'address' => 'getAddress',
        'nickname' => 'getNickname',
        'reference_id' => 'getReferenceId',
        'family_name' => 'getFamilyName',
        'created_at' => 'getCreatedAt',
        'given_name' => 'getGivenName',
        'note' => 'getNote',
        'cards' => 'getCards',
        'email_address' => 'getEmailAddress'
    );
  
    
    /**
      * $id The customer's unique ID.
      * @var string
      */
    protected $id;
    
    /**
      * $phone_number 
      * @var string
      */
    protected $phone_number;
    
    /**
      * $updated_at The time when the customer was updated, in RFC 3339 format.
      * @var string
      */
    protected $updated_at;
    
    /**
      * $address 
      * @var \SquareConnect\Model\Address
      */
    protected $address;
    
    /**
      * $nickname 
      * @var string
      */
    protected $nickname;
    
    /**
      * $reference_id 
      * @var string
      */
    protected $reference_id;
    
    /**
      * $family_name 
      * @var string
      */
    protected $family_name;
    
    /**
      * $created_at The time when the customer was created, in RFC 3339 format.
      * @var string
      */
    protected $created_at;
    
    /**
      * $given_name 
      * @var string
      */
    protected $given_name;
    
    /**
      * $note 
      * @var string
      */
    protected $note;
    
    /**
      * $cards Cards on file for the customer.
      * @var \SquareConnect\Model\Card[]
      */
    protected $cards;
    
    /**
      * $email_address 
      * @var string
      */
    protected $email_address;
    

    /**
     * Constructor
     * @param mixed[] $data Associated array of property value initalizing the model
     */
    public function __construct(array $data = null)
    {
        if ($data != null) {
            $this->id = $data["id"];
            $this->phone_number = $data["phone_number"];
            $this->updated_at = $data["updated_at"];
            $this->address = $data["address"];
            $this->nickname = $data["nickname"];
            $this->reference_id = $data["reference_id"];
            $this->family_name = $data["family_name"];
            $this->created_at = $data["created_at"];
            $this->given_name = $data["given_name"];
            $this->note = $data["note"];
            $this->cards = $data["cards"];
            $this->email_address = $data["email_address"];
        }
    }
    
    /**
     * Gets id
     * @return string
     */
    public function getId()
    {
        return $this->id;
    }
  
    /**
     * Sets id
     * @param string $id The customer's unique ID.
     * @return $this
     */
    public function setId($id)
    {
        
        $this->id = $id;
        return $this;
    }
    
    /**
     * Gets phone_number
     * @return string
     */
    public function getPhoneNumber()
    {
        return $this->phone_number;
    }
  
    /**
     * Sets phone_number
     * @param string $phone_number 
     * @return $this
     */
    public function setPhoneNumber($phone_number)
    {
        
        $this->phone_number = $phone_number;
        return $this;
    }
    
    /**
     * Gets updated_at
     * @return string
     */
    public function getUpdatedAt()
    {
        return $this->updated_at;
    }
  
    /**
     * Sets updated_at
     * @param string $updated_at The time when the customer was updated, in RFC 3339 format.
     * @return $this
     */
    public function setUpdatedAt($updated_at)
    {
        
        $this->updated_at = $updated_at;
        return $this;
    }
    
    /**
     * Gets address
     * @return \SquareConnect\Model\Address
     */
    public function getAddress()
    {
        return $this->address;
    }
  
    /**
     * Sets address
     * @param \SquareConnect\Model\Address $address 
     * @return $this
     */
    public function setAddress($address)
    {
        
        $this->address = $address;
        return $this;
    }
    
    /**
     * Gets nickname
     * @return string
     */
    public function getNickname()
    {
        return $this->nickname;
    }
  
    /**
     * Sets nickname
     * @param string $nickname 
     * @return $this
     */
    public function setNickname($nickname)
    {
        
        $this->nickname = $nickname;
        return $this;
    }
    
    /**
     * Gets reference_id
     * @return string
     */
    public function getReferenceId()
    {
        return $this->reference_id;
    }
  
    /**
     * Sets reference_id
     * @param string $reference_id 
     * @return $this
     */
    public function setReferenceId($reference_id)
    {
        
        $this->reference_id = $reference_id;
        return $this;
    }
    
    /**
     * Gets family_name
     * @return string
     */
    public function getFamilyName()
    {
        return $this->family_name;
    }
  
    /**
     * Sets family_name
     * @param string $family_name 
     * @return $this
     */
    public function setFamilyName($family_name)
    {
        
        $this->family_name = $family_name;
        return $this;
    }
    
    /**
     * Gets created_at
     * @return string
     */
    public function getCreatedAt()
    {
        return $this->created_at;
    }
  
    /**
     * Sets created_at
     * @param string $created_at The time when the customer was created, in RFC 3339 format.
     * @return $this
     */
    public function setCreatedAt($created_at)
    {
        
        $this->created_at = $created_at;
        return $this;
    }
    
    /**
     * Gets given_name
     * @return string
     */
    public function getGivenName()
    {
        return $this->given_name;
    }
  
    /**
     * Sets given_name
     * @param string $given_name 
     * @return $this
     */
    public function setGivenName($given_name)
    {
        
        $this->given_name = $given_name;
        return $this;
    }
    
    /**
     * Gets note
     * @return string
     */
    public function getNote()
    {
        return $this->note;
    }
  
    /**
     * Sets note
     * @param string $note 
     * @return $this
     */
    public function setNote($note)
    {
        
        $this->note = $note;
        return $this;
    }
    
    /**
     * Gets cards
     * @return \SquareConnect\Model\Card[]
     */
    public function getCards()
    {
        return $this->cards;
    }
  
    /**
     * Sets cards
     * @param \SquareConnect\Model\Card[] $cards Cards on file for the customer.
     * @return $this
     */
    public function setCards($cards)
    {
        
        $this->cards = $cards;
        return $this;
    }
    
    /**
     * Gets email_address
     * @return string
     */
    public function getEmailAddress()
    {
        return $this->email_address;
    }
  
    /**
     * Sets email_address
     * @param string $email_address 
     * @return $this
     */
    public function setEmailAddress($email_address)
    {
        
        $this->email_address = $email_address;
        return $this;
    }
    
    /**
     * Returns true if offset exists. False otherwise.
     * @param  integer $offset Offset 
     * @return boolean
     */
    public function offsetExists($offset)
    {
        return isset($this->$offset);
    }
  
    /**
     * Gets offset.
     * @param  integer $offset Offset 
     * @return mixed 
     */
    public function offsetGet($offset)
    {
        return $this->$offset;
    }
  
    /**
     * Sets value based on offset.
     * @param  integer $offset Offset 
     * @param  mixed   $value  Value to be set
     * @return void
     */
    public function offsetSet($offset, $value)
    {
        $this->$offset = $value;
    }
  
    /**
     * Unsets offset.
     * @param  integer $offset Offset 
     * @return void
     */
    public function offsetUnset($offset)
    {
        unset($this->$offset);
    }
  
    /**
     * Gets the string presentation of the object
     * @return string
     */
    public function __toString()
    {
        if (defined('JSON_PRETTY_PRINT')) {
            return json_encode(\SquareConnect\ObjectSerializer::sanitizeForSerialization($this), JSON_PRETTY_PRINT);
        } else {
            return json_encode(\SquareConnect\ObjectSerializer::sanitizeForSerialization($this));
        }
    }
}
