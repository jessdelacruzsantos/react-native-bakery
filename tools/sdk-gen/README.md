# sdk-gen

This is a Node application that can do two things:

* Generate core classes for Connect SDKs
* Generate HTML documentation for Connect SDKs.

## Building
Just run `npm install`. That should do it.

## Running
To generate core classes, run `node index.js`. Core classes for PHP and Ruby
SDKs will be written to the proper directories within `src`.

To generate documentation, run `node doc-gen.js`.
