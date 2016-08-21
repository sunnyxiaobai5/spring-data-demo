(function () {
    'use strict';

    angular.module('elapse.ui').directive('uiUploader', ['$http', '$cookies', function ($http, $cookies) {
        return {
            // scope: {}, // {} = isolate, true = child, false/undefined = no change
            // controller: function($scope, $element, $attrs, $transclude) {},
            // require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
            restrict: 'AE', // E = Element, A = Attribute, C = Class, M = Comment
            // template: '<div>你好</div>',
            templateUrl: 'scripts/components/08-uploader/ui-uploader.html',
            // replace: true,
            // transclude: true,
            // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
            link: function ($scope, iElm, iAttrs, controller) {

                $scope.filesInfo = [];

                var uploader = WebUploader.create({
                    //{Boolean}[可选][默认值：false] 是否自动上传
                    auto: false,

                    //swf文件路径
                    swf: 'js/Uploader.swf',

                    //{Boolean}[可选][默认值：false] 是否开启分片上传
                    chunked: true,

                    //{Boolean}[可选][默认值：5242880] 分片大小Byte
                    chunkSize: 1024 * 1024 * 5,

                    //{Boolean}[可选][默认值：2] 如果某个分片由于网络问题出错，自动重传次数
                    chunkRetry: 2,

                    //{Boolean}[可选][默认值：3] 上传并发数，允许同时最大上传进程数
                    threads: 3,

                    //{Object}[可选][默认值：{}] 文件上传请求的参数表，每次发送都会发送此对象中的参数
                    formData: {
                        _csrf: $cookies.get('CSRF-TOKEN')
                    },

                    //{Object}[可选][默认值：'file'] 设置文件上传域的name
                    fileVal: 'file',

                    //{Object}[可选][默认值：'POST'] 文件上传方式，POST或者GET
                    method: 'POST',

                    //{int}[可选][默认值：undefined] 验证文件总数量, 超出则不允许加入队列。
                    fileNumLimit: 10,

                    //{int}[可选][默认值：undefined] 验证文件总大小是否超出限制, 超出则不允许加入队列。
                    // fileSizeLimit:

                    //{int}[可选][默认值：undefined] 验证单个文件大小是否超出限制, 超出则不允许加入队列。
                    // fileSingleSizeLimit:

                    //上传地址
                    server: 'attachment/uploadToServer',

                    //选择文件的按钮。可选。
                    //内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick: {
                        id: '#picker',
                        innerHTML: '选择文件',
                        multiple: true
                    },
                    accept: {
                        //title: 'Images',
                        //extensions: 'gif,jpg,jpeg,bmp,png',
                        //mimeTypes: 'image/*'
                    },
                    //不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                    resize: false
                });

                // 当有文件被添加进队列的时候
                uploader.on('fileQueued', function (file) {
                    $scope.filesInfo.push(file);
                    file.setStatus('inited', "等待上传...");
                    $scope.$apply();
                });

                // 文件上传过程中创建进度条实时显示。
                uploader.on('uploadProgress', function (file, percentage) {
                    var $li = $('#' + file.id), $percent = $li.find('.progress .progress-bar');

                    // 避免重复创建
                    if (!$percent.length) {
                        $percent = $('<div class="progress progress-striped active">' +
                            '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                            '</div>' +
                            '</div>').appendTo($li).find('.progress-bar');
                    }

                    $percent.css('width', percentage * 100 + '%');

                    file.setStatus('progress', "上传中");
                    $scope.$apply();
                });

                //上传成功
                uploader.on('uploadSuccess', function (file, attachmentInfo) {
                    angular.element('#' + file.id).find('p.state').addClass('text-success');
                    file.setStatus('complete', "上传成功");
                    $scope.$apply();
                    //若已分片，则合并
                    if (attachmentInfo.chunked) {
                        $http.post("attachment/mergeToServer", attachmentInfo);
                    }
                });

                //上传失败
                uploader.on('uploadError', function (file) {
                    angular.element('#' + file.id).find('p.state').addClass('text-danger');
                    file.setStatus('error', "上传失败");
                    $scope.$apply();
                });

                //上传完成
                uploader.on('uploadComplete', function (file) {
                    angular.element('#' + file.id).find('.progress').fadeOut();
                });

                //手动上传
                $scope.upload = function () {
                    uploader.upload();
                };

                //删除文件
                $scope.delete = function (fileId, index) {
                    uploader.removeFile(fileId, true);
                    $scope.filesInfo.splice(index, 1);
                };

                //取消并中断文件上传
                $scope.cancel = function (fileId) {
                    uploader.cancelFile(fileId);
                };

                //重试
                $scope.retry = function (fileId) {
                    uploader.retry(fileId);
                };
            }
        };
    }]);
})();