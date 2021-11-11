package com.adc.huijin.adapter;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

public class ProductDetailPagerAdapter extends PagerAdapter{    
       
    private ImageView[] mImageViews; 
    
    private String[] picsUrls;
    
    public ProductDetailPagerAdapter(String [] urls,ImageView[] views){
    	this.picsUrls=urls;
    	mImageViews=views;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.picsUrls.length;
	}
	
	@Override  
    public void destroyItem(View container, int position, Object object) {  
        ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);  
          
    }

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	 /** 
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键 
     */  
    @Override  
    public Object instantiateItem(View container, int position) {  
    	if(position<this.picsUrls.length){//可以循环查看图片
    		System.out.println("+++++++++++++++++++++++++++++++"+position+"\t"+picsUrls.length);
    		System.out.println("-------------------------------"+mImageViews[position].toString());
    		//ImageLoader.getInstance().displayImage(picsUrls[position], mImageViews[position]);
    	}
        ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);  
        return mImageViews[position % mImageViews.length];  
    }  
}
