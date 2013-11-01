'use strict';

/* Controllers */

var facturesControllers = angular.module('facturesControllers', []);

facturesControllers.controller('NewIndividualCostController', ['$scope', '$http', '$log', 
function InsertIndividualCostCtrl($scope, $http, $log) {
	$scope.$log = $log
	$scope.save = function(cost) {
		$http.put('rest/individualcost/', cost).success(function(data) {
			 $scope.$log.info(data);
		});
	};
	
	$http.get('rest/budget/').success(function(data) {
		 $scope.$log.info(data);
		 $scope.budgets = data;
	});
	
	$scope.saveBg = function(bg) {
		$http.put('rest/budget/', bg).success(function(data) {
			$scope.$log.info(data);
		});
	};
	
	$scope.ic = {
			'date' : new Date().getTime(),
			'cost' : 12,
			'concept' : "test",
			'budgetId' : null
	};
	
	$scope.bg = {
			'year' : new Date().getFullYear() + "",
			'amount' : "12",
			'name' : "b" + new Date().getTime(),
	};
	
//	$scope.budgets = [
//		{
//			"assignation":10000,
//			"id":54008,
//			"start":1356994800000,
//			"end":1388530799900,
//			"name":"Intendencia"
//		},
//		{
//			"assignation":500000,
//			"id":55009,
//			"start":1356994800000,
//			"end":1388530799900,
//			"name":"Menjar"
//		},
//		{
//			"assignation":100000,
//			"id":55011,
//			"start":1356994800000,
//			"end":1388530799900,
//			"name":"Oci"
//		},
//		{
//			"assignation":50000,
//			"id":62004,
//			"start":1356994800000,
//			"end":1388530799900,
//			"name":"roba"
//		},
//		{
//			"assignation":180000,
//			"id":63008,
//			"start":1356994800000,
//			"end":1388530799900,
//			"name":"Restaurant"
//		},
//		{
//			"assignation":60000,
//			"id":79001,
//			"start":1356994800000,
//			"end":1388530799900,
//			"name":"cotxe"
//		}];


}]);


