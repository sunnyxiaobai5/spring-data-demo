/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: navbar.directive.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('clapseApp').directive('navbar', ['$state', 'Menu', 'Auth', function ($state, Menu, Auth) {
        return {
            restrict: 'E',
            scope: true,
            templateUrl: 'scripts/components/navbar/navbar.html',
            link: function (scope, iElement, iAttrs, controller) {

            },
            controller: function ($scope, $element, $attrs, $transclude) {

                $scope.logout = function () {
                    Auth.logout();
                    $state.go('login');
                };

                $scope.$watch(Auth.isAuthenticated, function (newValue, oldValue) {
                    $scope.isAuthenticated = newValue;
                    if (newValue) {
                        Menu.findSystem(null, function (data) {
                            $scope.menuList = data;
                        });
                    } else {
                        $scope.menuList = [];
                    }
                });
            }
        };
    }]);
})();
