package albert.lacambra.client.widgets;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.presenters.InvoiceListPresenter;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;

public class InvoiceCellList extends CellTable<Invoice>{

	private Column<Invoice, String> date;
	private Column<Invoice, String> extra;
	private Column<Invoice, String> price;
	private Column<Invoice, String> budget;
	private Column<Invoice, String> delete;
	private ListHandler<Invoice> sortHandler;

	public InvoiceCellList()
	{
		super(Invoice.KEY_PROVIDER);
		initColumns();
	}

	public void build(InvoiceListPresenter presenter,  List<Invoice> ressource) {
		loadSortHandlers(ressource);
		loadFieldUpdaters(presenter);
	}

	private void initColumns()
	{
		this.sortHandler = new ListHandler<Invoice>(null);
		addColumnSortHandler(sortHandler);

		date = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				
				DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL);
				String date = format.format(new Date(object.getDate()));
				
				return date;
			}
		};

		price = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				return String.valueOf(object.getPrice());
			}
		};

		budget = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				object.getBudgetId();
				return object.getBudget().getName();
			}
		};

		extra = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				return object.getExtra();
			}
		};

		delete = new Column<Invoice, String>(new ClickableTextCell()){

			@Override
			public String getValue(Invoice object) {
				return "delete";
			}
		};
		
		extra.setSortable(true);
		date.setSortable(true);
		budget.setSortable(true);
		price.setSortable(true);

		this.addColumn(date, "date");
		this.addColumn(price, "price");
		this.addColumn(budget, "budget");
		this.addColumn(extra, "extra");
		this.addColumn(delete, "delete");
		this.setPageSize(25);
		this.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	}

	public void loadSortHandlers(List<Invoice> ressources) {

		sortHandler.setList(ressources);
		
		sortHandler.setComparator(date , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		sortHandler.setComparator(price, new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				Float p1 = new Float(o1.getPrice());
				Float p2 = new Float(o2.getPrice());
				return p1.compareTo(p2);
			}
		});

		sortHandler.setComparator(budget , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return 
						o1.getBudget().getName()
						.compareTo(
								o2.getBudget().getName());
			}
		});

		sortHandler.setComparator(extra , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getExtra().compareTo(o2.getExtra());
			}
		});
	}

	private void loadFieldUpdaters(final InvoiceListPresenter presenter) {
		date.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getDate())) {
//					object.setDate(value);
//					presenter.invoiceUpdate(object);
				}
			}
		});

		budget.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
//				if(!value.equals(object.getCategory())) {
//					object.setCategory(value);
//					presenter.invoiceUpdate(object);
//				}
			}
		});

		price.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				Float price = new Float(value);
//				if(price != object.getPrice()) {
//					object.setPrice(price);
//					presenter.invoiceUpdate(object);
//				}
			}
		});

		extra.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getExtra())) {
					object.setExtra(value);
//					presenter.invoiceUpdate(object);
				}
			}
		});
		
		delete.setFieldUpdater(new FieldUpdater<Invoice, String>() {

			@Override
			public void update(int index, Invoice object, String value) {
//				presenter.deleteInvoice(object);
				
			}
		});

	}

}
