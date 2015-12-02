// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/customer.proto

package com.squareup.protos.connect.v3.actions;

/**
 * Protobuf type {@code squareup.connect.v3.actions.DeleteCustomerCardRequest}
 */
public  final class DeleteCustomerCardRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:squareup.connect.v3.actions.DeleteCustomerCardRequest)
    DeleteCustomerCardRequestOrBuilder {
  // Use DeleteCustomerCardRequest.newBuilder() to construct.
  private DeleteCustomerCardRequest(com.google.protobuf.GeneratedMessage.Builder builder) {
    super(builder);
  }
  private DeleteCustomerCardRequest() {
    locationId_ = "";
    customerId_ = "";
    cardId_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DeleteCustomerCardRequest(
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
            cardId_ = bs;
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
    return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_DeleteCustomerCardRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_DeleteCustomerCardRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest.class, com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest.Builder.class);
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

  public static final int CARD_ID_FIELD_NUMBER = 3;
  private volatile java.lang.Object cardId_;
  /**
   * <code>required string card_id = 3;</code>
   */
  public boolean hasCardId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required string card_id = 3;</code>
   */
  public java.lang.String getCardId() {
    java.lang.Object ref = cardId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        cardId_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string card_id = 3;</code>
   */
  public com.google.protobuf.ByteString
      getCardIdBytes() {
    java.lang.Object ref = cardId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      cardId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!hasCardId()) {
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
      output.writeBytes(3, getCardIdBytes());
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
        .computeBytesSize(3, getCardIdBytes());
    }
    size += unknownFields.getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(java.io.InputStream input)
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
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(
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
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseDelimitedFrom(java.io.InputStream input)
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
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseDelimitedFrom(
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
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(
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
  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parseFrom(
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
  public static Builder newBuilder(com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest prototype) {
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
   * Protobuf type {@code squareup.connect.v3.actions.DeleteCustomerCardRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:squareup.connect.v3.actions.DeleteCustomerCardRequest)
      com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_DeleteCustomerCardRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_DeleteCustomerCardRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest.class, com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest.Builder.class);
    }

    // Construct using com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest.newBuilder()
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
      }
    }
    public Builder clear() {
      super.clear();
      locationId_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      customerId_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      cardId_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_DeleteCustomerCardRequest_descriptor;
    }

    public com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest getDefaultInstanceForType() {
      return com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest.getDefaultInstance();
    }

    public com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest build() {
      com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest buildPartial() {
      com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest result = new com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest(this);
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
      result.cardId_ = cardId_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest) {
        return mergeFrom((com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest other) {
      if (other == com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest.getDefaultInstance()) return this;
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
      if (other.hasCardId()) {
        bitField0_ |= 0x00000004;
        cardId_ = other.cardId_;
        onChanged();
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
      if (!hasCardId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest) e.getUnfinishedMessage();
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

    private java.lang.Object cardId_ = "";
    /**
     * <code>required string card_id = 3;</code>
     */
    public boolean hasCardId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string card_id = 3;</code>
     */
    public java.lang.String getCardId() {
      java.lang.Object ref = cardId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          cardId_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string card_id = 3;</code>
     */
    public com.google.protobuf.ByteString
        getCardIdBytes() {
      java.lang.Object ref = cardId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cardId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string card_id = 3;</code>
     */
    public Builder setCardId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      cardId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string card_id = 3;</code>
     */
    public Builder clearCardId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      cardId_ = getDefaultInstance().getCardId();
      onChanged();
      return this;
    }
    /**
     * <code>required string card_id = 3;</code>
     */
    public Builder setCardIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      cardId_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:squareup.connect.v3.actions.DeleteCustomerCardRequest)
  }

  // @@protoc_insertion_point(class_scope:squareup.connect.v3.actions.DeleteCustomerCardRequest)
  private static final com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest();
  }

  public static com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static final com.google.protobuf.Parser<DeleteCustomerCardRequest> PARSER =
      new com.google.protobuf.AbstractParser<DeleteCustomerCardRequest>() {
    public DeleteCustomerCardRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new DeleteCustomerCardRequest(input, extensionRegistry);
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
  public com.google.protobuf.Parser<DeleteCustomerCardRequest> getParserForType() {
    return PARSER;
  }

  public com.squareup.protos.connect.v3.actions.DeleteCustomerCardRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
