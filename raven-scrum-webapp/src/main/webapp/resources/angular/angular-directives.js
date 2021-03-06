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

scdirectives.directive('ngValidatePassword', function(){
        //TODO password strength regexp 
  return {
       require : 'ngModel',
       restrict : 'A',
    link: function($scope, element, attrs, $ctrl) 
    {
        var passworddto = JSON.parse(attrs.ngValidatePassword);
        var password = angular.element('#' + passworddto.passwordid);
        element.bind('blur', function(evt)
        {
            $scope.$apply(function()
            {
                $ctrl.$setValidity("minlength", emptyValidation(element.val()) && minLengthValidation(element.val()));
                $ctrl.$setValidity("equalslogin", !(element.val() == passworddto.login));
                $ctrl.$setValidity("notmatch", !(element.val() != password.val()));
            });
        });
    }
  }
});

scdirectives.directive('ngValidateEmail', function($http){
        //TODO email regexp 
  return {
       require : 'ngModel',
       restrict : 'A',
    link: function($scope, element, attrs, ctrl)
    {
        var emaildto = JSON.parse(attrs.ngValidateEmail);
        var email = angular.element('#' + emaildto.emailid);
        element.bind('blur', function(evt){
            $scope.$apply(function(){
                ctrl.$setValidity("pattern", emptyValidation(element.val()))
                if(emaildto.emailid != "" || emaildto.emailid)
                {
                  ctrl.$setValidity("notmatch", element.val() == email.val());
                }
                if(!ctrl.$error.pattern && !ctrl.$error.notmatch)
                {
                     $http.post(emaildto.emailurl, JSON.stringify({'email': element.val() })).success(function(data,status,headers,cfg){
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