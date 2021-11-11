package com.adc.huijin.adapter;

import java.util.List;

import com.adc.huijin.R;
import com.adc.huijin.bean.ProductRecommend;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendGalleryAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater layoutInflater;
	int length;
	List<ProductRecommend> list;
	View[] views;
	
	public RecommendGalleryAdapter(Context context, List<ProductRecommend> list){
		this.context=context;
		this.layoutInflater=LayoutInflater.from(context);
		this.list=list;
		this.length=list.size()/2;
		views=new View[this.length];
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(this.list!=null&&this.list.size()>0)
			return Integer.MAX_VALUE;
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(arg1==null||arg1.getTag()==null){
			
			arg0=arg0%this.length;
			if(views[arg0]==null){
				arg1=this.layoutInflater.inflate(R.layout.product_recommend_item, null);
				
				arg1.setOnTouchListener(new View.OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						/*if(event.getAction()==0){
							//return true;
							ItemHolder holder=(ItemHolder)v.getTag();
							if(holder!=null){
								Toast.makeText(RecommendGalleryAdapter.this.context, "点击了"+holder.txtTitle.getText(), 1).show();
							}
						}*/
						return false;
					}
				});
				
				View top=arg1.findViewById(R.id.r_layout_1);
				ItemHolder topHolder=new ItemHolder();
				topHolder.rootView=top;
				topHolder.imageView=(ImageView)top.findViewById(R.id.img_1);
				topHolder.txtTitle=(TextView)top.findViewById(R.id.txt_1);
				topHolder.txtPrice=(TextView)top.findViewById(R.id.txt_2);
				ImageLoader.getInstance().displayImage(this.list.get(arg0*2).getImageUrl(), topHolder.imageView);
				topHolder.txtTitle.setText(this.list.get(arg0*2).getTitle());
				topHolder.txtPrice.setText(this.list.get(arg0*2).getPrice()+"");
				top.setTag(topHolder);
				//top.setOnClickListener(listener);
				
				top.setOnTouchListener(new View.OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==0){
							ItemHolder holder=(ItemHolder)v.getTag();
							if(holder!=null){
								Toast.makeText(RecommendGalleryAdapter.this.context, "点击了"+holder.txtTitle.getText(), 1).show();
							}
						}
						return false;
					}
				});
				
				View bottom=arg1.findViewById(R.id.r_layout_2);
				ItemHolder bottomHolder=new ItemHolder();
				bottomHolder.rootView=bottom;
				bottomHolder.imageView=(ImageView)bottom.findViewById(R.id.img_1);
				bottomHolder.txtTitle=(TextView)bottom.findViewById(R.id.txt_1);
				bottomHolder.txtPrice=(TextView)bottom.findViewById(R.id.txt_2);
				ImageLoader.getInstance().displayImage(this.list.get(arg0*2+1).getImageUrl(), bottomHolder.imageView);
				bottomHolder.txtTitle.setText(this.list.get(arg0*2+1).getTitle());
				bottomHolder.txtPrice.setText(this.list.get(arg0*2+1).getPrice()+"");
				bottom.setTag(bottomHolder);
				bottom.setVisibility(View.VISIBLE);
				//bottom.setOnClickListener(listener);
				views[arg0]=arg1;
			}else{
				arg1=views[arg0];
			}
			
		}
		return arg1;
	}
	
	//点击事件处理。
	View.OnClickListener listener=new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ItemHolder holder=(ItemHolder)v.getTag();
			Toast.makeText(RecommendGalleryAdapter.this.context, "点击了"+holder.txtTitle.getText(), 1).show();
		}
	};
	
	class ItemHolder{
		ImageView imageView;
        View rootView;
        TextView txtPrice;
        TextView txtTitle;
	}

}
