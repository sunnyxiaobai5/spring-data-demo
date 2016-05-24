(function () {
    'use strict';

    angular.module('elapse.ui').directive('uiBreadcrumb', ['$http', function ($http) {
        return {
            // scope: {}, // {} = isolate, true = child, false/undefined = no change
            // controller: function($scope, $element, $attrs, $transclude) {},
            // require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
            restrict: 'AE', // E = Element, A = Attribute, C = Class, M = Comment
            // template: '<div>你好</div>',
            templateUrl: 'scripts/components/05-breadcrumb/ui-breadcrumb.html',
            // replace: true,
            // transclude: true,
            // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
            link: function ($scope, iElm, iAttrs, controller) {


            }
        };
    }]);
})();