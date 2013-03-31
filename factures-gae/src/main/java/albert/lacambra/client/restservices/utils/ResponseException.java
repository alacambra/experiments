package albert.lacambra.client.restservices.utils;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;

@SuppressWarnings("serial")
public class ResponseException extends Exception {

	Response response;
	Request request;
	
	public ResponseException(Response response) {
		super();
		this.response = response;
	}
	
	public ResponseException(Response response, Throwable cause) {
		super(cause);
		this.response = response;
	}
	
	public ResponseException(Response response, Request request) {
		super();
		this.response = response;
		this.request = request;
	}
	
	public ResponseException(Response response, Request request, Throwable cause) {
		super(cause);
		this.response = response;
		this.request = request;
	}
	
	public ResponseException(Response response, Request request, String msg) {
		super(msg);
		this.response = response;
		this.request = request;
	}
	
	public ResponseException(Response response, String msg, Throwable cause) {
		super(msg, cause);
		this.response = response;
	}
	
	public Response getResponse() {
		return response;
	}
	
	public Request getRequest() {
		return request;
	}
}
