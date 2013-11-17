/**
 * Created by albert on 11/10/13.
 */

angular.module('httpInterceptor',[]).config(
    function($httpProvider) {
        $httpProvider.interceptors.push("requestInterceptor")
    })
    .factory("requestInterceptor", function($q){
        return{
            'request': function(config){
                return config
            }
        }
    });



var RestServices = function(endpoint) {
    this.endpoint = endpoint;
    var restServicesSelf = this;
    var _buffer = {
        "budget":{
            "get":{}
        },
        "ic":{
            "get":{}
        }
    };

    return {
        "bufferRestServices":function($http) {
            var buffer = _buffer["budget"];

            return {
                "getBudget":function (budgetId) {
                    a = 0;
                },
                "getBudgetsForYear":function (year, callback) {

                    if (buffer["get"].hasOwnProperty(year)) {
                        callback(buffer["get"][year]);
                        return;
                    }

                    $http.get(endpoint + '/budget/year/' + year).
                        success(function(data) {
                            buffer["get"][year] = data;
                            callback(data)
                        }).error(function(data, status, headers, config) {

                            if(status===401){
                                window.location = data;
                            }
                        });
                },

                "getAllBudgets":function () {},

                "updateBudget":function (budget) {},

                "addBudget":function (bg) {
                    buffer["get"] = {}
                    return $http.put(endpoint + '/budget/', bg);
                }
            }
        },
        "individualCostService":function($http) {

            var buffer = _buffer["ic"];

            return {
                "individualCost":function getIndividualCost(budgetId, invoiceId){
                },

                "getIndividualCosts":function getIndividualCosts(year, callback){

                    if (buffer["get"].hasOwnProperty(year)) {
                        callback(buffer["get"][year]);
                        return;
                    }

                    $http.get(endpoint + '/individualcost/year/' + year).
                        success(function(data) {
                            buffer["get"][year] = data;
                            callback(data)
                        }).error(function(data, status, headers, config) {

                            if(status===401){
                                window.location = data;
                            }
                        });
                },
                "saveIndividualCost":function saveIndividualCost(cost){
                    buffer["get"] = {}
                    return $http.put(endpoint + '/individualcost/', cost);
                },

                "updateIndividualCost":function updateIndividualCost(costId, invoice){

                },

                "deleteIndividualCost":function deleteIndividualCost(budgetId, costId){

                }
            }
        }
    }
};
