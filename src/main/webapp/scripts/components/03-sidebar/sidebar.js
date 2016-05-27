/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: sidebar.directive.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapse.ui').directive('sidebar', ['Menu', '$state', function (Menu, $state) {
        return {
            restrict: 'E',
            scope: true,
            templateUrl: 'scripts/components/03-sidebar/sidebar.html',
            link: function ($scope, iElement, iAttrs, controller) {

                var getSystemState = function () {
                    var state = $state.current;
                    while (state.parent !== 'main') {
                        state = $state.get(state.parent);
                    }
                    return state;
                };


                $scope.$watch(getSystemState, function (newValue, oldValue) {
                    if (newValue.data && newValue.data.id) {
                        Menu.findByParentId({id: newValue.data.id}, function (data) {
                            $scope.menus = data;
                        });
                    }
                });
            }
        };
    }]);
})();