<#include "./templates/ScrumHeader.ftl">
<#include "./templates/components/NavigationBar.ftl">
<body ng-app="ScrumBoardApp">
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
app.factory('TemplateData', function()
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
<div ng-class="{'col-lg-12': !leftpanel, 'col-lg-9': leftpanel}">
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
				<div class="col-lg-6" ng-controller="ModalController">
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
	<div class="row">
		<div class="col-lg-12">
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
</div>

<div class="col-lg-3 hide" ng-class="{'shown': leftpanel, 'hidden' : !leftpanel}">
	<div class="well">
		<div class="panel-left-header">
		{{subtaskpanel.task.title}} - {{subtaskpanel.task.id}}
		<div class="pull-right">
				<button type="button" class="close" ng-click="leftpanel = !leftpanel">×</button>
			</div>
		</div>
		<div class="task-panel-left">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="task-panel-sub-header">
								Task Actions
							</div>
						</div>
						<div class="row">
							<div class="col-lg-offset-1 col-lg-11">
								<a class="btn btn-primary btn-sm" ng-click="">Move to backlog </a>
								<a class="btn btn-primary btn-sm" ng-click="deleteTask()">Delete task </a>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="task-panel-sub-header">
								Task details
							</div>
						</div>
						<div class="row">
							<label>Description</label>
							<textarea readonly>{{subtaskpanel.task.description}}</textarea>
						</div>
						<div class="row">
							<label>Type</label>
							<select ui-select2 ng-model="panel.select2types" data-placeholder="Pick a number" style="width: 100%">
	   							 <option ng-repeat="type in data.select2types.data.results" value="{{type.type}}">{{type.type}}</option>  	
							</select>
						</div>
						<div class="row">
							<label>Assigned</label>
							<select ui-select2 ng-model="panel.select2users" data-placeholder="Pick a number" style="width: 100%">
	   							 <option ng-repeat="user in data.select2users.data.results" value="{{user.id}}">{{user.name}} {{user.surname}}</option>  	
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="task-panel-sub-header">
								Task Comments
							</div>
						</div>
						<div class="row">
							<div class="col-lg-offset-1 col-lg-11">

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</div>
</div>
</div>
<#include "./templates/ScrumFooter.ftl">