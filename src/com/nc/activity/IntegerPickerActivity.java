package com.nc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.PopupWindow;

import com.nc.components.R;

public class IntegerPickerActivity extends Activity {
	
	PopupWindow pw;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
    }
    
}