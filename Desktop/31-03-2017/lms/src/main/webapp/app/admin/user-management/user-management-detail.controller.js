(function() {
    'use strict';

    angular
        .module('lmsApp')
        .controller('UserManagementDetailController', UserManagementDetailController);

    UserManagementDetailController.$inject = ['$stateParams', 'User'];

    function UserManagementDetailController ($stateParams, User) {
        var vm = this;
        console.log('UserManagementDetailController', $stateParams.login);
        vm.load = load;
        vm.user = {};

        vm.load($stateParams.login);

        function load (login) {
            User.get({login: login}, function(result) {
            	console.log(result);
                vm.user = result;
            });
        }
    }
})();