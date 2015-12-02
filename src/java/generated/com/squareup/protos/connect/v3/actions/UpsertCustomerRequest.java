// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/customer.proto

package com.squareup.protos.connect.v3.actions;

/**
 * Protobuf type {@code squareup.connect.v3.actions.UpsertCustomerRequest}
 */
public  final class UpsertCustomerRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:squareup.connect.v3.actions.UpsertCustomerRequest)
    UpsertCustomerRequestOrBuilder {
  // Use UpsertCustomerRequest.newBuilder() to construct.
  private UpsertCustomerRequest(com.google.protobuf.GeneratedMessage.Builder builder) {
    super(builder);
  }
  private UpsertCustomerRequest() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpsertCustomerRequest(
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
            com.squareup.protos.connect.v3.resources.Customer.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = customer_.toBuilder();
            }
            customer_ = input.readMessage(com.squareup.protos.connect.v3.resources.Customer.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(customer_);
              customer_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
    return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_UpsertCustomerRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_UpsertCustomerRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.squareup.protos.connect.v3.actions.UpsertCustomerRequest.class, com.squareup.protos.connect.v3.actions.UpsertCustomerRequest.Builder.class);
  }

  private int bitField0_;
  public static final int CUSTOMER_FIELD_NUMBER = 1;
  private com.squareup.protos.connect.v3.resources.Customer customer_;
  /**
   * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
   *
   * <pre>
   * &#64;desc Note: changes to Customer.cards are ignored in this request.
   * </pre>
   */
  public boolean hasCustomer() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
   *
   * <pre>
   * &#64;desc Note: changes to Customer.cards are ignored in this request.
   * </pre>
   */
  public com.squareup.protos.connect.v3.resources.Customer getCustomer() {
    return customer_ == null ? com.squareup.protos.connect.v3.resources.Customer.getDefaultInstance() : customer_;
  }
  /**
   * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
   *
   * <pre>
   * &#64;desc Note: changes to Customer.cards are ignored in this request.
   * </pre>
   */
  public com.squareup.protos.connect.v3.resources.CustomerOrBuilder getCustomerOrBuilder() {
    return customer_ == null ? com.squareup.protos.connect.v3.resources.Customer.getDefaultInstance() : customer_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasCustomer()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(1, getCustomer());
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
        .computeMessageSize(1, getCustomer());
    }
    size += unknownFields.getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(java.io.InputStream input)
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
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(
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
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseDelimitedFrom(java.io.InputStream input)
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
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseDelimitedFrom(
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
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(
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
  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parseFrom(
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
  public static Builder newBuilder(com.squareup.protos.connect.v3.actions.UpsertCustomerRequest prototype) {
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
   * Protobuf type {@code squareup.connect.v3.actions.UpsertCustomerRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:squareup.connect.v3.actions.UpsertCustomerRequest)
      com.squareup.protos.connect.v3.actions.UpsertCustomerRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_UpsertCustomerRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_UpsertCustomerRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.squareup.protos.connect.v3.actions.UpsertCustomerRequest.class, com.squareup.protos.connect.v3.actions.UpsertCustomerRequest.Builder.class);
    }

    // Construct using com.squareup.protos.connect.v3.actions.UpsertCustomerRequest.newBuilder()
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
        getCustomerFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (customerBuilder_ == null) {
        customer_ = null;
      } else {
        customerBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.squareup.protos.connect.v3.actions.Customer.internal_static_squareup_connect_v3_actions_UpsertCustomerRequest_descriptor;
    }

    public com.squareup.protos.connect.v3.actions.UpsertCustomerRequest getDefaultInstanceForType() {
      return com.squareup.protos.connect.v3.actions.UpsertCustomerRequest.getDefaultInstance();
    }

    public com.squareup.protos.connect.v3.actions.UpsertCustomerRequest build() {
      com.squareup.protos.connect.v3.actions.UpsertCustomerRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.squareup.protos.connect.v3.actions.UpsertCustomerRequest buildPartial() {
      com.squareup.protos.connect.v3.actions.UpsertCustomerRequest result = new com.squareup.protos.connect.v3.actions.UpsertCustomerRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (customerBuilder_ == null) {
        result.customer_ = customer_;
      } else {
        result.customer_ = customerBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.squareup.protos.connect.v3.actions.UpsertCustomerRequest) {
        return mergeFrom((com.squareup.protos.connect.v3.actions.UpsertCustomerRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.squareup.protos.connect.v3.actions.UpsertCustomerRequest other) {
      if (other == com.squareup.protos.connect.v3.actions.UpsertCustomerRequest.getDefaultInstance()) return this;
      if (other.hasCustomer()) {
        mergeCustomer(other.getCustomer());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasCustomer()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.squareup.protos.connect.v3.actions.UpsertCustomerRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.squareup.protos.connect.v3.actions.UpsertCustomerRequest) e.getUnfinishedMessage();
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

    private com.squareup.protos.connect.v3.resources.Customer customer_ = null;
    private com.google.protobuf.SingleFieldBuilder<
        com.squareup.protos.connect.v3.resources.Customer, com.squareup.protos.connect.v3.resources.Customer.Builder, com.squareup.protos.connect.v3.resources.CustomerOrBuilder> customerBuilder_;
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public boolean hasCustomer() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public com.squareup.protos.connect.v3.resources.Customer getCustomer() {
      if (customerBuilder_ == null) {
        return customer_ == null ? com.squareup.protos.connect.v3.resources.Customer.getDefaultInstance() : customer_;
      } else {
        return customerBuilder_.getMessage();
      }
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public Builder setCustomer(com.squareup.protos.connect.v3.resources.Customer value) {
      if (customerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        customer_ = value;
        onChanged();
      } else {
        customerBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public Builder setCustomer(
        com.squareup.protos.connect.v3.resources.Customer.Builder builderForValue) {
      if (customerBuilder_ == null) {
        customer_ = builderForValue.build();
        onChanged();
      } else {
        customerBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public Builder mergeCustomer(com.squareup.protos.connect.v3.resources.Customer value) {
      if (customerBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            customer_ != null &&
            customer_ != com.squareup.protos.connect.v3.resources.Customer.getDefaultInstance()) {
          customer_ =
            com.squareup.protos.connect.v3.resources.Customer.newBuilder(customer_).mergeFrom(value).buildPartial();
        } else {
          customer_ = value;
        }
        onChanged();
      } else {
        customerBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public Builder clearCustomer() {
      if (customerBuilder_ == null) {
        customer_ = null;
        onChanged();
      } else {
        customerBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public com.squareup.protos.connect.v3.resources.Customer.Builder getCustomerBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getCustomerFieldBuilder().getBuilder();
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    public com.squareup.protos.connect.v3.resources.CustomerOrBuilder getCustomerOrBuilder() {
      if (customerBuilder_ != null) {
        return customerBuilder_.getMessageOrBuilder();
      } else {
        return customer_ == null ?
            com.squareup.protos.connect.v3.resources.Customer.getDefaultInstance() : customer_;
      }
    }
    /**
     * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
     *
     * <pre>
     * &#64;desc Note: changes to Customer.cards are ignored in this request.
     * </pre>
     */
    private com.google.protobuf.SingleFieldBuilder<
        com.squareup.protos.connect.v3.resources.Customer, com.squareup.protos.connect.v3.resources.Customer.Builder, com.squareup.protos.connect.v3.resources.CustomerOrBuilder> 
        getCustomerFieldBuilder() {
      if (customerBuilder_ == null) {
        customerBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            com.squareup.protos.connect.v3.resources.Customer, com.squareup.protos.connect.v3.resources.Customer.Builder, com.squareup.protos.connect.v3.resources.CustomerOrBuilder>(
                getCustomer(),
                getParentForChildren(),
                isClean());
        customer_ = null;
      }
      return customerBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:squareup.connect.v3.actions.UpsertCustomerRequest)
  }

  // @@protoc_insertion_point(class_scope:squareup.connect.v3.actions.UpsertCustomerRequest)
  private static final com.squareup.protos.connect.v3.actions.UpsertCustomerRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.squareup.protos.connect.v3.actions.UpsertCustomerRequest();
  }

  public static com.squareup.protos.connect.v3.actions.UpsertCustomerRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static final com.google.protobuf.Parser<UpsertCustomerRequest> PARSER =
      new com.google.protobuf.AbstractParser<UpsertCustomerRequest>() {
    public UpsertCustomerRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new UpsertCustomerRequest(input, extensionRegistry);
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
  public com.google.protobuf.Parser<UpsertCustomerRequest> getParserForType() {
    return PARSER;
  }

  public com.squareup.protos.connect.v3.actions.UpsertCustomerRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

