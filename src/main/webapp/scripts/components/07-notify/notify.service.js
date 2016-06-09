(function () {
    'use strict';
    angular.module('elapse.ui').factory('Notify', ['ngDialog', function (ngDialog) {
        var dialog;
        return {
            /**
             * 提示框
             * @param content 内容
             */
            notify: function (content) {
                if (dialog) {
                    dialog.close();
                }
                dialog = ngDialog.open({
                    template: content,
                    plain: true,
                    showClose: false,
                    overlay: false,
                    closeByDocument: false,
                    closeByEscape: false
                });
                setTimeout(function () {
                    dialog.close();
                }, 1000);
            },
            /**
             * 警告框
             * @param title 标题
             * @param content 内容
             * @param callback 确认回调函数
             */
            alert: function (title, content, callback) {
                ngDialog.openConfirm({
                    template: '<div class="clearfix"><div><b>' + title + '</b></div>' +
                    '<div>' + content + '</div>' +
                    '<div class="pull-right"><button class="btn btn-sm btn-primary margin-right-10" ng-click="close()">确定</button>' +
                    '</div></div>',
                    plain: true,
                    closeByDocument: false,
                    closeByEscape: true,
                    controller: ['$scope', function ($scope) {
                        $scope.close = function () {
                            (callback || angular.noop)();
                            $scope.closeThisDialog();
                        };
                    }]
                });
            },
            /**
             * 确认框
             * @param title 标题
             * @param content 内容
             * @param ok 确认回调函数
             * @param cancel 取消回调函数
             */
            confirm: function (title, content, ok, cancel) {
                ngDialog.openConfirm({
                    template: '<div class="clearfix"><div><b>' + title + '</b></div>' +
                    '<div>' + content + '</div>' +
                    '<div class="pull-right"><button class="btn btn-sm btn-primary margin-right-10" ng-click="ok()">确定</button>' +
                    '<button class="btn btn-sm btn-default" ng-click="cancel()">取消</button></div></div>',
                    plain: true,
                    closeByDocument: false,
                    closeByEscape: true,
                    controller: ['$scope', function ($scope) {
                        $scope.ok = function () {
                            (ok || angular.noop)();
                            $scope.closeThisDialog();
                        };

                        $scope.cancel = function () {
                            (cancel || angular.noop)();
                            $scope.closeThisDialog();
                        };
                    }]
                });
            }
        };
    }]);
})();

