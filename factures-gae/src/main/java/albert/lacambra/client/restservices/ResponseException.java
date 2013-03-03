package albert.lacambra.client.restservices;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

public class ResponseException {

	public ResponseException(Response response, Request request, Throwable e) {
	}

	public ResponseException(Response response, Request request, String string) {
	}

	public ResponseException(Response object, RequestException e) {
		// TODO Auto-generated constructor stub
	}

	public ResponseException(Response response, String msg, RequestException e) {
		// TODO Auto-generated constructor stub
	}

}
