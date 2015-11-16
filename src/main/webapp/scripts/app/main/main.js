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
        });

    });
