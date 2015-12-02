// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/location.proto

package com.squareup.protos.connect.v3.actions;

/**
 * Protobuf type {@code squareup.connect.v3.actions.ListLocationsResponse}
 */
public  final class ListLocationsResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:squareup.connect.v3.actions.ListLocationsResponse)
    ListLocationsResponseOrBuilder {
  // Use ListLocationsResponse.newBuilder() to construct.
  private ListLocationsResponse(com.google.protobuf.GeneratedMessage.Builder builder) {
    super(builder);
  }
  private ListLocationsResponse() {
    errors_ = java.util.Collections.emptyList();
    locations_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ListLocationsResponse(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              errors_ = new java.util.ArrayList<com.squareup.protos.connect.v3.resources.Error>();
              mutable_bitField0_ |= 0x00000001;
            }
            errors_.add(input.readMessage(com.squareup.protos.connect.v3.resources.Error.PARSER, extensionRegistry));
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              locations_ = new java.util.ArrayList<com.squareup.protos.connect.v3.resources.Location>();
              mutable_bitField0_ |= 0x00000002;
            }
            locations_.add(input.readMessage(com.squareup.protos.connect.v3.resources.Location.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        errors_ = java.util.Collections.unmodifiableList(errors_);
      }
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        locations_ = java.util.Collections.unmodifiableList(locations_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.squareup.protos.connect.v3.actions.Location.internal_static_squareup_connect_v3_actions_ListLocationsResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.squareup.protos.connect.v3.actions.Location.internal_static_squareup_connect_v3_actions_ListLocationsResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.squareup.protos.connect.v3.actions.ListLocationsResponse.class, com.squareup.protos.connect.v3.actions.ListLocationsResponse.Builder.class);
  }

  public static final int ERRORS_FIELD_NUMBER = 1;
  private java.util.List<com.squareup.protos.connect.v3.resources.Error> errors_;
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  public java.util.List<com.squareup.protos.connect.v3.resources.Error> getErrorsList() {
    return errors_;
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  public java.util.List<? extends com.squareup.protos.connect.v3.resources.ErrorOrBuilder> 
      getErrorsOrBuilderList() {
    return errors_;
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  public int getErrorsCount() {
    return errors_.size();
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  public com.squareup.protos.connect.v3.resources.Error getErrors(int index) {
    return errors_.get(index);
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  public com.squareup.protos.connect.v3.resources.ErrorOrBuilder getErrorsOrBuilder(
      int index) {
    return errors_.get(index);
  }

  public static final int LOCATIONS_FIELD_NUMBER = 2;
  private java.util.List<com.squareup.protos.connect.v3.resources.Location> locations_;
  /**
   * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
   */
  public java.util.List<com.squareup.protos.connect.v3.resources.Location> getLocationsList() {
    return locations_;
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
   */
  public java.util.List<? extends com.squareup.protos.connect.v3.resources.LocationOrBuilder> 
      getLocationsOrBuilderList() {
    return locations_;
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
   */
  public int getLocationsCount() {
    return locations_.size();
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
   */
  public com.squareup.protos.connect.v3.resources.Location getLocations(int index) {
    return locations_.get(index);
  }
  /**
   * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
   */
  public com.squareup.protos.connect.v3.resources.LocationOrBuilder getLocationsOrBuilder(
      int index) {
    return locations_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < errors_.size(); i++) {
      output.writeMessage(1, errors_.get(i));
    }
    for (int i = 0; i < locations_.size(); i++) {
      output.writeMessage(2, locations_.get(i));
    }
    unknownFields.writeTo(output);
  }

  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < errors_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, errors_.get(i));
    }
    for (int i = 0; i < locations_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, locations_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(java.io.InputStream input)
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
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(
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
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseDelimitedFrom(java.io.InputStream input)
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
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseDelimitedFrom(
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
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(
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
  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse parseFrom(
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
  public static Builder newBuilder(com.squareup.protos.connect.v3.actions.ListLocationsResponse prototype) {
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
   * Protobuf type {@code squareup.connect.v3.actions.ListLocationsResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:squareup.connect.v3.actions.ListLocationsResponse)
      com.squareup.protos.connect.v3.actions.ListLocationsResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.squareup.protos.connect.v3.actions.Location.internal_static_squareup_connect_v3_actions_ListLocationsResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.squareup.protos.connect.v3.actions.Location.internal_static_squareup_connect_v3_actions_ListLocationsResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.squareup.protos.connect.v3.actions.ListLocationsResponse.class, com.squareup.protos.connect.v3.actions.ListLocationsResponse.Builder.class);
    }

    // Construct using com.squareup.protos.connect.v3.actions.ListLocationsResponse.newBuilder()
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
        getErrorsFieldBuilder();
        getLocationsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (errorsBuilder_ == null) {
        errors_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        errorsBuilder_.clear();
      }
      if (locationsBuilder_ == null) {
        locations_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        locationsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.squareup.protos.connect.v3.actions.Location.internal_static_squareup_connect_v3_actions_ListLocationsResponse_descriptor;
    }

    public com.squareup.protos.connect.v3.actions.ListLocationsResponse getDefaultInstanceForType() {
      return com.squareup.protos.connect.v3.actions.ListLocationsResponse.getDefaultInstance();
    }

    public com.squareup.protos.connect.v3.actions.ListLocationsResponse build() {
      com.squareup.protos.connect.v3.actions.ListLocationsResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.squareup.protos.connect.v3.actions.ListLocationsResponse buildPartial() {
      com.squareup.protos.connect.v3.actions.ListLocationsResponse result = new com.squareup.protos.connect.v3.actions.ListLocationsResponse(this);
      int from_bitField0_ = bitField0_;
      if (errorsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          errors_ = java.util.Collections.unmodifiableList(errors_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.errors_ = errors_;
      } else {
        result.errors_ = errorsBuilder_.build();
      }
      if (locationsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          locations_ = java.util.Collections.unmodifiableList(locations_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.locations_ = locations_;
      } else {
        result.locations_ = locationsBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.squareup.protos.connect.v3.actions.ListLocationsResponse) {
        return mergeFrom((com.squareup.protos.connect.v3.actions.ListLocationsResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.squareup.protos.connect.v3.actions.ListLocationsResponse other) {
      if (other == com.squareup.protos.connect.v3.actions.ListLocationsResponse.getDefaultInstance()) return this;
      if (errorsBuilder_ == null) {
        if (!other.errors_.isEmpty()) {
          if (errors_.isEmpty()) {
            errors_ = other.errors_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureErrorsIsMutable();
            errors_.addAll(other.errors_);
          }
          onChanged();
        }
      } else {
        if (!other.errors_.isEmpty()) {
          if (errorsBuilder_.isEmpty()) {
            errorsBuilder_.dispose();
            errorsBuilder_ = null;
            errors_ = other.errors_;
            bitField0_ = (bitField0_ & ~0x00000001);
            errorsBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getErrorsFieldBuilder() : null;
          } else {
            errorsBuilder_.addAllMessages(other.errors_);
          }
        }
      }
      if (locationsBuilder_ == null) {
        if (!other.locations_.isEmpty()) {
          if (locations_.isEmpty()) {
            locations_ = other.locations_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureLocationsIsMutable();
            locations_.addAll(other.locations_);
          }
          onChanged();
        }
      } else {
        if (!other.locations_.isEmpty()) {
          if (locationsBuilder_.isEmpty()) {
            locationsBuilder_.dispose();
            locationsBuilder_ = null;
            locations_ = other.locations_;
            bitField0_ = (bitField0_ & ~0x00000002);
            locationsBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getLocationsFieldBuilder() : null;
          } else {
            locationsBuilder_.addAllMessages(other.locations_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.squareup.protos.connect.v3.actions.ListLocationsResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.squareup.protos.connect.v3.actions.ListLocationsResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.squareup.protos.connect.v3.resources.Error> errors_ =
      java.util.Collections.emptyList();
    private void ensureErrorsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        errors_ = new java.util.ArrayList<com.squareup.protos.connect.v3.resources.Error>(errors_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        com.squareup.protos.connect.v3.resources.Error, com.squareup.protos.connect.v3.resources.Error.Builder, com.squareup.protos.connect.v3.resources.ErrorOrBuilder> errorsBuilder_;

    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public java.util.List<com.squareup.protos.connect.v3.resources.Error> getErrorsList() {
      if (errorsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(errors_);
      } else {
        return errorsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public int getErrorsCount() {
      if (errorsBuilder_ == null) {
        return errors_.size();
      } else {
        return errorsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public com.squareup.protos.connect.v3.resources.Error getErrors(int index) {
      if (errorsBuilder_ == null) {
        return errors_.get(index);
      } else {
        return errorsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder setErrors(
        int index, com.squareup.protos.connect.v3.resources.Error value) {
      if (errorsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureErrorsIsMutable();
        errors_.set(index, value);
        onChanged();
      } else {
        errorsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder setErrors(
        int index, com.squareup.protos.connect.v3.resources.Error.Builder builderForValue) {
      if (errorsBuilder_ == null) {
        ensureErrorsIsMutable();
        errors_.set(index, builderForValue.build());
        onChanged();
      } else {
        errorsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder addErrors(com.squareup.protos.connect.v3.resources.Error value) {
      if (errorsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureErrorsIsMutable();
        errors_.add(value);
        onChanged();
      } else {
        errorsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder addErrors(
        int index, com.squareup.protos.connect.v3.resources.Error value) {
      if (errorsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureErrorsIsMutable();
        errors_.add(index, value);
        onChanged();
      } else {
        errorsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder addErrors(
        com.squareup.protos.connect.v3.resources.Error.Builder builderForValue) {
      if (errorsBuilder_ == null) {
        ensureErrorsIsMutable();
        errors_.add(builderForValue.build());
        onChanged();
      } else {
        errorsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder addErrors(
        int index, com.squareup.protos.connect.v3.resources.Error.Builder builderForValue) {
      if (errorsBuilder_ == null) {
        ensureErrorsIsMutable();
        errors_.add(index, builderForValue.build());
        onChanged();
      } else {
        errorsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder addAllErrors(
        java.lang.Iterable<? extends com.squareup.protos.connect.v3.resources.Error> values) {
      if (errorsBuilder_ == null) {
        ensureErrorsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, errors_);
        onChanged();
      } else {
        errorsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder clearErrors() {
      if (errorsBuilder_ == null) {
        errors_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        errorsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public Builder removeErrors(int index) {
      if (errorsBuilder_ == null) {
        ensureErrorsIsMutable();
        errors_.remove(index);
        onChanged();
      } else {
        errorsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public com.squareup.protos.connect.v3.resources.Error.Builder getErrorsBuilder(
        int index) {
      return getErrorsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public com.squareup.protos.connect.v3.resources.ErrorOrBuilder getErrorsOrBuilder(
        int index) {
      if (errorsBuilder_ == null) {
        return errors_.get(index);  } else {
        return errorsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public java.util.List<? extends com.squareup.protos.connect.v3.resources.ErrorOrBuilder> 
         getErrorsOrBuilderList() {
      if (errorsBuilder_ != null) {
        return errorsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(errors_);
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public com.squareup.protos.connect.v3.resources.Error.Builder addErrorsBuilder() {
      return getErrorsFieldBuilder().addBuilder(
          com.squareup.protos.connect.v3.resources.Error.getDefaultInstance());
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public com.squareup.protos.connect.v3.resources.Error.Builder addErrorsBuilder(
        int index) {
      return getErrorsFieldBuilder().addBuilder(
          index, com.squareup.protos.connect.v3.resources.Error.getDefaultInstance());
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
     */
    public java.util.List<com.squareup.protos.connect.v3.resources.Error.Builder> 
         getErrorsBuilderList() {
      return getErrorsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        com.squareup.protos.connect.v3.resources.Error, com.squareup.protos.connect.v3.resources.Error.Builder, com.squareup.protos.connect.v3.resources.ErrorOrBuilder> 
        getErrorsFieldBuilder() {
      if (errorsBuilder_ == null) {
        errorsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            com.squareup.protos.connect.v3.resources.Error, com.squareup.protos.connect.v3.resources.Error.Builder, com.squareup.protos.connect.v3.resources.ErrorOrBuilder>(
                errors_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        errors_ = null;
      }
      return errorsBuilder_;
    }

    private java.util.List<com.squareup.protos.connect.v3.resources.Location> locations_ =
      java.util.Collections.emptyList();
    private void ensureLocationsIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        locations_ = new java.util.ArrayList<com.squareup.protos.connect.v3.resources.Location>(locations_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        com.squareup.protos.connect.v3.resources.Location, com.squareup.protos.connect.v3.resources.Location.Builder, com.squareup.protos.connect.v3.resources.LocationOrBuilder> locationsBuilder_;

    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public java.util.List<com.squareup.protos.connect.v3.resources.Location> getLocationsList() {
      if (locationsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(locations_);
      } else {
        return locationsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public int getLocationsCount() {
      if (locationsBuilder_ == null) {
        return locations_.size();
      } else {
        return locationsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public com.squareup.protos.connect.v3.resources.Location getLocations(int index) {
      if (locationsBuilder_ == null) {
        return locations_.get(index);
      } else {
        return locationsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder setLocations(
        int index, com.squareup.protos.connect.v3.resources.Location value) {
      if (locationsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLocationsIsMutable();
        locations_.set(index, value);
        onChanged();
      } else {
        locationsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder setLocations(
        int index, com.squareup.protos.connect.v3.resources.Location.Builder builderForValue) {
      if (locationsBuilder_ == null) {
        ensureLocationsIsMutable();
        locations_.set(index, builderForValue.build());
        onChanged();
      } else {
        locationsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder addLocations(com.squareup.protos.connect.v3.resources.Location value) {
      if (locationsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLocationsIsMutable();
        locations_.add(value);
        onChanged();
      } else {
        locationsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder addLocations(
        int index, com.squareup.protos.connect.v3.resources.Location value) {
      if (locationsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLocationsIsMutable();
        locations_.add(index, value);
        onChanged();
      } else {
        locationsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder addLocations(
        com.squareup.protos.connect.v3.resources.Location.Builder builderForValue) {
      if (locationsBuilder_ == null) {
        ensureLocationsIsMutable();
        locations_.add(builderForValue.build());
        onChanged();
      } else {
        locationsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder addLocations(
        int index, com.squareup.protos.connect.v3.resources.Location.Builder builderForValue) {
      if (locationsBuilder_ == null) {
        ensureLocationsIsMutable();
        locations_.add(index, builderForValue.build());
        onChanged();
      } else {
        locationsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder addAllLocations(
        java.lang.Iterable<? extends com.squareup.protos.connect.v3.resources.Location> values) {
      if (locationsBuilder_ == null) {
        ensureLocationsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, locations_);
        onChanged();
      } else {
        locationsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder clearLocations() {
      if (locationsBuilder_ == null) {
        locations_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        locationsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public Builder removeLocations(int index) {
      if (locationsBuilder_ == null) {
        ensureLocationsIsMutable();
        locations_.remove(index);
        onChanged();
      } else {
        locationsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public com.squareup.protos.connect.v3.resources.Location.Builder getLocationsBuilder(
        int index) {
      return getLocationsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public com.squareup.protos.connect.v3.resources.LocationOrBuilder getLocationsOrBuilder(
        int index) {
      if (locationsBuilder_ == null) {
        return locations_.get(index);  } else {
        return locationsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public java.util.List<? extends com.squareup.protos.connect.v3.resources.LocationOrBuilder> 
         getLocationsOrBuilderList() {
      if (locationsBuilder_ != null) {
        return locationsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(locations_);
      }
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public com.squareup.protos.connect.v3.resources.Location.Builder addLocationsBuilder() {
      return getLocationsFieldBuilder().addBuilder(
          com.squareup.protos.connect.v3.resources.Location.getDefaultInstance());
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public com.squareup.protos.connect.v3.resources.Location.Builder addLocationsBuilder(
        int index) {
      return getLocationsFieldBuilder().addBuilder(
          index, com.squareup.protos.connect.v3.resources.Location.getDefaultInstance());
    }
    /**
     * <code>repeated .squareup.connect.v3.resources.Location locations = 2;</code>
     */
    public java.util.List<com.squareup.protos.connect.v3.resources.Location.Builder> 
         getLocationsBuilderList() {
      return getLocationsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        com.squareup.protos.connect.v3.resources.Location, com.squareup.protos.connect.v3.resources.Location.Builder, com.squareup.protos.connect.v3.resources.LocationOrBuilder> 
        getLocationsFieldBuilder() {
      if (locationsBuilder_ == null) {
        locationsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            com.squareup.protos.connect.v3.resources.Location, com.squareup.protos.connect.v3.resources.Location.Builder, com.squareup.protos.connect.v3.resources.LocationOrBuilder>(
                locations_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        locations_ = null;
      }
      return locationsBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:squareup.connect.v3.actions.ListLocationsResponse)
  }

  // @@protoc_insertion_point(class_scope:squareup.connect.v3.actions.ListLocationsResponse)
  private static final com.squareup.protos.connect.v3.actions.ListLocationsResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.squareup.protos.connect.v3.actions.ListLocationsResponse();
  }

  public static com.squareup.protos.connect.v3.actions.ListLocationsResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static final com.google.protobuf.Parser<ListLocationsResponse> PARSER =
      new com.google.protobuf.AbstractParser<ListLocationsResponse>() {
    public ListLocationsResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new ListLocationsResponse(input, extensionRegistry);
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
  public com.google.protobuf.Parser<ListLocationsResponse> getParserForType() {
    return PARSER;
  }

  public com.squareup.protos.connect.v3.actions.ListLocationsResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
