package albert.lacambra.server.models;

import lombok.Getter;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@Getter
public class Person {

	public static Key<Person> key(String id) {
		return Key.create(Person.class, id);
	}

	@Id String email;
	private String name;
	
	protected Person() {}
	
	public Person(String email) {
		this.email = email; 
	}
	
	public Person(String email, String name) {
		this(email);
		this.name = name;
	}
}




































