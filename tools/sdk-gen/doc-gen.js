var handlebars = require('handlebars');
var markdown = require('markdown').markdown;
var fs = require('fs');
var marked = require('marked');
var cheerio = require('cheerio');
var argv = require('minimist')(process.argv.slice(2));

// Load in the API definitions and index them
var apiDefinition = JSON.parse(fs.readFileSync('api.json', 'utf-8'));
var enumValueDescriptions = JSON.parse(fs.readFileSync('enum_mapping.json', 'utf-8'));
var apiChangelog = JSON.parse(fs.readFileSync('changelog.json', 'utf-8'));

// Load in HTML templates for doc types
var docpageTemplate = handlebars.compile(fs.readFileSync('doc-templates/docpage-template.html', 'utf-8'),{preventIndent: true});
var endpointTemplate = handlebars.compile(fs.readFileSync('doc-templates/endpoint-template.html', 'utf-8'));
var navTemplate = handlebars.compile(fs.readFileSync('doc-templates/nav-template.html', 'utf-8'));
var datatypeTemplate = handlebars.compile(fs.readFileSync('doc-templates/datatype-template.html', 'utf-8'));
var enumTemplate = handlebars.compile(fs.readFileSync('doc-templates/enum-template.html', 'utf-8'));
var changelogTemplate = handlebars.compile(fs.readFileSync('doc-templates/changelog-template.html', 'utf-8'));

handlebars.registerPartial('endpoint', endpointTemplate);
handlebars.registerPartial('nav', navTemplate);
handlebars.registerPartial('datatype', datatypeTemplate);
handlebars.registerPartial('enum', enumTemplate);
handlebars.registerPartial('changelog', changelogTemplate);

var apiName = '';
if (argv['name'] == undefined) {
  apiName = apiDefinition.info.title;
} else {
  apiName = argv['name'];
}

apiChangelog.title = apiName;

var warnings = [];

// Helper methods for Handlebars

// Removes whitespace in addition to making lower case
handlebars.registerHelper('idify', function(options) {
  return options.fn(this).toLowerCase().replace(/ /g,'').replace(/\./g, '');
});

handlebars.registerHelper('convertMarkdown', function(options) {
  return markdown.toHTML(options.fn(this));
});

// Adds a [] to the end of a parameter definition if it's an array
handlebars.registerHelper('checkArray', function(options) {
  if (options.fn(this) == 'true') {
  	return '[]';
  } else {
  	return '';
  }
});

handlebars.registerHelper('checkRequired', function(options) {
  if (options.fn(this) == 'true') {
    return '<br><strong>(required)</strong>';
  } else {
    return '';
  }
});

// Dynamically generates links to datatypes/enums that are listed as fields in other
// datatypes.
handlebars.registerHelper('processType', function(options) {
  var typeString = options.fn(this);

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

handlebars.registerHelper('linkToType', function(options) {
  var fullTypePath = options.fn(this);
  var typePathComponents = fullTypePath.split('/');
  var typeName = typePathComponents[typePathComponents.length - 1];
  return '<a href="#type-' + typeName.toLowerCase() + '">' + typeName + '</a>';
});

handlebars.registerHelper('capitalize', function(options) {
  return options.fn(this).toUpperCase();
});


var docpage = {};

// Convert the API Conventions article from Markdown to HTML
docpage.apiconventions = marked(fs.readFileSync('api-conventions.md', 'utf-8'));
docpage.title = apiName;
var $ = cheerio.load(docpage.apiconventions);

// Scan the headers of the API Conventions article to add them to the left nav
var h1s = [];

$('h1, h2, h3').each(function(i, elem) {
  if ($(this).is('h1')) {
    var h1 = {};
    h1.name = $(this).contents().text();
    h1.id = $(this).attr('id').replace(/-/g, '');
    $(this).attr('id', h1.id);
    $(this).before('<div name="' + h1.id + '" data-unique="' + h1.id + '"></div>');
    h1.subheaders = [];
    h1s.push(h1);
  } else if ($(this).is('h2')) {
    var h2 = {};
    h2.name = $(this).contents().text();
    h2.id = $(this).attr('id').replace(/-/g, '');
    $(this).attr('id', h2.id);
    $(this).before('<div name="' + h2.id + '" data-unique="' + h2.id + '"></div>');
    h2.subheaders = [];
    h1s[h1s.length - 1].subheaders.push(h2);
  } else if ($(this).is('h3')) {
    var h3 = {};
    h3.name = $(this).contents().text();
    h3.id = $(this).attr('id').replace(/-/g, '');
    $(this).attr('id', h3.id);
    $(this).before('<div name="' + h3.id + '" data-unique="' + h3.id + '"></div>');
    var subheaderArrayLength = h1s[h1s.length - 1].subheaders.length;
    h1s[h1s.length - 1].subheaders[subheaderArrayLength - 1].subheaders.push(h3);
  }
});

docpage.headers = h1s;
docpage.apiconventions = $.html();
docpage.changelog = apiChangelog;

// Do datatypes and enums first so endpoints can look up their request and response types
var types = apiDefinition.definitions;

var enums = [];
var datatypes = [];

for (var typeName in types) {
  if (types.hasOwnProperty(typeName)) {
    var definition = types[typeName];
    if (definition.hasOwnProperty('required')) {
      for (var propertyName in definition.properties) {
        if (definition.required.indexOf(propertyName) > -1) {
          definition.properties[propertyName].required = true;
        }
      }
    }
    var typeWrapper = {};
    typeWrapper.name = typeName;
    typeWrapper.details = definition;
    if (definition.hasOwnProperty('enum')) {
      var valueDescriptions = [];
      typeWrapper.values = [];
      for (var enumValue in enumValueDescriptions) {
        if (enumValueDescriptions.hasOwnProperty(enumValue)) {
          if (enumValue.indexOf(typeWrapper.name + ".") == 0) {
            valueDescriptions.push(enumValueDescriptions[enumValue]);
          }
        }
      }
      for (var i = 0; i < typeWrapper.details.enum.length; i++) {
        var enumInfo = {};
        enumInfo.name = typeWrapper.details.enum[i];
        enumInfo.description = valueDescriptions[i];
        typeWrapper.values.push(enumInfo);
      }
      enums.push(typeWrapper);
    } else {
      for (var propertyName in typeWrapper.details.properties) {
        if (typeWrapper.details.properties.hasOwnProperty(propertyName)) {
          if (typeWrapper.details.properties[propertyName].type == 'array') {
            if(typeWrapper.details.properties[propertyName].items.hasOwnProperty('type')) {
              typeWrapper.details.properties[propertyName].isbasearray = true;
            } else {
              typeWrapper.details.properties[propertyName].isobjectarray = true;
            }
          }
        }
      }
      datatypes.push(typeWrapper);
    }

    // Sort the datatypes and enums alphabetically
    enums.sort(function(a, b){
      return a.name.localeCompare(b.name);
    });
    datatypes.sort(function(a, b){
      return a.name.localeCompare(b.name);
    })

  }
}

docpage.enums = enums;
docpage.datatypes = datatypes;

// Now do endpoints
var endpoints = [];
var endpointEntities = {};
var paths = apiDefinition.paths;
for (var pathName in paths) {
  if (paths.hasOwnProperty(pathName)) {
    for (var httpmethod in paths[pathName]) {
      if (paths[pathName].hasOwnProperty(httpmethod)) {
        var endpoint = {}
        endpoint.path = pathName;
        endpoint.httpmethod = httpmethod;
        endpoint.details = paths[pathName][httpmethod];

        // Filter endpoint parameters out into different arrays by type for easier template generation
        endpoint.headerparams   = [];
        endpoint.pathparams     = [];
        endpoint.queryparams    = [];
        endpoint.bodyparams     = [];

        var endpointName = endpoint.details.operationId;
        var endpointRequestObjectName = endpointName + 'Request';
        var endpointResponseObjectName = endpointName + 'Response';

        // Extract the endpoint's request and response types and remove them from the type list
        // (So they don't end up documented as data types)
        var endpointRequestObject = getTypeWithName(endpointRequestObjectName, datatypes, true);
        var endpointResponseObject = getTypeWithName(endpointResponseObjectName, datatypes, true);

        if (endpointRequestObject != undefined) {
          if (endpointRequestObject.details.hasOwnProperty('example')) {
            endpoint.requestExample = {
              'httpMethod': endpoint.httpmethod.toUpperCase(),
              'url': endpointRequestObject.details.example.request_url,
            }

            exampleBody = endpointRequestObject.details.example.request_body
            if (exampleBody != undefined) {
              endpoint.requestExample.body = JSON.stringify(exampleBody, null, 2);
            }
          }
        }

        if (endpointResponseObject != undefined) {
          if (endpointResponseObject.details.hasOwnProperty('example')) {
            endpoint.responseExample = JSON.stringify(endpointResponseObject.details.example, null, 2);
          }
          endpoint.responsetype = endpointResponseObject;
        }

        for(var param in endpoint.details.parameters) {
          if (endpoint.details.parameters[param].in == 'header') {
            endpoint.headerparams.push(endpoint.details.parameters[param]);
            endpoint.hasheaderparams = true;
          } else if (endpoint.details.parameters[param].in == 'path') {
            endpoint.pathparams.push(endpoint.details.parameters[param]);
            endpoint.haspathparams = true;
          } else if (endpoint.details.parameters[param].in == 'query') {
            endpoint.queryparams.push(endpoint.details.parameters[param]);
            endpoint.hasqueryparams = true;
          } else if (endpoint.details.parameters[param].in == 'body') {
            var requestType = endpointRequestObject;

            if (requestType != null) {
              var bodyParamObj = requestType.details.properties;
              var bodyParamRequireds = requestType.details.required;
              for (var paramName in bodyParamObj) {
                if (bodyParamObj.hasOwnProperty(paramName)){
                  endpoint.hasbodyparams = true;
                  var bodyParam = {};
                  bodyParam.name = paramName;
                  bodyParam.details = bodyParamObj[paramName];
                  if (bodyParamRequireds != undefined && bodyParamRequireds.indexOf(paramName) >= 0) {
                    bodyParam.required = true;
                  } else {
                    bodyParam.required = false;
                  }
                  endpoint.bodyparams.push(bodyParam);
                }
              }
            }
          }
        }

        var endpointEntity = endpoint.details.tags[0].replace(/\./g, '');
        if (!endpointEntities.hasOwnProperty(endpointEntity)) {
          endpointEntities[endpointEntity] = [];
        }
        endpointEntities[endpointEntity].push(endpoint);

        endpoints.push(endpoint);
      }
    }
  }
}


docpage.endpoints = endpoints;
docpage.endpointEntities = endpointEntities;

console.log(docpageTemplate(docpage));


function getTypeWithName(typeName, typeList, removeFromList) {
  for (var i = 0; i < typeList.length; i++) {
    if (typeList[i].name == typeName) {
      var type = typeList[i];
      if (removeFromList) {
        typeList.splice(i, 1);
      }
      return type;
    }
  }
  return null;
}


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
