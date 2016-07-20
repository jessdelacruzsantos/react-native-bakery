"use strict";

exports.scanApiConventionsForH1s = function($) {
  const h1s = [];

  // This is done as a $('h1, h2, h3') and not as separate $('h1'), $('h2'),
  // $('h3') because this is all order-dependent (e.g. h2 handler uses last entry
  // in `h1s`).
  $('h1, h2, h3').each(function() {
    if ($(this).is('h1')) {
      let h1 = {};
      h1.name = $(this).contents().text();
      h1.id = $(this).attr('id').replace(/-/g, '');
      $(this).attr('id', h1.id);
      $(this).before('<div name="' + h1.id + '" data-unique="' + h1.id + '"></div>');
      h1.subheaders = [];

      h1s.push(h1);
    } else if ($(this).is('h2')) {
      let h2 = {};
      h2.name = $(this).contents().text();
      h2.id = $(this).attr('id').replace(/-/g, '');
      $(this).attr('id', h2.id);
      $(this).before('<div name="' + h2.id + '" data-unique="' + h2.id + '"></div>');
      h2.subheaders = [];

      h1s[h1s.length - 1].subheaders.push(h2);
    } else if ($(this).is('h3')) {
      let h3 = {};
      h3.name = $(this).contents().text();
      h3.id = $(this).attr('id').replace(/-/g, '');
      $(this).attr('id', h3.id);
      $(this).before('<div name="' + h3.id + '" data-unique="' + h3.id + '"></div>');
      let subheaderArrayLength = h1s[h1s.length - 1].subheaders.length;

      h1s[h1s.length - 1].subheaders[subheaderArrayLength - 1].subheaders.push(h3);
    }
  });

  return h1s;
};
