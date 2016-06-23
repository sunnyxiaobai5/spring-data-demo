(function () {
    'use strict';

    angular.module('elapseApp').controller('UserController', ['$scope', 'User', 'Notify', 'AUTH', function ($scope, User, Notify, AUTH) {

        $scope.users = [];

        //列定义
        var columns = [
            {field: 'id', displayName: 'id'},
            {field: 'name', displayName: '<b class="text-primary">姓名</b>'},
            {field: 'age', displayName: '年龄'}
        ];
        //顶部操作
        var topActions = [
            {
                name: '新增',
                auth: AUTH.ADD,
                handler: function () {
                }
            },
            {
                name: '删除',
                auth: AUTH.DELETE,
                handler: function (ids) {
                    if (ids.length === 0) {
                        Notify.notify('请至少勾选一项！');
                        return;
                    }
                    Notify.confirm('确认要删除所选吗？', '删除后无法恢复！');
                }
            }
        ];
        //行操作
        var rowActions = [
            {
                disabled: true,
                name: '查看',
                auth: AUTH.VIEW,
                handler: function () {
                    console.log('查看');
                }
            },
            {
                disabled: false,
                name: '编辑',
                auth: AUTH.UPDATE,
                handler: function () {
                    console.log('编辑');
                }
            },
            {
                name: '删除',
                auth: AUTH.DELETE,
                handler: function () {
                    console.log('删除');
                }
            }
        ];
        //底部操作
        var botActions = [
            {
                name: '导出',
                auth: AUTH.EXPORT,
                handler: function (ids) {
                    if (ids.length === 0) {
                        Notify.notify('请至少勾选一项！');
                        return;
                    }
                    window.open("user/exportExcel?ids=" + ids);
                }
            },
            {
                name: '打印',
                auth: AUTH.PRINT,
                dropdown: [
                    {
                        name: '打印所选',
                        handler: function (ids) {
                            Notify.alert('打印失败！', '没有选择数据！');
                        }
                    },
                    {name: '打印全部'}
                ]
            }
        ];

        //grid配置
        $scope.options = {
            columns: columns,
            sortBy: 'name',
            topActions: topActions,
            rowActions: rowActions,
            botActions: botActions
        };

        $scope.refresh = function () {
            $scope.users = [];
            User.query({number: 0, size: 20}, function (result) {
                angular.forEach(result, function (user) {
                    $scope.users.push(user);
                });
            });
        };

        $scope.refresh();
    }]);
})();
