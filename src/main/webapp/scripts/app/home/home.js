/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: home.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('clapseApp')
    .config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                views: {
                    'main@': {
                        templateUrl: 'scripts/app/home/home.html',
                        controller: 'HomeController'
                    },
                    resolve: {}
                }
            });
    });
