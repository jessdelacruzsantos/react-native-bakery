var handlebars = require('handlebars');
var fs = require('fs');


// Load in the API definitions and index them
var apiDefinition = JSON.parse(fs.readFileSync('api.json', 'utf-8'));

// Load in the various language SDK templates
var phpTemplate = handlebars.compile(fs.readFileSync('squareconnect-template.php', 'utf-8'));
var rubyTemplate = handlebars.compile(fs.readFileSync('squareconnect-template.rb', 'utf-8'));

var warnings = [];

// Helper methods for Handlebars

// Removes whitespace in addition to making lower case
handlebars.registerHelper('idify', function(options) {
  return options.fn(this).toLowerCase().replace(/ /g,'').replace(/\./g, '');
});

handlebars.registerHelper('paramify', function(options) {
  var splitString = options.fn(this).split('.');
  return splitString[splitString.length - 1];
});

handlebars.registerHelper('classify', function(options) {
  return options.fn(this).replace('.', '');
});

handlebars.registerHelper('backslash', function(options) {
  return '\\';
});

// Sort endpoints into buckets by entity
var endpoints = apiDefinition.endpoints;
var bucketedEndpoints = {};

for (var i = 0; i < endpoints.length; i++) {
  var endpoint = endpoints[i];
  validateEndpoint(endpoint);
  if (!bucketedEndpoints.hasOwnProperty(endpoint['entity'])) {
    bucketedEndpoints[endpoint['entity']] = {
      "entity": endpoint['entity'],
      "actions": []
    };
  }
  bucketedEndpoints[endpoint['entity']].actions.push(endpoint);
}

for (var endpointEntity in bucketedEndpoints) {
  bucketedEndpoints[endpointEntity].actions.sort(function(a, b) {
    if (a.action < b.action) {
      return -1;
    } else if (a.action > b.action) {
      return 1;
    } else {
      return 0;
    }
  });
}

// Now add datatypes
var objectTypes = {}
var datatypes = apiDefinition.datatypes;
datatypes.sort(function(a, b) {
  if (a.name < b.name) {
  	return -1;
  } else if (a.name > b.name) {
  	return 1;
  } else {
  	return 0;
  }
});
validateDatatypes(datatypes);

for (var i = 0; i < datatypes.length; i++) {
  objectTypes[datatypes[i].id] = datatypes[i];
}

// And add enums
var enums = apiDefinition.enums;
enums.sort(function(a, b) {
  if (a.name < b.name) {
  	return -1;
  } else if (a.name > b.name) {
  	return 1;
  } else {
  	return 0;
  }
});
validateEnums(enums);

for (var i = 0; i < enums.length; i++) {
  objectTypes[enums[i].id] = enums[i];
}

var phpCore = phpTemplate(apiDefinition);
var rubyCore = rubyTemplate(apiDefinition);


var homeDirectory = process.env['HOME'];

console.log("Writing PHP core class");
fs.writeFileSync(homeDirectory + '/Development/connect-sdks/src/php/SquareConnect.php', phpCore);

console.log("Writing Ruby core class");
fs.writeFileSync(homeDirectory + '/Development/connect-sdks/src/rubygem/square_connect.rb', rubyCore);

console.log("All done.");


// Log warnings encountered during processing.
/*console.log("WARNINGS:");
for (var i = 0; i < warnings.length; i++) {
  console.log(warnings[i]);
}*/

/*// Write the docpage out.
fs.writeFile("/Users/barlow/Development/connect-documentation-website/source/sections/_connect_v2_docpage.html", docpageHTML, function(err){
  if(err) {
  	return console.log(err);
  }
  console.log('Docpage saved successfully');
})*/

function validateEndpoint(endpoint) {
  if (endpoint.httpmethod == undefined || endpoint.httpmethod == '') {
    warnings.push("Endpoint " + endpoint.id + " missing `httpmethod`");
  }

  if (endpoint.path == undefined || endpoint.path == '') {
    warnings.push("Endpoint " + endpoint.id + " missing `path`");
  }

  if (endpoint.entity == undefined || endpoint.entity == '') {
    warnings.push("Endpoint " + endpoint.id + " missing `entity`");
  }

  if (endpoint.action == undefined || endpoint.action == '') {
    warnings.push("Endpoint " + endpoint.id + " missing `action`");
  }

  if (endpoint.description == undefined || endpoint.description == '') {
    warnings.push("Endpoint " + endpoint.id + " missing `description`");
  }
}

function validateDatatypes(datatypes) {
  for (var i = 0; i < datatypes.length; i++) {
    var datatype = datatypes[i];

    if (datatype.description == undefined || datatype.description == '') {
      warnings.push("Datatype " + datatype.id + " missing `description`");
    }
  }
 }

function validateEnums(enums) {
  for (var i = 0; i < enums.length; i++) {
    var enumm = enums[i];

    if (enumm.description == undefined || enumm.description == '') {
      warnings.push("Enum " + enumm.id + " missing `description`");
    }
  }
}

function populateSchema(sections, symbols) {

  for (var i = 0; i < sections.length; i++) {
    var section = sections[i];
    if (section.hasOwnProperty('entity')) {
      var correspondingSymbols = symbols[section.entity];
      if (correspondingSymbols != undefined) {
        if (correspondingSymbols.hasOwnProperty('datatypes')){
          section.datatypes = correspondingSymbols.datatypes;
        }
        if (correspondingSymbols.hasOwnProperty('enums')) {
          section.enums = correspondingSymbols.enums;
        }
        if (correspondingSymbols.hasOwnProperty('endpoints')) {
          section.endpoints = correspondingSymbols.endpoints;
        }
        if (correspondingSymbols.hasOwnProperty('articles')) {
          section.articles = correspondingSymbols.articles;
        }
        if (correspondingSymbols.hasOwnProperty('changelogs')) {
          section.changelogs = correspondingSymbols.changelogs;
        }
      }
    }
    if (section.hasOwnProperty('sections')) {
        populateSchema(sections[i].sections, symbols);
    }
  }
}
