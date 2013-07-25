package albert.lacambra.client.models;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Budget extends DTOBudget implements IsJsonSerializable {

	@Override
	public JSONValue serializeToJsonValue() {
		JSONObject v = new JSONObject();

		v.put("assignation", new JSONNumber(assignation));
		v.put("name", new JSONString(name));
		v.put("start", new JSONNumber(start));
		v.put("end", new JSONNumber(end));
		v.put("id", id == null ? null : new JSONNumber(id));

		return v;
	}

	@Override
	public IsJsonSerializable loadFromJson(JSONValue jsonValue) {
		
		JSONObject v = jsonValue.isObject();
		start = Long.parseLong(v.get("start").isNumber().toString());
		end = Long.parseLong(v.get("end").isNumber().toString());
		id = Long.parseLong(v.get("id").isNumber().toString());
		assignation = Integer.parseInt(v.get("assignation").isNumber().toString());
		name = v.get("name").isString().toString();

		return this;
	}

}




























