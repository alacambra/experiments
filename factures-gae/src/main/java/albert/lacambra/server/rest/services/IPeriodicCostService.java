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
import albert.lacambra.server.models.PeriodicCost;
import albert.lacambra.server.models.PeriodicCostEntry;
import albert.lacambra.shared.ResourceLocator;

@Path(ResourceLocator.periodicCostBase)
public interface IPeriodicCostService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{budgetId:[0-9]+}/{costId:[0-9]+}")
	public PeriodicCost getPeriodicCost(
			@PathParam("budgetId") Long budgetId, @PathParam("costId") Long costId);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/year/{year:[0-9]{4}}")
	public List<PeriodicCost> getAllPeriodicCosts(@PathParam("year") Integer year);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Long savePeriodicCost(PeriodicCost cost);
	
	@PUT
	@Path(ResourceLocator.periodicCostEntry + "/{budgetId:[0-9]+}/{costId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Long addCostEntry(
			@PathParam("budgetId") Long budgetId, @PathParam("costId") Long costId, PeriodicCostEntry entry);
	
	@PUT
	@Path(ResourceLocator.periodicCostEntry + "/costentry/{budgetId:[0-9]+}/{costId:[0-9]+}/{entryId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCostEntry(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId, 
			@PathParam("entryId") Long entryId,
			PeriodicCostEntry entry);
	
	@DELETE
	@Path(ResourceLocator.periodicCostEntry + "/{budgetId:[0-9]+}/{costId:[0-9]+}/{entryId:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCostEntry(
			@PathParam("budgetId") Long budgetId, 
			@PathParam("costId") Long costId,
			@PathParam("entryId") Long entryId);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{periodicCostId:[0-9]+}")
	public void updatePeriodicCost( @PathParam("periodicCostId") Long id, PeriodicCost periodicCost);

	@DELETE
	@Path("/{budgetId:[0-9]+}/{periodicCostId:[0-9]+}")
	public void deletePeriodicCost(@PathParam("budgetId") Long budgetId, @PathParam("periodicCostId") Long periodicCostId);
}


























