/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: error.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

angular.module('elapseApp')
    .config(function ($stateProvider) {

        $stateProvider
            .state('404', {
                url: '/404',
                data: {
                    pageTitle: 'Page Not Found'
                },
                views: {
                    '': {
                        templateUrl: 'scripts/app/error/404.html'
                    }
                }
            })
            .state('500', {
                url: '/500',
                data: {
                    pageTitle: 'Internal Server Error'
                },
                views: {
                    '': {
                        templateUrl: 'scripts/app/error/500.html'
                    }
                }
            });
    });