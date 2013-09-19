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