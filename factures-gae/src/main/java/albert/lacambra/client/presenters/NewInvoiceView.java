package albert.lacambra.client.presenters;

import java.util.Date;

import albert.lacambra.client.mocks.InputForms;

import com.allen_sauer.gwt.log.client.Log;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class NewInvoiceView extends ViewImpl implements
NewInvoicePresenter.MyView {

	private final Widget widget;
	private String selectedBgId = null;

	public interface Binder extends UiBinder<Widget, NewInvoiceView> {
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	private final Timer t;
	private final String DAY = "DD";
	private final String MONTH = "MM";
	private final String YEAR = "YYYY";


	@UiField HTMLPanel container;
	@UiField HTMLPanel formContainer;
	@UiField Button button;
	@UiField TextBox price;
	@UiField TextBox day;
	@UiField TextBox month;
	@UiField TextBox year;
	@UiField VerticalPanel budgetsPanel;
	@UiField TextBox extra;
	@UiField Label infoLabel;

	@Inject
	public NewInvoiceView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		
		formContainer.setVisible(false);
		InputForms inputForms = new InputForms();
		container.add(inputForms.getWidget());
		
		t = new Timer(){
			@Override
			public void run() {
				infoLabel.setVisible(false);
			}
		};
		attachHandlers();
		restartFields();
	}

	@Override
	public void restartFields() {
		price.setValue("");
		price.setFocus(true);
		day.setValue(DAY);
		month.setValue(MONTH);

		Date d = new Date();
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(PredefinedFormat.YEAR);
		year.setValue(dateTimeFormat.format(d));
		extra.setValue("");
	}

	public void onSuccess(String invoice) 
	{
		restartFields();

		t.cancel();
		t.schedule(5000);
		infoLabel.setText("Success:" + invoice);
		infoLabel.setVisible(true);
	}

	@Override
	public void addPossibleBudget(final String id, final String name) {
		final RadioButton box = new RadioButton("budget", name);
		box.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				
				if ( box.getValue() ) {
					selectedBgId = id;
					Log.info("Selected bg is " + id);
				} else {
					Log.info("Unselecting id " + id);
				}
			}
		});
		budgetsPanel.add(box);
	}

	private void attachHandlers()
	{
		day.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(day.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
					month.setFocus(true);
				}
			}
		});

		month.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(month.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
					year.setFocus(true);
				}
			}
		});

		year.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(year.getValue().length() >= 4 && event.getNativeKeyCode() != 9) {
					//					category.setFocus(true);
				}
			}
		});

		day.addFocusHandler(getFocusHandler(day));
		month.addFocusHandler(getFocusHandler(month));
		year.addFocusHandler(getFocusHandler(year));

		day.addBlurHandler(getBlurHandler(day, DAY));
		month.addBlurHandler(getBlurHandler(month, MONTH));
		year.addBlurHandler(getBlurHandler(year, YEAR));

	}

	private FocusHandler getFocusHandler(final HasText field) {

		FocusHandler ch = new FocusHandler() {


			@Override
			public void onFocus(FocusEvent event) {
				String text = field.getText();
				if(text.equals(DAY) || text.equals(MONTH) || text.equals(YEAR)) {
					field.setText("");
				}
			}
		};

		return ch;
	}

	private BlurHandler getBlurHandler(final HasText field, final String defaultTxt) {
		BlurHandler bh = new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String text = field.getText();
				if(text.equals("")){
					field.setText(defaultTxt);
				}
			}
		};

		return bh;
	}

	@Override
	public Button getButton() {
		return button;
	}

	@Override
	public TextBox getPrice() {
		return price;
	}

	@Override
	public TextBox getDay() {
		return day;
	}

	@Override
	public TextBox getMonth() {
		return month;
	}

	@Override
	public TextBox getYear() {
		return year;
	}

	@Override
	public VerticalPanel getBudgetsPanel() {
		return budgetsPanel;
	}

	@Override
	public TextBox getExtra() {
		return extra;
	}

	@Override
	public Label getInfoLabel() {
		return infoLabel;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public void setPrice(TextBox price) {
		this.price = price;
	}

	public void setDay(TextBox day) {
		this.day = day;
	}

	public void setMonth(TextBox month) {
		this.month = month;
	}

	public void setYear(TextBox year) {
		this.year = year;
	}

	public void setExtra(TextBox extra) {
		this.extra = extra;
	}

	public void setInfoLabel(Label infoLabel) {
		this.infoLabel = infoLabel;
	}

	@Override
	public String getSelectedBudgetId() {
		return selectedBgId;
	}
}
