package albert.lacambra.client.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import albert.lacambra.client.models.Budget;
import albert.lacambra.shared.ResourceLocator;

@Path(ResourceLocator.budgetBase)
public interface IBudgetService extends RestService{

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	void getBudget(@PathParam("id")Long id, MethodCallback<Budget> callback);

	@GET
	@Path("{year}")
	@Produces(MediaType.APPLICATION_JSON)
	void getBudgetsForYear(@PathParam("year")Integer year, MethodCallback<List<Budget>> callback);
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	void getAllBudgets(MethodCallback<List<Budget>> callback);

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	void updateBudget(@PathParam("id")Long id, Budget budgetDto, MethodCallback<Void> callback);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	void addBudget(Budget budgetDto, MethodCallback<Long> callback);

//	void getBudgetsForYear(String year){}

}





























