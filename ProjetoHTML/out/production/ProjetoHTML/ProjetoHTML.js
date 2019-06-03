if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'ProjetoHTML'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'ProjetoHTML'.");
}
var ProjetoHTML = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function main() {
    println('Ol\xE1 Mundo');
  }
  _.main = main;
  main();
  Kotlin.defineModule('ProjetoHTML', _);
  return _;
}(typeof ProjetoHTML === 'undefined' ? {} : ProjetoHTML, kotlin);
