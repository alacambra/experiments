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

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PeriodicCost;

@Path("/test")
public interface IPeriodicCostService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	public PeriodicCost getPeriodicCost(
			@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);

	/*
	 * just for test
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	public IndividualCost getIndividualCost(
			@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/year/{year:[0-9]{4}}")
	public List<PeriodicCost> getPeriodicCosts(@PathParam("year") Integer year);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response savePeriodicCost(PeriodicCost cost);
	
	/*
	 * just for test
	 */
	@PUT
	@Path("/test")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response savePeriodicCost(IndividualCost cost);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id:[0-9]+}")
	public Response updatePeriodicCost( @PathParam("invoiceId") Long id, PeriodicCost invoice);

	@DELETE
	@Path("/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	public void deletePeriodicCost(@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);
}


























