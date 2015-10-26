# Connect SDKs

This repo contains the source for the SDKs we generate for developers to use
Connect API v2.

Each language we support will have its own directory under `src`. Each language's
directory has a `generated` directory beneath it, which contains `protoc`-generated
wrappers for the SDK.

The `script` directory contains scripts that assist with the generation of these
wrappers, along with other tasks.

**Note: The generate_proto_classes.rb script will not work for you out of the box
yet. Soon though!**
