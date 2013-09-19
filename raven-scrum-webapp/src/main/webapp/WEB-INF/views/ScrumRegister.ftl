<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Login", "url": "/login"},{"name": "Register", "url" : "/register", "type" : "active"}]/>

<script type="text/javascript">

  var app = angular.module("registrationApp", ['ngAnimate', 'scDirectives', 'scControllers']);

  app.controller("RegisterController", function($scope, $http, $element, MessageData){

      $scope.submit = function()
      {
        $scope.messagedata = MessageData;
        $scope.registration.submitted = true;
        if($scope.registration.$valid && $scope.checkbox)
        {
          $http({
            url: "<@spring.url '/account/registration'/>",
            method: "POST",
            data: $element.serialize(),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
          }).success(function(data, status, headers, cfg){
            (data.success == true) ? $scope.messagedata.submitsuccess = true : $scope.messagedata.submiterror = true;
          }).error(function(data,status,headers,cfg){
            $scope.messagedata.submiterror = true;
          })
        }
      }

      $scope.validateLogin = function()
      {
            $scope.registration.login.$setValidity("minlength", emptyValidation($scope.registration.login.$viewValue) && minLengthValidation($scope.registration.login.$viewValue));
            if(!$scope.registration.login.$error.minlength)
            {
              $http.post("<@spring.url '/rest/validate/login'/>",JSON.stringify({'login' : $scope.registration.login.$viewValue})).success(function(data, status, headers, cfg){
                $scope.registration.login.$setValidity('unique', data.unique);
              }).error(function(data,status,headers,cfg){
                $scope.registration.login.$setValidity('unique', false);
              })
            }
      }
  });
</script>
<div class="col-lg-12">
  <div class="row">
    <div class="col-lg-12">
     <#include "./templates/components/Breadcrumb.ftl">
  </div>
</div>
<div class="row">
  <div class="col-lg-offset-3 col-lg-6">
    <legend>Rejestracja konta</legend>
  </div>
</div>

<div ng-app="registrationApp" class="row loadin">
  <div class="col-lg-offset-3 col-lg-6" ng-controller="MessageController">
    <div class="alert" ng-class="{'alert alert-success': messagedata.submitsuccess, 'alert alert-danger': messagedata.submiterror}" ng-show="messagedata.submitsuccess || messagedata.submiterror">
      <div class="row">
        <div class="col-lg-2">
            <i class="" ng-class="{'icon-ok icon-3x pull-right' : messagedata.submitsuccess, 'icon-remove icon-3x pull-right' : messagedata.submiterror || messageData.internalError}"></i>
        </div>
        <div class="col-lg-10">
          <h4 ng-show="messagedata.submitsuccess">Submit successful</h4>
          <h4 ng-show="messagedata.submiterror">Submit error!</h4>
          <p ng-show="messagedata.submitsuccess">Check your mail for confirmation email. <a href="<@spring.url '/login/'/>">Go to login page</a></p>
          <p ng-show="messagedata.submiterror">There were internal error during form submition, <a href ng-click="hideMessage()">check again your data</a> or try again later</p>
        </div>
      </div>
    </div>
    <div class="well" ng-hide="messagedata.submitsuccess || messagedata.submiterror">
      <blockquote>
        Wypełnij pola aby zarejestrować konto, zwróć uwagę na to, aby pola były wypełnione poprawnie
      </blockquote>
        <form name="registration" ng-controller="RegisterController" class="form-horizontal" ng-submit="submit()" novalidate>
          <div class="row">
                <div class="col-lg-11">
          <div class="form-group" ng-class="{'has-error': registration.login.$invalid && !registration.login.$focused , 'has-success': registration.login.$valid && registration.login.$dirty && !registration.login.$focused }">
            <div class="col-lg-7 col-lg-offset-3">
              <label class="control-label" for="inputLogin">Login</label>
              <input type="text" name="login" id="inputLogin" class="form-control" ng-model="login" placeholder="Login" ng-pattern="/^[\w]+$/" ng-blur="validateLogin()" ng-focused>
              <span class="help-block" ng-show="registration.login.$error.unique">Podany login jest zajęty</span>
              <span class="help-block" ng-show="registration.login.$error.minlength && !registration.login.$focused">Login musi mieć minimum 4 znaki</span>
              <span class="help-block" ng-show="registration.login.$error.pattern  && !registration.login.$focused">
                Nieprawidłowy format. Login nie może zawierać znaków specjalnych.
              </span>
              <span class="help-block" ng-show="registration.login.$valid && registration.login.$dirty && !registration.login.$focused"> <i class="icon-ok"></i>
              </span>
            </div>
          </div>
          <div class="form-group" ng-class="{'has-error': registration.passwordrepeat.$invalid && !registration.passwordrepeat.$focused , 'has-success': registration.passwordrepeat.$valid && registration.passwordrepeat.$dirty && !registration.passwordrepeat.$focused}">
            <div class="col-lg-7 col-lg-offset-3">
              <label class="control-label" for="inputPassword">Password</label>
              <input type="password" name="password" id="password" class="form-control" placeholder="Password" ng-model="password" ng-focused>
              <span class="help-block" ng-show="registration.passwordrepeat.$error.equalslogin && !registration.passwordrepeat.$focused">Hasło nie może być takie jak login</span>
              <span class="help-block" ng-show="registration.passwordrepeat.$error.notmatch && !registration.passwordrepeat.$focused">Hasła nie zgadzają się</span>
              <span class="help-block" ng-show="registration.passwordrepeat.$error.minlength && !registration.passwordrepeat.$focused">Hasło musi mieć min. 4 znaki
              </span>
              <span class="help-block" ng-show="registration.passwordrepeat.$valid && registration.passwordrepeat.$dirty && !registration.passwordrepeat.$focused"> 
                <i class="icon-ok"></i>
              </span>
            </div>
          </div>
          <div class="form-group" ng-class="{'has-error': registration.passwordrepeat.$invalid && !registration.passwordrepeat.$focused, 'has-success': registration.passwordrepeat.$valid && registration.passwordrepeat.$dirty && !registration.passwordrepeat.$focused }">
            <div class="col-lg-7 col-lg-offset-3">
              <label class="control-label" for="inputPasswordRepeat">Password repeat</label>
              <input type="password"  name="passwordrepeat" id="inputPasswordRepeat" ng-model="passwordrepeat" ng-validate-password='{"passwordid" : "password", "login" : "{{login}}"}' class="form-control" placeholder="Repeat password" ng-focused >
              <span class="help-block" ng-show="registration.passwordrepeat.$error.equalslogin && !registration.passwordrepeat.$focused">Hasło nie może być takie jak login</span>
              <span class="help-block" ng-show="registration.passwordrepeat.$error.notmatch && !registration.passwordrepeat.$focused">Hasła nie zgadzają się</span>
              <span class="help-block" ng-show="registration.passwordrepeat.$error.minlength && !registration.passwordrepeat.$focused">Hasło musi mieć min. 4 znaki</span>
              <span class="help-block" ng-show="registration.passwordrepeat.$valid && registration.passwordrepeat.$dirty && !registration.passwordrepeat.$focused">
                <i class="icon-ok"></i>
              </span>
            </div>
          </div>
          <div class="form-group" ng-class="{'has-error': registration.email.$invalid && !registration.email.$focused, 'has-success': registration.email.$valid && registration.email.$dirty && !registration.email.$focused}">
            <div class="col-lg-7 col-lg-offset-3">
              <label class="control-label" for="inputEmail">Email</label>
              <input type="text" name="email" id="inputEmail" class="form-control" ng-model="email" ng-validate-email='{"emailid" : "", "emailurl" : "<@spring.url "/rest/validate/email"/>"}' placeholder="Email" ng-pattern="/^[\w]+$/" ng-focused>
              <span class="help-block" ng-show="registration.email.$error.pattern && !registration.email.$focused">Nieprawidłowy format email.</span>
              <span class="help-block" ng-show="registration.email.$error.unique && !registration.email.$focused">Podany email jest powiązany z kontem w systemie.</span>
              <span class="help-block" ng-show="registration.email.$valid && registration.email.$dirty && !registration.email.$focused">
                <i class="icon-ok"></i>
              </span>
            </div>
          </div>
          <div class="form-group" ng-class="{'has-success': checkbox, 'has-error': !checkbox && registration.checkbox.$dirty}">
              <div class="col-lg-7 col-lg-offset-3">
                  <label class="control-label" for="inputCheckbox">Akceptuje regulamin</label>
                  </br>
                  <input type="checkbox" name="checkbox" id="inputCheckbox" ng-model="checkbox" required>
                  <span class="help-block" ng-hide="checkbox">Akceptacja regulaminu jest obowiązkowa</span>
                  <span class="help-block" ng-show="checkbox"><i class="icon-ok"></i></span> 
              </div>
          </div>
          <div class="form-group">
                <div class="col-lg-offset-3 col-lg-2">
                  <button type="submit" class="btn btn-primary btn-sm">Cancel</button>
                </div>
                <div class="col-lg-offset-3 col-lg-2">
                  <button type="submit" class="btn btn-primary btn-sm pull-right">Sign in</button>
                </div>
          </div>
          <div class="form-group">
              <div class="col-lg-offset-2 col-lg-10">
                <p class="text-danger" ng-show="registration.$invalid && registration.submitted">Formularz nie jest wypełniony poprawnie, sprawdz wprowadzone dane i spróbuj ponownie.</p>
              </div>
          </div>
          </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<#include "./templates/ScrumFooter.ftl">