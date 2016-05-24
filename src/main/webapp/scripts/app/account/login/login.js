/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: login.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapseApp')
        .config(function ($stateProvider) {

            $stateProvider
                .state('login', {
                    url: '/login',
                    views: {
                        '': {
                            templateUrl: 'scripts/app/account/login/login.html',
                            controller: 'LoginController'
                        }
                    }
                });
        });
})();