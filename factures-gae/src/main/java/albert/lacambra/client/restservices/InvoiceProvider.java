package albert.lacambra.client.restservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import albert.lacambra.client.events.InvoiceAddedEvent;
import albert.lacambra.client.events.InvoiceDeletedEvent;
import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.models.IsJsonSerializable;
import albert.lacambra.client.restservices.utils.AsyncCallback;

public class InvoiceProvider implements CollectionProvider<Invoice> {

	HashMap<Long, Invoice> invoices = new HashMap<Long, Invoice>();
	private EventBus eventBus;
	@Inject RestServices restServices;
	
	@Inject
	public InvoiceProvider(EventBus eventBus) {
		this.eventBus = eventBus;
		
	}
	
	@Override
	public void configure(Collection<Invoice> invoices) {

		for ( Invoice i : invoices) {
			this.invoices.put(i.getId(), i);
		}

		loadHandlers();

	}

	private void loadHandlers() {
		
		this.eventBus.addHandler(InvoiceAddedEvent.TYPE, new InvoiceAddedEvent.InvoiceAddedHandler() {

			@Override
			public void onInvoiceAdded(InvoiceAddedEvent event) {
				invoices.put(event.getInvoice().getId(), event.getInvoice());
			}
		});
		
		this.eventBus.addHandler(InvoiceDeletedEvent.TYPE, new InvoiceDeletedEvent.InvoiceDeletedHandler() {
			
			@Override
			public void onInvoiceDeleted(InvoiceDeletedEvent event) {
				Invoice i = invoices.remove(event.getInvoice().getId());
				
				if ( i == null ) {
					Log.info("invoice not found in provider " + event.getInvoice().getId());
				}
			}
		});
	}
	
	public void addInvoice(IsJsonSerializable i, AsyncCallback<Long> callback) {
		restServices.addInvoice(i, callback);
	}
	
	public void getAllInvoices(AsyncCallback<List<Invoice>> callback) {
		restServices.getAllInvoices(callback);
	}

	@Override
	public Invoice get(Long id) {

		return invoices.get(id);

	}

	@Override
	public Collection<Invoice> get() {
		return invoices.values();
	}
	
	public List<Invoice> getInvoicesAsList() {
		
		List<Invoice> invoicesList = new ArrayList<Invoice>();
		invoicesList.addAll(invoices.values());
		
		return invoicesList;
	}

}




















































