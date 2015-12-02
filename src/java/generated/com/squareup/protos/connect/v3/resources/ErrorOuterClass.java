// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/resources/error.proto

package com.squareup.protos.connect.v3.resources;

public final class ErrorOuterClass {
  private ErrorOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_resources_Error_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_resources_Error_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n)squareup/connect/v3/resources/error.pr" +
      "oto\022\035squareup.connect.v3.resources\"\277\014\n\005E" +
      "rror\0227\n\004type\030\001 \001(\0162).squareup.connect.v3" +
      ".resources.Error.Type\022\016\n\006detail\030\002 \001(\t\022\r\n" +
      "\005field\030\003 \001(\t\"\335\013\n\004Type\022\031\n\025INTERNAL_SERVER" +
      "_ERROR\020\000\022\022\n\014UNAUTHORIZED\020\244\271\002\022\032\n\024ACCESS_T" +
      "OKEN_EXPIRED\020\245\271\002\022\032\n\024ACCESS_TOKEN_REVOKED" +
      "\020\246\271\002\022\017\n\tFORBIDDEN\020\354\272\002\022\031\n\023INSUFFICIENT_SC" +
      "OPES\020\355\272\002\022\032\n\024APPLICATION_DISABLED\020\356\272\002\022\024\n\016" +
      "V1_APPLICATION\020\357\272\002\022\025\n\017V1_ACCESS_TOKEN\020\360\272",
      "\002\022!\n\033CARD_PROCESSING_NOT_ENABLED\020\361\272\002\022\021\n\013" +
      "BAD_REQUEST\020\300\270\002\022 \n\032MISSING_REQUIRED_PARA" +
      "METER\020\301\270\002\022\024\n\016INCORRECT_TYPE\020\302\270\002\022\022\n\014INVAL" +
      "ID_TIME\020\303\270\002\022\030\n\022INVALID_TIME_RANGE\020\304\270\002\022\023\n" +
      "\rINVALID_VALUE\020\305\270\002\022\024\n\016INVALID_CURSOR\020\306\270\002" +
      "\022\035\n\027UNKNOWN_QUERY_PARAMETER\020\307\270\002\022\034\n\026CONFL" +
      "ICTING_PARAMETERS\020\310\270\002\022\030\n\022EXPECTED_JSON_B" +
      "ODY\020\311\270\002\022\030\n\022INVALID_SORT_ORDER\020\312\270\002\022\024\n\016VAL" +
      "UE_TOO_LONG\020\313\270\002\022\023\n\rVALUE_TOO_LOW\020\314\270\002\022\026\n\020" +
      "EXPECTED_BOOLEAN\020\315\270\002\022\026\n\020EXPECTED_INTEGER",
      "\020\316\270\002\022\024\n\016EXPECTED_FLOAT\020\317\270\002\022\025\n\017EXPECTED_S" +
      "TRING\020\320\270\002\022\025\n\017EXPECTED_OBJECT\020\321\270\002\022\024\n\016EXPE" +
      "CTED_ARRAY\020\322\270\002\022\031\n\023INVALID_ARRAY_VALUE\020\323\270" +
      "\002\022\030\n\022INVALID_ENUM_VALUE\020\324\270\002\022\032\n\024INVALID_C" +
      "ONTENT_TYPE\020\325\270\002\022\030\n\022INVALID_FORM_VALUE\020\326\270" +
      "\002\022\023\n\rCARD_DECLINED\020\327\270\002\022\022\n\014CARD_EXPIRED\020\330" +
      "\270\002\022\030\n\022VERIFY_CVV_FAILURE\020\331\270\002\022\030\n\022VERIFY_A" +
      "VS_FAILURE\020\332\270\002\022\030\n\022INVALID_EXPIRATION\020\333\270\002" +
      "\022\035\n\027INVALID_EXPIRATION_YEAR\020\334\270\002\022\035\n\027INVAL" +
      "ID_EXPIRATION_DATE\020\347\270\002\022\022\n\014INVALID_CARD\020\350",
      "\270\002\022!\n\033DELAYED_TRANSACTION_EXPIRED\020\351\270\002\022\"\n" +
      "\034DELAYED_TRANSACTION_CANCELED\020\352\270\002\022\"\n\034DEL" +
      "AYED_TRANSACTION_CAPTURED\020\353\270\002\022 \n\032DELAYED" +
      "_TRANSACTION_FAILED\020\354\270\002\022\030\n\022CARD_TOKEN_EX" +
      "PIRED\020\355\270\002\022\025\n\017CARD_TOKEN_USED\020\356\270\002\022\025\n\017AMOU" +
      "NT_TOO_HIGH\020\357\270\002\022\033\n\025REFUND_AMOUNT_INVALID" +
      "\020\360\270\002\022\034\n\026REFUND_ALREADY_PENDING\020\361\270\002\022\034\n\026PA" +
      "YMENT_NOT_REFUNDABLE\020\362\270\002\022\017\n\tNOT_FOUND\020\320\273" +
      "\002\022\025\n\017REQUEST_TIMEOUT\020\340\276\002\022\036\n\030REQUEST_ENTI" +
      "TY_TOO_LARGE\020\324\302\002\022\034\n\026UNSUPPORTED_MEDIA_TY",
      "PE\020\234\304\002\022\022\n\014RATE_LIMITED\020\224\317\002\022\025\n\017NOT_IMPLEM" +
      "ENTED\020\264\207\003\022\031\n\023SERVICE_UNAVAILABLE\020\374\210\003B,\n(" +
      "com.squareup.protos.connect.v3.resources" +
      "P\001"
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
        }, assigner);
    internal_static_squareup_connect_v3_resources_Error_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_squareup_connect_v3_resources_Error_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_resources_Error_descriptor,
        new java.lang.String[] { "Type", "Detail", "Field", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}