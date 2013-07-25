package albert.lacambra.client.presenters;

import java.util.Date;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class NewBudgetView extends ViewImpl implements NewBudgetPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, NewBudgetView> {
	}

	@Inject
	public NewBudgetView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		restartFields();
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public void restartFields() {
		assignation.setValue("");
		assignation.setFocus(true);
		Date d = new Date();
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(PredefinedFormat.YEAR);
		year.setValue(dateTimeFormat.format(d));
		name.setValue("");
	}
	
	@Override
	public void setInSlot(Object slot, IsWidget content) {
		this.tmpPanel.add(content);
	}
	
	@Override
	public HasText getName() {
		return name;
	}
	
	@Override
	public HasText getAssignation() {
		return assignation;
	}
	
	@Override
	public HasText getYear() {
		return year;
	}
	
	@Override
	public void setName(String name) {
		this.name.setText(name);
	}
	
	@Override
	public void setAssignation(String assignation) {
		this.assignation.setText(assignation);
	}
	
	@Override
	public void setYear(String year) {
		this.year.setText(year);
	}
	
	@Override
	public HasClickHandlers getSubmit() {
		return submit;
	}

	@UiField TextBox name;
	@UiField TextBox assignation;
	@UiField TextBox year;
	@UiField Button submit;
	@UiField VerticalPanel tmpPanel;
}









































