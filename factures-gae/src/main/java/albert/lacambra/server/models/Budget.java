package albert.lacambra.server.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Data
public class Budget {

	public static Key<Budget> key(Key<Person> owner, Long id) {
		return Key.create(owner, Budget.class, id);
	}
	
	@Id private Long id;
	@JsonIgnore @Parent Key<Person> owner;
	private String name;
	private Long start;
	private Long end;
	private Integer assignation;
	
}
