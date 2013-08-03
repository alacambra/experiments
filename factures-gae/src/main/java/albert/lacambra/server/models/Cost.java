package albert.lacambra.server.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Cost {
	
	@Id protected Long id;
	protected Integer cost;
	protected String concept;
	@Parent protected Key<PersistedBudget> budget;
	protected String info;
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
	public String getInfo() {
		return info;
	}
	public String getTags() {
		return tags;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	public void setBudget(Key<PersistedBudget> budget) {
		this.budget = budget;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
