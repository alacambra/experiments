package albert.lacambra.server.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

public class NewPersistedBudget {
	public static Key<PersistedBudget> key(Key<Person> owner, Long id) {
		return Key.create(owner, PersistedBudget.class, id);
	}

	@Id 
	private Long id;
	
	@JsonIgnore 
	@Parent 
	private Key<Person> owner;
	
	private String name;
	
	@Index
	private Integer year;
	private Integer amount;
	private Integer total;
	
	public Long getId() {
		return id;
	}
	
	public Key<Person> getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwner(Key<Person> owner) {
		this.owner = owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
