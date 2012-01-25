/*
	Copyright 2012 Raœl de la Hoz Garrido

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
package nc.components;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class FloatPopup {

	private static final String TAG = FloatPopup.class.getSimpleName();

	private PopupWindow popupWindow;

	/**
	 * Default constructor
	 * 
	 * @param context
	 */
	public FloatPopup(Context context) {
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#constructor");
		}
		popupWindow = new PopupWindow(context);
		// properties
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// interceptor
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				boolean result = false;
				// click outside
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow.dismiss();
					result = true;
				}
				return result;
			}
		});

	}

	/**
	 * Set the content of popup
	 * 
	 * @param contentView
	 */
	public void setContentView(View contentView) {
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#setContentView");
		}
		popupWindow.setContentView(contentView);
	}

	/**
	 * Shows popup
	 * 
	 * @param parentView
	 */
	public void show(View parentView) {
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, "#show");
		}

		int positions[] = { 0, 0 };
		parentView.getLocationOnScreen(positions);
		int x = positions[0];
		int y = positions[1];

		popupWindow.showAtLocation(
				parentView.getRootView().findViewById(android.R.id.content),
				Gravity.NO_GRAVITY, x, y);
	} 
}
