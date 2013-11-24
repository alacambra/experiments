'use strict';

/* App Module */

var facturesApp = angular.module(
        'facturesApp',[
    'ngRoute',
    'arrayFilters',
    'httpInterceptor',
    'ui.bootstrap'
])
facturesApp.value("rest", new RestServices("rest"));

facturesApp.directive('dateFormat', function($filter) {
    var dateFilter = $filter('date');

    return {
        require: 'ngModel',
        link: function(scope, element, attr, ngModelCtrl) {
            ngModelCtrl.$formatters.unshift(function(value) {
                return dateFilter(value, 'dd-MM-yyyy');
            });

            ngModelCtrl.$parsers.push(function(value) {
                var regex = /(\d{1,2})-(\d{1,2})-(\d{4})/.exec(value);
                if (regex !== null && regex.length) {
                    return new Date(regex[3]+ " " + regex[2] + " " + regex[1] + " 00:01:00 GMT+1").getTime();
                }
            });
        }
    };
});


angular.module('arrayFilters', []).filter("pagination", function(){
    return function(inputArray, selectedPage, pageSize){

        if (inputArray === undefined) {
            return;
        }

        var start = selectedPage * pageSize;
        var end = start + pageSize;

        if(end > inputArray.length) {
            end = inputArray.length;
        }

        return inputArray.slice(start, end);
    } ;
});

facturesApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/individualcost/new', {
                    templateUrl: 'partials/new-individual-cost-form.html',
            controller: 'NewIndividualCostController'
        }).
                when('/periodiccost/new', {
                    templateUrl: 'partials/new-periodic-cost-form.html',
            controller: 'NewPeriodicCostController'
        }).
                when('/budget/new', {
                    templateUrl: 'partials/new-budget-form.html',
            controller: 'NewIndividualCostController'
        }).
                when('/individualcost/list', {
                    templateUrl: 'partials/list-costs.html',
            controller: 'ListCostsController'
        }).
                otherwise({
                    redirectTo: '/individualcost/new'
        });
    }]);
