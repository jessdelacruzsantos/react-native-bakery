# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: squareup/connect/v3/resources/country.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf.internal import enum_type_wrapper
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='squareup/connect/v3/resources/country.proto',
  package='squareup.connect.v3.resources',
  syntax='proto2',
  serialized_pb=_b('\n+squareup/connect/v3/resources/country.proto\x12\x1dsquareup.connect.v3.resources*\xad\x11\n\x07\x43ountry\x12\x06\n\x02ZZ\x10\x00\x12\x06\n\x02\x41\x44\x10\x14\x12\x07\n\x02\x41\x45\x10\x90\x06\x12\x06\n\x02\x41\x46\x10\x04\x12\x06\n\x02\x41G\x10\x1c\x12\x07\n\x02\x41I\x10\x94\x05\x12\x06\n\x02\x41L\x10\x08\x12\x06\n\x02\x41M\x10\x33\x12\x06\n\x02\x41O\x10\x18\x12\x06\n\x02\x41Q\x10\n\x12\x06\n\x02\x41R\x10 \x12\x06\n\x02\x41S\x10\x10\x12\x06\n\x02\x41T\x10(\x12\x06\n\x02\x41U\x10$\x12\x07\n\x02\x41W\x10\x95\x04\x12\x07\n\x02\x41X\x10\xf8\x01\x12\x06\n\x02\x41Z\x10\x1f\x12\x06\n\x02\x42\x41\x10\x46\x12\x06\n\x02\x42\x42\x10\x34\x12\x06\n\x02\x42\x44\x10\x32\x12\x06\n\x02\x42\x45\x10\x38\x12\x07\n\x02\x42\x46\x10\xd6\x06\x12\x06\n\x02\x42G\x10\x64\x12\x06\n\x02\x42H\x10\x30\x12\x06\n\x02\x42I\x10l\x12\x07\n\x02\x42J\x10\xcc\x01\x12\x07\n\x02\x42L\x10\x8c\x05\x12\x06\n\x02\x42M\x10<\x12\x06\n\x02\x42N\x10`\x12\x06\n\x02\x42O\x10\x44\x12\x07\n\x02\x42Q\x10\x97\x04\x12\x06\n\x02\x42R\x10L\x12\x06\n\x02\x42S\x10,\x12\x06\n\x02\x42T\x10@\x12\x06\n\x02\x42V\x10J\x12\x06\n\x02\x42W\x10H\x12\x06\n\x02\x42Y\x10p\x12\x06\n\x02\x42Z\x10T\x12\x06\n\x02\x43\x41\x10|\x12\x07\n\x02\x43\x43\x10\xa6\x01\x12\x07\n\x02\x43\x44\x10\xb4\x01\x12\x07\n\x02\x43\x46\x10\x8c\x01\x12\x07\n\x02\x43G\x10\xb2\x01\x12\x07\n\x02\x43H\x10\xf4\x05\x12\x07\n\x02\x43I\x10\x80\x03\x12\x07\n\x02\x43K\x10\xb8\x01\x12\x07\n\x02\x43L\x10\x98\x01\x12\x06\n\x02\x43M\x10x\x12\x07\n\x02\x43N\x10\x9c\x01\x12\x07\n\x02\x43O\x10\xaa\x01\x12\x07\n\x02\x43R\x10\xbc\x01\x12\x07\n\x02\x43U\x10\xc0\x01\x12\x07\n\x02\x43V\x10\x84\x01\x12\x07\n\x02\x43W\x10\x93\x04\x12\x07\n\x02\x43X\x10\xa2\x01\x12\x07\n\x02\x43Y\x10\xc4\x01\x12\x07\n\x02\x43Z\x10\xcb\x01\x12\x07\n\x02\x44\x45\x10\x94\x02\x12\x07\n\x02\x44J\x10\x86\x02\x12\x07\n\x02\x44K\x10\xd0\x01\x12\x07\n\x02\x44M\x10\xd4\x01\x12\x07\n\x02\x44O\x10\xd6\x01\x12\x06\n\x02\x44Z\x10\x0c\x12\x07\n\x02\x45\x43\x10\xda\x01\x12\x07\n\x02\x45\x45\x10\xe9\x01\x12\x07\n\x02\x45G\x10\xb2\x06\x12\x07\n\x02\x45H\x10\xdc\x05\x12\x07\n\x02\x45R\x10\xe8\x01\x12\x07\n\x02\x45S\x10\xd4\x05\x12\x07\n\x02\x45T\x10\xe7\x01\x12\x07\n\x02\x46I\x10\xf6\x01\x12\x07\n\x02\x46J\x10\xf2\x01\x12\x07\n\x02\x46K\x10\xee\x01\x12\x07\n\x02\x46M\x10\xc7\x04\x12\x07\n\x02\x46O\x10\xea\x01\x12\x07\n\x02\x46R\x10\xfa\x01\x12\x07\n\x02GA\x10\x8a\x02\x12\x07\n\x02GB\x10\xba\x06\x12\x07\n\x02GD\x10\xb4\x02\x12\x07\n\x02GE\x10\x8c\x02\x12\x07\n\x02GF\x10\xfe\x01\x12\x07\n\x02GG\x10\xbf\x06\x12\x07\n\x02GH\x10\xa0\x02\x12\x07\n\x02GI\x10\xa4\x02\x12\x07\n\x02GL\x10\xb0\x02\x12\x07\n\x02GM\x10\x8e\x02\x12\x07\n\x02GN\x10\xc4\x02\x12\x07\n\x02GP\x10\xb8\x02\x12\x07\n\x02GQ\x10\xe2\x01\x12\x07\n\x02GR\x10\xac\x02\x12\x07\n\x02GS\x10\xef\x01\x12\x07\n\x02GT\x10\xc0\x02\x12\x07\n\x02GU\x10\xbc\x02\x12\x07\n\x02GW\x10\xf0\x04\x12\x07\n\x02GY\x10\xc8\x02\x12\x07\n\x02HK\x10\xd8\x02\x12\x07\n\x02HM\x10\xce\x02\x12\x07\n\x02HN\x10\xd4\x02\x12\x07\n\x02HR\x10\xbf\x01\x12\x07\n\x02HT\x10\xcc\x02\x12\x07\n\x02HU\x10\xdc\x02\x12\x07\n\x02ID\x10\xe8\x02\x12\x07\n\x02IE\x10\xf4\x02\x12\x07\n\x02IL\x10\xf8\x02\x12\x07\n\x02IM\x10\xc1\x06\x12\x07\n\x02IN\x10\xe4\x02\x12\x06\n\x02IO\x10V\x12\x07\n\x02IQ\x10\xf0\x02\x12\x07\n\x02IR\x10\xec\x02\x12\x07\n\x02IS\x10\xe0\x02\x12\x07\n\x02IT\x10\xfc\x02\x12\x07\n\x02JE\x10\xc0\x06\x12\x07\n\x02JM\x10\x84\x03\x12\x07\n\x02JO\x10\x90\x03\x12\x07\n\x02JP\x10\x88\x03\x12\x07\n\x02KE\x10\x94\x03\x12\x07\n\x02KG\x10\xa1\x03\x12\x06\n\x02KH\x10t\x12\x07\n\x02KI\x10\xa8\x02\x12\x07\n\x02KM\x10\xae\x01\x12\x07\n\x02KN\x10\x93\x05\x12\x07\n\x02KP\x10\x98\x03\x12\x07\n\x02KR\x10\x9a\x03\x12\x07\n\x02KW\x10\x9e\x03\x12\x07\n\x02KY\x10\x88\x01\x12\x07\n\x02KZ\x10\x8e\x03\x12\x07\n\x02LA\x10\xa2\x03\x12\x07\n\x02LB\x10\xa6\x03\x12\x07\n\x02LC\x10\x96\x05\x12\x07\n\x02LI\x10\xb6\x03\x12\x07\n\x02LK\x10\x90\x01\x12\x07\n\x02LR\x10\xae\x03\x12\x07\n\x02LS\x10\xaa\x03\x12\x07\n\x02LT\x10\xb8\x03\x12\x07\n\x02LU\x10\xba\x03\x12\x07\n\x02LV\x10\xac\x03\x12\x07\n\x02LY\x10\xb2\x03\x12\x07\n\x02MA\x10\xf8\x03\x12\x07\n\x02MC\x10\xec\x03\x12\x07\n\x02MD\x10\xf2\x03\x12\x07\n\x02ME\x10\xf3\x03\x12\x07\n\x02MF\x10\x97\x05\x12\x07\n\x02MG\x10\xc2\x03\x12\x07\n\x02MH\x10\xc8\x04\x12\x07\n\x02MK\x10\xa7\x06\x12\x07\n\x02ML\x10\xd2\x03\x12\x06\n\x02MM\x10h\x12\x07\n\x02MN\x10\xf0\x03\x12\x07\n\x02MO\x10\xbe\x03\x12\x07\n\x02MP\x10\xc4\x04\x12\x07\n\x02MQ\x10\xda\x03\x12\x07\n\x02MR\x10\xde\x03\x12\x07\n\x02MS\x10\xf4\x03\x12\x07\n\x02MT\x10\xd6\x03\x12\x07\n\x02MU\x10\xe0\x03\x12\x07\n\x02MV\x10\xce\x03\x12\x07\n\x02MW\x10\xc6\x03\x12\x07\n\x02MX\x10\xe4\x03\x12\x07\n\x02MY\x10\xca\x03\x12\x07\n\x02MZ\x10\xfc\x03\x12\x07\n\x02NA\x10\x84\x04\x12\x07\n\x02NC\x10\x9c\x04\x12\x07\n\x02NE\x10\xb2\x04\x12\x07\n\x02NF\x10\xbe\x04\x12\x07\n\x02NG\x10\xb6\x04\x12\x07\n\x02NI\x10\xae\x04\x12\x07\n\x02NL\x10\x90\x04\x12\x07\n\x02NO\x10\xc2\x04\x12\x07\n\x02NP\x10\x8c\x04\x12\x07\n\x02NR\x10\x88\x04\x12\x07\n\x02NU\x10\xba\x04\x12\x07\n\x02NZ\x10\xaa\x04\x12\x07\n\x02OM\x10\x80\x04\x12\x07\n\x02PA\x10\xcf\x04\x12\x07\n\x02PE\x10\xdc\x04\x12\x07\n\x02PF\x10\x82\x02\x12\x07\n\x02PG\x10\xd6\x04\x12\x07\n\x02PH\x10\xe0\x04\x12\x07\n\x02PK\x10\xca\x04\x12\x07\n\x02PL\x10\xe8\x04\x12\x07\n\x02PM\x10\x9a\x05\x12\x07\n\x02PN\x10\xe4\x04\x12\x07\n\x02PR\x10\xf6\x04\x12\x07\n\x02PS\x10\x93\x02\x12\x07\n\x02PT\x10\xec\x04\x12\x07\n\x02PW\x10\xc9\x04\x12\x07\n\x02PY\x10\xd8\x04\x12\x07\n\x02QA\x10\xfa\x04\x12\x07\n\x02RE\x10\xfe\x04\x12\x07\n\x02RO\x10\x82\x05\x12\x07\n\x02RS\x10\xb0\x05\x12\x07\n\x02RU\x10\x83\x05\x12\x07\n\x02RW\x10\x86\x05\x12\x07\n\x02SA\x10\xaa\x05\x12\x06\n\x02SB\x10Z\x12\x07\n\x02SC\x10\xb2\x05\x12\x07\n\x02SD\x10\xe0\x05\x12\x07\n\x02SE\x10\xf0\x05\x12\x07\n\x02SG\x10\xbe\x05\x12\x07\n\x02SH\x10\x8e\x05\x12\x07\n\x02SI\x10\xc1\x05\x12\x07\n\x02SJ\x10\xe8\x05\x12\x07\n\x02SK\x10\xbf\x05\x12\x07\n\x02SL\x10\xb6\x05\x12\x07\n\x02SM\x10\xa2\x05\x12\x07\n\x02SN\x10\xae\x05\x12\x07\n\x02SO\x10\xc2\x05\x12\x07\n\x02SR\x10\xe4\x05\x12\x07\n\x02SS\x10\xd8\x05\x12\x07\n\x02ST\x10\xa6\x05\x12\x07\n\x02SV\x10\xde\x01\x12\x07\n\x02SX\x10\x96\x04\x12\x07\n\x02SY\x10\xf8\x05\x12\x07\n\x02SZ\x10\xec\x05\x12\x07\n\x02TC\x10\x9c\x06\x12\x07\n\x02TD\x10\x94\x01\x12\x07\n\x02TF\x10\x84\x02\x12\x07\n\x02TG\x10\x80\x06\x12\x07\n\x02TH\x10\xfc\x05\x12\x07\n\x02TJ\x10\xfa\x05\x12\x07\n\x02TK\x10\x84\x06\x12\x07\n\x02TL\x10\xf2\x04\x12\x07\n\x02TM\x10\x9b\x06\x12\x07\n\x02TN\x10\x94\x06\x12\x07\n\x02TO\x10\x88\x06\x12\x07\n\x02TR\x10\x98\x06\x12\x07\n\x02TT\x10\x8c\x06\x12\x07\n\x02TV\x10\x9e\x06\x12\x07\n\x02TW\x10\x9e\x01\x12\x07\n\x02TZ\x10\xc2\x06\x12\x07\n\x02UA\x10\xa4\x06\x12\x07\n\x02UG\x10\xa0\x06\x12\x07\n\x02UM\x10\xc5\x04\x12\x07\n\x02US\x10\xc8\x06\x12\x07\n\x02UY\x10\xda\x06\x12\x07\n\x02UZ\x10\xdc\x06\x12\x07\n\x02VA\x10\xd0\x02\x12\x07\n\x02VC\x10\x9e\x05\x12\x07\n\x02VE\x10\xde\x06\x12\x06\n\x02VG\x10\\\x12\x07\n\x02VI\x10\xd2\x06\x12\x07\n\x02VN\x10\xc0\x05\x12\x07\n\x02VU\x10\xa4\x04\x12\x07\n\x02WF\x10\xec\x06\x12\x07\n\x02WS\x10\xf2\x06\x12\x07\n\x02YE\x10\xf7\x06\x12\x07\n\x02YT\x10\xaf\x01\x12\x07\n\x02ZA\x10\xc6\x05\x12\x07\n\x02ZM\x10\xfe\x06\x12\x07\n\x02ZW\x10\xcc\x05\x42,\n(com.squareup.protos.connect.v3.resourcesP\x01')
)
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

_COUNTRY = _descriptor.EnumDescriptor(
  name='Country',
  full_name='squareup.connect.v3.resources.Country',
  filename=None,
  file=DESCRIPTOR,
  values=[
    _descriptor.EnumValueDescriptor(
      name='ZZ', index=0, number=0,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AD', index=1, number=20,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AE', index=2, number=784,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AF', index=3, number=4,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AG', index=4, number=28,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AI', index=5, number=660,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AL', index=6, number=8,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AM', index=7, number=51,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AO', index=8, number=24,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AQ', index=9, number=10,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AR', index=10, number=32,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AS', index=11, number=16,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AT', index=12, number=40,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AU', index=13, number=36,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AW', index=14, number=533,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AX', index=15, number=248,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AZ', index=16, number=31,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BA', index=17, number=70,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BB', index=18, number=52,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BD', index=19, number=50,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BE', index=20, number=56,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BF', index=21, number=854,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BG', index=22, number=100,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BH', index=23, number=48,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BI', index=24, number=108,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BJ', index=25, number=204,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BL', index=26, number=652,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BM', index=27, number=60,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BN', index=28, number=96,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BO', index=29, number=68,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BQ', index=30, number=535,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BR', index=31, number=76,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BS', index=32, number=44,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BT', index=33, number=64,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BV', index=34, number=74,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BW', index=35, number=72,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BY', index=36, number=112,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BZ', index=37, number=84,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CA', index=38, number=124,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CC', index=39, number=166,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CD', index=40, number=180,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CF', index=41, number=140,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CG', index=42, number=178,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CH', index=43, number=756,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CI', index=44, number=384,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CK', index=45, number=184,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CL', index=46, number=152,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CM', index=47, number=120,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CN', index=48, number=156,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CO', index=49, number=170,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CR', index=50, number=188,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CU', index=51, number=192,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CV', index=52, number=132,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CW', index=53, number=531,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CX', index=54, number=162,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CY', index=55, number=196,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CZ', index=56, number=203,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DE', index=57, number=276,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DJ', index=58, number=262,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DK', index=59, number=208,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DM', index=60, number=212,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DO', index=61, number=214,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DZ', index=62, number=12,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='EC', index=63, number=218,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='EE', index=64, number=233,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='EG', index=65, number=818,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='EH', index=66, number=732,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ER', index=67, number=232,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ES', index=68, number=724,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ET', index=69, number=231,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FI', index=70, number=246,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FJ', index=71, number=242,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FK', index=72, number=238,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FM', index=73, number=583,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FO', index=74, number=234,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FR', index=75, number=250,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GA', index=76, number=266,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GB', index=77, number=826,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GD', index=78, number=308,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GE', index=79, number=268,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GF', index=80, number=254,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GG', index=81, number=831,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GH', index=82, number=288,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GI', index=83, number=292,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GL', index=84, number=304,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GM', index=85, number=270,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GN', index=86, number=324,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GP', index=87, number=312,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GQ', index=88, number=226,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GR', index=89, number=300,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GS', index=90, number=239,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GT', index=91, number=320,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GU', index=92, number=316,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GW', index=93, number=624,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GY', index=94, number=328,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HK', index=95, number=344,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HM', index=96, number=334,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HN', index=97, number=340,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HR', index=98, number=191,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HT', index=99, number=332,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HU', index=100, number=348,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ID', index=101, number=360,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IE', index=102, number=372,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IL', index=103, number=376,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IM', index=104, number=833,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IN', index=105, number=356,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IO', index=106, number=86,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IQ', index=107, number=368,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IR', index=108, number=364,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IS', index=109, number=352,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IT', index=110, number=380,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='JE', index=111, number=832,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='JM', index=112, number=388,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='JO', index=113, number=400,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='JP', index=114, number=392,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KE', index=115, number=404,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KG', index=116, number=417,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KH', index=117, number=116,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KI', index=118, number=296,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KM', index=119, number=174,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KN', index=120, number=659,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KP', index=121, number=408,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KR', index=122, number=410,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KW', index=123, number=414,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KY', index=124, number=136,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KZ', index=125, number=398,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LA', index=126, number=418,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LB', index=127, number=422,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LC', index=128, number=662,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LI', index=129, number=438,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LK', index=130, number=144,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LR', index=131, number=430,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LS', index=132, number=426,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LT', index=133, number=440,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LU', index=134, number=442,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LV', index=135, number=428,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LY', index=136, number=434,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MA', index=137, number=504,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MC', index=138, number=492,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MD', index=139, number=498,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ME', index=140, number=499,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MF', index=141, number=663,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MG', index=142, number=450,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MH', index=143, number=584,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MK', index=144, number=807,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ML', index=145, number=466,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MM', index=146, number=104,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MN', index=147, number=496,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MO', index=148, number=446,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MP', index=149, number=580,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MQ', index=150, number=474,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MR', index=151, number=478,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MS', index=152, number=500,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MT', index=153, number=470,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MU', index=154, number=480,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MV', index=155, number=462,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MW', index=156, number=454,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MX', index=157, number=484,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MY', index=158, number=458,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MZ', index=159, number=508,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NA', index=160, number=516,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NC', index=161, number=540,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NE', index=162, number=562,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NF', index=163, number=574,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NG', index=164, number=566,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NI', index=165, number=558,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NL', index=166, number=528,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NO', index=167, number=578,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NP', index=168, number=524,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NR', index=169, number=520,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NU', index=170, number=570,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NZ', index=171, number=554,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='OM', index=172, number=512,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PA', index=173, number=591,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PE', index=174, number=604,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PF', index=175, number=258,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PG', index=176, number=598,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PH', index=177, number=608,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PK', index=178, number=586,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PL', index=179, number=616,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PM', index=180, number=666,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PN', index=181, number=612,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PR', index=182, number=630,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PS', index=183, number=275,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PT', index=184, number=620,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PW', index=185, number=585,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PY', index=186, number=600,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='QA', index=187, number=634,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RE', index=188, number=638,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RO', index=189, number=642,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RS', index=190, number=688,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RU', index=191, number=643,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RW', index=192, number=646,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SA', index=193, number=682,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SB', index=194, number=90,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SC', index=195, number=690,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SD', index=196, number=736,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SE', index=197, number=752,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SG', index=198, number=702,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SH', index=199, number=654,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SI', index=200, number=705,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SJ', index=201, number=744,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SK', index=202, number=703,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SL', index=203, number=694,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SM', index=204, number=674,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SN', index=205, number=686,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SO', index=206, number=706,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SR', index=207, number=740,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SS', index=208, number=728,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ST', index=209, number=678,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SV', index=210, number=222,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SX', index=211, number=534,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SY', index=212, number=760,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SZ', index=213, number=748,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TC', index=214, number=796,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TD', index=215, number=148,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TF', index=216, number=260,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TG', index=217, number=768,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TH', index=218, number=764,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TJ', index=219, number=762,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TK', index=220, number=772,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TL', index=221, number=626,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TM', index=222, number=795,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TN', index=223, number=788,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TO', index=224, number=776,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TR', index=225, number=792,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TT', index=226, number=780,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TV', index=227, number=798,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TW', index=228, number=158,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TZ', index=229, number=834,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UA', index=230, number=804,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UG', index=231, number=800,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UM', index=232, number=581,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='US', index=233, number=840,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UY', index=234, number=858,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UZ', index=235, number=860,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VA', index=236, number=336,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VC', index=237, number=670,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VE', index=238, number=862,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VG', index=239, number=92,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VI', index=240, number=850,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VN', index=241, number=704,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VU', index=242, number=548,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='WF', index=243, number=876,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='WS', index=244, number=882,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='YE', index=245, number=887,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='YT', index=246, number=175,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ZA', index=247, number=710,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ZM', index=248, number=894,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ZW', index=249, number=716,
      options=None,
      type=None),
  ],
  containing_type=None,
  options=None,
  serialized_start=79,
  serialized_end=2300,
)
_sym_db.RegisterEnumDescriptor(_COUNTRY)

Country = enum_type_wrapper.EnumTypeWrapper(_COUNTRY)
ZZ = 0
AD = 20
AE = 784
AF = 4
AG = 28
AI = 660
AL = 8
AM = 51
AO = 24
AQ = 10
AR = 32
AS = 16
AT = 40
AU = 36
AW = 533
AX = 248
AZ = 31
BA = 70
BB = 52
BD = 50
BE = 56
BF = 854
BG = 100
BH = 48
BI = 108
BJ = 204
BL = 652
BM = 60
BN = 96
BO = 68
BQ = 535
BR = 76
BS = 44
BT = 64
BV = 74
BW = 72
BY = 112
BZ = 84
CA = 124
CC = 166
CD = 180
CF = 140
CG = 178
CH = 756
CI = 384
CK = 184
CL = 152
CM = 120
CN = 156
CO = 170
CR = 188
CU = 192
CV = 132
CW = 531
CX = 162
CY = 196
CZ = 203
DE = 276
DJ = 262
DK = 208
DM = 212
DO = 214
DZ = 12
EC = 218
EE = 233
EG = 818
EH = 732
ER = 232
ES = 724
ET = 231
FI = 246
FJ = 242
FK = 238
FM = 583
FO = 234
FR = 250
GA = 266
GB = 826
GD = 308
GE = 268
GF = 254
GG = 831
GH = 288
GI = 292
GL = 304
GM = 270
GN = 324
GP = 312
GQ = 226
GR = 300
GS = 239
GT = 320
GU = 316
GW = 624
GY = 328
HK = 344
HM = 334
HN = 340
HR = 191
HT = 332
HU = 348
ID = 360
IE = 372
IL = 376
IM = 833
IN = 356
IO = 86
IQ = 368
IR = 364
IS = 352
IT = 380
JE = 832
JM = 388
JO = 400
JP = 392
KE = 404
KG = 417
KH = 116
KI = 296
KM = 174
KN = 659
KP = 408
KR = 410
KW = 414
KY = 136
KZ = 398
LA = 418
LB = 422
LC = 662
LI = 438
LK = 144
LR = 430
LS = 426
LT = 440
LU = 442
LV = 428
LY = 434
MA = 504
MC = 492
MD = 498
ME = 499
MF = 663
MG = 450
MH = 584
MK = 807
ML = 466
MM = 104
MN = 496
MO = 446
MP = 580
MQ = 474
MR = 478
MS = 500
MT = 470
MU = 480
MV = 462
MW = 454
MX = 484
MY = 458
MZ = 508
NA = 516
NC = 540
NE = 562
NF = 574
NG = 566
NI = 558
NL = 528
NO = 578
NP = 524
NR = 520
NU = 570
NZ = 554
OM = 512
PA = 591
PE = 604
PF = 258
PG = 598
PH = 608
PK = 586
PL = 616
PM = 666
PN = 612
PR = 630
PS = 275
PT = 620
PW = 585
PY = 600
QA = 634
RE = 638
RO = 642
RS = 688
RU = 643
RW = 646
SA = 682
SB = 90
SC = 690
SD = 736
SE = 752
SG = 702
SH = 654
SI = 705
SJ = 744
SK = 703
SL = 694
SM = 674
SN = 686
SO = 706
SR = 740
SS = 728
ST = 678
SV = 222
SX = 534
SY = 760
SZ = 748
TC = 796
TD = 148
TF = 260
TG = 768
TH = 764
TJ = 762
TK = 772
TL = 626
TM = 795
TN = 788
TO = 776
TR = 792
TT = 780
TV = 798
TW = 158
TZ = 834
UA = 804
UG = 800
UM = 581
US = 840
UY = 858
UZ = 860
VA = 336
VC = 670
VE = 862
VG = 92
VI = 850
VN = 704
VU = 548
WF = 876
WS = 882
YE = 887
YT = 175
ZA = 710
ZM = 894
ZW = 716


DESCRIPTOR.enum_types_by_name['Country'] = _COUNTRY


DESCRIPTOR.has_options = True
DESCRIPTOR._options = _descriptor._ParseOptions(descriptor_pb2.FileOptions(), _b('\n(com.squareup.protos.connect.v3.resourcesP\001'))
# @@protoc_insertion_point(module_scope)
