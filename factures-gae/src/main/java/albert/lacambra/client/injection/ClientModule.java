package albert.lacambra.client.injection;


import com.google.inject.Provides;
import com.google.inject.Singleton;

import albert.lacambra.client.restservices.IRequestBuilder;
import albert.lacambra.client.restservices.IRestClient;
import albert.lacambra.client.restservices.RequestBuilder;
import albert.lacambra.client.restservices.RestClient;
import albert.lacambra.client.restservices.RestServices;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import albert.lacambra.client.place.ClientPlaceManager;
import albert.lacambra.client.place.DefaultPlace;
import albert.lacambra.client.place.NameTokens;
import albert.lacambra.client.presenters.NewInvoicePresenter;
import albert.lacambra.client.presenters.NewInvoiceView;
import albert.lacambra.client.presenters.MainPresenter;
import albert.lacambra.client.presenters.MainView;
import albert.lacambra.client.presenters.NewBudgetPresenter;
import albert.lacambra.client.presenters.NewBudgetView;
import albert.lacambra.client.presenters.ResumePresenter;
import albert.lacambra.client.presenters.ResumeView;
import albert.lacambra.client.presenters.utils.BudgetProvider;
import albert.lacambra.client.presenters.utils.InvoiceProvider;
import albert.lacambra.client.injection.InvoiceListPresenter;
import albert.lacambra.client.injection.InvoiceListView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(NewInvoicePresenter.class,
				NewInvoicePresenter.MyView.class, NewInvoiceView.class,
				NewInvoicePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(
				NameTokens.newinvoice);
		
		bind(RestServices.class).in(Singleton.class);
		bind(IRestClient.class).to(RestClient.class).in(Singleton.class);
		bind(IRequestBuilder.class).to(RequestBuilder.class).in(Singleton.class);
		

		bindPresenter(MainPresenter.class, MainPresenter.MyView.class,
				MainView.class, MainPresenter.MyProxy.class);

		bindPresenter(NewBudgetPresenter.class,
				NewBudgetPresenter.MyView.class, NewBudgetView.class,
				NewBudgetPresenter.MyProxy.class);

		bindSingletonPresenterWidget(ResumePresenter.class,
				ResumePresenter.MyView.class, ResumeView.class);


		bindPresenter(InvoiceListPresenter.class,
				InvoiceListPresenter.MyView.class, InvoiceListView.class,
				InvoiceListPresenter.MyProxy.class);
		
		bind(BudgetProvider.class).in(Singleton.class);
		bind(InvoiceProvider.class).in(Singleton.class);
	}
	
	@Provides
	public IRestClient.EndPointProvider getEndPoint() {
		return new IRestClient.EndPointProvider() {

			@Override
			public String getEndPoint() {
				return "/rest/";
			}
		};
	}
}
