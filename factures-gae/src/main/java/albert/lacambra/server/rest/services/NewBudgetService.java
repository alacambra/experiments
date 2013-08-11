package albert.lacambra.server.rest.services;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.client.models.DTOBudget;
import albert.lacambra.server.models.NewPersistedBudget;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class NewBudgetService extends BasicService implements IBudgetService {

	@Override
	public DTOBudget getBudget(Long id) {

		Key<NewPersistedBudget> key = NewPersistedBudget.key(bracelet.getMeKey(), id);
		NewPersistedBudget bg = ofy().load().key(key).now();

		if ( bg == null ){
			throw new WebApplicationException(
					Response.status(Response.Status.NOT_FOUND)
					.entity("{\"msg\":\"not found\"}")
					.build());
		} 

		return bg.getDTOBudget();
	}

	@Override
	public List<DTOBudget> getBudgetsForYear(Integer year) 
			throws JsonGenerationException, JsonMappingException, IOException {

		Query<NewPersistedBudget> query = ofy().load().type(NewPersistedBudget.class).ancestor(bracelet.getMeKey());

		List<NewPersistedBudget> l = new ArrayList<NewPersistedBudget>();

		l = query.filter("year ==", year).list();
		ArrayList<NewPersistedBudget> bs = new ArrayList<NewPersistedBudget>();
		bs.addAll(l);
		
		if ( l == null ) {
			throw new WebApplicationException(Response.status(Status.NO_CONTENT).build());
		} 

		ArrayList<DTOBudget> budgets = new ArrayList<DTOBudget>();

		for ( NewPersistedBudget pb : l) {
			budgets.add(pb.getDTOBudget());
		}
		
		return budgets;
	}

	@Override
	public Response update() {
		return null;

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
	}

	@Override
	public List<DTOBudget> getAllBudgets() throws JsonGenerationException, JsonMappingException, IOException {

		List<NewPersistedBudget> l = ofy().load().type(NewPersistedBudget.class).ancestor(bracelet.getMeKey()).list();

		if ( l == null ) {
			throw new WebApplicationException(Response.status(Status.NO_CONTENT).build());
		} 

		ArrayList<DTOBudget> budgets = new ArrayList<DTOBudget>();

		for ( NewPersistedBudget pb : l) {
			budgets.add(pb.getDTOBudget());
		}

		return budgets;
	}

	@Override
	public Response saveBudget(DTOBudget dto) {

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
	
	@Override
	public Response updateBudget(Long id, DTOBudget dto) {

		NewPersistedBudget bg = new NewPersistedBudget(dto);
		bg.setId(id);
		
		try{
			ofy().save().entity(bg).now();
		} catch (Throwable e ) {
			log.severe(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error parsing dates").build();
		}

		return Response.status(Status.OK).build();
	}
	
	/**
	 * new version of saveBudget
	 * @param dto
	 * @return
	 */
	@Override
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

	@Override
	public List<DTOBudget> getBudgetsForYear(String year)
			throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}





























