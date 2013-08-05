package albert.lacambra.server.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import albert.lacambra.client.models.DTOBudget;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class NewPersistedBudget {

	public static Key<NewPersistedBudget> key(Key<Person> owner, Long id) {
		return Key.create(owner, NewPersistedBudget.class, id);
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

	public NewPersistedBudget(){}
	
	public NewPersistedBudget(DTOBudget dtoBudget) {
		this();
		id = dtoBudget.getId();
		year = dtoBudget.getYear();
		name = dtoBudget.getName();
		amount = dtoBudget.getAmount();

	}

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

	public NewPersistedBudget setId(Long id) {
		this.id = id;
		return this;
	}

	public NewPersistedBudget setOwner(Key<Person> owner) {
		this.owner = owner;
		return this;
	}

	public NewPersistedBudget setName(String name) {
		this.name = name;
		return this;
	}

	public NewPersistedBudget setYear(Integer year) {
		this.year = year;
		return this;
	}

	public NewPersistedBudget setAmount(Integer amount) {
		this.amount = amount;
		return this;
	}

	public NewPersistedBudget setTotal(Integer total) {
		this.total = total;
		return this;
	}

	public DTOBudget getDTOBudget() {

		return new DTOBudget()
		.setId(id)
		.setYear(year)
		.setName(name)
		.setAmount(amount);

	}
}
