package albert.lacambra.client.restservices;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RestModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(RestServices.class).in(Singleton.class);
	}
}
