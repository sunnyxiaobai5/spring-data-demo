/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: home.controller.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapseApp').controller('HomeController', ['$scope', '$state', 'Menu', function ($scope, $state, Menu) {
        $scope.systems = [];
        Menu.findSystems(function (result) {
            angular.forEach(result, function (system) {
                $scope.systems.push(system);
            });
        });
    }]);
})();
