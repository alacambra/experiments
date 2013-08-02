package albert.lacambra.server.rest.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.googlecode.objectify.Key;

import static albert.lacambra.server.ofy.OfyService.ofy;
import albert.lacambra.client.models.DTOInvoice;
import albert.lacambra.server.models.PersistedBudget;
import albert.lacambra.server.models.PersistedInvoice;
public class InvoiceService extends BasicService implements IInvoiceService {


	public InvoiceService() { 
		super();
	}

	@Override
	public DTOInvoice getInvoice( Long id ) {

//		Key<PersistedInvoice> key = PersistedInvoice.key(bracelet.getMeKey(), id);
//		PersistedInvoice i = ofy().load().key(key).getValue();
//
//		if ( i == null ){
//			throw new WebApplicationException(
//					Response.status(Response.Status.NOT_FOUND)
//					.entity("{\"msg\":\"not found\"}")
//					.build());
//		} 
//			
//		return i.getDTOInvoice();
		
		return null;
	}

	@Override
	public Response saveInvoice( DTOInvoice invoice ) {
		
		Key<PersistedBudget> key = PersistedBudget.key(bracelet.getMeKey(), invoice.getBudgetId());
		PersistedInvoice persistedInvoice = new PersistedInvoice(key, invoice);
		long id = ofy().save().entity(persistedInvoice).now().getId();
		
		return Response.status(Status.CREATED).header("x-insertedid", String.valueOf(id)).build();
	}


	@Override
	public List<DTOInvoice> getInvoices() throws JsonGenerationException, JsonMappingException, IOException {
		
		List<PersistedInvoice> l = 
				ofy()
				.load()
				.type(PersistedInvoice.class)
				.ancestor(bracelet.getMeKey())
				.list();

		if ( l == null ) {
			throw new WebApplicationException(
					Response.status(Status.NO_CONTENT).build());
		} 
		
		List<DTOInvoice> invoices = new ArrayList<DTOInvoice>();
		
		for ( PersistedInvoice i : l) {
			
			invoices.add(i.getDTOInvoice());
			
		}
		
		return invoices;
	}

	@Override
	public void deleteInvoice(Long budgetId, Long invoiceId) {
		Key<PersistedBudget> budgetKey = PersistedBudget.key(bracelet.getMeKey(), budgetId);
		Key<PersistedInvoice> key = PersistedInvoice.key(budgetKey, invoiceId);
		ofy().delete().key(key);
		PersistedInvoice invoice = ofy().load().key(key).now();
		List<PersistedInvoice> l = 
				ofy()
				.load()
				.type(PersistedInvoice.class)
				.ancestor(bracelet.getMeKey())
				.list();
//
//		if ( invoice == null ){
//			throw new WebApplicationException(
//					Response.status(Response.Status.NOT_FOUND).header("Content-Type", MediaType.APPLICATION_JSON)
//					.entity("{\"msg\":\"resource not found\"}")
//					.build());
//		}
//		
//		ofy().delete().entity(invoice);
		
	}
}










































