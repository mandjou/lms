(function() {
    'use strict';

    angular
        .module('lmsApp')
        .controller('SettingsController', SettingsController);

    SettingsController.$inject = ['Principal', 'Auth', 'User', 'Upload', 'AuthServerProvider', '$timeout'];

    function SettingsController (Principal, Auth, User, Upload, AuthServerProvider, $timeout) {
        var vm = this;

        vm.error = null;
        vm.save = save;
        vm.settingsAccount = null;
        vm.success = null;
        
        vm.getUserImage = getUserImage;
        vm.uploadFiles = uploadFiles;
        
        vm.getUserImage();
        
        function getUserImage() {
        	User.getProfileImage().$promise.then(function(image){
        		vm.userImage = image;
        	}, function(error) {
        		vm.userImage = {};
        	});
        }
        
		function uploadFiles(files, errFiles) {
			vm.files = files;
			vm.errFiles = errFiles;
			angular.forEach(files, function(file) {
				file.upload = Upload.upload({
					url: 'api/account/image',
					data: {file: file}
					
				});

				file.upload.then(function (response) {
					$timeout(function () {
						file.result = response.data;
					});
					vm.getUserImage();
				}, function (response) {
					if (response.status > 0)
						vm.error = response.status + ': ' + response.data.error;
				}, function (evt) {
					file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
				});
			});		  
		}

        /**
         * Store the "settings account" in a separate variable, and not in the shared "account" variable.
         */
        var copyAccount = function (account) {
            return {
                activated: account.activated,
                email: account.email,
                firstName: account.firstName,
                langKey: account.langKey,
                lastName: account.lastName,
                login: account.login
            };
        };

        Principal.identity().then(function(account) {
            vm.settingsAccount = copyAccount(account);
        });

        function save () {
            Auth.updateAccount(vm.settingsAccount).then(function() {
                vm.error = null;
                vm.success = 'OK';
                Principal.identity(true).then(function(account) {
                    vm.settingsAccount = copyAccount(account);
                });
            }).catch(function() {
                vm.success = null;
                vm.error = 'ERROR';
            });
        }
    }
})();
