/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: app.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
'use strict';

var basePath = "../../..";

angular.module('clapseApp', ['ui.router', 'ngResource'])
    .run(function ($rootScope, $window, $state) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toParams;
        });

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;
            if (toState && toState.data && toState.data.pageTitle) {
                $window.document.title = toState.data.pageTitle;
            }
        });

    })
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise('/');
        $stateProvider.state('site', {
            abstract: true,
            views: {
                'navbar@': {
                    template: '<navbar></navbar>'
                },
                'footer@': {
                    template: '<footer></footer>'
                }
            }
        });
    });

