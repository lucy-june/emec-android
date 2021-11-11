//package com.adc.huijin.ui;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.adc.huijin.R;
//import com.adc.huijin.ui.base.BaseActivity;
//import com.adc.huijin.utils.CommonTools;
//import com.adc.huijin.utils.ExitView;
//import com.adc.huijin.widgets.CustomScrollView;
//
//
//public class PersonalActivity extends BaseActivity implements OnClickListener {
//
//	private ImageView mBackgroundImageView = null;
//	private Button mLoginButton,mMoreButton,mExitButton;
//	private CustomScrollView mScrollView = null;
//	private Intent mIntent=null;
//	private ExitView exit;
//	private LinearLayout Ly_login,Ly_Other;
//	private RelativeLayout Ly_personalInfo;
//	private TextView username;
//	private int LOGIN_CODE=100;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_personal);
//		findViewById();
//		initView();
//	}
//
//	@Override
//	protected void findViewById() {
//		// TODO Auto-generated method stub
//		mBackgroundImageView = (ImageView) findViewById(R.id.personal_background_image);
//		mLoginButton = (Button) findViewById(R.id.personal_login_button);
//		mScrollView = (CustomScrollView) findViewById(R.id.personal_scrollView);
//		mMoreButton=(Button)this.findViewById(R.id.personal_more_button);
//		mExitButton=(Button)this.findViewById(R.id.personal_exit);
//		
//		
//		Ly_login=(LinearLayout)findViewById(R.id.login);
//		Ly_personalInfo=(RelativeLayout)findViewById(R.id.personal);
//		Ly_Other=(LinearLayout)findViewById(R.id.other_layout);
//		username=(TextView)findViewById(R.id.username);
//	}
//
//	@Override
//	protected void initView() {
//		// TODO Auto-generated method stub
//		mScrollView.setImageView(mBackgroundImageView);
//		
//		mLoginButton.setOnClickListener(this);
//		mMoreButton.setOnClickListener(this);
//		mExitButton.setOnClickListener(this);
//		
//	}
//
//	@Override
//	public void onClick(View v) {
//		//CommonTools.showShortToast(PersonalActivity.this, "稍后开放");
//		switch (v.getId()) {
//		case R.id.personal_login_button:
//			mIntent=new Intent(PersonalActivity.this, LoginActivity.class);
//			
//			startActivityForResult(mIntent, LOGIN_CODE);
//			break;
//
//		case R.id.personal_more_button:
//			mIntent=new Intent(PersonalActivity.this, MoreActivity.class);
//			startActivity(mIntent);
//			break;
//			
//		case R.id.personal_exit:
//			
//			//实例化SelectPicPopupWindow
//			exit = new ExitView(PersonalActivity.this, itemsOnClick);
//			//显示窗口
//			exit.showAtLocation(PersonalActivity.this.findViewById(R.id.layout_personal), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
//			
//			
//			break;
//			
//		default:
//			break;
//		}
//		
//	}
//	
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		
//		if(resultCode==20){
////			String name=data.getExtras().getString("username");
////			Log.i("name", name);
////			username.setText(name);
//			if(Ly_login.isShown()){
//				Ly_personalInfo.setVisibility(View.VISIBLE);
//				Ly_login.setVisibility(View.GONE);
//				Ly_Other.setVisibility(View.VISIBLE);
//			}
//			Ly_personalInfo.setVisibility(View.VISIBLE);
//			Ly_login.setVisibility(View.GONE);
//			Ly_Other.setVisibility(View.VISIBLE);
//		}
//		super.onActivityResult(requestCode, resultCode, data);
//	}
//	
//	//为弹出窗口实现监听类
//    private OnClickListener  itemsOnClick = new OnClickListener(){
//
//		public void onClick(View v) {
//			
//			switch (v.getId()) {
//			case R.id.btn_exit:
//				CommonTools.showShortToast(PersonalActivity.this, "退出程序");
//				
//				break;
//			case R.id.btn_cancel:
//				PersonalActivity.this.dismissDialog(R.id.btn_cancel);
//				
//				break;
//			default:
//				break;
//			}
//		}
//    };
//	
//}

package com.adc.huijin.ui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adc.huijin.R;
import com.adc.huijin.ui.base.BaseActivity;
import com.adc.huijin.utils.CommonTools;
import com.adc.huijin.utils.ExitView;
import com.adc.huijin.widgets.CustomScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;


public class PersonalActivity extends BaseActivity implements OnClickListener {

	private ImageView mBackgroundImageView = null, personIcon;
	private Button mLoginButton,mMoreButton,mExitButton;
	private RelativeLayout order_search, order_waitpay, all_order;
	private CustomScrollView mScrollView = null;
	private Intent mIntent=null;
	private ExitView exit;
	private LinearLayout Ly_login,Ly_Other;
	private RelativeLayout Ly_personalInfo;
	private TextView userid, jobtitle, score;
	private int LOGIN_CODE=100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		findViewById();
		initView();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		mBackgroundImageView = (ImageView)findViewById(R.id.personal_background_image);
		mLoginButton = (Button)findViewById(R.id.personal_login_button);
		mScrollView = (CustomScrollView)findViewById(R.id.personal_scrollView);
		mMoreButton=(Button)findViewById(R.id.personal_more_button);
		mExitButton=(Button)findViewById(R.id.personal_exit);
		
		order_search = (RelativeLayout)findViewById(R.id.personal_order_search);
		order_waitpay = (RelativeLayout)findViewById(R.id.personal_order_waitpay);
		all_order = (RelativeLayout)findViewById(R.id.personal_all_order);
		
		personIcon=(ImageView)findViewById(R.id.main_personIcon);
		Ly_login=(LinearLayout)findViewById(R.id.login);
		Ly_personalInfo=(RelativeLayout)findViewById(R.id.personal);
		Ly_Other=(LinearLayout)findViewById(R.id.other_layout);
		userid=(TextView)findViewById(R.id.userid);
		jobtitle=(TextView)findViewById(R.id.jobtitle);
		score=(TextView)findViewById(R.id.dept);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		mScrollView.setImageView(mBackgroundImageView);
		
		mLoginButton.setOnClickListener(this);
		mMoreButton.setOnClickListener(this);
		mExitButton.setOnClickListener(this);
		
		order_search.setOnClickListener(this);
		order_waitpay.setOnClickListener(this);
		all_order.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//CommonTools.showShortToast(PersonalActivity.this, "稍后开放");
		switch (v.getId()) {
		case R.id.personal_login_button:
			mIntent=new Intent(PersonalActivity.this, LoginActivity.class);
			startActivityForResult(mIntent, LOGIN_CODE);
			break;
		case R.id.personal_more_button:
			mIntent=new Intent(PersonalActivity.this, MoreActivity.class);
			startActivity(mIntent);
			break;
		case R.id.personal_exit:
			//实例化SelectPicPopupWindow
			exit = new ExitView(PersonalActivity.this, itemsOnClick);
			//显示窗口
			exit.showAtLocation(PersonalActivity.this.findViewById(R.id.layout_personal), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
			break;
		case R.id.personal_order_search:
			CommonTools.showShortToast(PersonalActivity.this, "personal order search");
			break;
		case R.id.personal_order_waitpay:
			CommonTools.showShortToast(PersonalActivity.this, "personal order waitpay");
			break;
		case R.id.personal_all_order:
			CommonTools.showShortToast(PersonalActivity.this, "personal all order");
			break;

		default:
			break;
		}
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if(resultCode==20){
//			String name=data.getExtras().getString("userid");
//			Log.i("name", name);
//			userid.setText(name);
			if(Ly_login.isShown()){
				Ly_personalInfo.setVisibility(View.VISIBLE);
				Ly_login.setVisibility(View.GONE);
				Ly_Other.setVisibility(View.VISIBLE);
			}
			Ly_personalInfo.setVisibility(View.VISIBLE);
			Ly_login.setVisibility(View.GONE);
			Ly_Other.setVisibility(View.VISIBLE);
			final Bundle contents = data.getExtras();
			userid.setText(contents.getString("userid"));
			jobtitle.setText(contents.getString("title"));
			score.setText("积分 " + contents.getInt("score"));
			ImageLoader.getInstance().displayImage(contents.getString("iconurl"), personIcon);
//			new Thread(new Runnable() {
//				public void run() {
//					Bitmap icon = getHttpBitmap(contents.getString("iconurl"));
//					if (icon != null)
//						personIcon.setImageBitmap(icon);
//					}
//				}).start();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
//	public static Bitmap getHttpBitmap(String src){
//		try{
//			URL url = new URL(src);
//			Log.d("url", "1");
//			//获得连接
//			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//			Log.d("url", "2");
//			//设置超时时间为5000毫秒，conn.setConnectionTiem(0);表示没有时间限制
//			conn.setConnectTimeout(5000);
//			Log.d("url", "3");
//			//连接设置获得数据流
//			conn.setDoInput(true);
//			Log.d("url", "4");
//			//不使用缓存
//			conn.setUseCaches(false);
//			Log.d("url", "5");
//			//这句可有可无，没有影响
//			conn.connect();
//			Log.d("url", "6");
//			//得到数据流
//			InputStream is = conn.getInputStream();
//			Log.d("url", "7");
//			//解析得到图片
//			Bitmap bitmap = BitmapFactory.decodeStream(is);
//			Log.d("url", "8");
//			//关闭数据流
//			is.close();
//			Log.d("url", "9");
//			return bitmap;
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}

	
	//为弹出窗口实现监听类
    private OnClickListener itemsOnClick = new OnClickListener(){

		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.btn_exit:
				CommonTools.showShortToast(PersonalActivity.this, "退出程序");
				break;
			case R.id.btn_cancel:
				PersonalActivity.this.dismissDialog(R.id.btn_cancel);
				break;
				
			default:
				break;
			}
		}
    };
	
}

