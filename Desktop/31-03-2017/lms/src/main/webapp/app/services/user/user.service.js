(function () {
    'use strict';

    angular
        .module('lmsApp')
        .factory('User', User);

    User.$inject = ['$resource'];

    function User ($resource) {
        var service = $resource('api/users/:login', {}, {
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
            'changePassword': { method: 'POST', url: 'api/user/password'},
            'getProfileImage': { method: 'GET', isArray: false, url: 'api/account/image'},
        });

        return service;
    }
})();