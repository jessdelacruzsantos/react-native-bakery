# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: squareup/connect/v3/resources/money.proto

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
  name='squareup/connect/v3/resources/money.proto',
  package='squareup.connect.v3.resources',
  syntax='proto2',
  serialized_pb=_b('\n)squareup/connect/v3/resources/money.proto\x12\x1dsquareup.connect.v3.resources\"R\n\x05Money\x12\x0e\n\x06\x61mount\x18\x01 \x01(\x03\x12\x39\n\x08\x63urrency\x18\x02 \x01(\x0e\x32\'.squareup.connect.v3.resources.Currency*\xfe\r\n\x08\x43urrency\x12\x08\n\x03\x41\x45\x44\x10\x90\x06\x12\x08\n\x03\x41\x46N\x10\xcb\x07\x12\x07\n\x03\x41LL\x10\x08\x12\x07\n\x03\x41MD\x10\x33\x12\x08\n\x03\x41NG\x10\x94\x04\x12\x08\n\x03\x41OA\x10\xcd\x07\x12\x07\n\x03\x41RS\x10 \x12\x07\n\x03\x41UD\x10$\x12\x08\n\x03\x41WG\x10\x95\x04\x12\x08\n\x03\x41ZN\x10\xb0\x07\x12\x08\n\x03\x42\x41M\x10\xd1\x07\x12\x07\n\x03\x42\x42\x44\x10\x34\x12\x07\n\x03\x42\x44T\x10\x32\x12\x08\n\x03\x42GN\x10\xcf\x07\x12\x07\n\x03\x42HD\x10\x30\x12\x07\n\x03\x42IF\x10l\x12\x07\n\x03\x42MD\x10<\x12\x07\n\x03\x42ND\x10`\x12\x07\n\x03\x42OB\x10\x44\x12\x08\n\x03\x42OV\x10\xd8\x07\x12\x08\n\x03\x42RL\x10\xda\x07\x12\x07\n\x03\x42SD\x10,\x12\x07\n\x03\x42TN\x10@\x12\x07\n\x03\x42WP\x10H\x12\x08\n\x03\x42YR\x10\xce\x07\x12\x07\n\x03\x42ZD\x10T\x12\x07\n\x03\x43\x41\x44\x10|\x12\x08\n\x03\x43\x44\x46\x10\xd0\x07\x12\x08\n\x03\x43HE\x10\xb3\x07\x12\x08\n\x03\x43HF\x10\xf4\x05\x12\x08\n\x03\x43HW\x10\xb4\x07\x12\x08\n\x03\x43LF\x10\xde\x07\x12\x08\n\x03\x43LP\x10\x98\x01\x12\x08\n\x03\x43NY\x10\x9c\x01\x12\x08\n\x03\x43OP\x10\xaa\x01\x12\x08\n\x03\x43OU\x10\xca\x07\x12\x08\n\x03\x43RC\x10\xbc\x01\x12\x08\n\x03\x43UC\x10\xa3\x07\x12\x08\n\x03\x43UP\x10\xc0\x01\x12\x08\n\x03\x43VE\x10\x84\x01\x12\x08\n\x03\x43ZK\x10\xcb\x01\x12\x08\n\x03\x44JF\x10\x86\x02\x12\x08\n\x03\x44KK\x10\xd0\x01\x12\x08\n\x03\x44OP\x10\xd6\x01\x12\x07\n\x03\x44ZD\x10\x0c\x12\x08\n\x03\x45GP\x10\xb2\x06\x12\x08\n\x03\x45RN\x10\xe8\x01\x12\x08\n\x03\x45TB\x10\xe6\x01\x12\x08\n\x03\x45UR\x10\xd2\x07\x12\x08\n\x03\x46JD\x10\xf2\x01\x12\x08\n\x03\x46KP\x10\xee\x01\x12\x08\n\x03GBP\x10\xba\x06\x12\x08\n\x03GEL\x10\xd5\x07\x12\x08\n\x03GHS\x10\xa8\x07\x12\x08\n\x03GIP\x10\xa4\x02\x12\x08\n\x03GMD\x10\x8e\x02\x12\x08\n\x03GNF\x10\xc4\x02\x12\x08\n\x03GTQ\x10\xc0\x02\x12\x08\n\x03GYD\x10\xc8\x02\x12\x08\n\x03HKD\x10\xd8\x02\x12\x08\n\x03HNL\x10\xd4\x02\x12\x08\n\x03HRK\x10\xbf\x01\x12\x08\n\x03HTG\x10\xcc\x02\x12\x08\n\x03HUF\x10\xdc\x02\x12\x08\n\x03IDR\x10\xe8\x02\x12\x08\n\x03ILS\x10\xf8\x02\x12\x08\n\x03INR\x10\xe4\x02\x12\x08\n\x03IQD\x10\xf0\x02\x12\x08\n\x03IRR\x10\xec\x02\x12\x08\n\x03ISK\x10\xe0\x02\x12\x08\n\x03JMD\x10\x84\x03\x12\x08\n\x03JOD\x10\x90\x03\x12\x08\n\x03JPY\x10\x88\x03\x12\x08\n\x03KES\x10\x94\x03\x12\x08\n\x03KGS\x10\xa1\x03\x12\x07\n\x03KHR\x10t\x12\x08\n\x03KMF\x10\xae\x01\x12\x08\n\x03KPW\x10\x98\x03\x12\x08\n\x03KRW\x10\x9a\x03\x12\x08\n\x03KWD\x10\x9e\x03\x12\x08\n\x03KYD\x10\x88\x01\x12\x08\n\x03KZT\x10\x8e\x03\x12\x08\n\x03LAK\x10\xa2\x03\x12\x08\n\x03LBP\x10\xa6\x03\x12\x08\n\x03LKR\x10\x90\x01\x12\x08\n\x03LRD\x10\xae\x03\x12\x08\n\x03LSL\x10\xaa\x03\x12\x08\n\x03LTL\x10\xb8\x03\x12\x08\n\x03LVL\x10\xac\x03\x12\x08\n\x03LYD\x10\xb2\x03\x12\x08\n\x03MAD\x10\xf8\x03\x12\x08\n\x03MDL\x10\xf2\x03\x12\x08\n\x03MGA\x10\xc9\x07\x12\x08\n\x03MKD\x10\xa7\x06\x12\x07\n\x03MMK\x10h\x12\x08\n\x03MNT\x10\xf0\x03\x12\x08\n\x03MOP\x10\xbe\x03\x12\x08\n\x03MRO\x10\xde\x03\x12\x08\n\x03MUR\x10\xe0\x03\x12\x08\n\x03MVR\x10\xce\x03\x12\x08\n\x03MWK\x10\xc6\x03\x12\x08\n\x03MXN\x10\xe4\x03\x12\x08\n\x03MXV\x10\xd3\x07\x12\x08\n\x03MYR\x10\xca\x03\x12\x08\n\x03MZN\x10\xaf\x07\x12\x08\n\x03NAD\x10\x84\x04\x12\x08\n\x03NGN\x10\xb6\x04\x12\x08\n\x03NIO\x10\xae\x04\x12\x08\n\x03NOK\x10\xc2\x04\x12\x08\n\x03NPR\x10\x8c\x04\x12\x08\n\x03NZD\x10\xaa\x04\x12\x08\n\x03OMR\x10\x80\x04\x12\x08\n\x03PAB\x10\xce\x04\x12\x08\n\x03PEN\x10\xdc\x04\x12\x08\n\x03PGK\x10\xd6\x04\x12\x08\n\x03PHP\x10\xe0\x04\x12\x08\n\x03PKR\x10\xca\x04\x12\x08\n\x03PLN\x10\xd9\x07\x12\x08\n\x03PYG\x10\xd8\x04\x12\x08\n\x03QAR\x10\xfa\x04\x12\x08\n\x03RON\x10\xb2\x07\x12\x08\n\x03RSD\x10\xad\x07\x12\x08\n\x03RUB\x10\x83\x05\x12\x08\n\x03RWF\x10\x86\x05\x12\x08\n\x03SAR\x10\xaa\x05\x12\x07\n\x03SBD\x10Z\x12\x08\n\x03SCR\x10\xb2\x05\x12\x08\n\x03SDG\x10\xaa\x07\x12\x08\n\x03SEK\x10\xf0\x05\x12\x08\n\x03SGD\x10\xbe\x05\x12\x08\n\x03SHP\x10\x8e\x05\x12\x08\n\x03SLL\x10\xb6\x05\x12\x08\n\x03SOS\x10\xc2\x05\x12\x08\n\x03SRD\x10\xc8\x07\x12\x08\n\x03SSP\x10\xd8\x05\x12\x08\n\x03STD\x10\xa6\x05\x12\x08\n\x03SVC\x10\xde\x01\x12\x08\n\x03SYP\x10\xf8\x05\x12\x08\n\x03SZL\x10\xec\x05\x12\x08\n\x03THB\x10\xfc\x05\x12\x08\n\x03TJS\x10\xcc\x07\x12\x08\n\x03TMT\x10\xa6\x07\x12\x08\n\x03TND\x10\x94\x06\x12\x08\n\x03TOP\x10\x88\x06\x12\x08\n\x03TRY\x10\xb5\x07\x12\x08\n\x03TTD\x10\x8c\x06\x12\x08\n\x03TWD\x10\x85\x07\x12\x08\n\x03TZS\x10\xc2\x06\x12\x08\n\x03UAH\x10\xd4\x07\x12\x08\n\x03UGX\x10\xa0\x06\x12\x08\n\x03USD\x10\xc8\x06\x12\x08\n\x03USN\x10\xe5\x07\x12\x08\n\x03USS\x10\xe6\x07\x12\x08\n\x03UYI\x10\xac\x07\x12\x08\n\x03UYU\x10\xda\x06\x12\x08\n\x03UZS\x10\xdc\x06\x12\x08\n\x03VEF\x10\xa9\x07\x12\x08\n\x03VND\x10\xc0\x05\x12\x08\n\x03VUV\x10\xa4\x04\x12\x08\n\x03WST\x10\xf2\x06\x12\x08\n\x03XAF\x10\xb6\x07\x12\x08\n\x03XAG\x10\xc1\x07\x12\x08\n\x03XAU\x10\xbf\x07\x12\x08\n\x03XBA\x10\xbb\x07\x12\x08\n\x03XBB\x10\xbc\x07\x12\x08\n\x03XBC\x10\xbd\x07\x12\x08\n\x03XBD\x10\xbe\x07\x12\x08\n\x03XCD\x10\xb7\x07\x12\x08\n\x03XDR\x10\xc0\x07\x12\x08\n\x03XOF\x10\xb8\x07\x12\x08\n\x03XPD\x10\xc4\x07\x12\x08\n\x03XPF\x10\xb9\x07\x12\x08\n\x03XPT\x10\xc2\x07\x12\x08\n\x03XTS\x10\xc3\x07\x12\x08\n\x03XXX\x10\xe7\x07\x12\x08\n\x03YER\x10\xf6\x06\x12\x08\n\x03ZAR\x10\xc6\x05\x12\x08\n\x03ZMK\x10\xfe\x06\x12\x08\n\x03ZMW\x10\xc7\x07\x12\x08\n\x03\x42TC\x10\xe9\x07\x42,\n(com.squareup.protos.connect.v3.resourcesP\x01')
)
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

_CURRENCY = _descriptor.EnumDescriptor(
  name='Currency',
  full_name='squareup.connect.v3.resources.Currency',
  filename=None,
  file=DESCRIPTOR,
  values=[
    _descriptor.EnumValueDescriptor(
      name='AED', index=0, number=784,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AFN', index=1, number=971,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ALL', index=2, number=8,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AMD', index=3, number=51,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ANG', index=4, number=532,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AOA', index=5, number=973,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ARS', index=6, number=32,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AUD', index=7, number=36,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AWG', index=8, number=533,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='AZN', index=9, number=944,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BAM', index=10, number=977,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BBD', index=11, number=52,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BDT', index=12, number=50,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BGN', index=13, number=975,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BHD', index=14, number=48,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BIF', index=15, number=108,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BMD', index=16, number=60,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BND', index=17, number=96,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BOB', index=18, number=68,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BOV', index=19, number=984,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BRL', index=20, number=986,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BSD', index=21, number=44,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BTN', index=22, number=64,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BWP', index=23, number=72,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BYR', index=24, number=974,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BZD', index=25, number=84,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CAD', index=26, number=124,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CDF', index=27, number=976,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CHE', index=28, number=947,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CHF', index=29, number=756,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CHW', index=30, number=948,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CLF', index=31, number=990,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CLP', index=32, number=152,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CNY', index=33, number=156,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='COP', index=34, number=170,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='COU', index=35, number=970,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CRC', index=36, number=188,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CUC', index=37, number=931,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CUP', index=38, number=192,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CVE', index=39, number=132,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='CZK', index=40, number=203,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DJF', index=41, number=262,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DKK', index=42, number=208,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DOP', index=43, number=214,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DZD', index=44, number=12,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='EGP', index=45, number=818,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ERN', index=46, number=232,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ETB', index=47, number=230,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='EUR', index=48, number=978,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FJD', index=49, number=242,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='FKP', index=50, number=238,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GBP', index=51, number=826,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GEL', index=52, number=981,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GHS', index=53, number=936,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GIP', index=54, number=292,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GMD', index=55, number=270,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GNF', index=56, number=324,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GTQ', index=57, number=320,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='GYD', index=58, number=328,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HKD', index=59, number=344,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HNL', index=60, number=340,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HRK', index=61, number=191,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HTG', index=62, number=332,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='HUF', index=63, number=348,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IDR', index=64, number=360,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ILS', index=65, number=376,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='INR', index=66, number=356,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IQD', index=67, number=368,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='IRR', index=68, number=364,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ISK', index=69, number=352,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='JMD', index=70, number=388,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='JOD', index=71, number=400,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='JPY', index=72, number=392,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KES', index=73, number=404,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KGS', index=74, number=417,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KHR', index=75, number=116,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KMF', index=76, number=174,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KPW', index=77, number=408,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KRW', index=78, number=410,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KWD', index=79, number=414,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KYD', index=80, number=136,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='KZT', index=81, number=398,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LAK', index=82, number=418,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LBP', index=83, number=422,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LKR', index=84, number=144,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LRD', index=85, number=430,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LSL', index=86, number=426,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LTL', index=87, number=440,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LVL', index=88, number=428,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='LYD', index=89, number=434,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MAD', index=90, number=504,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MDL', index=91, number=498,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MGA', index=92, number=969,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MKD', index=93, number=807,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MMK', index=94, number=104,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MNT', index=95, number=496,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MOP', index=96, number=446,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MRO', index=97, number=478,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MUR', index=98, number=480,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MVR', index=99, number=462,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MWK', index=100, number=454,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MXN', index=101, number=484,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MXV', index=102, number=979,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MYR', index=103, number=458,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='MZN', index=104, number=943,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NAD', index=105, number=516,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NGN', index=106, number=566,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NIO', index=107, number=558,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NOK', index=108, number=578,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NPR', index=109, number=524,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='NZD', index=110, number=554,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='OMR', index=111, number=512,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PAB', index=112, number=590,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PEN', index=113, number=604,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PGK', index=114, number=598,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PHP', index=115, number=608,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PKR', index=116, number=586,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PLN', index=117, number=985,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='PYG', index=118, number=600,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='QAR', index=119, number=634,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RON', index=120, number=946,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RSD', index=121, number=941,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RUB', index=122, number=643,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='RWF', index=123, number=646,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SAR', index=124, number=682,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SBD', index=125, number=90,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SCR', index=126, number=690,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SDG', index=127, number=938,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SEK', index=128, number=752,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SGD', index=129, number=702,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SHP', index=130, number=654,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SLL', index=131, number=694,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SOS', index=132, number=706,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SRD', index=133, number=968,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SSP', index=134, number=728,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='STD', index=135, number=678,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SVC', index=136, number=222,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SYP', index=137, number=760,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='SZL', index=138, number=748,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='THB', index=139, number=764,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TJS', index=140, number=972,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TMT', index=141, number=934,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TND', index=142, number=788,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TOP', index=143, number=776,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TRY', index=144, number=949,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TTD', index=145, number=780,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TWD', index=146, number=901,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='TZS', index=147, number=834,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UAH', index=148, number=980,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UGX', index=149, number=800,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='USD', index=150, number=840,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='USN', index=151, number=997,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='USS', index=152, number=998,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UYI', index=153, number=940,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UYU', index=154, number=858,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='UZS', index=155, number=860,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VEF', index=156, number=937,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VND', index=157, number=704,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='VUV', index=158, number=548,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='WST', index=159, number=882,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XAF', index=160, number=950,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XAG', index=161, number=961,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XAU', index=162, number=959,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XBA', index=163, number=955,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XBB', index=164, number=956,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XBC', index=165, number=957,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XBD', index=166, number=958,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XCD', index=167, number=951,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XDR', index=168, number=960,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XOF', index=169, number=952,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XPD', index=170, number=964,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XPF', index=171, number=953,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XPT', index=172, number=962,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XTS', index=173, number=963,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='XXX', index=174, number=999,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='YER', index=175, number=886,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ZAR', index=176, number=710,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ZMK', index=177, number=894,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='ZMW', index=178, number=967,
      options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='BTC', index=179, number=1001,
      options=None,
      type=None),
  ],
  containing_type=None,
  options=None,
  serialized_start=161,
  serialized_end=1951,
)
_sym_db.RegisterEnumDescriptor(_CURRENCY)

Currency = enum_type_wrapper.EnumTypeWrapper(_CURRENCY)
AED = 784
AFN = 971
ALL = 8
AMD = 51
ANG = 532
AOA = 973
ARS = 32
AUD = 36
AWG = 533
AZN = 944
BAM = 977
BBD = 52
BDT = 50
BGN = 975
BHD = 48
BIF = 108
BMD = 60
BND = 96
BOB = 68
BOV = 984
BRL = 986
BSD = 44
BTN = 64
BWP = 72
BYR = 974
BZD = 84
CAD = 124
CDF = 976
CHE = 947
CHF = 756
CHW = 948
CLF = 990
CLP = 152
CNY = 156
COP = 170
COU = 970
CRC = 188
CUC = 931
CUP = 192
CVE = 132
CZK = 203
DJF = 262
DKK = 208
DOP = 214
DZD = 12
EGP = 818
ERN = 232
ETB = 230
EUR = 978
FJD = 242
FKP = 238
GBP = 826
GEL = 981
GHS = 936
GIP = 292
GMD = 270
GNF = 324
GTQ = 320
GYD = 328
HKD = 344
HNL = 340
HRK = 191
HTG = 332
HUF = 348
IDR = 360
ILS = 376
INR = 356
IQD = 368
IRR = 364
ISK = 352
JMD = 388
JOD = 400
JPY = 392
KES = 404
KGS = 417
KHR = 116
KMF = 174
KPW = 408
KRW = 410
KWD = 414
KYD = 136
KZT = 398
LAK = 418
LBP = 422
LKR = 144
LRD = 430
LSL = 426
LTL = 440
LVL = 428
LYD = 434
MAD = 504
MDL = 498
MGA = 969
MKD = 807
MMK = 104
MNT = 496
MOP = 446
MRO = 478
MUR = 480
MVR = 462
MWK = 454
MXN = 484
MXV = 979
MYR = 458
MZN = 943
NAD = 516
NGN = 566
NIO = 558
NOK = 578
NPR = 524
NZD = 554
OMR = 512
PAB = 590
PEN = 604
PGK = 598
PHP = 608
PKR = 586
PLN = 985
PYG = 600
QAR = 634
RON = 946
RSD = 941
RUB = 643
RWF = 646
SAR = 682
SBD = 90
SCR = 690
SDG = 938
SEK = 752
SGD = 702
SHP = 654
SLL = 694
SOS = 706
SRD = 968
SSP = 728
STD = 678
SVC = 222
SYP = 760
SZL = 748
THB = 764
TJS = 972
TMT = 934
TND = 788
TOP = 776
TRY = 949
TTD = 780
TWD = 901
TZS = 834
UAH = 980
UGX = 800
USD = 840
USN = 997
USS = 998
UYI = 940
UYU = 858
UZS = 860
VEF = 937
VND = 704
VUV = 548
WST = 882
XAF = 950
XAG = 961
XAU = 959
XBA = 955
XBB = 956
XBC = 957
XBD = 958
XCD = 951
XDR = 960
XOF = 952
XPD = 964
XPF = 953
XPT = 962
XTS = 963
XXX = 999
YER = 886
ZAR = 710
ZMK = 894
ZMW = 967
BTC = 1001



_MONEY = _descriptor.Descriptor(
  name='Money',
  full_name='squareup.connect.v3.resources.Money',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='amount', full_name='squareup.connect.v3.resources.Money.amount', index=0,
      number=1, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='currency', full_name='squareup.connect.v3.resources.Money.currency', index=1,
      number=2, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=784,
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
  serialized_start=76,
  serialized_end=158,
)

_MONEY.fields_by_name['currency'].enum_type = _CURRENCY
DESCRIPTOR.message_types_by_name['Money'] = _MONEY
DESCRIPTOR.enum_types_by_name['Currency'] = _CURRENCY

Money = _reflection.GeneratedProtocolMessageType('Money', (_message.Message,), dict(
  DESCRIPTOR = _MONEY,
  __module__ = 'squareup.connect.v3.resources.money_pb2'
  # @@protoc_insertion_point(class_scope:squareup.connect.v3.resources.Money)
  ))
_sym_db.RegisterMessage(Money)


DESCRIPTOR.has_options = True
DESCRIPTOR._options = _descriptor._ParseOptions(descriptor_pb2.FileOptions(), _b('\n(com.squareup.protos.connect.v3.resourcesP\001'))
# @@protoc_insertion_point(module_scope)
