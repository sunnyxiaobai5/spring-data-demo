/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: system3.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('clapseApp')
    .config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('system3', {
                parent: 'main',
                url: '/system3',
                data: {
                    id: 3
                },
                views: {
                    'content@main': {
                        templateUrl: 'scripts/app/system3/system3.html'
                    }
                }
            });
    });