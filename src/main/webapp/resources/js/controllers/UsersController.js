'use strict';

/**
 * Users
 * @constructor
 */
var UsersController = function($scope, $http,$location,$modal,$log) {
   
	$scope.requestObject = {};
	$scope.requestObject.pageNumber = 1;
	$scope.requestObject.pageSize = 10;
	$scope.requestObject.direction = "DESC";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "ALL";
	$scope.requestObject.searchTerm = "";
	
    $scope.init = function() {
    	
    };
    
    $scope.init();
    
    var grid_selector = "#usersList";
	var pager_selector = "#usersPager";
	
    jQuery(grid_selector).jqGrid(
	{
		url : 'rest/protected/users/getAll',
		datatype: "json",
		mtype: "POST",
		ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
		ajaxRowOptions: { contentType: "application/json; charset=utf-8", dataType: "json" },
		postData: JSON.stringify($scope.requestObject),
		colNames : [ 'Name', 'Last Name'],
		colModel : [ {
			name : 'firstname'
		}, {
			name : 'lastname',
		}],
		jsonReader : {
	    	root:"usuarios",
	        page:  $scope.requestObject.pageNumber,
	        total: function (obj) {
	        	
	        	$scope.gridError = false;
	        	$scope.$apply();
	        	$scope.totalPages = 0;
	        	
	        	if(obj.errorMessage == null){
	        		$scope.totalPages = obj.totalPages;
	        	}
	        	return $scope.totalPages;
	        },
	        records: function (obj) {
	        	
	        	var totalElements = 0;
	        	if(obj.errorMessage == null){
	        		totalElements = obj.totalElements;
	        	}else{
	        		//notifyWsErrorOnGrid(obj);
	        	}
	        	return totalElements;
	        },
	        repeatitems: false
        },
        onPaging: function(button){
			
			if( !($("#"+button).hasClass("ui-state-disabled")) ){
				
				if(button == "next_grid-pager"){
					$scope.requestObject.pageNumber += 1;
				}else if(button == "last_grid-pager"){
					$scope.requestObject.pageNumber = $scope.totalPages;
				}else if(button == "first_grid-pager"){
					$scope.requestObject.pageNumber = 1;
				}else if(button == "records"){
					var rowNum = $(".ui-pg-selbox").val();
					$scope.requestObject.pageSize = rowNum;
				}else if(button == "user"){
					var rowNum = $(".ui-pg-input").val();
					$scope.requestObject.pageNumber = rowNum;
				}else{
					$scope.requestObject.pageNumber -= 1;
				}
				
				$(grid_selector).setGridParam({'postData':JSON.stringify($scope.requestObject)}).trigger("reloadGrid");
			}
			
		},
		onSortCol:function(index,iCol,sortorder){
			$scope.requestObject.sortBy = [];
			$scope.requestObject.sortBy.push(index);
			$scope.requestObject.direction = sortorder.toUpperCase();
			$(grid_selector).setGridParam({'postData':JSON.stringify($scope.requestObject)}).trigger("reloadGrid");
		},
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		sortname : 'id',
		viewrecords : true,
		sortorder : "desc",
		caption : "Users",
		loadComplete : function() {
			var table = this;
			setTimeout(function(){
				//updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		}
	});
	
    jQuery(grid_selector).jqGrid('navGrid', pager_selector, {
		edit : false,
		add : true,
		del : false
	});
	
	function enableTooltips(table) {
		$('#add_usersList')[0].title = "Add new user";
	}
	
	$scope.setSearchColumn = function(searchValue,event){
		//quick fix for radio buttons - only one selected.
		$(".searchBtnGroup .btn").removeClass("active");
		$scope.requestObject.searchColumn = searchValue;
		
	};
	
	$scope.search = function(){
		$(grid_selector).setGridParam({'postData':JSON.stringify($scope.requestObject)}).trigger("reloadGrid",[{ page: 1}]);
	};
	
	$(window).bind('resize', function() {
		$("#usersList").setGridWidth($(window).width()-300);
	}).trigger('resize');
	
	
	//CUSTOM ACTIONS
	$("#add_usersList .ui-pg-div").click(function(ev){
		ev.preventDefault();
		return false;
	});
	
	$scope.items = ['item1', 'item2', 'item3'];

	$scope.open = function(){
		
		var modalInstance = $modal.open({
			templateUrl: 'layoutservice/user/createUserModal',
			controller: ModalInstanceCtrl,
			resolve: {
				items: function () {
					return $scope.items;
				}
			}
		});

	    modalInstance.result.then(function () {
	    	$(grid_selector).setGridParam({'postData':JSON.stringify($scope.requestObject)}).trigger("reloadGrid");
	    },function () {
	      $log.info('Modal dismissed at: ' + new Date());	      
	    });
	    
	};
	
	$("#add_usersList .ui-pg-div").click(function(ev){
		$("#openAddNewUserModal").click();
		//$scope.open();
	});
};

var ModalInstanceCtrl = function ($http,$scope, $modalInstance, items) {

	$scope.items = items;
	
	$scope.requestObject = {};
	$scope.requestObject.pageNumber = "";
	$scope.requestObject.pageSize = 0;
	$scope.requestObject.direction = "";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "";
	$scope.requestObject.searchTerm = "";
	
	$scope.requestObject.user = {};
	$scope.requestObject.user.password = "";
	
	$scope.onError = false;
	$scope.tipoUsuarioList = [];
	
	$scope.EMAIL_REGEXP = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
	
	$scope.init = function(){
		
		$http.get('rest/protected/tipoUsuario/getAll')
		.success(function(response) {

			$scope.tipoUsuarioList = response.tipoUsuarioList;
			$scope.requestObject.user.idTipoUsuario = $scope.tipoUsuarioList[0].idTipoUsuario;
			
		});
		
	};
	
	$scope.init();
	
	$scope.create = function(event) {
		
		if(this.createUserForm.$valid){
			this.onError = false;
			
			$http.post('rest/protected/users/create',$scope.requestObject)
			.success(function(response) {

				if(response.code === 200){
					$modalInstance.close();
				}
				
			});
			
		}else{
			this.onError = true;
		}
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};