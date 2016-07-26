"use strict";

const fs = require('fs');
const path = require('path');

const marked = require('marked');
const cheerio = require('cheerio');
const argv = require('minimist')(process.argv.slice(2));

const DocpageRenderer = require('./docpage-renderer');
const docpageValidator = require('./docpage-validator');
const hbsSetup = require('./hbs-setup');
const helpers = require('./helpers');
const SdkSampleLoader = require('./sdk-sample-loader');

function readJson(filename) {
  return JSON.parse(fs.readFileSync(filename, 'utf-8'));
}

const files = {
  apiDefinition: 'api.json',
  enumValueDescriptions: 'enum_mapping.json',
  apiChangelog: 'changelog.json',
  apiConventions: 'api-conventions.md',
  docpageTemplate: 'doc-templates/docpage-template.html',
  endpointTemplate: 'doc-templates/endpoint-template.html',
  navTemplate: 'doc-templates/nav-template.html',
  datatypeTemplate: 'doc-templates/datatype-template.html',
  enumTemplate: 'doc-templates/enum-template.html',
  changelogTemplate: 'doc-templates/changelog-template.html',
};

const sdkLanguages = ['curl', 'php', 'ruby'];

// Load in the API definitions and index them
const apiDefinition = readJson(files['apiDefinition']);
const enumValueDescriptions = readJson(files['enumValueDescriptions']);
const apiChangelog = readJson(files['apiChangelog']);

let apiName = argv['name'];
if (!apiName) {
  apiName = apiDefinition.info.title;
}
apiChangelog.title = apiName;

let pathToSdkSamples = argv['sdk-samples'];
if (!pathToSdkSamples) {
  pathToSdkSamples = path.normalize(path.join(__dirname, '../..', 'sdk_samples'));
  console.error(`--sdk-samples not specified. Defaulting to ${pathToSdkSamples}`);
}

// TODO(dge|2016-07-25): By default, this script will always be run in
// development mode so that we don't break existing docgen scripts.
let developmentMode = true;
if (typeof argv['dev-mode'] === 'boolean') {
  developmentMode = argv['dev-mode'];
}

const sdkSampleLoader = new SdkSampleLoader(
    pathToSdkSamples, sdkLanguages, developmentMode);
const docpageRenderer = new DocpageRenderer(sdkSampleLoader);

// Set up all the handlebars templates for the docpages
const docpageTemplate = hbsSetup.createDocPageTemplate(files);

// Convert the API Conventions article from Markdown to HTML
let docpage = {
  apiconventions: marked(fs.readFileSync(files['apiConventions'], 'utf-8')),
  changelog: apiChangelog,
  title: apiName
};

const $ = cheerio.load(docpage.apiconventions);
// Scan the headers of the API Conventions article to add them to the left nav
const h1s = helpers.scanApiConventionsForH1s($);

docpage.headers = h1s;
docpage.apiconventions = $.html();

// Merge rendered fields into docpage
docpage = Object.assign(docpage,
    docpageRenderer.render(
      apiDefinition.definitions,
      enumValueDescriptions,
      apiDefinition.paths));

const warnings = docpageValidator.validate(docpage);

console.log(docpageTemplate(docpage));

if (warnings.length) {
  console.error("WARNINGS");
  warnings.forEach(function(warning) {
    console.error("* " + warning);
  });
} else {
  console.error("Success!");
}
