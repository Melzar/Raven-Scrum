<#include './templates/ScrumHeader.ftl'>
<#include './templates/components/NavigationBar.ftl'>
<#assign navlinks = [{"name" : "Create project", "url" : "/project/add", "icon" : "fa fa-plus", "type" : "active"},{"name" : "Projects screen", "url" : "/project", "icon" : "fa fa-list-alt"}]>
<body ng-app="ScrumBoardApp">
<script type="text/javascript">
	var app = angular.module("ScrumBoardApp", ["ngAnimate", "scDirectives", "scControllers","scServices", "ui.bootstrap", "ui.select2"])

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
							<form ng-controller="ProjectAddController" name="projectadd" class="form-horizontal" novalidate>
								<div class="alert" ng-class="{'animated fadeIn alert alert-success': projectadd.submitsuccess, 'animated fadeIn alert alert-danger': projectadd.submiterror}" ng-if="projectadd.submitsuccess || projectadd.submiterror">
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
								<div class="form-group" ng-class="{'has-error': (projectadd.projecttitle.$error.minlength || projectadd.projecttitle.$error.maxlength || projectadd.projecttitle.$error.unique) && !projectadd.projecttitle.$focused, 'has-success': projectadd.projecttitle.$valid && !projectadd.projecttitle.$focused}">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Project title</label>
										<input type="text" ng-model="projecttitle" id="projecttitle" name="projecttitle" class="form-control" placeholder="Insert project title" ng-focused ng-minlength="3" ng-maxlength="100" ng-project-title required>
										<span class="help-block no-margin" ng-if="projectadd.projecttitle.$error.minlength && !projectadd.projecttitle.$focused">Minimum project title length is 3 characters</span>
										<span class="help-block no-margin" ng-if="projectadd.projecttitle.$error.maxlength && !projectadd.projecttitle.$focused">Maximum project title length is 100 characters</span>
										<span class="help-block no-margin" ng-if="projectadd.projecttitle.$error.unique && !projectadd.projecttitle.$focued">
										Project title is taken, use diffrent title
										</span>
										<span class="help-block no-margin" ng-if="projectadd.projecttitle.$valid && !projectadd.projecttitle.$focused"> 
                        					<label class="input-lg-icon fa fa-check"></label>
                      					</span>
									</div>
								</div>
								<div class="form-group" ng-class="{'has-success': projectadd.projectdescription.$viewValue && !projectadd.projectdescription.$focused}">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Project description</label>
										<textarea  class="form-control" rows="3" ng-model="projectdescription" name="projectdescription" placeholder="Insert project description" ng-focused></textarea>
										<span class="help-block no-margin" ng-if="!projectadd.projectdescription.$viewValue">Optional field</span>
										<span class="help-block no-margin" ng-if="projectadd.projectdescription.$viewValue && !projectadd.projectdescription.$focused"> 
                        					<label class="input-lg-icon fa fa-check"></label>
                      					</span>
									</div>
								</div>
								<div class="form-group" ng-class="{'has-success': projectadd.projectstatus.$viewValue}">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Select project status</label>
										<div class="radio">
											<input type="radio" ng-model="projectstatus" name="projectstatus" value="ACTIVE" required>Active
										</div>
										<div class="radio">
											<input type="radio" ng-model="projectstatus" name="projectstatus" value="PLANNED" required>Planned
											<span class="help-block no-margin" ng-if="projectadd.projectstatus.$viewValue"> 
                        						<label class="input-lg-icon fa fa-check"></label>
                      						</span>
										</div>
									</div>
								</div>
								<div class="form-group" ng-class="{'has-success': select2users.length > 0}">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Attach users to project</label>
										<input ui-select2="select2data.select2usersavatar" ng-model="select2users" placeholder="Add users to project" style="width: 100%" ng-change="updateModel()">
										<span class="help-block no-margin" ng-if="select2users.length == 0">Optional field</span>
										<span class="help-block no-margin" ng-if="select2users.length > 0"> 
                        						<label class="input-lg-icon fa fa-check"></label>
                      					</span>
									</div>
								</div>
								<div class="form-group">
									<div ng-if="select2users" class="col-md-6 col-md-offset-3">
										<label class="control-label">Select project admin/s</label>

											<div ng-user-project>
											</div>
											<span class="help-block no-margin">Optional field. To select admin just click on avatar</span>
									</div>
								</div>
								<div class="form-group" ng-if="projectadd.$invalid && projectadd.submitted">
									<div class="col-md-6 col-md-offset-3">
									<p class="text-danger">Form cannot be submitted, check if data in fields is correct</p>
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