package com.adc.huijin.ui;

import java.util.concurrent.Callable;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.adc.huijin.R;
import com.adc.huijin.task.Callback;
import com.adc.huijin.ui.base.BaseActivity;
import com.adc.huijin.utils.CommonTools;
import com.adc.huijin.utils.HttpsRequestUtils;

public class RegisterNormalActivity extends BaseActivity {
	private EditText reg_userid, reg_mobilephone, reg_email, reg_password, reg_repassword;
	private Button btn;
	private RegisterNormalActivity registerNormalActivity=null;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register_normal);
		registerNormalActivity = RegisterNormalActivity.this;
		findViewById();
		initView();
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		reg_userid = (EditText) this.findViewById(R.id.register_normal_userid);
		reg_mobilephone = (EditText) this.findViewById(R.id.register_normal_mobilephone);
		reg_email = (EditText) this.findViewById(R.id.register_normal_email);
		reg_password = (EditText) this.findViewById(R.id.register_normal_password);
		reg_repassword = (EditText) this.findViewById(R.id.register_normal_repassword);
		btn = (Button) this.findViewById(R.id.normal_register);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doRegister();
			}
		});
	}


	private void doRegister() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		final String userid = reg_userid.getText().toString().trim();
		final String mobilephone = reg_mobilephone.getText().toString().trim();
		final String email = reg_email.getText().toString().trim();
		final String password = reg_password.getText().toString().trim();
		final String repassword = reg_repassword.getText().toString().trim();

		if (userid.equals("")) {
			DisplayToast("用户名不能为空");
			return;
		}
		if (password.equals("")) {
			DisplayToast("密码不能为空");
			return;
		}
		if (!password.equals(repassword)) {
			DisplayToast("两次密码输入不一致");
			return;
		}
		if (mobilephone.equals("")) {
			DisplayToast("手机号不能为空");
			return;
		}

		//*******************************
		registerNormalActivity.doAsync(new Callable<String>() {

			@Override
			public String call() throws Exception {

				String request = "USERID=" + userid + "&PASSWORD=" + password + "&TELPHONE=" + mobilephone;
				String result = HttpsRequestUtils.sendPost(HttpsRequestUtils.base+"app/account/regist", request);
				return result;
			}

		}, new Callback<String>() {

			@Override
			public void onCallback(String pCallbackValue) {
				// TODO Auto-generated method stub
				if(pCallbackValue.trim().equals("1")){
					CommonTools.showShortToast(RegisterNormalActivity.this, "注册成功");
				}
				if(pCallbackValue.trim().equals("0")){
					CommonTools.showShortToast(RegisterNormalActivity.this, "用户名已存在");
				}
			}
		}, new Callback<Exception>() {
			@Override
			public void onCallback(Exception pCallbackValue) {
				// TODO Auto-generated method stub
				pCallbackValue.printStackTrace();
			}
		}, true, getResources().getString(R.string.register_loading));


	}

}