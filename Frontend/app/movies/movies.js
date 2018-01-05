'use strict';

var moviesModule = angular.module('cinCity.movies', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/movies', {
    templateUrl: 'movies/movies.html',
    controller: 'MoviesCtrl'
  });
}])

moviesModule.controller('MoviesCtrl', ['$scope', 'userService', function($scope, userService) {

}]);

moviesModule.service('moviesService', function() {

});