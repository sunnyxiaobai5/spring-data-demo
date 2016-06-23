(function () {
    'use strict';

    angular.module('elapse.ui').directive('uiGrid', [function () {
        // Runs during compile
        return {
            // name: '',
            // priority: 1,
            // terminal: true,
            scope: {
                options: '=',
                ngModel: '='
            }, // {} = isolate, true = child, false/undefined = no change
            require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
            restrict: 'AE',
            templateUrl: 'scripts/components/04-grid/ui-grid.html',
            replace: true,
            // transclude: true,
            controller: function ($scope, $element, $attrs, $transclude) {
            },
            link: function ($scope, iElement, iAttrs, ngModelCtrl) {

                $scope.$watch('ngModel', function (newValue, oldValue) {
                    $scope.rowList = $scope.ngModel;

                    $scope.allItems = angular.copy($scope.rowList);
                }, true);

                //默认ID排序
                var defaultOptions = {
                    sortBy: 'id'
                };

                //合并配置
                $scope.options = angular.extend({}, defaultOptions, $scope.options);

                //默认升序
                $scope.orderAsc = true;

                //是否全部选中
                $scope.checkAllFlag = false;

                //选中的ID
                $scope.checkIds = [];

                //选中的数据
                $scope.checkItems = [];

                /************** 控制逻辑 start **************/
                /**
                 * 选中全部
                 */
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

                /**
                 * 选中一个
                 */
                $scope.checkOne = function () {
                    $scope.checkIds = [];
                    $scope.checkItems = $scope.rowList.filter(function (row) {
                        return row.checked;
                    });
                    $scope.checkAllFlag = $scope.checkItems.length === $scope.rowList.length;
                    $scope.checkItems.forEach(function (item) {
                        $scope.checkIds.push(item.id);
                    });
                };

                /**
                 * 改变排序字段
                 * @param field
                 */
                $scope.changeSortField = function (field) {
                    if ($scope.options.sortBy !== field) {
                        $scope.options.sortBy = field;
                        $scope.orderAsc = true;
                    } else {
                        $scope.orderAsc = !$scope.orderAsc;
                    }
                };
                /*************** 控制逻辑 end ***************/
            }
        };
    }]).filter('to_trusted', ['$sce', function ($sce) {
        return function (text) {
            if (typeof text === 'string') {
                return $sce.trustAsHtml(text);
            } else {
                return text;
            }
        };
    }]);
})();