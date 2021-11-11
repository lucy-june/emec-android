package com.adc.huijin.bean;

public class ProductSKC {
	int skcId;
	String skcSize;
	String skcColor;
	double price;
	int stock;//库存
	
	
	public ProductSKC(int skcId, String skcColor, String skcSize, double price,
			int stock) {
		super();
		this.skcId = skcId;
		this.skcSize = skcSize;
		this.skcColor = skcColor;
		this.price = price;
		this.stock = stock;
	}
	public ProductSKC(int skcId,String skcColor, String skcSize, int stock){
		this.skcId=skcId;
		this.skcColor=skcColor;
		this.skcSize=skcSize;
		this.stock=stock;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getSkcId() {
		return skcId;
	}
	public void setSkcId(int skcId) {
		this.skcId = skcId;
	}
	public String getSkcSize() {
		return skcSize;
	}
	public void setSkcSize(String skcSize) {
		this.skcSize = skcSize;
	}
	public String getSkcColor() {
		return skcColor;
	}
	public void setSkcColor(String skcColor) {
		this.skcColor = skcColor;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
