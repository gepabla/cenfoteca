'use strict';

angular.module('myApp.tipoUsuario', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/tipoUsuarios', {
    templateUrl: 'resources/tipoUsuario/tipo-usuario.html',
    controller: 'TipoUsuarioController'
  });
}])

.controller('TipoUsuarioController', ['$scope','$http', '$location', '$upload',function($scope,$http,$location,$upload) {
	
	$scope.files = {};
	$scope.onError = false;
	$scope.tipoUsuarioList = [];
	$scope.requestObject = {};
	
	$scope.init = function() {
		$http.get('rest/protected/tipoUsuario/getAll')
		.success(function(response) {
			$scope.tipoUsuarioList = response.tipoUsuarioList;
			$scope.requestObject.idTipoUsuario = $scope.tipoUsuarioList[0].idTipoUsuario;
			
		});
	};
	
	$scope.init();
	
	$scope.onFileSelect = function($files) {
    	$scope.files = $files;
    };
	
    $scope.saveTipoUsuario = function(event){
    	
    	if(this.createTipoUsuarioForm.$valid){
    		$scope.onError = false;
    		
    		//$files: an array of files selected, each file has name, size, and type.
    		for ( var i = 0; i < $scope.files.length; i++) {
    			var file = $scope.files[i];
    			$scope.upload = $upload.upload({
    				url : 'rest/protected/tipoUsuario/create',
    				data : {
    					idTipoUsuario:$scope.requestObject.idTipoUsuario,
    					tipo:$scope.requestObject.tipo
    				},
    				file : file,
    			}).progress(
					function(evt) {
						console.log('percent: '+ parseInt(100.0 * evt.loaded / evt.total));
					}).success(function(data, status, headers, config) {
						// Rent is uploaded successfully
						console.log(data);
					});
	    			//.error(...)
	    			//.then(success, error, progress); 
    		}
    	}else{
    		$scope.onError = true;
    	}
    };
	
}]);