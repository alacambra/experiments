package albert.lacambra.client.presenters.utils;

import java.util.Collection;
import java.util.HashMap;

import com.allen_sauer.gwt.log.client.Log;
import com.google.web.bindery.event.shared.EventBus;

import albert.lacambra.client.events.BudgetAddedEvent;
import albert.lacambra.client.events.BudgetDeletedEvent;
import albert.lacambra.client.models.Budget;

public class BudgetProvider implements CollectionProvider<Budget> {

	HashMap<Long, Budget> budgets = new HashMap<Long, Budget>();
	private EventBus eventBus;

	public BudgetProvider() {}

	@Override
	public void configure(Collection<Budget> budgets, EventBus eventBus) {

		for ( Budget i : budgets) {
			this.budgets.put(i.getId(), i);
		}

		this.eventBus = eventBus;
		loadHandlers();

	}

	private void loadHandlers() {

		this.eventBus.addHandler(BudgetAddedEvent.TYPE, new BudgetAddedEvent.BudgetAddedHandler() {

			@Override
			public void onBudgetAdded(BudgetAddedEvent event) {
				budgets.put(event.getBudget().getId(), event.getBudget());
			}
		});

		this.eventBus.addHandler(BudgetDeletedEvent.TYPE, new BudgetDeletedEvent.BudgetDeletedHandler() {

			@Override
			public void onBudgetDeleted(BudgetDeletedEvent event) {
				
				Budget i = budgets.remove(event.getBudget().getId());

				if ( i == null ) {
					Log.info("budget not found in provider " + event.getBudget().getId());
				}

			}
		});
	}

	@Override
	public Budget get(Long id) {

		return budgets.get(id);

	}

	@Override
	public Collection<Budget> get() {
		return budgets.values();
	}
	
	public HashMap<Long, Budget> getBudgets() {
		return budgets;
	}

}
