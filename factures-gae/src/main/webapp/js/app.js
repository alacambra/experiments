'use strict';

/* App Module */

var facturesApp = angular.module(
    'facturesApp',[
        'ngRoute',
        'arrayFilters',
        'httpInterceptor'
    ]);

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

//angular.module('httpInterceptor',[]).config(
//    function($httpProvider) {
//        $httpProvider.interceptors.push("requestInterceptor")
//    })
//    .factory("requestInterceptor", function($q){
//        return{
//            'request': function(config){
//                return "hello";
//            }
//        }
//    });

facturesApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/individualcost/new', {
                templateUrl: 'partials/new-individual-cost-form.html',
                controller: 'NewIndividualCostController'
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
