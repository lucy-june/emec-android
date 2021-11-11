package com.adc.huijin.ui;

import com.adc.huijin.R;
import com.adc.huijin.bean.GoodsItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsDetailActivity extends Activity implements OnClickListener{
	private TextView goodsName;
	private TextView goodsPrice;
	private TextView goodsDescription;
	private Button addToCart;
	private Button buy;
	private ImageView goodsImage;
	private Button goodsPlus;
	private Button goodsMinus;
	private EditText goodsNum;
	private TextView goodsMoney;
	private int num;
	private double money;
	private GoodsItem goodsItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_detail);
		findViewsByIds();
		initView();
	}
	
	protected void findViewsByIds() {
		this.goodsName=(TextView)this.findViewById(R.id.goodsname);
		this.goodsDescription=(TextView) this.findViewById(R.id.goodsdescription);
		this.goodsPrice=(TextView) this.findViewById(R.id.goodsprice);
		this.addToCart=(Button) this.findViewById(R.id.addtocart);
		this.buy=(Button) this.findViewById(R.id.buy);
		this.goodsImage=(ImageView) this.findViewById(R.id.goodsimage);
		this.goodsPlus=(Button) this.findViewById(R.id.goodsplus);
		this.goodsMinus=(Button) this.findViewById(R.id.goodsminus);
		this.goodsNum=(EditText) this.findViewById(R.id.goodsnum);
		this.goodsMoney=(TextView) this.findViewById(R.id.goodsmoney);
		this.goodsItem=(GoodsItem)this.getIntent().getSerializableExtra("goodsitem");
		num=1;
		this.money=this.goodsItem.price;
	}
	
	protected void initView(){
		this.addToCart.setOnClickListener(this);
		this.buy.setOnClickListener(this);
		this.goodsPlus.setOnClickListener(this);
		this.goodsMinus.setOnClickListener(this);
		this.goodsDescription.setText(this.goodsItem.description);
		this.goodsName.setText(this.goodsItem.name);
		this.goodsPrice.setText(this.goodsItem.price+"");
		//Bitmap bitmap=HttpImageLoader.getHttpBitmap(this.goodsItem.imageUrl);
		ImageLoader.getInstance().displayImage(this.goodsItem.imageUrl, this.goodsImage);
		//this.goodsImage.setImageBitmap(bitmap);
		this.goodsNum.setText("1");
		this.goodsMoney.setText(this.money+"");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.addtocart:
			Toast.makeText(this, "Add to Cart is not implemented now!", 2).show();
			break;
		case R.id.buy:
			Toast.makeText(this, "Buy is not implemented now!", 2).show();
			break;
		case R.id.goodsplus:
			this.num++;
			this.goodsNum.setText(this.num+"");
			this.money=this.goodsItem.price*num;
			this.goodsMoney.setText(this.money+"");
			break;
		case R.id.goodsminus:
			if(this.num>=2){
				this.num--;
				this.money=this.goodsItem.price*this.num;
				this.goodsNum.setText(this.num+"");
				this.goodsMoney.setText(this.money+"");
			}
			break;
		}
	}
}
