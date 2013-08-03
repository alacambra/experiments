package albert.lacambra.client.models;

public class DTOBudget {

	protected int assignation;
	protected Long id;
	protected Long start;
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

}

























































