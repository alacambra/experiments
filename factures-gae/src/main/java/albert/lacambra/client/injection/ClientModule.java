package albert.lacambra.client.injection;

import albert.lacambra.factures.app.place.ClientPlaceManager;
import albert.lacambra.factures.app.place.DefaultPlace;
import albert.lacambra.factures.app.place.NameTokens;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import albert.lacambra.client.presenters.DefaultPresenter;
import albert.lacambra.client.presenters.DefaultView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.start);
		
		bindPresenter(DefaultPresenter.class, DefaultPresenter.MyView.class,
				DefaultView.class, DefaultPresenter.MyProxy.class);
	}
}
