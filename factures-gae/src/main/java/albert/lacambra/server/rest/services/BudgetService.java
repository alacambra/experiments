package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.client.models.DTOBudget;
import albert.lacambra.server.models.NewPersistedBudget;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.PersistedInvoice;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class BudgetService extends BasicService 
//implements IBudgetService 
{

	public DTOBudget getBudget(Long id) {

		Key<PersistedBudget> key = PersistedBudget.key(bracelet.getMeKey(), id);
		PersistedBudget bg = ofy().load().key(key).now();

		if ( bg == null ){
			throw new WebApplicationException(
					Response.status(Response.Status.NOT_FOUND)
					.entity("{\"msg\":\"not found\"}")
					.build());
		} 

		return bg.getDTOBudget();
	}

	public List<DTOBudget> getBudgetsForYear(String year) 
			throws JsonGenerationException, JsonMappingException, IOException {

		Query<PersistedBudget> query = ofy().load().type(PersistedBudget.class).ancestor(bracelet.getMeKey());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String ini = year + "-01-01 00:00:00.0";
		String end = String.valueOf(Integer.parseInt(year) + 1) + "-01-01 00:00:00.0";

		List<PersistedBudget> l = new ArrayList<PersistedBudget>();

		try {

			l = query.filter("start >=", dateFormat.parse(ini).getTime()).list();//.filter("end <=", dateFormat.parse(end).getTime()).list();
			ArrayList<PersistedBudget> bs = new ArrayList<PersistedBudget>();
			bs.addAll(l);
			for( PersistedBudget b : bs) {

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
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build());
		}

		if ( l == null ) {
			throw new WebApplicationException(Response.status(Status.NO_CONTENT).build());
		} 

		ArrayList<DTOBudget> budgets = new ArrayList<DTOBudget>();

		for ( PersistedBudget pb : l) {
			budgets.add(pb.getDTOBudget());
		}
		
		return budgets;
	}

	
	public Response update() {

		List<PersistedBudget> l = ofy().load().type(PersistedBudget.class).ancestor(bracelet.getMeKey()).list();

		for ( PersistedBudget oldBudget : l ) {

			PersistedBudget newBudget = new PersistedBudget();
			newBudget.setOwner(bracelet.getMeKey());
			newBudget.setAssignation(oldBudget.getAssignation());
			newBudget.setEnd(oldBudget.getEnd());
			newBudget.setStart(oldBudget.getStart());
			newBudget.setName(oldBudget.getName());
			long id = ofy().save().entity(newBudget).now().getId();
			newBudget.setId(id);

			Query<PersistedInvoice> q = ofy().load().type(PersistedInvoice.class).ancestor(oldBudget);
			List<PersistedInvoice> invoices = new ArrayList<PersistedInvoice>();
			invoices.addAll(q.list());

			for ( PersistedInvoice oldInvoice : invoices ) {
				Key<PersistedBudget> bKey = PersistedBudget.key(bracelet.getMeKey(), newBudget.getId());
				PersistedInvoice newInvoice = new PersistedInvoice(bKey);
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

	public List<DTOBudget> getAllBudgets() throws JsonGenerationException, JsonMappingException, IOException {

		List<PersistedBudget> l = ofy().load().type(PersistedBudget.class).ancestor(bracelet.getMeKey()).list();

		if ( l == null ) {
			throw new WebApplicationException(Response.status(Status.NO_CONTENT).build());
		} 

		ArrayList<DTOBudget> budgets = new ArrayList<DTOBudget>();

		for ( PersistedBudget pb : l) {
			budgets.add(pb.getDTOBudget());
		}

		return budgets;
	}

	
	public Response saveBudget(DTOBudget dto) {

		PersistedBudget pb = new PersistedBudget(dto).setOwner(bracelet.getMeKey());

		long id = 0;

		try{
			id = ofy().save().entity(pb).now().getId();
		} catch (Throwable e ) {
			log.severe(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build();
		}

		return Response.status(Status.CREATED).header("x-insertedid", String.valueOf(id)).build();
	}
	
	/**
	 * new version of saveBudget
	 * @param dto
	 * @return
	 */
	public Response addBudget(DTOBudget dto) {

		NewPersistedBudget pb = new NewPersistedBudget(dto).setOwner(bracelet.getMeKey());

		long id = 0;

		try{
			id = ofy().save().entity(pb).now().getId();
		} catch (Throwable e ) {
			log.severe(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build();
		}

		return Response.status(Status.CREATED).header("x-insertedid", String.valueOf(id)).build();
	}

	
	public List<DTOBudget> getBudgetsForYear(Integer year)
			throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}





























