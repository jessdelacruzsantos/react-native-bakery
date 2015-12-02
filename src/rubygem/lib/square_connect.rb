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

  @@connectRoot = URI.parse('https://connect.squareupstaging.com')

  def self.Charge(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/Charge'
    request = Squareup::Connect::V3::Actions::ChargeRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], true)
request.idempotency_key = self.checkValue('idempotency_key', requestHash['idempotency_key'], true)
if !requestHash['amount_money'].nil?
  request.amount_money = Squareup::Connect::V3::Resources::Money.new();
  request.amount_money.amount = self.checkValue('amount', requestHash['amount_money']['amount'], false)
  request.amount_money.currency = self.checkValue('currency', requestHash['amount_money']['currency'], false)
else
  raise "Missing required field " + field.name
end

request.delay_capture = self.checkValue('delay_capture', requestHash['delay_capture'], false)
request.reference_id = self.checkValue('reference_id', requestHash['reference_id'], false)
request.note = self.checkValue('note', requestHash['note'], false)
if !requestHash['card_nonce'].nil?
  request.card_nonce = Squareup::Connect::V3::Actions::ChargeRequest::CardNonceInstrument.new();
  request.card_nonce.card_nonce = self.checkValue('card_nonce', requestHash['card_nonce']['card_nonce'], true)
  if !requestHash['card_nonce']['billing_address'].nil?
  request.card_nonce.billing_address = Squareup::Connect::V3::Resources::Address.new();
  request.card_nonce.billing_address.line_1 = self.checkValue('line_1', requestHash['card_nonce']['billing_address']['line_1'], false)
  request.card_nonce.billing_address.line_2 = self.checkValue('line_2', requestHash['card_nonce']['billing_address']['line_2'], false)
  request.card_nonce.billing_address.line_3 = self.checkValue('line_3', requestHash['card_nonce']['billing_address']['line_3'], false)
  request.card_nonce.billing_address.locality = self.checkValue('locality', requestHash['card_nonce']['billing_address']['locality'], false)
  request.card_nonce.billing_address.sublocality = self.checkValue('sublocality', requestHash['card_nonce']['billing_address']['sublocality'], false)
  request.card_nonce.billing_address.sublocality_2 = self.checkValue('sublocality_2', requestHash['card_nonce']['billing_address']['sublocality_2'], false)
  request.card_nonce.billing_address.sublocality_3 = self.checkValue('sublocality_3', requestHash['card_nonce']['billing_address']['sublocality_3'], false)
  request.card_nonce.billing_address.region = self.checkValue('region', requestHash['card_nonce']['billing_address']['region'], false)
  request.card_nonce.billing_address.region_2 = self.checkValue('region_2', requestHash['card_nonce']['billing_address']['region_2'], false)
  request.card_nonce.billing_address.region_3 = self.checkValue('region_3', requestHash['card_nonce']['billing_address']['region_3'], false)
  request.card_nonce.billing_address.postal_code = self.checkValue('postal_code', requestHash['card_nonce']['billing_address']['postal_code'], false)
  request.card_nonce.billing_address.country = self.checkValue('country', requestHash['card_nonce']['billing_address']['country'], false)
end

  request.card_nonce.customer_id = self.checkValue('customer_id', requestHash['card_nonce']['customer_id'], false)
end

if !requestHash['customer_card'].nil?
  request.customer_card = Squareup::Connect::V3::Actions::ChargeRequest::CustomerCardInstrument.new();
  request.customer_card.customer_id = self.checkValue('customer_id', requestHash['customer_card']['customer_id'], true)
  request.customer_card.customer_card_id = self.checkValue('customer_card_id', requestHash['customer_card']['customer_card_id'], false)
end


    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::ChargeResponse))
  end

  def self.RefundTender(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/RefundTender'
    request = Squareup::Connect::V3::Actions::RefundTenderRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], true)
request.idempotency_key = self.checkValue('idempotency_key', requestHash['idempotency_key'], true)
request.source_transaction_id = self.checkValue('source_transaction_id', requestHash['source_transaction_id'], true)
request.source_tender_id = self.checkValue('source_tender_id', requestHash['source_tender_id'], true)
request.reason = self.checkValue('reason', requestHash['reason'], true)
if !requestHash['amount_money'].nil?
  request.amount_money = Squareup::Connect::V3::Resources::Money.new();
  request.amount_money.amount = self.checkValue('amount', requestHash['amount_money']['amount'], false)
  request.amount_money.currency = self.checkValue('currency', requestHash['amount_money']['currency'], false)
else
  raise "Missing required field " + field.name
end


    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::RefundTenderResponse))
  end

  def self.ListLocations(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListLocations'
    request = Squareup::Connect::V3::Actions::ListLocationsRequest.new()

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::ListLocationsResponse))
  end

  def self.CreateCardNonce(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCardNonce'
    request = Squareup::Connect::V3::Actions::CreateCardNonceRequest.new()
request.client_id = self.checkValue('client_id', requestHash['client_id'], true)
request.business_id = self.checkValue('business_id', requestHash['business_id'], false)
if !requestHash['card_data'].nil?
  request.card_data = Squareup::Connect::V3::Resources::CardData.new();
  request.card_data.number = self.checkValue('number', requestHash['card_data']['number'], false)
  request.card_data.exp_month = self.checkValue('exp_month', requestHash['card_data']['exp_month'], false)
  request.card_data.exp_year = self.checkValue('exp_year', requestHash['card_data']['exp_year'], false)
  request.card_data.cvv = self.checkValue('cvv', requestHash['card_data']['cvv'], false)
  request.card_data.name = self.checkValue('name', requestHash['card_data']['name'], false)
  if !requestHash['card_data']['billing_address'].nil?
  request.card_data.billing_address = Squareup::Connect::V3::Resources::Address.new();
  request.card_data.billing_address.line_1 = self.checkValue('line_1', requestHash['card_data']['billing_address']['line_1'], false)
  request.card_data.billing_address.line_2 = self.checkValue('line_2', requestHash['card_data']['billing_address']['line_2'], false)
  request.card_data.billing_address.line_3 = self.checkValue('line_3', requestHash['card_data']['billing_address']['line_3'], false)
  request.card_data.billing_address.locality = self.checkValue('locality', requestHash['card_data']['billing_address']['locality'], false)
  request.card_data.billing_address.sublocality = self.checkValue('sublocality', requestHash['card_data']['billing_address']['sublocality'], false)
  request.card_data.billing_address.sublocality_2 = self.checkValue('sublocality_2', requestHash['card_data']['billing_address']['sublocality_2'], false)
  request.card_data.billing_address.sublocality_3 = self.checkValue('sublocality_3', requestHash['card_data']['billing_address']['sublocality_3'], false)
  request.card_data.billing_address.region = self.checkValue('region', requestHash['card_data']['billing_address']['region'], false)
  request.card_data.billing_address.region_2 = self.checkValue('region_2', requestHash['card_data']['billing_address']['region_2'], false)
  request.card_data.billing_address.region_3 = self.checkValue('region_3', requestHash['card_data']['billing_address']['region_3'], false)
  request.card_data.billing_address.postal_code = self.checkValue('postal_code', requestHash['card_data']['billing_address']['postal_code'], false)
  request.card_data.billing_address.country = self.checkValue('country', requestHash['card_data']['billing_address']['country'], false)
end

else
  raise "Missing required field " + field.name
end


    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::CreateCardNonceResponse))
  end

  def self.UpsertCustomer(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/UpsertCustomer'
    request = Squareup::Connect::V3::Actions::UpsertCustomerRequest.new()
if !requestHash['customer'].nil?
  request.customer = Squareup::Connect::V3::Resources::Customer.new();
  request.customer.id = self.checkValue('id', requestHash['customer']['id'], false)
  request.customer.business_id = self.checkValue('business_id', requestHash['customer']['business_id'], false)
  request.customer.location_id = self.checkValue('location_id', requestHash['customer']['location_id'], false)
  request.customer.created_at = self.checkValue('created_at', requestHash['customer']['created_at'], false)
  request.customer.updated_at = self.checkValue('updated_at', requestHash['customer']['updated_at'], false)
  if !requestHash['customer']['cards'].nil?
  request.customer.cards = Squareup::Connect::V3::Resources::Card.new();
  request.customer.cards.id = self.checkValue('id', requestHash['customer']['cards']['id'], false)
  request.customer.cards.card_brand = self.checkValue('card_brand', requestHash['customer']['cards']['card_brand'], false)
  request.customer.cards.last_4 = self.checkValue('last_4', requestHash['customer']['cards']['last_4'], false)
  request.customer.cards.exp_month = self.checkValue('exp_month', requestHash['customer']['cards']['exp_month'], false)
  request.customer.cards.exp_year = self.checkValue('exp_year', requestHash['customer']['cards']['exp_year'], false)
end

  request.customer.given_name = self.checkValue('given_name', requestHash['customer']['given_name'], false)
  request.customer.family_name = self.checkValue('family_name', requestHash['customer']['family_name'], false)
  request.customer.nickname = self.checkValue('nickname', requestHash['customer']['nickname'], false)
  request.customer.email_address = self.checkValue('email_address', requestHash['customer']['email_address'], false)
  if !requestHash['customer']['address'].nil?
  request.customer.address = Squareup::Connect::V3::Resources::Address.new();
  request.customer.address.line_1 = self.checkValue('line_1', requestHash['customer']['address']['line_1'], false)
  request.customer.address.line_2 = self.checkValue('line_2', requestHash['customer']['address']['line_2'], false)
  request.customer.address.line_3 = self.checkValue('line_3', requestHash['customer']['address']['line_3'], false)
  request.customer.address.locality = self.checkValue('locality', requestHash['customer']['address']['locality'], false)
  request.customer.address.sublocality = self.checkValue('sublocality', requestHash['customer']['address']['sublocality'], false)
  request.customer.address.sublocality_2 = self.checkValue('sublocality_2', requestHash['customer']['address']['sublocality_2'], false)
  request.customer.address.sublocality_3 = self.checkValue('sublocality_3', requestHash['customer']['address']['sublocality_3'], false)
  request.customer.address.region = self.checkValue('region', requestHash['customer']['address']['region'], false)
  request.customer.address.region_2 = self.checkValue('region_2', requestHash['customer']['address']['region_2'], false)
  request.customer.address.region_3 = self.checkValue('region_3', requestHash['customer']['address']['region_3'], false)
  request.customer.address.postal_code = self.checkValue('postal_code', requestHash['customer']['address']['postal_code'], false)
  request.customer.address.country = self.checkValue('country', requestHash['customer']['address']['country'], false)
end

  request.customer.phone_number = self.checkValue('phone_number', requestHash['customer']['phone_number'], false)
  request.customer.memo = self.checkValue('memo', requestHash['customer']['memo'], false)
else
  raise "Missing required field " + field.name
end


    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::UpsertCustomerResponse))
  end

  def self.ListCustomers(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListCustomers'
    request = Squareup::Connect::V3::Actions::ListCustomersRequest.new()
if !requestHash['params'].nil?
  request.params = Squareup::Connect::V3::Actions::ListCustomersRequest::Params.new();
  request.params.location_id = self.checkValue('location_id', requestHash['params']['location_id'], false)
end

request.cursor = self.checkValue('cursor', requestHash['cursor'], false)

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::ListCustomersResponse))
  end

  def self.RetrieveCustomer(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveCustomer'
    request = Squareup::Connect::V3::Actions::RetrieveCustomerRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], false)
request.customer_id = self.checkValue('customer_id', requestHash['customer_id'], true)

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::RetrieveCustomerResponse))
  end

  def self.CreateCustomerCard(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/CreateCustomerCard'
    request = Squareup::Connect::V3::Actions::CreateCustomerCardRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], true)
request.customer_id = self.checkValue('customer_id', requestHash['customer_id'], true)
request.card_nonce = self.checkValue('card_nonce', requestHash['card_nonce'], false)
if !requestHash['card_data'].nil?
  request.card_data = Squareup::Connect::V3::Resources::CardData.new();
  request.card_data.number = self.checkValue('number', requestHash['card_data']['number'], false)
  request.card_data.exp_month = self.checkValue('exp_month', requestHash['card_data']['exp_month'], false)
  request.card_data.exp_year = self.checkValue('exp_year', requestHash['card_data']['exp_year'], false)
  request.card_data.cvv = self.checkValue('cvv', requestHash['card_data']['cvv'], false)
  request.card_data.name = self.checkValue('name', requestHash['card_data']['name'], false)
  if !requestHash['card_data']['billing_address'].nil?
  request.card_data.billing_address = Squareup::Connect::V3::Resources::Address.new();
  request.card_data.billing_address.line_1 = self.checkValue('line_1', requestHash['card_data']['billing_address']['line_1'], false)
  request.card_data.billing_address.line_2 = self.checkValue('line_2', requestHash['card_data']['billing_address']['line_2'], false)
  request.card_data.billing_address.line_3 = self.checkValue('line_3', requestHash['card_data']['billing_address']['line_3'], false)
  request.card_data.billing_address.locality = self.checkValue('locality', requestHash['card_data']['billing_address']['locality'], false)
  request.card_data.billing_address.sublocality = self.checkValue('sublocality', requestHash['card_data']['billing_address']['sublocality'], false)
  request.card_data.billing_address.sublocality_2 = self.checkValue('sublocality_2', requestHash['card_data']['billing_address']['sublocality_2'], false)
  request.card_data.billing_address.sublocality_3 = self.checkValue('sublocality_3', requestHash['card_data']['billing_address']['sublocality_3'], false)
  request.card_data.billing_address.region = self.checkValue('region', requestHash['card_data']['billing_address']['region'], false)
  request.card_data.billing_address.region_2 = self.checkValue('region_2', requestHash['card_data']['billing_address']['region_2'], false)
  request.card_data.billing_address.region_3 = self.checkValue('region_3', requestHash['card_data']['billing_address']['region_3'], false)
  request.card_data.billing_address.postal_code = self.checkValue('postal_code', requestHash['card_data']['billing_address']['postal_code'], false)
  request.card_data.billing_address.country = self.checkValue('country', requestHash['card_data']['billing_address']['country'], false)
end

end


    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::CreateCustomerCardResponse))
  end

  def self.DeleteCustomerCard(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/DeleteCustomerCard'
    request = Squareup::Connect::V3::Actions::DeleteCustomerCardRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], true)
request.customer_id = self.checkValue('customer_id', requestHash['customer_id'], true)
request.card_id = self.checkValue('card_id', requestHash['card_id'], true)

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::DeleteCustomerCardResponse))
  end

  def self.CaptureTransaction(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/CaptureTransaction'
    request = Squareup::Connect::V3::Actions::CaptureTransactionRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], true)
request.transaction_id = self.checkValue('transaction_id', requestHash['transaction_id'], true)

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::CaptureTransactionResponse))
  end

  def self.VoidTransaction(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/VoidTransaction'
    request = Squareup::Connect::V3::Actions::VoidTransactionRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], true)
request.transaction_id = self.checkValue('transaction_id', requestHash['transaction_id'], true)

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::VoidTransactionResponse))
  end

  def self.ListTransactions(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/ListTransactions'
    request = Squareup::Connect::V3::Actions::ListTransactionsRequest.new()
if !requestHash['params'].nil?
  request.params = Squareup::Connect::V3::Actions::ListTransactionsRequest::Params.new();
  request.params.location_id = self.checkValue('location_id', requestHash['params']['location_id'], true)
  request.params.begin_time = self.checkValue('begin_time', requestHash['params']['begin_time'], true)
  request.params.end_time = self.checkValue('end_time', requestHash['params']['end_time'], true)
  request.params.sort = self.checkValue('sort', requestHash['params']['sort'], true)
end

request.cursor = self.checkValue('cursor', requestHash['cursor'], false)

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::ListTransactionsResponse))
  end

  def self.RetrieveTransaction(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/RetrieveTransaction'
    request = Squareup::Connect::V3::Actions::RetrieveTransactionRequest.new()
request.location_id = self.checkValue('location_id', requestHash['location_id'], true)
request.transaction_id = self.checkValue('transaction_id', requestHash['transaction_id'], true)

    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::RetrieveTransactionResponse))
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

  def self.checkValue(fieldName, value, required)
    if (value.nil? && required)
      raise "Missing required field " + fieldName
    end
    return value
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
