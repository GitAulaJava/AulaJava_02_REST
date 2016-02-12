'use strict';
angular.module('ngdemo.rodapeDirective', [])
    .directive('rodape', rodape);

function rodape(){
  return {
    restrict:'E',
    templateUrl:'partials/rodape.html'
  };
}
