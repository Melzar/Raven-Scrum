<#include "./templates/ScrumHeader.ftl">
<#include "./templates/components/NavigationBar.ftl">
<body ng-app="ScrumBoardApp" ng-controller="ModalController">
<div class="subbar">
	<div class="subbar-nav">
		<ul class="nav navbar-nav pull-right">
			<li class="sub-active">
				<a>Scrumboard projektu</a>
			</li>
			<li>
				<a>Dashboard projektu</a>
			</li>
			<li>
				<a>
					Akcje projektu <i class="fa fa-caret-down"></i>
				</a>
			</li>
			<li>
				<a>Backlog projektu</a>
			</li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	
var app = angular.module("ScrumBoardApp", ["ngAnimate", "ngDragDrop", "scDirectives", "scControllers", "ui.bootstrap", "ui.select2"])
app.factory('ScrumData', function()
{
	return {getlink: "<@spring.url '/rest/project/1/scrumboard/active'/>"};
})
app.factory('TemplateData', function(ScrumData)
{
	var formatUser = function(user)
	{
	        return user.name + ' ' + user.surname;
	}

	var formatTask = function(task)
	{
		return task.title
	}

	var formatType = function(type)
	{
		return type.type;
	}
	return {select2types: {'width': 'copy', 'data': {'results': [], 'text': 'tag'}, 'formatResult': formatType, 'formatSelection': formatType }, select2users: {'width': 'copy','data': {'results': [], 'text': 'tag'},'formatResult': formatUser, 'formatSelection': formatUser}, select2tasks: {'width' : 'copy', 'data': {'results': [], 'text': 'title'}, 'formatResult' : formatTask, 'formatSelection' : formatTask}, sourcelink: "<@spring.url ''/>", taskdescription: ""};
})
</script>

<div class="wrapper">
<div class="col-lg-12">
<script type="text/ng-template" id="ScrumTasks.html">
			<div ng-repeat="task in scrumtasks">
			<div class="row loadin">
				<div class="col-lg-12">
					<div class="scrum-columns columns-header">
						<a class="subtask-title">{{task.title}}</a>
						<span class="pull-right">		
									T: {{task.progress.TODO.length}}
									D: {{task.progress.DOING.length}}
									U: {{task.progress.UAT.length}}
									DN: {{task.progress.DONE.length}}
						</span>
					</div>
				</div>
			</div>
			<div class="row loadin table" id="{{task.id}}">
					<div class="col-lg-3 cell scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true}" ng-model="task.progress.TODO" subtask="{{task.id}}" ng-dropped>
						<div class="subtask" ng-model="task.progress.TODO" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-repeat="subtask in task.progress.TODO" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}' }" data-drag="true" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding second" data-drop="true" ng-model="task.progress.DOING" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true}" subtask="{{task.id}}" ng-dropped>
						<div class="subtask" ng-model="task.progress.DOING" ng-repeat="subtask in task.progress.DOING" data-drag="true"  data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true}" ng-model="task.progress.UAT" subtask="{{task.id}}" ng-dropped>
						<div class="subtask" ng-model="task.progress.UAT" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-repeat="subtask in task.progress.UAT" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" data-drag="true" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding second" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true}" ng-model="task.progress.DONE" subtask="{{task.id}}" ng-dropped>
						<div class="subtask" ng-model="task.progress.DONE" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-repeat="subtask in task.progress.DONE" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" data-drag="true" ng-scrum-task></div>
					</div>
			</div>
			</div>
</script>
	<div class="row">
		<div class="col-lg-6">
			<div class="row">
				<div class="col-lg-12">
					<legend>Scrumboard</legend>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="row">
				<div class="col-lg-6">
				</div>
				<div class="col-lg-6">
					<a class="btn btn-primary btn-lg pull-right" ng-click="addTask()"> <i class="fa fa-plus"></i>
						Dodaj zadanie
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row loadin">
		<div class="col-lg-6">
			<div class="row top-margin">
				<div class="col-lg-7">
					<div class="alert alert-info">
						<div class="row">
							<div class="col-lg-2">
								<i class="icon-time icon-3x"></i>
							</div>
							<div class="col-lg-10">
								<div class="row">
									<div class="col-lg-12">
										<span> <b>Sprint 1</b>
										</span>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<span>01.01.2011 - 07.01.2011 (x - d.left)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="row top-margin">
				<div class="col-lg-12">
					<div class="alert alert-info pull-right">
						<div class="col-lg-1">
							<i class="icon-info icon-3x pull-left"></i>
						</div>
						<div class="col-lg-10">
							<div class="row">
								<div class="col-lg-12">
									<span>Tasks left in sprint overall : 10</span>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<span>Your tasks left : 10</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row top-margin loadin table">

			<div class="col-lg-3 cell scrum-columns columns-top">{{scrumcounter.todo}} TODO</div>
			<div class="col-lg-3 cell scrum-columns columns-top second">{{scrumcounter.doing}} Doing...</div>
			<div class="col-lg-3 cell scrum-columns columns-top">{{scrumcounter.uat}} UAT</div>
			<div class="col-lg-3 cell scrum-columns columns-top second">{{scrumcounter.done}} Done</div>
	</div>
	<div class="row">
		<div class="col-lg-12" ng-Scrumboard>
			<!-- Content injected dynamically -->
		</div>
	</div>
</div>
</div>
<#include "./templates/ScrumFooter.ftl">