// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/transaction.proto

package com.squareup.protos.connect.v3.actions;

public interface ListTransactionsRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:squareup.connect.v3.actions.ListTransactionsRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .squareup.connect.v3.actions.ListTransactionsRequest.Params params = 1;</code>
   */
  boolean hasParams();
  /**
   * <code>optional .squareup.connect.v3.actions.ListTransactionsRequest.Params params = 1;</code>
   */
  com.squareup.protos.connect.v3.actions.ListTransactionsRequest.Params getParams();
  /**
   * <code>optional .squareup.connect.v3.actions.ListTransactionsRequest.Params params = 1;</code>
   */
  com.squareup.protos.connect.v3.actions.ListTransactionsRequest.ParamsOrBuilder getParamsOrBuilder();

  /**
   * <code>optional string cursor = 2;</code>
   */
  boolean hasCursor();
  /**
   * <code>optional string cursor = 2;</code>
   */
  java.lang.String getCursor();
  /**
   * <code>optional string cursor = 2;</code>
   */
  com.google.protobuf.ByteString
      getCursorBytes();
}