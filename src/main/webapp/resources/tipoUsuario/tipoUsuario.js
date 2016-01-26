'use strict';

angular.module('myApp.tipoUsuario', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/tipoUsuarios', {
    templateUrl: 'resources/tipoUsuario/tipo-usuario.html',
    controller: 'TipoUsuarioController'
  });
}])

.controller('TipoUsuarioController', ['$scope','$http',function($scope,$http) {
	$scope.tipoUsuarios = [];
	$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
	//$http.post('/rest/protected/tipoUsuario/getAll',$scope.requestObject).success(function(response) {
		//console.log("response",response)
		//$scope.tipoUsuarios = response.tipoUsuarios;
		//console.log("$scope.usuarios",$scope.usuarios)
		
	//});
	
    $scope.saveRent = function(event){
    	
    	if(this.createRentForm.$valid){
    		$scope.onError = false;
    		
    		//$files: an array of files selected, each file has name, size, and type.
    		for ( var i = 0; i < $scope.files.length; i++) {
    			var file = $scope.files[i];
    			$scope.upload = $upload.upload({
    				url : 'rest/protected/rent/create',
    				data : {
    					idTipoAlquiler:$scope.requestObject.idTipoAlquiler,
    					name:$scope.requestObject.name,
    					description:$scope.requestObject.description,
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