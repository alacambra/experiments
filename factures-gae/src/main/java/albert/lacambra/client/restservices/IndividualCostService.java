package albert.lacambra.client.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import albert.lacambra.client.models.IndividualCost;

public interface IndividualCostService extends RestService{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{budgetId}/{invoiceId}")
	public void getIndividualCost(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("invoiceId") Long invoiceId, 
			MethodCallback<IndividualCost> callback);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("year/{year:[0-9]{4}}")
	public void getIndividualCosts(@PathParam("year") Integer year, MethodCallback<List<IndividualCost>> callback);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Long saveIndividualCost(IndividualCost invoice, MethodCallback<Long> callback);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void updateIndividualCost(
			@PathParam("invoiceId") Long id, IndividualCost invoice, MethodCallback<Void> callback);
	
	@DELETE
	@Path("/{budgetId}/{invoiceId")
	public void deleteIndividualCost(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("invoiceId") Long invoiceId, 
			MethodCallback<Void> callback);
}


























