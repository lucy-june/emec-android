package com.adc.huijin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ApiClient {
	//private static Logger log = Logger.getLogger(HttpXmlClient.class);
	
	public static String post(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		
		//log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);
		
		body = invoke(httpclient, post);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	}
	
	public static String get(String url) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		
		//log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);		
		HttpResponse resp = httpclient.execute(get);
		if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
			body= EntityUtils.toString(resp.getEntity(),"UTF-8");
		}		
		httpclient.getConnectionManager().shutdown();
		return body;
	}
		
	
	private static String invoke(DefaultHttpClient httpclient,
			HttpUriRequest httpost) throws ClientProtocolException, IOException {
		
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		
		return body;
	}

	private static String paseResponse(HttpResponse response) {
		//log.info("get response from http server..");
		HttpEntity entity = response.getEntity();
		
		String body = "";
		try {
			body = EntityUtils.toString(entity,"UTF-8");
			//log.info(body);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient,
			HttpUriRequest httpost) throws ClientProtocolException, IOException {
		//log.info("execute post...");
		HttpResponse response = null;
		response = httpclient.execute(httpost);		
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params){
		
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		Set<String> keySet = params.keySet();
		for(String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		
		try {
			//log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return httpost;
	}

	public static Bitmap getNetBitmap(String url) {
		// TODO Auto-generated method stub
		Bitmap bitmap=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse resp = httpclient.execute(httpget);
			if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
				HttpEntity entity = resp.getEntity();
				InputStream in = entity.getContent();
				bitmap = BitmapFactory.decodeStream(in);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			httpclient.getConnectionManager().shutdown();
		}
		
		return bitmap;
	}
	
}
