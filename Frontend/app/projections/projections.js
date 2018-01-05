'use strict';

var projectionsModule = angular.module('cinCity.projections', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/projections', {
    templateUrl: 'projections/projections.html',
    controller: 'ProjectionsCtrl'
  });
}])

projectionsModule.controller('ProjectionsCtrl', ['$scope', 'userService', function($scope, userService) {

}]);

projectionsModule.service('projectionsService', function() {

});