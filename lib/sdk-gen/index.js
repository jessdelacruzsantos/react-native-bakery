var handlebars = require('handlebars');
var fs = require('fs');


// Load in the API definitions and index them
var apiDefinition = JSON.parse(fs.readFileSync('api.json', 'utf-8'));

// Load in the PHP class definitions
var phpTemplate = handlebars.compile(fs.readFileSync('squareconnect-template.php', 'utf-8'));

//handlebars.registerPartial('endpoint', endpointTemplate);

var warnings = [];


// Helper methods for Handlebars

// Removes whitespace in addition to making lower case
handlebars.registerHelper('idify', function(options) {
  return options.fn(this).toLowerCase().replace(/ /g,'').replace(/\./g, '');
});

handlebars.registerHelper('paramify', function(options) {
  var splitString = options.fn(this).toLowerCase().split('.');
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

var phpThing = phpTemplate(apiDefinition);
console.log(phpThing);

// Templatize all of the endpoints
for (var entityName in bucketedEndpoints) {
  var entity = bucketedEndpoints[entityName];


  for (var i = 0; i < entity.actions.length; i++) {
    var endpointPath = entity.actions[i].path;

    var pathComponents = endpointPath.split('/');
    var phpPathString = "'";
    for (var j = 0; j < pathComponents.length; j++) {
      if (pathComponents[j] != '') {
        phpPathString = phpPathString + '/';
        if (pathComponents[j].charAt(0) == ':') {
          phpPathString = phpPathString + "'" + " . " + "$" + pathComponents[j].replace(":", "") + " . '";
        } else {
        	phpPathString = phpPathString + pathComponents[j];
        }
      }
    }
    phpPathString = phpPathString + "'";
  }
  //console.log(bucketedEndpoints[entityName]);
}
/*
var phpThing = phpTemplate(bucketedEndpoints['Transaction']);
console.log(objectTypes);
console.log(phpThing);
*/



// Generate the docpage and its nav
//var docpageNavHTML = navTemplate(docpageSchema);
//var docpageHTML = docpageTemplate(docpageSchema);

//console.log(docpageSchema);


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

// Generates an index of all the datatypes and enums on the docpage,
// along with their IDs. This enables cross-linking.
/*function indexTypes(apiDefinition) {
  var typeIndex = {};
  var datatypes = apiDefinition.datatypes;
  for (var i = 0; i < datatypes.length; i++) {
    typeIndex[datatypes[i].id] = {
      'target': 'datatype-' + datatypes[i].name.toLowerCase().replace(/ /g,'').replace(/\./g, ''),
      'name': datatypes[i].name
    };
  }
  var enums = apiDefinition.enums;
  for (var i = 0; i < enums.length; i++) {
    typeIndex[enums[i].id] = {
      'target': 'enum-' + enums[i].name.toLowerCase().replace(/ /g,'').replace(/\./g, ''),
      'name': enums[i].name
  	};
  }
  return typeIndex;
}*/

// Dynamically generates links to datatypes/enums that are listed as fields in other
// datatypes.
/*
handlebars.registerHelper('processType', function(options) {
  var typeString = options.fn(this);

  // First, convert simple proto types to simple js types
  if (typeString == "int32" || typeString == "int64") {
  	return "number";
  } else if (typeString == "bool"){
  	return "boolean";
  } else if (typeString == "string") {
  	return "string";
  } else if (typeString.indexOf("map&lt;") !== -1) {
    return "object";
  } else {

  	// Okay, we have a custom type. First, find all of the types in the index that
  	// the field might be referring to

    // Namespace the type to prevent collisions with symbols with the same suffix
    typeString = '.' + typeString;

  	var possibleTypes = [];
  	for (var key in typeIndex) {
      if (key.indexOf(typeString, key.length - typeString.length) !== -1) {
      	possibleTypes.push(key);
      }
  	}

  	// No matches. Add a warning.
  	if (possibleTypes.length == 0) {
  	  warnings.push('No matches found for symbol ' + typeString + ', cannot cross-link');
  	  return typeString;

  	// Exactly one match. Perfect. Link to that type.
  	} else if (possibleTypes.length == 1) {
  	  var type = typeIndex[possibleTypes[0]];
  	  return '<a href="#' + type.target + '">' + type.name + '</a>';

  	// Multiple types matched. Do a little more work to see if we can narrow down to one.
  	} else {
      warnings.push('Multiple matches found for symbol ' + typeString + ', cannot cross-link\n' +
                      'Possible values:');
      for (var i = 0; i < possibleTypes.length; i++) {
        warnings.push(possibleTypes[i]);
      }
      return typeString;
  	}
  }
});
*/
