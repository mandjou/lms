(function () {
    'use strict';

    angular
        .module('lmsApp')
        .factory('Course', Course);

    Course.$inject = ['$resource'];

    function Course ($resource) {
        var service = $resource('api/course', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' },
            'update': { method:'PUT' },
            'delete':{ method:'DELETE'},
          
        });

        return service;
    }
})();