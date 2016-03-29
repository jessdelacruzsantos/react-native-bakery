=begin
Square Connect API

OpenAPI spec version: 2.0

Generated by: https://github.com/swagger-api/swagger-codegen.git


=end

require 'date'

module SquareConnect
  # Defines the query parameters that can be included in\na request to the [ListTransactions](#endpoint-listtransactions) endpoint.
  class ListTransactionsRequest
    # The beginning of the requested reporting period, in RFC 3339 format.\n\nDefault value: The current time minus one year.
    attr_accessor :begin_time

    # The end of the requested reporting period, in RFC 3339 format.\n\nDefault value: The current time.
    attr_accessor :end_time

    # The order in which results are listed in the response (`ASC` for\noldest first, `DESC` for newest first).\n\nDefault value: `DESC`
    attr_accessor :sort_order

    # A pagination cursor returned by a previous call to this endpoint.\nProvide this to retrieve the next set of results for your original query.\n\nSee [Paginating results](#paginatingresults) for more information.
    attr_accessor :cursor

    # Attribute mapping from ruby-style variable name to JSON key.
    def self.attribute_map
      {
        
        :'begin_time' => :'begin_time',
        
        :'end_time' => :'end_time',
        
        :'sort_order' => :'sort_order',
        
        :'cursor' => :'cursor'
        
      }
    end

    # Attribute type mapping.
    def self.swagger_types
      {
        :'begin_time' => :'String',
        :'end_time' => :'String',
        :'sort_order' => :'String',
        :'cursor' => :'String'
        
      }
    end

    def initialize(attributes = {})
      return unless attributes.is_a?(Hash)

      # convert string to symbol for hash key
      attributes = attributes.inject({}){|memo,(k,v)| memo[k.to_sym] = v; memo}

      
      if attributes[:'begin_time']
        self.begin_time = attributes[:'begin_time']
      end
      
      if attributes[:'end_time']
        self.end_time = attributes[:'end_time']
      end
      
      if attributes[:'sort_order']
        self.sort_order = attributes[:'sort_order']
      end
      
      if attributes[:'cursor']
        self.cursor = attributes[:'cursor']
      end
      
    end

    # Custom attribute writer method checking allowed values (enum).
    def sort_order=(sort_order)
      allowed_values = ["DESC", "ASC"]
      if sort_order && !allowed_values.include?(sort_order)
        fail "invalid value for 'sort_order', must be one of #{allowed_values}"
      end
      @sort_order = sort_order
    end

    # Check equality by comparing each attribute.
    def ==(o)
      return true if self.equal?(o)
      self.class == o.class &&
          begin_time == o.begin_time &&
          end_time == o.end_time &&
          sort_order == o.sort_order &&
          cursor == o.cursor
    end

    # @see the `==` method
    def eql?(o)
      self == o
    end

    # Calculate hash code according to all attributes.
    def hash
      [begin_time, end_time, sort_order, cursor].hash
    end

    # build the object from hash
    def build_from_hash(attributes)
      return nil unless attributes.is_a?(Hash)
      self.class.swagger_types.each_pair do |key, type|
        if type =~ /^Array<(.*)>/i
          if attributes[self.class.attribute_map[key]].is_a?(Array)
            self.send("#{key}=", attributes[self.class.attribute_map[key]].map{ |v| _deserialize($1, v) } )
          else
            #TODO show warning in debug mode
          end
        elsif !attributes[self.class.attribute_map[key]].nil?
          self.send("#{key}=", _deserialize(type, attributes[self.class.attribute_map[key]]))
        else
          # data not found in attributes(hash), not an issue as the data can be optional
        end
      end

      self
    end

    def _deserialize(type, value)
      case type.to_sym
      when :DateTime
        DateTime.parse(value)
      when :Date
        Date.parse(value)
      when :String
        value.to_s
      when :Integer
        value.to_i
      when :Float
        value.to_f
      when :BOOLEAN
        if value.to_s =~ /^(true|t|yes|y|1)$/i
          true
        else
          false
        end
      when /\AArray<(?<inner_type>.+)>\z/
        inner_type = Regexp.last_match[:inner_type]
        value.map { |v| _deserialize(inner_type, v) }
      when /\AHash<(?<k_type>.+), (?<v_type>.+)>\z/
        k_type = Regexp.last_match[:k_type]
        v_type = Regexp.last_match[:v_type]
        {}.tap do |hash|
          value.each do |k, v|
            hash[_deserialize(k_type, k)] = _deserialize(v_type, v)
          end
        end
      else # model
        _model = SquareConnect.const_get(type).new
        _model.build_from_hash(value)
      end
    end

    def to_s
      to_hash.to_s
    end

    # to_body is an alias to to_body (backward compatibility))
    def to_body
      to_hash
    end

    # return the object in the form of hash
    def to_hash
      hash = {}
      self.class.attribute_map.each_pair do |attr, param|
        value = self.send(attr)
        next if value.nil?
        hash[param] = _to_hash(value)
      end
      hash
    end

    # Method to output non-array value in the form of hash
    # For object, use to_hash. Otherwise, just return the value
    def _to_hash(value)
      if value.is_a?(Array)
        value.compact.map{ |v| _to_hash(v) }
      elsif value.is_a?(Hash)
        {}.tap do |hash|
          value.each { |k, v| hash[k] = _to_hash(v) }
        end
      elsif value.respond_to? :to_hash
        value.to_hash
      else
        value
      end
    end

  end
end
