(function() {
    'use strict';

    angular
        .module('lmsApp')
        .controller('UserManagementNewController', UserManagementNewController);

    UserManagementNewController.$inject = ['$state', '$stateParams', 'entity', 'User'];

    function UserManagementNewController ($state, $stateParams, entity, User) {
        var vm = this;
        
        vm.genders = ['MALE', 'FEMALE'];
        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_TEACHER'];
        vm.user = entity;
        
        console.log(vm.user);
        vm.save = save;
        
        function onSaveSuccess(result) {
        	console.log(result);
        	vm.isSaving = true;
        	$state.go('user-management');
        }
        
        function onSaveError() {
        	console.log('error');
        	vm.isSaving = false;
        }
        
        function save() {
        	console.log(vm.user);
            if (vm.user.id !== null) {
            	console.log('update process');
                User.update(vm.user, onSaveSuccess, onSaveError);
            } else {
            	console.log('add process');
                vm.user.langKey = 'en';
                User.save(vm.user, onSaveSuccess, onSaveError);
            }
        }
        
    }
})();