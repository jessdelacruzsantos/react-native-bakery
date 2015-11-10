require 'net/http'
require 'uri'

gemdir = File.dirname(__FILE__)
Dir.foreach(gemdir + '/squareup/connect/v3/actions') do |path|
  if path.end_with? '.rb'
    require path
  end
end
Dir.foreach(gemdir + '/squareup/connect/v3/resources') do |path|
  if path.end_with? '.rb'
    require path
  end
end

class SquareConnect

  @@connectRoot = URI.parse('https://connect.squareup.com')

  {{#each this.endpoints}}
  def self.{{this.id}}(context, requestObject)
    requestPath = '/services/squareup.connect.v3.SquareConnectV3/{{this.id}}'
    requestBody = requestObject.serialize_to_string()
    responseWrapper = Squareup::Connect::V3::Actions::{{#paramify}}{{this.outputtype}}{{/paramify}}.new()
    self.sendRequest(requestPath, context, requestBody, responseWrapper)
  end

  {{/each}}

  def self.sendRequest(path, context, body, responseWrapper)
    http = Net::HTTP.new(@@connectRoot.host, @@connectRoot.port)
    http.use_ssl = true
    request = Net::HTTP::Post.new(path)
    request.add_field('Content-Type', 'application/x-protobuf')
    request.add_field('Authorization', 'Bearer ' + context.access_token)
    request.body = body
    response = http.request(request)
  end

end

class RequestContext
  attr_reader :access_token

  def initialize(token)
    @access_token = token
  end
end
