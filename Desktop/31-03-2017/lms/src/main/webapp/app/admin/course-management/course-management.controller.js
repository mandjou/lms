(function() {
	'use strict';

	angular
	.module('lmsApp')
	.controller('CourseManagementController', CourseManagementController);

	CourseManagementController.$inject = ['Principal', '$scope','Course', 'ParseLinks', 'AlertService', '$state', 'pagingParams', 'paginationConstants'];
	function CourseManagementController(Principal,$scope, Course, ParseLinks, AlertService, $state, pagingParams, paginationConstants) {
		var vm = this;
		console.log("controller for course")

		vm.setActive = setActive;
		vm.loadAll = loadAll;
		vm.courses = [];
		vm.page = 1;
		vm.totalItems = null;
		vm.clear = clear;
		vm.links = null;
		vm.loadPage = loadPage;
		vm.predicate = pagingParams.predicate;
		vm.reverse = pagingParams.ascending;
		vm.itemsPerPage = paginationConstants.itemsPerPage;
		vm.transition = transition;

		vm.loadAll();
//		Principal.identity().then(function(account) {
//			vm.currentAccount = account;
//		});

		function setActive (course, isActivated) {
			course.activated = isActivated;
			Course.update(course, function () {
				vm.loadAll();
				vm.clear();
			});
			
		}

		function loadAll () {	
			
			Course.query({
				page: pagingParams.page - 1,
				size: vm.itemsPerPage,
				sort: sort()
				
			}, onSuccess, onError);
			
		}

		function onSuccess(data, headers) {
			
			vm.links = ParseLinks.parse(headers('link'));
			vm.totalItems = headers('X-Total-Count');
			vm.queryCount = vm.totalItems;
			vm.page = pagingParams.page;
			vm.course = data;
		}

		function onError(error) {
			
			AlertService.error(error.data.message);
		}
		function clear () {
			vm.course = {
					id: null, course_name: null, course_type: null, course_faculty: null, course_Dept: null,
					activated: null,  createdBy: null, createdDate: null,
					lastModifiedBy: null, lastModifiedDate: null,

			};
		}

		function sort () {
			var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
			if (vm.predicate !== 'id') {
				result.push('id');
			}
			return result;
		}

		function loadPage (page) {
			vm.page = page;
			vm.transition();			
			console.log(" loadAll ", page);
		}

		function transition () {
			$state.transitionTo($state.$current, {
				page: vm.page,
				sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
				search: vm.currentSearch
			});
		}
	}

})();
