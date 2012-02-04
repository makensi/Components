/*
	Copyright 2012 Raul de la Hoz Garrido

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package nc.components.activity;

import nc.components.ComponentsConstants;
import nc.components.R;
import nc.components.view.IntegerPickerView;
import nc.components.view.IntegerPickerView.OnValueChangeListener;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Check all integer picker features
 * 
 * @author makensi
 * 
 */
public class IntegerPickerTestActivityTest extends
		ActivityInstrumentationTestCase2<IntegerPickerTestActivity> {

	// properties
	private IntegerPickerTestActivity activity;
	private Instrumentation instrumentation;
	private IntegerPickerView integerPickerView;
	private Button increase, decrease;
	private TextView quantity;

	/**
	 * Constructor
	 */
	public IntegerPickerTestActivityTest() {
		super(IntegerPickerTestActivity.class.getPackage().toString(),
				IntegerPickerTestActivity.class);
	}

	/**
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		instrumentation = getInstrumentation();

		// we check with one of integer components
		integerPickerView = (IntegerPickerView) activity
				.findViewById(R.id.test_nc_components_integer_picker);
		View selector = integerPickerView.getSelectorView();
		increase = (Button) selector
				.findViewById(R.id.nc_components_increase_quantity);
		decrease = (Button) selector
				.findViewById(R.id.nc_components_decrease_quantity);
		quantity = (TextView) selector
				.findViewById(R.id.nc_components_quantity_text);

		setText(ComponentsConstants.ZERO);
	}

	/**
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Verify that several properties are correctly setted
	 */
	public void testProperties() {
		assertTrue("IntegerPicker is not clickable",
				integerPickerView.isClickable());
		assertFalse("Integer picker selector is been shown!",
				integerPickerView.isSelectorVisible());

	}

	/**
	 * Test if integer selector it's open after click
	 */
	public void testSelectorOpen() {

		assertFalse("Integer picker selector is been shown!",
				integerPickerView.isSelectorVisible());

		openSelector();

		assertTrue("Integer picker selector isn't been shown!",
				integerPickerView.isSelectorVisible());
	}

	/**
	 * Check if after open selector both integerPickerView quantity text and
	 * quantity (inside selector popup) are equals
	 */
	public void testQuantityValue() {
		openSelector();

		assertEquals("Integer picker value distinct quantity value",
				integerPickerView.getText(), quantity.getText());
	}

	/**
	 * Check if value in quantity text view (inside popup) increase after
	 * perform a click
	 */
	public void testIncreaseValue() {
		openSelector();

		// increase value to compare
		int expected = Integer.parseInt(quantity.getText().toString());
		expected++;

		clickButton(increase);

		int actual = Integer.parseInt(quantity.getText().toString());
		assertEquals("Value hasn't increased!", expected, actual);

	}

	/**
	 * Check if value in quantity text view (inside popup) decrease after
	 * perform a click
	 */
	public void testDecreaseValue() {
		setText(ComponentsConstants.ONE);

		openSelector();

		// decrease value to compare
		int expected = Integer.parseInt(quantity.getText().toString());
		expected--;

		clickButton(decrease);

		int actual = Integer.parseInt(quantity.getText().toString());
		assertEquals("Value hasn't decreased!", expected, actual);

	}

	/**
	 * Verify that decrease button is unable on 0 value (startup and after
	 * decrease the value);
	 */
	public void decreaseDisableOnZero() {
		openSelector();

		assertFalse("Decrease button is clickable with initialization in 0!",
				decrease.isClickable());

		clickButton(increase);
		assertTrue(
				"Decrease button isn't clickable after value greater than 0",
				decrease.isClickable());

		clickButton(decrease);
		assertFalse("Decrease button is clickable after back to 0!",
				decrease.isClickable());
	}

	/**
	 * Check if value change in every click on both increase/decrease buttons
	 */
	public void testValueChangeEvent() {
		openSelector();

		final boolean[] checks = { false, false };

		integerPickerView.setOnValueChangeListener(new OnValueChangeListener() {

			private int times = 0;

			@Override
			public void onValueChange(View view) {
				checks[times++] = true;
			}
		});

		clickButton(increase);
		clickButton(decrease);

		assertTrue("Increase was not launched event!", checks[0]);
		assertTrue("Decrease was not launched event!", checks[1]);

	}

	/**
	 * Check if value is set in integerPickerView after popup close
	 */
	public void testSetValue() {
		fail("Not implemented yet!");
	}

	/**
	 * Check if event set value is launched in popup close
	 */
	public void testSetValueEvent() {
		fail("Not implemented yet!");
	}

	/**
	 * Open selector
	 */
	private void openSelector() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				integerPickerView.performClick();
			}
		});
		instrumentation.waitForIdleSync();
	}

	/**
	 * Set value for integer picker
	 * 
	 * @param text
	 */
	private void setText(final String text) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				integerPickerView.setText(text);
			}
		});
		instrumentation.waitForIdleSync();
	}

	/**
	 * Perform a click on button passed as parameter
	 * 
	 * @param button
	 */
	private void clickButton(final Button button) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				button.performClick();
			}
		});
		instrumentation.waitForIdleSync();
	}
}
