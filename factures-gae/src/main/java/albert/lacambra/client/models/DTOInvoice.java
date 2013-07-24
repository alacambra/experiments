package albert.lacambra.client.models;

public class DTOInvoice {

	private Long date;
	private String extra;
	private Integer price;
	private Long id;
	private Long budgetId;
	
	public Long getBudgetId() {
		return budgetId;
	}

	public DTOInvoice setBudgetId(Long bugetId) {
		this.budgetId = bugetId;
		return this;
	}

	public Long getDate() {
		return date;
	}

	public String getExtra() {
		return extra;
	}

	public Integer getPrice() {
		return price;
	}

	public Long getId() {
		return id;
	}

	public DTOInvoice setDate(Long date) {
		this.date = date;
		return this;
	}

	public DTOInvoice setExtra(String extra) {
		this.extra = extra;
		return this;
	}

	public DTOInvoice setPrice(Integer price) {
		this.price = price;
		return this;
	}

	public DTOInvoice setId(Long id) {
		this.id = id;
		return this;
	}

}

