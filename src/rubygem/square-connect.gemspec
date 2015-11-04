Gem::Specification.new do |s|
  s.name = 'square-connect'
  s.version = '0.0.0'
  s.files = ['lib/square-connect.rb']
  s.files += Dir['lib/squareup/connect/v3/actions/*.rb']
  s.files += Dir['lib/squareup/connect/v3/resources/*.rb']
  s.summary = 'foo'
  s.authors = 'barlow'
  s.require_paths = ['lib']
end
