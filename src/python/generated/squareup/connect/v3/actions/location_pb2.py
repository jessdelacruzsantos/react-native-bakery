# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: squareup/connect/v3/actions/location.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from squareup.connect.v3.resources import error_pb2 as squareup_dot_connect_dot_v3_dot_resources_dot_error__pb2
from squareup.connect.v3.resources import location_pb2 as squareup_dot_connect_dot_v3_dot_resources_dot_location__pb2


DESCRIPTOR = _descriptor.FileDescriptor(
  name='squareup/connect/v3/actions/location.proto',
  package='squareup.connect.v3.actions',
  syntax='proto2',
  serialized_pb=_b('\n*squareup/connect/v3/actions/location.proto\x12\x1bsquareup.connect.v3.actions\x1a)squareup/connect/v3/resources/error.proto\x1a,squareup/connect/v3/resources/location.proto\"\x16\n\x14ListLocationsRequest\"\x89\x01\n\x15ListLocationsResponse\x12\x34\n\x06\x65rrors\x18\x01 \x03(\x0b\x32$.squareup.connect.v3.resources.Error\x12:\n\tlocations\x18\x02 \x03(\x0b\x32\'.squareup.connect.v3.resources.LocationB*\n&com.squareup.protos.connect.v3.actionsP\x01')
  ,
  dependencies=[squareup_dot_connect_dot_v3_dot_resources_dot_error__pb2.DESCRIPTOR,squareup_dot_connect_dot_v3_dot_resources_dot_location__pb2.DESCRIPTOR,])
_sym_db.RegisterFileDescriptor(DESCRIPTOR)




_LISTLOCATIONSREQUEST = _descriptor.Descriptor(
  name='ListLocationsRequest',
  full_name='squareup.connect.v3.actions.ListLocationsRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto2',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=164,
  serialized_end=186,
)


_LISTLOCATIONSRESPONSE = _descriptor.Descriptor(
  name='ListLocationsResponse',
  full_name='squareup.connect.v3.actions.ListLocationsResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='errors', full_name='squareup.connect.v3.actions.ListLocationsResponse.errors', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='locations', full_name='squareup.connect.v3.actions.ListLocationsResponse.locations', index=1,
      number=2, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto2',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=189,
  serialized_end=326,
)

_LISTLOCATIONSRESPONSE.fields_by_name['errors'].message_type = squareup_dot_connect_dot_v3_dot_resources_dot_error__pb2._ERROR
_LISTLOCATIONSRESPONSE.fields_by_name['locations'].message_type = squareup_dot_connect_dot_v3_dot_resources_dot_location__pb2._LOCATION
DESCRIPTOR.message_types_by_name['ListLocationsRequest'] = _LISTLOCATIONSREQUEST
DESCRIPTOR.message_types_by_name['ListLocationsResponse'] = _LISTLOCATIONSRESPONSE

ListLocationsRequest = _reflection.GeneratedProtocolMessageType('ListLocationsRequest', (_message.Message,), dict(
  DESCRIPTOR = _LISTLOCATIONSREQUEST,
  __module__ = 'squareup.connect.v3.actions.location_pb2'
  # @@protoc_insertion_point(class_scope:squareup.connect.v3.actions.ListLocationsRequest)
  ))
_sym_db.RegisterMessage(ListLocationsRequest)

ListLocationsResponse = _reflection.GeneratedProtocolMessageType('ListLocationsResponse', (_message.Message,), dict(
  DESCRIPTOR = _LISTLOCATIONSRESPONSE,
  __module__ = 'squareup.connect.v3.actions.location_pb2'
  # @@protoc_insertion_point(class_scope:squareup.connect.v3.actions.ListLocationsResponse)
  ))
_sym_db.RegisterMessage(ListLocationsResponse)


DESCRIPTOR.has_options = True
DESCRIPTOR._options = _descriptor._ParseOptions(descriptor_pb2.FileOptions(), _b('\n&com.squareup.protos.connect.v3.actionsP\001'))
# @@protoc_insertion_point(module_scope)
