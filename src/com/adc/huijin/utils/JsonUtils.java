package com.adc.huijin.utils;

import java.util.ArrayList;
import java.util.List;

import com.adc.huijin.bean.Product;
import com.adc.huijin.bean.ProductBasicInfo;
import com.adc.huijin.bean.ProductSKC;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	
	//获得product
	public static Product parseProduct(String json){
		Product product=new Product();
		JSONObject object=JSON.parseObject(json);
		product.setItemCode(object.getString("ITEMCODE"));
		product.setName(object.getString("ITEMNAME"));
		product.setBrandCode(object.getString("BRANDCODE"));
		product.setDescription(object.getString("DESCRIPTION"));
		product.setmPrice(object.getDoubleValue("MARKETPRICE"));
		product.setHjPrice(object.getDoubleValue("PRICE"));
		JSONArray pics=object.getJSONArray("itempics");
		String [] urls=new String[pics.size()];
		int i;
		for(i=0;i<urls.length;i++){
			urls[i]=pics.getJSONObject(i).getString("URL");
		}
		product.setPicsUrl(urls);
		JSONArray skcArray=object.getJSONArray("skcs");
		List<ProductSKC> skcs=new ArrayList<ProductSKC>();
		for(i=0;i<skcArray.size();i++){
			ProductSKC skc=new ProductSKC(skcArray.getJSONObject(i).getIntValue("ID"),skcArray.getJSONObject(i).getString("COLOR"),skcArray.getJSONObject(i).getString("SIZE"),skcArray.getJSONObject(i).getIntValue("STOCK"));
			skcs.add(skc);
		}
		product.setSkcs(skcs);
		product.setFavourite(false);
		return product;
	}
	
	//获取商品简要信息列表
	public static List<ProductBasicInfo> parseProductList(String json){
		List<ProductBasicInfo> productList=new ArrayList<ProductBasicInfo>();
		JSONArray list=JSON.parseArray(json);
		for(int i=0;i<list.size();i++){
			ProductBasicInfo pbi=new ProductBasicInfo();
			JSONObject tmp=list.getJSONObject(i);
			pbi.setItemCode(tmp.getString("ITEMCODE"));
			/*if(tmp.getString("BRANDNAME").equals("null")){
				pbi.setItemName(tmp.getString("ITEMNAME"));
			}else{
				pbi.setItemName(tmp.getString("BRANDNAME")+" "+tmp.getString("ITEMNAME"));
			}*/
			pbi.setItemName(tmp.getString("BRANDNAME")+" "+tmp.getString("ITEMNAME"));
			pbi.setBrand(tmp.getString("BRANDNAME"));
			pbi.setHjPrice(tmp.getDoubleValue("PRICE"));
			pbi.setCommentNum(234);
			pbi.setRating(1);
			pbi.setMarketPrice(tmp.getDoubleValue("MARKETPRICE"));
			pbi.setPicUrl(tmp.getString("URL"));
			productList.add(pbi);
		}
		return productList;
	}
}
