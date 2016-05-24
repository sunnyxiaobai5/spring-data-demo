(function () {
    'use strict';

    angular.module('elapse.ui').directive('uiGrid', ['$http', function ($http) {
        // Runs during compile
        return {
            // name: '',
            // priority: 1,
            // terminal: true,
            scope: {
                options: '=',
                ngModel: '='
            }, // {} = isolate, true = child, false/undefined = no change
            // controller: function($scope, $element, $attrs, $transclude) {},
            require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
            restrict: 'AE', // E = Element, A = Attribute, C = Class, M = Comment
            // template: '<input type="text"/>',
            templateUrl: 'scripts/components/04-grid/ui-grid.html',
            replace: true,
            // transclude: true,
            // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
            link: function ($scope, iElement, iAttrs, ngModel) {

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

                //所有数据
                $scope.allItems = [];

                //传入的数据
                $scope.rowList = $scope.ngModel;

                angular.copy($scope.rowList, $scope.allItems);

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
                    console.log($scope.options.sortBy)
                    console.log($scope.orderAsc)
                };
                /*************** 控制逻辑 end ***************/
            }
        };
    }]).filter('to_trusted', ['$sce', function ($sce) {
        return function (text) {
            // console.log(text)
            // console.log(typeof text)
            if (typeof text == 'string')
                return $sce.trustAsHtml(text);
            else
                return text;
        };
    }]);
})();