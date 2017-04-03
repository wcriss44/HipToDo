'use strict';

describe('Controller Tests', function() {

    describe('Owner Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOwner, MockToDo;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOwner = jasmine.createSpy('MockOwner');
            MockToDo = jasmine.createSpy('MockToDo');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Owner': MockOwner,
                'ToDo': MockToDo
            };
            createController = function() {
                $injector.get('$controller')("OwnerDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'hipToDoApp:ownerUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
