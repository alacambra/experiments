package albert.lacambra.client.restservices;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import albert.lacambra.client.events.BudgetAddedEvent;
import albert.lacambra.client.events.BudgetDeletedEvent;
import albert.lacambra.client.models.Budget;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.ResponseException;
import albert.lacambra.shared.ResourceLocator;

public class BudgetProvider implements CollectionProvider<Budget> {

	HashMap<Long, Budget> budgets = new HashMap<Long, Budget>();
	private EventBus eventBus;
	IBudgetService budgetService;

	@Inject
	public BudgetProvider(EventBus eventBus) {
		
		this.eventBus = eventBus;
		
		Resource resource = new Resource( GWT.getHostPageBaseURL()+ "rest/" + ResourceLocator.budgetBase);

		budgetService = GWT.create(IBudgetService.class);
		((RestServiceProxy)budgetService).setResource(resource);

	}

	@Override
	public void configure(Collection<Budget> budgets) {

		for ( Budget i : budgets) {
			this.budgets.put(i.getId(), i);
		}

//		loadHandlers();

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
	
	public void getBudgets(String year, final AsyncCallback<List<Budget>> callback) {
		budgetService.getAllBudgets(new MethodCallback<List<Budget>>() {
			
			@Override
			public void onSuccess(Method method, List<Budget> response) {
				callback.onSuccess(response);
				
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				callback.onFailure(new ResponseException(method.getResponse(), exception));
			}
		});
	}
	
	public void addBudget(Budget b, final AsyncCallback<Long> callback) {
		budgetService.addBudget(b, new MethodCallback<Long>() {

			@Override
			public void onFailure(Method method, Throwable exception) {
				callback.onFailure(new ResponseException(method.getResponse(), exception));
				
			}

			@Override
			public void onSuccess(Method method, Long response) {
				callback.onSuccess(response);
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
