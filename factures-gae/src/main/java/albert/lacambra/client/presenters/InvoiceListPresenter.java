package albert.lacambra.client.presenters;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.client.events.InvoiceAddedEvent;
import albert.lacambra.client.events.InvoiceRemovedEvent;
import albert.lacambra.client.events.InvoicesLoadedEvent;
import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.place.NameTokens;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import albert.lacambra.client.presenters.MainPresenter;
import albert.lacambra.client.restservices.InvoiceProvider;
import albert.lacambra.client.widgets.InvoiceCellList;

public class InvoiceListPresenter extends
Presenter<InvoiceListPresenter.MyView, InvoiceListPresenter.MyProxy> {

	InvoiceProvider invoiceProvider;
	ListDataProvider<Invoice> provider;

	public interface MyView extends View {
		HasData<Invoice> getCellTable();

		InvoiceCellList getCellList();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.invoicelist)
	public interface MyProxy extends ProxyPlace<InvoiceListPresenter> {
	}

	@Inject
	public InvoiceListPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, InvoiceProvider invoiceProvider) {
		super(eventBus, view, proxy);
		this.invoiceProvider = invoiceProvider;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPresenter.TYPE_MainContent, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}


	@Override
	protected void onReset() {
		super.onReset();
	}

	@Override
	protected void onBind() {

		super.onBind();
		provider = new ListDataProvider<Invoice>();
		provider.addDataDisplay(getView().getCellTable());

		getEventBus().addHandler(InvoicesLoadedEvent.TYPE, new InvoicesLoadedEvent.InvoicesLoadedHandler() {

			@Override
			public void onInvoicesLoaded(InvoicesLoadedEvent event) {
				loadInvoicesList();
			}
		});
		
		getEventBus().addHandler(InvoiceAddedEvent.TYPE, new InvoiceAddedEvent.InvoiceAddedHandler() {
			
			@Override
			public void onInvoiceAdded(InvoiceAddedEvent event) {
				provider.getList().add(event.getInvoice());
			}
		});
		
		getEventBus().addHandler(InvoiceRemovedEvent.TYPE, new InvoiceRemovedEvent.InvoiceRemovedHandler(){

			@Override
			public void onInvoiceRemoved(InvoiceRemovedEvent event) {
				
				provider.getList().remove(event.getInvoiceId());
				provider.refresh();
				
			}
			
		});
		

		loadInvoicesList();

	}

	private void loadInvoicesList() {
		List<Invoice> l = new ArrayList<Invoice>();
		l.addAll(invoiceProvider.get());
		provider.setList(l);
		getView().getCellList().getSortHandler().setList(provider.getList());
		provider.refresh();
	}
}






















