(function () {
    'use strict';

    angular.module('elapse.ui')
        .constant('AUTH', {
            ADD: {
                key: 1,
                name: '添加',
                icon: 'glyphicon glyphicon-plus'
            },
            DELETE: {
                key: 2,
                name: '删除',
                icon: 'glyphicon glyphicon-remove'
            },
            UPDATE: {
                key: 3,
                name: '修改',
                icon: 'glyphicon glyphicon-edit'
            },
            QUERY: {
                key: 4,
                name: '查询',
                icon: 'glyphicon glyphicon-search'
            },
            IMPORT: {
                key: 5,
                name: '导入',
                icon: 'glyphicon glyphicon-import'
            },
            EXPORT: {
                key: 6,
                name: '导出',
                icon: 'glyphicon glyphicon-export'
            },
            PRINT: {
                key: 7,
                name: '打印',
                icon: 'glyphicon glyphicon-print'
            },
            VIEW: {
                key: 8,
                name: '查看',
                icon: 'glyphicon glyphicon-eye-open'
            }
        })
        .constant('UPLOADER', {
            //TODO 上传文件前判断文件大小是否超出（改值与后端配置相同）
            MAX_SIZE: 2 * 1024 * 1024 * 1024    //2G
        });
})();