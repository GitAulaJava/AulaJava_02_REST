'use strict';

var app = angular.module('ngdemo');

angular.module('ngdemo.ctrlUsuario', [])
        .controller('ControllerUsuario', ControllerUsuario)

ControllerUsuario.$inject = ['$scope', 'Restangular'];

function ControllerUsuario($scope, Restangular) {

    init();
    atualizaLista();

    function init() {
        $scope.paginas = 5;
        setarLinhas($scope.paginas);
    };

    function setarLinhas(num) {
        if (num == 'Todas') {
            num = $scope.users.length;
        };
        $scope.entryLimit = num;
    };

    $scope.ordenarPor = function (coluna) {
        $scope.criterioDeOrdenacao = coluna;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };

    $scope.setItemsPagina = function (num) {
        setarLinhas(num);
    };

    function atualizaLista() {
        Restangular.all("usuarios").getList().then(function (objeto) {
            $scope.users = objeto;
            setarLinhas($scope.paginas);
        });
        $scope.cargos = Restangular.all("cargos").getList().$object;
    };

    $scope.createNewUser = function () {
        Restangular.all('usuarios').post($scope.user).then(function () {
            atualizaLista();
            $scope.user = null;
        });
    };

    $scope.deleteUser = function (userId) {
        Restangular.one('usuarios', userId).remove().then(function () {
            atualizaLista();
        });
    };

    $scope.updateUser = function () {
        var copiaItem = Restangular.copy($scope.user);
        $scope.user = copiaItem.put().then(function () {
            $scope.user = null;
            atualizaLista();
        });
    };

    $scope.carregaUser = function (userId) {
        $scope.user = Restangular.one("usuarios", userId).get().$object;
    };
}
