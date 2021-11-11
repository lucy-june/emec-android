package com.adc.huijin.category;

import java.util.List;

import com.adc.huijin.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CategoryListViewAdapter extends BaseAdapter{

	private String[] sub_categories;
	private Fragment context;
	private int[] imageUrl;
	public CategoryListViewAdapter(Fragment context,String[] sc,int[] images)
	{
		this.context=context;
		this.sub_categories=sc;
		this.imageUrl=images;
	}
	public void setImage(int[] id)
	{
		this.imageUrl=id;
	}
	public void setString(String[] sc)
	{
		this.sub_categories=sc;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.sub_categories.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) this.context.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			View gridView = null;
	 
			//if (convertView == null)
			{
	 
				//gridView = new View(context);
	 
				// get layout from mobile.xml
				gridView = inflater.inflate(R.layout.category_item, null);
	 
				// set value into textview
				TextView textView = (TextView) gridView
						.findViewById(R.id.grid_item_label);
				textView.setText(this.sub_categories[position]);
	 
				// set image based on selected text
				ImageView imageView = (ImageView) gridView
						.findViewById(R.id.grid_item_image);
	            imageView.setImageResource(this.imageUrl[position]);
			}//else {
			//	gridView = (View) convertView;
			//}
			return gridView;
	}
}

