// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/refund.proto

package com.squareup.protos.connect.v3.actions;

public interface RefundTenderResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:squareup.connect.v3.actions.RefundTenderResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  java.util.List<com.squareup.protos.connect.v3.resources.Error> 
      getErrorsList();
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  com.squareup.protos.connect.v3.resources.Error getErrors(int index);
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  int getErrorsCount();
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  java.util.List<? extends com.squareup.protos.connect.v3.resources.ErrorOrBuilder> 
      getErrorsOrBuilderList();
  /**
   * <code>repeated .squareup.connect.v3.resources.Error errors = 1;</code>
   */
  com.squareup.protos.connect.v3.resources.ErrorOrBuilder getErrorsOrBuilder(
      int index);

  /**
   * <code>optional .squareup.connect.v3.resources.TenderRefund refund = 2;</code>
   */
  boolean hasRefund();
  /**
   * <code>optional .squareup.connect.v3.resources.TenderRefund refund = 2;</code>
   */
  com.squareup.protos.connect.v3.resources.TenderRefund getRefund();
  /**
   * <code>optional .squareup.connect.v3.resources.TenderRefund refund = 2;</code>
   */
  com.squareup.protos.connect.v3.resources.TenderRefundOrBuilder getRefundOrBuilder();
}
