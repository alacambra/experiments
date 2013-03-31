package albert.lacambra.client.restservices;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;

import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.models.IsJso;
import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.ResponseException;
import albert.lacambra.shared.ResourceLocator;

public class RestServices {

	private final IRestClient client;

	@Inject
	public RestServices(
			IRestClient newClient
			) {

		this.client = newClient;
	}

	public void getInvoice(Long id, AsyncCallback<Invoice> callback) {
		client.get(ResourceLocator.invoiceBase, id,  new SimpleCallback<Invoice>(callback, new Invoice()));

	}
	
	public void addInvoice(Invoice i, AsyncCallback<Long> callback) {
		client.put(ResourceLocator.invoiceBase,i.serializeToJsonValue(), callback);
	}
	
	public void getAllInvoices(AsyncCallback<List<Invoice>> callback) {
		client.get(ResourceLocator.invoiceBase, new CollectionSimpleCallback<Invoice>(callback, new Invoice()));

	}

	private class SimpleCallback<T extends IsJso<T>> implements AsyncCallback<JSONValue> {

		private AsyncCallback<T> callback;
		private T instance;

		public SimpleCallback( AsyncCallback<T> callback, T instance) {

			if (callback == null || instance == null)
				throw new IllegalArgumentException("Callback cannot be null");

			this.callback = callback;
			this.instance = instance;
		}

		@Override
		public void onFailure(ResponseException caught) {
			callback.onFailure(caught);

		}

		@Override
		public void onSuccess(JSONValue result) {

			T res = instance.buildJso(result.isString().stringValue());
			callback.onSuccess(res);

		}
	}
	
	private class CollectionSimpleCallback<T extends IsJso<T>> implements AsyncCallback<JSONValue> {

		private AsyncCallback<List<T>> callback;
		private T instance;

		public CollectionSimpleCallback( AsyncCallback<List<T>> callback, T instance) {

			if (callback == null || instance == null)
				throw new IllegalArgumentException("Callback cannot be null");

			this.callback = callback;
			this.instance = instance;
		}

		@Override
		public void onFailure(ResponseException caught) {
			callback.onFailure(caught);

		}

		@Override
		public void onSuccess(JSONValue result) {

			List<T> invoices = new ArrayList<T>();
			
			JSONArray array = result.isArray();
			
			if ( array == null ) {
				callback.onFailure(new ResponseException(null));
			}
			
			for ( int i= 0; i < array.size(); i++  ) {
				invoices.add(instance.buildJso(array.get(i).isString().stringValue()));
			}
			
			
			callback.onSuccess(invoices);

		}
	}

}
