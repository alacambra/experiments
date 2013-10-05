package albert.lacambra.client.restservices;

import albert.lacambra.client.restservices.utils.AsyncCallback;
import albert.lacambra.client.restservices.utils.AsyncCallbackNoReturnValue;

import com.google.gwt.json.client.JSONValue;

public interface IRestClient 
{
	public interface EndPointProvider{
		public String getEndPoint();
	}
	
	/**
	 * Server URL where to send the requests.
	 */
	public abstract String getServiceEndPoint();
	
	/**
	 * Performs a GET request.
	 * @param <T>
	 * 
	 * @param String name that will identify the correct resource to load by the API Resource Controller.
	 * @param id Resource's id. 
	 * @param callback Callback used to receive the server Response.
	 */
	public abstract void get(
			final String resourceApiName, 
			final Long id,
			final AsyncCallback<JSONValue> callback
			);
	
	/**
	 * Performs a GET request. No Id is given. 
	 * </br>Therefore the current userId will be used or a list will be returned or both of them together.
	 * @param <T>
	 * 
	 * @param resourceApiName name that will identify the correct resource to load by the API Resource Controller.
	 * @param callback Callback used to receive the server Response.
	 */
	public abstract void get(final String resourceApiName, final AsyncCallback<JSONValue> callback);

	/**
	 * Performs a PUT request. Because an ID is given is expected to make an update. The resultant object will be returned
	 * @param <T>
	 * 
	 * @param String name that will identify the correct resource to load by the API Resource Controller.
	 * @param id resource's Id to be updated
	 * @param resource The resource containing the information to be sent.
	 * @param callback Callback used to receive the server Response.
	 */
	public abstract void put(
			final String resourceApiName, 
			final Integer id,
			final JSONValue payload,
			final AsyncCallback<JSONValue> callback
			);

	/**
	 * Performs a PUT request. No Id is given. Therefore a new resource will be created in server-side. The id of the new resource
	 * will be given back.
	 * @param <T>
	 * 
	 * @param String name that will identify the correct resource to load by the API Resource Controller.
	 * @param resource The resource containing the information to be sent.
	 * @param callback Callback used to receive the server Response.
	 */
	
	public abstract void put(
			final String resourceApiName,
			final JSONValue payload,
			final AsyncCallback<Long> callback
			);
	
//	/**
//	 * Performs a PUT request. The difference is that the callback gives no result back. 
//	 * It is expected to be used only in the creation of contacts and objects connection beacause the generated Id is not important
//	 * @param resourceApiName
//	 * @param resource
//	 * @param callback
//	 */
//	public abstract <T> void put(final String resourceApiName, final T resource, final AsyncCallbackNoReturnValue callback);
	
	/**
	 * 
	 * @param <T>
	 * @param String name that will identify the correct resource to load by the API Resource Controller.
	 * @param id Id of the resource to be deleted.
	 * @param callback Callback used to receive the server Response.
	 * @throws Exception 
	 */
	public abstract <T> void delete(
			final String resourceApiName,
			final Long id, 
			final AsyncCallbackNoReturnValue callback);
	
	public abstract void put(String resourceApiName, JSONValue payload, AsyncCallbackNoReturnValue callback);

	void removeBuffer();

	@Deprecated
	void delete(String resourceApiName, Integer id1, Integer id2, AsyncCallbackNoReturnValue callback);

	void delete(String resourceApiName, AsyncCallbackNoReturnValue callback, Long ...ids) 
			throws IllegalArgumentException;

}
































































