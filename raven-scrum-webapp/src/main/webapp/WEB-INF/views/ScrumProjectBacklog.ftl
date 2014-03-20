<#include "./templates/ScrumHeader.ftl">
<#include "./templates/components/NavigationBar.ftl">
<body ng-app="ScrumBoardApp">
<script type="text/javascript">
	
var app = angular.module("ScrumBoardApp", ["ngAnimate", "ngDragDrop", "scDirectives", "scControllers","scServices", "ui.bootstrap", "ui.select2"])
app.factory('TemplateData', function()
{
	return {sourcelink: "<@spring.url ''/>", project: ${project}};
})
</script>


<div class="wrapper">
	<div class="box">
		<div class="box-header">
			<h1><i class="fa fa-list"></i>Backlog</h1>
		</div>
		<div class="box-content-wrapper">
			<div class="box-content">
				<div class="row">
				<div class="col-md-3">
					<div class="box-small-wrapper">
						<div class="box-small-content" ng-controller="EpicController">
							<h2>Epic</h2>
							<div class="epic epic-top">
								<a class="btn btn-default btn-xs" ng-click="toggle()" ng-if="!addepic">Add epic</a>
								<div ng-if="addepic" class="input-group" ng-class="{'fadeIn animated': addepic}">
									<span class="input-group-addon color">
										<a class="btn btn-xs color-picker" ng-style="{'background-color': selectedcolor.code}" ng-click="togglePicker()"></a>
									</span>
									<input type="text" class="form-control input-sm" ng-model="$parent.epicName">
									<div class="colors-wrapper" ng-if="picker">
										<div class="colors-container">
											<div class="color-container inline" ng-repeat="ec in epiccolors" ng-style="{'background-color': ec.code}" ng-click="chooseColor(ec)"></div>
										</div>
									</div>
									<span class="input-group-btn">
										<a class="btn btn-default btn-sm" ng-click="submitEpic()">Submit</a>
									</span>
								</div>
							</div>
							<hr>
							<div class="epic epic-content">
								<div class="epic epic-element transitionable" ng-repeat="epic in epics">
									<div class="color-container inline" ng-style="{'background-color': epic.color.code}" ng-click="changeEpicColor(epic)"></div>
									<div class="colors-container" ng-if="epic.picker" ng-class="{'transitionable': epic.picker}">
											<div class="color-container inline" ng-repeat="ec in epiccolors" ng-style="{'background-color' : ec.code}" ng-click="chooseEpicColor(ec, epic)"></div>
									</div>
									<div class="input-group" ng-if="epic.edit">
							          <input type="text" ng-model="epic.epicName" class="form-control input-sm">
							          <span class="input-group-btn">
							            <a class="btn btn-default btn-sm" type="button" ng-click="saveEpicName(epic)">Save</a>
							          </span>
							        </div>
									<span class="epic-text" ng-if="!epic.edit">{{epic.epicName}}</span>
									<div class="btn-group" ng-if="!epic.edit">
								        <a id="btnGroupVerticalDrop1" type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
								          <i class="fa fa-cog"></i>
								          <span class="caret"></span>
								        </a>
								        <ul class="dropdown-menu" role="menu" aria-labelledby="btnGroupVerticalDrop1">
								          <li><a href="#" ng-click="editEpicName(epic)">Edit</a></li>
								          <li><a href="#" ng-click="deleteEpic($index)">Delete</a></li>
								        </ul>
								    </div>
								</div>
							</div>					
						</div>
					</div>
				</div>
				<div class="col-md-6">
						<div id="backlog" class="box-small-wrapper" ng-controller="BacklogTaskController">
							<div class="box-small-content">
								<h2>Tasks</h2>
								<div class="backlog-sprint container-wrapper">
										<div class="backlog-sprint container root-task" ng-init="sprint.showChildren = true">
											SPRINT
											<a class="pull-right" ng-if="!sprint.showChildren" ng-click="sprint.showChildren = !sprint.showChildren"><i class="fa fa-angle-double-down fa-2x"></i></a>			
											<a class="pull-right" ng-if="sprint.showChildren" ng-click="sprint.showChildren = !sprint.showChildren"><i class="fa fa-angle-double-up fa-2x"></i></a>
										</div>
								</div>
								<div class="backlog-tasks" ng-if="sprint.showChildren" ng-repeat="task in scrumtasks">
									<div class="backlog-task" data-drop="true" data-jqyoui-options="{accept:'.bsubtask:not([sprint=true])'}" jqyoui-droppable="{multiple:true, onDrop: ''}" ng-model="task.subtasksRaw">
										<div class="container-wrapper">
											<div class="backlog-task container task-parent">
													<a ng-class="{'subtask-done': task.state == 'DONE'}">{{task.title}}</a>		
													<small ng-class="{'subtask-done': task.state == 'DONE'}"> : {{task.description}}</small>	
													<a class="pull-right" ng-if="!task.showChildren" ng-click="task.showChildren = !task.showChildren"><i class="fa fa-angle-double-down"></i></a>			
													<a class="pull-right" ng-if="task.showChildren" ng-click="task.showChildren = !task.showChildren"><i class="fa fa-angle-double-up"></i></a>		
											</div>
										</div>
										<div class="backlog-subtasks" ng-if="task.showChildren" ng-repeat="subtask in task.subtasksRaw">
											<div ng-backlog-subtask></div>										
										</div>	
									</div>

								</div>
								<div data-drop="true" data-jqyoui-options="{accept:'.bsubtask:not([sprint=false])'}" jqyoui-droppable="{multiple:true, onDrop: ''}" ng-model="backlogtasks">
								<div class="backlog-planned container-wrapper">
									<div class="backlog-planned container root-task" ng-init="backlog.showChildren = true">
											BACKLOG
											<a class="pull-right" ng-if="!backlog.showChildren" ng-click="backlog.showChildren = !backlog.showChildren"><i class="fa fa-angle-double-down fa-2x"></i></a>			
											<a class="pull-right" ng-if="backlog.showChildren" ng-click="backlog.showChildren = !backlog.showChildren"><i class="fa fa-angle-double-up fa-2x"></i></a>
									</div>
								</div>
								<div class="backlog-tasks" ng-if="backlog.showChildren">		
									<div class="backlog-task">
										<div class="backlog-task container task-parent bsubtask" sprint="false" ng-model="backlogtasks" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: ''}" ng-repeat="task in backlogtasks" data-jqyoui-options="{revert: 'invalid', containment: '#backlog'}" data-drag="true">
												<a>{{task.title}} - {{task.id}}</a>		
												<small> : {{task.description}}</small>
										</div>
									</div>
								</div>
								</div>
							</div>
						</div>
				</div>
				<div class="col-md-3">
						<div class="box-small-wrapper">
							<div class="box-small-content">
								<h2>Toolbar</h2>
							</div>
						</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>


<#include "./templates/ScrumFooter.ftl">