<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign navlinks = [{"name" : "Projects screen", "url" : "/project", "icon" : "fa fa-list-alt", "type": "active"}, {"name" : "Projects history", "url" : "/project/history", "icon" : "fa fa-archive"},{"name" : "Projects search", "url" : "/project/search", "icon" : "fa fa-search"}]>
<body ng-app="ScrumBoardApp" ng-controller="ProjectController">
<script type="text/javascript">

var app = angular.module("ScrumBoardApp", ["ngAnimate","scDirectives", "scControllers", "ui.bootstrap", "ui.select2"]);

app.factory('TemplateData', function(){
	
	return {sourcelink: "<@spring.url ''/>", projectresourcelink: "/rest/project/list"}
})
	
</script>

<div class="subbar navbar-color">
	<div class="subbar-nav">
		<ul class="nav subbar-nav pull-right">
			<li class="sub-active">
				<a>Add project</a>
			</li>
		</ul>
	</div>
</div>
<div class="wrapper">
	<div class="row">
		<div class="col-md-2">
			<#include "./templates/components/NavigationBarLeft.ftl">
		</div>
		<div class="col-md-10">
			<div class="box">
				<div class="box-header">
					<h1><i class="fa fa-list-alt"></i>Project list</h1>
				</div>
				<div class="box-content-wrapper">
<!-- 					<div class="box-small-wrapper pull-right">
						<div class="box-small-content">
						</div>
					</div> -->
					<div class="row">
						<div class="col-md-6">
							<div class="box-small-wrapper">
								<div class="box-small-content">
									<h2>Active projects</h2>
									<div ng-controller="ProjectController">
												<div ng-repeat="project in projects" ng-project>
												</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="box-small-wrapper">
								<div class="box-small-content">
									<h2>Planned projects</h2>
									
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