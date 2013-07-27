package albert.lacambra.client.presenters.utils;

import java.util.Collection;
import java.util.HashMap;

import com.allen_sauer.gwt.log.client.Log;
import com.google.web.bindery.event.shared.EventBus;

import albert.lacambra.client.events.InvoiceAddedEvent;
import albert.lacambra.client.events.InvoiceDeletedEvent;
import albert.lacambra.client.models.Invoice;

public class InvoiceProvider implements CollectionProvider<Invoice> {

	HashMap<Long, Invoice> invoices = new HashMap<Long, Invoice>();
	private EventBus eventBus;
	
	public InvoiceProvider() {}
	
	@Override
	public void configure(Collection<Invoice> invoices, EventBus eventBus) {

		for ( Invoice i : invoices) {
			this.invoices.put(i.getId(), i);
		}

		this.eventBus = eventBus;
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

	@Override
	public Invoice get(Long id) {

		return invoices.get(id);

	}

	@Override
	public Collection<Invoice> get() {
		return invoices.values();
	}

}
