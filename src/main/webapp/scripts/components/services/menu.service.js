/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: menu.service.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapseApp').factory('Menu', ['$resource', function ($resource) {
        return $resource('menus/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'findSystems': {method: 'GET', url: 'menus/findSystems', isArray: true},
            'findModules': {method: 'GET', url: 'menus/findModules', isArray: true}
        });
    }]);
})();