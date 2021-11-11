package com.adc.huijin.bean;

import java.util.ArrayList;
import java.util.List;

public class Product {
	String itemCode;
	String name;
	String description;
	String brandCode;
	String[] picsUrl;
	double hjPrice;
	double mPrice;
	boolean isFavourite;
	float rating;
	int commentsNum;
	
	String [] promotions;//后面把这个写成类.
	
	//skc应该怎么组织？
	//String [] skc_sizes;
	//String [] skc_colors;
	List<ProductSKC> skcs;
	
	
	
	
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public List<ProductSKC> getSkcs() {
		return skcs;
	}

	public void setSkcs(List<ProductSKC> skcs) {
		this.skcs = skcs;
	}

	//获得全部颜色
	public List<String> getAllColors(){
		List<String> colors=new ArrayList<String>();
		String color;
		for(int i=0;i<this.skcs.size();i++){
			color=this.skcs.get(i).getSkcColor();
			if(color!=null&&!color.isEmpty()&&!colors.contains(color)){//&&!colors.contains(color)
				colors.add(color);
			}
		}
		return colors;
	}
	
	//获得全部尺码
	public List<String> getAllSizes(){
		List<String> sizes=new ArrayList<String>();
		String size;
		for(int i=0;i<this.skcs.size();i++){
			size=this.skcs.get(i).getSkcSize();
			if(size!=null&&!size.isEmpty()&&!sizes.contains(size)){//&&!sizes.contains(size)
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+size);
				sizes.add(size);
			}
		}
		return sizes;
	}
	
	//根据尺码获得可选的颜色。
	public List<String> getColors(String size){
		List<String> colors=new ArrayList<String>();
		for(int i=0;i<this.skcs.size();i++){
			if(this.skcs.get(i).getSkcSize().equals(size)&&this.skcs.get(i).getStock()>0&&this.skcs.get(i).getSkcColor()!=null&&!this.skcs.get(i).getSkcColor().isEmpty()&&!colors.contains(this.skcs.get(i).getSkcColor())){
				colors.add(this.skcs.get(i).getSkcColor());
			}
		}
		return colors;
	}
	
	//根据颜色获得可选的尺码。
	public List<String> getSizes(String color){
		List<String> sizes=new ArrayList<String>();
		for(int i=0;i<this.skcs.size();i++){
			if(this.skcs.get(i).getSkcColor().equals(color)&&this.skcs.get(i).getStock()>0&&this.skcs.get(i).getSkcSize()!=null&&!this.skcs.get(i).getSkcSize().isEmpty()&&!sizes.contains(this.skcs.get(i).getSkcSize())){
				sizes.add(this.skcs.get(i).getSkcSize());
			}
		}
		return sizes;
	}
	//根据颜色尺码获得skc
	public ProductSKC getProductSKC(String color,String size){
		ProductSKC skc=null;
		for(int i=0;i<this.skcs.size();i++){
			skc=this.skcs.get(i);
			if((color==null||color.isEmpty()||color.equals(skc.getSkcColor()))&&(size==null||size.isEmpty()||size.equals(skc.getSkcSize()))){
				break;
			}
		}
		return skc;
	}
	//获得第一个skc
	public ProductSKC getFirstSKC(){
		if(skcs.size()>0)
		 return skcs.get(0);
		return null;
	}
	//获得价格最低的skc
	public ProductSKC getCheapSKC(){
		ProductSKC skc=null;
		if(this.skcs.size()>0)
			skc=this.skcs.get(0);
		for(int i=1;i<this.skcs.size();i++){
			if(skc.getPrice()>this.skcs.get(i).getPrice()){
				skc=this.skcs.get(i);
			}
		}
		return skc;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}
	public String[] getPromotions() {
		return promotions;
	}
	public void setPromotions(String[] promotions) {
		this.promotions = promotions;
	}
	public boolean isFavourite() {
		return isFavourite;
	}
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getPicsUrl() {
		return picsUrl;
	}
	public void setPicsUrl(String [] picsUrl) {
		this.picsUrl = picsUrl;
	}
	public double getHjPrice() {
		return hjPrice;
	}
	public void setHjPrice(double hjPrice) {
		this.hjPrice = hjPrice;
	}
	public double getmPrice() {
		return mPrice;
	}
	public void setmPrice(double mPrice) {
		this.mPrice = mPrice;
	}
	
	
}
