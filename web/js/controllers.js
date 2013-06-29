'use strict';

/* Controllers */

angular.module('faceit.controllers', []).
  controller('Faces', ['$scope', 'Face', function($scope, Face) {
    $scope.faces = Face.query();
  }]);