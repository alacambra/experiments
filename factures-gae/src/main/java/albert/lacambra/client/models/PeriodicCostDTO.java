package albert.lacambra.client.models;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import albert.lacambra.shared.models.PeriodStep;

public class PeriodicCostDTO extends CostDTO<PeriodicCostDTO> implements IsJsonSerializable{

	private PeriodStep periodStep;
	private Long start;
	private Long end;
	private Boolean isFixedCost;
	private Integer foreseenCost;

	public PeriodStep getPeriodStep() {
		return periodStep;
	}
	public Long getStart() {
		return start;
	}
	public Long getEnd() {
		return end;
	}
	public Boolean getIsFixedCost() {
		return isFixedCost;
	}
	public Integer getForeseenCost() {
		return foreseenCost;
	}
	public PeriodicCostDTO setPeriodStep(PeriodStep periodStep) {
		this.periodStep = periodStep;
		return this;
	}
	public PeriodicCostDTO setStart(Long start) {
		this.start = start;
		return this;
	}
	public PeriodicCostDTO setEnd(Long end) {
		this.end = end;
		return this;
	}
	public PeriodicCostDTO setIsFixedCost(Boolean isFixedCost) {
		this.isFixedCost = isFixedCost;
		return this;
	}
	public PeriodicCostDTO setForeseenCost(Integer foreseenCost) {
		this.foreseenCost = foreseenCost;
		return this;
	}

	@Override
	public JSONValue serializeToJsonValue() {

		JSONObject v = new JSONObject();

		v.put("concept", new JSONString(concept));
		v.put("cost", new JSONNumber(cost));
		v.put("start", new JSONNumber(start));
		v.put("end", new JSONNumber(end));
		v.put("periodStep", new JSONString(periodStep.name()));
		v.put("id", id == null ? null : new JSONNumber(id));
		v.put("budgetId", new JSONNumber(budgetId != null ? budgetId : 0));
		v.put("isFixedCost", new JSONString(isFixedCost.toString()));

		return v;
	}

	@Override
	public IsJsonSerializable loadFromJson(JSONValue jsonValue) {
		return null;
	}
}






















































