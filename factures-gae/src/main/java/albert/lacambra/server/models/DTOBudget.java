package albert.lacambra.server.models;


public class DTOBudget{

	protected Long id;
	protected String name;
	protected Integer year;
	protected Integer amount;

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public DTOBudget setId(long id) {
		this.id = id;
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

























































