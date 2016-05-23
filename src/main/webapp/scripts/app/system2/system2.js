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
(function () {
    'use strict';

    angular.module('clapseApp')
        .config(function ($stateProvider) {

            $stateProvider
                .state('SITE_2', {
                    parent: 'main',
                    url: '/SITE_2',
                    data: {
                        id: 2
                    },
                    views: {
                        'content@main': {
                            templateUrl: 'scripts/app/system2/system2.html'
                        }
                    }
                })
                .state('SITE_2_1', {
                    parent: 'SITE_2',
                    url: '/SITE_2_1',
                    data: {},
                    views: {
                        'content@': {
                            templateUrl: 'scripts/app/main/main.html'
                        }
                    }
                });
        });
})();