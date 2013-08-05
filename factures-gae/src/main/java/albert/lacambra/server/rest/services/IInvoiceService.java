package albert.lacambra.server.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import albert.lacambra.client.models.DTOInvoice;
import albert.lacambra.shared.ResourceLocator;

@Path(ResourceLocator.invoiceBase)
public interface IInvoiceService {

	@GET
	@Path("/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public DTOInvoice getInvoice(@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);
	
	@GET
	@Path("{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@Deprecated
	public DTOInvoice getInvoice(@PathParam("id") Long id);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Deprecated
	public List<DTOInvoice> getInvoices();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("year/{year:[0-9]{4}}")
	public List<DTOInvoice> getInvoices(Integer year);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveInvoice(DTOInvoice invoice);
	
	@DELETE
	@Path("/{budgetId:[0-9]+}/{invoiceId:[0-9]+}")
	public void deleteInvoice(@PathParam("budgetId") Long budgetId, @PathParam("invoiceId") Long invoiceId);
	
}




















