<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Scrumboard", "url": "/scrumboard"},{"name": "Dashboard", "url": "/account/dashboard" , "type": "active"}]>
<#assign navlinks = [{"name" : "Scrumboard", "url" : "/scrumboard", "icon" : "icon-gear"},{"name" : "Dashboard", "url" : "/account/dashboard", "icon" : "icon-dashboard", "type" : "active"},{"name" : "Powiadomienia", "url": "/account/notifications", "icon": "icon-envelope"},{"name" : "Statystyki konta", "url" : "/account/statistics", "icon" : "icon-bar-chart"},{"name" : "Ustawienia powiadomień", "url": "/account/norificationsettings", "icon" : "icon-gears"},{"name": "Edycja danych konta", "url": "/account/edit", "icon" : "icon-edit"},{"name": "Usunięcie konta", "url" : "/account/delete", "icon" : "icon-trash"}]>

<script>
 $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip({
                  'placement': 'bottom'
                 })
 });
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
				<legend>Dashboard</legend>

			</div>
		</div>
	</div>
</div>
</div>
<#include "./templates/ScrumFooter.ftl">