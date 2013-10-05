package albert.lacambra.client.presenters;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import albert.lacambra.client.models.Budget;
import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.restservices.BudgetProvider;
import albert.lacambra.client.restservices.InvoiceProvider;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.ResponseException;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class ResumePresenter extends PresenterWidget<ResumePresenter.MyView> {

	@Inject BudgetProvider budgetProvider;
	@Inject InvoiceProvider invoiceProvider;
	
	public interface MyView extends View {

		void clear();
		Button getRefreshBtn();
		void addBudget(String bgName, String value);

	}

	@Inject
	public ResumePresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().getRefreshBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				budgetProvider.getBudgets("2013", new AsyncCallback<List<Budget>>() {
					
					@Override
					public void onSuccess(final List<Budget> budgets) {
						invoiceProvider.getAllInvoices(new AsyncCallback<List<Invoice>>() {
							
							@Override
							public void onSuccess(List<Invoice> invoices) {
								getView().clear();
								calculateAndLoad(budgets, invoices);
							}
							
							@Override
							public void onFailure(ResponseException caught) {
								Log.error("", caught);
							}
						});
					}
					
					@Override
					public void onFailure(ResponseException caught) {
						Log.error("", caught);
					}
				});
			}
		}));
	}
	
	private void calculateAndLoad(List<Budget> budgets, List<Invoice> invoices) {
		
		HashMap<Long, Integer> totals = new HashMap<Long, Integer>();
		HashMap<Long, Budget> names = new HashMap<Long, Budget>();
		
		for ( Budget bg : budgets ) {
			
			totals.put(bg.getId(), 0);
			names.put(bg.getId(), bg);
			
		}
		
		for ( Invoice i : invoices ) {
			
			if ( totals.containsKey(i.getBudgetId()) ) {
				
				totals.put(i.getBudgetId(), totals.get(i.getBudgetId()) + i.getPrice());
				
			} else {
				
				Log.info("Invoice " + i.getId() + " has inexistent budget " + i.getBudgetId());
				
			}
		}
		
		for ( Entry<Long, Integer> bg : totals.entrySet() ) {
			
			int used = bg.getValue() / 100;
			int assignation = names.get(bg.getKey()).getAssignation() / 100;
			
			String value = 
					used + "€" 
					+ " over " 
					+ assignation + "€ " 
					+ "(" + used * 100 /assignation + "%)";  
			
			getView().addBudget(names.get(bg.getKey()).getName(), value);
			
		}
		
	}
	
}











































