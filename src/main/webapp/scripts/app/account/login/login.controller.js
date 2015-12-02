/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: login.controller.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('clapseApp').controller('LoginController', ['$scope', '$state', 'Auth', function ($scope, $state, Auth) {
    $scope.login = function () {
        Auth.login();
        $state.go('home');
    };
}]);
