package albert.lacambra.client.models;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.json.client.JSONValue;

public class Budget implements IsJso<Budget>, IsJsonSerializable{

	private Long id;
	private BudgetJso jso;
	
	private Budget(BudgetJso jso)
	{
		this.jso = jso;
	}
	
	public Budget()
	{
		jso = BudgetJso.build();
	}
	
	public Budget(String json)
	{
		jso = BudgetJso.build(json);
	}
	
	
	public Long getId() {
		Log.debug(jso.getBudgetId());
		return Long.parseLong(jso.getBudgetId());
	}
	public String getName() {
		return jso.getName();
	}
	public Long getStart() {
		return Long.parseLong(jso.getStart());
	}
	public Long getEnd() {
		return Long.parseLong(jso.getEnd());
	}
	public Integer getAssignation() {
		return jso.getAssignation();
	}
	public void setId(Long id) {
		this.id = id;
		jso.setBudgetId(String.valueOf(id));
	}
	public void setName(String name) {
		jso.setName(name);
	}
	public void setStart(Long start) {
		jso.setStart(String.valueOf(start));
	}
	public void setEnd(Long end) {
		jso.setEnd(String.valueOf(end));
	}
	public void setAssignation(Integer assignation) {
		jso.setAssignation(assignation);
	}
	
	@Override
	public Budget buildJso(String json) {
		jso = BudgetJso.build(json);
		return new Budget(jso);
	}
	
	public JSONValue serializeToJsonValue() {
		return jso.serializeToJsonValue();
	}

	@Override
	public void loadFromJson(JSONValue jsonValue) {
		// TODO Auto-generated method stub
		
	}
}




























