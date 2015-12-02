// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/charge.proto

package com.squareup.protos.connect.v3.actions;

public interface ChargeRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:squareup.connect.v3.actions.ChargeRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string location_id = 1;</code>
   */
  boolean hasLocationId();
  /**
   * <code>required string location_id = 1;</code>
   */
  java.lang.String getLocationId();
  /**
   * <code>required string location_id = 1;</code>
   */
  com.google.protobuf.ByteString
      getLocationIdBytes();

  /**
   * <code>required string idempotency_key = 2;</code>
   */
  boolean hasIdempotencyKey();
  /**
   * <code>required string idempotency_key = 2;</code>
   */
  java.lang.String getIdempotencyKey();
  /**
   * <code>required string idempotency_key = 2;</code>
   */
  com.google.protobuf.ByteString
      getIdempotencyKeyBytes();

  /**
   * <code>required .squareup.connect.v3.resources.Money amount_money = 3;</code>
   */
  boolean hasAmountMoney();
  /**
   * <code>required .squareup.connect.v3.resources.Money amount_money = 3;</code>
   */
  com.squareup.protos.connect.v3.resources.Money getAmountMoney();
  /**
   * <code>required .squareup.connect.v3.resources.Money amount_money = 3;</code>
   */
  com.squareup.protos.connect.v3.resources.MoneyOrBuilder getAmountMoneyOrBuilder();

  /**
   * <code>optional .squareup.connect.v3.actions.ChargeRequest.CardNonceInstrument card_nonce = 4;</code>
   *
   * <pre>
   *--&#64;desc Tokenized card nonce
   * </pre>
   */
  boolean hasCardNonce();
  /**
   * <code>optional .squareup.connect.v3.actions.ChargeRequest.CardNonceInstrument card_nonce = 4;</code>
   *
   * <pre>
   *--&#64;desc Tokenized card nonce
   * </pre>
   */
  com.squareup.protos.connect.v3.actions.ChargeRequest.CardNonceInstrument getCardNonce();
  /**
   * <code>optional .squareup.connect.v3.actions.ChargeRequest.CardNonceInstrument card_nonce = 4;</code>
   *
   * <pre>
   *--&#64;desc Tokenized card nonce
   * </pre>
   */
  com.squareup.protos.connect.v3.actions.ChargeRequest.CardNonceInstrumentOrBuilder getCardNonceOrBuilder();

  /**
   * <code>optional .squareup.connect.v3.actions.ChargeRequest.CustomerCardInstrument customer_card = 5;</code>
   *
   * <pre>
   *--&#64;desc A card-on-file for a customer.
   * </pre>
   */
  boolean hasCustomerCard();
  /**
   * <code>optional .squareup.connect.v3.actions.ChargeRequest.CustomerCardInstrument customer_card = 5;</code>
   *
   * <pre>
   *--&#64;desc A card-on-file for a customer.
   * </pre>
   */
  com.squareup.protos.connect.v3.actions.ChargeRequest.CustomerCardInstrument getCustomerCard();
  /**
   * <code>optional .squareup.connect.v3.actions.ChargeRequest.CustomerCardInstrument customer_card = 5;</code>
   *
   * <pre>
   *--&#64;desc A card-on-file for a customer.
   * </pre>
   */
  com.squareup.protos.connect.v3.actions.ChargeRequest.CustomerCardInstrumentOrBuilder getCustomerCardOrBuilder();

  /**
   * <code>optional bool delay_capture = 6;</code>
   *
   * <pre>
   *--&#64;desc Request that the created payment only execute Auth and require a separate call for capture.
   * </pre>
   */
  boolean hasDelayCapture();
  /**
   * <code>optional bool delay_capture = 6;</code>
   *
   * <pre>
   *--&#64;desc Request that the created payment only execute Auth and require a separate call for capture.
   * </pre>
   */
  boolean getDelayCapture();

  /**
   * <code>optional string reference_id = 7;</code>
   */
  boolean hasReferenceId();
  /**
   * <code>optional string reference_id = 7;</code>
   */
  java.lang.String getReferenceId();
  /**
   * <code>optional string reference_id = 7;</code>
   */
  com.google.protobuf.ByteString
      getReferenceIdBytes();

  /**
   * <code>optional string note = 8;</code>
   */
  boolean hasNote();
  /**
   * <code>optional string note = 8;</code>
   */
  java.lang.String getNote();
  /**
   * <code>optional string note = 8;</code>
   */
  com.google.protobuf.ByteString
      getNoteBytes();
}
