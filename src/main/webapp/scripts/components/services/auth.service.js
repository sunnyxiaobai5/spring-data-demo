/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: auth.service.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapseApp').factory('Auth', ['$resource', function () {
        var identity, _authenticated = false;

        return {
            login: function () {
                _authenticated = true;
                console.log("login");
            },

            logout: function () {
                _authenticated = false;
                console.log("logout");
            },

            isAuthenticated: function () {
                return _authenticated;
            },

            createAccount: function () {
                console.log("createAccount");
            },

            updateAccount: function () {
                console.log("updateAccount");
            },

            activeAccount: function () {
                console.log("activeAccount");
            },

            changePassword: function () {
                console.log("changePassword");
            }
        };
    }]);
})();
