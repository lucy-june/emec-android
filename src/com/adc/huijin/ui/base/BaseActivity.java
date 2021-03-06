package com.adc.huijin.ui.base;

import java.util.Locale;
import java.util.concurrent.Callable;
import com.adc.huijin.AppConfig;
import com.adc.huijin.AppManager;
import com.adc.huijin.R;
import com.adc.huijin.image.ImageLoaderConfig;
import com.adc.huijin.task.AsyncCallable;
import com.adc.huijin.task.Callback;
import com.adc.huijin.task.EMobileTask;
import com.adc.huijin.task.ProgressCallable;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

	public static final String TAG = BaseActivity.class.getSimpleName();

	protected Handler mHandler = null;
	protected InputMethodManager imm;
	private TelephonyManager tManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getInstance().addActivity(this);
		
		if (!ImageLoader.getInstance().isInited()) {
			ImageLoaderConfig.initImageLoader(this, AppConfig.BASE_IMAGE_CACHE);
		}
		
		tManager=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	/**
	 * ????????????id
	 */
	protected abstract void findViewById();

	/**
	 * ???????????????
	 */
	protected abstract void initView();

	/**
	 * ??????????????????Activity
	 * 
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * ??????????????????Activity???????????????Bundle??????
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * ??????Action??????Activity
	 * 
	 * @param pAction
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * ??????Action??????Activity???????????????Bundle??????
	 * 
	 * @param pAction
	 * @param pBundle
	 */
	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}
	
	protected void DisPlay(String content){
		Toast.makeText(this, content, 1).show();
	}

	/**???????????????*/
	public void showProgressDialog() {
		ProgressDialog progressDialog = null;
		
		if(progressDialog!=null){
			progressDialog.cancel();
		}
		progressDialog=new ProgressDialog(this);
		Drawable drawable=getResources().getDrawable(R.drawable.loading_animation);
		progressDialog.setIndeterminateDrawable(drawable);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setMessage("????????????????????????????????????");
		progressDialog.show();
	}
	
	
	public void DisplayToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	protected void hideOrShowSoftInput(boolean isShowSoft,EditText editText) {
		if (isShowSoft) {
			imm.showSoftInput(editText, 0);
		}else {
			imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
		}
	}
	
	//??????????????????????????????
	protected String getVersionName() throws Exception {
		// ??????packagemanager?????????
		PackageManager packageManager = getPackageManager();
		// getPackageName()???????????????????????????0???????????????????????????
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		return packInfo.versionName;
	}

	
//??????????????????
	protected String getDeviceId() throws Exception {
		String deviceId=tManager.getDeviceId();
		
		return deviceId;
		
	}
	
	/**
	 * ??????SIM????????????
	 * 
	 * @return
	 */
	protected String getToken() {
		return tManager.getSimSerialNumber();
	}

	/*??????????????????*/
	
	protected String getClientOs() {
		return android.os.Build.ID;
	}
	
	/*?????????????????????*/
	protected String getClientOsVer() {
		return android.os.Build.VERSION.RELEASE;
	}
	
	//?????????????????????
	protected String getLanguage() {
		return Locale.getDefault().getLanguage();
	}
	
 protected String getCountry() {
		
		return Locale.getDefault().getCountry();
	}
	
	/**
	 * 
	 * @param <T> ??????????????????????????????????????????
	 * @param pCallable ???????????????????????????
	 * @param pCallback ??????
	 */ 
	protected <T> void doAsync(final Callable<T> pCallable, final Callback<T> pCallback, final Callback<Exception> pExceptionCallback,final boolean showDialog, String message) {
		EMobileTask.doAsync(this, null, message, pCallable, pCallback,pExceptionCallback, false, showDialog);
	}

	protected <T> void doAsync(final CharSequence pTitle,final CharSequence pMessage, final Callable<T> pCallable, final Callback<T> pCallback, final boolean showDialog) {
		EMobileTask.doAsync(this, pTitle, pMessage, pCallable, pCallback, null,false, showDialog);
	}

	/**
	 * Performs a task in the background, showing a {@link ProgressDialog},
	 * while the {@link Callable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResID
	 * @param pMessageResID
	 * @param pErrorMessageResID
	 * @param pCallable
	 * @param pCallback
	 */
	protected <T> void doAsync(final int pTitleResID, final int pMessageResID, final Callable<T> pCallable, final Callback<T> pCallback) {
		this.doAsync(pTitleResID, pMessageResID, pCallable, pCallback, null);
	}

	/**
	 * Performs a task in the background, showing a indeterminate
	 * {@link ProgressDialog}, while the {@link Callable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResID
	 * @param pMessageResID
	 * @param pErrorMessageResID
	 * @param pCallable
	 * @param pCallback
	 * @param pExceptionCallback
	 */
	protected <T> void doAsync(final int pTitleResID, final int pMessageResID, final Callable<T> pCallable, final Callback<T> pCallback, final Callback<Exception> pExceptionCallback) {
		com.adc.huijin.task.EMobileTask.doAsync(this, pTitleResID, pMessageResID, pCallable, pCallback, pExceptionCallback);
	}

	/**
	 * Performs a task in the background, showing a {@link ProgressDialog} with
	 * an ProgressBar, while the {@link AsyncCallable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResID
	 * @param pMessageResID
	 * @param pErrorMessageResID
	 * @param pAsyncCallable
	 * @param pCallback
	 */
	protected <T> void doProgressAsync(final int pTitleResID, final ProgressCallable<T> pCallable, final Callback<T> pCallback) {
		this.doProgressAsync(pTitleResID, pCallable, pCallback, null);
	}

	/**
	 * Performs a task in the background, showing a {@link ProgressDialog} with
	 * a ProgressBar, while the {@link AsyncCallable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResID
	 * @param pMessageResID
	 * @param pErrorMessageResID
	 * @param pAsyncCallable
	 * @param pCallback
	 * @param pExceptionCallback
	 */
	protected <T> void doProgressAsync(final int pTitleResID, final ProgressCallable<T> pCallable, final Callback<T> pCallback,	final Callback<Exception> pExceptionCallback) {
		EMobileTask.doProgressAsync(this, pTitleResID, pCallable, pCallback, pExceptionCallback);
	}

	/**
	 * Performs a task in the background, showing an indeterminate
	 * {@link ProgressDialog}, while the {@link AsyncCallable} is being
	 * processed.
	 * 
	 * @param <T>
	 * @param pTitleResID
	 * @param pMessageResID
	 * @param pErrorMessageResID
	 * @param pAsyncCallable
	 * @param pCallback
	 * @param pExceptionCallback
	 */
	protected <T> void doAsync(final int pTitleResID, final int pMessageResID, final AsyncCallable<T> pAsyncCallable, final Callback<T> pCallback, final Callback<Exception> pExceptionCallback) {
		EMobileTask.doAsync(this, pTitleResID, pMessageResID, pAsyncCallable, pCallback, pExceptionCallback);
	}
	
}
