'use strict';

var app = angular.module('ngdemo');

angular.module('ngdemo.ctrlEntidade', [])
    .controller('ControllerEntidade', ControllerEntidade)

ControllerEntidade.$inject = ['$scope', 'Restangular'];

function ControllerEntidade($scope, Restangular) {
    
    init();
    atualizaLista();

    function init(){
        $scope.paginas = 5;
        setarLinhas($scope.paginas); 
    };
      
    function setarLinhas(num){
        if (num == 'Todas'){
            num = $scope.entidades.length;
        }
        $scope.entryLimit = num;
    };
     
    $scope.ordenarPor = function(coluna) {
        $scope.criterioDeOrdenacao = coluna;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };

    $scope.setItemsPagina = function(num) {
        setarLinhas(num);       
    };
    
   function atualizaLista(){
       $scope.entidades = Restangular.all("entidades").getList().$object;
   }; 
     
   $scope.createNewEntidade = function () {             
        Restangular.all('entidades').post($scope.entidade).then(function(){
            atualizaLista();
            $scope.entidade = null;
        });
   };
   
   $scope.deleteEntidade = function (entidadeId) {
        Restangular.one('entidades', entidadeId).remove().then(function(){
           atualizaLista();
        });       
    };
    
   $scope.updateEntidade = function () {  
        var copiaItem = Restangular.copy($scope.entidade);
        $scope.entidade = copiaItem.put().then(function(){
            $scope.entidade = null;
            atualizaLista(); 
        });
    };
    
   $scope.carregaEntidade = function (entidadeId) {
        $scope.entidade = Restangular.one("entidades", entidadeId).get().$object;
   };
} 
