package albert.lacambra.server.rpc;

import static albert.lacambra.server.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

import albert.lacambra.client.models.DTOInvoice;
import albert.lacambra.client.rpc.InvoiceService;
import albert.lacambra.server.auth.Bracelet;
import albert.lacambra.server.models.Budget;
import albert.lacambra.server.models.PersistedInvoice;

@SuppressWarnings("serial")
public class InvoiceServiceImpl extends RemoteServiceServlet implements InvoiceService {

	protected Bracelet bracelet;

	@Override
	public DTOInvoice getInvoice(Long id) {
		Key<PersistedInvoice> key = PersistedInvoice.key(bracelet.getMeKey(), id);
		PersistedInvoice i = ofy().load().key(key).getValue();

		return i.getDTOInvoice();
	}

	@Override
	public Long addInvoice(DTOInvoice i) {
		Key<Budget> key = Budget.key(bracelet.getMeKey(), i.getBudgetId());
		PersistedInvoice persistedInvoice = new PersistedInvoice(key, i);
		persistedInvoice.setBudget(key);
		long id = ofy().save().entity(persistedInvoice).now().getId();
		return id;
	}

	@Override
	public List<DTOInvoice> getAllInvoices() {
		
		List<PersistedInvoice> l = 
				ofy().load()
				.type(PersistedInvoice.class)
				.ancestor(bracelet.getMeKey())
				.list();
		
		List<DTOInvoice> dtoInvoices = new ArrayList<DTOInvoice>();
		
		for ( PersistedInvoice i : l) {
			dtoInvoices.add(i.getDTOInvoice());
		}
		
		return dtoInvoices;
	}
}
