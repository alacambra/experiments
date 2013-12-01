'use strict';

facturesApp.controller('NewIndividualCostController', ['$scope', '$http', '$log', '$injector','$q',
    function NewIndividualCostController($scope, $http, $log, $injector, $q) {
        var rest = $injector.get("rest");
        var helper = $injector.get("helper");
        $scope.$log = $log;

        $scope.save = function(cost) {
            var clonedCost = helper.clone(cost);
            clonedCost.date = helper.convertDateToTimeStamp(clonedCost.date);
            rest.individualCostService($http).saveIndividualCost(clonedCost).success(function(data) {
                $scope.$log.info(data);
                $scope.resetNewIndividualCostForm();
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
            });
        };

        $scope.resetNewIndividualCostForm = function() {
            
            var today = new Date();
            
            $scope.ic = {
                'date' : today.getDate() + "-" + today.getMonth() + "-" + today.getFullYear(),
                'cost' : "",
                'concept' : "",
                'budgetId' : null,
                'tags': null
            };
        };

        $scope.resetBgForm = function() {
            $scope.bg = {
                'year' : new Date().getFullYear() + "",
                'amount' : "",
                'name' : ""
            };
        };

        $scope.resetBgForm();
        $scope.resetNewIndividualCostForm();
    }]);

facturesApp.controller('NewPeriodicCostController', ['$scope', '$http', '$log', '$injector','$q',
    function NewIndividualCostController($scope, $http, $log, $injector, $q) {
        
        var rest = $injector.get("rest");
        var helper = $injector.get("helper");
        $scope.$log = $log;

        $scope.save = function(cost) {
            cost = helper.clone(cost);
            cost.start = helper.convertDateToTimeStamp(cost.start);
            cost.end = helper.convertDateToTimeStamp(cost.end);
            
            rest.periodicCostService($http).savePeriodicCost(cost).success(function(data) {
                $scope.$log.info(data);
                $scope.resetNewIndividualCostForm();
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
            });
        };

        $scope.resetNewIndividualCostForm = function() {
            
            var today = new Date();
            
            $scope.pc = {
                'start' : "01-01-" + today.getFullYear(),
                'end' : "31-12-" + today.getFullYear(),
                'cost' : "",
                'concept' : "",
                'budgetId' : null,
                'isFixedCost':false,
                'tags': null,
                'periodStep':"MONTH"
            };
        };
        
        $scope.steps = [
            "DAY",
            "WEEK",
            "MONTH",
            "BIMONTH",
            "TRIMESTER",
            "QUARTER",
            "SEMESTER",
            "YEAR"
        ];

        $scope.resetBgForm = function() {
            $scope.bg = {
                'year' : new Date().getFullYear() + "",
                'amount' : "",
                'name' : ""
            };
        };

        $scope.resetBgForm();
        $scope.resetNewIndividualCostForm();
    }]);

facturesApp.controller('ListIndividualCostsController', ['$scope', '$http', '$log', "$injector",
    function ListIndividualCostsController($scope, $http, $log, $injector) {
        $scope.pageNo = 0;
        $scope.pageSize = 2000000;
        $scope.$log = $log;

        var rest = $injector.get("rest");

        rest.individualCostService($http).getIndividualCosts(2013, function(data) {
            $scope.$log.info(data);
            $scope.individualCosts = data;
        });

        $scope.sort = function(fieldName) {
            if($scope.sortField === fieldName) {
                $scope.reverse = !$scope.reverse;
            } else {
                $scope.sortField = fieldName;
                $scope.reverse = false;
            }
        };

        $scope.isSortUp = function(fieldName) {
            return $scope.sortField === fieldName && !$scope.reverse;
        };

        $scope.isSortDown = function(fieldName) {
            return $scope.sortField === fieldName && $scope.reverse;
        };


    }]);

facturesApp.controller('ListPeriodicCostsController', ['$scope', '$http', '$log', "$injector",
    function ListPeriodicCostsController($scope, $http, $log, $injector) {
        $scope.pageNo = 0;
        $scope.pageSize = 2000000;
        $scope.$log = $log;

        var rest = $injector.get("rest");

        rest.periodicCostService($http).getAllPeriodicCosts(2013, function(data) {
            $scope.$log.info(data);
            $scope.periodicCosts = data;
        });

//        $scope.periodicCosts = [{"id":4855443348258816,"cost":43,"concept":"","budgetId":5207287069147136,"tags":"gdd","periodStep":"MONTH","start":1356994800000,"end":1388444400000,"isFixedCost":true,"foreseenCost":null},{"id":5418393301680128,"cost":null,"concept":"","budgetId":5207287069147136,"tags":"hgjghj","periodStep":"MONTH","start":1356994800000,"end":1388444400000,"isFixedCost":false,"foreseenCost":6},{"id":6262818231812096,"cost":null,"concept":"","budgetId":5207287069147136,"tags":"fdg","periodStep":"MONTH","start":1356994800000,"end":1388444400000,"isFixedCost":false,"foreseenCost":4},{"id":6614661952700416,"cost":null,"concept":"","budgetId":5207287069147136,"tags":"ghfh","periodStep":"MONTH","start":1356994800000,"end":1386802800000,"isFixedCost":false,"foreseenCost":5},{"id":5981343255101440,"cost":34,"concept":"","budgetId":5629499534213120,"tags":"fdgdfg","periodStep":"MONTH","start":1356994800000,"end":1388444400000,"isFixedCost":false,"foreseenCost":345}];

        $scope.sort = function(fieldName) {
            if($scope.sortField === fieldName) {
                $scope.reverse = !$scope.reverse;
            } else {
                $scope.sortField = fieldName;
                $scope.reverse = false;
            }
        };

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

            $location.path("/individualcost/new");
            updatePlace("individualCost");

        };
        
        $scope.goToNewPeriodicCost = function() {

            $location.path("/periodiccost/new");
            updatePlace("periodicCost");

        };

        $scope.goToNewBudget = function() {

            $location.path("/budget/new");
            updatePlace("budget");
        };

        $scope.goToIndividualCostList = function() {

            $location.path("/individualcost/list");
            updatePlace("individualCost");
        };
        
        $scope.goToPeriodicCostList = function() {

            $location.path("/periodiccost/list");
            updatePlace("periodicCost");
        };

        $scope.place = {
            'budget' : false,
            'periodicCost' : false,
            'individualCost' : true
        };

        var updatePlace = function(newPlace) {
            for (var place in $scope.place) {
                if( place === newPlace) {
                    $scope.place[place] = true;
                } else {
                    $scope.place[place] = false;
                }
            }
        };

        //        updatePlace($location.path())

    }]);









