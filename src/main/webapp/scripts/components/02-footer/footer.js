/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: footer.directive.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapse.ui').directive('footer', [function () {
        return {
            restrict: 'E',
            scope: true,
            templateUrl: 'scripts/components/02-footer/footer.html',
            link: function (scope, iElement, iAttrs, controller) {

            },
            controller: function ($scope, $element, $attrs, $transclude) {

            }
        };
    }]);
})();