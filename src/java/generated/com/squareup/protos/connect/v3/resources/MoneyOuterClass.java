// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/resources/money.proto

package com.squareup.protos.connect.v3.resources;

public final class MoneyOuterClass {
  private MoneyOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_resources_Money_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_resources_Money_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n)squareup/connect/v3/resources/money.pr" +
      "oto\022\035squareup.connect.v3.resources\"R\n\005Mo" +
      "ney\022\016\n\006amount\030\001 \001(\003\0229\n\010currency\030\002 \001(\0162\'." +
      "squareup.connect.v3.resources.Currency*\376" +
      "\r\n\010Currency\022\010\n\003AED\020\220\006\022\010\n\003AFN\020\313\007\022\007\n\003ALL\020\010" +
      "\022\007\n\003AMD\0203\022\010\n\003ANG\020\224\004\022\010\n\003AOA\020\315\007\022\007\n\003ARS\020 \022\007" +
      "\n\003AUD\020$\022\010\n\003AWG\020\225\004\022\010\n\003AZN\020\260\007\022\010\n\003BAM\020\321\007\022\007\n" +
      "\003BBD\0204\022\007\n\003BDT\0202\022\010\n\003BGN\020\317\007\022\007\n\003BHD\0200\022\007\n\003BI" +
      "F\020l\022\007\n\003BMD\020<\022\007\n\003BND\020`\022\007\n\003BOB\020D\022\010\n\003BOV\020\330\007" +
      "\022\010\n\003BRL\020\332\007\022\007\n\003BSD\020,\022\007\n\003BTN\020@\022\007\n\003BWP\020H\022\010\n",
      "\003BYR\020\316\007\022\007\n\003BZD\020T\022\007\n\003CAD\020|\022\010\n\003CDF\020\320\007\022\010\n\003C" +
      "HE\020\263\007\022\010\n\003CHF\020\364\005\022\010\n\003CHW\020\264\007\022\010\n\003CLF\020\336\007\022\010\n\003C" +
      "LP\020\230\001\022\010\n\003CNY\020\234\001\022\010\n\003COP\020\252\001\022\010\n\003COU\020\312\007\022\010\n\003C" +
      "RC\020\274\001\022\010\n\003CUC\020\243\007\022\010\n\003CUP\020\300\001\022\010\n\003CVE\020\204\001\022\010\n\003C" +
      "ZK\020\313\001\022\010\n\003DJF\020\206\002\022\010\n\003DKK\020\320\001\022\010\n\003DOP\020\326\001\022\007\n\003D" +
      "ZD\020\014\022\010\n\003EGP\020\262\006\022\010\n\003ERN\020\350\001\022\010\n\003ETB\020\346\001\022\010\n\003EU" +
      "R\020\322\007\022\010\n\003FJD\020\362\001\022\010\n\003FKP\020\356\001\022\010\n\003GBP\020\272\006\022\010\n\003GE" +
      "L\020\325\007\022\010\n\003GHS\020\250\007\022\010\n\003GIP\020\244\002\022\010\n\003GMD\020\216\002\022\010\n\003GN" +
      "F\020\304\002\022\010\n\003GTQ\020\300\002\022\010\n\003GYD\020\310\002\022\010\n\003HKD\020\330\002\022\010\n\003HN" +
      "L\020\324\002\022\010\n\003HRK\020\277\001\022\010\n\003HTG\020\314\002\022\010\n\003HUF\020\334\002\022\010\n\003ID",
      "R\020\350\002\022\010\n\003ILS\020\370\002\022\010\n\003INR\020\344\002\022\010\n\003IQD\020\360\002\022\010\n\003IR" +
      "R\020\354\002\022\010\n\003ISK\020\340\002\022\010\n\003JMD\020\204\003\022\010\n\003JOD\020\220\003\022\010\n\003JP" +
      "Y\020\210\003\022\010\n\003KES\020\224\003\022\010\n\003KGS\020\241\003\022\007\n\003KHR\020t\022\010\n\003KMF" +
      "\020\256\001\022\010\n\003KPW\020\230\003\022\010\n\003KRW\020\232\003\022\010\n\003KWD\020\236\003\022\010\n\003KYD" +
      "\020\210\001\022\010\n\003KZT\020\216\003\022\010\n\003LAK\020\242\003\022\010\n\003LBP\020\246\003\022\010\n\003LKR" +
      "\020\220\001\022\010\n\003LRD\020\256\003\022\010\n\003LSL\020\252\003\022\010\n\003LTL\020\270\003\022\010\n\003LVL" +
      "\020\254\003\022\010\n\003LYD\020\262\003\022\010\n\003MAD\020\370\003\022\010\n\003MDL\020\362\003\022\010\n\003MGA" +
      "\020\311\007\022\010\n\003MKD\020\247\006\022\007\n\003MMK\020h\022\010\n\003MNT\020\360\003\022\010\n\003MOP\020" +
      "\276\003\022\010\n\003MRO\020\336\003\022\010\n\003MUR\020\340\003\022\010\n\003MVR\020\316\003\022\010\n\003MWK\020" +
      "\306\003\022\010\n\003MXN\020\344\003\022\010\n\003MXV\020\323\007\022\010\n\003MYR\020\312\003\022\010\n\003MZN\020",
      "\257\007\022\010\n\003NAD\020\204\004\022\010\n\003NGN\020\266\004\022\010\n\003NIO\020\256\004\022\010\n\003NOK\020" +
      "\302\004\022\010\n\003NPR\020\214\004\022\010\n\003NZD\020\252\004\022\010\n\003OMR\020\200\004\022\010\n\003PAB\020" +
      "\316\004\022\010\n\003PEN\020\334\004\022\010\n\003PGK\020\326\004\022\010\n\003PHP\020\340\004\022\010\n\003PKR\020" +
      "\312\004\022\010\n\003PLN\020\331\007\022\010\n\003PYG\020\330\004\022\010\n\003QAR\020\372\004\022\010\n\003RON\020" +
      "\262\007\022\010\n\003RSD\020\255\007\022\010\n\003RUB\020\203\005\022\010\n\003RWF\020\206\005\022\010\n\003SAR\020" +
      "\252\005\022\007\n\003SBD\020Z\022\010\n\003SCR\020\262\005\022\010\n\003SDG\020\252\007\022\010\n\003SEK\020\360" +
      "\005\022\010\n\003SGD\020\276\005\022\010\n\003SHP\020\216\005\022\010\n\003SLL\020\266\005\022\010\n\003SOS\020\302" +
      "\005\022\010\n\003SRD\020\310\007\022\010\n\003SSP\020\330\005\022\010\n\003STD\020\246\005\022\010\n\003SVC\020\336" +
      "\001\022\010\n\003SYP\020\370\005\022\010\n\003SZL\020\354\005\022\010\n\003THB\020\374\005\022\010\n\003TJS\020\314" +
      "\007\022\010\n\003TMT\020\246\007\022\010\n\003TND\020\224\006\022\010\n\003TOP\020\210\006\022\010\n\003TRY\020\265",
      "\007\022\010\n\003TTD\020\214\006\022\010\n\003TWD\020\205\007\022\010\n\003TZS\020\302\006\022\010\n\003UAH\020\324" +
      "\007\022\010\n\003UGX\020\240\006\022\010\n\003USD\020\310\006\022\010\n\003USN\020\345\007\022\010\n\003USS\020\346" +
      "\007\022\010\n\003UYI\020\254\007\022\010\n\003UYU\020\332\006\022\010\n\003UZS\020\334\006\022\010\n\003VEF\020\251" +
      "\007\022\010\n\003VND\020\300\005\022\010\n\003VUV\020\244\004\022\010\n\003WST\020\362\006\022\010\n\003XAF\020\266" +
      "\007\022\010\n\003XAG\020\301\007\022\010\n\003XAU\020\277\007\022\010\n\003XBA\020\273\007\022\010\n\003XBB\020\274" +
      "\007\022\010\n\003XBC\020\275\007\022\010\n\003XBD\020\276\007\022\010\n\003XCD\020\267\007\022\010\n\003XDR\020\300" +
      "\007\022\010\n\003XOF\020\270\007\022\010\n\003XPD\020\304\007\022\010\n\003XPF\020\271\007\022\010\n\003XPT\020\302" +
      "\007\022\010\n\003XTS\020\303\007\022\010\n\003XXX\020\347\007\022\010\n\003YER\020\366\006\022\010\n\003ZAR\020\306" +
      "\005\022\010\n\003ZMK\020\376\006\022\010\n\003ZMW\020\307\007\022\010\n\003BTC\020\351\007B,\n(com.s" +
      "quareup.protos.connect.v3.resourcesP\001"
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
    internal_static_squareup_connect_v3_resources_Money_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_squareup_connect_v3_resources_Money_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_resources_Money_descriptor,
        new java.lang.String[] { "Amount", "Currency", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}