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
	
var app = angular.module("ScrumBoardApp", ["ngAnimate", "ngDragDrop", "scDirectives", "scControllers", "ui.bootstrap", "ui.select2", "ui.directives"])
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
	<div class="box">
		<div class="box-header">
			<h1><i class="fa fa-cog"></i>Scrumboard</h1>
		</div>
		<div class="box-content-wrapper table-layout">
			<div class="box-content table-cell-layout">
				<div class="row">
					<div class="col-md-12">
						<div class="box-small-wrapper">
							<div class="box-small-content">
								<div class="row">
										<div class="col-md-12">
											<div class="project-avatar pull-left">
											</div>
											<div class="sprint-details">
													<div class="circle-box pull-right">
												<!-- 	<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
   															<circle cx="62" cy="63" r="55" stroke="#1abc9c" stroke-width="10" fill="none"></circle>
   															<text x="18" y="64" style="font-size: 16;">DAYS LEFT</text>
   															<text x="51" y="85" style="font-size: 16;">14</text>
														</svg> -->
													</div>
													<div class="box-small-header sprint-details-header">
														<p>Sprint data</p>
														<small>
															Sprint 01.11.2013 - 10.11.2013
														</small>
													</div>
													<div class="filter-box clear-both">
														<a class="btn btn-default">Filter 1</a>
														<a class="btn btn-default">Filter 2</a>
														<a class="btn btn-default">Filter 3</a>
														<a class="btn btn-default">Filter 4</a>
														<a class="btn btn-default">Filter 5</a>
														<a class="btn btn-default">Filter 6</a>
														<a class="btn btn-default">Filter 7</a>
														<a class="btn btn-default">Filter 8</a>
														<a class="btn btn-default">Filter 9</a>
														<a class="btn btn-default">Filter 10</a>
														<a class="btn btn-default">Filter 11</a>
														<a class="btn btn-default">Filter 12</a>
														<a class="btn btn-default">Filter 13</a>
													</div>
											</div>
											<div class="row">
												
											</div>
											<div class="row">
												<div class="col-md-12">
												
													
												</div>
											</div>
										</div>
								</div>	
							</div>
						</div>
					</div>
				</div>
				<div class="row loadin">
					<div class="col-md-12">
						<div class="widget-box">
							<div class="alert alert-warning alert-dismissable">
				                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				                <i class="icon-exclamation-sign"></i> <strong>Welcome!</strong> On project [projectname] scrumboard .
				             </div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="box-small-wrapper box-nobottom-radius">
						<div class="box-small-content box-nopadding box-nobottom-radius box-noborder-bottom box-noshadow-dark">
							<div class="subtask-table">
								<div class="subtask-column column-top bordered-right"><p>{{scrumcounter.todo}} Todo</p></div>
								<div class="subtask-column column-top bordered-right" ><p>{{scrumcounter.doing}} Doing...</p></div>
								<div class="subtask-column column-top bordered-right"><p>{{scrumcounter.uat}} Uat</p></div>
								<div class="subtask-column column-top"><p>{{scrumcounter.done}} Done</p></div>
							</div>
						</div>
						</div>
						<div class="row">
							<div class="col-md-12" ng-Scrumboard>
								<!-- Content injected dynamically -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div ng-if="rightpanel" class="table-cell-layout box-content right-panel" ng-class="{'fadeIn animated': rightpanel}">
				<div class="box-small-wrapper fixed-layout right-panel-styles" id="affix">
				<div class="box-small-content">
				<div class="box-small-header">
					<button type="button" class="close pull-right" ng-click="hidePanel()">×</button>
					<p class="">{{subtaskpanel.task.title}} - {{subtaskpanel.task.id}}</p>
				</div>
				<div class="task-panel-right">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="list-group">
							        <a href="#" class="list-group-item" ng-click="">Move to backlog</a>
							        <a href="#" class="list-group-item" ng-click="makeParent()">Make parent</a>
							        <a href="#" class="list-group-item" ng-click="deleteTask()">Delete task</a>
							    </div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<label>Description</label>
								<textarea readonly>{{subtaskpanel.task.description}}</textarea>
							</div>
							<div class="row">
								<label class="padding-top">Type</label>
								<select ui-select2 ng-model="panel.select2types" data-placeholder="Pick a number" style="width: 100%">
		   							 <option ng-repeat="type in data.select2types.data.results" value="{{type.type}}">{{type.type}}</option>  	
								</select>
							</div>
							<div class="row">
								<label class="padding-top">Assigned</label>
								<select ui-select2 ng-model="panel.select2users" data-placeholder="Pick a number" style="width: 100%">
		   							 <option ng-repeat="user in data.select2users.data.results" value="{{user.id}}">{{user.name}} {{user.surname}}</option>  	
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="row">
									<label class="padding-top">Task Comments</label>
									<div class="panel panel-default">
									  <div class="panel-body">
									    Basic panel example
									  </div>
									</div>
							</div>
						</div>
					</div>
				</div>
				</div>	
				</div>
			</div>	
		</div>
	</div>
</div>
</div>
<div class="sidebar-left transitionable" ng-controller="SidebarController" ng-class="{'left-hide': hidden}">
	<div class="element transitionable">
		<a class="" ng-click="hidden = !hidden" ng-class="{'sidebar-left-show-btn' : hidden, 'sidebar-left-hide-btn': !hidden}"><i class="fa fa-angle-double-left"></i></a>
		<ul class="list-unstyled element-list">
			<li><a class="" tooltip-placement="right" tooltip="Add task"ng-click="addTask()"><i class="fa fa-plus"></i></a></li>
			<li><a class="" tooltip-placement="right" tooltip="Project dashboard"><i class="fa fa-dashboard"></i></a></li>
			<li><a class="" tooltip-placement="right" tooltip="Contact scrummaster"><i class="fa fa-envelope-o"></i></a></li>
			<li><a class="" tooltip-placement="right" tooltip="Close sprint"><i class="fa fa-times-circle-o"></i></a></li>
		</ul>
	</div>
</div>
<#include "./templates/ScrumFooter.ftl">