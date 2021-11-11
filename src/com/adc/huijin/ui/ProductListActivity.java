package com.adc.huijin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adc.huijin.AppConfig;
import com.adc.huijin.R;
import com.adc.huijin.bean.ProductBasicInfo;
import com.adc.huijin.task.Callback;
import com.adc.huijin.ui.base.BaseActivity;
import com.adc.huijin.utils.HttpsRequestUtils;
import com.adc.huijin.utils.JsonUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProductListActivity extends BaseActivity{
	ListView productListView;
	AutoCompleteTextView autoCompleteTextView;
	ProductListActivity productListActivity;
	List<ProductBasicInfo> productList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		productListActivity=ProductListActivity.this;
		setContentView(R.layout.activity_product_list);
		findViewById();
		initData();
	}
	
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		productListView=(ListView)this.findViewById(R.id.product_list_search_lv);
		autoCompleteTextView=(AutoCompleteTextView)this.findViewById(R.id.product_list_search_et);
		autoCompleteTextView.clearFocus();
		autoCompleteTextView.setCursorVisible(false);
		//System.out.println("MMMMMMMMMMMMMMMMMMMMMMM"+this.productListView.toString());
	}

	@Override
	protected void initView() {
		ProductListAdapter adapter=new ProductListAdapter(this.productList);
		productListView.setAdapter(adapter);
		//System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLL");
		
		productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(productListActivity,ProductDetailActivity.class);
				intent.putExtra("SKUID", ((ProductHolder)arg1.getTag()).skuid);
				startActivity(intent);
			}
		});
	}
	void initData() {
		// TODO Auto-generated method stub
		productListActivity.doAsync(new Callable<List<ProductBasicInfo>>(){

			@Override
			public List<ProductBasicInfo> call() throws Exception {
				// TODO Auto-generated method stub
				String s5 =HttpsRequestUtils.sendGet(AppConfig.HTTPSHOST+"app/item/getBrief","page=1&num=6");
				List<ProductBasicInfo> productList=JsonUtils.parseProductList(s5);
				return productList;
			}
			
		}, new Callback<List<ProductBasicInfo>>(){

			@Override
			public void onCallback(List<ProductBasicInfo> pCallbackValue) {
				// TODO Auto-generated method stub
				productListActivity.productList=pCallbackValue;
				initView();
			}
			
		}, new Callback<Exception>(){

			@Override
			public void onCallback(Exception pCallbackValue) {
				// TODO Auto-generated method stub
				pCallbackValue.printStackTrace();
				Toast.makeText(productListActivity, "请求数据出错，请稍后重试。", Toast.LENGTH_SHORT).show();
			}
			
		}, true, "loading...");
	}

	class ProductListAdapter extends BaseAdapter{
		List<ProductBasicInfo> productList;
		LayoutInflater layoutInflater; 
		ProductListAdapter(List<ProductBasicInfo> productList){
			this.productList=productList;
			System.out.println("##############################"+this.productList.size()+this.productList.get(0).getItemName());
			this.layoutInflater=LayoutInflater.from(productListActivity);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.productList.size();
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
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			System.out.println("HHHHHHHHHHHHHHHH");
			if(arg1==null||arg1.getTag()==null){
				System.out.println("HHHHHHHHHHHHHHHH");
				arg1=layoutInflater.inflate(R.layout.product_list_item, null);
				ProductHolder holder=new ProductHolder();
				holder.imageView=(ImageView)arg1.findViewById(R.id.product_item_image);
				holder.itemName=(TextView)arg1.findViewById(R.id.product_item_name);
				holder.adWord=(TextView)arg1.findViewById(R.id.product_item_adword);
				holder.hjPrice=(TextView)arg1.findViewById(R.id.product_item_hjPrice);
				holder.mPrice=(TextView)arg1.findViewById(R.id.product_item_martPrice);
				holder.good=(TextView)arg1.findViewById(R.id.product_item_good);
				holder.commentNum=(TextView)arg1.findViewById(R.id.product_item_commentNumber);
				
				ProductBasicInfo tmp=this.productList.get(arg0);
				ImageLoader.getInstance().displayImage(tmp.getPicUrl(), holder.imageView);
				holder.itemName.setText(tmp.getItemName()+"(内部测试!)");
				holder.hjPrice.setText("￥"+tmp.getHjPrice());
				SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(" ￥"+tmp.getMarketPrice());
				localSpannableStringBuilder.setSpan(new StrikethroughSpan(), 1, localSpannableStringBuilder.length(), 33);
				holder.mPrice.setText(localSpannableStringBuilder);
				holder.mPrice.setVisibility(View.VISIBLE);
				holder.good.setText("好评率："+tmp.getRating()*100+"%");
				holder.commentNum.setText("("+tmp.getCommentNum()+"人)");
				holder.skuid=tmp.getItemCode();
				arg1.setTag(holder);
			}
			return arg1;
		}
		
	}
	
	class ProductHolder{
		String skuid;
		ImageView imageView;
		TextView itemName;
		TextView adWord;
		TextView hjPrice;
		TextView mPrice;
		TextView good;
		TextView commentNum;
	}
	
}
