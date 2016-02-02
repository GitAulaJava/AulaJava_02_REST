'use strict';

/**
 * @ngdoc directive
  * @description
  * Directive of the webCobrancasApp
 */

angular.module('ngdemo.rodapeDirective', [])
    .directive('rodape', rodape);

/**
 * Directive rodape
 */

function rodape(){
  return {
    restrict:'E',
    templateUrl:'partials/rodape.html'
  };
}
