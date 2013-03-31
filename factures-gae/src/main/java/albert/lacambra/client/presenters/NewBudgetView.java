package albert.lacambra.client.presenters;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class NewBudgetView extends ViewImpl implements
		NewBudgetPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, NewBudgetView> {
	}

	@Inject
	public NewBudgetView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
