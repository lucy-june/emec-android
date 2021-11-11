package com.adc.huijin.category;



import com.adc.huijin.R;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public final class CategoryByMainStreamRGListener implements OnCheckedChangeListener{

	Fragment context;
	public CategoryByMainStreamRGListener(Fragment context)
	{
		this.context=context;
	}
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		MyListView gv=(MyListView) context.getView().findViewById(R.id.sub_category_gridview);
		CategoryListViewAdapter gvad=(CategoryListViewAdapter) gv.getAdapter();
		switch(arg0.getCheckedRadioButtonId())
		{
		case R.id.inter_brands:case R.id.floor1:
		{
			String[] a={"奢侈名包","数码家电"};
			int[] b={R.drawable.luxary_bags,R.drawable.catergory_digtcamer};
			gvad.setImage(b);
			gvad.setString(a);
			break;
		}
		case R.id.fasion_women_clothing:case R.id.floor2:
		{
			String[] a={"女装内衣","女装上装"};
			int[] b={R.drawable.undershit,R.drawable.upshirt};
			gvad.setImage(b);
			gvad.setString(a);
			break;
		}
		case R.id.fasion_men_clothing:case R.id.floor3:
		{
			String[] a={"男装上装","男装下装"};
			int[] b={R.drawable.menclothing,R.drawable.menunderclothing};
			gvad.setImage(b);
			gvad.setString(a);
			break;
		}
		case R.id.sprots_outdoor:
		{
			
		}
		case R.id.bags_handbags:
		{
			
		}
		case R.id.furniture_textile:
		{
			
		}
		case R.id.beauty_cosmetics:
		{
			
		}
		case R.id.foods_drinks:
		{
			
		}
		}
		gvad.notifyDataSetChanged();
		
	}

}
