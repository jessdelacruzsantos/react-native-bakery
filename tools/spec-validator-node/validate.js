"use strict";

const sway = require('sway');
const fs = require('fs');

// const specFilename = '../../api.json.d/api.json';
const specFilename = 'api.json';

var contents = null;
try {
  contents = fs.readFileSync(specFilename, 'utf8');
} catch (e) {
  console.error("Unable to read file", specFilename);
  process.exit(1);
}

sway.create({definition: JSON.parse(contents)})
  .then(function(api) {
    var results = api.validate();
    if (results.errors.length) {
      console.log('Validation errors detected:')
      results.errors.forEach(function (e) {
        console.error(e)
      });
      process.exit(1);
    }
  }, function(err) {
    console.error(err);
    process.exit(1);
  });
