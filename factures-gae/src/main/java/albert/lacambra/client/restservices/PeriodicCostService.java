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

import albert.lacambra.client.models.PeriodicCost;
import albert.lacambra.client.models.PeriodicCostEntry;
import albert.lacambra.shared.ResourceLocator;

public interface PeriodicCostService extends RestService{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{budgetId}/{costId}")
	public void getPeriodicCost(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId, 
			MethodCallback<PeriodicCost> callback);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/year/{year}")
	public void getPeriodicCosts(
			@PathParam("year") Integer year, MethodCallback<List<PeriodicCost>> callback);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void savePeriodicCost(PeriodicCost cost, MethodCallback<Long> callback);
	
	@PUT
	@Path(ResourceLocator.periodicCostEntry + "/{budgetId}/{costId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void addCostEntry(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId, 
			PeriodicCostEntry entry, 
			MethodCallback<Long> callback);
	
	@PUT
	@Path(ResourceLocator.periodicCostEntry + "/costentry/{budgetId}/{costId}/{entryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCostEntry(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId, 
			@PathParam("entryId") Long entryId,
			PeriodicCostEntry entry,
			MethodCallback<Void> callback);
	
	@DELETE
	@Path(ResourceLocator.periodicCostEntry + "/{budgetId}/{costId}/{entryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCostEntry(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId, 
			@PathParam("entryId") Long entryId,
			MethodCallback<Void> callback);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{periodicCostId}")
	public void updatePeriodicCost(
			@PathParam("periodicCostId") Long id, PeriodicCost periodicCost, MethodCallback<Void> callback);

	@DELETE
	@Path("/{budgetId}/{periodicCostId}")
	public void deletePeriodicCost(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("periodicCostId") Long periodicCostId, 
			MethodCallback<Void> callback);
}


























