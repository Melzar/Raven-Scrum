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
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Project title</label>
										<input type="text" ng-model="projecttitle" id="projecttitle" name="projecttitle" class="form-control" placeholder="Insert project title" required>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Project description</label>
										<textarea placeholder="Insert project description"></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Select project status</label>
										<input type="radio" name="active">
										<input type="radio" name="passive">
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-6 col-md-offset-3">
										<label class="control-label">Attach users to project</label>
										<input ui-select2="select2data.select2usersavatar" ng-model="select2users" placeholder="Add users to project" style="width: 100%">
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