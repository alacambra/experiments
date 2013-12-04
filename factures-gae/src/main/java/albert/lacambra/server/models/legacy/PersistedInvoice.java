package albert.lacambra.server.models.legacy;


import org.codehaus.jackson.annotate.JsonIgnore;

import albert.lacambra.server.models.Person;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity(name="Invoice")
public class PersistedInvoice{

    @Id private Long id;
    
    @JsonIgnore @Parent Key<PersistedBudgetLegacy> budget;
    private String extra;
    @Index
    private Long date;
    @Index
    private Integer price;
	
    protected PersistedInvoice() { }
    
    public static Key<PersistedInvoice> key(Key<Person> parent, Long id) {
		return Key.create(parent, PersistedInvoice.class, id);
	}
    
	public PersistedInvoice(Key<PersistedBudgetLegacy> budget) { 
		this();
		this.budget = budget;
	}
	
	public PersistedInvoice(Key<PersistedBudgetLegacy> budget, DTOInvoice dtoInvoice) { 
		this();
		this.budget = budget;
		extra = dtoInvoice.getExtra();
		date = dtoInvoice.getDate();
		price = dtoInvoice.getPrice();
	}
	
	public PersistedInvoice(Long id, Key<PersistedBudgetLegacy> budget) {
		this(budget);
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( !(obj instanceof PersistedInvoice) ) {
			return false;
		}
		
		if ( obj == this ) return true;
		
		if ( id == ((PersistedInvoice) obj).getId() ) return true;
		
		return false;
	}

	public Long getId() {
		return id;
	}

	public Key<PersistedBudgetLegacy> getBudget() {
		return budget;
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

	public void setBudget(Key<PersistedBudgetLegacy> budget) {
		this.budget = budget;
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
	
	public DTOInvoice getDTOInvoice() {
		
		return new DTOInvoice()
			.setBudgetId(budget.getId())
			.setDate(date)
			.setExtra(extra)
			.setId(id)
			.setPrice(price);
		
	}

}






















































