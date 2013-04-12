package albert.lacambra.client.models;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;


public class InvoiceJso extends JavaScriptObject
{
	protected InvoiceJso(){}
	
	public static final ProvidesKey<Invoice> KEY_PROVIDER = new ProvidesKey<Invoice>() {
		public Object getKey(Invoice item) {
			return item == null ? null : item.getId();
		}
	};
	    
	public final native int getPrice() /*-{ 
	    return this.price;
	}-*/;

	public final native void setPrice(int value) /*-{
	    this.price = value; 
	}-*/;

	public final native String getBudgetId() /*-{
	    return this.budgetId;
	}-*/;
	
	public final native void setBudgetId(String value) /*-{
	    this.budgetId = value; 
	}-*/;

	public final native String getExtra() /*-{
	    return this.extra;
	}-*/;

	public final native void setExtra(String value) /*-{
		this.extra = value; 
	}-*/;

	public final native String getDate() /*-{
		return this.date;
	}-*/;

	public final native void setDate(String value) /*-{
	    this.date = value;
	}-*/;

	public static final native InvoiceJso build(String json) /*-{
	    return eval('(' + json + ')');
	}-*/;

	public static final native InvoiceJso build() /*-{
	    return {};
	}-*/;

	public final String serialize(){
		String json = new JSONObject(this).toString();
		return json;
	}
	
	public final JSONValue serializeToJsonValue(){
		return new JSONObject(this);
	}
	
	public final String print()
	{
		return getDate() + ":" + getPrice()  + ":" + getBudgetId() + ":" +getExtra();
	}
}





























