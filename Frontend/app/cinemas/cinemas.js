'use strict';

angular.module('cinCity.cinemas', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/cinemas', {
    templateUrl: 'cinemas/cinemas.html',
    controller: 'CinemasCtrl'
  });
}])

.controller('CinemasCtrl', ['$scope', 'userService', function($scope, userService) {

}]);