package albert.lacambra.server.models.legacy;

public class DTOBudget {

	protected int assignation;
	protected Long id;
	protected Long start;
	protected Long end;
	protected String name;
	
	
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
	
	
}
