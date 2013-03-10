package albert.lacambra.client.injection;

import albert.lacambra.factures.app.place.ClientPlaceManager;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

//		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.newinvoice);
		
	}
}
