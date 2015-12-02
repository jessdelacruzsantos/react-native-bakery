// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/card_nonce.proto

package com.squareup.protos.connect.v3.actions;

public interface CreateCardNonceRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:squareup.connect.v3.actions.CreateCardNonceRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string client_id = 1;</code>
   */
  boolean hasClientId();
  /**
   * <code>required string client_id = 1;</code>
   */
  java.lang.String getClientId();
  /**
   * <code>required string client_id = 1;</code>
   */
  com.google.protobuf.ByteString
      getClientIdBytes();

  /**
   * <code>optional string business_id = 2;</code>
   */
  boolean hasBusinessId();
  /**
   * <code>optional string business_id = 2;</code>
   */
  java.lang.String getBusinessId();
  /**
   * <code>optional string business_id = 2;</code>
   */
  com.google.protobuf.ByteString
      getBusinessIdBytes();

  /**
   * <code>required .squareup.connect.v3.resources.CardData card_data = 3;</code>
   */
  boolean hasCardData();
  /**
   * <code>required .squareup.connect.v3.resources.CardData card_data = 3;</code>
   */
  com.squareup.protos.connect.v3.resources.CardData getCardData();
  /**
   * <code>required .squareup.connect.v3.resources.CardData card_data = 3;</code>
   */
  com.squareup.protos.connect.v3.resources.CardDataOrBuilder getCardDataOrBuilder();
}
