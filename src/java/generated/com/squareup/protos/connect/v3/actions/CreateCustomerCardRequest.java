// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/customer.proto

package com.squareup.protos.connect.v3.actions;

/**
 * Protobuf type {@code squareup.connect.v3.actions.CreateCustomerCardRequest}
 */
public  final class CreateCustomerCardRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:squareup.connect.v3.actions.CreateCustomerCardRequest)
    CreateCustomerCardRequestOrBuilder {
  // Use CreateCustomerCardRequest.newBuilder() to construct.
  private CreateCustomerCardRequest(com.google.protobuf.GeneratedMessage.Builder builder) {
    super(builder);
  }
  private CreateCustomerCardRequest() {
    locationId_ = "";
    customerId_ = "";
    cardNonce_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CreateCustomerCardRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            locationId_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            customerId_ = bs;
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            cardNonce_ = bs;
            break;
          }
          case 34: {
            com.squareup.protos.connect.v3.resources.CardData.Builder subBuilder = null;
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
              subBuilder = cardData_.toBuilder();
            }
            cardData_ = input.readMessage(com.squareup.protos.connect.v3.resources.CardData.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(cardData_);
              cardData_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000008;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(new com.google.protobuf.InvalidProtocolBufferException(e)
          .setUnfinishedMessage(this));
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_CreateCustomerCardRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_CreateCustomerCardRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest.class, com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest.Builder.class);
  }

  private int bitField0_;
  public static final int LOCATION_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object locationId_;
  /**
   * <code>required string location_id = 1;</code>
   */
  public boolean hasLocationId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string location_id = 1;</code>
   */
  public java.lang.String getLocationId() {
    java.lang.Object ref = locationId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        locationId_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string location_id = 1;</code>
   */
  public com.google.protobuf.ByteString
      getLocationIdBytes() {
    java.lang.Object ref = locationId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      locationId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CUSTOMER_ID_FIELD_NUMBER = 2;
  private volatile java.lang.Object customerId_;
  /**
   * <code>required string customer_id = 2;</code>
   */
  public boolean hasCustomerId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string customer_id = 2;</code>
   */
  public java.lang.String getCustomerId() {
    java.lang.Object ref = customerId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        customerId_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string customer_id = 2;</code>
   */
  public com.google.protobuf.ByteString
      getCustomerIdBytes() {
    java.lang.Object ref = customerId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      customerId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CARD_NONCE_FIELD_NUMBER = 3;
  private volatile java.lang.Object cardNonce_;
  /**
   * <code>optional string card_nonce = 3;</code>
   *
   * <pre>
   * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
   * </pre>
   */
  public boolean hasCardNonce() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional string card_nonce = 3;</code>
   *
   * <pre>
   * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
   * </pre>
   */
  public java.lang.String getCardNonce() {
    java.lang.Object ref = cardNonce_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        cardNonce_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string card_nonce = 3;</code>
   *
   * <pre>
   * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
   * </pre>
   */
  public com.google.protobuf.ByteString
      getCardNonceBytes() {
    java.lang.Object ref = cardNonce_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      cardNonce_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CARD_DATA_FIELD_NUMBER = 4;
  private com.squareup.protos.connect.v3.resources.CardData cardData_;
  /**
   * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
   */
  public boolean hasCardData() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
   */
  public com.squareup.protos.connect.v3.resources.CardData getCardData() {
    return cardData_ == null ? com.squareup.protos.connect.v3.resources.CardData.getDefaultInstance() : cardData_;
  }
  /**
   * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
   */
  public com.squareup.protos.connect.v3.resources.CardDataOrBuilder getCardDataOrBuilder() {
    return cardData_ == null ? com.squareup.protos.connect.v3.resources.CardData.getDefaultInstance() : cardData_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasLocationId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCustomerId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeBytes(1, getLocationIdBytes());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBytes(2, getCustomerIdBytes());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeBytes(3, getCardNonceBytes());
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeMessage(4, getCardData());
    }
    unknownFields.writeTo(output);
  }

  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, getLocationIdBytes());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, getCustomerIdBytes());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, getCardNonceBytes());
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getCardData());
    }
    size += unknownFields.getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    try {
      return PARSER.parseFrom(input);
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      if (e.getCause() instanceof java.io.IOException) {
        throw (java.io.IOException) e.getCause();
      }
      throw e;
    }
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    try {
      return PARSER.parseFrom(input, extensionRegistry);
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      if (e.getCause() instanceof java.io.IOException) {
        throw (java.io.IOException) e.getCause();
      }
      throw e;
    }
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    try {
      return PARSER.parseDelimitedFrom(input);
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      if (e.getCause() instanceof java.io.IOException) {
        throw (java.io.IOException) e.getCause();
      }
      throw e;
    }
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    try {  return PARSER.parseDelimitedFrom(input, extensionRegistry);
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      if (e.getCause() instanceof java.io.IOException) {
        throw (java.io.IOException) e.getCause();
      }
      throw e;
    }
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    try {
      return PARSER.parseFrom(input);
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      if (e.getCause() instanceof java.io.IOException) {
        throw (java.io.IOException) e.getCause();
      }
      throw e;
    }
  }
  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    try {
      return PARSER.parseFrom(input, extensionRegistry);
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      if (e.getCause() instanceof java.io.IOException) {
        throw (java.io.IOException) e.getCause();
      }
      throw e;
    }
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code squareup.connect.v3.actions.CreateCustomerCardRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:squareup.connect.v3.actions.CreateCustomerCardRequest)
      com.squareup.protos.connect.v3.actions.CreateCustomerCardRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_CreateCustomerCardRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_CreateCustomerCardRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest.class, com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest.Builder.class);
    }

    // Construct using com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        getCardDataFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      locationId_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      customerId_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      cardNonce_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      if (cardDataBuilder_ == null) {
        cardData_ = null;
      } else {
        cardDataBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_CreateCustomerCardRequest_descriptor;
    }

    public com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest getDefaultInstanceForType() {
      return com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest.getDefaultInstance();
    }

    public com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest build() {
      com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest buildPartial() {
      com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest result = new com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.locationId_ = locationId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.customerId_ = customerId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.cardNonce_ = cardNonce_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      if (cardDataBuilder_ == null) {
        result.cardData_ = cardData_;
      } else {
        result.cardData_ = cardDataBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest) {
        return mergeFrom((com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest other) {
      if (other == com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest.getDefaultInstance()) return this;
      if (other.hasLocationId()) {
        bitField0_ |= 0x00000001;
        locationId_ = other.locationId_;
        onChanged();
      }
      if (other.hasCustomerId()) {
        bitField0_ |= 0x00000002;
        customerId_ = other.customerId_;
        onChanged();
      }
      if (other.hasCardNonce()) {
        bitField0_ |= 0x00000004;
        cardNonce_ = other.cardNonce_;
        onChanged();
      }
      if (other.hasCardData()) {
        mergeCardData(other.getCardData());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasLocationId()) {
        return false;
      }
      if (!hasCustomerId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest) e.getUnfinishedMessage();
        if (e.getCause() instanceof java.io.IOException) {
          throw (java.io.IOException) e.getCause();
        }
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object locationId_ = "";
    /**
     * <code>required string location_id = 1;</code>
     */
    public boolean hasLocationId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string location_id = 1;</code>
     */
    public java.lang.String getLocationId() {
      java.lang.Object ref = locationId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          locationId_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string location_id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getLocationIdBytes() {
      java.lang.Object ref = locationId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        locationId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string location_id = 1;</code>
     */
    public Builder setLocationId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      locationId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string location_id = 1;</code>
     */
    public Builder clearLocationId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      locationId_ = getDefaultInstance().getLocationId();
      onChanged();
      return this;
    }
    /**
     * <code>required string location_id = 1;</code>
     */
    public Builder setLocationIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      locationId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object customerId_ = "";
    /**
     * <code>required string customer_id = 2;</code>
     */
    public boolean hasCustomerId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string customer_id = 2;</code>
     */
    public java.lang.String getCustomerId() {
      java.lang.Object ref = customerId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          customerId_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string customer_id = 2;</code>
     */
    public com.google.protobuf.ByteString
        getCustomerIdBytes() {
      java.lang.Object ref = customerId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        customerId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string customer_id = 2;</code>
     */
    public Builder setCustomerId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      customerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string customer_id = 2;</code>
     */
    public Builder clearCustomerId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      customerId_ = getDefaultInstance().getCustomerId();
      onChanged();
      return this;
    }
    /**
     * <code>required string customer_id = 2;</code>
     */
    public Builder setCustomerIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      customerId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object cardNonce_ = "";
    /**
     * <code>optional string card_nonce = 3;</code>
     *
     * <pre>
     * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
     * </pre>
     */
    public boolean hasCardNonce() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string card_nonce = 3;</code>
     *
     * <pre>
     * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
     * </pre>
     */
    public java.lang.String getCardNonce() {
      java.lang.Object ref = cardNonce_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          cardNonce_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string card_nonce = 3;</code>
     *
     * <pre>
     * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
     * </pre>
     */
    public com.google.protobuf.ByteString
        getCardNonceBytes() {
      java.lang.Object ref = cardNonce_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cardNonce_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string card_nonce = 3;</code>
     *
     * <pre>
     * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
     * </pre>
     */
    public Builder setCardNonce(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      cardNonce_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string card_nonce = 3;</code>
     *
     * <pre>
     * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
     * </pre>
     */
    public Builder clearCardNonce() {
      bitField0_ = (bitField0_ & ~0x00000004);
      cardNonce_ = getDefaultInstance().getCardNonce();
      onChanged();
      return this;
    }
    /**
     * <code>optional string card_nonce = 3;</code>
     *
     * <pre>
     * &#64;desc Card nonce. One time use token that represents card data to create an instrument linked to this customer.
     * </pre>
     */
    public Builder setCardNonceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      cardNonce_ = value;
      onChanged();
      return this;
    }

    private com.squareup.protos.connect.v3.resources.CardData cardData_ = null;
    private com.google.protobuf.SingleFieldBuilder<
        com.squareup.protos.connect.v3.resources.CardData, com.squareup.protos.connect.v3.resources.CardData.Builder, com.squareup.protos.connect.v3.resources.CardDataOrBuilder> cardDataBuilder_;
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public boolean hasCardData() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public com.squareup.protos.connect.v3.resources.CardData getCardData() {
      if (cardDataBuilder_ == null) {
        return cardData_ == null ? com.squareup.protos.connect.v3.resources.CardData.getDefaultInstance() : cardData_;
      } else {
        return cardDataBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public Builder setCardData(com.squareup.protos.connect.v3.resources.CardData value) {
      if (cardDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        cardData_ = value;
        onChanged();
      } else {
        cardDataBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000008;
      return this;
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public Builder setCardData(
        com.squareup.protos.connect.v3.resources.CardData.Builder builderForValue) {
      if (cardDataBuilder_ == null) {
        cardData_ = builderForValue.build();
        onChanged();
      } else {
        cardDataBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000008;
      return this;
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public Builder mergeCardData(com.squareup.protos.connect.v3.resources.CardData value) {
      if (cardDataBuilder_ == null) {
        if (((bitField0_ & 0x00000008) == 0x00000008) &&
            cardData_ != null &&
            cardData_ != com.squareup.protos.connect.v3.resources.CardData.getDefaultInstance()) {
          cardData_ =
            com.squareup.protos.connect.v3.resources.CardData.newBuilder(cardData_).mergeFrom(value).buildPartial();
        } else {
          cardData_ = value;
        }
        onChanged();
      } else {
        cardDataBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000008;
      return this;
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public Builder clearCardData() {
      if (cardDataBuilder_ == null) {
        cardData_ = null;
        onChanged();
      } else {
        cardDataBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public com.squareup.protos.connect.v3.resources.CardData.Builder getCardDataBuilder() {
      bitField0_ |= 0x00000008;
      onChanged();
      return getCardDataFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    public com.squareup.protos.connect.v3.resources.CardDataOrBuilder getCardDataOrBuilder() {
      if (cardDataBuilder_ != null) {
        return cardDataBuilder_.getMessageOrBuilder();
      } else {
        return cardData_ == null ?
            com.squareup.protos.connect.v3.resources.CardData.getDefaultInstance() : cardData_;
      }
    }
    /**
     * <code>optional .squareup.connect.v3.resources.CardData card_data = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        com.squareup.protos.connect.v3.resources.CardData, com.squareup.protos.connect.v3.resources.CardData.Builder, com.squareup.protos.connect.v3.resources.CardDataOrBuilder> 
        getCardDataFieldBuilder() {
      if (cardDataBuilder_ == null) {
        cardDataBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            com.squareup.protos.connect.v3.resources.CardData, com.squareup.protos.connect.v3.resources.CardData.Builder, com.squareup.protos.connect.v3.resources.CardDataOrBuilder>(
                getCardData(),
                getParentForChildren(),
                isClean());
        cardData_ = null;
      }
      return cardDataBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:squareup.connect.v3.actions.CreateCustomerCardRequest)
  }

  // @@protoc_insertion_point(class_scope:squareup.connect.v3.actions.CreateCustomerCardRequest)
  private static final com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest();
  }

  public static com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static final com.google.protobuf.Parser<CreateCustomerCardRequest> PARSER =
      new com.google.protobuf.AbstractParser<CreateCustomerCardRequest>() {
    public CreateCustomerCardRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new CreateCustomerCardRequest(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  @java.lang.Override
  public com.google.protobuf.Parser<CreateCustomerCardRequest> getParserForType() {
    return PARSER;
  }

  public com.squareup.protos.connect.v3.actions.CreateCustomerCardRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
