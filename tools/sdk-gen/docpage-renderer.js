"use strict";

const _ = require('lodash');

function getTypeWithName(typeName, typeList, removeFromList) {
  for (let i = 0; i < typeList.length; i++) {
    if (typeList[i].name == typeName) {
      let type = typeList[i];
      if (removeFromList) {
        typeList.splice(i, 1);
      }
      return type;
    }
  }
  return null;
}

function DocpageRenderer() {
}

// Render datatypes and enums
DocpageRenderer.prototype.renderDatatypes = function(apiDefinitions, enumValueDescriptions) {
  // So that minimal refactoring is needed for a copy-pasted segment of code
  let types = apiDefinitions;
  let enums = [];
  let datatypes = [];

  _.forOwn(types, (definition, typeName) => {
    if (definition.hasOwnProperty('required')) {
      for (let propertyName in definition.properties) {
        if (definition.required.indexOf(propertyName) > -1) {
          definition.properties[propertyName].required = true;
        }
      }
    }
    let typeWrapper = {};
    typeWrapper.name = typeName;
    typeWrapper.details = definition;
    if (definition.hasOwnProperty('enum')) {
      let valueDescriptions = [];
      typeWrapper.values = [];
      _.forOwn(enumValueDescriptions, function(description, enumValue) {
        if (enumValue.indexOf(typeWrapper.name + ".") == 0) {
          valueDescriptions.push(description);
        }
      });
      for (let i = 0; i < typeWrapper.details.enum.length; i++) {
        let enumInfo = {};
        enumInfo.name = typeWrapper.details.enum[i];
        enumInfo.description = valueDescriptions[i];
        typeWrapper.values.push(enumInfo);
      }
      enums.push(typeWrapper);
    } else {
      _.forOwn(typeWrapper.details.properties, function(property) {
        if (property.type == 'array') {
          if(property.items.hasOwnProperty('type')) {
            property.isbasearray = true;
          } else {
            property.isobjectarray = true;
          }
        }
      });
      datatypes.push(typeWrapper);
    }

    // Sort the datatypes and enums alphabetically
    enums.sort(function(a, b){
      return a.name.localeCompare(b.name);
    });
    datatypes.sort(function(a, b){
      return a.name.localeCompare(b.name);
    })

  });

  return {
    enums: enums,
    datatypes: datatypes
  };
}

DocpageRenderer.prototype.renderEndpoints = function(paths, datatypes) {
  let endpoints = [];
  let endpointEntities = {};
  _.forOwn(paths, (_ignored, pathName) => {
    _.forOwn(paths[pathName], (endpointDetails, httpmethod) => {
      let endpoint = {}
      endpoint.path = pathName;
      endpoint.httpmethod = httpmethod;
      endpoint.details = endpointDetails;

      // Filter endpoint parameters out into different arrays by type for easier template generation
      endpoint.headerparams   = [];
      endpoint.pathparams     = [];
      endpoint.queryparams    = [];
      endpoint.bodyparams     = [];

      let endpointName = endpoint.details.operationId;
      let endpointRequestObjectName = endpointName + 'Request';
      let endpointResponseObjectName = endpointName + 'Response';

      // Extract the endpoint's request and response types and remove them from the type list
      // (So they don't end up documented as data types)
      let endpointRequestObject = getTypeWithName(endpointRequestObjectName, datatypes, true);
      let endpointResponseObject = getTypeWithName(endpointResponseObjectName, datatypes, true);

      if (endpointRequestObject) {
        if (endpointRequestObject.details.hasOwnProperty('example')) {
          endpoint.requestExample = {
            'httpMethod': endpoint.httpmethod.toUpperCase(),
            'url': endpointRequestObject.details.example.request_url,
          }

          let exampleBody = endpointRequestObject.details.example.request_body
            if (exampleBody) {
              endpoint.requestExample.body = JSON.stringify(exampleBody, null, 2);
            }
        }

      }

      if (endpointResponseObject) {
        if (endpointResponseObject.details.hasOwnProperty('example')) {
          endpoint.responseExample = JSON.stringify(endpointResponseObject.details.example, null, 2);
        }
        endpoint.responsetype = endpointResponseObject;
      }

      for(let param in endpoint.details.parameters) {
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
          let requestType = endpointRequestObject;

          if (requestType != null) {
            let bodyParamObj = requestType.details.properties;
            let bodyParamRequireds = requestType.details.required;
            _.forOwn(bodyParamObj, function(bodyParamDetails, paramName) {
              endpoint.hasbodyparams = true;
              let bodyParam = {};
              bodyParam.name = paramName;
              bodyParam.details = bodyParamDetails;
              if (bodyParamRequireds && bodyParamRequireds.indexOf(paramName) >= 0) {
                bodyParam.required = true;
              } else {
                bodyParam.required = false;
              }
              endpoint.bodyparams.push(bodyParam);
            });
          }
        }
      }

      let endpointEntity = endpoint.details.tags[0].replace(/\./g, '');
      if (!endpointEntities.hasOwnProperty(endpointEntity)) {
        endpointEntities[endpointEntity] = [];
      }
      endpointEntities[endpointEntity].push(endpoint);

      endpoints.push(endpoint);
    });
  });

  return {
    endpoints: endpoints,
    endpointEntities: endpointEntities
  };
}

DocpageRenderer.prototype.render = function(definitions, enumDescriptions, paths) {
  let docpage = this.renderDatatypes(definitions, enumDescriptions);
  docpage = Object.assign(docpage, this.renderEndpoints(paths, docpage.datatypes));
  return docpage;
};

module.exports = DocpageRenderer;
