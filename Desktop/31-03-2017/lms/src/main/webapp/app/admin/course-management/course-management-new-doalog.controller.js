(function() {
    'use strict';

    angular
        .module('lmsApp')
        .controller('addCourseController', addCourseController);

    addCourseController.$inject = ['$uibModalInstance','$scope','$state', '$stateParams','entity','Course'];

     function addCourseController ($uibModalInstance,$scope,$state, $stateParams, entity, Course) {
        var vm = this;        
        vm.course={};        
        vm.course_type = ['Long Semester', 'Short Semester'];
        vm.course = entity;
         console.log("course controlller add");
             vm.save = save;
             vm.error=false;
             vm.clear = clear;
            
        
        function onSaveSuccess(result) {
        	console.log("saving",result);
        	vm.isSaving = true;
        	$uibModalInstance.close(true);
           }        
        function onSaveError() {
        	console.log('error');
        	vm.error=true;
        	vm.isSaving = false;
        }
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        function save() {
        	console.log("save ",vm.course);
            if (vm.course.id !== null) {
            	console.log('add process');
                Course.save(vm.course, onSaveSuccess, onSaveError);
            } else {
            	console.log('add process');
            	Course.save(vm.course, onSaveSuccess, onSaveError);
            }
        }
        
    }
})();