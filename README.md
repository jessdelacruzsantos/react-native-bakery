# Connect SDKs

This repo contains the source for the SDKs we generate for developers to use
the next generation of the Connect API.

**Note:** Some of the stuff described in this README hasn't been fully implemented yet.

## Layout

The `tools` directory contains tools for generating portions of the SDKs:

* `api-parser` is a Java application that parses the v3 protos in `connect-public-protos`
  and spits out a JSON representation of the API. Check its README to learn how to use it.

* `sdk-gen` is a Node application that consumes the JSON generated by `api-parser`
  and produces the __non-proto__ classes for the SDKs via Handlebars templates
  (right now `sdk-gen` has templates to generate classes for PHP and Ruby only).
  Check its README as well.

* The `src` directory contains actual source for various languages' SDKs. This
  includes both core classes generated by `sdk-gen` and proto classes generated by
  the `generate_proto_classes.rb` script in the `script` directory.

## Updating the SDKs

There are two pieces of each SDK that potentially need updating: The proto-generated
classes, and the core classes.

### Updating proto classes

1. Make sure you have the latest version of `connect-public-protos` checked out.

2. Run `generate_proto_classes.rb` in the `script` directory. In a perfect world,
   new proto-generated classes will appear for all currently supported languages.
   **Running this script requires some setup. See the comments at the top of the script.**

3. If you updated the protos, your probably also need to update the core classes
   (_definitely_ if the proto changes include a new endpoint). Follow the steps
   in __Updating core classes__ as well.

4. Commit and push your changes.

### Updating core classes

1. Handlebars templates for most SDK core classes live in `tools/api-parser`. If
   you want to change the boilerplate code generated for a core class, edit it there.

2. If you're updating core classes because protos changed, you first need to parse
   the latest version of the protos. Run `tools/api-parser` to generate an updated
   JSON representation of the API. See its README for details.

3. Run the `tools/api-parser` Node app (see its README for details) to generate new
   core classes for supported languages.

4. Commit and push your changes.


## TODOs

### Testing
* Test SDKs across multiple versions of associated languages

## First-wave languages
* PHP (prototype near complete)
* Ruby (prototype near complete)

## Second-wave languages
* Java (proto classes generated)
* Python (proto classes generated)
* JS (proto classes generated)
