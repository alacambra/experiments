package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import albert.lacambra.client.models.PeriodicCost;
import com.google.gwt.event.shared.HasHandlers;

public class PeriodicCostDeletedEvent extends
		GwtEvent<PeriodicCostDeletedEvent.PeriodicCostDeletedHandler> {

	public static Type<PeriodicCostDeletedHandler> TYPE = new Type<PeriodicCostDeletedHandler>();
	private PeriodicCost periodicCost;

	public interface PeriodicCostDeletedHandler extends EventHandler {
		void onPeriodicCostDeleted(PeriodicCostDeletedEvent event);
	}

	public PeriodicCostDeletedEvent(PeriodicCost periodicCost) {
		this.periodicCost = periodicCost;
	}

	public PeriodicCost getPeriodicCost() {
		return periodicCost;
	}

	@Override
	protected void dispatch(PeriodicCostDeletedHandler handler) {
		handler.onPeriodicCostDeleted(this);
	}

	@Override
	public Type<PeriodicCostDeletedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<PeriodicCostDeletedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, PeriodicCost periodicCost) {
		source.fireEvent(new PeriodicCostDeletedEvent(periodicCost));
	}
}
