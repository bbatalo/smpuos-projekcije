'use strict';

var usersModule = angular.module('cinCity.users', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/users', {
    templateUrl: 'users/users.html',
    controller: 'UsersCtrl'
  });
}]);

usersModule.controller('UsersCtrl', ['$scope', 'userService', function($scope, userService) {
  $scope.message = 'users controller'

}]);

usersModule.service('userService', function() {
  var user = {};
  var users = [];

  var setUser = function(newUser) {
    user = newUser;
  };

  var getUser = function() {
    return user;
  };

  return {
    setUser: setUser,
    getUser: getUser
  };
});


