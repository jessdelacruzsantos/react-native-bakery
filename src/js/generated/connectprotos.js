var ProtoBuf = require('protobufjs');

module.exports.squareup = ProtoBuf.newBuilder({})['import']({
    "package": "squareup.connect.v3",
    "messages": [
        {
            "name": "resources",
            "fields": [],
            "messages": [
                {
                    "name": "Address",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "line_1",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "line_2",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "line_3",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "locality",
                            "id": 6
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "sublocality",
                            "id": 7
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "sublocality_2",
                            "id": 8
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "sublocality_3",
                            "id": 9
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "region",
                            "id": 13
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "region_2",
                            "id": 14
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "region_3",
                            "id": 15
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "postal_code",
                            "id": 16
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "country_code",
                            "id": 17
                        }
                    ]
                },
                {
                    "name": "Card",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "Brand",
                            "name": "card_brand",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "last_4",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "int64",
                            "name": "exp_month",
                            "id": 4
                        },
                        {
                            "rule": "optional",
                            "type": "int64",
                            "name": "exp_year",
                            "id": 5
                        }
                    ],
                    "enums": [
                        {
                            "name": "Brand",
                            "values": [
                                {
                                    "name": "OTHER_BRAND",
                                    "id": 0
                                },
                                {
                                    "name": "VISA",
                                    "id": 1
                                },
                                {
                                    "name": "MASTERCARD",
                                    "id": 2
                                },
                                {
                                    "name": "AMERICAN_EXPRESS",
                                    "id": 3
                                },
                                {
                                    "name": "DISCOVER",
                                    "id": 4
                                },
                                {
                                    "name": "DISCOVER_DINERS",
                                    "id": 5
                                },
                                {
                                    "name": "JCB",
                                    "id": 6
                                },
                                {
                                    "name": "CHINA_UNIONPAY",
                                    "id": 7
                                },
                                {
                                    "name": "SQUARE_GIFT_CARD",
                                    "id": 8
                                }
                            ]
                        }
                    ]
                },
                {
                    "name": "CardData",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "number",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "int64",
                            "name": "exp_month",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "int64",
                            "name": "exp_year",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "cvv",
                            "id": 4
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "name",
                            "id": 5
                        },
                        {
                            "rule": "optional",
                            "type": "Address",
                            "name": "billing_address",
                            "id": 6
                        }
                    ]
                },
                {
                    "name": "Error",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "Type",
                            "name": "type",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "detail",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "field",
                            "id": 3
                        }
                    ],
                    "enums": [
                        {
                            "name": "Type",
                            "values": [
                                {
                                    "name": "INTERNAL_SERVER_ERROR",
                                    "id": 0
                                },
                                {
                                    "name": "UNAUTHORIZED",
                                    "id": 40100
                                },
                                {
                                    "name": "ACCESS_TOKEN_EXPIRED",
                                    "id": 40101
                                },
                                {
                                    "name": "ACCESS_TOKEN_REVOKED",
                                    "id": 40102
                                },
                                {
                                    "name": "FORBIDDEN",
                                    "id": 40300
                                },
                                {
                                    "name": "INSUFFICIENT_SCOPES",
                                    "id": 40301
                                },
                                {
                                    "name": "APPLICATION_DISABLED",
                                    "id": 40302
                                },
                                {
                                    "name": "V1_APPLICATION",
                                    "id": 40303
                                },
                                {
                                    "name": "V1_ACCESS_TOKEN",
                                    "id": 40304
                                },
                                {
                                    "name": "CARD_PROCESSING_NOT_ENABLED",
                                    "id": 40305
                                },
                                {
                                    "name": "BAD_REQUEST",
                                    "id": 40000
                                },
                                {
                                    "name": "MISSING_REQUIRED_PARAMETER",
                                    "id": 40001
                                },
                                {
                                    "name": "INCORRECT_TYPE",
                                    "id": 40002
                                },
                                {
                                    "name": "INVALID_TIME",
                                    "id": 40003
                                },
                                {
                                    "name": "INVALID_TIME_RANGE",
                                    "id": 40004
                                },
                                {
                                    "name": "INVALID_VALUE",
                                    "id": 40005
                                },
                                {
                                    "name": "INVALID_CURSOR",
                                    "id": 40006
                                },
                                {
                                    "name": "UNKNOWN_QUERY_PARAMETER",
                                    "id": 40007
                                },
                                {
                                    "name": "CONFLICTING_PARAMETERS",
                                    "id": 40008
                                },
                                {
                                    "name": "EXPECTED_JSON_BODY",
                                    "id": 40009
                                },
                                {
                                    "name": "INVALID_SORT_ORDER",
                                    "id": 40010
                                },
                                {
                                    "name": "VALUE_TOO_LONG",
                                    "id": 40011
                                },
                                {
                                    "name": "VALUE_TOO_LOW",
                                    "id": 40012
                                },
                                {
                                    "name": "EXPECTED_BOOLEAN",
                                    "id": 40013
                                },
                                {
                                    "name": "EXPECTED_INTEGER",
                                    "id": 40014
                                },
                                {
                                    "name": "EXPECTED_FLOAT",
                                    "id": 40015
                                },
                                {
                                    "name": "EXPECTED_STRING",
                                    "id": 40016
                                },
                                {
                                    "name": "EXPECTED_OBJECT",
                                    "id": 40017
                                },
                                {
                                    "name": "EXPECTED_ARRAY",
                                    "id": 40018
                                },
                                {
                                    "name": "INVALID_ARRAY_VALUE",
                                    "id": 40019
                                },
                                {
                                    "name": "INVALID_ENUM_VALUE",
                                    "id": 40020
                                },
                                {
                                    "name": "INVALID_CONTENT_TYPE",
                                    "id": 40021
                                },
                                {
                                    "name": "INVALID_FORM_VALUE",
                                    "id": 40022
                                },
                                {
                                    "name": "CARD_DECLINED",
                                    "id": 40023
                                },
                                {
                                    "name": "CARD_EXPIRED",
                                    "id": 40024
                                },
                                {
                                    "name": "VERIFY_CVV_FAILURE",
                                    "id": 40025
                                },
                                {
                                    "name": "VERIFY_AVS_FAILURE",
                                    "id": 40026
                                },
                                {
                                    "name": "INVALID_EXPIRATION",
                                    "id": 40027
                                },
                                {
                                    "name": "INVALID_EXPIRATION_YEAR",
                                    "id": 40028
                                },
                                {
                                    "name": "INVALID_EXPIRATION_DATE",
                                    "id": 40039
                                },
                                {
                                    "name": "INVALID_CARD",
                                    "id": 40040
                                },
                                {
                                    "name": "DELAYED_PAYMENT_EXPIRED",
                                    "id": 40041
                                },
                                {
                                    "name": "DELAYED_PAYMENT_CANCELED",
                                    "id": 40042
                                },
                                {
                                    "name": "CARD_TOKEN_EXPIRED",
                                    "id": 40043
                                },
                                {
                                    "name": "CARD_TOKEN_USED",
                                    "id": 40044
                                },
                                {
                                    "name": "AMOUNT_TOO_HIGH",
                                    "id": 40045
                                },
                                {
                                    "name": "NOT_FOUND",
                                    "id": 40400
                                },
                                {
                                    "name": "REQUEST_TIMEOUT",
                                    "id": 40800
                                },
                                {
                                    "name": "REQUEST_ENTITY_TOO_LARGE",
                                    "id": 41300
                                },
                                {
                                    "name": "UNSUPPORTED_MEDIA_TYPE",
                                    "id": 41500
                                },
                                {
                                    "name": "RATE_LIMITED",
                                    "id": 42900
                                },
                                {
                                    "name": "NOT_IMPLEMENTED",
                                    "id": 50100
                                },
                                {
                                    "name": "SERVICE_UNAVAILABLE",
                                    "id": 50300
                                }
                            ]
                        }
                    ]
                },
                {
                    "name": "Money",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "int64",
                            "name": "amount",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "Currency",
                            "name": "currency",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "Refund",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "business_id",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "location_id",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "created_at",
                            "id": 4
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "completed_at",
                            "id": 5
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "source_transaction_id",
                            "id": 6
                        },
                        {
                            "rule": "optional",
                            "type": "Type",
                            "name": "type",
                            "id": 7
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "reason",
                            "id": 8
                        },
                        {
                            "rule": "optional",
                            "type": "Money",
                            "name": "amount_money",
                            "id": 9
                        }
                    ],
                    "enums": [
                        {
                            "name": "Type",
                            "values": [
                                {
                                    "name": "FULL",
                                    "id": 1
                                },
                                {
                                    "name": "PARTIAL",
                                    "id": 2
                                }
                            ]
                        }
                    ]
                },
                {
                    "name": "Tender",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "business_id",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "location_id",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "transaction_id",
                            "id": 4
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "created_at",
                            "id": 5
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "note",
                            "id": 6
                        },
                        {
                            "rule": "optional",
                            "type": "Money",
                            "name": "amount_money",
                            "id": 7
                        },
                        {
                            "rule": "repeated",
                            "type": "RefundTransaction",
                            "name": "refund_transactions",
                            "id": 8
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "customer_id",
                            "id": 9
                        },
                        {
                            "rule": "optional",
                            "type": "Charge",
                            "name": "charge",
                            "id": 10,
                            "oneof": "instrument"
                        }
                    ],
                    "oneofs": {
                        "instrument": [
                            10
                        ]
                    },
                    "messages": [
                        {
                            "name": "RefundTransaction",
                            "fields": [
                                {
                                    "rule": "optional",
                                    "type": "string",
                                    "name": "transaction_id",
                                    "id": 1
                                },
                                {
                                    "rule": "optional",
                                    "type": "string",
                                    "name": "refund_id",
                                    "id": 2
                                }
                            ]
                        },
                        {
                            "name": "Charge",
                            "fields": [
                                {
                                    "rule": "optional",
                                    "type": "Status",
                                    "name": "status",
                                    "id": 1
                                },
                                {
                                    "rule": "optional",
                                    "type": "Card",
                                    "name": "card",
                                    "id": 2
                                }
                            ],
                            "enums": [
                                {
                                    "name": "Status",
                                    "values": [
                                        {
                                            "name": "UNKNOWN",
                                            "id": 0
                                        },
                                        {
                                            "name": "AUTHORIZED",
                                            "id": 1
                                        },
                                        {
                                            "name": "CAPTURED",
                                            "id": 2
                                        },
                                        {
                                            "name": "VOIDED",
                                            "id": 3
                                        },
                                        {
                                            "name": "FAILED",
                                            "id": 4
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    "name": "Transaction",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "business_id",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "location_id",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "created_at",
                            "id": 6
                        },
                        {
                            "rule": "repeated",
                            "type": "Tender",
                            "name": "tenders",
                            "id": 7
                        },
                        {
                            "rule": "repeated",
                            "type": "Refund",
                            "name": "refunds",
                            "id": 8
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "reference_id",
                            "id": 9
                        }
                    ]
                },
                {
                    "name": "Customer",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "business_id",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "location_id",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "created_at",
                            "id": 4
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "updated_at",
                            "id": 5
                        },
                        {
                            "rule": "repeated",
                            "type": "Card",
                            "name": "cards",
                            "id": 6
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "full_name",
                            "id": 7
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "email_address",
                            "id": 8
                        },
                        {
                            "rule": "optional",
                            "type": "Address",
                            "name": "address",
                            "id": 9
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "phone_number",
                            "id": 10
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "memo",
                            "id": 11
                        }
                    ]
                },
                {
                    "name": "Location",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "business_id",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "name",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "Address",
                            "name": "address",
                            "id": 4
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "timezone",
                            "id": 5
                        },
                        {
                            "rule": "repeated",
                            "type": "Capability",
                            "name": "capabilities",
                            "id": 10
                        }
                    ],
                    "enums": [
                        {
                            "name": "Capability",
                            "values": [
                                {
                                    "name": "CREDIT_CARD_PROCESSING",
                                    "id": 1
                                }
                            ]
                        }
                    ]
                }
            ],
            "enums": [
                {
                    "name": "Currency",
                    "values": [
                        {
                            "name": "AED",
                            "id": 784
                        },
                        {
                            "name": "AFN",
                            "id": 971
                        },
                        {
                            "name": "ALL",
                            "id": 8
                        },
                        {
                            "name": "AMD",
                            "id": 51
                        },
                        {
                            "name": "ANG",
                            "id": 532
                        },
                        {
                            "name": "AOA",
                            "id": 973
                        },
                        {
                            "name": "ARS",
                            "id": 32
                        },
                        {
                            "name": "AUD",
                            "id": 36
                        },
                        {
                            "name": "AWG",
                            "id": 533
                        },
                        {
                            "name": "AZN",
                            "id": 944
                        },
                        {
                            "name": "BAM",
                            "id": 977
                        },
                        {
                            "name": "BBD",
                            "id": 52
                        },
                        {
                            "name": "BDT",
                            "id": 50
                        },
                        {
                            "name": "BGN",
                            "id": 975
                        },
                        {
                            "name": "BHD",
                            "id": 48
                        },
                        {
                            "name": "BIF",
                            "id": 108
                        },
                        {
                            "name": "BMD",
                            "id": 60
                        },
                        {
                            "name": "BND",
                            "id": 96
                        },
                        {
                            "name": "BOB",
                            "id": 68
                        },
                        {
                            "name": "BOV",
                            "id": 984
                        },
                        {
                            "name": "BRL",
                            "id": 986
                        },
                        {
                            "name": "BSD",
                            "id": 44
                        },
                        {
                            "name": "BTN",
                            "id": 64
                        },
                        {
                            "name": "BWP",
                            "id": 72
                        },
                        {
                            "name": "BYR",
                            "id": 974
                        },
                        {
                            "name": "BZD",
                            "id": 84
                        },
                        {
                            "name": "CAD",
                            "id": 124
                        },
                        {
                            "name": "CDF",
                            "id": 976
                        },
                        {
                            "name": "CHE",
                            "id": 947
                        },
                        {
                            "name": "CHF",
                            "id": 756
                        },
                        {
                            "name": "CHW",
                            "id": 948
                        },
                        {
                            "name": "CLF",
                            "id": 990
                        },
                        {
                            "name": "CLP",
                            "id": 152
                        },
                        {
                            "name": "CNY",
                            "id": 156
                        },
                        {
                            "name": "COP",
                            "id": 170
                        },
                        {
                            "name": "COU",
                            "id": 970
                        },
                        {
                            "name": "CRC",
                            "id": 188
                        },
                        {
                            "name": "CUC",
                            "id": 931
                        },
                        {
                            "name": "CUP",
                            "id": 192
                        },
                        {
                            "name": "CVE",
                            "id": 132
                        },
                        {
                            "name": "CZK",
                            "id": 203
                        },
                        {
                            "name": "DJF",
                            "id": 262
                        },
                        {
                            "name": "DKK",
                            "id": 208
                        },
                        {
                            "name": "DOP",
                            "id": 214
                        },
                        {
                            "name": "DZD",
                            "id": 12
                        },
                        {
                            "name": "EGP",
                            "id": 818
                        },
                        {
                            "name": "ERN",
                            "id": 232
                        },
                        {
                            "name": "ETB",
                            "id": 230
                        },
                        {
                            "name": "EUR",
                            "id": 978
                        },
                        {
                            "name": "FJD",
                            "id": 242
                        },
                        {
                            "name": "FKP",
                            "id": 238
                        },
                        {
                            "name": "GBP",
                            "id": 826
                        },
                        {
                            "name": "GEL",
                            "id": 981
                        },
                        {
                            "name": "GHS",
                            "id": 936
                        },
                        {
                            "name": "GIP",
                            "id": 292
                        },
                        {
                            "name": "GMD",
                            "id": 270
                        },
                        {
                            "name": "GNF",
                            "id": 324
                        },
                        {
                            "name": "GTQ",
                            "id": 320
                        },
                        {
                            "name": "GYD",
                            "id": 328
                        },
                        {
                            "name": "HKD",
                            "id": 344
                        },
                        {
                            "name": "HNL",
                            "id": 340
                        },
                        {
                            "name": "HRK",
                            "id": 191
                        },
                        {
                            "name": "HTG",
                            "id": 332
                        },
                        {
                            "name": "HUF",
                            "id": 348
                        },
                        {
                            "name": "IDR",
                            "id": 360
                        },
                        {
                            "name": "ILS",
                            "id": 376
                        },
                        {
                            "name": "INR",
                            "id": 356
                        },
                        {
                            "name": "IQD",
                            "id": 368
                        },
                        {
                            "name": "IRR",
                            "id": 364
                        },
                        {
                            "name": "ISK",
                            "id": 352
                        },
                        {
                            "name": "JMD",
                            "id": 388
                        },
                        {
                            "name": "JOD",
                            "id": 400
                        },
                        {
                            "name": "JPY",
                            "id": 392
                        },
                        {
                            "name": "KES",
                            "id": 404
                        },
                        {
                            "name": "KGS",
                            "id": 417
                        },
                        {
                            "name": "KHR",
                            "id": 116
                        },
                        {
                            "name": "KMF",
                            "id": 174
                        },
                        {
                            "name": "KPW",
                            "id": 408
                        },
                        {
                            "name": "KRW",
                            "id": 410
                        },
                        {
                            "name": "KWD",
                            "id": 414
                        },
                        {
                            "name": "KYD",
                            "id": 136
                        },
                        {
                            "name": "KZT",
                            "id": 398
                        },
                        {
                            "name": "LAK",
                            "id": 418
                        },
                        {
                            "name": "LBP",
                            "id": 422
                        },
                        {
                            "name": "LKR",
                            "id": 144
                        },
                        {
                            "name": "LRD",
                            "id": 430
                        },
                        {
                            "name": "LSL",
                            "id": 426
                        },
                        {
                            "name": "LTL",
                            "id": 440
                        },
                        {
                            "name": "LVL",
                            "id": 428
                        },
                        {
                            "name": "LYD",
                            "id": 434
                        },
                        {
                            "name": "MAD",
                            "id": 504
                        },
                        {
                            "name": "MDL",
                            "id": 498
                        },
                        {
                            "name": "MGA",
                            "id": 969
                        },
                        {
                            "name": "MKD",
                            "id": 807
                        },
                        {
                            "name": "MMK",
                            "id": 104
                        },
                        {
                            "name": "MNT",
                            "id": 496
                        },
                        {
                            "name": "MOP",
                            "id": 446
                        },
                        {
                            "name": "MRO",
                            "id": 478
                        },
                        {
                            "name": "MUR",
                            "id": 480
                        },
                        {
                            "name": "MVR",
                            "id": 462
                        },
                        {
                            "name": "MWK",
                            "id": 454
                        },
                        {
                            "name": "MXN",
                            "id": 484
                        },
                        {
                            "name": "MXV",
                            "id": 979
                        },
                        {
                            "name": "MYR",
                            "id": 458
                        },
                        {
                            "name": "MZN",
                            "id": 943
                        },
                        {
                            "name": "NAD",
                            "id": 516
                        },
                        {
                            "name": "NGN",
                            "id": 566
                        },
                        {
                            "name": "NIO",
                            "id": 558
                        },
                        {
                            "name": "NOK",
                            "id": 578
                        },
                        {
                            "name": "NPR",
                            "id": 524
                        },
                        {
                            "name": "NZD",
                            "id": 554
                        },
                        {
                            "name": "OMR",
                            "id": 512
                        },
                        {
                            "name": "PAB",
                            "id": 590
                        },
                        {
                            "name": "PEN",
                            "id": 604
                        },
                        {
                            "name": "PGK",
                            "id": 598
                        },
                        {
                            "name": "PHP",
                            "id": 608
                        },
                        {
                            "name": "PKR",
                            "id": 586
                        },
                        {
                            "name": "PLN",
                            "id": 985
                        },
                        {
                            "name": "PYG",
                            "id": 600
                        },
                        {
                            "name": "QAR",
                            "id": 634
                        },
                        {
                            "name": "RON",
                            "id": 946
                        },
                        {
                            "name": "RSD",
                            "id": 941
                        },
                        {
                            "name": "RUB",
                            "id": 643
                        },
                        {
                            "name": "RWF",
                            "id": 646
                        },
                        {
                            "name": "SAR",
                            "id": 682
                        },
                        {
                            "name": "SBD",
                            "id": 90
                        },
                        {
                            "name": "SCR",
                            "id": 690
                        },
                        {
                            "name": "SDG",
                            "id": 938
                        },
                        {
                            "name": "SEK",
                            "id": 752
                        },
                        {
                            "name": "SGD",
                            "id": 702
                        },
                        {
                            "name": "SHP",
                            "id": 654
                        },
                        {
                            "name": "SLL",
                            "id": 694
                        },
                        {
                            "name": "SOS",
                            "id": 706
                        },
                        {
                            "name": "SRD",
                            "id": 968
                        },
                        {
                            "name": "SSP",
                            "id": 728
                        },
                        {
                            "name": "STD",
                            "id": 678
                        },
                        {
                            "name": "SVC",
                            "id": 222
                        },
                        {
                            "name": "SYP",
                            "id": 760
                        },
                        {
                            "name": "SZL",
                            "id": 748
                        },
                        {
                            "name": "THB",
                            "id": 764
                        },
                        {
                            "name": "TJS",
                            "id": 972
                        },
                        {
                            "name": "TMT",
                            "id": 934
                        },
                        {
                            "name": "TND",
                            "id": 788
                        },
                        {
                            "name": "TOP",
                            "id": 776
                        },
                        {
                            "name": "TRY",
                            "id": 949
                        },
                        {
                            "name": "TTD",
                            "id": 780
                        },
                        {
                            "name": "TWD",
                            "id": 901
                        },
                        {
                            "name": "TZS",
                            "id": 834
                        },
                        {
                            "name": "UAH",
                            "id": 980
                        },
                        {
                            "name": "UGX",
                            "id": 800
                        },
                        {
                            "name": "USD",
                            "id": 840
                        },
                        {
                            "name": "USN",
                            "id": 997
                        },
                        {
                            "name": "USS",
                            "id": 998
                        },
                        {
                            "name": "UYI",
                            "id": 940
                        },
                        {
                            "name": "UYU",
                            "id": 858
                        },
                        {
                            "name": "UZS",
                            "id": 860
                        },
                        {
                            "name": "VEF",
                            "id": 937
                        },
                        {
                            "name": "VND",
                            "id": 704
                        },
                        {
                            "name": "VUV",
                            "id": 548
                        },
                        {
                            "name": "WST",
                            "id": 882
                        },
                        {
                            "name": "XAF",
                            "id": 950
                        },
                        {
                            "name": "XAG",
                            "id": 961
                        },
                        {
                            "name": "XAU",
                            "id": 959
                        },
                        {
                            "name": "XBA",
                            "id": 955
                        },
                        {
                            "name": "XBB",
                            "id": 956
                        },
                        {
                            "name": "XBC",
                            "id": 957
                        },
                        {
                            "name": "XBD",
                            "id": 958
                        },
                        {
                            "name": "XCD",
                            "id": 951
                        },
                        {
                            "name": "XDR",
                            "id": 960
                        },
                        {
                            "name": "XOF",
                            "id": 952
                        },
                        {
                            "name": "XPD",
                            "id": 964
                        },
                        {
                            "name": "XPF",
                            "id": 953
                        },
                        {
                            "name": "XPT",
                            "id": 962
                        },
                        {
                            "name": "XTS",
                            "id": 963
                        },
                        {
                            "name": "XXX",
                            "id": 999
                        },
                        {
                            "name": "YER",
                            "id": 886
                        },
                        {
                            "name": "ZAR",
                            "id": 710
                        },
                        {
                            "name": "ZMK",
                            "id": 894
                        },
                        {
                            "name": "ZMW",
                            "id": 967
                        },
                        {
                            "name": "BTC",
                            "id": 1001
                        }
                    ]
                }
            ]
        },
        {
            "name": "actions",
            "fields": [],
            "messages": [
                {
                    "name": "CreateCardRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "client_id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "business_id",
                            "id": 2
                        },
                        {
                            "rule": "required",
                            "type": "resources.CardData",
                            "name": "card_data",
                            "id": 3
                        }
                    ]
                },
                {
                    "name": "CreateCardResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "card_nonce",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Card",
                            "name": "card",
                            "id": 3
                        }
                    ]
                },
                {
                    "name": "ChargeRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "idempotency_key",
                            "id": 2
                        },
                        {
                            "rule": "required",
                            "type": "resources.Money",
                            "name": "amount_money",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "customer_id",
                            "id": 4
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "card_nonce",
                            "id": 5,
                            "oneof": "instrument"
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "customer_card_id",
                            "id": 6,
                            "oneof": "instrument"
                        },
                        {
                            "rule": "optional",
                            "type": "bool",
                            "name": "delay_capture",
                            "id": 7
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "reference_id",
                            "id": 8
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "note",
                            "id": 9
                        }
                    ],
                    "oneofs": {
                        "instrument": [
                            5,
                            6
                        ]
                    }
                },
                {
                    "name": "ChargeResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Transaction",
                            "name": "transaction",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "ListCustomersRequest",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "Params",
                            "name": "params",
                            "id": 1,
                            "oneof": "query"
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "cursor",
                            "id": 2,
                            "oneof": "query"
                        }
                    ],
                    "oneofs": {
                        "query": [
                            1,
                            2
                        ]
                    },
                    "messages": [
                        {
                            "name": "Params",
                            "fields": [
                                {
                                    "rule": "optional",
                                    "type": "string",
                                    "name": "location_id",
                                    "id": 1
                                }
                            ]
                        }
                    ]
                },
                {
                    "name": "ListCustomersResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "repeated",
                            "type": "resources.Customer",
                            "name": "customers",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "cursor",
                            "id": 3
                        }
                    ]
                },
                {
                    "name": "UpsertCustomerRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "resources.Customer",
                            "name": "customer",
                            "id": 1
                        }
                    ]
                },
                {
                    "name": "UpsertCustomerResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Customer",
                            "name": "customer",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "RetrieveCustomerRequest",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "customer_id",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "RetrieveCustomerResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Customer",
                            "name": "customer",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "CreateCustomerCardRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "customer_id",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "card_nonce",
                            "id": 3
                        },
                        {
                            "rule": "optional",
                            "type": "resources.CardData",
                            "name": "card_data",
                            "id": 4
                        }
                    ]
                },
                {
                    "name": "CreateCustomerCardResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Card",
                            "name": "card",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "UpdateCustomerCardRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "customer_id",
                            "id": 2
                        },
                        {
                            "rule": "required",
                            "type": "resources.Card",
                            "name": "card",
                            "id": 3
                        }
                    ]
                },
                {
                    "name": "UpdateCustomerCardResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Card",
                            "name": "card",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "DeleteCustomerCardRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "customer_id",
                            "id": 2
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "card_id",
                            "id": 3
                        }
                    ]
                },
                {
                    "name": "DeleteCustomerCardResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        }
                    ]
                },
                {
                    "name": "ListLocationsRequest",
                    "fields": []
                },
                {
                    "name": "ListLocationsResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "repeated",
                            "type": "resources.Location",
                            "name": "locations",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "RefundRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "idempotency_key",
                            "id": 2
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "source_transaction_id",
                            "id": 3
                        },
                        {
                            "rule": "required",
                            "type": "resources.Refund.Type",
                            "name": "type",
                            "id": 4
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "reason",
                            "id": 5
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Money",
                            "name": "amount_money",
                            "id": 6
                        }
                    ]
                },
                {
                    "name": "RefundResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Refund",
                            "name": "refund",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "ListTransactionsRequest",
                    "fields": [
                        {
                            "rule": "optional",
                            "type": "Params",
                            "name": "params",
                            "id": 1,
                            "oneof": "query"
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "cursor",
                            "id": 2,
                            "oneof": "query"
                        }
                    ],
                    "oneofs": {
                        "query": [
                            1,
                            2
                        ]
                    },
                    "messages": [
                        {
                            "name": "Params",
                            "fields": [
                                {
                                    "rule": "required",
                                    "type": "string",
                                    "name": "location_id",
                                    "id": 1
                                },
                                {
                                    "rule": "required",
                                    "type": "string",
                                    "name": "begin_time",
                                    "id": 2
                                },
                                {
                                    "rule": "required",
                                    "type": "string",
                                    "name": "end_time",
                                    "id": 3
                                },
                                {
                                    "rule": "required",
                                    "type": "Sort",
                                    "name": "sort",
                                    "id": 4
                                }
                            ],
                            "enums": [
                                {
                                    "name": "Sort",
                                    "values": [
                                        {
                                            "name": "CREATED_AT_DESC",
                                            "id": 0
                                        },
                                        {
                                            "name": "CREATED_AT_ASC",
                                            "id": 1
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    "name": "ListTransactionsResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "repeated",
                            "type": "resources.Transaction",
                            "name": "transactions",
                            "id": 2
                        },
                        {
                            "rule": "optional",
                            "type": "string",
                            "name": "cursor",
                            "id": 3
                        }
                    ]
                },
                {
                    "name": "RetrieveTransactionRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "transaction_id",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "RetrieveTransactionResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        },
                        {
                            "rule": "optional",
                            "type": "resources.Transaction",
                            "name": "transaction",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "CaptureTransactionRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "transaction_id",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "CaptureTransactionResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        }
                    ]
                },
                {
                    "name": "VoidTransactionRequest",
                    "fields": [
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "location_id",
                            "id": 1
                        },
                        {
                            "rule": "required",
                            "type": "string",
                            "name": "transaction_id",
                            "id": 2
                        }
                    ]
                },
                {
                    "name": "VoidTransactionResponse",
                    "fields": [
                        {
                            "rule": "repeated",
                            "type": "resources.Error",
                            "name": "errors",
                            "id": 1
                        }
                    ]
                }
            ]
        }
    ],
    "services": [
        {
            "name": "SquareConnectV3",
            "options": {},
            "rpc": {
                "Charge": {
                    "request": "actions.ChargeRequest",
                    "response": "actions.ChargeResponse",
                    "options": {}
                },
                "Refund": {
                    "request": "actions.RefundRequest",
                    "response": "actions.RefundResponse",
                    "options": {}
                },
                "ListLocations": {
                    "request": "actions.ListLocationsRequest",
                    "response": "actions.ListLocationsResponse",
                    "options": {}
                },
                "CreateCard": {
                    "request": "actions.CreateCardRequest",
                    "response": "actions.CreateCardResponse",
                    "options": {}
                },
                "UpsertCustomer": {
                    "request": "actions.UpsertCustomerRequest",
                    "response": "actions.UpsertCustomerResponse",
                    "options": {}
                },
                "ListCustomers": {
                    "request": "actions.ListCustomersRequest",
                    "response": "actions.ListCustomersResponse",
                    "options": {}
                },
                "RetrieveCustomer": {
                    "request": "actions.RetrieveCustomerRequest",
                    "response": "actions.RetrieveCustomerResponse",
                    "options": {}
                },
                "CreateCustomerCard": {
                    "request": "actions.CreateCustomerCardRequest",
                    "response": "actions.CreateCustomerCardResponse",
                    "options": {}
                },
                "UpdateCustomerCard": {
                    "request": "actions.UpdateCustomerCardRequest",
                    "response": "actions.UpdateCustomerCardResponse",
                    "options": {}
                },
                "DeleteCustomerCard": {
                    "request": "actions.DeleteCustomerCardRequest",
                    "response": "actions.DeleteCustomerCardResponse",
                    "options": {}
                },
                "CaptureTransaction": {
                    "request": "actions.CaptureTransactionRequest",
                    "response": "actions.CaptureTransactionResponse",
                    "options": {}
                },
                "VoidTransaction": {
                    "request": "actions.VoidTransactionRequest",
                    "response": "actions.VoidTransactionResponse",
                    "options": {}
                },
                "ListTransactions": {
                    "request": "actions.ListTransactionsRequest",
                    "response": "actions.ListTransactionsResponse",
                    "options": {}
                },
                "RetrieveTransaction": {
                    "request": "actions.RetrieveTransactionRequest",
                    "response": "actions.RetrieveTransactionResponse",
                    "options": {}
                }
            }
        }
    ]
}).build(["squareup"]);
