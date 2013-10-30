'use strict';

/* Controllers */

var facturesControllers = angular.module('facturesControllers', []);

facturesControllers.controller('NewIndividualCostController', ['$scope', '$http',
  function InsertIndividualCostCtrl($scope, $http) {
    $http.put('individualcost', individualCost).success(function(data) {
      $scope.phones = data;
    });
  }]);

