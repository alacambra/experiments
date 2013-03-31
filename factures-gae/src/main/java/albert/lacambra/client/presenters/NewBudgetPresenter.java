package albert.lacambra.client.presenters;


import org.junit.experimental.categories.Categories.IncludeCategory;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.client.models.Budget;
import albert.lacambra.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import albert.lacambra.client.presenters.MainPresenter;
import albert.lacambra.client.restservices.RestServices;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.ResponseException;

public class NewBudgetPresenter extends Presenter<NewBudgetPresenter.MyView, NewBudgetPresenter.MyProxy> {

	@Inject RestServices services;
	
	public interface MyView extends View {

		HasText getName();

		HasText getAssignation();

		HasText getYear();

		void setName(String name);

		void setAssignation(String assignation);

		void setYear(String year);

		HasClickHandlers getSubmit();

		void restartFields();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.newbudget)
	public interface MyProxy extends ProxyPlace<NewBudgetPresenter> {
	}

	@Inject
	public NewBudgetPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPresenter.TYPE_MainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		registerHandler(getView().getSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Budget budget = new Budget();
				assignDates(budget);
				budget.setAssignation(Integer.valueOf(getView().getAssignation().getText()) * 100);
				budget.setName(getView().getName().getText());
				services.addBudget(budget, new AsyncCallback<Long>() {
					
					@Override
					public void onSuccess(Long result) {
						Log.info("budget added with id " + result);
					}
					
					@Override
					public void onFailure(ResponseException caught) {
						Log.error("", caught);
					}
				});
			}
		}));
	}

	private void assignDates(Budget budget) {

		String y = getView().getYear().getText();
		Log.info(y + ":" + y.length());
		String year = y.matches("^[0-9]{4}$") ? y : null;
		
		if (year == null) {
			throw new IllegalArgumentException("Date must have 4 cyphers");
		}
		
		DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.S"); 
		budget.setStart(format.parse(year + "-01-01 00:00:00.0").getTime());
		budget.setEnd(format.parse(year + "-12-31 23:59:59.9").getTime());
		
	}
}












































