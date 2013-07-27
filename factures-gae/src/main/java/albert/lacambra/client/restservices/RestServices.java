package albert.lacambra.client.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;

import albert.lacambra.client.models.Budget;
import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.models.IsJsonSerializable;
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
	
	public void addInvoice(IsJsonSerializable i, AsyncCallback<Long> callback) {
		client.put(ResourceLocator.invoiceBase,i.serializeToJsonValue(), callback);
	}
	
	public void getAllInvoices(AsyncCallback<List<Invoice>> callback) {
		client.get(ResourceLocator.invoiceBase, 
				new CollectionSimpleCallback<Invoice>(callback, 
						new InstanceGenerator<Invoice>() {

							@Override
							public Invoice getInstance() {
								return new Invoice();
							}
		}));

	}
	
	public void addBudget(Budget b, AsyncCallback<Long> callback) {
		client.put(ResourceLocator.budgetBase,b.serializeToJsonValue(), callback);
	}
	
	public void getBudgets(String year, AsyncCallback<List<Budget>> callback) {
		client.get(ResourceLocator.budgetBase, 
				new CollectionSimpleCallback<Budget>(callback, 
						new InstanceGenerator<Budget>() {

							@Override
							public Budget getInstance() {
								return new Budget();
							}
						}));
	}

	private class SimpleCallback<T extends IsJsonSerializable> implements AsyncCallback<JSONValue> {

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

			instance.loadFromJson(result);
			callback.onSuccess(instance);

		}
	}
	
	private class CollectionSimpleCallback<T extends IsJsonSerializable> implements AsyncCallback<JSONValue> {

		private AsyncCallback<List<T>> callback;
		private InstanceGenerator<T> instanceGenerator;

		public CollectionSimpleCallback( AsyncCallback<List<T>> callback, InstanceGenerator<T> instanceGenerator) {

			if (callback == null || instanceGenerator == null)
				throw new IllegalArgumentException("Callback cannot be null");

			this.callback = callback;
			this.instanceGenerator = instanceGenerator;
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
				@SuppressWarnings("unchecked")
				T instance = (T) instanceGenerator.getInstance().loadFromJson(array.get(i));
				invoices.add(instance);
			}
			
			
			callback.onSuccess(invoices);

		}
	}
	
	private interface InstanceGenerator<T extends IsJsonSerializable>{
		T getInstance();
	}

}
















