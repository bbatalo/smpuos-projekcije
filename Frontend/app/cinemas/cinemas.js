'use strict';

var cinemasModule = angular.module('cinCity.cinemas', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/cinemas', {
    templateUrl: 'cinemas/cinemas.html',
    controller: 'CinemasCtrl'
  });
}])

cinemasModule.controller('CinemasCtrl', ['$scope', 'userService', function($scope, userService) {

}]);

cinemasModule.service('cinemasService', function() {

});