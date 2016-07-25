"use strict";

const fs = require('fs');
const path = require('path');

// For compatibility between Node >= 6.3 and < 6.3.
if (!fs.constants) {
  fs.constants = { R_OK: fs.R_OK };
}

// `languages` should be a list of lower-case supported SDK languages. It
// should match the enums used in the Java-based api-parser.
// When `developmentMode` is true, `readFile` will return `null` for nonexistent
// samples so that the developer can easily iterate on adding samples for a
// particular language. It should be set `false` otherwise to prevent deploying
// incomplete samples to production.
function SdkSampleLoader(sampleBasePath, languages, developmentMode) {
  try {
    fs.accessSync(sampleBasePath, fs.constants.R_OK);
  } catch (err) {
    console.error(`Unable to access directory ${sampleBasePath}`);
    throw err;
  }

  this.basePath = sampleBasePath;
  this.languages = languages;
  this.developmentMode = developmentMode;
  if (developmentMode) {
    console.error("[WARN] Development mode is enabled. Missing sample code " +
        "files will only output warnings.");
  }
}

// endpoint example: Charge
// objectType example: ChargeRequest
SdkSampleLoader.prototype.readFile = function(endpoint, objectType, language) {
  let fullPath = path.join(this.basePath, endpoint, `${objectType}.${language}`);

  if (this.developmentMode) {
    try {
      return fs.readFileSync(fullPath, 'utf-8');
    } catch (err) {
      console.error(err);
      return null;
    }
  } else {
    return fs.readFileSync(fullPath, 'utf-8');
  }
};

SdkSampleLoader.prototype.readSamples = function(endpoint, objectType) {
  let samplesByLanguage = {};
  this.languages.forEach((language) => {
    samplesByLanguage[language] = this.readFile(endpoint, objectType, language);
  });
  return samplesByLanguage;
};

module.exports = SdkSampleLoader;
