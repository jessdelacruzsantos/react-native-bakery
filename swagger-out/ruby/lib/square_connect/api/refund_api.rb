=begin
Square Connect API

OpenAPI spec version: 2.0

Generated by: https://github.com/swagger-api/swagger-codegen.git


=end

require "uri"

module SquareConnect
  class RefundApi
    attr_accessor :api_client

    def initialize(api_client = ApiClient.default)
      @api_client = api_client
    end

    # ListRefunds
    # Lists refunds for one of a business&#39;s locations.
    # @param authorization The value to provide in the Authorization header of\nyour request. This value should follow the format `Bearer YOUR_ACCESS_TOKEN_HERE`.
    # @param location_id The ID of the location to list refunds for.
    # @param [Hash] opts the optional parameters
    # @option opts [String] :begin_time The beginning of the requested reporting period, in RFC 3339 format.\n\nDefault value: The current time minus one year.
    # @option opts [String] :end_time The end of the requested reporting period, in RFC 3339 format.\n\nDefault value: The current time.
    # @option opts [String] :sort_order The order in which results are listed in the response (`ASC` for\noldest first, `DESC` for newest first).\n\nDefault value: `DESC`
    # @option opts [String] :cursor A pagination cursor returned by a previous call to this endpoint.\nProvide this to retrieve the next set of results for your original query.\n\nSee [Paginating results](#paginatingresults) for more information.
    # @return [ListRefundsResponse]
    def list_refunds(authorization, location_id, opts = {})
      data, status_code, headers = list_refunds_with_http_info(authorization, location_id, opts)
      return data
    end

    # ListRefunds
    # Lists refunds for one of a business&#39;s locations.
    # @param authorization The value to provide in the Authorization header of\nyour request. This value should follow the format `Bearer YOUR_ACCESS_TOKEN_HERE`.
    # @param location_id The ID of the location to list refunds for.
    # @param [Hash] opts the optional parameters
    # @option opts [String] :begin_time The beginning of the requested reporting period, in RFC 3339 format.\n\nDefault value: The current time minus one year.
    # @option opts [String] :end_time The end of the requested reporting period, in RFC 3339 format.\n\nDefault value: The current time.
    # @option opts [String] :sort_order The order in which results are listed in the response (`ASC` for\noldest first, `DESC` for newest first).\n\nDefault value: `DESC`
    # @option opts [String] :cursor A pagination cursor returned by a previous call to this endpoint.\nProvide this to retrieve the next set of results for your original query.\n\nSee [Paginating results](#paginatingresults) for more information.
    # @return [Array<(ListRefundsResponse, Fixnum, Hash)>] ListRefundsResponse data, response status code and response headers
    def list_refunds_with_http_info(authorization, location_id, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: RefundApi#list_refunds ..."
      end
      
      # verify the required parameter 'authorization' is set
      fail "Missing the required parameter 'authorization' when calling list_refunds" if authorization.nil?
      
      # verify the required parameter 'location_id' is set
      fail "Missing the required parameter 'location_id' when calling list_refunds" if location_id.nil?
      
      if opts[:'sort_order'] && !['DESC', 'ASC'].include?(opts[:'sort_order'])
        fail 'invalid value for "sort_order", must be one of DESC, ASC'
      end
      
      # resource path
      path = "/v2/locations/{location_id}/refunds".sub('{format}','json').sub('{' + 'location_id' + '}', location_id.to_s)

      # query parameters
      query_params = {}
      query_params[:'begin_time'] = opts[:'begin_time'] if opts[:'begin_time']
      query_params[:'end_time'] = opts[:'end_time'] if opts[:'end_time']
      query_params[:'sort_order'] = opts[:'sort_order'] if opts[:'sort_order']
      query_params[:'cursor'] = opts[:'cursor'] if opts[:'cursor']

      # header parameters
      header_params = {}

      # HTTP header 'Accept' (if needed)
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = ['application/json']
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)
      header_params[:'Authorization'] = authorization

      # form parameters
      form_params = {}

      # http body (model)
      post_body = nil
      
      auth_names = []
      data, status_code, headers = @api_client.call_api(:GET, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'ListRefundsResponse')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: RefundApi#list_refunds\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end

    # CreateRefund
    # Initiates a refund for a previously charged tender.
    # @param authorization The value to provide in the Authorization header of\nyour request. This value should follow the format `Bearer YOUR_ACCESS_TOKEN_HERE`.
    # @param location_id The ID of the original transaction&#39;s associated location.
    # @param transaction_id The ID of the original transaction that includes the tender to refund.
    # @param body An object containing the fields to POST for the request.\n\nSee the corresponding object definition for field details.
    # @param [Hash] opts the optional parameters
    # @return [CreateRefundResponse]
    def create_refund(authorization, location_id, transaction_id, body, opts = {})
      data, status_code, headers = create_refund_with_http_info(authorization, location_id, transaction_id, body, opts)
      return data
    end

    # CreateRefund
    # Initiates a refund for a previously charged tender.
    # @param authorization The value to provide in the Authorization header of\nyour request. This value should follow the format `Bearer YOUR_ACCESS_TOKEN_HERE`.
    # @param location_id The ID of the original transaction&#39;s associated location.
    # @param transaction_id The ID of the original transaction that includes the tender to refund.
    # @param body An object containing the fields to POST for the request.\n\nSee the corresponding object definition for field details.
    # @param [Hash] opts the optional parameters
    # @return [Array<(CreateRefundResponse, Fixnum, Hash)>] CreateRefundResponse data, response status code and response headers
    def create_refund_with_http_info(authorization, location_id, transaction_id, body, opts = {})
      if @api_client.config.debugging
        @api_client.config.logger.debug "Calling API: RefundApi#create_refund ..."
      end
      
      # verify the required parameter 'authorization' is set
      fail "Missing the required parameter 'authorization' when calling create_refund" if authorization.nil?
      
      # verify the required parameter 'location_id' is set
      fail "Missing the required parameter 'location_id' when calling create_refund" if location_id.nil?
      
      # verify the required parameter 'transaction_id' is set
      fail "Missing the required parameter 'transaction_id' when calling create_refund" if transaction_id.nil?
      
      # verify the required parameter 'body' is set
      fail "Missing the required parameter 'body' when calling create_refund" if body.nil?
      
      # resource path
      path = "/v2/locations/{location_id}/transactions/{transaction_id}/refund".sub('{format}','json').sub('{' + 'location_id' + '}', location_id.to_s).sub('{' + 'transaction_id' + '}', transaction_id.to_s)

      # query parameters
      query_params = {}

      # header parameters
      header_params = {}

      # HTTP header 'Accept' (if needed)
      _header_accept = ['application/json']
      _header_accept_result = @api_client.select_header_accept(_header_accept) and header_params['Accept'] = _header_accept_result

      # HTTP header 'Content-Type'
      _header_content_type = ['application/json']
      header_params['Content-Type'] = @api_client.select_header_content_type(_header_content_type)
      header_params[:'Authorization'] = authorization

      # form parameters
      form_params = {}

      # http body (model)
      post_body = @api_client.object_to_http_body(body)
      
      auth_names = []
      data, status_code, headers = @api_client.call_api(:POST, path,
        :header_params => header_params,
        :query_params => query_params,
        :form_params => form_params,
        :body => post_body,
        :auth_names => auth_names,
        :return_type => 'CreateRefundResponse')
      if @api_client.config.debugging
        @api_client.config.logger.debug "API called: RefundApi#create_refund\nData: #{data.inspect}\nStatus code: #{status_code}\nHeaders: #{headers}"
      end
      return data, status_code, headers
    end
  end
end
