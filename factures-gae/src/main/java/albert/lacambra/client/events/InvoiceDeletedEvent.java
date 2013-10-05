package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import albert.lacambra.client.models.Invoice;
import com.google.gwt.event.shared.HasHandlers;

public class InvoiceDeletedEvent extends
		GwtEvent<InvoiceDeletedEvent.InvoiceDeletedHandler> {

	public static Type<InvoiceDeletedHandler> TYPE = new Type<InvoiceDeletedHandler>();
	private Invoice invoice;

	public interface InvoiceDeletedHandler extends EventHandler {
		void onInvoiceDeleted(InvoiceDeletedEvent event);
	}

	public InvoiceDeletedEvent(Invoice invoice) {
		this.invoice = invoice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	protected void dispatch(InvoiceDeletedHandler handler) {
		handler.onInvoiceDeleted(this);
	}

	@Override
	public Type<InvoiceDeletedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<InvoiceDeletedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Invoice invoice) {
		source.fireEvent(new InvoiceDeletedEvent(invoice));
	}
}
