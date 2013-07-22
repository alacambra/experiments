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

	public Long getId() {
		return id;
	}

	public Key<Budget> getBudget() {
		return budget;
	}

	public Long getBudgetId() {
		return budgetId;
	}

	public String getExtra() {
		return extra;
	}

	public Long getDate() {
		return date;
	}

	public Integer getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBudget(Key<Budget> budget) {
		this.budget = budget;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	

}


























