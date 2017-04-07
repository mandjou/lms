(function() {
    'use strict';

    angular
        .module('lmsApp')
        .directive('lmsSortBy', lmsSortBy);

    function lmsSortBy() {
        var directive = {
            restrict: 'A',
            scope: false,
            require: '^lmsSort',
            link: linkFunc
        };

        return directive;

        function linkFunc(scope, element, attrs, parentCtrl) {
            element.bind('click', function () {
                parentCtrl.sort(attrs.lmsSortBy);
            });
        }
    }
})();
