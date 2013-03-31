package albert.lacambra.server.models;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;


import albert.lacambra.shared.models.IInvoice;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Data
public class Invoice implements IInvoice 
{

    @Id private Long id;
    
    @JsonIgnore @Parent Key<Budget> budget;
    @Transient Long budgetId;
    private String extra;
    private Long date;
    private Integer price;
	
    protected Invoice() { }
    
    public static Key<Invoice> key(Key<Person> parent, Long id) {
		return Key.create(parent, Invoice.class, id);
	}
    
	public Invoice(Key<Budget> budget) { 
		this();
		this.budget = budget;
	}
	
	public Invoice(Long id, Key<Budget> budget) {
		this(budget);
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( !(obj instanceof Invoice) ) {
			return false;
		}
		
		if ( obj == this ) return true;
		
		if ( id == ((IInvoice) obj).getId() ) return true;
		
		return false;
	}
}


























