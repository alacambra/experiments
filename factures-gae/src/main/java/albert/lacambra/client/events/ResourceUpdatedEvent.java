package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class ResourceUpdatedEvent extends
		GwtEvent<ResourceUpdatedEvent.ResourceUpdatedHandler> {

	public static Type<ResourceUpdatedHandler> TYPE = new Type<ResourceUpdatedHandler>();

	public interface ResourceUpdatedHandler extends EventHandler {
		void onResourceUpdated(ResourceUpdatedEvent event);
	}

	public ResourceUpdatedEvent() {
	}

	@Override
	protected void dispatch(ResourceUpdatedHandler handler) {
		handler.onResourceUpdated(this);
	}

	@Override
	public Type<ResourceUpdatedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ResourceUpdatedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new ResourceUpdatedEvent());
	}
}
