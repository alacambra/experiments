package albert.lacambra.client.injection;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class InvoiceListView extends ViewImpl implements
		InvoiceListPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, InvoiceListView> {
	}

	@Inject
	public InvoiceListView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
