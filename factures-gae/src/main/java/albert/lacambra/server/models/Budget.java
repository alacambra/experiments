package albert.lacambra.server.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Budget {

	public static Key<Budget> key(Key<Person> owner, Long id) {
		return Key.create(owner, Budget.class, id);
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
	
	public Budget() {}
	
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwner(Key<Person> owner) {
		this.owner = owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public void setAssignation(Integer assignation) {
		this.assignation = assignation;
	}

	public void setUsed(int used) {
		this.used = used;
	}
	
	public Long getId() {
		return id;
	}
}
