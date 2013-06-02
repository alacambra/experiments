package albert.lacambra.server.rest.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.server.models.Budget;
import albert.lacambra.shared.ResourceLocator;

@Path(ResourceLocator.budgetBase)
public interface IBudgetService {
	
	@GET
	@Path("{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBudget(@PathParam("id") Long id);
	
	@GET
	@Path("year/{year:[0-9]{4}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBudgetsForYear(@PathParam("year") String year) throws JsonGenerationException, JsonMappingException, IOException ;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBudgets() throws JsonGenerationException, JsonMappingException, IOException ;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveBudget(Budget budget);

	@GET
	@Path("update")
	Response update();
}


























