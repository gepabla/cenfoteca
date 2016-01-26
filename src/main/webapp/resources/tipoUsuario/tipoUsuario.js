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
	
	$scope.init = function() {
		$http.get('rest/protected/tipoUsuario/getAll')
		.success(function(response) {
			$scope.tipoUsuarioList = response.tipoUsuarioList;
		});
	};
	
	$scope.init();
	
    $scope.saveTipoUsuario = function(event){
    	
    	var _id = 0;
    	var data = {};
    	//El for es para incrementar el idTipousuario a la hora de enviarlo a la BD
    	for(var i=0; i < $scope.tipoUsuarioList.length; i++) {
    		if(i == $scope.tipoUsuarioList.length - 1) {
    			_id = $scope.tipoUsuarioList[i].idTipoUsuario + 1;
    		}
    	}
    	//Objeto JSON que lleva el idTipoUsuario y el tipo
    	data = {
    			idTipoUsuario : _id,
    			tipo : $scope.requestObject.tipo
    	};
    	
    	$http.post('rest/protected/tipoUsuario/create', data)
    	.success(function(data, status, headers, config) {
            $scope.message = data;
          }).error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
          }); 
    };
	
}]);