'use strict';

facturesApp.controller('NewIndividualCostController', ['$scope', '$http', '$log', '$injector','$q',
    function NewIndividualCostController($scope, $http, $log, $injector, $q) {
        var rest = $injector.get("rest");
        $scope.$log = $log;

        $scope.save = function(cost) {
            rest.individualCostService($http).saveIndividualCost(cost).success(function(data) {
                $scope.$log.info(data);
                $scope.resetNewIndividualCostForm()
            });
        };

        rest.bufferRestServices($http).getBudgetsForYear(2013, function(data) {

            $scope.$log.info(data);
            $scope.budgets = data;

        });

        $scope.saveBg = function(bg) {
            rest.bufferRestServices($http).addBudget(bg).success(function(data) {
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

facturesApp.controller('ListCostsController', ['$scope', '$http', '$log', "$injector",
    function ListCostsController($scope, $http, $log, $injector) {
        $scope.pageNo = 0;
        $scope.pageSize = 2000000;
        $scope.$log = $log;

        var rest = $injector.get("rest");
//        $scope.individualCosts = test_costs

        rest.individualCostService($http).getIndividualCosts(2013, function(data) {
            $scope.$log.info(data);
            $scope.individualCosts = data
        });

        $scope.sort = function(fieldName) {
            if($scope.sortField === fieldName) {
                $scope.reverse = !$scope.reverse;
            } else {
                $scope.sortField = fieldName;
                $scope.reverse = false;
            }
        }

        $scope.isSortUp = function(fieldName) {
            return $scope.sortField === fieldName && !$scope.reverse;
        };

        $scope.isSortDown = function(fieldName) {
            return $scope.sortField === fieldName && $scope.reverse;
        };


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










































