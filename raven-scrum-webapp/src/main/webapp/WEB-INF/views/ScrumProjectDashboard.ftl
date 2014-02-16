<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign navlinks = [{"name": "Manage Project Users", "url": "/project/manage?project=${project}", "icon": "fa fa-cogs"},{"name": "Edit Project Data", "url" : "/project/edit?project=${project}", "icon": "fa fa-edit"},{"name": "Project Sprints", "url": "/project/sprints?project=${project}", "icon": "fa fa-list-ul"},{"name": "Project Statistics", "url": "/project/statistics", "icon": "fa fa-bar-chart-o"}]>
<body ng-app="ScrumBoardApp">
<script>
	var app = angular.module("ScrumBoardApp", ["ngAnimate", "scControllers", "scDirectives", "scServices", "ui.bootstrap", "ui.select2"]);

	app.factory("TemplateData", function()
	{
		return {sourcelink:"<@spring.url ''/>", project: ${project}}
	})
</script>

<div class="wrapper" ng-controller="ProjectController">
	<div class="row">
		<div class="col-md-2">
			<#include "./templates/components/NavigationBarLeft.ftl">
		</div>
		<div class="col-md-10">
			<div class="box">
				<div class="box-header">
					<h1><i class="fa fa-dashboard"></i>Project Dashboard</h1>
				</div>
				<div class="box-content-wrapper top-margins">
					<div class="box-small-wrapper pull-right">
							<div class="box-small-content">
									<a href="#" class="thumbnail">
												            <img data-src="holder.js/100%x180" alt="100%x180" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKsAAAC0CAYAAADraNxXAAAFeUlEQVR4Xu3YvUscURiF8buFgtoq2oqtNoIo/vtWKoidWIudrJ0gfiTcCXczTHZmN+menMcqupnsed/z82Z2ZvP5/Efxyw0ANjATK6AlI3YbEKsQMBsQK6Yqg4pVA5gNiBVTlUHFqgHMBsSKqcqgYtUAZgNixVRlULFqALMBsWKqMqhYNYDZgFgxVRlUrBrAbECsmKoMKlYNYDYgVkxVBhWrBjAbECumKoOKVQOYDYgVU5VBxaoBzAbEiqnKoGLVAGYDYsVUZVCxagCzAbFiqjKoWDWA2YBYMVUZVKwawGxArJiqDCpWDWA2IFZMVQYVqwYwGxArpiqDilUDmA2IFVOVQcWqAcwGxIqpyqBi1QBmA2LFVGVQsWoAswGxYqoyqFg1gNmAWDFVGVSsGsBsQKyYqgwqVg1gNiBWTFUGFasGMBsQK6Yqg4pVA5gNiBVTlUHFqgHMBsSKqcqgYtUAZgNixVRlULFqALMBsWKqMqhYNYDZgFgxVRlUrBrAbECsmKoMKlYNYDYgVkxVBhWrBjAbECumKoOKVQOYDYgVU5VBxaoBzAbEiqnKoGLVAGYDYsVUZVCxagCzAbFiqjKoWDWA2YBYMVUZVKwawGxArJiqDCpWDWA2IFZMVQYVqwYwGxArpiqDilUDmA1EY/3+/i7X19fl7e2tXF5elq2trfLw8FCen5//KHB/f7+cnJx0P1923TqNj1339PRUHh8fF//E8fFxOTg46L6fz+fl/v6+e8/61X9tnff8n/5OLNbPz89ydXVVvr6+ymw2W2Dtl7sM1zrXLQMydl37+fb2djk/Py93d3fl9fW1y7OxsdFl7L9Wf7Hq36uvpX1FYu3DqYWPYW2n7OHhYTk6OipT17UTcGdnp8PUrq0n4e7u7uIXY/h+7bq9vb3u5O5f9/Hx0Z247f3bCZx6usZivb29Laenp91J1r8NaKdVg1lPsHaS1Z9NXddOxYuLi3Jzc7M4EaeumzpZX15elmJteD1ZgzYwde85PFVX3R7U11fdIoy939h1wwztZBVrENI26hieVR+gpl6vp2v9r73/gWzq/f7lNkCsYu2eBvRPyPbBZriaMawNXr11eH9//+OT+9TTh+F9af1+c3PTe9be8iPvWVedrMPTbh2sDWJ9unB2dtbdC9c/9z+5L8M6dbK2D2Y+DfjVgFgHz1nrUlbdG/7NCbnO89nhc9b+f/M+Z/19VERjDbzzQY8sVnR9WeHFmtU3elqxouvLCi/WrL7R04oVXV9WeLFm9Y2eVqzo+rLCizWrb/S0YkXXlxVerFl9o6cVK7q+rPBizeobPa1Y0fVlhRdrVt/oacWKri8rvFiz+kZPK1Z0fVnhxZrVN3pasaLrywov1qy+0dOKFV1fVnixZvWNnlas6Pqywos1q2/0tGJF15cVXqxZfaOnFSu6vqzwYs3qGz2tWNH1ZYUXa1bf6GnFiq4vK7xYs/pGTytWdH1Z4cWa1Td6WrGi68sKL9asvtHTihVdX1Z4sWb1jZ5WrOj6ssKLNatv9LRiRdeXFV6sWX2jpxUrur6s8GLN6hs9rVjR9WWFF2tW3+hpxYquLyu8WLP6Rk8rVnR9WeHFmtU3elqxouvLCi/WrL7R04oVXV9WeLFm9Y2eVqzo+rLCizWrb/S0YkXXlxVerFl9o6cVK7q+rPBizeobPa1Y0fVlhRdrVt/oacWKri8rvFiz+kZPK1Z0fVnhxZrVN3pasaLrywov1qy+0dOKFV1fVnixZvWNnlas6Pqywos1q2/0tGJF15cVXqxZfaOnFSu6vqzwYs3qGz2tWNH1ZYUXa1bf6GnFiq4vK7xYs/pGTytWdH1Z4cWa1Td6WrGi68sKL9asvtHTihVdX1b4n4Ds4UXrUfTOAAAAAElFTkSuQmCC" style="height: 180px; width: 100%; display: block;">
											</a>
							</div>
					</div>
					<div class="row">
						
						<div class="col-md-8">
							<div class="box-small-wrapper">
								<div class="box-small-content">
									<h2>Project details</h2>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
							<div class="col-md-12">
								<div class="box-small-wrapper">
									<div class="box-small-content">
										<h2>Project users</h2>
										<div ng-user>
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