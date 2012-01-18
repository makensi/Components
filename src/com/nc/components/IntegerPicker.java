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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IntegerPicker extends TextView implements OnClickListener {

	private Popup popup;
	
	public IntegerPicker(Context context) {
		super(context);
		init(context);
	}

	public IntegerPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public IntegerPicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/**
	 * Init method
	 * 
	 * @param context
	 */
	private void init(Context context) {
		popup = new Popup(context);
		this.setOnClickListener(this);
		// inflating
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		View viewRoot = (View) inflater.inflate(R.layout.integer_picker, null);
		// button increase and events
		Button increase = (Button) viewRoot.findViewById(R.id.increase_quantity);
		increase.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Integer value = Integer.parseInt(getText().toString());
				value++;
				setText(value.toString());
			}
		});
		// button increase and events
		Button decrease = (Button) viewRoot.findViewById(R.id.decrease_quantity);
		decrease.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Integer value = Integer.parseInt(getText().toString());
				if(value>0){
					value--;
					setText(value.toString());
				}
			}
		});
		popup.setContentView(viewRoot);
	}

	@Override
	public void onClick(View view) {
		popup.show(view);
	}

}
