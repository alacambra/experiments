package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.server.models.Budget;
import albert.lacambra.server.models.PersistedInvoice;

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

	public Response getBudgetsForYear(String year) throws JsonGenerationException, JsonMappingException, IOException {

		Query<Budget> query = ofy().load().type(Budget.class).ancestor(bracelet.getMeKey());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String ini = year + "-01-01 00:00:00.0";
		String end = String.valueOf(Integer.parseInt(year) + 1) + "-01-01 00:00:00.0";

		List<Budget> l = new ArrayList<Budget>();

		try {

			l = query.filter("start >=", dateFormat.parse(ini).getTime()).list();//.filter("end <=", dateFormat.parse(end).getTime()).list();
			ArrayList<Budget> bs = new ArrayList<Budget>();
			bs.addAll(l);
			for( Budget b : bs) {

				if ( b.getEnd() > dateFormat.parse(end).getTime() ) {

					l.remove(b);

				} else {

					Query<PersistedInvoice> q = ofy().load().type(PersistedInvoice.class).ancestor(b);
					List<PersistedInvoice> invoices = new ArrayList<PersistedInvoice>();
					invoices.addAll(q.list());

					int total = 0;

					for ( PersistedInvoice i : invoices ) {
						total += i.getPrice(); 
					}

					b.setUsed(total);
				}
			}



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

	@Override
	public Response update() {
		
		List<Budget> l = ofy().load().type(Budget.class).ancestor(bracelet.getMeKey()).list();
		
  		for ( Budget oldBudget : l ) {
			
			Budget newBudget = new Budget();
			newBudget.setOwner(bracelet.getMeKey());
			newBudget.setAssignation(oldBudget.getAssignation());
			newBudget.setEnd(oldBudget.getEnd());
			newBudget.setStart(oldBudget.getStart());
			newBudget.setName(oldBudget.getName());
			long id = ofy().save().entity(newBudget).now().getId();
			newBudget.setId(String.valueOf(id));
			
			Query<PersistedInvoice> q = ofy().load().type(PersistedInvoice.class).ancestor(oldBudget);
			List<PersistedInvoice> invoices = new ArrayList<PersistedInvoice>();
			invoices.addAll(q.list());

 			for ( PersistedInvoice oldInvoice : invoices ) {
				Key<Budget> bKey = Budget.key(bracelet.getMeKey(), Long.parseLong(newBudget.getId()));
				PersistedInvoice newInvoice = new PersistedInvoice(bKey);
//				newInvoice.setBudgetId(id);
				newInvoice.setDate(oldInvoice.getDate());
				newInvoice.setExtra(oldInvoice.getExtra());
				newInvoice.setPrice(oldInvoice.getPrice());
				ofy().save().entity(newInvoice).now().getId();
			}
 			ofy().delete().entities(invoices);
			ofy().delete().entities(oldBudget);
		}
  		
  		return Response.status(Status.NO_CONTENT).build();
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





























