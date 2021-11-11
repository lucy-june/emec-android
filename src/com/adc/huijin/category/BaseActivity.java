package com.adc.huijin.category;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
	public static final int LOGIN_CODE = 100;//use for startforresult
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ActivityManager am = ActivityManager.getInstance();
		//am.addActivity(this);
		//ActionBar ab = getActionBar();
		//ab.hide();
	}
}
