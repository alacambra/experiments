package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import albert.lacambra.client.models.Invoice;
import com.google.gwt.event.shared.HasHandlers;

public class InvoiceAddedEvent extends
		GwtEvent<InvoiceAddedEvent.InvoiceAddedHandler> {

	public static Type<InvoiceAddedHandler> TYPE = new Type<InvoiceAddedHandler>();
	private Invoice invoice;

	public interface InvoiceAddedHandler extends EventHandler {
		void onInvoiceAdded(InvoiceAddedEvent event);
	}

	public InvoiceAddedEvent(Invoice invoice) {
		this.invoice = invoice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	protected void dispatch(InvoiceAddedHandler handler) {
		handler.onInvoiceAdded(this);
	}

	@Override
	public Type<InvoiceAddedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<InvoiceAddedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Invoice invoice) {
		source.fireEvent(new InvoiceAddedEvent(invoice));
	}
}
