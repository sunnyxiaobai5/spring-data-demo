/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: main.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('clapseApp')
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');

        $stateProvider.state('home', {
            parent: 'site',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                },
                resolve: {}
            }
        }).state('system1', {
            parent: 'site',
            url: '/system1',
            data: {
                id: 1
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system2', {
            parent: 'site',
            url: '/system2',
            data: {
                id: 2
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system3', {
            parent: 'site',
            url: '/system3',
            data: {
                id: 3
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system4', {
            parent: 'site',
            url: '/system4',
            data: {
                id: 4
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system1.module1', {
            parent: 'system1',
            url: '/module1',
            data: {},
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system1.module2', {
            parent: 'system1',
            url: '/module2',
            data: {},
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system2.module1', {
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
