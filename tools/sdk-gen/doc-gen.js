"use strict";

const fs = require('fs');
const marked = require('marked');
const cheerio = require('cheerio');
const argv = require('minimist')(process.argv.slice(2));

const helpers = require('./helpers');

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

// Load in the API definitions and index them
const apiDefinition = readJson(files['apiDefinition']);
const enumValueDescriptions = readJson(files['enumValueDescriptions']);
const apiChangelog = readJson(files['apiChangelog']);

let apiName = argv['name'];
if (!apiName) {
  apiName = apiDefinition.info.title;
}
apiChangelog.title = apiName;

// Set up all the handlebars templates for the docpages
const docpageTemplate = require('./hbs-setup').createDocPageTemplate(files);

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
    require('./docpage-renderer').render(
      apiDefinition.definitions,
      enumValueDescriptions,
      apiDefinition.paths));

const warnings = require('./docpage-validator').validate(docpage);

console.log(docpageTemplate(docpage));

if (warnings.length) {
  console.error("WARNINGS");
  warnings.forEach(function(warning) {
    console.error("* " + warning);
  });
} else {
  console.error("Success!");
}
