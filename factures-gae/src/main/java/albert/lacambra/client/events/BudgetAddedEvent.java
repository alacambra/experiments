package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import albert.lacambra.client.models.Budget;
import com.google.gwt.event.shared.HasHandlers;

public class BudgetAddedEvent extends
		GwtEvent<BudgetAddedEvent.BudgetAddedHandler> {

	public static Type<BudgetAddedHandler> TYPE = new Type<BudgetAddedHandler>();
	private Budget budget;

	public interface BudgetAddedHandler extends EventHandler {
		void onBudgetAdded(BudgetAddedEvent event);
	}

	public BudgetAddedEvent(Budget budget) {
		this.budget = budget;
	}

	public Budget getBudget() {
		return budget;
	}

	@Override
	protected void dispatch(BudgetAddedHandler handler) {
		handler.onBudgetAdded(this);
	}

	@Override
	public Type<BudgetAddedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<BudgetAddedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Budget budget) {
		source.fireEvent(new BudgetAddedEvent(budget));
	}
}
