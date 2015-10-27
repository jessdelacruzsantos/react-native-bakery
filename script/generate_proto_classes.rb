# BEFORE YOU USE THIS SCRIPT, YOU MUST DO ALL OF THE FOLLOWING!!
#
# 1. Install pear (a PHP package repository) by following the instructions here:
#    http://jason.pureconcepts.net/2012/10/install-pear-pecl-mac-os-x/
#
# 2. Run the following command:
#    pear install Console_CommandLine
#
# 3. Check if you have the file /etc/php.ini
#    (If you don't, create it)
#
# 4. Add the following line to /etc/php.ini:
#    include_path = "/usr/local/pear/share/pear"
#
#    Note: If you already set the include_path variable in /etc/php.ini, append
#          the above path to the existing value with a leading semicolon
#
# 5. Run the following commands from the repo root:
#    git submodule init
#    git submodule update
#    (The OSS PHP proto generation repo is a submodule of this repo.)
#
# Okay, you should be good to go:
# ruby generate_proto_classes.rb


require 'fileutils'
require 'Find'

HOME_DIR = ENV['HOME']

def generate_class(path)

  dir_path = File.dirname(path)
  proto_path_prefix = HOME_DIR + '/Development/connect-public-protos/squareup/connect/v3/'
  proto_output_location = HOME_DIR + '/Development/connect-sdks/src/php/generated/' + dir_path.partition(proto_path_prefix).last

  if !File.directory?(proto_output_location)
    FileUtils.mkdir_p(proto_output_location)
  end

  # PHP
  system "protoc --plugin=protoc-gen-php='#{HOME_DIR}/Development/connect-sdks/src/php/Protobuf-PHP/protoc-gen-php.php' \
          --proto_path=#{HOME_DIR}/Development/connect-public-protos \
          --php_out='skip-imported=true:#{proto_output_location}' \
          #{path}"

  # Python
  proto_output_location = HOME_DIR + '/Development/connect-sdks/src/python/generated'

  system "protoc \
          --proto_path=#{HOME_DIR}/Development/connect-public-protos \
          --python_out='#{proto_output_location}' \
          #{path}"
end

Find.find(HOME_DIR + '/Development/connect-public-protos/squareup/connect/v3') do | path |
  if path.end_with?('.proto')
    generate_class(path)
  end
end
