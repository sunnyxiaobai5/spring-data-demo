(function () {
    'use strict';

    angular.module('elapse.ui').directive('uiGrid', ['$http', function ($http) {
        // Runs during compile
        return {
            // name: '',
            // priority: 1,
            // terminal: true,
            // scope: {}, // {} = isolate, true = child, false/undefined = no change
            // controller: function($scope, $element, $attrs, $transclude) {},
            // require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
            restrict: 'AE', // E = Element, A = Attribute, C = Class, M = Comment
            // template: '<div>你好</div>',
            templateUrl: 'scripts/components/04-grid/ui-grid.html',
            replace: true,
            // transclude: true,
            // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
            link: function ($scope, iElm, iAttrs, controller) {

                $scope.options = {};

                $scope.sortBy = 'name';

                $scope.orderAsc = true;

                $scope.checkAllFlag = false;

                $scope.checkIds = [];

                $scope.checkItems = [];

                $scope.allItems = [];

                /************** 模拟数据 start **************/
                $scope.options.columns = [
                    {'field': 'id', 'displayName': 'id'},
                    {'field': 'name', 'displayName': '<b class="text-primary">姓名</b>'},
                    {'field': 'age', 'displayName': '年龄'}
                ];

                $scope.rowList = [
                    {'id': 1, 'name': 'name1', age: 54},
                    {'id': 2, 'name': 'name2', age: 56},
                    {'id': 4, 'name': 'name4', age: 67},
                    {'id': 3, 'name': 'name3', age: 51},
                    {'id': 5, 'name': 'name5', age: 48}
                ];

                angular.copy($scope.rowList, $scope.allItems);

                $scope.options.topActions = [
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
                ];

                $scope.options.botActions = [
                    {name: '导出'},
                    {name: '打印'},
                    {name: '删除'}
                ];
                /*************** 模拟数据 end ***************/

                /************** 控制逻辑 start **************/
                $scope.checkAll = function () {
                    $scope.checkIds = [];
                    $scope.checkItems = [];
                    angular.forEach($scope.rowList, function (row) {
                        row.checked = $scope.checkAllFlag;
                        if ($scope.checkAllFlag) {
                            $scope.checkIds.push(row.id);
                            $scope.checkItems.push(row);
                        }
                    });
                };

                $scope.checkOne = function () {
                    $scope.checkIds = [];
                    $scope.checkItems = $scope.rowList.filter(function (row) {
                        return row.checked;
                    });
                    $scope.checkAllFlag = $scope.checkItems.length === $scope.rowList.length;
                    $scope.checkItems.forEach(function (item) {
                        $scope.checkIds.push(item.id);
                    });

                    //for (var i = 0; i < $scope.rowList.length; i++) {
                    //    if (!$scope.rowList[i].checked) {
                    //        $scope.checkAllFlag = false;
                    //        return;
                    //    }
                    //}
                    //$scope.checkAllFlag = true;

                };

                $scope.changeSortField = function (field) {
                    if ($scope.sortBy !== field) {
                        $scope.sortBy = field;
                        $scope.orderAsc = true;
                    } else {
                        $scope.orderAsc = !$scope.orderAsc;
                    }
                    console.log($scope.sortBy)
                    console.log($scope.orderAsc)
                };
                /*************** 控制逻辑 end ***************/
            }
        };
    }]).filter('to_trusted', ['$sce', function ($sce) {
        return function (text) {
            console.log(text)
            console.log(typeof text)
            if (typeof text == 'string')
                return $sce.trustAsHtml(text);
            else
                return text;
        };
    }]);
})();