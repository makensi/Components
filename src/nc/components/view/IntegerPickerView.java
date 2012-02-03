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
package nc.components.view;

import nc.components.ComponentsConstants;
import nc.components.R;
import nc.components.widget.FloatPopupWindow;
import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

/**
 * Integer Picker View
 * 
 * Android Text view with edition features. It create a widget Popup Window with
 * two buttons which allows increase or decrease text integer value.
 * 
 * @author makensi
 * 
 */
public class IntegerPickerView extends TextView implements OnClickListener {

	// contstants
	private static final String TAG = IntegerPickerView.class.getSimpleName();

	// properties
	private FloatPopupWindow popup;
	private OnValueChangeListener onChangeValueListener;
	private OnSetValueListener onSetValueListener;
	private Button increase;
	private Button decrease;
	private TextView quantity;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public IntegerPickerView(Context context) {
		super(context);
		init(context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 *            component attributes
	 */
	public IntegerPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 *            component attributes
	 * @param defStyle
	 *            ignored by andoid API
	 */
	public IntegerPickerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/**
	 * Init method
	 * 
	 * @param context
	 */
	private void init(Context context) {
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#init");
		}

		// register click event
		this.setOnClickListener(this);

		// we create popup window
		popup = new FloatPopupWindow(context);
		// set event dismis in popup window
		popup.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				if (onSetValueListener != null) {
					onSetValueListener.onSetValue(IntegerPickerView.this);
				}
			}
		});

		// inflating view & and views
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		View viewRoot = (View) inflater.inflate(
				R.layout.nc_components_integer_picker, null);
		increase = (Button) viewRoot.findViewById(R.id.increase_quantity);
		decrease = (Button) viewRoot.findViewById(R.id.decrease_quantity);
		quantity = (TextView) viewRoot.findViewById(R.id.quantity_text);

		// set disable increase button if start value is 0
		decrease.setEnabled(!getText().toString().equals(
				ComponentsConstants.ZERO));

		increase.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Log.isLoggable(TAG, Log.DEBUG)) {
					Log.d(TAG, "increase#setOnClickListener#onclick");
				}

				Integer value = Integer.parseInt(getText().toString());
				value++;
				setText(value.toString());
				quantity.setText(value.toString());
				// change value event
				if (onChangeValueListener != null) {
					onChangeValueListener.onValueChange(IntegerPickerView.this);
				}
				// enable
				decrease.setEnabled(true);
			}
		});

		decrease.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Log.isLoggable(TAG, Log.DEBUG)) {
					Log.d(TAG, "decrease#setOnClickListener#onclick");
				}

				Integer value = Integer.parseInt(getText().toString());
				if (value > 0) {
					value--;
					setText(value.toString());
					quantity.setText(value.toString());
					// change value event
					if (onChangeValueListener != null) {
						onChangeValueListener
								.onValueChange(IntegerPickerView.this);
					}
				}
				// disable
				decrease.setEnabled(!getText().toString().equals(
						ComponentsConstants.ZERO));
			}
		});

		// set popup content view
		popup.setContentView(viewRoot);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#onClick");
		}
		// set text
		quantity.setText(getText());
		popup.show(view);
	}

	/**
	 * Allows set event launched after every change of value
	 * 
	 * @param onChangeValueListener
	 */
	public void setOnValueChangeListener(
			OnValueChangeListener onChangeValueListener) {
		this.onChangeValueListener = onChangeValueListener;
	}

	/**
	 * Set event launched after close popup
	 * 
	 * @param onSetValueListener
	 */
	public void setOnSetValueListener(OnSetValueListener onSetValueListener) {
		this.onSetValueListener = onSetValueListener;
	}

	/**
	 * Interface to represent value change event
	 * 
	 * @author makensi
	 * 
	 */
	public interface OnValueChangeListener {

		/**
		 * Event executed after click in increase or decrease buttons
		 * 
		 * @param view
		 *            current IntegerPickerView reference
		 */
		public void onValueChange(View view);

	}

	/**
	 * Interface to represent the event after popup closed
	 * 
	 * @author makensi
	 * 
	 */
	public interface OnSetValueListener {

		/**
		 * Event executed after popup closed
		 * 
		 * @param view
		 *            current IntegerPickerView reference
		 */
		public void onSetValue(View view);

	}

}
