package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class InvoicesLoadedEvent extends
		GwtEvent<InvoicesLoadedEvent.InvoicesLoadedHandler> {

	public static Type<InvoicesLoadedHandler> TYPE = new Type<InvoicesLoadedHandler>();

	public interface InvoicesLoadedHandler extends EventHandler {
		void onInvoicesLoaded(InvoicesLoadedEvent event);
	}

	public InvoicesLoadedEvent() {
	}

	@Override
	protected void dispatch(InvoicesLoadedHandler handler) {
		handler.onInvoicesLoaded(this);
	}

	@Override
	public Type<InvoicesLoadedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<InvoicesLoadedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new InvoicesLoadedEvent());
	}
}
