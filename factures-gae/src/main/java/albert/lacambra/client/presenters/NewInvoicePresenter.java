package albert.lacambra.client.presenters;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.place.NameTokens;
import albert.lacambra.client.restservices.RestServices;
import albert.lacambra.client.restservices.utils.AsyncCallback;
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
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class NewInvoicePresenter extends
Presenter<NewInvoicePresenter.MyView, NewInvoicePresenter.MyProxy> {

	public interface MyView extends View {
		public Button getButton();
		public TextBox getPrice();
		public TextBox getDay();
		public TextBox getMonth();
		public TextBox getYear();
		public TextBox getCategory();
		public TextBox getExtra();
		public Label getInfoLabel();
		void restartFields();
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
				restServices.getAllInvoices(new AsyncCallback<List<Invoice>>() {
					
					@Override
					public void onSuccess(List<Invoice> result) {
						Log.info("results received " +result.size());
					}
					
					@Override
					public void onFailure(ResponseException caught) {
						Log.error("",caught);
					}
				});
				Invoice invoice = new Invoice();
				invoice.setCategory(getView().getCategory().getText());
				invoice.setDate(getDate());
				invoice.setExtra(getView().getExtra().getText());
				addInvoice(invoice);
			}
		});
	}
	
	public void addInvoice(Invoice invoice) {
		restServices.addInvoice(invoice, new AsyncCallback<Long>() {
			
			@Override
			public void onSuccess(Long result) {
				Log.info("invoice added with id " + result );
				
			}
			
			@Override
			public void onFailure(ResponseException caught) {
				Log.error("",caught);
			}
		});
	}
	
	private Long getDate()
	{
		String y = getView().getYear().getValue().matches("^[0-9]{4}$") ? getView().getYear().getValue() : "1970";
		
		String m = getView().getMonth().getValue().matches("^[0-1]{0,1}[0-9]{1}$") ? getView().getMonth().getValue() : "01";
		m = m.length() < 2 ? 0 + m : m;
		
		String d = getView().getDay().getValue().matches("^[0-3]{0,1}[0-9]{1}$") ? getView().getDay().getValue() : "01";
		d = d.length() < 2 ? 0 + d : d;
		
		String date = d + "-" + m + "-" + y;
		DateTimeFormat format = DateTimeFormat.getFormat("dd-MM-yyyy");
		return format.parse(date).getTime();
	}
	
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPresenter.TYPE_MainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
