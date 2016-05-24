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
(function () {
    'use strict';

    angular.module('elapseApp')
        .config(function ($stateProvider) {

            $stateProvider
                .state('SITE_3', {
                    parent: 'main',
                    url: '/SITE_3',
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
})();