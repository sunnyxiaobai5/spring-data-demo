/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 *
 * <li>项目名称: spring-data-demo</li>
 * <li>文件名称: app.constants.js</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
(function () {
    'use strict';

    angular.module('elapseApp')
        .constant('VERSION', '0.0.1-SNAPSHOT')
        .constant('EXCEPTION_CODE', {
            'SYSTEM_EXCEPTION': 0,      //系统内部异常
            'COMMON_EXCEPTION': 1,      //自定义异常（全局处理）
            'BUSSINESS_EXCEPTION': 2    //自定义异常（自行处理）
        });
})();