// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/customer.proto

package com.squareup.protos.connect.v3.actions;

public interface UpsertCustomerRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:squareup.connect.v3.actions.UpsertCustomerRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
   *
   * <pre>
   * &#64;desc Note: changes to Customer.cards are ignored in this request.
   * </pre>
   */
  boolean hasCustomer();
  /**
   * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
   *
   * <pre>
   * &#64;desc Note: changes to Customer.cards are ignored in this request.
   * </pre>
   */
  com.squareup.protos.connect.v3.resources.Customer getCustomer();
  /**
   * <code>required .squareup.connect.v3.resources.Customer customer = 1;</code>
   *
   * <pre>
   * &#64;desc Note: changes to Customer.cards are ignored in this request.
   * </pre>
   */
  com.squareup.protos.connect.v3.resources.CustomerOrBuilder getCustomerOrBuilder();
}
