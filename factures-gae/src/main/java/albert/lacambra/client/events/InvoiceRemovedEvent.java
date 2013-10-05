package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import albert.lacambra.client.models.Invoice;
import com.google.gwt.event.shared.HasHandlers;

public class InvoiceRemovedEvent extends
		GwtEvent<InvoiceRemovedEvent.InvoiceRemovedHandler> {

	public static Type<InvoiceRemovedHandler> TYPE = new Type<InvoiceRemovedHandler>();
	private Invoice invoiceId;

	public interface InvoiceRemovedHandler extends EventHandler {
		void onInvoiceRemoved(InvoiceRemovedEvent event);
	}

	public InvoiceRemovedEvent(Invoice invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Invoice getInvoiceId() {
		return invoiceId;
	}

	@Override
	protected void dispatch(InvoiceRemovedHandler handler) {
		handler.onInvoiceRemoved(this);
	}

	@Override
	public Type<InvoiceRemovedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<InvoiceRemovedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Invoice invoiceId) {
		source.fireEvent(new InvoiceRemovedEvent(invoiceId));
	}
}
