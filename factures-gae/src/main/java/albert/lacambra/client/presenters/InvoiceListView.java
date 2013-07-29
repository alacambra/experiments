package albert.lacambra.client.presenters;

import albert.lacambra.client.models.Invoice;
import albert.lacambra.client.widgets.InvoiceCellList;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;
import com.google.inject.Inject;

public class InvoiceListView extends ViewImpl implements
		InvoiceListPresenter.MyView {

	private final Widget widget;
	InvoiceCellList cellList;
	@UiField HTMLPanel container;	

	public interface Binder extends UiBinder<Widget, InvoiceListView> {
	}

	@Inject
	public InvoiceListView(final Binder binder, InvoiceCellList cellList) {
		widget = binder.createAndBindUi(this);
//		cellList = new InvoiceCellList();
		this.cellList = cellList;
		container.add(cellList);
		SimplePager pager = new SimplePager();
		pager.setDisplay(cellList);
		container.add(pager);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public HasData<Invoice> getCellTable() {
		return cellList;
	}
	
	@Override
	public InvoiceCellList getCellList() {
		return cellList;
	}
}
