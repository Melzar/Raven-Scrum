<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Dashboard", "url": "/account/dashboard"},{"name": "Edit Account Data", "url": "/account/edit" , "type": "active"}]>
<#assign navlinks = [{"name" : "Dashboard", "url" : "/account/dashboard", "icon" : "fa fa-dashboard"}, {"name": "Edit account data", "url": "/account/edit", "icon" : "fa fa-edit", "type" : "active"},{"name": "Delete account", "url" : "/account/delete", "icon" : "fa fa-trash-o"}]>
<body ng-app="ScrumBoardApp">
<script type="text/javascript">
	
var app = angular.module("ScrumBoardApp", ["ngAnimate", "scDirectives","scControllers", "ui.bootstrap"]);

app.controller("PasswordController", function($scope, $element, $http){
		$scope.changePassword = function ()
		{			
			$scope.passwordmessage = {submitsuccess: false, submiterror: false, toggle : false};
			$scope.editpassword.submitted = true;
			if(!$scope.editpassword.$invalid)
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

		$scope.hideMessage = function()
		{
			$scope.passwordmessage.submitsuccess = false;
			$scope.passwordmessage.submiterror = false;
			$scope.passwordmessage.toggle = false;		
		}
})

app.controller("EmailController", function($scope, $element, $http){
		$scope.changeEmail = function ()
		{
			$scope.emailmessage = {submitsuccess: false, submiterror: false, toggle : false};
			$scope.editemail.submitted = true;
			console.log($element)
			console.log($scope)
			if(!$scope.editemail.$invalid)
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

		$scope.hideMessage = function()
		{
			$scope.emailmessage.submitsuccess = false;
			$scope.emailmessage.submiterror = false;
			$scope.emailmessage.toggle = false;		
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
				<h1><i class="fa fa-edit"></i>Edit account data</h1>
			</div>
			<div class="box-content-wrapper">
				<div class="box-small-wrapper">
					<div class="box-small-content">
						<h2>Edit Password</h2>
						<form ng-controller="PasswordController" name="editpassword" class="form-horizontal" ng-submit="changePassword()" novalidate>
										<div class="alert" ng-class="{'animated fadeIn alert alert-success': passwordmessage.submitsuccess, 'animated fadeIn alert alert-danger': passwordmessage.submiterror}" ng-if="passwordmessage.submitsuccess || passwordmessage.submiterror">
								           <button type="button" class="close" ng-click="hideMessage()">×</button>
								              <div class="alert-icon pull-left">
								                 <i class="" ng-class="{'fa fa-check fa-3x' : passwordmessage.submitsuccess, 'fa fa-times fa-3x' : passwordmessage.submiterror}"></i>
								              </div>
								              <div class="alert-details">
								                <h4 ng-if="passwordmessage.submitsuccess">Submit successful</h4>
								                <h4 ng-if="passwordmessage.submiterror">Submit error!</h4>
								                <p ng-if="passwordmessage.submitsuccess">Password successfully changed</p>
								                <p ng-if="passwordmessage.submiterror">There were internal error during form submition, check again your data or try again later</p>
								              </div>
								        </div>
										<div class="form-group" ng-class="{'has-error': (editpassword.passwordrepeat.$error.minlength || editpassword.passwordrepeat.$error.equalslogin || editpassword.passwordrepeat.$error.notmatch) && !editpassword.passwordrepeat.$focused, 'has-success': editpassword.passwordrepeat.$valid && !editpassword.passwordrepeat.$focused}">
												<div class="col-md-4 col-md-offset-4">
														<label class="control-label">Password</label>
														<input type="password" ng-model="password" id="password" name="password" class="form-control" required/>
														<span class="help-block no-margin" ng-show="editpassword.passwordrepeat.$error.minlength && !editpassword.passwordrepeat.$focused">The minimum lenght of password is 4</span>
             											<span class="help-block no-margin" ng-show="editpassword.passwordrepeat.$error.equalslogin && !editpassword.passwordrepeat.$focused">Password cannot be the same as login</span>
             											<span class="help-block no-margin" ng-show="editpassword.passwordrepeat.$error.notmatch && !editpassword.passwordrepeat.$focused">Passwords do not match</span>
             											<span class="help-block no-margin" ng-if="editpassword.passwordrepeat.$valid && !registration.passwordrepeat.$focused"> 
                    										<label class="input-lg-icon fa fa-check"></label>
                 										</span>
												</div>
										</div>
										<div class="form-group" ng-class="{'has-error': (editpassword.passwordrepeat.$error.minlength || editpassword.passwordrepeat.$error.equalslogin || editpassword.passwordrepeat.$error.notmatch) && !editpassword.passwordrepeat.$focused, 'has-success': editpassword.passwordrepeat.$valid &&!editpassword.passwordrepeat.$focused}">
												<div class="col-md-4 col-md-offset-4">
														<label class="control-label">Password repeat</label>
														<input type="password" ng-model="passwordrepeat" ng-validate-password='{"passwordid" : "password", "loginid" : "login"}' name="passwordrepeat" class="form-control" ng-focused required/>
														<input type="hidden" id="login" ng-model="login" name="login" value='<@security.authentication property="name"/>'>
														<span class="help-block no-margin" ng-show="editpassword.passwordrepeat.$error.minlength && !editpassword.passwordrepeat.$focused">The minimum lenght of password is 4</span>
             											<span class="help-block no-margin" ng-show="editpassword.passwordrepeat.$error.equalslogin && !editpassword.passwordrepeat.$focused">Password cannot be the same as login</span>
             											<span class="help-block no-margin" ng-show="editpassword.passwordrepeat.$error.notmatch && !editpassword.passwordrepeat.$focused">Passwords do not match</span>
             											<span class="help-block no-margin" ng-if="editpassword.passwordrepeat.$valid && !registration.passwordrepeat.$focused"> 
                    										<label class="input-lg-icon fa fa-check"></label>
                 										</span>
												</div>
										</div>
										<div class="form-group">
											<div class="col-md-4 col-md-offset-4">
												<button type="submit" class="btn btn-primary btn-sm pull-right">Save</button>
												<button class="btn btn-primary btn-sm">Cancel</button>
											</div>
										</div>
										<div class="form-group" ng-if="editpassword.$invalid && editpassword.submitted">
							                <div class="col-lg-offset-4 col-lg-4">
							                  <p class="text-danger">Form data is not correct, check provided data and try again.</p>
							                </div>
            						    </div>
        							</form>
					</div>
				</div>
				<div class="divider-vertical">
				</div>
				<div class="box-small-wrapper">
					<div class="box-small-content">
						<h2>Edit email</h2>
												
							<form name="editemail" ng-controller="EmailController" class="form-horizontal" ng-submit="changeEmail()" novalidate>
										<div class="alert" ng-class="{'animated fadeIn alert alert-success': emailmessage.submitsuccess, 'animated fadeIn alert alert-danger': emailmessage.submiterror}" ng-if="emailmessage.submitsuccess || emailmessage.submiterror">
								           <button type="button" class="close" ng-click="hideMessage()">×</button>
								              <div class="alert-icon pull-left">
								                 <i class="" ng-class="{'fa fa-check fa-3x' : emailmessage.submitsuccess, 'fa fa-times fa-3x' : emailmessage.submiterror}"></i>
								              </div>
								              <div class="alert-details">
								                <h4 ng-if="emailmessage.submitsuccess">Submit successful</h4>
								                <h4 ng-if="emailmessage.submiterror">Submit error!</h4>
								                <p ng-if="emailmessage.submitsuccess">Email successfully changed</p>
								                <p ng-if="emailmessage.submiterror">There were internal error during form submition, check again your data or try again later</p>
								              </div>
								        </div>
								        <div class="col-md-7 col-md-offset-3">
										</div>	
										<div class="form-group" ng-class="{'has-error' : (editemail.emailrepeat.$error.notmatch || editemail.emailrepeat.$error.unique || editemail.emailrepeat.$error.pattern) && !editemail.emailrepeat.$focused, 'has-success' : editemail.emailrepeat.$valid && !editemail.emailrepeat.$focused}">
												<div class="col-md-4 col-md-offset-4">
														<label class="control-label">Email</label>
														<input type="text" ng-model="email" id="email" name="email" class="form-control" required/>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$error.notmatch && !editemail.emailrepeat.$focused">Emails do not match</span>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$error.unique && !editemail.emailrepeat.$focused">Provided email is associated with another account</span>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$error.pattern && !editemail.emailrepeat.$focused">Incorrect email format</span>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$valid && !editemail.emailrepeat.$focused">
															<label class="input-lg-icon fa fa-check"></label>
														</span>
												</div>
												
										</div>
										<div class="form-group" ng-class="{'has-error' : (editemail.emailrepeat.$error.notmatch || editemail.emailrepeat.$error.unique || editemail.emailrepeat.$error.pattern) && !editemail.emailrepeat.$focused, 'has-success' : editemail.emailrepeat.$valid && !editemail.emailrepeat.$focused}">
												<div class="col-md-4 col-md-offset-4">
														<label class="control-label">Email repeat</label>
														<input type="text" ng-model="emailrepeat" ng-validate-email='{"emailid" : "email", "emailurl" : "<@spring.url "/rest/validate/email"/>"}' name="emailrepeat" class="form-control" ng-focused required/>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$error.notmatch && !editemail.emailrepeat.$focused">Emails do not match</span>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$error.unique && !editemail.emailrepeat.$focused">Provided email is associated with another account</span>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$error.pattern && !editemail.emailrepeat.$focused">Incorrect email format</span>
														<span class="help-block no-margin" ng-show="editemail.emailrepeat.$valid && !editemail.emailrepeat.$focused">
															<label class="input-lg-icon fa fa-check"></label>
														</span>
												</div>
										</div>
										<div class="form-group">
											<div class="col-md-4 col-md-offset-4">
												<button class="btn btn-primary btn-sm pull-right">Save</button>
												<button class="btn btn-primary btn-sm">Cancel</button>
											</div>
										</div>
										<div class="form-group" ng-if="editemail.$invalid && editemail.submitted">
							                <div class="col-md-offset-4 col-md-4">
							                  <p class="text-danger">Form data is not correct, check provided data and try again.</p>
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

<#include "./templates/ScrumFooter.ftl" >