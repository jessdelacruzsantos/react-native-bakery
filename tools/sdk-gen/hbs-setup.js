"use strict";

const fs = require('fs');

const handlebars = require('handlebars');
const markdown = require('markdown').markdown;

function hbsCompile(filename, additionalOpts) {
  return handlebars.compile(fs.readFileSync(filename, 'utf-8'), additionalOpts);
}

function registerHelpers() {
  // Removes whitespace in addition to making lower case
  handlebars.registerHelper('idify', function(options) {
    return options.fn(this).toLowerCase().replace(/[ \.]/g,'');
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

  handlebars.registerHelper('paramify', function(options) {
    let splitString = options.fn(this).split('.');
    return splitString[splitString.length - 1];
  });

  handlebars.registerHelper('classify', function(options) {
    return options.fn(this).replace('.', '');
  });

  // eslint-disable-next-line no-unused-vars
  handlebars.registerHelper('backslash', function(options) {
    return '\\';
  });

  handlebars.registerHelper('linkToType', function(options) {
    let fullTypePath = options.fn(this);
    let typePathComponents = fullTypePath.split('/');
    let typeName = typePathComponents[typePathComponents.length - 1];
    return '<a href="#type-' + typeName.toLowerCase() + '">' + typeName + '</a>';
  });

  handlebars.registerHelper('capitalize', function(options) {
    return options.fn(this).toUpperCase();
  });
}

/**
 * Setup object:
 * - "apiDefinition": usually api.json
 * - "enumValueDescriptions"
 * - "apiChangelog"
 * - "docpageTemplate"
 * - "endpointTemplate"
 * - "navTemplate"
 * - "datatypeTemplate"
 * - "enumTemplate"
 * - "changelogTemplate"
 */
exports.createDocPageTemplate = function(files) {
  // Load in HTML templates for doc types
  const docpageTemplate = hbsCompile(files['docpageTemplate'], { preventIndent: true });
  const endpointTemplate = hbsCompile(files['endpointTemplate']);
  const navTemplate = hbsCompile(files['navTemplate']);
  const datatypeTemplate = hbsCompile(files['datatypeTemplate']);
  const enumTemplate = hbsCompile(files['enumTemplate']);
  const changelogTemplate = hbsCompile(files['changelogTemplate']);

  handlebars.registerPartial('endpoint', endpointTemplate);
  handlebars.registerPartial('nav', navTemplate);
  handlebars.registerPartial('datatype', datatypeTemplate);
  handlebars.registerPartial('enum', enumTemplate);
  handlebars.registerPartial('changelog', changelogTemplate);

  registerHelpers();

  return docpageTemplate;
};
