package albert.lacambra.server.models;

@SuppressWarnings("unchecked")
public class CostDTO<T> {

	protected Long id;
	protected Integer cost;
	protected String concept;
	protected Long budgetId;
	protected String tags;
	
	public Long getId() {
		return id;
	}
	public Integer getCost() {
		return cost;
	}
	public String getConcept() {
		return concept;
	}
	public Long getBudgetId() {
		return budgetId;
	}
	public String getTags() {
		return tags;
	}
	public T setId(Long id) {
		this.id = id;
		return (T) this;
	}
	public T setCost(Integer cost) {
		this.cost = cost;
		return (T) this;
	}
	public T setConcept(String concept) {
		this.concept = concept;
		return (T) this;
	}
	public T setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
		return (T) this;
	}
	public T setTags(String tags) {
		this.tags = tags;
		return (T) this;
	}
	
	
}
