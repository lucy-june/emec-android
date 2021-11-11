package com.adc.huijin.bean;

public class ProductRecommend {
	int skuId;
	double price;
	String imageUrl;
	String title;
	
	
	
	public ProductRecommend(int skuId, double price, String imageUrl,
			String title) {
		super();
		this.skuId = skuId;
		this.price = price;
		this.imageUrl = imageUrl;
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSkuId() {
		return skuId;
	}
	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
