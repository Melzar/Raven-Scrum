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

sccontrollers.controller('ScrumBoardController', function($q, $scope, $http, $element, ScrumData)
{
	$scope.scrumdata = ScrumData;
	$http.get(ScrumData.getlink).success(function(data,status,headers,cfg){
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
			$scope.scrumcounter.todo += value.todo.length;
			$scope.scrumcounter.doing += value.doing.length;
			$scope.scrumcounter.uat += value.uat.length;
			$scope.scrumcounter.done += value.done.length;
		})
	}

})