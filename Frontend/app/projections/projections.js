'use strict';

angular.module('cinCity.projections', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/projections', {
    templateUrl: 'projections/projections.html',
    controller: 'ProjectionsCtrl'
  });
}])

.controller('ProjectionsCtrl', ['$scope', 'userService', function($scope, userService) {

}]);