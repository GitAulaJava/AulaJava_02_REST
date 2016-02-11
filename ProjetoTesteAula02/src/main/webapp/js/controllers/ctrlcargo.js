'use strict';

var app = angular.module('ngdemo');

angular.module('ngdemo.ctrlCargo', [])
    .controller('ControllerCargo', ControllerCargo)

ControllerCargo.$inject = ['$scope', 'Restangular'];

function ControllerCargo($scope, Restangular) {
    
    init();
    atualizaLista();

    function init(){
        $scope.paginas = 5;
        setarLinhas($scope.paginas); 
    };
      
    function setarLinhas(num){
        if (num == 'Todas'){
            num = $scope.cargos.length;
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
        Restangular.all("cargos").getList().then(function (objeto) {
            $scope.cargos = objeto;
            setarLinhas($scope.paginas);
        });
   };

   $scope.createNewCargo = function () {             
        Restangular.all('cargos').post($scope.cargo).then(function(){
            atualizaLista();
            $scope.cargo = null;
        });
   };
   
   $scope.deleteCargo = function (cargoId) {
        Restangular.one('cargos', cargoId).remove().then(function(){
           atualizaLista();
        });       
    };
    
   $scope.updateCargo = function () {  
        var copiaItem = Restangular.copy($scope.cargo);
        $scope.cargo = copiaItem.put().then(function(){
            $scope.cargo = null;
            atualizaLista(); 
        });
    };
    
   $scope.carregaCargo = function (cargoId) {
        $scope.cargo = Restangular.one("cargos",cargoId).get().$object;
   };
}
