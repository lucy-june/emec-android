package com.adc.huijin.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.adc.huijin.AppManager;
import com.adc.huijin.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends TabActivity {

	public static final String TAG = HomeActivity.class.getSimpleName();

	private RadioGroup mTabButtonGroup;
	private TabHost mTabHost;

	public static final String TAB_MAIN = "MAIN_ACTIVITY";
	public static final String TAB_SEARCH = "SEARCH_ACTIVITY";
	public static final String TAB_CATEGORY = "CATEGORY_ACTIVITY";
	public static final String TAB_CART = "CART_ACTIVITY";
	public static final String TAB_PERSONAL = "PERSONAL_ACTIVITY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getInstance().addActivity(this);
		setContentView(R.layout.activity_home);
		
		

		
		findViewById();
		initView();
	}

	private void findViewById() {
		mTabButtonGroup = (RadioGroup) findViewById(R.id.home_radio_button_group);
	}

	private void initView() {

		mTabHost = getTabHost();

		Intent i_main = new Intent(this, IndexActivity.class);
		Intent i_search = new Intent(this, SearchActivity.class);
		Intent i_category = new Intent(this, com.adc.huijin.category.CategoryActivity.class);
		Intent i_cart = new Intent(this, CartActivity.class);
		Intent i_personal = new Intent(this, PersonalActivity.class);

		mTabHost.addTab(mTabHost.newTabSpec(TAB_MAIN).setIndicator(TAB_MAIN)
				.setContent(i_main));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_SEARCH)
				.setIndicator(TAB_SEARCH).setContent(i_search));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_CATEGORY)
				.setIndicator(TAB_CATEGORY).setContent(i_category));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_CART).setIndicator(TAB_CART)
				.setContent(i_cart));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_PERSONAL)
				.setIndicator(TAB_PERSONAL).setContent(i_personal));

		mTabHost.setCurrentTabByTag(TAB_MAIN);

		mTabButtonGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.home_tab_main:
							mTabHost.setCurrentTabByTag(TAB_MAIN);
							break;

						case R.id.home_tab_search:
							mTabHost.setCurrentTabByTag(TAB_SEARCH);
							break;

						case R.id.home_tab_category:
							mTabHost.setCurrentTabByTag(TAB_CATEGORY);
							break;

						case R.id.home_tab_cart:
							mTabHost.setCurrentTabByTag(TAB_CART);
							break;

						case R.id.home_tab_personal:
							mTabHost.setCurrentTabByTag(TAB_PERSONAL);
							break;

						default:
							break;
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_about:

			break;

		case R.id.menu_setting:

			break;

		case R.id.menu_history:

			break;

		case R.id.menu_feedback:

			break;

		case R.id.menu_exit:

			showAlertDialog("????????????", "???????????????????????????", "??????", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					AppManager.getInstance().AppExit(getApplicationContext());
					ImageLoader.getInstance().clearMemoryCache();
				}
			}, "??????", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});

			break;

		case R.id.menu_help:

			break;

		default:
			break;
		}
		return true;
	}

	/** ???????????????????????????????????????????????? **/
	protected void showAlertDialog(String title, String message,
			String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		new AlertDialog.Builder(this).setTitle(title).setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
	}

}
