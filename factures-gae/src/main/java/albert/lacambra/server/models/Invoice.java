package albert.lacambra.server.models;


import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;


import albert.lacambra.shared.models.IInvoice;

import com.google.gwt.editor.client.Editor.Ignore;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Data
public class Invoice implements IInvoice 
{

    @Id private Long id;
    
    @JsonIgnore @Parent Key<Budget> budget;
    @Ignore Long budgetId;
    private String extra;
    @Index
    private Long date;
    @Index
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


























