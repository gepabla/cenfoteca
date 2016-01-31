'use strict';

angular.module('myApp.tipoUsuario', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/tipoUsuarios', {
    templateUrl: 'resources/tipoUsuario/tipo-usuario.html',
    controller: 'TipoUsuarioController'
  });
}])

.controller('TipoUsuarioController', ['$scope','$http', function($scope,$http) {
	
	$scope.onError = false;
	$scope.tipoUsuarioList = []; //Lista de tipo usuarios
	$scope.requestObject = {};
	
//	$scope.init = function() {
//		$http.get('rest/protected/tipoUsuario/getAll')
//		.success(function(response) {
//			$scope.tipoUsuarioList = response.tipoUsuarioList;
//		});
//	};
//	
//	$scope.init();
	
    $scope.saveTipoUsuario = function(event){
    	
    	var data = {};
    	
    	//Objeto JSON que lleva solo el tipo
    	data = {
    			tipo : $scope.requestObject.tipo
    	};
    	
    	$http.post('rest/protected/tipoUsuario/create', data)
    	.success(function(data, status, config) {
            $scope.message = data;
          }).error(function(data, status, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
          }); 
    };
	
}]);