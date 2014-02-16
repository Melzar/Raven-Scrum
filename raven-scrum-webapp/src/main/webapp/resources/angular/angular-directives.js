var scdirectives = angular.module('scDirectives', []);

scdirectives.directive('ngFocused', function() {
    var FOCUS_CLASS = "ng-focused";
    return {
      restrict: 'A',
      require: 'ngModel',
      link: function(scope, element, attrs, ctrl) {
        ctrl.$focused = false;
        element.bind('focus', function(evt) {
          element.addClass(FOCUS_CLASS);
          scope.$apply(function() {ctrl.$focused = true;});
        }).bind('blur', function(evt) {
          element.removeClass(FOCUS_CLASS);
          scope.$apply(function() {ctrl.$focused = false;});
        });
      }
    }
});

scdirectives.directive('ngProjectTitle', function($http, TemplateData){
  return {
    require : 'ngModel',
    restrict : 'A',
    link: function($scope, element, attrs, $ctrl)
    {
      element.bind('blur', function(evt)
      {
        $scope.$apply(function()
        {
          $http.post(TemplateData.sourcelink + "/rest/project/validate/title", JSON.stringify({'title' : element.val()})
          ).success(function(data,status,headers,cfg){
            $ctrl.$setValidity("unique", data.unique);
          }).error(function(data,status,headers,cfg){
            $ctrl.$setValidity("unique", data.unique);
          })
        })
      })
    }
  }
})

scdirectives.directive('ngValidatePassword', function(){
        //TODO password strength regexp 
  return {
       require : 'ngModel',
       restrict : 'A',
    link: function($scope, element, attrs, $ctrl) 
    {
        var passworddto = JSON.parse(attrs.ngValidatePassword);
        var password = angular.element('#' + passworddto.passwordid);
        var login = angular.element('#'+passworddto.loginid)
        element.bind('blur', function(evt)
        {
            $scope.$apply(function()
            {
                $ctrl.$setValidity("minlength", emptyValidation(element.val()) && minLengthValidation(element.val()));
                $ctrl.$setValidity("equalslogin", !(element.val() == login.val()));
                $ctrl.$setValidity("notmatch", !(element.val() != password.val()));
            });
        });
    }
  }
});

//TODO refactor this directive logic
scdirectives.directive('ngValidateEmail', function($http){
  return {
       require : 'ngModel',
       restrict : 'A',
    link: function($scope, element, attrs, ctrl)
    {
        var emaildto = JSON.parse(attrs.ngValidateEmail);
        var email = angular.element('#' + emaildto.emailid);
        element.bind('blur', function(evt){
            $scope.$apply(function(){
                ctrl.$setValidity('unique', true);
                ctrl.$setValidity("pattern", emptyValidation(element.val()) && element.val().trim().match("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))
                if(emaildto.emailid != "" || emaildto.emailid)
                {
                  ctrl.$setValidity("notmatch", element.val() == email.val());
                }
                if(!ctrl.$error.pattern && !ctrl.$error.notmatch)
                {
                     $http.post(emaildto.emailurl, JSON.stringify({'email': element.val().trim() })).success(function(data,status,headers,cfg){
                       ctrl.$setValidity('unique', data.unique);
                     }).error(function(data, status, headers, cfg){
                       ctrl.$setValidity('unique', false);
                     })
                }
            });
        });
    }
  }
});

scdirectives.directive('ngSprintCreatePopup', function(TemplateData)
{
  return{
    restrict: 'A',
    transclude: false,
    templateUrl: TemplateData.sourcelink + '/template/popup/popupCreate.ftl',
    controller: "SprintPopupController"
  }
})

scdirectives.directive('ngSprintClosePopup', function(TemplateData)
{
  return{
    restrict: 'A',
    transclude: false,
    templateUrl: TemplateData.sourcelink + '/template/popup/popupFinalize.ftl',
    controller: "SprintPopupController"
  }
})

scdirectives.directive('ngUser', function(TemplateData)
{
  return{
    restrict: 'A',
    transclude: false,
    templateUrl: TemplateData.sourcelink + '/template/scrum/user.ftl',
  }
})

scdirectives.directive('ngUserProject', function(TemplateData)
{
  return{
    restrict: 'A',
    transclude: false,
    templateUrl: TemplateData.sourcelink + '/template/scrum/user-project.ftl',
  }
})

scdirectives.directive('ngScrumTask', function(TemplateData)
{
  return{
    restrict: 'A',
    transclude: false,
    templateUrl : TemplateData.sourcelink + '/template/scrum/subtask.ftl',
 }
})

scdirectives.directive('ngProject', function(TemplateData)
{
  return{
    restrict: 'A',
    transclude: false,
    templateUrl : TemplateData.sourcelink + '/template/scrum/project.ftl',
  }
})

scdirectives.directive('ngScrumboard', function(TemplateData)
{
  return{
    restrict: 'A',
    transclude: false,
    templateUrl: TemplateData.sourcelink + '/template/scrum/scrumtasks.ftl',
    controller: 'ScrumBoardController',
  }
})

scdirectives.directive('ngDropped', function($animate)
{
  return{
    require : 'ngModel',
    restrict : 'A',
    transclude : false,
    link : function (scope, element, attrs, ctrl)
    {
        element.bind('drop', function(evt)
        {
          $animate.removeClass(element,'loadin');
          $animate.addClass(element,'loadin');
        })
    }
  }
})
