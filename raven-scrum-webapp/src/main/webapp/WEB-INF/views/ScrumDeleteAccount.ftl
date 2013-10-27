<#include "./templates/ScrumHeader.ftl">
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Dashboard", "url": "/account/dashboard"},{"name": "Delete account", "url": "/account/delete" , "type": "active"}]>
<#assign navlinks = [{"name" : "Scrumboard", "url" : "/scrumboard", "icon" : "fa fa-gear"},{"name" : "Dashboard", "url" : "/account/dashboard", "icon" : "fa fa-dashboard"},{"name" : "Powiadomienia", "url": "/account/notifications", "icon": "fa fa-envelope-o"},{"name" : "Statystyki konta", "url" : "/account/statistics", "icon" : "fa fa-bar-chart-o"},{"name" : "Ustawienia powiadomień", "url": "/account/norificationsettings", "icon" : "fa fa-gears"},{"name": "Edycja danych konta", "url": "/account/edit", "icon" : "fa fa-edit"},{"name": "Usunięcie konta", "url" : "/account/delete", "icon" : "fa fa-trash-o", "type" : "active"}]>
<body  ng-app="deleteApp">
<script type="text/javascript">
	
	var app = angular.module("deleteApp", ["ui.directives","ngAnimate","scDirectives","scControllers","ui.bootstrap"])

	app.controller("DeleteController", function($scope, $http, $element, $animate)
	{
			$scope.validatePassword = function()
			{
				$http.post("<@spring.url '/rest/validate/password'/>", angular.toJson({"password": $scope.deleteaccount.password.$viewValue })).success(function(data,status, headers, cfg){
					$scope.deleteaccount.password.$setValidity('badcredentials', data.credentials)
				}).error(function(data,status,headers,cfg){
					$scope.deleteaccount.password.$setValidity('badcredentials', false)
				})
				
			}

			$scope.submitDelete = function()
			{
				$scope.deletemessage = {submitsuccess: false, submiterror: false, toggle: false};
				$scope.deletemessage.submitsuccess = true;
			}
	})
</script>

<div class="wrapper">
	<div class="col-lg-12">
		<div class="row">
			<div class="col-lg-12">
				<#include "./templates/components/Breadcrumb.ftl"></div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<#include "./templates/components/NavigationBarLeft.ftl">
			</div>
				<div class="col-lg-9 loadin">
					<div class="well">
					<div class="row">
						<div class="col-lg-12">
							<legend>Delete account</legend>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="well">
								<div class="row">
											<div class="col-lg-12">
												<div class="well-header">
													<span>Account deletion procedure</span>
												</div>
											</div>
								</div>
								<div class="row">
									<div class="col-lg-10 col-lg-offset-1">
									<blockquote class="text-center">Usunięcie konta przez użykownika prowadzi do jego zablokowania. Aby usunąć konto wraz ze wszelkimi powiązaniami należy skontaktować się z administratorem. Wpisz hasło powiązane z kontem, potwierdzenie usunięcia / zablokowania konta zostanie wysłane na email</blockquote>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-11">
										<form name="deleteaccount" class="form-horizontal" ng-controller="DeleteController" ng-submit="submitDelete()" novalidate>
												<div class="form-group">
													<div class="col-lg-9 col-lg-offset-2">
															<div class="ng-cloak" ng-class="{'alert alert-danger': deletemessage.submiterror, 'alert alert-success': deletemessage.submitsuccess}" ng-show="deletemessage.toggle">
																<div class="row">
																	<div class="col-lg-2">
																		<i class="" ng-class="{'icon-ok icon-3x pull-right': deletemessage.submitsuccess, 'icon-remove icon-3x pull-right': deletmessage.submiterror}"></i>
																	</div>
																	<div class="col-lg-10">
																		<button type="button" class="close" ng-click="deletemessage.toggle = !deletemessage.toggle">×</button>
																		<h4 ng-show="deletemessage.submitsuccess">Submit successful!</h4>
																		<h4 ng-show="deletemessage.submiterror">Submit error!</h4>
																		<p ng-show="deletemessage.submitsuccess">Your account has been successfully deleted</p>
																		<p ng-show="deletemessage.submiterror">Error during form submittion check again your data or contact admin</p>
																	</div>
																</div>
															</div>
													</div>
												</div>											
												<div class="form-group" ng-class="{'has-success': deleteaccount.password.$valid && deleteaccount.password.$dirty && !deleteaccount.password.$focused , 'has-error' : deleteaccount.password.$invalid && !deleteaccount.password.$focused}">
													<div class="col-lg-5 col-lg-offset-4">
														<label class="control-label" for="password">Account password</label>
														<input type="password" name="password" ng-model="password" class="form-control" ng-blur="validatePassword()" ng-focused></input>
														<span class="help-block" ng-show="deleteaccount.password.$error.badcredentials && !deleteaccount.password.$focused">Provided password is invalid</span>
														<span class="help-block" ng-show="deleteaccount.password.$valid && deleteaccount.password.$dirty && !deleteaccount.password.$focused"><i class="icon-ok"></i></span>
													</div>
												</div>
												<div class="form-group" ng-class="{'has-success': checkbox , 'has-error': !checkbox && deleteaccount.checkbox.$dirty }">
													<div class="col-lg-5 col-lg-offset-4">
														<label class="control-label" for="checkbox">Account delete confirmation</label>
														</br>
														<input type="checkbox" name="checkbox" ng-model="checkbox" required></input>
														<span class="help-block" ng-hide="checkbox">Account delete confirmation is required</span>
														<span class="help-block" ng-show="checkbox"><i class="icon-ok"></i></span>
													</div>
												</div>
												<div class="form-group">
													<div class="col-lg-2 col-lg-offset-4">
														<button type="submit" class="btn btn-primary btn-sm">Cancel</button>
													</div>
													<div class="col-lg-2 col-lg-offset-1">
														<button type="submit" class="btn btn-primary btn-sm pull-right">Submit</button>
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
			</div>
		</div>
	</div>
	<#include "./templates/ScrumFooter.ftl">