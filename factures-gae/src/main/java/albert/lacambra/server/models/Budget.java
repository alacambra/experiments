package albert.lacambra.server.models;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Data
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
	@Transient private int used;
	
	public Budget() {}
	
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}
	
	public String getId() {
		return String.valueOf(id);
	}
}
