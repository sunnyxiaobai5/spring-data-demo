'use strict';
/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: main.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
angular.module('clapseApp')
    .config(function ($stateProvider) {
        $stateProvider.state('home', {
            parent: 'site',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html',
                    //controller: 'MainController'
                },
                resolve: {}
            }
        }).state('system1', {
            parent: 'site',
            url: '/system1',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system2', {
            parent: 'site',
            url: '/system2',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system3', {
            parent: 'site',
            url: '/system3',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        }).state('system4', {
            parent: 'site',
            url: '/system4',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/main/main.html'
                }
            }
        });

    });
