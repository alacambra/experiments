package albert.lacambra.server.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import static albert.lacambra.server.ofy.OfyService.ofy;

@Entity
@SuppressWarnings("unchecked")
public abstract class Cost<T> {
	
	@Id protected Long id;
	@Parent protected Key<PersistedBudget> budget;
	
	@Index private Integer year;
	
	@Index protected Integer cost;
	@Ignore protected Long budgetId; 
	
	protected String concept;
	
	protected String tags;
	
	
	public Integer getCost() {
		return cost;
	}
	public String getConcept() {
		return concept;
	}
	
	public Key<PersistedBudget> getBudget() {
		return budget;
	}
	
	public String getTags() {
		return tags;
	}
	
	public Long getBudgetId() {
		return budgetId;
	}
	
	public T setCost(Integer cost) {
		this.cost = cost;
		return (T) this;
	}
	
	public T setConcept(String concept) {
		this.concept = concept;
		return (T) this;
	}
	
	
	public T setBudget(Key<PersistedBudget> budget) {
		this.budget = budget;
		year = ofy().load().key(this.budget).now().getYear();
		return (T) this;
	}

	public T setTags(String tags) {
		this.tags = tags;
		return (T) this;
	}
	
	public Long getId() {
		return id;
	}
	
	public T setId(Long id) {
		this.id = id;
		return (T) this;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public T setYear(Integer year) {
		this.year = year;
		return (T) this;
	}
	
	public T setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
		return (T) this;
	}
	
	public abstract <R extends CostDTO<R>> R getDTO(); 
}


























































