<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<body ng-app="ScrumBoardApp">

<script type="text/javascript">
	
var app = angular.module("ScrumBoardApp", ['ngAnimate', 'ui.directives', 'scDirectives', 'scControllers', 'ui.bootstrap']);

app.factory('TemplateData', function(){
	return {sourcelink: '<@spring.url ""/>'}
})
</script>

<div class="wrapper center">
	<div class="box">
		<div class="box-header">
			<h1><i class="fa fa-lock"></i>Login</h1>
		</div>
		<div class="box-content-wrapper">
			<div ng-controller="MessageController">
				<div class="alert alert-danger"  ng-class="{'animated fadeIn' : messagedata.submiterror}"  ng-if="messagedata.submiterror">
						<button type="button" class="close" ng-click="hideMessage()">×</button>
						<h4>Błąd logowania</h4>
						<p ng-if="messagedata.submiterror">Nieprawidłowy login lub hasło</p>
						<p ng-if="messagedata.shadowflag == 1">Konto nie zostało potwierdzone przez użytkownika</p>
				</div> 
			</div>
			<div class="box-small-wrapper">
			<form  method="POST" class="form-horizontal box-small-content" ng-controller="AuthenticationController" action="<@spring.url '/static/j_spring_security_check'/>" name="authentication" ui-keypress="{13:'validate()'}" novalidate>
				<div class="form-group">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="input-box">
							<label for="login">Login</label>
							<input type="text" class="input-lg form-control" id="login" name="login" />
							<label class="input-lg-icon fa fa-user colorize" for="login"></label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="input-box">
							<label for="password">Password</label>
							<input type="password" class="input-lg form-control" id="password" name="password" />
							<label class="input-lg-icon fa fa-key colorize" for="password"></label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-8">
						<a class="btn btn-primary pull-right" ng-click="validate()">Submit</a>
						<a href="<@spring.url '/register'/>">Register account</a><br/>
						<a href="#">Recover password</a>	
					</div>
				</div>
			</form>
		</div>
</div>
</div>
</div>
</div>

<#include "./templates/ScrumFooter.ftl" >