/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: system1.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('clapseApp')
    .config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('system1', {
                parent: 'main',
                url: '/system1',
                data: {
                    id: 1
                },
                views: {
                    'content@main': {
                        templateUrl: 'scripts/app/system1/system1.html'
                    }
                }
            })
            .state('system1.module1', {
                parent: 'system1',
                url: '/module1',
                data: {},
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html'
                    }
                }
            })
            .state('system1.module2', {
                parent: 'system1',
                url: '/module2',
                data: {},
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html'
                    }
                }
            });
    });