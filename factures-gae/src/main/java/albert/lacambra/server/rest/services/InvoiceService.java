package albert.lacambra.server.rest.services;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.googlecode.objectify.Key;
import static albert.lacambra.server.ofy.OfyService.ofy;
import albert.lacambra.server.models.Budget;
import albert.lacambra.server.models.PersistedInvoice;
public class InvoiceService extends BasicService implements IInvoiceService {


	public InvoiceService() { 
		super();
	}

	@Override
	public Response getInvoice( Long id ) {

		Key<PersistedInvoice> key = PersistedInvoice.key(bracelet.getMeKey(), id);
		PersistedInvoice i = ofy().load().key(key).getValue();

		Response  r = null;

		if ( i == null ){
			r = Response.status(Response.Status.NOT_FOUND).entity("{\"msg\":\"not found\"}").build();
		} else {
			r = Response.ok(i).build();
		}
		return r;
	}

	@Override
	public Response saveInvoice( PersistedInvoice invoice ) {
		
		Key<Budget> key = Budget.key(bracelet.getMeKey(), invoice.getBudget().getId());
		invoice.setBudget(key);
		long id = ofy().save().entity(invoice).now().getId();
		
		return Response.status(Status.CREATED).header("x-insertedid", String.valueOf(id)).build();
	}


	@Override
	public Response getInvoices() throws JsonGenerationException, JsonMappingException, IOException {
		List<PersistedInvoice> l = ofy().load().type(PersistedInvoice.class).ancestor(bracelet.getMeKey()).list();

		if ( l == null ) {
			return Response.status(Status.NO_CONTENT).build();
		} else {
			return Response.ok().entity(m.writeValueAsString(l)).build();
		}
	}
}










































