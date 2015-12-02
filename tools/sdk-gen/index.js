var handlebars = require('handlebars');
var fs = require('fs');
var stringFormat = require("string-template");


// Load in the API definitions and index them
var apiDefinition = JSON.parse(fs.readFileSync('api.json', 'utf-8'));

// Load in the various language SDK templates
var phpTemplate = handlebars.compile(fs.readFileSync('squareconnect-template.php', 'utf-8'));
var rubyTemplate = handlebars.compile(fs.readFileSync('squareconnect-template.rb', 'utf-8'));

var warnings = [];
var protoBaseTypes = ['double', 'float', 'int32', 'int64', 'uint32', 'uint64',
                      'sint32', 'sint64', 'fixed32', 'fixed64', 'sfixed32',
                      'sfixed64', 'bool', 'string', 'bytes'];

// Helper methods for Handlebars

// Removes whitespace and periods in addition to making lower case
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

// Backslashes don't play nice in templates. This mitigates that.
handlebars.registerHelper('backslash', function(options) {
  return '\\';
});

handlebars.registerHelper('populateRequestObject', function(options) {
  var inputType = options.fn(this);
  return populateRequest(inputType);
});

handlebars.registerHelper('populateRequestObjectRuby', function(options) {
  var inputType = options.fn(this);
  return populateRequestRuby(inputType);
});


function populateRequestRuby(inputType) {
  var typeName = '.' + inputType;
  var datatype = findDatatype(typeName);
  if (datatype == null) {
    console.log("ERROR, couldn't find an object to match input type " + inputType);
    return '';
  }

  var codeLines = [];

  var className = getSDKClassName(datatype.id, 'ruby');

  // Instantiate the request type
  //codeLines.push('$request = new \\squareup\\connect\\v3\\actions\\' + datatype.name + '();');
  codeLines.push('request = ' + className + '.new()');

  // Generate field assignment lines for all fields in the request type
  for (var i = 0; i < datatype.fields.length; i++) {
    codeLines.push(generateFieldAssignmentRuby(datatype.fields[i], []));
  }

  return generateSDKString(codeLines);
}

// Generates PHP code that builds and populates a request object based on
// input parameterss
function populateRequest(inputType) {
  var typeName = '.' + inputType;
  var datatype = findDatatype(typeName);
  if (datatype == null) {
    console.log("ERROR, couldn't find an object to match input type " + inputType);
    return '';
  }

  var codeLines = [];

  var className = getSDKClassName(datatype.id, 'php');

  // Instantiate the request type
  //codeLines.push('$request = new \\squareup\\connect\\v3\\actions\\' + datatype.name + '();');
  codeLines.push('$request = new ' + className + '();');

  // Generate field assignment lines for all fields in the request type
  for (var i = 0; i < datatype.fields.length; i++) {
    codeLines.push(generateFieldAssignment(datatype.fields[i], []));
  }

  return generateSDKString(codeLines);
}

// Based on a datatype's full name in the proto file, generate the
// corresponding SDK class name for the appropriate language.
// Each language's naming conventions differ slightly.
function getSDKClassName(entityId, language) {
  if (language == 'php') {
    return '\\' + entityId.replace(/\./g, "\\");
  } else if (language == 'ruby') {
    var tempstring = '::' + entityId.replace(/\./g, "::");
    tempstring = tempstring.replace(/::[a-z]/g, function(txt) {
      return '::' + txt.charAt(2).toUpperCase();
    });
    return tempstring.substr(2);
  } else {
    return '';
  }
}

function generateFieldAssignmentRuby(field, pathComponents) {
  var codeLines = [];
  var objectPath = generateObjectPath(pathComponents, 'ruby');
  var parameterPath = generateObjectPath(pathComponents, 'ruby-array');

  if (protoBaseTypes.indexOf(field.type) > -1 || isEnum(field.type)) {
    return stringFormat("{0}.{1} = self.checkValue('{2}', {3}['{4}'], {5})", [
      objectPath, field.name, field.name, parameterPath, field.name, field.required
    ]);
  } else {
    var datatype = findDatatype(field.type);
    if (datatype == null) {
      console.log("ERROR, couldn't find an object to match input type " + field.type);
      return null;
    }
    var className = getSDKClassName(datatype.id, 'ruby');

    // Make sure a value for the object was provided
    codeLines.push(stringFormat("if !{0}['{1}'].nil?", [parameterPath, field.name]));

    // If it was, instantiate the corresponding proto wrapper class
    codeLines.push(stringFormat("  {0}.{1} = {2}.new();", [objectPath, field.name, className]));

    // Loop through the object's fields and assign them
    for (var i = 0; i < datatype.fields.length; i++) {
      var extendedPath = pathComponents.slice();
      extendedPath.push(field.name);
      codeLines.push('  ' + generateFieldAssignmentRuby(datatype.fields[i], extendedPath));
    }
  

    // Throw in an exception if it's a required field and it *wasn't* provided.
    if (field.required) {
      codeLines.push('else');
      codeLines.push("  raise \"Missing required field \" + field.name");
    }
    codeLines.push('end');

    return generateSDKString(codeLines);
  }
}

function generateFieldAssignment(field, pathComponents) {
  var codeLines = [];
  var objectPath = generateObjectPath(pathComponents, 'php');
  var parameterPath = generateObjectPath(pathComponents, 'php-array');

  // If this field is an int or string or whatever, just do a generic assignment.
  // Same if it's an array of *anything*.
  // Also need some logic here to check if the field is there, and if it's a
  // required one, to fail if it's not
  if (protoBaseTypes.indexOf(field.type) > -1 || isEnum(field.type)) {

    return stringFormat("{0}->{1} = self::checkValue('{2}', {3}['{4}'], {5});", [
      objectPath, field.name, field.name, parameterPath, field.name, field.required
    ]);

  // Otherwise it's an enum or SDK-defined class, so there's more work to do.
} else {
    var datatype = findDatatype(field.type);
    if (datatype == null) {
      console.log("ERROR, couldn't find an object to match input type " + field.type);
      return null;
    }
    var className = getSDKClassName(datatype.id, 'php');

    // Make sure a value for the object was provided
    codeLines.push(stringFormat("if ({0}['{1}']) {", [parameterPath, field.name]));

    // If it was, instantiate the corresponding proto wrapper class
    codeLines.push(stringFormat("  {0}->{1} = new {2}();", [objectPath, field.name, className]));

    // Loop through the object's fields and assign them
    for (var i = 0; i < datatype.fields.length; i++) {
      var extendedPath = pathComponents.slice();
      extendedPath.push(field.name);
      codeLines.push('  ' + generateFieldAssignment(datatype.fields[i], extendedPath));
    }
    codeLines.push('}');

    // Throw in an exception if it's a required field and it *wasn't* provided.
    if (field.required) {
      codeLines.push('else {');
      codeLines.push("  throw new \\Exception('Missing required field " + field.name + "');");
      codeLines.push('}')
    }

    return generateSDKString(codeLines);
  }
}

// Turn an array of strings into a single string with newlines
function generateSDKString(codeLines) {
  var generatedString = '';
  for (var i = 0; i < codeLines.length; i++) {
    if (codeLines[i] !== '') {
      generatedString = generatedString + codeLines[i] + '\n';
    }
  }
  return generatedString;
}

function isEnum(symbolName) {
  var datatypes = apiDefinition.enums;
  var matchingEnums = [];

  // Find the request type in the API definition
  for (var i = 0; i < enums.length; i++) {
    if (enums[i].id.indexOf(symbolName, enums[i].id.length - symbolName.length) !== -1) {
      matchingEnums.push(enums[i]);
    }
  }
  if (matchingEnums.length == 1) {
    return true;

    // Either there are no matches or multiple matches. Bad news either way.
  } else {
    return false;
  }
}

// Builds paths for assignment of nested parameters
function generateObjectPath(pathComponents, language) {
  if (language == 'php') {
    var path = '$request';
    for (var i = 0; i < pathComponents.length; i++) {
      path = path + '->' + pathComponents[i];
    }
    return path;
  } else if (language == 'php-array') {
    var path = '$requestArray';
    for (var i = 0; i < pathComponents.length; i++) {
      path = path + "['" + pathComponents[i] + "']";
    }
    return path;
  } else if (language == 'ruby') {
    var path = 'request';
    for (var i = 0; i < pathComponents.length; i++) {
      path = path + '.' + pathComponents[i];
    }
    return path;
  } else if (language == 'ruby-array') {
    var path = 'requestHash';
    for (var i = 0; i < pathComponents.length; i++) {
      path = path + "['" + pathComponents[i] + "']";
    }
    return path;
  }

  {
    return '';
  }
}

// Fetches all of the information for a particular datatype in the API by its name.
function findDatatype(datatypeName) {
  var datatypes = apiDefinition.datatypes;
  var matchingDatatypes = [];

  // Find the request type in the API definition
  for (var i = 0; i < datatypes.length; i++) {
    if (datatypes[i].id.indexOf(datatypeName, datatypes[i].id.length - datatypeName.length) !== -1) {
      matchingDatatypes.push(datatypes[i]);
    }
  }
  if (matchingDatatypes.length == 1) {
    return matchingDatatypes[0];

    // Either there are no matches or multiple matches. Bad news either way.
  } else {
    return null;
  }
}

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
fs.writeFileSync(homeDirectory + '/Development/connect-sdks/src/rubygem/lib/square_connect.rb', rubyCore);

console.log("All done.");


// Log warnings encountered during processing.
/*console.log("WARNINGS:");
for (var i = 0; i < warnings.length; i++) {
  console.log(warnings[i]);
}*/

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

// Generates an index of all the datatypes and enums on the docpage,
// along with their IDs. This enables cross-linking.
function indexTypes(apiDefinition) {
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
}

function processType(type) {
  var typeString = type;

  // First, convert simple proto types to simple js types
  if (typeString == "int32" || typeString == "int64") {
    return "number";
  } else if (typeString == "bool"){
    return "boolean";
  } else if (typeString == "string") {
    return "string";
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

    // Multiple types matched.
    } else {
      warnings.push('Multiple matches found for symbol ' + typeString + ', cannot cross-link\n' +
                      'Possible values:');
      for (var i = 0; i < possibleTypes.length; i++) {
        warnings.push(possibleTypes[i]);
      }
      return typeString;
    }
  }
}
