package albert.lacambra.shared.models;

public interface IInvoice {

	public abstract Long getDate();

	public abstract String getExtra();

	public abstract Integer getPrice();

	public abstract void setDate(Long date);

	public abstract void setExtra(String extra);

	public abstract void setPrice(Integer price);

	public abstract Long getId();

	public abstract void setId(Long id);
	
	public abstract Long getBudgetId();
	
	public abstract void setBudgetId(Long id);
	
	

}