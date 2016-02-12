'use strict';

var app = angular.module('ngdemo');

angular.module('ngdemo.ctrlEntidade', [])
        .controller('ControllerEntidade', ControllerEntidade)

ControllerEntidade.$inject = ['$scope', 'Restangular'];

function ControllerEntidade($scope, Restangular) {

    init();
    atualizaLista();
    var usuarios = [];

    function init() {
        $scope.paginas = 5;
        setarLinhas($scope.paginas);
    }
    ;

    function setarLinhas(num) {
        if (num == 'Todas') {
            num = $scope.entidades.length;
        }
        $scope.entryLimit = num;
    }
    ;

    $scope.ordenarPor = function (coluna) {
        $scope.criterioDeOrdenacao = coluna;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };

    $scope.setItemsPagina = function (num) {
        setarLinhas(num);
    };

    function atualizaLista() {
        Restangular.all("entidades").getList().then(function (objeto) {
            $scope.entidades = objeto;
            setarLinhas($scope.paginas);
        });
        $scope.listaUsuarios = Restangular.all("usuarios").getList().$object;
    }
    ;

    $scope.createNewEntidade = function () {
        Restangular.all('entidades').post($scope.entidade).then(function () {
            atualizaLista();
            $scope.entidade = null;
        });
    };

    $scope.deleteEntidade = function (entidadeId) {
        Restangular.one('entidades', entidadeId).remove().then(function () {
            atualizaLista();
        });
    };

    $scope.updateEntidade = function () {
        $scope.entidade.usuarios = $scope.usuarios;
        console.log($scope.entidade);
        var copiaItem = Restangular.copy($scope.entidade);
        $scope.entidade = copiaItem.put().then(function () {
            $scope.entidade = null;
            atualizaLista();
        });
    };

    $scope.carregaEntidade = function (entidadeId) {
        Restangular.one("entidades", entidadeId).get().then(function (objeto) {
            $scope.entidade = objeto;
            $scope.usuarios = $scope.entidade.usuarios;
        });
    };

    $scope.adicionaItem = function () {
        var user;
        Restangular.one("usuarios/" + $scope.id_usuario).get().then(function (objeto) {
            user = objeto.plain(); 
            //usuarios.push(user);
            $scope.usuarios.push(user);// = usuarios;
        });
    };
    
    $scope.retiraUsuario = function(usuario) {  
        
        console.log($scope.usuarios.indexOf(usuario));
       $scope.usuarios.splice($scope.usuarios.indexOf(usuario), 1   );
    };
    
    $scope.limpaVariaveis = function () {
        $scope.entidade = null;
    };
}
