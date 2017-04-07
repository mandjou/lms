(function() {
    'use strict';

    angular
        .module('lmsApp')
        .controller('UserManagementPasswordController', UserManagementPasswordController);

    UserManagementPasswordController.$inject = ['$uibModalInstance', 'entity', 'User'];

    function UserManagementPasswordController ($uibModalInstance, entity, User) {
        var vm = this;
        
        vm.doNotMatch = null;
        vm.error = null;
        vm.user = entity;
        vm.clear = clear;
        vm.changePassword = changePassword;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        function changePassword(login) {
        	if (vm.password !== vm.confirmPassword) {
        		vm.error = null;
        		vm.success = null;
        		vm.doNotMatch = 'ERROR';
        	} else {
        		vm.doNotMatch = null;
        		vm.user.password = vm.password;
        		User.changePassword(vm.user).$promise.then(function(){
        			vm.error = null;
        			$uibModalInstance.close(true);
        		}).catch(function(){
        			vm.error = 'ERROR';
        		});
        	}
        }
    }
})();