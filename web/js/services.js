'use strict';

/* Services */


angular.module('faceit.services', ['ngResource']).
    factory('Face', function($resource) {
        return $resource('/api/faces')
    })
