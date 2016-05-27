(function () {
    'use strict';

    angular.module('elapse.ui').directive('uiQuery', [function () {
        return {
            restrict: 'E',
            scope: true,
            templateUrl: 'scripts/components/06-query/query.html',
            link: function ($scope, iElement, iAttrs, controller) {

            },
            controller: function ($scope, $element, $attrs, $transclude) {

            }
        };
    }]);
})();
