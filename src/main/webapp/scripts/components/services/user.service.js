/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: user.service.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapseApp').factory('User', ['$resource', '$q', function ($resource, $q) {
        return $resource('user/:id', {}, {
            'query': {
                method: 'GET', isArray: true, interceptor: {
                    //TODO 具体异常处理DEMO
                    responseError: function (rejection) {
                        // alert(rejection);
                        $q.reject(rejection);
                    }
                }
            },
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': {method: 'PUT'}
        });
    }]);
})();