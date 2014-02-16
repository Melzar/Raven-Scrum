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
										<a class="btn btn-xs color-picker" ng-style="selectedcolor.color" ng-click="togglePicker()"></a>
									</span>
									<input type="text" class="form-control input-sm">
									<div class="colors-wrapper" ng-if="picker">
										<div class="colors-container">
											<div class="color-container inline" ng-repeat="ec in epiccolors" ng-style="ec.color" ng-click="chooseColor(ec)"></div>
										</div>
									</div>
									<span class="input-group-btn">
										<button class="btn btn-default btn-sm">Submit</button>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
						<div class="box-small-wrapper">
							<div class="box-small-content">
								<h2>Tasks</h2>
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