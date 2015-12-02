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

  {{#each this.endpoints}}
  def self.{{this.id}}(context, requestHash)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/{{this.id}}'
    {{#populateRequestObjectRuby}}{{this.inputtype}}{{/populateRequestObjectRuby}}
    return ::ProtocolBuffers::Message.to_hash(self.sendRequest(requestPath, context, request.serialize_to_string(), Squareup::Connect::V3::Actions::{{this.id}}Response))
  end

  {{/each}}

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
