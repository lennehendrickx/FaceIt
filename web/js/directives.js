'use strict';

/* Directives */


angular.module('faceit.directives', [])
    .directive("thumbnails", function () {
        return {
            restrict: "EA",
            transclude: true,     // it has HTML content
            replace: true,        // replace the original markup with our template
            scope: {},            // no scope variables required
            template:             // template assigns class and transclusion element
                "<ul class='thumbnails' ng-transclude></ul>",
            controller: function () {}
        };
    })
    .directive("thumbnail", function () {
        return {
            restrict: "EA",
            transclude: true,     // it has HTML content
            replace: true,        // replace the original markup with our template
            require: "^thumbnails",
            scope: {
                link: "@",
                imageUrl: "@"
            },
            template:             // template assigns class and transclusion element
                '<li>\
                    <a href="{{link}}" class="thumbnail" ng-transclude><img ng-src="{{imageUrl}}"></a>\
                 </li>'
        };
    }
);