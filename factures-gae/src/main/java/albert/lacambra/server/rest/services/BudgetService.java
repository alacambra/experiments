package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.ofy.VersionConverter;

import com.google.inject.Inject;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class BudgetService extends BasicService implements IBudgetService {

	@Inject
	public BudgetService(Bracelet bracelet) {
		super(bracelet);
	}

	@Override
	public PersistedBudget getBudget(Long id) {

		Key<PersistedBudget> key = PersistedBudget.key(bracelet.getMeKey(), id);
		PersistedBudget bg = ofy().load().key(key).now();

		if ( bg == null ){
			throw new WebApplicationException(
					Response.status(Response.Status.NOT_FOUND)
					.entity("{\"msg\":\"not found\"}")
					.build());
		} 

		return bg;
	}

	@Override
	public List<PersistedBudget> getBudgetsForYear(Integer year){

		Query<PersistedBudget> query = ofy().load().type(PersistedBudget.class).ancestor(bracelet.getMeKey());

		List<PersistedBudget> l = new ArrayList<PersistedBudget>();

		l = query.filter("year =", year).list();
		
		if ( l == null ) {
			throw new WebApplicationException(Response.status(Status.NO_CONTENT).build());
		} 

		return l;
	}

//	@Override
//	public Response update() {
//		return null;
//
//		List<NewPersistedBudget> l = ofy().load().type(NewPersistedBudget.class).ancestor(bracelet.getMeKey()).list();
//
//		for ( NewPersistedBudget oldBudget : l ) {
//
//			NewPersistedBudget newBudget = new NewPersistedBudget();
//			newBudget.setOwner(bracelet.getMeKey());
//			newBudget.setAssignation(oldBudget.getAssignation());
//			newBudget.setEnd(oldBudget.getEnd());
//			newBudget.setStart(oldBudget.getStart());
//			newBudget.setName(oldBudget.getName());
//			long id = ofy().save().entity(newBudget).now().getId();
//			newBudget.setId(id);
//
//			Query<PersistedInvoice> q = ofy().load().type(PersistedInvoice.class).ancestor(oldBudget);
//			List<PersistedInvoice> invoices = new ArrayList<PersistedInvoice>();
//			invoices.addAll(q.list());
//
//			for ( PersistedInvoice oldInvoice : invoices ) {
//				Key<NewPersistedBudget> bKey = NewPersistedBudget.key(bracelet.getMeKey(), newBudget.getId());
//				PersistedInvoice newInvoice = new PersistedInvoice(bKey);
//				newInvoice.setDate(oldInvoice.getDate());
//				newInvoice.setExtra(oldInvoice.getExtra());
//				newInvoice.setPrice(oldInvoice.getPrice());
//				ofy().save().entity(newInvoice).now().getId();
//			}
//			
//			ofy().delete().entities(invoices);
//			ofy().delete().entities(oldBudget);
//		}
//
//		return Response.status(Status.NO_CONTENT).build();
//	}

	@Override
	public List<PersistedBudget> getAllBudgets() {

		List<PersistedBudget> l = ofy().load().type(PersistedBudget.class).ancestor(bracelet.getMeKey()).list();

		if ( l == null ) {
			throw new WebApplicationException(Response.status(Status.NO_CONTENT).build());
		} 

		return l;
	}

	@Override
	public void updateBudget(Long id, PersistedBudget budget) {

		budget.setId(id);
		
		try{
			ofy().save().entity(budget).now();
		} catch (Throwable e ) {
			log.severe(e.getMessage());
			throw new WebApplicationException(
					Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build());
		}
	}
	
	/**
	 * new version of saveBudget
	 * @param dto
	 * @return
	 */
	@Override
	public Long addBudget(PersistedBudget budget) {

		budget.setOwner(bracelet.getMeKey());

		long id = 0;

		try{
			id = ofy().save().entity(budget).now().getId();
		} catch (Throwable e ) {
			log.severe(e.getMessage());
			throw new WebApplicationException(
					Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build());
		}

		return id;
	}

	@Override
	public Response VersionConvert() {
		new VersionConverter().convertAll();
		return Response.ok().build();
	}

}





























