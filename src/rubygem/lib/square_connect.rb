require 'net/http'
require 'uri'
require 'json'

gemdir = File.dirname(__FILE__)
Dir.foreach(gemdir + '/squareup/connect/v3/actions') do |path|
  if path.end_with? '.rb'
    require gemdir + '/squareup/connect/v3/actions/' + path
  end
end
Dir.foreach(gemdir + '/squareup/connect/v3/resources') do |path|
  if path.end_with? '.rb'
    require gemdir + '/squareup/connect/v3/resources/' + path
  end
end

class SquareConnect

  @@connectRoot = URI.parse('https://connect.squareup.com')

  def self.Charge(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/Charge'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::ChargeResponse)
  end

  def self.Refund(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/Refund'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::RefundResponse)
  end

  def self.ListLocations(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListLocations'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::ListLocationsResponse)
  end

  def self.CreateCard(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCard'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::CreateCardResponse)
  end

  def self.UpsertCustomer(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/UpsertCustomer'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::UpsertCustomerResponse)
  end

  def self.ListCustomers(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListCustomers'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::ListCustomersResponse)
  end

  def self.RetrieveCustomer(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveCustomer'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::RetrieveCustomerResponse)
  end

  def self.CreateCustomerCard(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCustomerCard'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::CreateCustomerCardResponse)
  end

  def self.UpdateCustomerCard(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/UpdateCustomerCard'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::UpdateCustomerCardResponse)
  end

  def self.DeleteCustomerCard(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/DeleteCustomerCard'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::DeleteCustomerCardResponse)
  end

  def self.CaptureTransaction(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/CaptureTransaction'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::CaptureTransactionResponse)
  end

  def self.VoidTransaction(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/VoidTransaction'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::VoidTransactionResponse)
  end

  def self.ListTransactions(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListTransactions'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::ListTransactionsResponse)
  end

  def self.RetrieveTransaction(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveTransaction'
    requestBody = requestObject.serialize_to_string()
    return self.sendRequest(requestPath, context, requestBody, Squareup::Connect::V3::Actions::RetrieveTransactionResponse)
  end


  def self.sendRequest(path, context, body, responseClass)
    http = Net::HTTP.new(@@connectRoot.host, @@connectRoot.port)
    http.use_ssl = true
    request = Net::HTTP::Post.new(path)
    request.add_field('Content-Type', 'application/x-protobuf')
    request.add_field('Authorization', 'Bearer ' + context.access_token)
    request.body = body
    response = http.request(request)
    return responseClass.parse(response.body)
  end

  def self.ObtainToken(clientId, clientSecret, code, redirectUri = '')
    http = Net::HTTP.new(@@connectRoot.host, @@connectRoot.port)
    http.use_ssl = true
    request = Net::HTTP::Post.new('/oauth2/token')
    request.add_field('Content-Type', 'application/json')
    requestBody = {
      :client_id => clientId,
      :client_secret => clientSecret,
      :code => code
    }
    if redirectUri != ''
      requestBody[:redirect_uri] = redirectUri
    end
    request.body = requestBody.to_json
    response = http.request(request);

    return response
  end

  def self.RenewToken(clientId, clientSecret, accessToken)
    http = Net::HTTP.new(@@connectRoot.host, @@connectRoot.port)
    http.use_ssl = true
    request = Net::HTTP::Post.new('/oauth2/clients/' + clientId + '/access-token/renew')
    request.add_field('Content-Type', 'application/json')
    request.add_field('Authorization', 'Client ' + clientSecret)
    requestBody = {
      :access_token => accessToken
    }
    request.body = requestBody.to_json
    response = http.request(request);

    return response
  end

  def self.RevokeToken(clientId, clientSecret, accessToken, merchantId='')
    http = Net::HTTP.new(@@connectRoot.host, @@connectRoot.port)
    http.use_ssl = true
    request = Net::HTTP::Post.new('/oauth2/revoke')
    request.add_field('Content-Type', 'application/json')
    request.add_field('Authorization', 'Client ' + clientSecret)
    requestBody = {
      :client_id => clientId
    }
    if merchantId == ''
      requestBody[:access_token] = accessToken
    else
      requestBody[:merchant_id] = merchantId
    end
    request.body = requestBody.to_json
    response = http.request(request);

    return response
  end
end

class RequestContext
  attr_reader :access_token

  def initialize(token)
    @access_token = token
  end
end

class OAuthResponse
  attr_reader :headers
  attr_reader :body
  attr_reader :code

  def initialize(headers, body, code)
    @headers = headers
    @body = body
    @code = code
  end
end
