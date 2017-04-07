(function() {
    'use strict';

    angular
        .module('lmsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('course-management', {
            parent: 'admin',
            url: '/course-management?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Courses'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/course-management/course-management.html',
                    controller: 'CourseManagementController',
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
            } 
            })
           .state('course-management.new', {
            parent: 'course-management',
            url: '/newcourse',
            data: {
                authorities: ['ROLE_ADMIN']
            }, onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/course-management/course-management-new-doalog.html',
                    controller: 'addCourseController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Course', function(Course) {
                            return Course({login : $stateParams.login});
                        }]
                    }
                }).result.then(function() {
                	console.log ("course sAVED");
                    $state.go('course-management', null, { reload: true });
                }, function() {
                	console.log ("course NOT sAVED")
                    $state.go('course-management');
                });
            }]
        });        
               
    }
})();
