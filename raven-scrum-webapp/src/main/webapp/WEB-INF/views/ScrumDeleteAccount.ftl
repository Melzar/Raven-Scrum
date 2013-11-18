<#include "./templates/ScrumHeader.ftl">
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Dashboard", "url": "/account/dashboard"},{"name": "Delete account", "url": "/account/delete" , "type": "active"}]>
<#assign navlinks = [{"name" : "Dashboard", "url" : "/account/dashboard", "icon" : "fa fa-dashboard"},{"name" : "Powiadomienia", "url": "/account/notifications", "icon": "fa fa-envelope-o"},{"name" : "Statystyki konta", "url" : "/account/statistics", "icon" : "fa fa-bar-chart-o"},{"name" : "Ustawienia powiadomień", "url": "/account/norificationsettings", "icon" : "fa fa-gears"},{"name": "Edycja danych konta", "url": "/account/edit", "icon" : "fa fa-edit"},{"name": "Usunięcie konta", "url" : "/account/delete", "icon" : "fa fa-trash-o", "type" : "active"}]>
<body  ng-app="ScrumBoardApp">
<script type="text/javascript">
	var app = angular.module("ScrumBoardApp", ["ui.directives","ngAnimate","scDirectives","scControllers","ui.bootstrap"])

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
				$scope.deleteaccount.submitted = true;
				if(!$scope.deleteaccount.$invalid)
				{
					$scope.deletemessage.submitsuccess = true;
					console.log("submit todo")
				}
			}

			$scope.hideMessage = function()
			{
				$scope.deletemessage.submitsuccess = false;
				$scope.deletemessage.submiterror = false;
				$scope.deletemessage.toggle = false;
			}
	})
</script>

<div class="wrapper">
	<div class="row">
		<div class="col-md-2">
			<#include "./templates/components/NavigationBarLeft.ftl">
		</div>
		<div class="col-md-10">
			<div class="box">
				<div class="box-header">
					<h1><i class="fa fa-trash-o"></i>Delete account</h1>
				</div>
				<div class="box-content-wrapper">
					<div class="box-small-wrapper">
						<div class="box-small-content">
							<h2>Delete account procedure</h2>
								<form name="deleteaccount" class="form-horizontal" ng-controller="DeleteController" ng-submit="submitDelete()" novalidate>
										<div class="alert" ng-class="{'animated fadeIn alert alert-success': deletemessage.submitsuccess, 'animated fadeIn alert alert-danger': deletemessage.submiterror}" ng-if="deletemessage.submitsuccess || deletemessage.submiterror">
								           <button type="button" class="close" ng-click="hideMessage()">×</button>
								              <div class="alert-icon pull-left">
								                 <i class="" ng-class="{'fa fa-check fa-3x' : deletemessage.submitsuccess, 'fa fa-times fa-3x' : deletemessage.submiterror}"></i>
								              </div>
								              <div class="alert-details">
								                <h4 ng-if="deletemessage.submitsuccess">Submit successful</h4>
								                <h4 ng-if="deletemessage.submiterror">Submit error!</h4>
								                <p ng-if="deletemessage.submitsuccess">Your account has been successfully deleted</p>
								                <p ng-if="deletemessage.submiterror">There were internal error during form submition, check again your data or try again later</p>
								              </div>
								        </div>
								        <div class="col-md-10 col-md-offset-1">
											<blockquote class="text-center">Usunięcie konta przez użykownika prowadzi do jego zablokowania. Aby usunąć konto wraz ze wszelkimi powiązaniami należy skontaktować się z administratorem. Wpisz hasło powiązane z kontem, potwierdzenie usunięcia / zablokowania konta zostanie wysłane na email</blockquote>
										</div>											
										<div class="form-group" ng-class="{'has-success': deleteaccount.password.$valid && !deleteaccount.password.$focused , 'has-error' : deleteaccount.password.$error.badcredentials && !deleteaccount.password.$focused}">
													<div class="col-md-4 col-md-offset-4">
														<label class="control-label" for="password">Account password</label>
														<input type="password" name="password" ng-model="password" class="form-control" ng-blur="validatePassword()" ng-focused required></input>
														<span class="help-block no-margin" ng-if="deleteaccount.password.$error.badcredentials && !deleteaccount.password.$focused">Provided password is invalid</span>
														<span class="help-block no-margin" ng-if="deleteaccount.password.$valid && !deleteaccount.password.$focused">
															<label class="input-lg-icon fa fa-check"></label>
														</span>
													</div>
												</div>
												<div class="form-group" ng-class="{'has-success': checkbox , 'has-error': !checkbox && deleteaccount.checkbox.$dirty }">
													<div class="col-md-4 col-md-offset-4">
														<label class="control-label" for="checkbox">Account delete confirmation</label><br/>
														<input type="checkbox" name="checkbox" ng-model="checkbox" required></input>
														<span class="help-block no-margin" ng-if="!checkbox">Account delete confirmation is required</span>
														<span class="help-block no-margin" ng-if="checkbox"><i class="icon-ok"></i></span>
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-4 col-md-offset-4">
														<button type="submit" class="btn btn-primary btn-sm pull-right">Submit</button>
														<button type="submit" class="btn btn-primary btn-sm">Cancel</button>
													</div>
												</div>
												<div class="form-group" ng-if="deleteaccount.$invalid && deleteaccount.submitted">
									                <div class="col-md-offset-4 col-md-4">
									                  <p class="text-danger">Formularz nie jest wypełniony poprawnie, sprawdz wprowadzone dane i spróbuj ponownie.</p>
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
<#include "./templates/ScrumFooter.ftl">