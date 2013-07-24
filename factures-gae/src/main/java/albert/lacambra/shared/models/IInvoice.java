package albert.lacambra.shared.models;

public interface IInvoice {

	Long getDate();

	String getExtra();

	Integer getPrice();

	void setDate(Long date);

	void setExtra(String extra);

	void setPrice(Integer price);

	Long getId();

	void setId(Long id);
	
	Long getBudgetId();
	
	void setBudgetId(Long id);
	
	

}