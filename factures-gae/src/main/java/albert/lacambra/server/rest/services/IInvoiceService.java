package albert.lacambra.server.rest.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import albert.lacambra.server.models.Invoice;

@Path("/invoice")
public interface IInvoiceService {

	@GET
	@Path("{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvoice(@PathParam("id") Long id);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvoices() throws JsonGenerationException, JsonMappingException, IOException;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveInvoice(Invoice invoice);
	
}