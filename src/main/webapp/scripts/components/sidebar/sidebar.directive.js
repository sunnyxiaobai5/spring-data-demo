'use strict';
/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: sidebar.directive.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
angular.module('clapseApp').directive('sidebar', ['Menu', '$state', function (Menu, $state) {
    return {
        restrict: 'E',
        scope: true,
        templateUrl: basePath + '/scripts/components/sidebar/sidebar.html',
        link: function ($scope, iElm, IAttrs, controller) {
            $scope.$watch(function () {
                return $state.current;
            }, function (newValue, oldValue) {
                console.log(newValue)
                if (newValue && newValue.parent == 'site' && newValue.data && newValue.data.id) {
                    Menu.findByParentId({id: newValue.data.id}, function (data) {
                        $scope.menuList = data.result;
                    });
                }
            });
        }
    }
}]);