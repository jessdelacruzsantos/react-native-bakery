// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/resources/location.proto

package com.squareup.protos.connect.v3.resources;

public final class LocationOuterClass {
  private LocationOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_resources_Location_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_resources_Location_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n,squareup/connect/v3/resources/location" +
      ".proto\022\035squareup.connect.v3.resources\032+s" +
      "quareup/connect/v3/resources/address.pro" +
      "to\"\370\001\n\010Location\022\n\n\002id\030\001 \001(\t\022\023\n\013business_" +
      "id\030\002 \001(\t\022\014\n\004name\030\003 \001(\t\0227\n\007address\030\004 \001(\0132" +
      "&.squareup.connect.v3.resources.Address\022" +
      "\020\n\010timezone\030\005 \001(\t\022H\n\014capabilities\030\n \003(\0162" +
      "2.squareup.connect.v3.resources.Location" +
      ".Capability\"(\n\nCapability\022\032\n\026CREDIT_CARD" +
      "_PROCESSING\020\001B,\n(com.squareup.protos.con",
      "nect.v3.resourcesP\001"
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
          com.squareup.protos.connect.v3.resources.AddressOuterClass.getDescriptor(),
        }, assigner);
    internal_static_squareup_connect_v3_resources_Location_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_squareup_connect_v3_resources_Location_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_resources_Location_descriptor,
        new java.lang.String[] { "Id", "BusinessId", "Name", "Address", "Timezone", "Capabilities", });
    com.squareup.protos.connect.v3.resources.AddressOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
