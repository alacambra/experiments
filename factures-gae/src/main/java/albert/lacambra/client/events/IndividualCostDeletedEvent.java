package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import albert.lacambra.client.models.IndividualCost;
import com.google.gwt.event.shared.HasHandlers;

public class IndividualCostDeletedEvent extends
		GwtEvent<IndividualCostDeletedEvent.IndividualCostDeletedHandler> {

	public static Type<IndividualCostDeletedHandler> TYPE = new Type<IndividualCostDeletedHandler>();
	private IndividualCost individualCost;

	public interface IndividualCostDeletedHandler extends EventHandler {
		void onIndividualCostDeleted(IndividualCostDeletedEvent event);
	}

	public IndividualCostDeletedEvent(IndividualCost individualCost) {
		this.individualCost = individualCost;
	}

	public IndividualCost getIndividualCost() {
		return individualCost;
	}

	@Override
	protected void dispatch(IndividualCostDeletedHandler handler) {
		handler.onIndividualCostDeleted(this);
	}

	@Override
	public Type<IndividualCostDeletedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<IndividualCostDeletedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, IndividualCost individualCost) {
		source.fireEvent(new IndividualCostDeletedEvent(individualCost));
	}
}
