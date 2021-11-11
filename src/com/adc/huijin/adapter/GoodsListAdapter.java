package com.adc.huijin.adapter;


import com.adc.huijin.R;
import com.adc.huijin.bean.GoodsResult;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsListAdapter extends BaseAdapter {
	/**
	 * added by kcq 2014.06.05
	 */
	
	private LayoutInflater layoutInflater;  
    private GoodsResult goodsResult;
    private Context context;
    
    public GoodsResult getGoodsResult() {
		return goodsResult;
	}

	public void setGoodsResult(GoodsResult goodsResult) {
		this.goodsResult = goodsResult;
	}

	
	public GoodsListAdapter(Context context,GoodsResult goodsResult){
		this.context=context;
    	this.layoutInflater=LayoutInflater.from(context);
    	this.goodsResult=goodsResult;
    	//getData(url);
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsResult.resultCount;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return goodsResult.results.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder=new ViewHolder();
		if(arg1==null){
			arg1=layoutInflater.inflate(R.layout.activity_goods_item, null);
			holder.image=(ImageView) arg1.findViewById(R.id.goodsitem_image);
			holder.title=(TextView) arg1.findViewById(R.id.goodsitem_title);
			holder.content=(TextView) arg1.findViewById(R.id.goodsitem_content);
			holder.price=(TextView) arg1.findViewById(R.id.goodsitem_price);
			holder.info=(TextView) arg1.findViewById(R.id.goodsitem_info);
			//使用tag存储数据
			arg1.setTag(holder);
		}else{
			holder=(ViewHolder) arg1.getTag();
		}
		//Bitmap bitmap=HttpImageLoader.getHttpBitmap(this.goodsResult.results.get(arg0).imageUrl);
		ImageLoader.getInstance().displayImage(this.goodsResult.results.get(arg0).imageUrl, holder.image);
		//holder.image.setImageBitmap(bitmap);
		holder.title.setText(this.goodsResult.results.get(arg0).name);
		if(this.goodsResult.results.get(arg0).description.length()>32){
			holder.content.setText(this.goodsResult.results.get(arg0).description.substring(0, 30)+"...");
		}else{
			holder.content.setText(this.goodsResult.results.get(arg0).description);
		}		
		holder.price.setText(this.goodsResult.results.get(arg0).price+"");
		holder.info.setText("好评率 99% （1234人）");
		return arg1;
	}
	
	public static class ViewHolder{
		ImageView image;
		TextView title;
		TextView content;
		TextView price;
		TextView info;
	}

}
