<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Dashboard", "url": "/account/dashboard"},{"name": "Edit Account Data", "url": "/account/edit" , "type": "active"}]>
<#assign navlinks = [{"name" : "Scrumboard", "url" : "/scrumboard", "icon" : "icon-gear"},{"name" : "Dashboard", "url" : "/account/dashboard", "icon" : "icon-dashboard"},{"name" : "Powiadomienia", "url": "/account/notifications", "icon": "icon-envelope"},{"name" : "Statystyki konta", "url" : "/account/statistics", "icon" : "icon-bar-chart"},{"name" : "Ustawienia powiadomień", "url": "/account/norificationsettings", "icon" : "icon-gears"},{"name": "Edycja danych konta", "url": "/account/edit", "icon" : "icon-edit", "type" : "active"},{"name": "Usunięcie konta", "url" : "/account/delete", "icon" : "icon-trash"}]>

<script type="text/javascript">
	
var app = angular.module("editApp", ["ngAnimate", "ui.directives", "scDirectives"]);

app.controller("PasswordController", function($scope, $element, $http){
		$scope.changePassword = function ()
		{			
			$scope.passwordmessage = {submitsuccess: false, submiterror: false, toggle : false};
			if(!$scope.editpassword.passwordrepeat.$error.minlength && !$scope.editpassword.passwordrepeat.$error.equalslogin && !$scope.editpassword.passwordrepeat.$error.notmatch)
			{
				$http({
					url: "<@spring.url '/account/edit/password' />",
					method: "POST",
					data: $element.serialize(),
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}).success(function(data,status,headers,cfg){
					 $scope.passwordmessage.toggle = true;
					(data.success) ? $scope.passwordmessage.submitsuccess = true : $scope.passwordmessage.submiterror = true;
				}).error(function(data,status,headers,cfg){
					$scope.passwordmessage.toggle = true;
					$scope.passwordmessage.submiterror = true;
				})
			}
		}
})

app.controller("EmailController", function($scope, $element, $http){
		$scope.changeEmail = function ()
		{
			$scope.emailmessage = {submitsuccess: false, submiterror: false, toggle : false};
			if(!$scope.editemail.emailrepeat.$error.pattern && !$scope.editemail.emailrepeat.$error.unique && !$scope.editemail.emailrepeat.$error.notmatch)
			{
				$http({
					url: "<@spring.url '/account/edit/email' />",
					method: "POST",
					data: $element.serialize(),
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}).success(function(data,status,headers,cfg){
					$scope.emailmessage.toggle = true;
					(data.success) ? $scope.emailmessage.submitsuccess = true : $scope.emailmessage.submiterror = true;
				}).error(function(data,status,headers,cfg){
					$scope.emailmessage.toggle = true;
					$scope.emailmessage.submiterror = true;
				})
			}
		}
})

</script>

<div class="wrapper" ng-app="editApp">
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
				<#include "./templates/components/NavigationBarLeft.ftl">
			</div>
			<div class="col-lg-9 loadin">
				<div class="row">
					<div class="col-lg-12">
						<legend>Edit account data</legend>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<div class="row">
								<div class="col-lg-12">
									<div class="well-header">
										<span>Edit password</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-11">
									<form name="editpassword" ng-controller="PasswordController" class="form-horizontal" ng-submit="changePassword()" novalidate>
										<div class="form-group">
											<div class="col-lg-9 col-lg-offset-2">
												<div class="" ng-class="{'alert alert-danger': passwordmessage.submiterror, 'alert alert-success': passwordmessage.submitsuccess}" ng-show="passwordmessage.toggle" >
													<div class="row">
														<div class="col-lg-2"> <i class="" ng-class="{'icon-ok icon-3x pull-right': passwordmessage.submitsuccess, 'icon-remove icon-3x pull-right': passwordmessage.submiterror}"></i>
														</div>
														<div class="col-lg-10">
															<button type="button" class="close" ng-click="passwordmessage.toggle = !passwordmessage.toggle">×</button>
															<h4 ng-show="passwordmessage.submitsuccess">Submit successful</h4>
															<h4 ng-show="passwordmessage.submiterror">Submit error!</h4>
															<p  ng-show="passwordmessage.submitsuccess">Password successfully changed</p>
															<p  ng-show="passwordmessage.submiterror">
																Error during form submission, check again your data or try again later
															</p>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group" ng-class="{'has-error': editpassword.passwordrepeat.$invalid && !editpassword.passwordrepeat.$focused, 'has-success': editpassword.passwordrepeat.$valid && editpassword.passwordrepeat.$dirty && !editpassword.passwordrepeat.$focused}">
												<div class="col-lg-5 col-lg-offset-4">
														<label class="control-label">Password</label>
														<input type="password" ng-model="password" id="password" name="password" class="form-control"/>
														<span class="help-block" ng-show="editpassword.passwordrepeat.$error.minlength && !editpassword.passwordrepeat.$focused">Hasło musi miec minimum 4 znaki</span>
             											<span class="help-block" ng-show="editpassword.passwordrepeat.$error.equalslogin && !editpassword.passwordrepeat.$focused">Hasło nie moze być takie samo jak login</span>
             											<span class="help-block" ng-show="editpassword.passwordrepeat.$error.notmatch && !editpassword.passwordrepeat.$focused">Hasła się nie zgadzają</span>
												</div>
										</div>
										<div class="form-group" ng-class="{'has-error': editpassword.passwordrepeat.$invalid && !editpassword.passwordrepeat.$focused, 'has-success': editpassword.passwordrepeat.$valid && editpassword.passwordrepeat.$dirty &&!editpassword.passwordrepeat.$focused}">
												<div class="col-lg-5 col-lg-offset-4">
														<label class="control-label">Password repeat</label>
														<input type="password" ng-model="passwordrepeat" ng-validate-password='{"passwordid" : "password", "login" : "<@security.authentication property="name"/>" }' name="passwordrepeat" class="form-control" ng-focused />
														<span class="help-block" ng-show="editpassword.passwordrepeat.$error.minlength && !editpassword.passwordrepeat.$focused">Hasło musi miec minimum 4 znaki</span>
             											<span class="help-block" ng-show="editpassword.passwordrepeat.$error.equalslogin && !editpassword.passwordrepeat.$focused">Hasło nie moze być takie samo jak login</span>
             											<span class="help-block" ng-show="editpassword.passwordrepeat.$error.notmatch && !editpassword.passwordrepeat.$focused">Hasła się nie zgadzają</span>
												</div>
										</div>
										<div class="form-group">
											<div class="col-lg-2 col-lg-offset-4">
												<button class="btn btn-primary btn-sm">Anuluj</button>
											</div>
											<div class="col-lg-2 col-lg-offset-1">
												<button type="submit" class="btn btn-primary btn-sm pull-right">Zapisz</button>
											</div>
										</div>
        							</form>
								</div>
							</div>
						</div>
					</div>
				</div>
					<div class="row">
					<div class="col-lg-12">
						<div class="well">
							<div class="row">
								<div class="col-lg-12">
									<div class="well-header">
										<span>Edit email</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-7 col-lg-offset-3">
									<blockquote>Potwierdzenie zmiany emaila zostanie wyslane na wybrany <b>nowy</b> adres</blockquote>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-11">
									<form name="editemail" ng-controller="EmailController" class="form-horizontal" ng-submit="changeEmail()" novalidate>
											<div class="col-lg-9 col-lg-offset-2">
												<div class="" ng-class="{'alert alert-danger': emailmessage.submiterror, 'alert alert-success': emailmessage.submitsuccess}" ng-show="emailmessage.toggle"  >
													<div class="row">
														<div class="col-lg-2"> <i class="" ng-class="{'icon-ok icon-3x pull-right': emailmessage.submitsuccess, 'icon-remove icon-3x pull-right': emailmessage.submiterror}"></i>
														</div>
														<div class="col-lg-10">
															<button type="button" class="close" ng-click="emailmessage.toggle = !emailmessage.toggle">×</button>
															<h4 ng-show="emailmessage.submitsuccess">Submit successful</h4>
															<h4 ng-show="emailmessage.submiterror">Submit error!</h4>
															<p  ng-show="emailmessage.submitsuccess">Email successfully changed</p>
															<p  ng-show="emailmessage.submiterror">
																Error during form submission, check again your data or try again later
															</p>
														</div>
													</div>
												</div>
											</div>

										<div class="form-group" ng-class="{'has-error' : editemail.emailrepeat.$invalid && !editemail.emailrepeat.$focused, 'has-success' : editemail.emailrepeat.$valid && editemail.emailrepeat.$dirty && !editemail.emailrepeat.$focused}">
												<div class="col-lg-5 col-lg-offset-4">
														<label class="control-label">Email</label>
														<input type="text" ng-model="email" id="email" name="email" class="form-control"/>
														<span class="help-block" ng-show="editemail.emailrepeat.$error.notmatch && !editemail.emailrepeat.$focused">Emails do not match</span>
														<span class="help-block" ng-show="editemail.emailrepeat.$error.unique && !editemail.emailrepeat.$focused">Podany email jest powiązany z kontem w systemie</span>
														<span class="help-block" ng-show="editemail.emailrepeat.$error.pattern && !editemail.emailrepeat.$focused">Nieprawidłowy format email</span>
														<span class="help-block" ng-show="editemail.emailrepeat.$valid && editemail.emailrepeat.$dirty && !editemail.emailrepeat.$focused">
															<i class="icon-ok"></i>
														</span>
												</div>
												
										</div>
										<div class="form-group" ng-class="{'has-error' : editemail.emailrepeat.$invalid && !editemail.emailrepeat.$focused, 'has-success' : editemail.emailrepeat.$valid && editemail.emailrepeat.$dirty && !editemail.emailrepeat.$focused}">
												<div class="col-lg-5 col-lg-offset-4">
														<label class="control-label">Email repeat</label>
														<input type="text" ng-model="emailrepeat" ng-validate-email='{"emailid" : "email", "emailurl" : "<@spring.url "/rest/validate/email"/>"}' name="emailrepeat" class="form-control"  ng-focused/>
														<span class="help-block" ng-show="editemail.emailrepeat.$error.notmatch && !editemail.emailrepeat.$focused">Emails do not match</span>
														<span class="help-block" ng-show="editemail.emailrepeat.$error.unique && !editemail.emailrepeat.$focused">Podany email jest powiązany z kontem w systemie</span>
														<span class="help-block" ng-show="editemail.emailrepeat.$error.pattern && !editemail.emailrepeat.$focused">Nieprawidłowy format email</span>
														<span class="help-block" ng-show="editemail.emailrepeat.$valid && editemail.emailrepeat.$dirty && !editemail.emailrepeat.$focused">
															<i class="icon-ok"></i>
														</span>
												</div>
										</div>
										<div class="form-group">
											<div class="col-lg-2 col-lg-offset-4">
												<button class="btn btn-primary btn-sm">Anuluj</button>
											</div>
											<div class="col-lg-2 col-lg-offset-1">
												<button class="btn btn-primary btn-sm pull-right">Zapisz</button>
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
</div>



<#include "./templates/ScrumFooter.ftl" >