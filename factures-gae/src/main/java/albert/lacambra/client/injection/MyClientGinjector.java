package albert.lacambra.client.injection;

import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.inject.client.AsyncProvider;
import albert.lacambra.client.presenters.InvoiceListPresenter;

public interface MyClientGinjector extends Ginjector{

	AsyncProvider<InvoiceListPresenter> getInvoiceListPresenter();

	
}
