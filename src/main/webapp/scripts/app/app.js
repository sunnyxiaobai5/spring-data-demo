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
(function () {
    'use strict';

    angular.module('elapseApp', ['ui.router', 'ngResource', 'elapse.ui'])
        .run(function ($rootScope, $window, AUTH) {
            $rootScope.AUTH = AUTH;

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

            //enable CSRF
            $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
            $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

            $httpProvider.interceptors.push('httpInterceptor');

            $urlRouterProvider.otherwise('/home');

        })
        .factory('httpInterceptor', function ($q, $injector, EXCEPTION_CODE) {

            return {
                'request': function (config) {
                    // do something on success
                    return config;
                },

                'requestError': function (rejection) {
                    // do something on error
                    // if (canRecover(rejection)) {
                    //     return responseOrNewPromise
                    // }
                    return $q.reject(rejection);
                },

                'response': function (response) {
                    // do something on success
                    return response;
                },

                'responseError': function (rejection) {
                    var $state = $injector.get('$state');
                    var Notify = $injector.get('Notify');
                    if (rejection.status == 404) {
                        $state.go('404');
                        $q.reject(rejection);
                        return;
                    }
                    var header = rejection.headers();

                    switch (Number(header.code)) {
                        case EXCEPTION_CODE.SYSTEM_EXCEPTION:
                            Notify.alert("服务器内部错误", "请联系管理员");
                            break;
                        case EXCEPTION_CODE.COMMON_EXCEPTION:
                            Notify.alert("操作失败！原因如下", rejection.data.message);
                            break;
                        case EXCEPTION_CODE.BUSSINESS_EXCEPTION:
                            Notify.notify(rejection.data.message);
                            break;
                        default:
                            $state.go('500');
                            break;
                    }

                    return $q.reject(rejection);
                }
            };
        });
})();

