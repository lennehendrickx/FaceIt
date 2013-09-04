'use strict';

// Declare app level module which depends on filters, and services
angular.module('faceit', ['faceit.filters', 'faceit.services', 'faceit.directives', 'faceit.controllers']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: 'partials/faces.html', controller: 'Faces'})
        .when('/face/:faceId', {templateUrl: 'partials/face.html', controller: 'Face'})
        .otherwise({redirectTo: '/'});
  }]);
