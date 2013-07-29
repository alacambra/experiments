package albert.lacambra.client.presenters;

import java.util.List;

import albert.lacambra.client.events.InvoicesLoadedEvent;
import albert.lacambra.client.models.Budget;
import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.place.NameTokens;
import albert.lacambra.client.restservices.BudgetProvider;
import albert.lacambra.client.restservices.InvoiceProvider;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.ResponseException;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Button;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPresenter extends Presenter<MainPresenter.MyView, MainPresenter.MyProxy> {

	@Inject PlaceManager placeManager;
	BudgetProvider budgetProvider;
	InvoiceProvider invoiceProvider;

	@ContentSlot 
	public static final Type<RevealContentHandler<?>> TYPE_MainContent = new Type<RevealContentHandler<?>>();

	public static final Object SLOT_controls = new Object();


	public interface MyView extends View {

		Button getNewBudgetBtn();
		Button getNewInvoiceBtn();
	}

	@ProxyCodeSplit
	public interface MyProxy extends Proxy<MainPresenter> {
	}

	@Inject
	public MainPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy,
			BudgetProvider budgetProvider,
			InvoiceProvider invoiceProvider) {
		super(eventBus, view, proxy);
		
		this.invoiceProvider = invoiceProvider;
		this.budgetProvider = budgetProvider;

	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		registerHandler(getView().getNewBudgetBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.newbudget).build();
				placeManager.revealPlace(placeRequest);

			}
		}));

		registerHandler(getView().getNewInvoiceBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.newinvoice).build();
				placeManager.revealPlace(placeRequest);

			}
		}));

		invoiceProvider.getAllInvoices(new AsyncCallback<List<Invoice>>() {

			@Override
			public void onSuccess(List<Invoice> result) {
				invoiceProvider.configure(result);

				budgetProvider.getBudgets("2013", new AsyncCallback<List<Budget>>() {

					@Override
					public void onSuccess(List<Budget> result) {
						budgetProvider.configure(result);

						for ( Invoice i: invoiceProvider.get()) {
							i.setBudget(budgetProvider.getBudgets().get(i.getBudgetId()));
						}

						getEventBus().fireEvent(new InvoicesLoadedEvent());

					}

					@Override
					public void onFailure(ResponseException caught) {
						Log.error(caught.getRequest().toString(), caught);
					}
				});

			}

			@Override
			public void onFailure(ResponseException caught) {
				Log.error(caught.getRequest().toString(), caught);
			}
		});

	}
}


































