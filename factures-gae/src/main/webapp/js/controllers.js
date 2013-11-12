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
//        $scope.individualCosts = test_costs
        var p = $http.get('rest/individualcost/year/2013/');

        p.success(function(data) {

            $scope.$log.info(data);
            $scope.individualCosts = data
        });
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

        $scope.goToIndividualCostList = function() {

            $location.path("/individualcost/list")
            updatePlace("individualCostList")
        }

        $scope.place = {
            'newBudgetPlace' : false,
            'newIndividualCostPlace' : true,
            'individualCostList' : false
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

//        updatePlace($location.path())

    }]);


var test_costs = [{"id":4644337115725824,"cost":43,"concept":"gfbfgggg","budgetId":null,"tags":null,"date":1384296901992},{"id":4785074604081152,"cost":4,"concept":"gnfn","budgetId":null,"tags":"gfhgf","date":1384119539033},{"id":5066549580791808,"cost":5,"concept":"fgtn","budgetId":null,"tags":null,"date":1384109997075},{"id":5770237022568448,"cost":43,"concept":"gfbfgggg","budgetId":null,"tags":null,"date":1384296901992},{"id":5910974510923776,"cost":null,"concept":"","budgetId":null,"tags":null,"date":1384124712827},{"id":6192449487634432,"cost":2,"concept":"fdgdfg","budgetId":null,"tags":null,"date":1384119193436},{"id":6473924464345088,"cost":43,"concept":"gfbfgggg","budgetId":null,"tags":null,"date":1384296901992}]










































