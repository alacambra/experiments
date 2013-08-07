package albert.lacambra.server.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import albert.lacambra.client.models.IndividualCostDTO;
import albert.lacambra.server.models.IndividualCost;

public interface IIndividualCostService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	public IndividualCost getIndividualCost(
			@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("year/{year:[0-9]{4}}")
	public List<IndividualCost> getIndividualCosts(@PathParam("invoiceId") Integer year);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveIndividualCost(IndividualCost invoice);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id:[0-9]+}")
	public Response updateIndividualCost( @PathParam("invoiceId") Long id, IndividualCost invoice);
	
	@DELETE
	@Path("/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	public void deleteIndividualCost(@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);
}

























