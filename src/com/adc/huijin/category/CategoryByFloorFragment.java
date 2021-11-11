package com.adc.huijin.category;



import com.adc.huijin.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CategoryByFloorFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the
 * {@link CategoryByFloorFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class CategoryByFloorFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
    public Context context;
	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment CategoryByFloorFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static CategoryByFloorFragment newInstance(String param1,
			String param2) {
		CategoryByFloorFragment fragment = new CategoryByFloorFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public CategoryByFloorFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view=inflater.inflate(R.layout.fragment_category_by_floor, container,
				false);
		 RadioGroup rg= (RadioGroup)view.findViewById(R.id.floor_tabs);
			rg.setOnCheckedChangeListener(new CategoryByMainStreamRGListener(this));
			
			MyListView gv=(MyListView) view.findViewById(R.id.sub_category_gridview);
			String[] a={"奢侈名包","数码家电"};
			int[] b={R.drawable.luxary_bags,R.drawable.catergory_digtcamer};
			CategoryListViewAdapter gva=new CategoryListViewAdapter(this,a,b);
			gv.setAdapter(gva);
		    
			gv.setOnItemClickListener(new OnItemClickListener(){


				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					Intent in=new Intent(getContext(),ThirdClassActivity.class);
					startActivity(in);
				}

			}
		    );
			return view;
			
	}

	protected Context getContext() {
		// TODO Auto-generated method stub
		return this.getActivity();
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
