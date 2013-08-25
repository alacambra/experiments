package albert.lacambra.client.events;

import albert.lacambra.client.models.IndividualCost;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class IndividualCostAddedEvent extends
		GwtEvent<IndividualCostAddedEvent.IndividualCostAddedHandler> {

	public static Type<IndividualCostAddedHandler> TYPE = new Type<IndividualCostAddedHandler>();

	public interface IndividualCostAddedHandler extends EventHandler {
		void onIndividualCostAdded(IndividualCostAddedEvent event);
	}

	private IndividualCost individualCost;

	public IndividualCostAddedEvent(IndividualCost individualCost) {
		this.individualCost = individualCost;
	}
	
	public IndividualCost getIndividualCost() {
		return individualCost;
	}

	@Override
	protected void dispatch(IndividualCostAddedHandler handler) {
		handler.onIndividualCostAdded(this);
	}

	@Override
	public Type<IndividualCostAddedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<IndividualCostAddedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, IndividualCost individualCost) {
		source.fireEvent(new IndividualCostAddedEvent(individualCost));
	}
}
