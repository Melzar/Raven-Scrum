<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Scrumboard", "url": "/scrumboard"},{"name": "Dashboard", "url": "/account/dashboard" , "type": "active"}]>
<#assign navlinks = [{"name" : "Scrumboard", "url" : "/scrumboard", "icon" : "fa fa-gear"},{"name" : "Dashboard", "url" : "/account/dashboard", "icon" : "fa fa-dashboard", "type" : "active"},{"name" : "Powiadomienia", "url": "/account/notifications", "icon": "fa fa-envelope"},{"name" : "Statystyki konta", "url" : "/account/statistics", "icon" : "fa fa-bar-chart-o"},{"name" : "Ustawienia powiadomień", "url": "/account/norificationsettings", "icon" : "fa fa-gears"},{"name": "Edycja danych konta", "url": "/account/edit", "icon" : "fa fa-edit"},{"name": "Usunięcie konta", "url" : "/account/delete", "icon" : "fa fa-trash-o"}]>
<body ng-app="DashboardApp">
<script>
var app = angular.module("DashboardApp", ["ngAnimate", "ngDragDrop", "scDirectives", "scControllers", "ui.bootstrap","ui.select2"])

</script>




<div class="col-lg-12">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-12">
					<#include "./templates/components/BreadCrumb.ftl">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<#include "./templates/components/NavigationBarLeft.ftl"></div>
			<div class="col-lg-9 loadin">
			<div class="well">
							<div class="row">
								<div class="col-lg-12">
									<legend>Dashboard</legend>
								</div>
							</div>
			</div>
			</div>
		</div>
	</div>
</div>
</div>
<#include "./templates/ScrumFooter.ftl">