(function() {
    'use strict';

    angular
        .module('hipToDoApp')
        .controller('OwnerDetailController', OwnerDetailController);

    OwnerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Owner', 'ToDo'];

    function OwnerDetailController($scope, $rootScope, $stateParams, previousState, entity, Owner, ToDo) {
        var vm = this;

        vm.owner = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('hipToDoApp:ownerUpdate', function(event, result) {
            vm.owner = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
