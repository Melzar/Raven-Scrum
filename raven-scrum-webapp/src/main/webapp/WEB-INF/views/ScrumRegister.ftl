<#include "./templates/ScrumHeader.ftl" >
<#include "./templates/components/NavigationBar.ftl">
<#assign links = [{"name": "Login", "url": "/login"},{"name": "Register", "url" : "/register", "type" : "active"}]/>
<body ng-app="ScrumBoardApp">
<script type="text/javascript">

  var app = angular.module("ScrumBoardApp", ['ngAnimate', 'scDirectives', 'scControllers', 'ui.bootstrap']);

  app.factory("TemplateData", function(){
      return {sourcelink: "<@spring.url ''/>"}
  })

  
</script>
<!-- <div class="breadcrumb-wrapper">
  <div class="breadcrumb-container">
     <#include "./templates/components/Breadcrumb.ftl">
  </div>
</div>
 -->
<div class="wrapper center">
    <div class="box">
      <div class="box-header">
        <h1><i class="fa fa-pencil-square-o"></i>Register</h1>
      </div>
      <div class="box-content-wrapper">
        <div ng-controller="MessageController">
          <div class="alert" ng-class="{'animated fadeIn alert alert-success': messagedata.submitsuccess, 'animated fadeIn alert alert-danger': messagedata.submiterror}" ng-if="messagedata.submitsuccess || messagedata.submiterror">
              <button type="button" class="close" ng-click="hideMessage()">×</button>
              <div class="alert-icon pull-left">
                 <i class="" ng-class="{'fa fa-check fa-3x' : messagedata.submitsuccess, 'fa fa-times fa-3x' : messagedata.submiterror || messageData.internalError}"></i>
              </div>
              <div class="alert-details">
                <h4 ng-if="messagedata.submitsuccess">Submit successful</h4>
                <h4 ng-if="messagedata.submiterror">Submit error!</h4>
                <p ng-if="messagedata.submitsuccess">Check your mail for confirmation email. <a href="<@spring.url '/login/'/>">Go to login page</a></p>
                <p ng-if="messagedata.submiterror">There were internal error during form submition, check again your data or try again later</p>
              </div>
          </div>
        </div>
        <div class="box-small-wrapper">
          <form ng-controller="RegisterController" name="registration" class="form-horizontal box-small-content" ng-submit="submit()" novalidate>
             <blockquote>
              To register account, insert your data into inputs. Check if provided data is correct.
            </blockquote>
              <div class="form-group" ng-class="{'has-error': (registration.login.$error.unique || registration.login.$error.minlength || registration.login.$error.pattern ) && !registration.login.$focused , 'has-success': registration.login.$valid && !registration.login.$focused }">
                    <div class="col-md-8 col-md-offset-2">
                      <label class="control-label" for="login">Login</label>
                      <input type="text" name="login" id="login" class="form-control" ng-model="login" placeholder="Login" ng-pattern="/^[\w]+$/" ng-blur="validateLogin()" ng-focused required>
                      <span class="help-block no-margin" ng-if="registration.login.$error.unique">Provided login is taken</span>
                      <span class="help-block no-margin" ng-if="registration.login.$error.minlength && !registration.login.$focused">The minimum length of login is 4 characters</span>
                      <span class="help-block no-margin" ng-if="registration.login.$error.pattern  && !registration.login.$focused">
                        Incorrect format. Login cannot contain special signs.
                      </span>
                      <span class="help-block no-margin" ng-if="registration.login.$valid && !registration.login.$focused"> 
                        <label class="input-lg-icon fa fa-check"></label>
                      </span>
                    </div>
              </div>
              <div class="form-group">
                <div class="col-md-8 col-md-offset-2">
                <label class="control-label" for="name">Name (Optional)</label>
                <input type="text" id="name" class="form-control" placeholder="Name" ng-model="name">
                </div>
              </div>
              <div class="form-group">
                <div class="col-md-8 col-md-offset-2">
                <label class="control-label" for="surname">Surname (Optional)</label>
                <input type="text" id="surname" class="form-control" placeholder="Surname" ng-model="surname">
                </div>
              </div>
              <div class="form-group" ng-class="{'has-error': (registration.passwordrepeat.$error.equalslogin || registration.passwordrepeat.$error.notmatch || registration.passwordrepeat.$error.minlength) && !registration.passwordrepeat.$focused , 'has-success': registration.passwordrepeat.$valid && !registration.passwordrepeat.$focused}">
                <div class="col-md-8 col-md-offset-2">
                  <label class="control-label" for="password">Password</label>
                  <input type="password" name="password" id="password" class="form-control" placeholder="Password" ng-model="password" ng-focused required>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$error.equalslogin && !registration.passwordrepeat.$focused">Password cannot be same as login</span>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$error.notmatch && !registration.passwordrepeat.$focused">Provided passwords do not match</span>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$error.minlength && !registration.passwordrepeat.$focused">The minimum lenght of password is 4
                  </span>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$valid && !registration.passwordrepeat.$focused"> 
                    <label class="input-lg-icon fa fa-check"></label>
                  </span>
                </div>
              </div>
              <div class="form-group" ng-class="{'has-error': (registration.passwordrepeat.$error.equalslogin || registration.passwordrepeat.$error.notmatch || registration.passwordrepeat.$error.minlength) && !registration.passwordrepeat.$focused, 'has-success': registration.passwordrepeat.$valid && !registration.passwordrepeat.$focused }">
                <div class="col-md-8 col-md-offset-2">
                  <label class="control-label" for="passwordRepeat">Password repeat</label>
                  <input type="password"  name="passwordrepeat" id="passwordRepeat" ng-model="passwordrepeat" ng-validate-password='{"passwordid" : "password", "loginid" : "login"}' class="form-control" placeholder="Repeat password" ng-focused required>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$error.equalslogin && !registration.passwordrepeat.$focused">Password cannot be same as login</span>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$error.notmatch && !registration.passwordrepeat.$focused">Provided passwords do not match</span>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$error.minlength && !registration.passwordrepeat.$focused">The minimum lenght of password is 4</span>
                  <span class="help-block no-margin" ng-if="registration.passwordrepeat.$valid && !registration.passwordrepeat.$focused">
                    <label class="input-lg-icon fa fa-check"></label>
                  </span>
                </div>
              </div>
              <div class="form-group" ng-class="{'has-error': (registration.email.$error.pattern || registration.email.$error.unique) && !registration.email.$focused, 'has-success': registration.email.$valid && !registration.email.$focused}">
                <div class="col-md-8 col-md-offset-2">
                  <label class="control-label" for="email">Email</label>
                  <input type="text" name="email" id="email" class="form-control" ng-model="email" ng-validate-email='{"emailid" : "", "emailurl" : "<@spring.url "/rest/validate/email"/>"}' placeholder="Email" ng-focused required>
                  <span class="help-block no-margin" ng-if="registration.email.$error.pattern && !registration.email.$focused">Incorrect email format.</span>
                  <span class="help-block no-margin" ng-if="registration.email.$error.unique && !registration.email.$focused">Provided email is actually associated with another account.</span>
                  <span class="help-block no-margin" ng-if="registration.email.$valid && !registration.email.$focused">
                    <label class="input-lg-icon fa fa-check"></label>
                  </span>
                </div>
              </div>
              <div class="form-group" ng-class="{'has-success': checkbox, 'has-error': !checkbox && registration.checkbox.$dirty}">
                    <div class="col-md-8 col-md-offset-2">
                        <label class="checkbox no-padding" for="checkbox">I accept the regulations</label> 
                        <input type="checkbox" name="checkbox" id="checkbox" ng-model="checkbox" required>
                        <span class="help-block no-margin" ng-hide="checkbox">Accepting regulations is obligatory</span>
                        <span class="help-block no-margin" ng-if="checkbox"><i class="input-lg-icon fa fa-check"></i></span> 
                    </div>
              </div>
              <div class="form-group">
                <div class="col-md-offset-2 col-md-8">
                  <a href="<@spring.url '/login' />" class="btn btn-primary btn-sm">Cancel</a>
                  <button type="submit" class="btn btn-primary btn-sm pull-right">Sign in</button>
                </div>
              </div>
              <div class="form-group" ng-if="registration.$invalid && registration.submitted">
                <div class="col-md-offset-2 col-md-10">
                  <p class="text-danger">Form data is not correct, check provided data and try again.</p>
                </div>
              </div>
           </form>
        </div>
      </div>
    </div>
</div>

<#include "./templates/ScrumFooter.ftl">