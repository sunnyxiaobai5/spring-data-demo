'use strict';
/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: login.js.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/

angular.module('clapseApp')
    .config(function ($stateProvider) {
        $stateProvider.state('login', {
            parent: 'account',
            url: '/login',
            views: {
                'content@': {
                    templateUrl: 'scripts/app/account/login/login.html',
                    controller: 'LoginController'
                },
                resolve: {}
            }
        });
    });