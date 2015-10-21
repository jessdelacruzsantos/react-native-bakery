require 'fileutils'
require 'Find'

HOME_DIR = ENV['HOME']

def generate_class(path)

  # PHP
  dir_path = File.dirname(path)
  proto_path_prefix = HOME_DIR + '/Development/connect-public-protos/squareup/connect/v3/'
  proto_output_location = HOME_DIR + '/Development/connect-sdks/src/php/generated/' + dir_path.partition(proto_path_prefix).last
  puts 'LOCATION ' + proto_output_location

  if !File.directory?(proto_output_location)
    FileUtils.mkdir_p(proto_output_location)
  end

  system "protoc --plugin=protoc-gen-php='#{HOME_DIR}/Development/connect-sdks/lib/Protobuf-PHP/protoc-gen-php.php' \
          --proto_path=#{HOME_DIR}/Development/connect-public-protos \
          --php_out='skip-imported=true:#{proto_output_location}' \
          #{path}"
end

Find.find(HOME_DIR + '/Development/connect-public-protos/squareup/connect/v3') do | path |
  if path.end_with?('.proto')
    generate_class(path)
  end
end
