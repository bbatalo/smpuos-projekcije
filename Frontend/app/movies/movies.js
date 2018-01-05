'use strict';

angular.module('cinCity.movies', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/movies', {
    templateUrl: 'movies/movies.html',
    controller: 'MoviesCtrl'
  });
}])

.controller('MoviesCtrl', ['$scope', 'userService', function($scope, userService) {

}]);