package albert.lacambra.client.restservices;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.user.client.Window;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class RestGinjectorModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(IRestClient.class).to(RestClient.class).in(Singleton.class);
		bind(IRequestBuilder.class).to(RequestBuilder.class).in(Singleton.class);
		bind(Credentials.class).in(Singleton.class);
		//		bind(IRestServices.class).to(RestServices.class).in(Singleton.class);
		//		bind(EventBus.class).to(SimpleEventBus.class);
	}

	@Provides
	public IRestClient.EndPointProvider getEndPoint() {
		//		if( GWT.isProdMode() ) {
		return new IRestClient.EndPointProvider() {

			@Override
			public String getEndPoint() {

				String host = Window.Location.getHostName();

				if (host.equals("http://local.poolingpeople.org/")) {

					return Window.Location.getHostName() + "/rest/";

				} else if(GWT.getHostPageBaseURL().matches(".*apitester-clientdev.*")) {

					return "http://" + Window.Location.getHostName() + "/ppapi_clientdev/rest/";

				} else if(GWT.getHostPageBaseURL().matches(".*apitester-apidev.*")) {

					if(GWT.isProdMode()) {
						return "http://" + Window.Location.getHostName() + "/ppapi_apidev/rest/";
					} else {
						return "http://" + Window.Location.getHostName() + "/rest/";
					}

				} else if(GWT.getHostPageBaseURL().matches(".*apitester-test.*")) {

					return "http://" + Window.Location.getHostName() + "/ppapi_test/rest/";

				} else {
					return "http://" + Window.Location.getHostName() + "/rest/";
				}
			}
		};
		//		} else {
		//			return new IRestClient.EndPointProvider() {
		//
		//				@Override
		//				public String getEndPoint() {
		//					return "http://" + Window.Location.getHostName() + "/ppapi_clientdev/rest/";
		//				}
		//			};
		//		}
	}

}
