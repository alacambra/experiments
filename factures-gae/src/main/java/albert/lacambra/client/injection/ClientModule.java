package albert.lacambra.client.injection;


import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import albert.lacambra.client.restservices.IRequestBuilder;
import albert.lacambra.client.restservices.IRestClient;
import albert.lacambra.client.restservices.RequestBuilder;
import albert.lacambra.client.restservices.RestClient;

import com.gwtplatform.mvp.client.gin.DefaultModule;

import albert.lacambra.client.place.ClientPlaceManager;
import albert.lacambra.client.place.DefaultPlace;
import albert.lacambra.client.place.NameTokens;
import albert.lacambra.client.presenters.NewInvoicePresenter;
import albert.lacambra.client.presenters.NewInvoiceView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(NewInvoicePresenter.class,
				NewInvoicePresenter.MyView.class, NewInvoiceView.class,
				NewInvoicePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(
				NameTokens.newinvoice);
		
		bind(IRestClient.class).to(RestClient.class).in(Singleton.class);
		bind(IRequestBuilder.class).to(RequestBuilder.class).in(Singleton.class);
		
	}
	
	@Provides
	public IRestClient.EndPointProvider getEndPoint() {
		//		if( GWT.isProdMode() ) {
		return new IRestClient.EndPointProvider() {

			@Override
			public String getEndPoint() {

//				String host = Window.Location.getHostName();
				return "/rest/";
//				if (host.equals("http://local.poolingpeople.org/")) {
//
//					return Window.Location.getHostName() + "/rest/";
//
//				} else if(GWT.getHostPageBaseURL().matches(".*apitester-clientdev.*")) {
//
//					return "http://" + Window.Location.getHostName() + "/ppapi_clientdev/rest/";
//
//				} else if(GWT.getHostPageBaseURL().matches(".*apitester-apidev.*")) {
//
//					if(GWT.isProdMode()) {
//						return "http://" + Window.Location.getHostName() + "/ppapi_apidev/rest/";
//					} else {
//						return "http://" + Window.Location.getHostName() + "/rest/";
//					}
//
//				} else if(GWT.getHostPageBaseURL().matches(".*apitester-test.*")) {
//
//					return "http://" + Window.Location.getHostName() + "/ppapi_test/rest/";
//
//				} else {
//					return "http://" + Window.Location.getHostName() + "/rest/";
//				}
			}
		};
	}
}
