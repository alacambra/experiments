package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;

import albert.lacambra.client.models.PeriodicCost;
import albert.lacambra.client.restservices.PeriodicCostProvider;

import com.google.gwt.event.shared.HasHandlers;

public class PeriodicCostAddedEvent extends
		GwtEvent<PeriodicCostAddedEvent.PeriodicCostAddedHandler> {

	public static Type<PeriodicCostAddedHandler> TYPE = new Type<PeriodicCostAddedHandler>();
	private PeriodicCost periodicCost;

	public interface PeriodicCostAddedHandler extends EventHandler {
		void onPeriodicCostAdded(PeriodicCostAddedEvent event);
	}

	public PeriodicCostAddedEvent(PeriodicCost periodicCost) {
		this.periodicCost = periodicCost;
	}

	public PeriodicCost getPeriodicCost() {
		return periodicCost;
	}

	@Override
	protected void dispatch(PeriodicCostAddedHandler handler) {
		handler.onPeriodicCostAdded(this);
	}

	@Override
	public Type<PeriodicCostAddedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<PeriodicCostAddedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, PeriodicCost periodicCost) {
		source.fireEvent(new PeriodicCostAddedEvent(periodicCost));
	}
}
