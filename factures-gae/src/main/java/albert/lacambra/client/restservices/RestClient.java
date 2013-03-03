package albert.lacambra.client.restservices;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

import com.google.inject.Inject;

class RestClient implements IRestClient{

	private static final boolean USE_CACHING = false;
	
	private final String serviceEndPoint;
	private final HashSet<Integer> validCodes;
	private final IRequestBuilder requestBuilder;
	private final HashMap<String, JSONValue> buffer = new HashMap<String, JSONValue>();

	@Inject
	public RestClient(
			final IRequestBuilder requestBuilder,
//			final EventBus eventBus, 
			final IRestClient.EndPointProvider endPointProvider
			)
	{
		
		this.serviceEndPoint = endPointProvider.getEndPoint();
		
		Log.debug( "Using endpoint: " + serviceEndPoint );
		
		this.validCodes = new HashSet<Integer>();
		this.validCodes.add(Response.SC_OK);
		this.validCodes.add(Response.SC_NOT_MODIFIED);
		this.validCodes.add(Response.SC_CREATED);
		this.validCodes.add(Response.SC_NO_CONTENT);
//		this.eventBus = eventBus;
		this.requestBuilder = requestBuilder;
		
	}
	
	@Override
	public void removeBuffer() {
		buffer.clear();
	}

	@Override
	public String getServiceEndPoint() {
		return serviceEndPoint;
	}

	@Override
	public void get(
			final String resourceApiName,
			final Integer id,
			final AsyncCallback<JSONValue> asyncCallback
			) {

		String resourceUrl =  "";

		if(id == -1) {
			
			resourceUrl = resourceApiName;
			
		} else {
			
			resourceUrl = resourceApiName + "/" + id;
			
		}

		final String finalResourceUrl = resourceUrl;

		if (useCachedRequest(resourceUrl)) {			

			asyncCallback.onSuccess(buffer.get(resourceUrl));
			return;

		}

		requestBuilder.build(RequestBuilder.GET, serviceEndPoint + resourceUrl);

		try {

			requestBuilder.sendRequest(null , new RequestCallback() {

				public void onError(Request request, Throwable e) {

					Log.debug(finalResourceUrl + ": Can't load JSON");
					asyncCallback.onFailure(new ResponseException(null, request, e));
					
				}

				public void onResponseReceived(Request request, Response response) {

					if (RestClient.this.validCodes.contains(response.getStatusCode()))  {
						
						JSONValue value = null;
						
						try {
							
							String responseText = response.getText();
							value = JSONParser.parseStrict(responseText);

						} catch(NullPointerException e) {
							
							asyncCallback.onFailure(new ResponseException(response, request, e));
							return;
							
						} catch(IllegalArgumentException e) {
							
							asyncCallback.onFailure(new ResponseException(response, request, e));
							return;
							
						}
						
						buffer.put(finalResourceUrl, value);
						asyncCallback.onSuccess(value);

					} else if (Response.SC_UNAUTHORIZED == response.getStatusCode()) {
						
//						eventBus.fireEvent(new UnauthorizedEvent());
						asyncCallback.onFailure(new ResponseException(response, request, "Forbiden"));
						
					} else {

						String msg = "GET '" + resourceApiName+ "/" + (id == -1 ? "" : id) 
								+ "': says " + response.getStatusCode();
						
						Log.debug( msg );
						asyncCallback.onFailure(new ResponseException(response, request, msg));
					}
				}
			});
		}
		catch (RequestException e) {

			String msg = "RestService/GET '" + resourceApiName+ "/" + id == null ? "" : id + "': Build Request Error";

			Log.debug(msg);
			asyncCallback.onFailure(new ResponseException(null, msg, e));

		}
	}	

	@Override
	public void get(final String resourceApiName,final AsyncCallback<JSONValue> callback) {
		this.get(resourceApiName, -1, callback);
	}

	@Override
	public void put(
			final String resourceApiName, 
			final Integer id, 
			final JSONValue payload, 
			final AsyncCallback<JSONValue> callback
			) {

		requestBuilder.build(RequestBuilder.PUT, serviceEndPoint + resourceApiName + "/"  + id);
		requestBuilder.setHeader("Content-Type", "application/json");
		
		try {

			requestBuilder.sendRequest(payload.toString(), new RequestCallback() {
				
				public void onError(Request request, Throwable e) {
					
					String msg = "PUT '" + resourceApiName + "/" + id + "': Can't load JSON";
					Log.error(msg, e);
					callback.onFailure(new ResponseException(null, request, e));
					
				}
				
				public void onResponseReceived(Request request, Response response) {

					if (RestClient.this.validCodes.contains(response.getStatusCode())) {

						try {

							String responseText = response.getText();
							JSONValue value = JSONParser.parseStrict(responseText);
							callback.onSuccess(value);
							return;
							
						} catch(NullPointerException e) {

							String msg = "PUT '" + resourceApiName + "/" + id + "': Updated paylod not received";
							Log.debug(msg, e);
							callback.onFailure(new ResponseException(null, request, e));
							return;

						} catch(IllegalArgumentException e) {
							
							String msg = "PUT '" + resourceApiName + "/" + id + "': Upload is empty";
							Log.debug(msg, e);
							callback.onFailure(new ResponseException(null, request, e));
							return;
							
						}

					} else {
						
						String msg = "PUT '" + resourceApiName + "/" + id + "': Can't load JSON";
						Throwable e = new Throwable("Resource not saved. Server saids " + response.getStatusCode()); 
						Log.debug(msg, e);
						callback.onFailure(new ResponseException(response, request, e));
						
					}
				}
			});
		} catch (RequestException e)  {
			
			String msg = "GET '" + resourceApiName + "/" + id + "': Build Request Error";
			Log.error(msg, e);
			callback.onFailure(new ResponseException(null, e));
		}
	}

	@Override
	public void put(final String resourceApiName, final JSONValue payload, final AsyncCallback<Integer> callback) {
			
		requestBuilder.build(RequestBuilder.PUT, serviceEndPoint + resourceApiName + "/");
		requestBuilder.setHeader("Content-Type", "application/json");
		
		try {

			final Long num = new Date().getTime();
			
			Log.info("Sending request (" + num + "): "+ RequestBuilder.PUT + " " +  serviceEndPoint + resourceApiName + "/" + 
					" payload: " + payload);
			
			requestBuilder.sendRequest(payload.toString(), new RequestCallback() {
				
				public void onError(Request request, Throwable e) {
					
					String msg = "PUT '" + resourceApiName + "': Can't load JSON";
					Log.error(msg, e);
					callback.onFailure(new ResponseException(null, request, e));
					
					
				}
				
				public void onResponseReceived(Request request, Response response) {

					if (RestClient.this.validCodes.contains(response.getStatusCode())) {

						try {
							
							Integer insertedId = Integer.parseInt(response.getHeader("X-InsertedId"));
							Log.info("Response received (" + num + "):" + response.getHeadersAsString());
							callback.onSuccess(insertedId);

						} catch(NullPointerException e) {

							String msg = "PUT '" + resourceApiName + "/': Updated paylaod not received";
							Log.debug(msg, e);
							callback.onFailure(new ResponseException(response, request, e));
							return;

						} 
						catch(NumberFormatException e) {
							
							String msg = "PUT '" + resourceApiName + "/': Invalid value received";
							Log.debug(msg, e);
							callback.onFailure(new ResponseException(response, request, e));
							return;

						} catch(IllegalArgumentException e) {
							
							String msg = "PUT '" + resourceApiName + "/': InsertedId is empty";
							Log.debug(msg, e);
							callback.onFailure(new ResponseException(response, request, e));
							return;
							
						}

					} else {
						
						String msg = "PUT '" + resourceApiName + "/': Can't load JSON";
						Throwable e = new Throwable("Resource not saved. Server saids " + response.getStatusCode()); 
						Log.debug(msg, e);
						callback.onFailure(new ResponseException(response, request, e));
						
					}
				}
			});
			
		} catch (RequestException e) {
			
			Log.error("GET '" + resourceApiName + "/': Build Request Error", e);
			callback.onFailure(new ResponseException(null, e));
			
		}
	}
	
	@Override
	public void put(final String resourceApiName, final JSONValue payload, final AsyncCallbackNoReturnValue callback) {
			
		requestBuilder.build(RequestBuilder.PUT, serviceEndPoint + resourceApiName + "/");
		requestBuilder.setHeader("Content-Type", "application/json");
		
		try {

			requestBuilder.sendRequest(payload.toString(), new RequestCallback() {
				
				public void onError(Request request, Throwable e) {
					
					String msg = "PUT '" + resourceApiName + "': Can't load JSON";
					Log.error(msg, e);
					callback.onFailure(new ResponseException(null, request, e));
					
				}
				
				public void onResponseReceived(Request request, Response response) {

					if (RestClient.this.validCodes.contains(response.getStatusCode())) {
						
						callback.onSuccess();

					} else {
						
						String msg = "PUT '" + resourceApiName + "/': Can't load JSON";
						Throwable e = new Throwable("Resource not saved. Server sais " + response.getStatusCode()); 
						Log.debug(msg, e);
						callback.onFailure(new ResponseException(response, request, e));
						
					}
				}
			});
			
		} catch (RequestException e) {
			
			Log.error("PUT'" + resourceApiName + "/': Build Request Error", e);
			callback.onFailure(new ResponseException(null, e));
			
		}
	}

	@Override
	public void delete( final String resourceApiName, final Integer id, final AsyncCallbackNoReturnValue callback) throws Exception {
		
		if(id == null) {
			throw new Exception("Id cannot be null");
		}
		
		requestBuilder.build(RequestBuilder.DELETE, serviceEndPoint + resourceApiName + "/" + id);
		
		try 
		{
			requestBuilder.sendRequest(null , new RequestCallback() {
				
				public void onError(Request request, Throwable e) {
					
					String msg = "GET " + serviceEndPoint + resourceApiName + "/" + id + ": Can't load JSON";
					Log.debug(msg, e);
					callback.onFailure(new ResponseException(null, request, e));
					
				}
				
				public void onResponseReceived(Request request, Response response) {
					
					if (RestClient.this.validCodes.contains(response.getStatusCode())) {
						
						callback.onSuccess();
						
					} else {
						
						String msg = "";
						Throwable e = new Throwable("Resource not removed. Server saids " + response.getStatusCode()); 
						Log.debug(msg, e);
						callback.onFailure(new ResponseException(response, request, e));
						
					}
				}
			});
		} 
		catch (RequestException e) {
			
			String msg = "GET '" + resourceApiName + "/" + id + "': Build Request Error";
			Log.debug(msg, e);
			callback.onFailure(new ResponseException(null, e));
			
		}
	}
	
	@Override
	public void delete( 
			final String resourceApiName, final Integer id1, final Integer id2, final AsyncCallbackNoReturnValue callback) 
					throws IllegalArgumentException {
		
		if(id1 == null || id2 == null ) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		
		requestBuilder.build(RequestBuilder.DELETE, serviceEndPoint + resourceApiName + "/" + id1 + "/" + id2 ) ;
		
		try 
		{
			requestBuilder.sendRequest(null , new RequestCallback() {
				
				public void onError(Request request, Throwable e) {
					
					String msg = "GET " + serviceEndPoint + resourceApiName + "/" + id1 + "/" + id2 + ": Can't load JSON";
					Log.debug(msg, e);
					callback.onFailure(new ResponseException(null, request, e));
					
				}
				
				public void onResponseReceived(Request request, Response response) {
					
					if (RestClient.this.validCodes.contains(response.getStatusCode())) {
						
						callback.onSuccess();
						
					} else {
						
						String msg = "";
						Throwable e = new Throwable("Resource not removed. Server saids " + response.getStatusCode()); 
						Log.debug(msg, e);
						callback.onFailure(new ResponseException(response, request, e));
						
					}
				}
			});
		} 
		catch (RequestException e) {
			
			String msg = "GET '" + resourceApiName + "/" + "/" + id1 + "/" + id2 + "': Build Request Error";
			Log.debug(msg, e);
			callback.onFailure(new ResponseException(null, e));
			
		}
	}
	
	@Override
	public void delete( 
			final String resourceApiName, final AsyncCallbackNoReturnValue callback, final Integer ...ids) 
					throws IllegalArgumentException {
		
		if(ids == null || ids.length == 0) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		
		String idsStrTmp = "";
		
		for (int i = 0; i < ids.length - 1; i++) {
			idsStrTmp = idsStrTmp + ids [i] + "/";
		}
		
		idsStrTmp =  idsStrTmp + ids[ids.length - 1];
		
		final String idsStr = idsStrTmp;
		
		requestBuilder.build(RequestBuilder.DELETE, serviceEndPoint + resourceApiName + "/" + idsStr );
		
		try 
		{
			requestBuilder.sendRequest(null , new RequestCallback() {
				
				public void onError(Request request, Throwable e) {
					
					String msg = "GET " + serviceEndPoint + resourceApiName + "/" + idsStr + ": Can't load JSON";
					Log.debug(msg, e);
					callback.onFailure(new ResponseException(null, request, e));
					
				}
				
				public void onResponseReceived(Request request, Response response) {
					
					if (RestClient.this.validCodes.contains(response.getStatusCode())) {
						
						callback.onSuccess();
						
					} else {
						
						String msg = "";
						Throwable e = new Throwable("Resource not removed. Server saids " + response.getStatusCode()); 
						Log.debug(msg, e);
						callback.onFailure(new ResponseException(response, request, e));
						
					}
				}
			});
		} 
		catch (RequestException e) {
			
			String msg = "DEL '" + resourceApiName + "/" + idsStr + "': Build Request Error";
			Log.debug(msg, e);
			callback.onFailure(new ResponseException(null, e));
			
		}
	}

	protected void responseIsValid(int code){

	}

	@SuppressWarnings("unused")
	private boolean useCachedRequest(String request){
		return USE_CACHING && buffer.containsKey(request); 
	}
}



































