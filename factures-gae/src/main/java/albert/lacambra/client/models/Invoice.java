package albert.lacambra.client.models;

import albert.lacambra.server.models.DTOInvoice;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class Invoice extends DTOInvoice implements IsJsonSerializable
{
	
	private Budget budget;
	
	public static final ProvidesKey<Invoice> KEY_PROVIDER = new ProvidesKey<Invoice>() {
		public Object getKey(Invoice item) {
			return item == null ? null : item.getId();
		}
	};
	
	@Override
	public JSONValue serializeToJsonValue() {
		
		JSONObject v = new JSONObject();
		
		v.put("date", new JSONNumber(date));
		v.put("extra", new JSONString(extra));
		v.put("price", new JSONNumber(price));
		v.put("id", id == null ? null : new JSONNumber(id));
		v.put("budgetId", new JSONNumber(budgetId));
		
		return v;
	}

	@Override
	public IsJsonSerializable loadFromJson(JSONValue jsonValue) {
		
		JSONObject v = jsonValue.isObject();
		date = Long.parseLong(v.get("date").isNumber().toString());
		price = Integer.parseInt(v.get("price").isNumber().toString());
		id = Long.parseLong(v.get("id").isNumber().toString());
		budgetId = Long.parseLong(v.get("budgetId").isNumber().toString());
		extra = v.get("extra").isString().toString();
		
		return this;
	}
	
	public Budget getBudget() {
		return budget;
	}
	
	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	
}
