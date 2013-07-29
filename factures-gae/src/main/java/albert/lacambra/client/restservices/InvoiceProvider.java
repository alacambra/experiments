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
import albert.lacambra.client.events.InvoiceRemovedEvent;
import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.models.IsJsonSerializable;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.AsyncCallbackNoReturnValue;
import albert.lacambra.client.restservices.utils.ResponseException;

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
	
	public void addInvoice(final Invoice invoice, final AsyncCallback<Long> callback) {
		restServices.addInvoice(invoice, new AsyncCallback<Long>() {
			
			@Override
			public void onSuccess(Long id) {
				invoices.put(id, invoice);
				eventBus.fireEvent(new InvoiceAddedEvent(invoice));
				callback.onSuccess(id);
			}
			
			@Override
			public void onFailure(ResponseException caught) {
				callback.onFailure(caught);
			}
		});
	}
	
	public void getAllInvoices(AsyncCallback<List<Invoice>> callback) {
		restServices.getAllInvoices(callback);
	}
	
	public void deleteInvoice(final Invoice invoice, final AsyncCallbackNoReturnValue callback) {
		restServices.deleteInvoice(invoice, new AsyncCallbackNoReturnValue() {
			
			@Override
			public void onSuccess() {
				invoices.remove(invoice);
				eventBus.fireEvent(new InvoiceRemovedEvent(invoice));
				callback.onSuccess();
			}
			
			@Override
			public void onFailure(ResponseException caught) {
				callback.onFailure(caught);
			}
		});
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




















































