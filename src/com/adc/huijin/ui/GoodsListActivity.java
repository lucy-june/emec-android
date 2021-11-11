package com.adc.huijin.ui;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.adc.huijin.R;
import com.adc.huijin.adapter.GoodsListAdapter;
import com.adc.huijin.bean.GoodsResult;
import com.adc.huijin.ui.base.BaseActivity;
import com.adc.huijin.utils.HttpGetGoods;
import com.adc.huijin.AppException;
/**
 * 
 * @author kcq
 *
 */
public class GoodsListActivity extends BaseActivity {

	private ListView goods_listview;
	private LayoutInflater layoutInflater;
	GoodsListAdapter ga;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_goods);
		//this.ga=new GoodsListAdapter(GoodsListActivity.this,"http://192.168.0.189:3000/goods.html");
		//findViewById();
		//initView();
		initData();
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				ga=new GoodsListAdapter(GoodsListActivity.this,(GoodsResult)msg.obj);
				System.out.println("HHHHHHHHHHHHHHHHH");
				findViewById();
				initView();
			} else if (msg.what == -1) {
				Toast.makeText(GoodsListActivity.this, "返回结果为空！", 1).show();
			}else{
				Toast.makeText(GoodsListActivity.this, "请检查网络连接！", 1).show();
			}
		}
	};
	
	private void initData() {

		new Thread() {
			public void run() {
				Message msg = new Message();
				GoodsResult goodsResult;
				try {
					goodsResult = HttpGetGoods.getResult("http://192.168.0.189:3000/goods.html");
					if (goodsResult.resultCount > 0) {
						msg.what = 1;
						msg.obj = goodsResult;
					} else {
						msg.what = -1;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what = -2;
					msg.obj = e;
				} 		
				mHandler.sendMessage(msg);
			}
		}.start();
	}
	

	@Override
	protected void findViewById() {
		goods_listview=(ListView)this.findViewById(R.id.goods_listview);
		//final GoodsListAdapter ga=new GoodsListAdapter(GoodsListActivity.this,"http://192.168.0.189:3000/goods1.html");
		goods_listview.setAdapter(ga);
		goods_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view, int parent,
					long id) {
				//Toast.makeText(GoodsListActivity.this, "你点击了第"+id+"项", 1).show();
				//Intent intent=new Intent(GoodsListActivity.this,GoodsDetailActivity.class);
				//Bundle bundle=new Bundle();
				//bundle.putSerializable("goodsitem",ga.getGoodsResult().results.get((int)id));
				//intent.putExtras(bundle);
				//test new detail activity
				Intent intent1=new Intent(GoodsListActivity.this,ProductDetailActivity.class);
				startActivity(intent1);
				
			}
		});
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

}

