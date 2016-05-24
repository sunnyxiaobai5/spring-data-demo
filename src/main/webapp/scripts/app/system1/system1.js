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
(function () {
    'use strict';

    angular.module('elapseApp')
        .config(function ($stateProvider) {

            $stateProvider
                .state('SITE_1', {
                    parent: 'main',
                    url: '/SITE_1',
                    data: {
                        id: 1
                    },
                    views: {
                        'content@main': {
                            templateUrl: 'scripts/app/system1/system1.html'
                        }
                    }
                })
                .state('SITE_1_1', {
                    parent: 'SITE_1',
                    url: '/SITE_1_1',
                    data: {}
                })
                .state('SITE_1_2', {
                    parent: 'SITE_1',
                    url: '/SITE_1_2',
                    data: {}
                })
                .state('SITE_1_3', {
                    parent: 'SITE_1',
                    url: '/SITE_1_3',
                    data: {},
                    views: {
                        'content@main': {
                            templateUrl: 'scripts/app/system1/user/user-query.html',
                            controller: 'UserController'
                        }
                    }
                })
                .state('SITE_1_1_1', {
                    parent: 'SITE_1_1',
                    url: '/SITE_1_1_1',
                    data: {},
                    views: {
                        'content@': {
                            templateUrl: 'scripts/app/main/main.html'
                        }
                    }
                })
                .state('SITE_1_1_2', {
                    parent: 'SITE_1_1',
                    url: '/SITE_1_1_2',
                    data: {},
                    views: {
                        'content@': {
                            templateUrl: 'scripts/app/main/main.html'
                        }
                    }
                })
                .state('SITE_1_1_3', {
                    parent: 'SITE_1_1',
                    url: '/SITE_1_1_3',
                    data: {},
                    views: {
                        'content@': {
                            templateUrl: 'scripts/app/main/main.html'
                        }
                    }
                });
        });
})();