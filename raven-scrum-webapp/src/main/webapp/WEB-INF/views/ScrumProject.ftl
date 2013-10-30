<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign navlinks = [{"name" : "Projects screen", "url" : "/project", "icon" : "fa fa-list-alt", "type": "active"},{"name" : "Add project", "url" : "/project/add", "icon" : "fa fa-plus"}]>
<body ng-app="projectApp" ng-controller="ProjectController">
<script type="text/javascript">

var app = angular.module("projectApp", ["ngAnimate","scDirectives", "scControllers", "ui.bootstrap", "ui.select2"]);

app.factory('TemplateData', function(){
	
	return {sourcelink: "<@spring.url ''/>"}
})
	
</script>

<div class="wrapper">
<div class="col-lg-12">
		<div class="row">
			<div class="col-lg-2">
				<#include "./templates/components/NavigationBarLeft.ftl">
			</div>
			<div class="col-lg-10 loadin">
				<div class="well well-page-header">
					<div class="row">
						<div class="col-lg-12">
							<legend>Projects screen</legend>	
						</div>
					</div>
				</div>	
				<div class="row">
					<div class="col-lg-4">
						<div class="well">
							<div class="row">
								<div class="col-lg-12">
									<div class="well-header">
										<span>Project groups</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-8">
						<div class="well">
							<div class="row">
								<div class="col-lg-12">
									<div class="well-header">
										<span>Project list</span>
									</div>
										<div class="row" ng-repeat="project in projects" ng-project="<@spring.url ''/>">
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

<#include "./templates/ScrumFooter.ftl">