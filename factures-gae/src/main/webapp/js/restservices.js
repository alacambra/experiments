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
//                return $q.accept();
            }
        }
    });

var RestServices = function(endpoint) {
    this.endpoint = endpoint;

    /************************************************************
     @GET
     @Path("{id:[\\d]+}")
     @Produces(MediaType.APPLICATION_JSON)
     PersistedBudget getBudget(@PathParam("id")Long id);
     *************************************************************/
    var getBudget = function (budgetId) {
        a = 0;
    };


    /************************************************************
    @GET
    @Path("year/{year:[\\d]{4}}")
    @Produces(MediaType.APPLICATION_JSON)
    List<PersistedBudget> getBudgetsForYear(@PathParam("year")Integer year);
    *************************************************************/

    var getBudgetsForYear = function (budgetId) {
        a = 0;
    };

    /************************************************************
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<PersistedBudget> getAllBudgets();
    *************************************************************/

    var getAllBudgets = function (budgetId) {
        a = 0;
    };

    /************************************************************
    @PUT
    @Path("{id:[\\d]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    void updateBudget(@PathParam("id")Long id, PersistedBudget dto);
    *************************************************************/

    var updateBudget = function (budgetId) {
        a = 0;
    };

    /************************************************************
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Long addBudget(PersistedBudget dto);
    *************************************************************/

    var addBudget = function (budgetId) {
        a = 0;
    };
};
