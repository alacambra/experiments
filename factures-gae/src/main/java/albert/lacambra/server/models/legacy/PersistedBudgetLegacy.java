package albert.lacambra.server.models.legacy;

import org.codehaus.jackson.annotate.JsonIgnore;

import albert.lacambra.server.models.Person;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity(name="Budget")
public class PersistedBudgetLegacy {

	public static Key<PersistedBudgetLegacy> key(Key<Person> owner, Long id) {
		return Key.create(owner, PersistedBudgetLegacy.class, id);
	}

	@Id private Long id;
	@JsonIgnore @Parent Key<Person> owner;
	private String name;
	@Index
	private Long start;
	@Index
	private Long end;
	private Integer assignation;
	@Ignore private int used;

	public PersistedBudgetLegacy() {}
	
	public PersistedBudgetLegacy(DTOBudget dtoBudget) {
		
		this();
		id = dtoBudget.getId();
		start = dtoBudget.getStart();
		end = dtoBudget.getEnd();
		name = dtoBudget.getName();
		assignation = dtoBudget.getAssignation();
		
	}

	public Key<Person> getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public Long getStart() {
		return start;
	}

	public Long getEnd() {
		return end;
	}

	public Integer getAssignation() {
		return assignation;
	}

	public int getUsed() {
		return used;
	}

	public PersistedBudgetLegacy setId(Long id) {
		this.id = id;
		return this;
	}

	public PersistedBudgetLegacy setOwner(Key<Person> owner) {
		this.owner = owner;
		return this;
	}

	public PersistedBudgetLegacy setName(String name) {
		this.name = name;
		return this;
	}

	public PersistedBudgetLegacy setStart(Long start) {
		this.start = start;
		return this;
	}

	public PersistedBudgetLegacy setEnd(Long end) {
		this.end = end;
		return this;
	}

	public PersistedBudgetLegacy setAssignation(Integer assignation) {
		this.assignation = assignation;
		return this;
	}

	public PersistedBudgetLegacy setUsed(int used) {
		this.used = used;
		return this;
	}

	public Long getId() {
		return id;
	}

	public DTOBudget getDTOBudget() {

		return new DTOBudget()
		.setId(id)
		.setStart(start)
		.setEnd(end)
		.setName(name)
		.setAssignation(assignation);

	}
}





































































