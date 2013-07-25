package albert.lacambra.client.presenters;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ResumeView extends ViewImpl implements ResumePresenter.MyView {

	private final Widget widget;
	
	@UiField VerticalPanel budgetsNamePanel;
	@UiField VerticalPanel quantitiesPanel;
	@UiField Button refreshBtn;

	public interface Binder extends UiBinder<Widget, ResumeView> {
	}

	@Inject
	public ResumeView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public void addBudget(String bgName, String value) {
		budgetsNamePanel.add(new Label(bgName));
		quantitiesPanel.add(new Label(value));
	}

	@Override
	public void clear() {
		budgetsNamePanel.clear();
		quantitiesPanel.clear();
	}

	@Override
	public Button getRefreshBtn() {
		return refreshBtn;
	}
	
	
}
