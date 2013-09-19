<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">

<script type="text/javascript">
	
var app = angular.module("authenticationApp", ['ngAnimate', 'ui.directives', 'scDirectives', 'scControllers']);

app.controller("authenticationController", function($scope, $http, $element, MessageData){
	$scope.validate = function()
	{
		$scope.messagedata = MessageData;
		$http({
			url : "<@spring.url '/rest/authentication/credentials'/>",
			method : "POST",
			data : $element.serialize(),
			headers : {'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(function(data,status,headers,cfg){
			if(status == 200)
			{
				$element.submit();
			}
			if(status == 401)
			{	
				(!data) ? $scope.messagedata.submiterror = true : $scope.messagedata.shadowflag = data.flag;
			}
		}).error(function(data,status,headers,cfg){
			$scope.messagedata.submiterror = true;
		})
	}
})
</script>

<div class="container loadin" >
		<div class="row" ng-app="authenticationApp">
			<div class="col-lg-offset-2 col-lg-8">
				<div class="row">
					<div class="col-lg-12">
						<div class="alert alert-danger" ng-controller="MessageController" ng-show="messagedata.submiterror">
							<button type="button" class="close" ng-click="hideMessage()">×</button>
							<h4>Błąd logowania</h4>
							<p ng-show="messagedata.submiterror">Nieprawidłowy login lub hasło</p>
							<p ng-show="messagedata.shadowflag == 1">Konto nie zostało potwierdzone przez użytkownika</p>
						</div> 
						<div class="well">
							<form  method="POST" class="form-horizontal" ng-controller="authenticationController" action="<@spring.url '/static/j_spring_security_check'/>" name="authentication" ui-keypress="{13:'validate()'}" novalidate>
								<div class="row">
									<div class="col-lg-12">
										<legend>Login</legend>
									</div>
								</div>
								<div class="row">
								<div class="col-lg-11">
									<div class="form-group">
										<div class="col-lg-7 col-lg-offset-3">
											<div class="input-box">
												<label for="login">Login</label>
												<input type="text" class="input-lg form-control" id="login" name="login" />
												<label class="input-lg-icon icon-user colorize" for="login"></label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-7 col-lg-offset-3">
													<div class="input-box">
														<label for="password">Password</label>
														<input type="password" class="input-lg form-control" id="password" name="password" />
														<label class="input-lg-icon icon-lock colorize" for="password"></label>
													</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-lg-offset-3 col-lg-3">
											<a href="#">Recover password</a>
											<a href="<@spring.url '/register'/>
											">Register account
										</a>
									</div>
									<div class="col-lg-offset-2 col-lg-2">
										<a class="btn btn-primary pull-right" ng-click="validate()">Submit</a>
									</div>
								</div>
							</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-offset-10 col-lg-2">
					<span class="span-concave"> <i class="icon-double-angle-up colorize"></i>
						1.0 test
					</span>
				</div>
			</div>
		</div>
	</div>
</div>

<#include "./templates/ScrumFooter.ftl" >