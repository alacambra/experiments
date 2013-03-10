package albert.lacambra.client.presenters;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class DefaultView extends ViewImpl implements DefaultPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, DefaultView> {
	}

	@Inject
	public DefaultView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
