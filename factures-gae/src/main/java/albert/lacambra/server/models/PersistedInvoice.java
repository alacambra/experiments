package albert.lacambra.server.models;


import org.codehaus.jackson.annotate.JsonIgnore;

import albert.lacambra.client.models.DTOInvoice;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class PersistedInvoice{

    @Id private Long id;
    
    @JsonIgnore @Parent Key<Budget> budget;
//    @Ignore Long budgetId;
    private String extra;
    @Index
    private Long date;
    @Index
    private Integer price;
	
    protected PersistedInvoice() { }
    
    public static Key<PersistedInvoice> key(Key<Person> parent, Long id) {
		return Key.create(parent, PersistedInvoice.class, id);
	}
    
	public PersistedInvoice(Key<Budget> budget) { 
		this();
		this.budget = budget;
	}
	
	public PersistedInvoice(Key<Budget> budget, DTOInvoice dtoInvoice) { 
		this();
		this.budget = budget;
		extra = dtoInvoice.getExtra();
		date = dtoInvoice.getDate();
		price = dtoInvoice.getPrice();
	}
	
	public PersistedInvoice(Long id, Key<Budget> budget) {
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

	public Key<Budget> getBudget() {
		return budget;
	}

//	public Long getBudgetId() {
//		return budgetId;
//	}

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

//	public void setBudgetId(Long budgetId) {
//		this.budgetId = budgetId;
//	}

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






















































