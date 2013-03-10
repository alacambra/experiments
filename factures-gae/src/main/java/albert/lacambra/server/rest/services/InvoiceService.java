package albert.lacambra.server.rest.services;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.googlecode.objectify.Key;
import static albert.lacambra.server.ofy.OfyService.ofy;
import albert.lacambra.server.models.Invoice;

public class InvoiceService extends BasicService implements IInvoiceService {


	public InvoiceService() { 
		super();
	}

	@Override
	public Response getInvoice( Long id ) {

		Key<Invoice> key = Invoice.key(bracelet.getMeKey(), id);
		Invoice i = ofy().load().key(key).getValue();

		Response  r = null;

		if ( i == null ){
			r = Response.status(Response.Status.NOT_FOUND).entity("{\"msg\":\"not found\"}").build();
		} else {
			r = Response.ok(i).build();
		}
		return r;
	}

	@Override
	public Response saveInvoice( Invoice invoice ) {
		
		invoice.setOwner(bracelet.getMeKey());
		long id = ofy().save().entity(invoice).now().getId();
		
		return Response.status(Status.CREATED).header("x-insertedid", String.valueOf(id)).build();
	}


	@Override
	public Response getInvoices() throws JsonGenerationException, JsonMappingException, IOException {
		List<Invoice> l = ofy().load().type(Invoice.class).ancestor(bracelet.getMeKey()).list();

		if ( l == null ) {
			return Response.status(Status.NO_CONTENT).build();
		} else {
			return Response.ok().entity(m.writeValueAsString(l)).build();
		}
	}
}










































