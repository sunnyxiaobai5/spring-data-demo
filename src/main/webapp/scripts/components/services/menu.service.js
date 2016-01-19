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
'use strict';

angular.module('clapseApp').factory('Menu', ['$resource', function ($resource) {
    return $resource('menu/:id', {}, {
        'query': {method: 'GET', isArray: true},
        'get': {
            method: 'GET',
            transformResponse: function (data) {
                data = angular.fromJson(data);
                return data;
            }
        },
        'update': {method: 'PUT'},
        'findByParentId': {method: 'GET', url: 'menu/findByParentId/:id', isArray: true},
        'findSystem': {method: 'GET', url: 'menu/findSystem', isArray: true}
    });
}]);

