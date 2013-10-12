<#include "./templates/ScrumHeader.ftl">
<#include "./templates/components/NavigationBar.ftl">
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
					Akcje projektu <i class="icon-caret-down"></i>
				</a>
			</li>
			<li>
				<a>Backlog projektu</a>
			</li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	
var app = angular.module("ScrumBoardApp", ["ngAnimate", "ngDragDrop", "scDirectives", "scControllers"])
app.factory('ScrumData', function()
{
	return {getlink: "<@spring.url '/rest/project/1/scrumboard/active'/>", projectdata: ''};
})
</script>

<div class="wrapper">
<div class="col-lg-12" ng-app="ScrumBoardApp">
<script type="text/ng-template" id="ScrumTasks.html">
			<div ng-repeat="task in scrumtasks">
			<div class="row loadin">
				<div class="col-lg-12">
					<div class="scrum-columns columns-header">
						<a class="subtask-title">{{task.title}}</a>
						<span class="pull-right">		
									T: {{task.todo.length}}
									D: {{task.doing.length}}
									U: {{task.uat.length}}
									DN: {{task.done.length}}
						</span>
					</div>
				</div>
			</div>
			<div class="row loadin table" id="{{task.idTask}}">
					<div class="col-lg-3 cell scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.idTask}}])'}" jqyoui-droppable="{multiple:true}" ng-model="task.todo" subtask="{{task.idTask}}" ng-dropped>
						<div class="subtask" ng-model="task.todo" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-repeat="subtask in task.todo" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.idTask}}' }" data-drag="true" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding second" data-drop="true" ng-model="task.doing" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.idTask}}])'}" jqyoui-droppable="{multiple:true}" subtask="{{task.idTask}}" ng-dropped>
						<div class="subtask" ng-model="task.doing" ng-repeat="subtask in task.doing" data-drag="true"  data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.idTask}}'}" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.idTask}}])'}" jqyoui-droppable="{multiple:true}" ng-model="task.uat" subtask="{{task.idTask}}" ng-dropped>
						<div class="subtask" ng-model="task.uat" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-repeat="subtask in task.uat" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.idTask}}'}" data-drag="true" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding second" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.idTask}}])'}" jqyoui-droppable="{multiple:true}" ng-model="task.done" subtask="{{task.idTask}}" ng-dropped>
						<div class="subtask" ng-model="task.done" jqyoui-draggable="{index: {{$index}}, animate:false}" ng-repeat="subtask in task.done" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.idTask}}'}" data-drag="true" ng-scrum-task></div>
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
				<div class="col-lg-6"></div>
				<div class="col-lg-6">
					<a class="btn btn-primary btn-lg pull-right"> <i class="icon-plus"></i>
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