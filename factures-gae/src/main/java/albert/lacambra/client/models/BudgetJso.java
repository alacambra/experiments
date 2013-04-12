package albert.lacambra.client.models;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;


public class BudgetJso extends JavaScriptObject
{
	protected BudgetJso(){}

	public static final ProvidesKey<Budget> KEY_PROVIDER = new ProvidesKey<Budget>() {
		public Object getKey(Budget item) {
			return item == null ? null : item.getId();
		}
	};

	public final native int getAssignation() /*-{ 
	    return this.assignation;
	}-*/;

	public final native void setAssignation(int value) /*-{
	    this.assignation = value; 
	}-*/;

	public final native String getBudgetId() /*-{
	    return this.id;
	}-*/;

	public final native void setBudgetId(String value) /*-{
	    this.id = value; 
	}-*/;

	public final native String getStart() /*-{
		return this.start;
	}-*/;

	public final native void setStart(String value) /*-{
	    this.start = value;
	}-*/;

	public final native String getEnd() /*-{
		return this.end;
	}-*/;

	public final native void setEnd(String value) /*-{
    	this.end = value;
	}-*/;
	
	public final native String getName() /*-{
	    return this.name;
	}-*/;

	public final native void setName(String value) /*-{
		this.name = value; 
	}-*/;


	public static final native BudgetJso build(String json) /*-{
	    return eval('(' + json + ')');
	}-*/;

	public static final native BudgetJso build() /*-{
	    return {};
	}-*/;

	public final String serialize(){
		String json = new JSONObject(this).toString();
		return json;
	}

	public final JSONValue serializeToJsonValue(){
		return new JSONObject(this);
	}
}





























