// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: squareup/connect/v3/actions/location.proto

package com.squareup.protos.connect.v3.actions;

public final class Location {
  private Location() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_ListLocationsRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_ListLocationsRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_squareup_connect_v3_actions_ListLocationsResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_squareup_connect_v3_actions_ListLocationsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n*squareup/connect/v3/actions/location.p" +
      "roto\022\033squareup.connect.v3.actions\032)squar" +
      "eup/connect/v3/resources/error.proto\032,sq" +
      "uareup/connect/v3/resources/location.pro" +
      "to\"\026\n\024ListLocationsRequest\"\211\001\n\025ListLocat" +
      "ionsResponse\0224\n\006errors\030\001 \003(\0132$.squareup." +
      "connect.v3.resources.Error\022:\n\tlocations\030" +
      "\002 \003(\0132\'.squareup.connect.v3.resources.Lo" +
      "cationB*\n&com.squareup.protos.connect.v3" +
      ".actionsP\001"
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
          com.squareup.protos.connect.v3.resources.LocationOuterClass.getDescriptor(),
        }, assigner);
    internal_static_squareup_connect_v3_actions_ListLocationsRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_squareup_connect_v3_actions_ListLocationsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_ListLocationsRequest_descriptor,
        new java.lang.String[] { });
    internal_static_squareup_connect_v3_actions_ListLocationsResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_squareup_connect_v3_actions_ListLocationsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_squareup_connect_v3_actions_ListLocationsResponse_descriptor,
        new java.lang.String[] { "Errors", "Locations", });
    com.squareup.protos.connect.v3.resources.ErrorOuterClass.getDescriptor();
    com.squareup.protos.connect.v3.resources.LocationOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}