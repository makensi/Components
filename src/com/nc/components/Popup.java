package com.nc.components;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class Popup {

	private static final String TAG = Popup.class.getSimpleName();

	private PopupWindow popupWindow;

	/**
	 * Default constructor
	 * 
	 * @param context
	 */
	public Popup(Context context) {
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
		// TEMPORAL
		Resources resources = context.getResources();
		popupWindow.setBackgroundDrawable(resources
				.getDrawable(android.R.drawable.toast_frame));
		// interceptor
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				boolean result = false;
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
		popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY,
				parentView.getRight(), parentView.getBottom());
	}
}
