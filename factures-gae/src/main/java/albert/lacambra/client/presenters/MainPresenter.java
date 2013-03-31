package albert.lacambra.client.presenters;

import albert.lacambra.client.place.NameTokens;

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
			final MyProxy proxy) {
		super(eventBus, view, proxy);
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

				PlaceRequest placeRequest = new PlaceRequest(NameTokens.newbudget);
				placeManager.revealPlace(placeRequest);

			}
		}));

		registerHandler(getView().getNewInvoiceBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				PlaceRequest placeRequest = new PlaceRequest(NameTokens.newinvoice);
				placeManager.revealPlace(placeRequest);

			}
		}));

	}
}


































