package albert.lacambra.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import albert.lacambra.client.models.Budget;
import com.google.gwt.event.shared.HasHandlers;

public class BudgetDeletedEvent extends
		GwtEvent<BudgetDeletedEvent.BudgetDeletedHandler> {

	public static Type<BudgetDeletedHandler> TYPE = new Type<BudgetDeletedHandler>();
	private Budget Budget;

	public interface BudgetDeletedHandler extends EventHandler {
		void onBudgetDeleted(BudgetDeletedEvent event);
	}

	public BudgetDeletedEvent(Budget Budget) {
		this.Budget = Budget;
	}

	public Budget getBudget() {
		return Budget;
	}

	@Override
	protected void dispatch(BudgetDeletedHandler handler) {
		handler.onBudgetDeleted(this);
	}

	@Override
	public Type<BudgetDeletedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<BudgetDeletedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Budget Budget) {
		source.fireEvent(new BudgetDeletedEvent(Budget));
	}
}
