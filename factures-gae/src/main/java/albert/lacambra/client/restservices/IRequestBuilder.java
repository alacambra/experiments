package albert.lacambra.client.restservices;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;

interface IRequestBuilder {

	/**
	 * Returns the callback previously set by
	 * {@link #setCallback(RequestCallback)}, or <code>null</code> if no callback
	 * was set.
	 */
	public abstract RequestCallback getCallback();

	/**
	 * Returns the value of a header previous set by
	 * {@link #setHeader(String, String)}, or <code>null</code> if no such header
	 * was set.
	 * 
	 * @param header the name of the header
	 */
	public abstract String getHeader(String header);

	/**
	 * Returns the HTTP method specified in the constructor.
	 */
	public abstract String getHTTPMethod();

	/**
	 * Returns the password previously set by {@link #setPassword(String)}, or
	 * <code>null</code> if no password was set.
	 */
	public abstract String getPassword();

	/**
	 * Returns the requestData previously set by {@link #setRequestData(String)},
	 * or <code>null</code> if no requestData was set.
	 */
	public abstract String getRequestData();

	/**
	 * Returns the timeoutMillis previously set by {@link #setTimeoutMillis(int)},
	 * or <code>0</code> if no timeoutMillis was set.
	 */
	public abstract int getTimeoutMillis();

	/**
	 * Returns the HTTP URL specified in the constructor.
	 */
	public abstract String getUrl();

	/**
	 * Returns the user previously set by {@link #setUser(String)}, or
	 * <code>null</code> if no user was set.
	 */
	public abstract String getUser();

	/**
	 * Sends an HTTP request based on the current builder configuration. If no
	 * request headers have been set, the header "Content-Type" will be used with
	 * a value of "text/plain; charset=utf-8". You must call
	 * {@link #setRequestData(String)} and {@link #setCallback(RequestCallback)}
	 * before calling this method.
	 * 
	 * @return a {@link Request} object that can be used to track the request
	 * @throws RequestException if the call fails to initiate
	 * @throws NullPointerException if a request callback has not been set
	 */
	public abstract Request send() throws RequestException;

	/**
	 * Sends an HTTP request based on the current builder configuration with the
	 * specified data and callback. If no request headers have been set, the
	 * header "Content-Type" will be used with a value of "text/plain;
	 * charset=utf-8". This method does not cache <code>requestData</code> or
	 * <code>callback</code>.
	 * 
	 * @param requestData the data to send as part of the request
	 * @param callback the response handler to be notified when the request fails
	 *          or completes
	 * @return a {@link Request} object that can be used to track the request
	 * @throws NullPointerException if <code>callback</code> <code>null</code>
	 */
	public abstract Request sendRequest(String requestData,
			RequestCallback callback) throws RequestException;

	/**
	 * Sets the response handler for this request. This method <b>must</b> be
	 * called before calling {@link #send()}.
	 * 
	 * @param callback the response handler to be notified when the request fails
	 *          or completes
	 * 
	 * @throws NullPointerException if <code>callback</code> is <code>null</code>
	 */
	public abstract void setCallback(RequestCallback callback);

	/**
	 * Sets a request header with the given name and value. If a header with the
	 * specified name has already been set then the new value overwrites the
	 * current value.
	 * 
	 * @param header the name of the header
	 * @param value the value of the header
	 * 
	 * @throws NullPointerException if header or value are null
	 * @throws IllegalArgumentException if header or value are the empty string
	 */
	public abstract void setHeader(String header, String value);

	/**
	 * Sets the password to use in the request URL. This is ignored if there is no
	 * user specified.
	 * 
	 * @param password password to use in the request URL
	 * 
	 * @throws IllegalArgumentException if the password is empty
	 * @throws NullPointerException if the password is null
	 */
	public abstract void setPassword(String password);

	/**
	 * Sets the data to send as part of this request. This method <b>must</b> be
	 * called before calling {@link #send()}.
	 * 
	 * @param requestData the data to send as part of the request
	 */
	public abstract void setRequestData(String requestData);

	/**
	 * Sets the number of milliseconds to wait for a request to complete. Should
	 * the request timeout, the
	 * {@link com.google.gwt.http.client.RequestCallback#onError(Request, Throwable)}
	 * method will be called on the callback instance given to the
	 * {@link ion2s.poolingpeople.restservices.client.google.gwt.http.client.RequestBuilder#sendRequest(String, RequestCallback)}
	 * method. The callback method will receive an instance of the
	 * {@link com.google.gwt.http.client.RequestTimeoutException} class as its
	 * {@link java.lang.Throwable} argument.
	 * 
	 * @param timeoutMillis number of milliseconds to wait before canceling the
	 *          request, a value of zero disables timeouts
	 * 
	 * @throws IllegalArgumentException if the timeout value is negative
	 */
	public abstract void setTimeoutMillis(int timeoutMillis);

	/**
	 * Sets the user name that will be used in the request URL.
	 * 
	 * @param user user name to use
	 * @throws IllegalArgumentException if the user is empty
	 * @throws NullPointerException if the user is null
	 */
	public abstract void setUser(String user);

	void build(Method httpMethod, String url);

}