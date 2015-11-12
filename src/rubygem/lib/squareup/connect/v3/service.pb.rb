#!/usr/bin/env ruby
# Generated by the protocol buffer compiler. DO NOT EDIT!

require 'protocol_buffers'

begin; require 'squareup/connect/v3/actions/card_nonce.pb'; rescue LoadError; end
begin; require 'squareup/connect/v3/actions/charge.pb'; rescue LoadError; end
begin; require 'squareup/connect/v3/actions/customer.pb'; rescue LoadError; end
begin; require 'squareup/connect/v3/actions/location.pb'; rescue LoadError; end
begin; require 'squareup/connect/v3/actions/refund.pb'; rescue LoadError; end
begin; require 'squareup/connect/v3/actions/transaction.pb'; rescue LoadError; end

module Squareup
  module Connect
    module V3
      class SquareConnectV3 < ::ProtocolBuffers::Service
        set_fully_qualified_name "squareup.connect.v3.SquareConnectV3"

        rpc :charge, "Charge", ::Squareup::Connect::V3::Actions::ChargeRequest, ::Squareup::Connect::V3::Actions::ChargeResponse
        rpc :refund, "Refund", ::Squareup::Connect::V3::Actions::RefundRequest, ::Squareup::Connect::V3::Actions::RefundResponse
        rpc :list_locations, "ListLocations", ::Squareup::Connect::V3::Actions::ListLocationsRequest, ::Squareup::Connect::V3::Actions::ListLocationsResponse
        rpc :create_card_nonce, "CreateCardNonce", ::Squareup::Connect::V3::Actions::CreateCardNonceRequest, ::Squareup::Connect::V3::Actions::CreateCardNonceResponse
        rpc :upsert_customer, "UpsertCustomer", ::Squareup::Connect::V3::Actions::UpsertCustomerRequest, ::Squareup::Connect::V3::Actions::UpsertCustomerResponse
        rpc :list_customers, "ListCustomers", ::Squareup::Connect::V3::Actions::ListCustomersRequest, ::Squareup::Connect::V3::Actions::ListCustomersResponse
        rpc :retrieve_customer, "RetrieveCustomer", ::Squareup::Connect::V3::Actions::RetrieveCustomerRequest, ::Squareup::Connect::V3::Actions::RetrieveCustomerResponse
        rpc :create_customer_card, "CreateCustomerCard", ::Squareup::Connect::V3::Actions::CreateCustomerCardRequest, ::Squareup::Connect::V3::Actions::CreateCustomerCardResponse
        rpc :delete_customer_card, "DeleteCustomerCard", ::Squareup::Connect::V3::Actions::DeleteCustomerCardRequest, ::Squareup::Connect::V3::Actions::DeleteCustomerCardResponse
        rpc :capture_transaction, "CaptureTransaction", ::Squareup::Connect::V3::Actions::CaptureTransactionRequest, ::Squareup::Connect::V3::Actions::CaptureTransactionResponse
        rpc :void_transaction, "VoidTransaction", ::Squareup::Connect::V3::Actions::VoidTransactionRequest, ::Squareup::Connect::V3::Actions::VoidTransactionResponse
        rpc :list_transactions, "ListTransactions", ::Squareup::Connect::V3::Actions::ListTransactionsRequest, ::Squareup::Connect::V3::Actions::ListTransactionsResponse
        rpc :retrieve_transaction, "RetrieveTransaction", ::Squareup::Connect::V3::Actions::RetrieveTransactionRequest, ::Squareup::Connect::V3::Actions::RetrieveTransactionResponse
      end
    end
  end
end
