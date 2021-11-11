package com.adc.huijin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.adc.huijin.AppException;
import com.adc.huijin.bean.GoodsResult;

/**
 * 
 * @author kcq
 *
 */
public class HttpGetGoods {
	
	public static GoodsResult getResult(String url) throws ClientProtocolException, IOException{
		String result = "";
        BufferedReader in = null;
		URL realUrl;
		result=ApiClient.get(url);
		GoodsResult goodsResult=JSON.parseObject(result,GoodsResult.class);
		return goodsResult;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoodsResult test=null;
		try {
			test = HttpGetGoods.getResult("http://127.0.0.1:3000/goods.html");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test: "+test.resultCount+"\t"+test.results.get(0).price);
		
	}

}
