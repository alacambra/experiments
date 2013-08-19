package albert.lacambra.client.models;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class IndividualCostDTO extends CostDTO<IndividualCostDTO> implements IsJsonSerializable{
	private Long date;
	
	public Long getDate() {
		return date;
	}
	
	public IndividualCostDTO setDate(Long date) {
		this.date = date;
		return this;
	}

	@Override
	public JSONValue serializeToJsonValue() {
		JSONObject v = new JSONObject();

		v.put("concept", new JSONString(concept));
		v.put("cost", new JSONNumber(cost));
		v.put("date", new JSONNumber(date));
		v.put("id", id == null ? null : new JSONNumber(id));
		v.put("budgetId", new JSONNumber(budgetId != null ? budgetId : 0));

		return v;
	}

	@Override
	public IsJsonSerializable loadFromJson(JSONValue jsonValue) {
		// TODO Auto-generated method stub
		return null;
	}
}




























































