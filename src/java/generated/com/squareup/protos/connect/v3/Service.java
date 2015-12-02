// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/service.proto

package com.squareup.protos.connect.v3;

public final class Service {
  private Service() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!squareup/connect/v3/service.proto\022\023squ" +
      "areup.connect.v3\032,squareup/connect/v3/ac" +
      "tions/card_nonce.proto\032(squareup/connect" +
      "/v3/actions/charge.proto\032*squareup/conne" +
      "ct/v3/actions/customer.proto\032*squareup/c" +
      "onnect/v3/actions/location.proto\032(square" +
      "up/connect/v3/actions/refund.proto\032-squa" +
      "reup/connect/v3/actions/transaction.prot" +
      "o2\365\014\n\017SquareConnectV3\022a\n\006Charge\022*.square" +
      "up.connect.v3.actions.ChargeRequest\032+.sq",
      "uareup.connect.v3.actions.ChargeResponse" +
      "\022s\n\014RefundTender\0220.squareup.connect.v3.a" +
      "ctions.RefundTenderRequest\0321.squareup.co" +
      "nnect.v3.actions.RefundTenderResponse\022v\n" +
      "\rListLocations\0221.squareup.connect.v3.act" +
      "ions.ListLocationsRequest\0322.squareup.con" +
      "nect.v3.actions.ListLocationsResponse\022|\n" +
      "\017CreateCardNonce\0223.squareup.connect.v3.a" +
      "ctions.CreateCardNonceRequest\0324.squareup" +
      ".connect.v3.actions.CreateCardNonceRespo",
      "nse\022y\n\016UpsertCustomer\0222.squareup.connect" +
      ".v3.actions.UpsertCustomerRequest\0323.squa" +
      "reup.connect.v3.actions.UpsertCustomerRe" +
      "sponse\022v\n\rListCustomers\0221.squareup.conne" +
      "ct.v3.actions.ListCustomersRequest\0322.squ" +
      "areup.connect.v3.actions.ListCustomersRe" +
      "sponse\022\177\n\020RetrieveCustomer\0224.squareup.co" +
      "nnect.v3.actions.RetrieveCustomerRequest" +
      "\0325.squareup.connect.v3.actions.RetrieveC" +
      "ustomerResponse\022\205\001\n\022CreateCustomerCard\0226",
      ".squareup.connect.v3.actions.CreateCusto" +
      "merCardRequest\0327.squareup.connect.v3.act" +
      "ions.CreateCustomerCardResponse\022\205\001\n\022Dele" +
      "teCustomerCard\0226.squareup.connect.v3.act" +
      "ions.DeleteCustomerCardRequest\0327.squareu" +
      "p.connect.v3.actions.DeleteCustomerCardR" +
      "esponse\022\205\001\n\022CaptureTransaction\0226.squareu" +
      "p.connect.v3.actions.CaptureTransactionR" +
      "equest\0327.squareup.connect.v3.actions.Cap" +
      "tureTransactionResponse\022|\n\017VoidTransacti",
      "on\0223.squareup.connect.v3.actions.VoidTra" +
      "nsactionRequest\0324.squareup.connect.v3.ac" +
      "tions.VoidTransactionResponse\022\177\n\020ListTra" +
      "nsactions\0224.squareup.connect.v3.actions." +
      "ListTransactionsRequest\0325.squareup.conne" +
      "ct.v3.actions.ListTransactionsResponse\022\210" +
      "\001\n\023RetrieveTransaction\0227.squareup.connec" +
      "t.v3.actions.RetrieveTransactionRequest\032" +
      "8.squareup.connect.v3.actions.RetrieveTr" +
      "ansactionResponseB\"\n\036com.squareup.protos",
      ".connect.v3P\001"
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
          com.squareup.protos.connect.v3.actions.CardNonce.getDescriptor(),
          com.squareup.protos.connect.v3.actions.Charge.getDescriptor(),
          com.squareup.protos.connect.v3.actions.Customer.getDescriptor(),
          com.squareup.protos.connect.v3.actions.Location.getDescriptor(),
          com.squareup.protos.connect.v3.actions.Refund.getDescriptor(),
          com.squareup.protos.connect.v3.actions.Transaction.getDescriptor(),
        }, assigner);
    com.squareup.protos.connect.v3.actions.CardNonce.getDescriptor();
    com.squareup.protos.connect.v3.actions.Charge.getDescriptor();
    com.squareup.protos.connect.v3.actions.Customer.getDescriptor();
    com.squareup.protos.connect.v3.actions.Location.getDescriptor();
    com.squareup.protos.connect.v3.actions.Refund.getDescriptor();
    com.squareup.protos.connect.v3.actions.Transaction.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
