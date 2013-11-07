'use strict';

/* App Module */

var facturesApp = angular.module(
    'facturesApp',[
        'ngRoute',
        'ui.bootstrap'
        ])

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
	otherwise({
		redirectTo: '/individualcost/new'
	});
}]);
