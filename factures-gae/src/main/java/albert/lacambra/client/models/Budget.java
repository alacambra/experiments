package albert.lacambra.client.models;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Budget extends DTOBudget implements IsJsonSerializable {

	@Override
	public JSONValue serializeToJsonValue() {

		JSONObject v = new JSONObject();

		v.put("name", new JSONString(name));
		v.put("year", new JSONNumber(year));
		v.put("amount", new JSONNumber(amount));		
		
		return v;
	}
	
	@Override
	public IsJsonSerializable loadFromJson(JSONValue jsonValue) {
		
		JSONObject v = jsonValue.isObject();
		year = Integer.parseInt(v.get("year").isNumber().toString());
		id = Long.parseLong(v.get("id").isNumber().toString());
		amount = Integer.parseInt(v.get("amount").isNumber().toString());
		name = v.get("name").isString().toString();

		return this;
	}

}




























