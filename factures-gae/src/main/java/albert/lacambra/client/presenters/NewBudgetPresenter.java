package albert.lacambra.client.presenters;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import albert.lacambra.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import albert.lacambra.client.presenters.MainPresenter;

public class NewBudgetPresenter extends
		Presenter<NewBudgetPresenter.MyView, NewBudgetPresenter.MyProxy> {

	public interface MyView extends View {
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
	}
}
