(function () {
    'use strict';

    angular.module('elapseApp').controller('UserController', ['$scope', 'User', '$http', function ($scope, User, $http) {

        $scope.options = {
            columns: [
                {field: 'id', displayName: 'id'},
                {field: 'name', displayName: '<b class="text-primary">姓名</b>'},
                {field: 'age', displayName: '年龄'}
            ],
            topActions: [
                {
                    name: '导出',
                    handler: function (ids, checkItems, allItems, event) {
                        if (ids.length === 0) {
                            window.alert("请选择！");
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
                            handler: function (ids, checkItems, allItems, event) {
                                console.log('打印所选');
                                console.log(ids);
                                console.log(checkItems);
                                console.log(allItems);
                                console.log(event);
                            }
                        },
                        {name: '打印全部'}
                    ]
                },
                {name: '删除'},
                {name: '审核'}
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
