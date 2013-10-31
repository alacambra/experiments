'use strict';

/* App Module */

var facturesApp = angular.module('facturesApp', [
  'ngRoute',
  'facturesControllers'
]);

facturesApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/individualcost/new', {
        templateUrl: 'partials/new-individual-cost-form.html',
        controller: 'NewIndividualCostController'
      }).
      otherwise({
          redirectTo: '/individualcost/new'
        });
  }]);
