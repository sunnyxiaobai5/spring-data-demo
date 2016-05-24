(function () {
    'use strict';

    angular.module('elapseApp').controller('UserController', ['$scope', function ($scope) {

        $scope.options = {
            columns: [
                {'field': 'id', 'displayName': 'id'},
                {'field': 'name', 'displayName': '<b class="text-primary">姓名</b>'},
                {'field': 'age', 'displayName': '年龄'}
            ],
            topActions: [
                {
                    name: '导出',
                    action: function (ids, checkItems, allItems, event) {
                        console.log(ids);
                        console.log(checkItems);
                        console.log(allItems);
                    }
                },
                {
                    name: '打印',
                    dropdowns: [
                        {'name': '打印所选'},
                        {'name': '打印全部'}
                    ]
                },
                {name: '删除'},
                {name: '审核'}
            ],
            botActions: [
                {name: '导出'},
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

        $scope.users = [
            {'id': 1, 'name': 'name1', age: 54},
            {'id': 2, 'name': 'name2', age: 56},
            {'id': 4, 'name': 'name4', age: 67},
            {'id': 3, 'name': 'name3', age: 51},
            {'id': 5, 'name': 'name5', age: 48}
        ];
    }]);
})();
