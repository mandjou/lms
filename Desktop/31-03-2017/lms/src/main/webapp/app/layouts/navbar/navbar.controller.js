(function() {
    'use strict';

    angular
        .module('lmsApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal'];

    function NavbarController ($state, Auth, Principal) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;
        vm.account = {};
        
        Principal.identity().then(function(account){
        	vm.account = account;
        });

        function login() {
            collapseNavbar();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('default', {}, {reload: true});
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }
    }
})();