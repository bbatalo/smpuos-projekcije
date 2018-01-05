'use strict';

// Declare app level module which depends on views, and components
var cinCity = angular.module('cinCity', [
  'ngRoute',
  'cinCity.users',
  'cinCity.projections',
  'cinCity.movies',
  'cinCity.cinemas',
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/users'});
}]);

cinCity.service('userService', function() {
  var user = {};

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

cinCity.controller('DashboardController', ['$scope', 'userService', function DashboardController($scope, userService) {

  

}]);


