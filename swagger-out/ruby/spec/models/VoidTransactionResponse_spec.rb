=begin
Square Connect API

OpenAPI spec version: 2.0

Generated by: https://github.com/swagger-api/swagger-codegen.git


=end

require 'spec_helper'
require 'json'
require 'date'

# Unit tests for SquareConnect::
# Automatically generated by swagger-codegen (github.com/swagger-api/swagger-codegen)
# Please update as you see appropriate
describe 'VoidTransactionResponse' do
  before do
    # run before each test
    @instance = SquareConnect::VoidTransactionResponse.new
  end

  after do
    # run after each test
  end

  describe 'test an instance of VoidTransactionResponse' do
    it 'should create an instact of VoidTransactionResponse' do
      @instance.should be_a(SquareConnect::VoidTransactionResponse) 
    end
  end
  describe 'test attribute "errors"' do
    it 'should work' do
       # assertion here
       # should be_a()
       # should be_nil
       # should ==
       # should_not ==
    end
  end

end

