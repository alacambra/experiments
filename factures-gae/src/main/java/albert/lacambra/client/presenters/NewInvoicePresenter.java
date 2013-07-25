package albert.lacambra.client.presenters;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.client.models.Budget;
import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.place.NameTokens;
import albert.lacambra.client.restservices.RestServices;
import albert.lacambra.client.restservices.utils.ResponseException;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import albert.lacambra.client.restservices.utils.AsyncCallback;

public class NewInvoicePresenter extends Presenter<NewInvoicePresenter.MyView, NewInvoicePresenter.MyProxy> {

	public interface MyView extends View {
		public Button getButton();
		public TextBox getPrice();
		public TextBox getDay();
		public TextBox getMonth();
		public TextBox getYear();
		public VerticalPanel getBudgetsPanel();
		public TextBox getExtra();
		public Label getInfoLabel();
		void restartFields();
		void addPossibleBudget(String id, String name);
		String getSelectedBudgetId();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.newinvoice)
	public interface MyProxy extends ProxyPlace<NewInvoicePresenter> {
	}

	private RestServices restServices;

	@Inject
	public NewInvoicePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final RestServices restServices) {

		super(eventBus, view, proxy);
		this.restServices = restServices;
		getView().getButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				String bid = getView().getSelectedBudgetId();
				
				if  (  bid ==  null ) bid="1";
				
				Invoice invoice = 
					(Invoice) new Invoice()
					.setBudgetId(Long.parseLong(bid))
					.setDate(getDate())
					.setExtra(getView().getExtra().getText())
					.setPrice(Integer.parseInt(getView().getPrice().getText()));
				
				addInvoice(invoice);
			}
		});
	}

	public void addInvoice(final Invoice invoice) {
		
		restServices.addInvoice(invoice, new AsyncCallback<Long>() {

			@Override
			public void onSuccess(Long result) {
				Log.info("invoice added with id " + result );
				getView()
					.getInfoLabel()
					.setText("invoice \"" + invoice.getExtra() +"\" added with id " + result );
				
				getView().restartFields();
			}

			@Override
			public void onFailure( ResponseException caught) {
				Log.error("",caught);
				getView().getInfoLabel().setText("error adding invoice:" +  caught.getMessage());
			}
		});
	}

	public void addBugetIntoList(Budget budget) {
		getView().addPossibleBudget(String.valueOf(budget.getId()), budget.getName());
	}

	private Long getDate() {
		String y = getView().getYear().getValue().matches("^[0-9]{4}$") ? getView().getYear().getValue() : "1970";

		String m = getView().getMonth().getValue().matches("^[0-1]{0,1}[0-9]{1}$") ? getView().getMonth().getValue() : "01";
		m = m.length() < 2 ? 0 + m : m;

		String d = getView().getDay().getValue().matches("^[0-3]{0,1}[0-9]{1}$") ? getView().getDay().getValue() : "01";
		d = d.length() < 2 ? 0 + d : d;

		String date = d + "-" + m + "-" + y + " 10:00:00.0";
		DateTimeFormat format = DateTimeFormat.getFormat("dd-MM-yyyy HH:mm:ss.S");
		Log.debug(date + ":" + format.parse(date).getTime());
		return format.parse(date).getTime();
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPresenter.TYPE_MainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		loadBudgets(getView().getYear().getValue());

	}

	private void loadBudgets(String year) {

		if ( year != null ) {
			
			restServices.getBudgets(year, new AsyncCallback<List<Budget>>() {

				@Override
				public void onSuccess(List<Budget> result) {
					for (Budget budget : result) {
						Log.info(budget.getName());
						getView().addPossibleBudget(String.valueOf(budget.getId()), budget.getName());
					}
				}

				@Override
				public void onFailure(ResponseException caught) {
					Log.error("", caught);
				}
			});
		}
	}

}


















































