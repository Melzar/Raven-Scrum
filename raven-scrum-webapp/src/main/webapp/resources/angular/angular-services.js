var scservices = angular.module('scServices', []);

scservices.service('Select2Service', function($http, TemplateData){
	
	var formatUserPlain = function(user)
	{
	    return user.name + ' ' + user.surname;
	}

	var formatUserAvatarOnly = function(user)
	{
		return "<a href='#' class='thumbnail inline'><img class='media-object' data-src='holder.js/64x64' src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAACDUlEQVR4Xu2Yz6/BQBDHpxoEcfTjVBVx4yjEv+/EQdwa14pTE04OBO+92WSavqoXOuFp+u1JY3d29rvfmQ9r7Xa7L8rxY0EAOAAlgB6Q4x5IaIKgACgACoACoECOFQAGgUFgEBgEBnMMAfwZAgaBQWAQGAQGgcEcK6DG4Pl8ptlsRpfLxcjYarVoOBz+knSz2dB6vU78Lkn7V8S8d8YqAa7XK83ncyoUCjQej2m5XNIPVmkwGFC73TZrypjD4fCQAK+I+ZfBVQLwZlerFXU6Her1eonreJ5HQRAQn2qj0TDukHm1Ws0Ix2O2260RrlQqpYqZtopVAoi1y+UyHY9Hk0O32w3FkI06jkO+74cC8Dh2y36/p8lkQovFgqrVqhFDEzONCCoB5OSk7qMl0Gw2w/Lo9/vmVMUBnGi0zi3Loul0SpVKJXRDmphvF0BOS049+n46nW5sHRVAXMAuiTZObcxnRVA5IN4DJHnXdU3dc+OLP/V63Vhd5haLRVM+0jg1MZ/dPI9XCZDUsbmuxc6SkGxKHCDzGJ2j0cj0A/7Mwti2fUOWR2Km2bxagHgt83sUgfcEkN4RLx0phfjvgEdi/psAaRf+lHmqEviUTWjygAC4EcKNEG6EcCOk6aJZnwsKgAKgACgACmS9k2vyBwVAAVAAFAAFNF0063NBAVAAFAAFQIGsd3JN/qBA3inwDTUHcp+19ttaAAAAAElFTkSuQmCC' tooltip-placement='bottom' tooltip='ojej' title="+ formatUserPlain(user) +"></img></a>"; 
	}

	var formatUserAvatarWithText = function(user)
	{
		return "<div class='table-layout'><a href='#' class='thumbnail inline table-cell-layout'><img class='media-object' data-src='holder.js/64x64' src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAACDUlEQVR4Xu2Yz6/BQBDHpxoEcfTjVBVx4yjEv+/EQdwa14pTE04OBO+92WSavqoXOuFp+u1JY3d29rvfmQ9r7Xa7L8rxY0EAOAAlgB6Q4x5IaIKgACgACoACoECOFQAGgUFgEBgEBnMMAfwZAgaBQWAQGAQGgcEcK6DG4Pl8ptlsRpfLxcjYarVoOBz+knSz2dB6vU78Lkn7V8S8d8YqAa7XK83ncyoUCjQej2m5XNIPVmkwGFC73TZrypjD4fCQAK+I+ZfBVQLwZlerFXU6Her1eonreJ5HQRAQn2qj0TDukHm1Ws0Ix2O2260RrlQqpYqZtopVAoi1y+UyHY9Hk0O32w3FkI06jkO+74cC8Dh2y36/p8lkQovFgqrVqhFDEzONCCoB5OSk7qMl0Gw2w/Lo9/vmVMUBnGi0zi3Loul0SpVKJXRDmphvF0BOS049+n46nW5sHRVAXMAuiTZObcxnRVA5IN4DJHnXdU3dc+OLP/V63Vhd5haLRVM+0jg1MZ/dPI9XCZDUsbmuxc6SkGxKHCDzGJ2j0cj0A/7Mwti2fUOWR2Km2bxagHgt83sUgfcEkN4RLx0phfjvgEdi/psAaRf+lHmqEviUTWjygAC4EcKNEG6EcCOk6aJZnwsKgAKgACgACmS9k2vyBwVAAVAAFAAFNF0063NBAVAAFAAFQIGsd3JN/qBA3inwDTUHcp+19ttaAAAAAElFTkSuQmCC' tooltip-placement='bottom' tooltip='ojej' title="+ formatUserPlain(user) +"></img></a><div class='table-cell-layout element-middle padding-left'>" + formatUserPlain(user)+ "</div></div>"; 
	}

	var formatTask = function(task)
	{
		return task.title
	}

	var formatType = function(type)
	{
		return type.type;
	}

	this.select2usersavatar = {'watchModelChanges': false,'multiple': true, 'width': 'copy','data': {'results': [], 'text': 'tag'},'formatResult': formatUserAvatarWithText, 'formatSelection': formatUserAvatarOnly};
	this.select2users = {'width': 'copy','data': {'results': [], 'text': 'tag'},'formatResult': formatUserPlain, 'formatSelection': formatUserPlain};
	this.select2tasks = {'width' : 'copy', 'data': {'results': [], 'text': 'title'}, 'formatResult' : formatTask, 'formatSelection' : formatTask};
	this.select2types = {'width': 'copy', 'data': {'results': [], 'text': 'tag'}, 'formatResult': formatType, 'formatSelection': formatType};
})

scservices.service('ProjectService', function($http, TemplateData){
	
	this.getProjectUsers = function(DTO)
	{
		$http.get(TemplateData.sourcelink + '/rest/project/users?project=' + TemplateData.project).success(function(data,status,headers,cfg){
			DTO.results = data;
		}).error(function(data,status,headers,cfg){
			DTO.results =  null
			//todo error message;
		})
	}
})

scservices.service('ScrumBoardService', function($http, TemplateData){

	this.getTaskTypes = function(DTO)
	{
		$http.get(TemplateData.sourcelink + "/rest/task/type").success(function(data, status, headers, cfg){
			DTO.results = data;
		}).error(function(data,status,headers,cfg){
			DTO.results = null;
		//TODO ERROR MESSAGE
		})	
	}
})

scservices.service('AccountService', function($http, TemplateData){
	
	this.getActiveAccounts = function(DTO)
	{
		$http.get(TemplateData.sourcelink + '/rest/account/list').success(function(data,status,headers,cfg){
			DTO.results = data
		}).error(function(data,status,headers,cfg){
			DTO.results =  null
			//todo error message;
		})
	}
})

scservices.service('BooleanTools', function(){
	
	this.toggler = function(value)
	{
		return (!value) ? true : false;
	}
})