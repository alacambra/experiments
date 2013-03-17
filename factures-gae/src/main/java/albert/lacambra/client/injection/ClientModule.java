package albert.lacambra.client.injection;


import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import albert.lacambra.client.place.ClientPlaceManager;
import albert.lacambra.client.place.DefaultPlace;
import albert.lacambra.client.place.NameTokens;
import albert.lacambra.client.injection.TestPresenter;
import albert.lacambra.client.injection.TestView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));


		bindPresenter(TestPresenter.class, TestPresenter.MyView.class,
				TestView.class, TestPresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.test);
	}
}
