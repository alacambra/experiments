package albert.lacambra.server.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.shared.ResourceLocator;

@Path(ResourceLocator.budgetBase)
public interface IBudgetService {

	@GET
	@Path("{id:[\\d]+}")
	@Produces(MediaType.APPLICATION_JSON)
	PersistedBudget getBudget(@PathParam("id")Long id);

	@GET
	@Path("year/{year:[\\d]{4}}")
	@Produces(MediaType.APPLICATION_JSON)
	List<PersistedBudget> getBudgetsForYear(@PathParam("year")Integer year);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<PersistedBudget> getAllBudgets();

	@PUT
	@Path("{id:[\\d]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	void updateBudget(@PathParam("id")Long id, PersistedBudget dto);

	/**
	 * new version of saveBudget
	 * @param dto
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	Long addBudget(PersistedBudget dto);

}