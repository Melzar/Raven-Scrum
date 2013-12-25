<#include './templates/ScrumHeader.ftl'>
<#include './templates/components/NavigationBar.ftl'>
<#assign navlinks = [{"name" : "Create project", "url" : "/project/add", "icon" : "fa fa-plus", "type" : "active"},{"name" : "Projects screen", "url" : "/project", "icon" : "fa fa-list-alt"}, {"name" : "Projects history", "url" : "/project/history", "icon" : "fa fa-archive"},{"name" : "Projects search", "url" : "/project/search", "icon" : "fa fa-search"}]>
<body ng-app="ScrumBoardApp">
<script type="text/javascript">
	var app = angular.module("ScrumBoardApp", ["ngAnimate", "scDirectives", "scControllers","scServices", "ui.bootstrap", "ui.select2", "ui.directives"])

	app.factory("TemplateData", function(){
		return { sourcelink: "<@spring.url ''/>"}
	})
</script>

<div class="wrapper" ng-app="ScrumBoardApp">
<div class="row">
	<div class="col-md-2">
		<#include "./templates/components/NavigationBarLeft.ftl">
	</div>
	<div class="col-md-10">
		<div class="box">
			<div class="box-header">
				<h1><i class="fa fa-plus"></i>Create new project</h1>
			</div>
			<div class="box-content-wrapper">
				<div class="box-small-wrapper">
					<div class="box-small-content">
							<form ng-controller="ProjectAddController" name="ProjectAdd" class="form-horizontal" novalidate>
								<div class="alert" ng-class="{'animated fadeIn alert alert-success': projectadd.success, 'animated fadeIn alert alert-danger': projectadd.submiterror}" ng-if="projectadd.submitsuccess || projectadd.submiterror">
								           <button type="button" class="close" ng-click="hideMessage()">×</button>
								              <div class="alert-icon pull-left">
								                 <i class="" ng-class="{'fa fa-check fa-3x' : projectadd.submitsuccess, 'fa fa-times fa-3x' : projectadd.submiterror}"></i>
								              </div>
								              <div class="alert-details">
								                <h4 ng-if="projectadd.submitsuccess">Submit successful</h4>
								                <h4 ng-if="projectadd.submiterror">Submit error!</h4>
								                <p ng-if="projectadd.submitsuccess">Project has been created successfully</p>
								                <p ng-if="projectadd.submiterror">There were internal error during form submition, check again your data or try again later</p>
								              </div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Project title</label>
										<input type="text" ng-model="projecttitle" id="projecttitle" name="projecttitle" class="form-control" placeholder="Insert project title" required>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Project description</label>
										<textarea  class="form-control" rows="3" ng-model="projectdescription" name="projectdescription" placeholder="Insert project description"></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Select project status</label>
										<div class="radio">
											<input type="radio" ng-model="projectstatus" name="projectstatus" value="ACTIVE">Active
										</div>
										<div class="radio">
											<input type="radio" ng-model="projectstatus" name="projectstatus" value="PLANNED">Planned
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Attach users to project</label>
										<input ui-select2="select2data.select2usersavatar" ng-model="select2users" placeholder="Add users to project" style="width: 100%">
									</div>
								</div>
								<div class="form-group">
									<div ng-if="select2users" class="col-md-6 col-md-offset-3">
										<label class="control-label">Select project manager/s</label>
											<div ng-repeat="user in select2users" ng-user-project>
											</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<a class="btn btn-primary pull-right" ng-click="createProject()">Save and create</a>
										<a class="btn btn-primary" href="<@spring.url '/project'/>">Cancel</a>
									</div>
								</div>
							</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>