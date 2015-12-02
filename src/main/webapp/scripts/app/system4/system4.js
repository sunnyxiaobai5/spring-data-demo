/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: system4.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('clapseApp')
    .config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('system4', {
                parent: 'main',
                url: '/system4',
                data: {
                    id: 4
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/system4/system4.html'
                    }
                }
            });
    });