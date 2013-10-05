package albert.lacambra.client.models;

import albert.lacambra.server.models.PeriodicCostDTO;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class PeriodicCost extends PeriodicCostDTO  implements IsJsonSerializable{
	
	@Override
	public JSONValue serializeToJsonValue() {

		JSONObject v = new JSONObject();

		v.put("concept", new JSONString(concept));
		v.put("cost", new JSONNumber(cost));
		v.put("start", new JSONNumber(start));
		v.put("end", new JSONNumber(end));
		v.put("periodStep", new JSONString(periodStep.name()));
		v.put("id", id == null ? null : new JSONNumber(id));
		v.put("budgetId", new JSONNumber(budgetId != null ? budgetId : 1));
		v.put("isFixedCost", new JSONString(isFixedCost.toString()));

		return v;
	}

	@Override
	public IsJsonSerializable loadFromJson(JSONValue jsonValue) {
		return null;
	}
}
