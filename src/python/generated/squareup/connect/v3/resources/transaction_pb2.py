# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: squareup/connect/v3/resources/transaction.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from squareup.connect.v3.resources import refund_pb2 as squareup_dot_connect_dot_v3_dot_resources_dot_refund__pb2
from squareup.connect.v3.resources import tender_pb2 as squareup_dot_connect_dot_v3_dot_resources_dot_tender__pb2


DESCRIPTOR = _descriptor.FileDescriptor(
  name='squareup/connect/v3/resources/transaction.proto',
  package='squareup.connect.v3.resources',
  syntax='proto2',
  serialized_pb=_b('\n/squareup/connect/v3/resources/transaction.proto\x12\x1dsquareup.connect.v3.resources\x1a*squareup/connect/v3/resources/refund.proto\x1a*squareup/connect/v3/resources/tender.proto\"\xea\x01\n\x0bTransaction\x12\n\n\x02id\x18\x01 \x01(\t\x12\x13\n\x0b\x62usiness_id\x18\x02 \x01(\t\x12\x13\n\x0blocation_id\x18\x03 \x01(\t\x12\x12\n\ncreated_at\x18\x06 \x01(\t\x12\x36\n\x07tenders\x18\x07 \x03(\x0b\x32%.squareup.connect.v3.resources.Tender\x12\x43\n\x0etender_refunds\x18\x08 \x03(\x0b\x32+.squareup.connect.v3.resources.TenderRefund\x12\x14\n\x0creference_id\x18\t \x01(\tB,\n(com.squareup.protos.connect.v3.resourcesP\x01')
  ,
  dependencies=[squareup_dot_connect_dot_v3_dot_resources_dot_refund__pb2.DESCRIPTOR,squareup_dot_connect_dot_v3_dot_resources_dot_tender__pb2.DESCRIPTOR,])
_sym_db.RegisterFileDescriptor(DESCRIPTOR)




_TRANSACTION = _descriptor.Descriptor(
  name='Transaction',
  full_name='squareup.connect.v3.resources.Transaction',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='squareup.connect.v3.resources.Transaction.id', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='business_id', full_name='squareup.connect.v3.resources.Transaction.business_id', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='location_id', full_name='squareup.connect.v3.resources.Transaction.location_id', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='created_at', full_name='squareup.connect.v3.resources.Transaction.created_at', index=3,
      number=6, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='tenders', full_name='squareup.connect.v3.resources.Transaction.tenders', index=4,
      number=7, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='tender_refunds', full_name='squareup.connect.v3.resources.Transaction.tender_refunds', index=5,
      number=8, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='reference_id', full_name='squareup.connect.v3.resources.Transaction.reference_id', index=6,
      number=9, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
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
  serialized_start=171,
  serialized_end=405,
)

_TRANSACTION.fields_by_name['tenders'].message_type = squareup_dot_connect_dot_v3_dot_resources_dot_tender__pb2._TENDER
_TRANSACTION.fields_by_name['tender_refunds'].message_type = squareup_dot_connect_dot_v3_dot_resources_dot_refund__pb2._TENDERREFUND
DESCRIPTOR.message_types_by_name['Transaction'] = _TRANSACTION

Transaction = _reflection.GeneratedProtocolMessageType('Transaction', (_message.Message,), dict(
  DESCRIPTOR = _TRANSACTION,
  __module__ = 'squareup.connect.v3.resources.transaction_pb2'
  # @@protoc_insertion_point(class_scope:squareup.connect.v3.resources.Transaction)
  ))
_sym_db.RegisterMessage(Transaction)


DESCRIPTOR.has_options = True
DESCRIPTOR._options = _descriptor._ParseOptions(descriptor_pb2.FileOptions(), _b('\n(com.squareup.protos.connect.v3.resourcesP\001'))
# @@protoc_insertion_point(module_scope)
