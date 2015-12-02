// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/transaction.proto

package com.squareup.protos.connect.v3.actions;

public final class Transaction {
  private Transaction() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_ListTransactionsRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_ListTransactionsRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_ListTransactionsRequest_Params_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_ListTransactionsRequest_Params_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_ListTransactionsResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_ListTransactionsResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_RetrieveTransactionRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_RetrieveTransactionRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_RetrieveTransactionResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_RetrieveTransactionResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_CaptureTransactionRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_CaptureTransactionRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_CaptureTransactionResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_CaptureTransactionResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_VoidTransactionRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_VoidTransactionRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_VoidTransactionResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_VoidTransactionResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n-squareup/connect/v3/actions/transactio" +
      "n.proto\022\033squareup.connect.v3.actions\032)sq" +
      "uareup/connect/v3/resources/error.proto\032" +
      "/squareup/connect/v3/resources/transacti" +
      "on.proto\"\312\002\n\027ListTransactionsRequest\022M\n\006" +
      "params\030\001 \001(\0132;.squareup.connect.v3.actio" +
      "ns.ListTransactionsRequest.ParamsH\000\022\020\n\006c" +
      "ursor\030\002 \001(\tH\000\032\304\001\n\006Params\022\023\n\013location_id\030" +
      "\001 \002(\t\022\022\n\nbegin_time\030\002 \002(\t\022\020\n\010end_time\030\003 " +
      "\002(\t\022N\n\004sort\030\004 \002(\0162@.squareup.connect.v3.",
      "actions.ListTransactionsRequest.Params.S" +
      "ort\"/\n\004Sort\022\023\n\017CREATED_AT_DESC\020\000\022\022\n\016CREA" +
      "TED_AT_ASC\020\001B\007\n\005query\"\242\001\n\030ListTransactio" +
      "nsResponse\0224\n\006errors\030\001 \003(\0132$.squareup.co" +
      "nnect.v3.resources.Error\022@\n\014transactions" +
      "\030\002 \003(\0132*.squareup.connect.v3.resources.T" +
      "ransaction\022\016\n\006cursor\030\003 \001(\t\"I\n\032RetrieveTr" +
      "ansactionRequest\022\023\n\013location_id\030\001 \002(\t\022\026\n" +
      "\016transaction_id\030\002 \002(\t\"\224\001\n\033RetrieveTransa" +
      "ctionResponse\0224\n\006errors\030\001 \003(\0132$.squareup",
      ".connect.v3.resources.Error\022?\n\013transacti" +
      "on\030\002 \001(\0132*.squareup.connect.v3.resources" +
      ".Transaction\"H\n\031CaptureTransactionReques" +
      "t\022\023\n\013location_id\030\001 \002(\t\022\026\n\016transaction_id" +
      "\030\002 \002(\t\"R\n\032CaptureTransactionResponse\0224\n\006" +
      "errors\030\001 \003(\0132$.squareup.connect.v3.resou" +
      "rces.Error\"E\n\026VoidTransactionRequest\022\023\n\013" +
      "location_id\030\001 \002(\t\022\026\n\016transaction_id\030\002 \002(" +
      "\t\"O\n\027VoidTransactionResponse\0224\n\006errors\030\001" +
      " \003(\0132$.squareup.connect.v3.resources.Err",
      "orB*\n&com.squareup.protos.connect.v3.act" +
      "ionsP\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.squareup.protos.connect.v3.resources.ErrorOuterClass.getDescriptor(),
          com.squareup.protos.connect.v3.resources.TransactionOuterClass.getDescriptor(),
        }, assigner);
    internal_static_squareup_connect_v3_actions_ListTransactionsRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_squareup_connect_v3_actions_ListTransactionsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_ListTransactionsRequest_descriptor,
        new java.lang.String[] { "Params", "Cursor", "Query", });
    internal_static_squareup_connect_v3_actions_ListTransactionsRequest_Params_descriptor =
      internal_static_squareup_connect_v3_actions_ListTransactionsRequest_descriptor.getNestedTypes().get(0);
    internal_static_squareup_connect_v3_actions_ListTransactionsRequest_Params_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_ListTransactionsRequest_Params_descriptor,
        new java.lang.String[] { "LocationId", "BeginTime", "EndTime", "Sort", });
    internal_static_squareup_connect_v3_actions_ListTransactionsResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_squareup_connect_v3_actions_ListTransactionsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_ListTransactionsResponse_descriptor,
        new java.lang.String[] { "Errors", "Transactions", "Cursor", });
    internal_static_squareup_connect_v3_actions_RetrieveTransactionRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_squareup_connect_v3_actions_RetrieveTransactionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_RetrieveTransactionRequest_descriptor,
        new java.lang.String[] { "LocationId", "TransactionId", });
    internal_static_squareup_connect_v3_actions_RetrieveTransactionResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_squareup_connect_v3_actions_RetrieveTransactionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_RetrieveTransactionResponse_descriptor,
        new java.lang.String[] { "Errors", "Transaction", });
    internal_static_squareup_connect_v3_actions_CaptureTransactionRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_squareup_connect_v3_actions_CaptureTransactionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_CaptureTransactionRequest_descriptor,
        new java.lang.String[] { "LocationId", "TransactionId", });
    internal_static_squareup_connect_v3_actions_CaptureTransactionResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_squareup_connect_v3_actions_CaptureTransactionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_CaptureTransactionResponse_descriptor,
        new java.lang.String[] { "Errors", });
    internal_static_squareup_connect_v3_actions_VoidTransactionRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_squareup_connect_v3_actions_VoidTransactionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_VoidTransactionRequest_descriptor,
        new java.lang.String[] { "LocationId", "TransactionId", });
    internal_static_squareup_connect_v3_actions_VoidTransactionResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_squareup_connect_v3_actions_VoidTransactionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_VoidTransactionResponse_descriptor,
        new java.lang.String[] { "Errors", });
    com.squareup.protos.connect.v3.resources.ErrorOuterClass.getDescriptor();
    com.squareup.protos.connect.v3.resources.TransactionOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}