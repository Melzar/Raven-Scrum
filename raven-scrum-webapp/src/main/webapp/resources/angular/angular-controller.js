var sccontrollers = angular.module('scControllers', []);

sccontrollers.factory('MessageData', function(){
	return {submitsuccess: false, submiterror: false, shadowflag: 0, toggle : false}
})

sccontrollers.controller('MessageController',function($scope, $http, $element, MessageData){
		
		$scope.messagedata = MessageData;
		$scope.showMessage = function()
		{
			$scope.messagedata.toggle = true;
		}

		$scope.hideMessage = function()
		{
			$scope.messagedata.toggle = false;
			$scope.messagedata.submitsuccess = false;
			$scope.messagedata.submiterror = false;
			$scope.messagedata.shadowflag = 0;
		}
})

sccontrollers.controller('ScrumBoardController', function($scope, $http, $element,$location, TemplateData)
{
	$scope.scrumdata = {};
	$scope.subtaskdata = {id: '', state: ''};
	$http.get(TemplateData.sourcelink + '/rest/project/'+$location.search().project+'/scrumboard/active').success(function(data,status,headers,cfg){
		$scope.scrumdata.projectdata = data;
		$scope.scrumtasks = data.sprint.tasks;
		$scope.setwatchers();
	}).error(function(data,status,headers,cfg){
		//TODO MESSAGE OF ERROR
	})


	$scope.setwatchers = function ()
	{
		 $scope.$watch(function(){ return angular.toJson($scope.scrumtasks)}, function(){
				$scope.updatecount()
		 })
	}

	$scope.updatecount = function()
	{
		$scope.scrumcounter = {todo : 0, doing : 0, uat : 0, done : 0};
		angular.forEach($scope.scrumtasks, function(value, key){
			$scope.scrumcounter.todo += value.progress.TODO.length;
			$scope.scrumcounter.doing += value.progress.DOING.length;
			$scope.scrumcounter.uat += value.progress.UAT.length;
			$scope.scrumcounter.done += value.progress.DONE.length;
		})
	}



	$scope.getsubtask = function(evt, ui)
	{
		$scope.subtaskdata.id = evt.target.dataset.idsub;
	}

	$scope.changestate = function()
	{
			$http({
				url: TemplateData.sourcelink + "/rest/task/changestate",
				method: "POST",
				data: $scope.subtaskdata ,
				headers : {'Content-Type': 'application/json'}
			}).success(function(datares, status, headers, cfg){
				console.log("success")
			}).error(function(datares, status, headers, cfg){
				//todo messagebox
				console.log("error")
			})
		console.log($scope.subtaskdata)
	}
})

sccontrollers.controller('ModalController', function($scope, $http, $modal, $log, TemplateData)
{

	$scope.addTask = function()
	{
		TemplateData.select2tasks.data.results = $scope.scrumtasks;
		TemplateData.modalbody = TemplateData.sourcelink + '/template/modal/ScrumModalBody.ftl';
		TemplateData.modaltitle = "Add Task";

		$http.get(TemplateData.sourcelink + '/rest/project/1/users').success(function(data,status,headers,cfg){
			TemplateData.select2users.data.results = data;
		}).error(function(data,status,headers,cfg){
		//TODO MESSAGE OF ERROR
		})

		$http.get(TemplateData.sourcelink + "/rest/task/type").success(function(data, status, headers, cfg){
			TemplateData.select2types.data.results = data;
		}).error(function(data,status,headers,cfg){
		//TODO ERROR MESSAGE
		})	

		var modalInstance = $modal.open(
		{
			templateUrl: TemplateData.sourcelink + '/template/modal/ModalTemplate.ftl',
      		controller: 'ModalInstanceController',
      		resolve: {
        		data: function () {
        		  return TemplateData;
        		}
      		}
		})

		modalInstance.result.then(function (promiseditem) {
    	}, function (promiseditem) {
    		if(promiseditem)
    		{
    			console.log($scope)
    		}
    	  	$log.info('Modal dismissed at: ' + new Date());
    	});
	}
})

sccontrollers.controller('ModalInstanceController', function($scope, $http, $modalInstance, data)
{
	$scope.data = data;
	$scope.closeModal = function()
	{
		$modalInstance.dismiss();
	}

	$scope.save = function()
	{
			var postdata = {idUser: "", idParent: "", description: "", type: ""};
			postdata.idUser = data.select2user.id;
			postdata.idParent = data.select2task.id;
			postdata.description = data.taskdescription;
			postdata.type = data.select2type.id;
			$http({
				url: data.sourcelink + "/rest/task/" + data.select2task.id + "/add",
				method: "POST",
				data: postdata ,
				headers : {'Content-Type': 'application/json'}
			}).success(function(datares, status, headers, cfg){
				data.select2task.progress.TODO.push(datares);
				$modalInstance.close(datares);
			}).error(function(datares, status, headers, cfg){
				//todo messagebox
				console.log("error")
			})
	}
})

sccontrollers.controller('DashboardController', function($scope, $http, TemplateData){

	$scope.templatedata = TemplateData;
	$http.get(TemplateData.sourcelink  + '/rest/project/list/user?login=' + TemplateData.user).success(function(data, status, headers, cfg)
	{
		$scope.projects = data;
	}).error(function(data, status, headers, cfg){
		//TODO error message
	})
})

sccontrollers.controller('ProjectController', function($scope, $http, TemplateData){
	$scope.templatedata = TemplateData;
	$http.get(TemplateData.sourcelink  + '/rest/project/list').success(function(data, status, headers, cfg)
	{
		$scope.projects = data;
	}).error(function(data, status, headers, cfg){
		//TODO error message
	})
})

sccontrollers.controller('NavigationController', function($scope, $http)
{

})
