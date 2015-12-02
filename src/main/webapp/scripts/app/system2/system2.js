/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: system2.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('clapseApp')
    .config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('system2', {
                parent: 'main',
                url: '/system2',
                data: {
                    id: 2
                },
                views: {
                    'content@main': {
                        templateUrl: 'scripts/app/system2/system2.html'
                    }
                }
            })
            .state('system2.module1', {
                parent: 'system2',
                url: '/module1',
                data: {},
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html'
                    }
                }
            });
    });