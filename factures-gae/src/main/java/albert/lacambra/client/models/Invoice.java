package albert.lacambra.client.models;



import albert.lacambra.shared.models.IInvoice;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.view.client.ProvidesKey;

public class Invoice implements IsJso<Invoice>, IInvoice
{
	private InvoiceJso jso;
	private Long id;
	
	public static final ProvidesKey<Invoice> KEY_PROVIDER = new ProvidesKey<Invoice>() {
		public Object getKey(Invoice item) {
			return item == null ? null : item.getId();
		}
	};
	
	private Invoice(InvoiceJso jso)
	{
		this.jso = jso;
	}
	
	public Invoice()
	{
		jso = InvoiceJso.buildInvoce();
	}
	
	public Invoice(String json)
	{
		jso = InvoiceJso.buildInvoce(json);
	}

	public String serialize() {
		return jso.serialize();
	}
	
	public JSONValue serializeToJsonValue() {
		return jso.serializeToJsonValue();
	}

	public float getMonthlyPrice() {
		return jso.getPrice();
	}

	public String getCategory() {
		return jso.getBudgetId();
	}

	public void setCategory(String text) {
		jso.setBudgetId(text);
		
	}

	public void setExtra(String text) {
		jso.setExtra(text);
	}

	public void setPrice(Integer price) {
		jso.setPrice(price);
	}

	public Long getDate() {
		return Long.valueOf(jso.getDate());
	}

	public Integer getPrice() {
		return jso.getPrice();
	}

	public String getExtra() {
		return jso.getExtra();
	}

	@Override
	public Invoice buildJso(String json) {
		jso = InvoiceJso.buildInvoce(json);
		return new Invoice(jso);
	}

	@Override
	public void setDate(Long date) {
		jso.setDate(String.valueOf(date));
	}
	

	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;		
	}

	@Override
	public Long getBudgetId() {
		return Long.parseLong(jso.getBudgetId());
	}

	@Override
	public void setBudgetId(Long id) {
		jso.setBudgetId(String.valueOf(id));
	}

}
