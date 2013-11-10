'use strict';

facturesApp.controller('NewIndividualCostController', ['$scope', '$http', '$log',
    function NewIndividualCostController($scope, $http, $log) {
        $scope.$log = $log;

        $scope.save = function(cost) {
            $http.put('rest/individualcost/', cost).success(function(data) {
                $scope.$log.info(data);
                $scope.resetNewIndividualCostForm()
            });
        };

        var p = $http.get('rest/budget/');

        p.success(function(data) {

            $scope.$log.info(data);
            $scope.budgets = data;

        }).error(function(data, status, headers, config) {

                if(status===401){
                    window.location = data;
                }

            });;

        $scope.saveBg = function(bg) {
            $http.put('rest/budget/', bg).success(function(data) {
                $scope.$log.info(data);
                $scope.resetBgForm();
            })
        };

        $scope.resetNewIndividualCostForm = function() {
            $scope.ic = {
                'date' : new Date().getTime(),
                'cost' : "",
                'concept' : "",
                'budgetId' : null,
                'tags': null
            };
        }

        $scope.resetBgForm = function() {
            $scope.bg = {
                'year' : new Date().getFullYear() + "",
                'amount' : "",
                'name' : ""
            };
        }

        $scope.resetBgForm()
        $scope.resetNewIndividualCostForm()
    }]);

facturesApp.controller('ListCostsController', ['$scope', '$http', '$log',
    function ListCostsController($scope, $http, $log) {
        $scope.$log = $log;
    }]);

facturesApp.controller("NavigationController", ['$scope', '$location',
    function NavigationController($scope, $location) {

        $scope.goToNewIndividualCost = function() {

            $location.path("/individualcost/new")
            updatePlace("newIndividualCostPlace")

        }

        $scope.goToNewBudget = function() {

            $location.path("/budget/new")
            updatePlace("newBudgetPlace")
        }

        $scope.place = {
            'newBudgetPlace' : false,
            'newIndividualCostPlace' : true
        }

        var updatePlace = function(newPlace) {
            for (var place in $scope.place) {
                if( place === newPlace) {
                    $scope.place[place] = true;
                } else {
                    $scope.place[place] = false;
                }

            }
        }

    }]);













































