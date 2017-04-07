(function() {
    'use strict';

    angular
        .module('lmsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('default', {
            parent: 'app',
            url: '/default',
            data: {
                authorities: [],
                pageTitle: 'LMS Login'
            },
            views: {
                'content@': {
                    templateUrl: 'app/default/default.html',
                    controller: 'DefaultController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();