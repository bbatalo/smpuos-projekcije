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

cinCity.controller('DashboardController', ['$scope', 'userService', function DashboardController($scope, userService) {

}]);


