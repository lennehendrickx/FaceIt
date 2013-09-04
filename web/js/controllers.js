'use strict';

/* Controllers */

angular.module('faceit.controllers', []).
  controller('Faces', ['$scope', 'Face', function($scope, Face) {
    $scope.faces = Face.query();
  }])
  .controller('Face', ['$scope', 'Face', '$routeParams', function($scope, Face, $routeParams) {
    $scope.face = Face.get({faceId : $routeParams.faceId});
}]);