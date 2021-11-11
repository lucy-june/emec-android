package com.adc.huijin.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.adc.huijin.bean.User;
import com.adc.huijin.utils.ApiClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author kcq
 * 处理用户登录退出等操作。
 *
 */
public class UserLogin {

	public User login(String username,String password) throws IOException{
		Map<String, String> params=new HashMap<String,String>();
		params.put("username", username);
		params.put("password", password);
		String result=ApiClient.post("http://192.168.0.189:3001/login1", params);
		//解析json数据包
		System.out.println(result);
		JSONObject loginResult=(JSONObject) JSON.parse(result);
		boolean isSuccess=loginResult.getBoolean("result");
		if(isSuccess){
			return loginResult.getObject("user", User.class);
		}
		return null;
	}
	
	public static void main(String [] args){
		UserLogin ul=new UserLogin();
		try {
			ul.login("test","test123");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
