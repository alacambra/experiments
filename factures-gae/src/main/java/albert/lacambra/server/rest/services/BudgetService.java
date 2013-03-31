package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.server.models.Budget;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class BudgetService extends BasicService implements IBudgetService {

	public Response getBudget(Long id) {
		Key<Budget> key = Budget.key(bracelet.getMeKey(), id);
		Budget bg = ofy().load().key(key).getValue();

		Response  r = null;

		if ( bg == null ){
			r = Response.status(Response.Status.NOT_FOUND).entity("{\"msg\":\"not found\"}").build();
		} else {
			r = Response.ok(bg).build();
		}
		return r;
	}
	
	public Response getBudgetsOfYear(String year) throws JsonGenerationException, JsonMappingException, IOException {
		Query<Budget> query = ofy().load().type(Budget.class).ancestor(bracelet.getMeKey());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String ini = year + "-01-01 00:00:00.0";
		String end = String.valueOf(Integer.parseInt(year) + 1) + "-01-01 00:00:00.0";
		
		List<Budget> l = new ArrayList<Budget>();
		try {
			l = query.filter("start >=", dateFormat.parse(ini).getTime()).filter("end <=", dateFormat.parse(end).getTime()).list();
		} catch (ParseException e) {
			log.severe("Error parsing dates");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build();
		}
		

		if ( l == null ) {
			return Response.status(Status.NO_CONTENT).build();
		} else {
			return Response.ok().entity(m.writeValueAsString(l)).build();
		}
	}
	
	public Response getAllBudgets() throws JsonGenerationException, JsonMappingException, IOException {
		List<Budget> l = ofy().load().type(Budget.class).ancestor(bracelet.getMeKey()).list();

		if ( l == null ) {
			return Response.status(Status.NO_CONTENT).build();
		} else {
			return Response.ok().entity(m.writeValueAsString(l)).build();
		}
	}

	@Override
	public Response saveBudget(Budget budget) {

		budget.setOwner(bracelet.getMeKey());
		
		long id = 0;
		
		try{
			id = ofy().save().entity(budget).now().getId();
		} catch (Throwable e ) {
			log.severe(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build();
		}
		
		return Response.status(Status.CREATED).header("x-insertedid", String.valueOf(id)).build();
	}
}





























