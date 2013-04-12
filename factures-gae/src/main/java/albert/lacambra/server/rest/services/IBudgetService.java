package albert.lacambra.server.rest.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
	public Response getBudget(Long id);
	
	@GET
	@Path("{year:[0-9]{4}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBudgetsForYear(String year) throws JsonGenerationException, JsonMappingException, IOException ;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBudgets() throws JsonGenerationException, JsonMappingException, IOException ;
	
	@PUT
	@Consumes("application/json")
	public Response saveBudget(Budget budget);
}


























