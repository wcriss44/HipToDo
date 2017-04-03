(function() {
    'use strict';

    angular
        .module('hipToDoApp')
        .controller('ToDoController', ToDoController);

    ToDoController.$inject = ['ToDo'];

    function ToDoController(ToDo) {

        var vm = this;

        vm.toDos = [];

        loadAll();

        function loadAll() {
            ToDo.query(function(result) {
                vm.toDos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
