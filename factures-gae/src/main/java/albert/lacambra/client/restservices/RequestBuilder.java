package albert.lacambra.client.restservices;


import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.inject.Inject;

class RequestBuilder implements IRequestBuilder {
	
	private com.google.gwt.http.client.RequestBuilder requestBuilder;
	private Credentials credentials;
	
	@Inject
	public RequestBuilder(Credentials credentials){
		this.credentials = credentials;
	}
	
	@Override
	public void build(Method httpMethod, String url){
		requestBuilder = new com.google.gwt.http.client.RequestBuilder(httpMethod, url);
	}
	
	@Override
	public RequestCallback getCallback() {
		return requestBuilder.getCallback();
	}

	@Override
	public String getHeader(String header) {
		return requestBuilder.getHeader(header);
	}

	@Override
	public String getHTTPMethod() {
		return this.requestBuilder.getHTTPMethod();
	}

	@Override
	public String getPassword() {
		return this.requestBuilder.getPassword();
	}

	@Override
	public String getRequestData() {
		return this.requestBuilder.getRequestData();
	}

	@Override
	public int getTimeoutMillis() {
		return this.requestBuilder.getTimeoutMillis();
	}

	@Override
	public String getUrl() {
		return this.requestBuilder.getUrl();
	}

	@Override
	public String getUser() {
		return this.requestBuilder.getUser();
	}

	@Override
	public Request send() throws RequestException {
		return this.requestBuilder.send();
	}

	@Override
	public Request sendRequest(String requestData, RequestCallback callback) throws RequestException {
		
//		String b64 = Base64.encode(credentials.getUsername() + ":" + credentials.getSecret());
//		Log.debug("Using auth: Authorization:" + "Basic " + b64);
//		requestBuilder.setHeader("Authorization", "Basic " + b64);
		
		/*
		 * @todo no-cache used for development. remember to delete
		 */
//		if(!GWT.isProdMode()) {
//			requestBuilder.setHeader("Cache-Control", "no-cache");
//			Log.debug("Forcing no cahe in request " + requestBuilder.getUrl());
//		}	
		
		return this.requestBuilder.sendRequest(requestData, callback);
	}

	@Override
	public void setCallback(RequestCallback callback) {
		this.requestBuilder.setCallback(callback);
		
	}

	@Override
	public void setHeader(String header, String value) {
		this.requestBuilder.setHeader(header, value);
		
	}

	@Override
	public void setPassword(String password) {
		this.requestBuilder.setPassword(password);
		
	}

	@Override
	public void setRequestData(String requestData) {
		 this.requestBuilder.setRequestData(requestData);
		
	}

	@Override
	public void setTimeoutMillis(int timeoutMillis) {
		 this.requestBuilder.setTimeoutMillis(timeoutMillis);
		
	}

	@Override
	public void setUser(String user) {
		 this.requestBuilder.setUser(user);
	}
}
