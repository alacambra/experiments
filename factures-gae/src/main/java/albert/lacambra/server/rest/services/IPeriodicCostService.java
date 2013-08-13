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

import org.restlet.resource.Delete;

import albert.lacambra.server.models.IndividualCost;
import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PeriodicCostEntry;

@Path("/test")
public interface IPeriodicCostService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{budgetId:[0-9]+}/{costId:[0-9]+}")
	public PeriodicCost getPeriodicCost(
			@PathParam("budgetId") Long budgetId, @PathParam("costId") Long costId);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/year/{year:[0-9]{4}}")
	public List<PeriodicCost> getPeriodicCosts(@PathParam("year") Integer year);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Long savePeriodicCost(PeriodicCost cost);
	
	@PUT
	@Path("/costentry/{budgetId:[0-9]+}/{costId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Long addCostEntry(
			@PathParam("budgetId") Long budgetId, @PathParam("costId") Long costId, PeriodicCostEntry entry);
	
	@PUT
	@Path("/costentry/{budgetId:[0-9]+}/{costId:[0-9]+}/{entryId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Long updateCostEntry(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId, 
			@PathParam("entryId") Long entryId,
			PeriodicCostEntry entry);
	
	@DELETE
	@Path("/costentry/{budgetId:[0-9]+}/{costId:[0-9]+}/{entryId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Long deleteCostEntry(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId, 
			@PathParam("entryId") Long entryId,
			PeriodicCostEntry entry);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id:[0-9]+}")
	public void updatePeriodicCost( @PathParam("invoiceId") Long id, PeriodicCost invoice);

	@DELETE
	@Path("/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	public void deletePeriodicCost(@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);
}


























