<#include "./templates/ScrumHeader.ftl">
<#include "./templates/components/NavigationBar.ftl">
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
					Akcje projektu <i class="icon-caret-down"></i>
				</a>
			</li>
			<li>
				<a>Backlog projektu</a>
			</li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	
var app = angular.module("ScrumBoardApp", ["ngAnimate", "ngDragDrop"])

app.controller("ScrumBoardCtrl", function($scope){
	$scope.todo = [{"title": "Item title", "description" : "Item description"}];
	$scope.progress = [];
	$scope.uat = [];
	$scope.done = [];

})

</script>

<div class="wrapper">
<div class="col-lg-12" ng-app="ScrumBoardApp" ng-controller="ScrumBoardCtrl">
	<div class="row">
		<div class="col-lg-6">
			<div class="row">
				<div class="col-lg-12">
					<legend>Scrumboard</legend>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="row">
				<div class="col-lg-6"></div>
				<div class="col-lg-6">
					<a class="btn btn-primary btn-lg pull-right"> <i class="icon-plus"></i>
						Dodaj zadanie
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row loadin">
		<div class="col-lg-6">
			<div class="row top-margin">
				<div class="col-lg-7">
					<div class="alert alert-info">
						<div class="row">
							<div class="col-lg-2">
								<i class="icon-time icon-3x"></i>
							</div>
							<div class="col-lg-10">
								<div class="row">
									<div class="col-lg-12">
										<span> <b>Sprint 1</b>
										</span>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<span>01.01.2011 - 07.01.2011 (x - d.left)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="row top-margin">
				<div class="col-lg-12">
					<div class="alert alert-info pull-right">
						<div class="col-lg-1">
							<i class="icon-info icon-3x pull-left"></i>
						</div>
						<div class="col-lg-10">
							<div class="row">
								<div class="col-lg-12">
									<span>Tasks left in sprint overall : 10</span>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<span>Your tasks left : 10</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		{{todo}}
	{{progress}}
	{{uat}}
	{{done}}
	<div class="row top-margin loadin">
		<div class="col-lg-3">
			<div class="scrum-columns columns-top">TODO</div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-top">Doing...</div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-top">UAT</div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-top">Done</div>
		</div>
	</div>
	<div class="row loadin">
		<div class="col-lg-12">
			<div class="scrum-columns columns-header">
				<a class="subtask-title">TYTUŁ ZADANIA</a>
			</div>
		</div>
	</div>
	<div class="row loadin" id="dupa">
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([ng-model=todo])'}" jqyoui-droppable="{multiple:true}" ng-model="todo">
				<div class="subtask" ng-model="todo" jqyoui-draggable="{index: {{$index}}, animate:true}" ng-repeat="task in todo" data-jqyoui-options="{revert: 'invalid', containment: '#dupa' }" data-drag="true" ng-hide="!task.title">
					<div class="row">
						<div class="col-lg-3">
									<div class="subtask-type">
										<i class="icon-gear icon-2x"></i>
									</div>
						</div>
						<div class="col-lg-7">
							<div class="row">
									<a class="subtask-title subtask-text text-limit-1">{{task.title}}</a>
							</div>
							<div class="row">
									<span class="subtask-description subtask-text text-limit-2">{{task.description}}</span>
							</div>
						</div>
						<div class="col-lg-2">
							<div class="subtask-avatar pull-right"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding" data-drop="true" ng-model="progress" data-jqyoui-options="{accept:'.subtask:not([ng-model=progress])'}" jqyoui-droppable="{multiple:true}">
				<div class="subtask"  ng-repeat="task in progress" ng-show="task.title" data-drag="true" ng-model="progress" data-jqyoui-options="{revert: 'invalid', containment: '#dupa'}" jqyoui-draggable="{index: {{$index}}, animate:true}">
					<div class="row">
						<div class="col-lg-3">
									<div class="subtask-type">
										<i class="icon-gear icon-2x"></i>
									</div>
						</div>
						<div class="col-lg-7">
							<div class="row">
									<a class="subtask-title subtask-text text-limit-1">TYTUL PODZADANIA</a>
							</div>
							<div class="row">
									<span class="subtask-description subtask-text text-limit-2">Subtask description</span>
							</div>
						</div>
						<div class="col-lg-2">
							<div class="subtask-avatar pull-right"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([ng-model=uat])'}" jqyoui-droppable="{multiple:true}" ng-model="uat">
				<div class="subtask" ng-model="uat" jqyoui-draggable="{index: {{$index}}, animate:true}" ng-repeat="task in uat" data-jqyoui-options="{revert: 'invalid', containment: '#dupa'}" data-drag="true" ng-hide="!task.title">
					<div class="row">
						<div class="col-lg-3">
									<div class="subtask-type">
										<i class="icon-gear icon-2x"></i>
									</div>
						</div>
						<div class="col-lg-7">
							<div class="row">
									<a class="subtask-title subtask-text text-limit-1">TYTUL PODZADANIA</a>
							</div>
							<div class="row">
									<span class="subtask-description subtask-text text-limit-2">Subtask description</span>
							</div>
						</div>
						<div class="col-lg-2">
							<div class="subtask-avatar pull-right"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([ng-model=done])'}" jqyoui-droppable="{multiple:true}" ng-model="done">
				<div class="subtask" ng-model="done" jqyoui-draggable="{index: {{$index}}, animate:true}" ng-repeat="task in done" data-jqyoui-options="{revert: 'invalid', containment: '#dupa'}" data-drag="true" ng-hide="!task.title">
					<div class="row">
						<div class="col-lg-3">
									<div class="subtask-type">
										<i class="icon-gear icon-2x"></i>
									</div>
						</div>
						<div class="col-lg-7">
							<div class="row">
									<a class="subtask-title subtask-text text-limit-1">TYTUL PODZADANIA</a>
							</div>
							<div class="row">
									<span class="subtask-description subtask-text text-limit-2">Subtask description</span>
							</div>
						</div>
						<div class="col-lg-2">
							<div class="subtask-avatar pull-right"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row loadin">
		<div class="col-lg-12">
			<div class="scrum-columns columns-header">
				<a class="subtask-title">Inne</a>
			</div>
		</div>
	</div>
	<div class="row loadin">
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding"></div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding"></div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding"></div>
		</div>
		<div class="col-lg-3">
			<div class="scrum-columns columns-inside-padding"></div>
		</div>
	</div>
</div>
</div>
<#include "./templates/ScrumFooter.ftl">