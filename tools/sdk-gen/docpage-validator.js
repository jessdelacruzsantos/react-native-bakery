"use strict";

function validateEndpoint(endpoint) {
  let warnings = [];

  // If `endpoint.whatever` is undefined, null, or empty string...

  if (!endpoint.httpmethod) {
    warnings.push("Endpoint " + endpoint.id + " missing `httpmethod`");
  }

  if (!endpoint.path) {
    warnings.push("Endpoint " + endpoint.id + " missing `path`");
  }

  if (!endpoint.entity) {
    warnings.push("Endpoint " + endpoint.id + " missing `entity`");
  }

  if (!endpoint.action) {
    warnings.push("Endpoint " + endpoint.id + " missing `action`");
  }

  if (!endpoint.description) {
    warnings.push("Endpoint " + endpoint.id + " missing `description`");
  }

  return warnings;
}

function validateDatatypes(datatypes) {
  let warnings = [];

  for (let i = 0; i < datatypes.length; i++) {
    let datatype = datatypes[i];

    if (!datatype.description) {
      warnings.push("Datatype " + datatype.id + " missing `description`");
    }
  }

  return warnings;
}

function validateEnums(enums) {
  let warnings = [];

  for (let i = 0; i < enums.length; i++) {
    let enumm = enums[i];

    if (!enumm.description) {
      warnings.push("Enum " + enumm.id + " missing `description`");
    }
  }

  return warnings;
}

exports.validate = function(docpage) {
  let warnings = [];

  docpage.endpoints.forEach(function(endpoint) {
    warnings.concat(validateEndpoint(endpoint));
  });

  warnings.concat(validateDatatypes(docpage.datatypes));
  warnings.concat(validateEnums(docpage.enums));
  return warnings;
};
