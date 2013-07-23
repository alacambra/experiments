package albert.lacambra.client.presenters;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainView extends ViewImpl implements MainPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, MainView> {
	}

	@Inject
	public MainView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if ( slot == MainPresenter.TYPE_MainContent) {
			insertOrRemove(contentPanel, slot, content);
		} else {
			super.setInSlot(slot, content);
		}
	}
	
	private void insertOrRemove(Panel panel, Object slot, IsWidget content) {
		panel.clear();
		if ( content != null ) {
			panel.add(content);
		}
	}
	
	@Override
	public Button getNewInvoiceBtn() {
		return newInvoiceBtn;
	}

	@Override
	public Button getNewBudgetBtn() {
		return newBudgetBtn;
	}

	public void setMainPanel(VerticalPanel mainPanel) {
		this.mainPanel = mainPanel;
	}




	@UiField VerticalPanel mainPanel;
	@UiField HorizontalPanel naviPanel;
	@UiField HTMLPanel contentPanel;
	@UiField Button newInvoiceBtn;
	@UiField Button newBudgetBtn;
}
