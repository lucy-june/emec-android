package com.adc.huijin.category;

import java.util.ArrayList;
import java.util.List;








import com.adc.huijin.R;
import com.adc.huijin.category.CategoryByFloorFragment.OnFragmentInteractionListener;

import android.app.Activity;
import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class CategoryActivity extends BaseActivity implements com.adc.huijin.category.CategoryByFloorFragment.OnFragmentInteractionListener ,
com.adc.huijin.category.CategoryFragment.OnFragmentInteractionListener,com.adc.huijin.category.CategoryByMainstreamFragment.OnFragmentInteractionListener  
{

	private List<Fragment> AppFragments;
	private PagerAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_new);

		this.AppFragments = getFragments();
		mAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(),AppFragments);
		ViewPager vp = (ViewPager) findViewById(R.id.home_viewpager);
		vp.setAdapter(mAdapter);
		
	}

	private List<Fragment> getFragments()
	{
		List<Fragment> f = new ArrayList<Fragment>();
		f.add(new CategoryFragment());

		return f;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_category,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}

}
