Gem::Specification.new do |gem|
  gem.name = 'square_connect'
  gem.version = '0.0.0'
  gem.files = ['lib/square_connect.rb']
  gem.files += Dir['lib/squareup/connect/v3/actions/*.rb']
  gem.files += Dir['lib/squareup/connect/v3/resources/*.rb']
  gem.summary = 'foo'
  gem.authors = 'barlow'
  gem.require_paths = ['lib']
end
