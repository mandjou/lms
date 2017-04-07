(function() {
    'use strict';

    angular
        .module('lmsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-management', {
            parent: 'admin',
            url: '/user-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Users'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/user-management/user-management.html',
                    controller: 'UserManagementController',
                    controllerAs: 'vm'
                }
            },            
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                }
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort)
                    };
                }]
            }        })
        .state('user-management.new', {
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/user-management/user-management-new.html',
                    controller: 'UserManagementNewController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	entity: function() {
            		return {
            			id: null, login: null, firstName: null, lastName: null, email: null,
            			activated: true, langKey: null, gender: null, nationality: null,
            			faculty: null, dateOfBirth: null, address: null, phoneNumber: null,
            			passportNumber: null, createdBy: null, createdDate: null,
                        lastModifiedBy: null, lastModifiedDate: null, resetDate: null,
                        resetKey: null, authorities: null
            			
            		};
            	}
            }

        })
        .state('user-management.edit', {
            url: '/{login}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/user-management/user-management-edit.html',
                    controller: 'UserManagementNewController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            	entity: function($stateParams, User) {
            		return User.get({login : $stateParams.login});
            	}
            }
        })
        .state('user-management-detail', {
            parent: 'user-management',
            url: '/user/{login}',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'lms'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/user-management/user-management-detail.html',
                    controller: 'UserManagementDetailController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('user-management.delete', {
            url: '/{login}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/user-management/user-management-delete-dialog.html',
                    controller: 'UserManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['User', function(User) {
                            return User.get({login : $stateParams.login});
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-management.password', {
            parent: 'user-management',
            url: '/{login}/password',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/user-management/user-management-password-dialog.html',
                    controller: 'UserManagementPasswordController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['User', function(User) {
                            return User.get({login : $stateParams.login});
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-management', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });        
    }
})();
