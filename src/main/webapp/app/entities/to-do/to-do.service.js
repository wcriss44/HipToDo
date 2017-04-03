(function() {
    'use strict';
    angular
        .module('hipToDoApp')
        .factory('ToDo', ToDo);

    ToDo.$inject = ['$resource', 'DateUtils'];

    function ToDo ($resource, DateUtils) {
        var resourceUrl =  'api/to-dos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datePosted = DateUtils.convertLocalDateFromServer(data.datePosted);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datePosted = DateUtils.convertLocalDateToServer(copy.datePosted);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datePosted = DateUtils.convertLocalDateToServer(copy.datePosted);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
