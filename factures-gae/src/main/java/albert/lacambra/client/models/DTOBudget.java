package albert.lacambra.client.models;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;


public class DTOBudget implements IsJsonSerializable{

	@Deprecated
	protected int assignation;
	protected Long id;
	@Deprecated
	protected Long start;
	@Deprecated
	protected Long end;
	protected String name;

	/*
	 * New fields. 
	 * Will replace start, end and assignation
	 */
	protected Integer year;
	protected Integer amount;

	public int getAssignation() {
		return assignation;
	}
	public Long getId() {
		return id;
	}
	public long getStart() {
		return start;
	}
	public long getEnd() {
		return end;
	}
	public String getName() {
		return name;
	}
	public DTOBudget setAssignation(int assignation) {
		this.assignation = assignation;
		return this;
	}
	public DTOBudget setId(long id) {
		this.id = id;
		return this;
	}
	public DTOBudget setStart(long start) {
		this.start = start;
		return this;
	}
	public DTOBudget setEnd(long end) {
		this.end = end;
		return this;
	}
	public DTOBudget setName(String name) {
		this.name = name;
		return this;
	}
	
	public Integer getYear() {
		return year;
	}
	public Integer getAmount() {
		return amount;
	}
	public DTOBudget setId(Long id) {
		this.id = id;
		return this;
	}

	public DTOBudget setYear(Integer year) {
		this.year = year;
		return this;
	}
	
	public DTOBudget setAmount(Integer amount) {
		this.amount = amount;
		return this;
	}
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
		// TODO Auto-generated method stub
		return null;
	}

}

























































