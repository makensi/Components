/*
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.nc.components;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.nc.ComponentsConstants;

public class IntegerPicker extends TextView implements OnClickListener {

	private static final String TAG = IntegerPicker.class.getSimpleName();

	private FloatPopup popup;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public IntegerPicker(Context context) {
		super(context);
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#constructor");
		}
		init(context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 */
	public IntegerPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#constructor");
		}
		init(context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public IntegerPicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#constructor");
		}
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
		popup = new FloatPopup(context);
		this.setOnClickListener(this);
		// inflating
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		View viewRoot = (View) inflater.inflate(R.layout.nc_components_integer_picker, null);

		final Button increase = (Button) viewRoot
				.findViewById(R.id.increase_quantity);
		final Button decrease = (Button) viewRoot
				.findViewById(R.id.decrease_quantity);
		final TextView quantity = (TextView) viewRoot
				.findViewById(R.id.quantity_text);

		// set text
		quantity.setText(getText());

		// first disable
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
				}
				// disable
				decrease.setEnabled(!getText().toString().equals(
						ComponentsConstants.ZERO));
			}
		});

		popup.setContentView(viewRoot);
	}

	@Override
	public void onClick(View view) {
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#onClick");
		}
		popup.show(view);
	}

}
