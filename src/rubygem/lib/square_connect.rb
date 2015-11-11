require 'net/http'
require 'uri'

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

end

class RequestContext
  attr_reader :access_token

  def initialize(token)
    @access_token = token
  end
end
