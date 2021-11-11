package com.adc.huijin.category;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeViewPagerAdapter extends FragmentPagerAdapter{

	private List<Fragment> myFragments;
	public HomeViewPagerAdapter(FragmentManager fm,List<Fragment> fms) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.myFragments=fms;
	}
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		if(this.myFragments == null)
			return null;
		return this.myFragments.get(arg0);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(this.myFragments == null)
			return 0;
		return this.myFragments.size();
	}

}
