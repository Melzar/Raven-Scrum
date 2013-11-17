var sccontrollers = angular.module('scControllers', []);

sccontrollers.factory('MessageData', function(){
	return {submitsuccess: false, submiterror: false, submitwarning: false, shadowflag: 0, toggle : false}
})

sccontrollers.controller("AuthenticationController", function($scope, $http, $element, MessageData, TemplateData){
	$scope.validate = function()
	{
		$http({
			url : TemplateData.sourcelink + '/rest/authentication/credentials',
			method : "POST",
			data : $element.serialize(),
			headers : {'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(function(data,status,headers,cfg){
			if(status == 200)
			{
				$element.submit();
			}
			if(status == 401)
			{	
				(!data) ? MessageData.submiterror = true : MessageData.shadowflag = data.flag;
			}
		}).error(function(data,status,headers,cfg){
			MessageData.submiterror = true;
			console.log($scope)
		})
	}
})

sccontrollers.controller('MessageController',function($scope, $http, MessageData){
		
		$scope.messagedata = MessageData;
		$scope.showMessage = function()
		{
			$scope.messagedata.toggle = true;
		}

		$scope.hideMessage = function()
		{
			$scope.messagedata.submitsuccess = false;
			$scope.messagedata.submiterror = false;
			$scope.messagedata.shadowflag = 0;
		}
})

sccontrollers.controller("RegisterController", function($scope, $http, $element, MessageData, TemplateData){
      $scope.submit = function()
      {
        $scope.messagedata = MessageData;
        $scope.registration.submitted = true;
        if($scope.registration.$valid && $scope.checkbox)
        {
          $http({
            url: TemplateData.sourcelink + '/account/registration',
            method: "POST",
            data: $element.serialize(),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
          }).success(function(data, status, headers, cfg){
            (data.success == true) ? $scope.messagedata.submitsuccess = true : $scope.messagedata.submiterror = true;
          }).error(function(data,status,headers,cfg){
            $scope.messagedata.submiterror = true;
          })
        }
      }

      $scope.validateLogin = function()
      {
      		console.log($scope)
            $scope.registration.login.$setValidity("minlength", emptyValidation($scope.registration.login.$viewValue) && minLengthValidation($scope.registration.login.$viewValue));
            ($scope.registration.login.$viewValue == $scope.registration.passwordrepeat.$viewValue) ? $scope.registration.passwordrepeat.$setValidity("equalslogin", false) : $scope.registration.passwordrepeat.$setValidity("equalslogin", true); 
            if(!$scope.registration.login.$error.minlength)
            {
              $http.post(TemplateData.sourcelink + '/rest/validate/login',JSON.stringify({'login' : $scope.registration.login.$viewValue})).success(function(data, status, headers, cfg){
                $scope.registration.login.$setValidity('unique', data.unique);
              }).error(function(data,status,headers,cfg){
                $scope.registration.login.$setValidity('unique', false);
              })
            }
      }
  });

sccontrollers.controller('ScrumBoardController', function($scope, $http, $location, TemplateData)
{
	$scope.rightpanel = false;
	$scope.data = TemplateData;
	$scope.scrumdata = {};
	$scope.subtaskdata = {id: '', state: ''};

	$http.get(TemplateData.sourcelink + '/rest/project/'+$location.search().project+'/scrumboard/active').success(function(data,status,headers,cfg){
		$scope.scrumdata.projectdata = data;
		$scope.scrumtasks = data.sprint.tasks;
		$scope.$watch(function(){ return angular.toJson($scope.scrumtasks)}, function(){
				$scope.updatecount()
		})
	}).error(function(data,status,headers,cfg){
		//TODO MESSAGE OF ERROR
	})

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
		$scope.subtaskdata.id = evt ? evt.target.dataset.idsub : window.event.srcElement.dataset.idsub;
	}

	$scope.changestate = function(evt,ui)
	{
		$scope.subtaskdata.state = evt ? evt.target.dataset.state : window.event.srcElement.dataset.state;
			$http({
				url: TemplateData.sourcelink + "/rest/task/changestate",
				method: "POST",
				data: $scope.subtaskdata,
				headers : {'Content-Type': 'application/json'}
			}).success(function(datares, status, headers, cfg){
				console.log("success")
			}).error(function(datares, status, headers, cfg){
				//todo messagebox
				console.log("error")
			})
	}

	$scope.findAndRemove = function(sub, index, parent)
	{
		parent.progress[sub.state].splice(index, 1);
	}

	$scope.makeParent = function()
	{
		$http({
			url: TemplateData.sourcelink + "/rest/task/makeparent",
			method: "POST",
			data: $scope.subtaskpanel.task,
			headers: {'Content-Type': 'application/json'}
		}).success(function(data, status, headers, cfg){
			$scope.subtaskpanel.parent.progress[$scope.subtaskpanel.task.state].splice($scope.subtaskpanel.index, 1);
			$scope.scrumtasks.push(data);
			$scope.rightpanel = false;
		}).error(function(data, status, headers, cfg){
			//todo messagebox
			console.log("error");
		})
	}

	$scope.deleteTask = function()
	{
		console.log($scope.subtaskpanel.task)
		$http({
				url: TemplateData.sourcelink + "/rest/task/delete",
				method: "POST",
				data: $scope.subtaskpanel.task,
				headers : {'Content-Type': 'application/json'}
		}).success(function(datares, status, headers, cfg){
				$scope.subtaskpanel.parent.progress[$scope.subtaskpanel.task.state].splice($scope.subtaskpanel.index, 1);
				$scope.rightpanel = false;
		}).error(function(datares, status, headers, cfg){
				//todo messagebox
				console.log("error")
		})
	}

	$scope.showPanel = function(subtask, index , parent)
	{
		console.log(subtask)
		$scope.rightpanel = true;
		$scope.subtaskpanel = {'task': subtask, 'index': index, 'parent': parent};
		console.log($scope.subtaskpanel)
		$scope.getTaskTypes();
		$scope.getProjectUsers();
	}

	$scope.hidePanel = function()
	{
		$scope.rightpanel = false;
	}

	$scope.getTaskTypes = function()
	{
		$http.get(TemplateData.sourcelink + "/rest/task/type").success(function(data, status, headers, cfg){
			TemplateData.select2types.data.results = data;
		}).error(function(data,status,headers,cfg){
		//TODO ERROR MESSAGE
		})	
	}

	$scope.getProjectUsers = function()
	{
		$http.get(TemplateData.sourcelink + '/rest/project/1/users').success(function(data,status,headers,cfg){
			TemplateData.select2users.data.results = data;
		}).error(function(data,status,headers,cfg){
		//TODO MESSAGE OF ERROR
		})
	}

})

// sccontrollers.controller('ModalController', function($scope, $http, $modal, $log, TemplateData)
// {

// 	$scope.addTask = function()
// 	{
// 		TemplateData.select2tasks.data.results = $scope.scrumtasks;
// 		TemplateData.modalbody = TemplateData.sourcelink + '/template/modal/ScrumModalBody.ftl';
// 		TemplateData.modaltitle = "Add Task";

// 		$scope.getTaskTypes();
// 		$scope.getProjectUsers();

// 		var modalInstance = $modal.open(
// 		{
// 			templateUrl: TemplateData.sourcelink + '/template/modal/ModalTemplate.ftl',
//       		controller: 'ModalInstanceController',
//       		resolve: {
//         		data: function () {
//         		  return TemplateData;
//         		}
//       		}
// 		})

// 		modalInstance.result.then(function (promiseditem) {
//     	}, function (promiseditem) {
//     		if(promiseditem)
//     		{
//     			console.log($scope)
//     		}
//     	  	$log.info('Modal dismissed at: ' + new Date());
//     	});
// 	}
// })

sccontrollers.controller('ModalInstanceController', function($scope, $http, $modalInstance, data)
{
	$scope.data = data;
	$scope.closeModal = function()
	{
		$modalInstance.dismiss();
	}

	$scope.save = function()
	{
			var postdata = {idUser: data.select2user.id, idParent: data.select2task.id, description: data.taskdescription, type: data.select2type.id};
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

})

sccontrollers.controller('ProjectController', function($scope, $http, TemplateData){
	$scope.templatedata = TemplateData;
	$http.get(TemplateData.sourcelink  + TemplateData.projectresourcelink).success(function(data, status, headers, cfg)
	{
		$scope.projects = data;
	}).error(function(data, status, headers, cfg){
		//TODO error message
	})
})

sccontrollers.controller('NavigationController', function($scope, $http)
{

})

sccontrollers.controller('SidebarController', function($scope, $http, $modal, TemplateData)
{
	$scope.addTask = function()
	{
		TemplateData.select2tasks.data.results = $scope.scrumtasks;
		TemplateData.modalbody = TemplateData.sourcelink + '/template/modal/ScrumModalBody.ftl';
		TemplateData.modaltitle = "Add Task";

		$scope.getTaskTypes();
		$scope.getProjectUsers();

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

		// modalInstance.result.then(function (promiseditem) {
  //   	}, function (promiseditem) {
  //   		if(promiseditem)
  //   		{
  //   			console.log($scope)
  //   		}
  //   	  	$log.info('Modal dismissed at: ' + new Date());
  //   	});
	}
})
