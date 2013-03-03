package albert.lacambra.server.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Data
public class Invoice {

    @Id @Getter @Setter private Long id;
    
    @JsonIgnore @Parent Key<Person> owner;
    private String category;
    private String extra;
    private Long date;
    private Integer price;
	
    protected Invoice() { }
    
    public static Key<Invoice> key(Key<Person> parent, Long id) {
		return Key.create(parent, Invoice.class, id);
	}
    
	public Invoice(Key<Person> owner) { 
		this();
		this.owner = owner;
	}
	
	public Invoice(Long id, Key<Person> owner) {
		this(owner);
		this.id = id;
	}
	
	@JsonIgnore
	public Key<Person> getOwner() {
		return owner;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( !(obj instanceof Invoice) ) {
			return false;
		}
		
		if ( obj == this ) return true;
		
		if ( id == ((Invoice) obj).getId() ) return true;
		
		return false;
	}
}


























