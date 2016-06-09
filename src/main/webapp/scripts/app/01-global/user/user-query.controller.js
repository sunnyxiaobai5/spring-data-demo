(function () {
    'use strict';

    angular.module('elapseApp').controller('UserController', ['$scope', 'User', 'Notify', function ($scope, User, Notify) {

        $scope.options = {
            columns: [
                {field: 'id', displayName: 'id'},
                {field: 'name', displayName: '<b class="text-primary">姓名</b>'},
                {field: 'age', displayName: '年龄'}
            ],
            topActions: [
                {
                    name: '导出',
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
                    dropdown: [
                        {
                            name: '打印所选',
                            handler: function (ids) {
                                Notify.alert('打印失败！', '没有选择数据！');
                            }
                        },
                        {name: '打印全部'}
                    ]
                },
                {
                    name: '删除',
                    handler: function (ids) {
                        if (ids.length === 0) {
                            Notify.notify('请至少勾选一项！');
                            return;
                        }
                        Notify.confirm('确认要删除所选吗？', '删除后无法恢复！');
                    }
                }
            ],
            botActions: [
                {
                    name: '导出',
                    handler: function (ids, checkItems, allItems, event) {
                        console.log('导出');
                        console.log(ids);
                        console.log(checkItems);
                        console.log(allItems);
                        console.log(event);
                    }
                },
                {name: '打印'},
                {name: '删除'}
            ],
            rowActions: [{
                disabled: true,
                name: '查看',
                handler: function () {
                    console.log('查看');
                }
            }, {
                disabled: false,
                name: '编辑',
                handler: function () {
                    console.log('编辑');
                }
            }, {
                name: '删除',
                handler: function () {
                    console.log('删除');
                }
            }],
            sortBy: 'name'
        };

        $scope.users = [];
        User.query({number: 0, size: 20}, function (result) {
            angular.forEach(result, function (user) {
                $scope.users.push(user);
            });
        });
    }]);
})();
