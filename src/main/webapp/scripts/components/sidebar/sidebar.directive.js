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
angular.module('clapseApp').directive('sidebar', ['Menu', function (Menu) {
    return {
        restrict: 'E',
        scope: true,
        templateUrl: basePath + '/scripts/components/sidebar/sidebar.html',
        link: function ($scope, iElm, IAttrs, controller) {
            Menu.findByParentId(null, function (data) {
                $scope.menuList = data.result;
            });
        }
    }
}]);