#!/usr/bin/env ruby
# Generated by the protocol buffer compiler. DO NOT EDIT!

require 'protocol_buffers'

begin; require 'squareup/connect/v3/resources/refund.pb'; rescue LoadError; end
begin; require 'squareup/connect/v3/resources/error.pb'; rescue LoadError; end
begin; require 'squareup/connect/v3/resources/money.pb'; rescue LoadError; end

module Squareup
  module Connect
    module V3
      module Actions
        # forward declarations
        class RefundRequest < ::ProtocolBuffers::Message; end
        class RefundResponse < ::ProtocolBuffers::Message; end

        class RefundRequest < ::ProtocolBuffers::Message
          set_fully_qualified_name "squareup.connect.v3.actions.RefundRequest"

          required :string, :location_id, 1
          optional :string, :idempotency_key, 2
          required :string, :source_transaction_id, 3
          required ::Squareup::Connect::V3::Resources::Refund::Type, :type, 4
          required :string, :reason, 5
          optional ::Squareup::Connect::V3::Resources::Money, :amount_money, 6
        end

        class RefundResponse < ::ProtocolBuffers::Message
          set_fully_qualified_name "squareup.connect.v3.actions.RefundResponse"

          repeated ::Squareup::Connect::V3::Resources::Error, :errors, 1
          optional ::Squareup::Connect::V3::Resources::Refund, :refund, 2
        end

      end
    end
  end
end
