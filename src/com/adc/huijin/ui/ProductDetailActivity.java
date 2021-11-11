package com.adc.huijin.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adc.huijin.adapter.ProductDetailPagerAdapter;
import com.adc.huijin.bean.Product;
import com.adc.huijin.bean.ProductRecommend;
import com.adc.huijin.bean.ProductSKC;
import com.adc.huijin.task.Callback;
import com.adc.huijin.ui.base.BaseActivity;
import com.adc.huijin.utils.ApiClient;
import com.adc.huijin.utils.HttpsRequestUtils;
import com.adc.huijin.utils.JsonUtils;
import com.adc.huijin.R;
import com.nostra13.universalimageloader.core.ImageLoader;

@SuppressLint("InflateParams")
public class ProductDetailActivity extends BaseActivity{

	private ProductDetailActivity productDetailActivity;
	private TopImageHolder mTopImageHolder;
	private ProductInfoHolder mProductInfoHolder;
	private StockColorSizeHolder mStockColorSizeHolder;
	private CommentServerHolder mCommentServerHolder;
	private RecommendHolder mRecommendHolder;
	private Product product;
	private ProductSKC selectedSKC;
	private List<ProductRecommend> recommends;
	ImageView [] views;
	
	String SKUID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		productDetailActivity=ProductDetailActivity.this;
		setContentView(R.layout.activity_product_detail);
		this.SKUID=this.getIntent().getStringExtra("SKUID");
		//findViewById();
		initData();
		getRecommends();
	}
	
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		mTopImageHolder=new TopImageHolder(this.findViewById(R.id.top_layout));
		mProductInfoHolder=new ProductInfoHolder(this.findViewById(R.id.product_detail_information_layout));
		mStockColorSizeHolder=new StockColorSizeHolder(this.findViewById(R.id.product_color_size_layout));
		mCommentServerHolder=new CommentServerHolder(this.findViewById(R.id.product_server_discuss_im));
		
	}
	
	void getViews(){
		mTopImageHolder=new TopImageHolder(this.findViewById(R.id.top_layout));
		mProductInfoHolder=new ProductInfoHolder(this.findViewById(R.id.product_detail_information_layout));
		mStockColorSizeHolder=new StockColorSizeHolder(this.findViewById(R.id.product_color_size_layout));
		mCommentServerHolder=new CommentServerHolder(this.findViewById(R.id.product_server_discuss_im));
		mRecommendHolder=new RecommendHolder(this.findViewById(R.id.product_guess_you_love));
	}
	
	@Override
	protected void initView() {
		
	}
	
	void getRecommends(){
		productDetailActivity.doAsync(new Callable<List<ProductRecommend>>(){

			@Override
			public List<ProductRecommend> call() throws Exception {
				// TODO Auto-generated method stub
				List<ProductRecommend> list=new ArrayList<ProductRecommend>();
				list.add(new ProductRecommend(1, 199, "http://g.search.alicdn.com/img/i4/896627152/TB2fR6dXVXXXXbBXXXXXXXXXXXX_!!896627152.jpg_120x120.jpg", "测试一"));
				list.add(new ProductRecommend(2, 199, "http://g.search.alicdn.com/img/i4/1789113670/TB2t5L7XVXXXXaUXpXXXXXXXXXX_!!1789113670.jpg_120x120.jpg", "测试二"));
				list.add(new ProductRecommend(3, 199, "http://g.search3.alicdn.com/img/i3/1689085390/TB2IxvkXVXXXXbfXXXXXXXXXXXX_!!1689085390.gif_120x120.jpg", "测试三"));
				list.add(new ProductRecommend(4, 199, "http://g.search3.alicdn.com/img/i3/2030650737/T23Zg3XXFbXXXXXXXX_!!2030650737.jpg_120x120.jpg", "测试四"));
				list.add(new ProductRecommend(5, 199, "http://g.search2.alicdn.com/img/i2/T1ECmGFGtXXXXXXXXX_!!0-item_pic.jpg_120x120.jpg", "测试五"));
				list.add(new ProductRecommend(6, 199, "http://g.search2.alicdn.com/img/i2/201781634/T2IjfTXtNaXXXXXXXX_!!201781634.jpg_120x120.jpg", "测试六"));
				return list;
			}
			
		}, new Callback<List<ProductRecommend>>(){

			@Override
			public void onCallback(List<ProductRecommend> pCallbackValue) {
				// TODO Auto-generated method stub
				productDetailActivity.recommends=pCallbackValue;
				mRecommendHolder.show();
			}
			
		}, new Callback<Exception>(){

			@Override
			public void onCallback(Exception pCallbackValue) {
				// TODO Auto-generated method stub
				
			}
			
		}, true, "loading...");
	}
	
	protected void initData() {
		// TODO Auto-generated method stub
		productDetailActivity.doAsync(new Callable<Product>(){

			@Override
			public Product call() throws Exception {
				// TODO Auto-generated method stub
				//findViewById();
				//Thread.sleep(10000);
				getViews();
				
				if(productDetailActivity.SKUID==null||productDetailActivity.SKUID.isEmpty()){
					productDetailActivity.SKUID="1518511";
				}
				String s5 =HttpsRequestUtils.sendPost("https://202.120.40.140:3001/app/item/display","ITEMCODE="+productDetailActivity.SKUID);
				Product product=JsonUtils.parseProduct(s5);
				return product;
			}
			
		}, new Callback<Product>(){

			@Override
			public void onCallback(Product pCallbackValue) {
				// TODO Auto-generated method stub
				ProductDetailActivity.this.product=pCallbackValue;
				mTopImageHolder.show();
				mProductInfoHolder.show();
				mStockColorSizeHolder.show();
				mCommentServerHolder.show();
				
			}
			
		}, new Callback<Exception>(){

			@Override
			public void onCallback(Exception pCallbackValue) {
				// TODO Auto-generated method stub
				pCallbackValue.printStackTrace();
				Toast.makeText(productDetailActivity, "请求数据出错", Toast.LENGTH_SHORT).show();
			}
			
		}, true, "loading...");
	}
	private class TopImageHolder{
		ImageView mCollectBtn;
	    int mCurrenIndex = 0;
	    ImageView mDefaultImageView;
	    TextView mImageIndex;
	    int mImageSize = 0;
	    PriceHolder mPriceHolder;
	    ViewPager mViewPager;
	    
	    TopImageHolder(View arg2)
	    {
	      this.mViewPager = ((ViewPager)arg2.findViewById(R.id.image_pager));
	      this.mCollectBtn = ((ImageView)arg2.findViewById(R.id.product_collect_button));
	      this.mDefaultImageView = ((ImageView)arg2.findViewById(R.id.img_1));
	      //this.mDefaultImageView.setImageDrawable(new ExceptionDrawable(ProductDetailNewLastActivity.this, ProductDetailNewLastActivity.this.getString(2131361872)));
	      this.mImageIndex = ((TextView)arg2.findViewById(R.id.txt_1));
	      ((View)this.mCollectBtn.getParent()).setOnClickListener(new View.OnClickListener()
	      {
	        public void onClick(View paramView)
	        {
	          TopImageHolder.this.mCollectBtn.performClick();
	        }
	      });
	      this.mPriceHolder = new PriceHolder(arg2.findViewById(R.id.product_price_layout));
	    }
	    
	    void initCollectButton(){
	    	//判断是否登录且收藏
	    	if(ProductDetailActivity.this.product.isFavourite()){
	    		this.mCollectBtn.setSelected(true);
	    	}
	    	
	    	//监听点击事件
	    	this.mCollectBtn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
				}
	    		
	    	});
	    }
	    
	    void show(){
	    	String[] imageurls=ProductDetailActivity.this.product.getPicsUrl();
	    	this.mImageSize=imageurls.length;
	    	if(this.mImageSize>0){
	    		this.mImageIndex.setText("1/"+this.mImageSize);
	    	}else{
	    		this.mImageIndex.setText("-/-");
	    	}	    	
	    	views=new ImageView[mImageSize];
	    	
	    	for(int i=0;i<mImageSize;i++){
	    		ImageView test=new ImageView(ProductDetailActivity.this);
	    		views[i]=test;
	    		ImageLoader.getInstance().displayImage(imageurls[i], views[i]);
	    	}
	    	
	    	this.mViewPager.setAdapter(new ProductDetailPagerAdapter(imageurls,views));
	    	this.mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onPageSelected(int arg0) {
					// TODO Auto-generated method stub
					TopImageHolder.this.mCurrenIndex=arg0;
					TopImageHolder.this.mImageIndex.setText((arg0+1)+"/"+TopImageHolder.this.mImageSize);
				}
	    		
	    	});
	    	
	    	initCollectButton();
	    	this.mPriceHolder.show();
	    }
	    
	}
	
	private class PriceHolder{
    	ImageView jiangImage;//降价的图标
        TextView mHjPrice;
        TextView mMprice;
        View mVJZIconContainer;//图标的容器
        ImageView vipImage;//vip专享图标
        ImageView zengImage;//有赠品图标

        private PriceHolder(View arg2)
        {
          this.mHjPrice = ((TextView)arg2.findViewById(R.id.product_hjPrice));
          this.mMprice = ((TextView)arg2.findViewById(R.id.product_marketPrice));
          this.mVJZIconContainer = arg2.findViewById(R.id.product_price_icon_layout);
          this.vipImage = ((ImageView)arg2.findViewById(R.id.product_item_detail_vip_icon));
          this.jiangImage = ((ImageView)arg2.findViewById(R.id.product_item_detail_jiang_icon));
          this.zengImage = ((ImageView)arg2.findViewById(R.id.product_item_detail_zeng_icon));
        }
        
        void show()
        {
          showPrice();
          showJXZIcon();
        }
        
        void showPrice(){
        	String str=ProductDetailActivity.this.getString(R.string.product_hj_price);
        	String hjPrice=str+ProductDetailActivity.this.product.getHjPrice();
        	SpannableStringBuilder ssb=new SpannableStringBuilder(hjPrice);
        	int i=str.length()-1;
        	int j=ssb.length();
        	ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);
        	ssb.setSpan(new RelativeSizeSpan(1.6F), i, j, 33);
        	ssb.setSpan(new StyleSpan(1), i, j, 33);
        	ssb.setSpan(fcs, i, j, 33);
        	System.out.println("*********************"+ssb);
        	this.mHjPrice.setText(ssb);
        	str=ProductDetailActivity.this.getString(R.string.product_price_label);
        	SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(" " + str + ProductDetailActivity.this.product.getmPrice());
            localSpannableStringBuilder.setSpan(new ForegroundColorSpan(-7303024), 0, localSpannableStringBuilder.length(), 17);
            localSpannableStringBuilder.setSpan(new StrikethroughSpan(), 1, localSpannableStringBuilder.length(), 33);
            localSpannableStringBuilder.insert(0, "参考价：");
            this.mMprice.setText(localSpannableStringBuilder);
            this.mMprice.setVisibility(View.VISIBLE);
        }
        
        //显示价格的图标降享赠
        void showJXZIcon(){
        	
        }
    }
	
	
	 class ProductInfoHolder{
		 TextView mAdWord;
		 View mInfoView;
		 TextView mName;
		 PromotionHolder mPromotionHolder;
		 View mRootView;
		 
		 ProductInfoHolder(View view){
			 this.mRootView = view;
			 this.mInfoView = view.findViewById(R.id.l_layout_1);
		     this.mName = ((TextView)view.findViewById(R.id.product_name));
		     this.mAdWord = ((TextView)view.findViewById(R.id.product_adword));
		     this.mPromotionHolder = new PromotionHolder(view.findViewById(R.id.l_layout_2));
		     
		     this.mInfoView.setOnClickListener(new View.OnClickListener()
		      {
		        public void onClick(View paramView)
		        {
		        	//跳转商品详细介绍Activity（内嵌网页）
		        	/*
		          Intent localIntent = new Intent(ProductDetailNewLastActivity.this, ProductDetailInfoActivity.class);
		          Bundle localBundle = new Bundle();
		          localBundle.putSerializable("product", ProductDetailNewLastActivity.this.product);
		          localIntent.putExtras(localBundle);
		          ProductDetailNewLastActivity.this.startActivity(localIntent);
		          */
		        	
		        	Toast.makeText(ProductDetailActivity.this,"应该跳转", 1).show();
		        }
		      });
		 }
		 
		 public void show(){
			 showNameAndAdWord();
			 mPromotionHolder.show();
		 }
		 void showNameAndAdWord(){
			 String name=ProductDetailActivity.this.product.getName();
			 String adWord=ProductDetailActivity.this.product.getDescription();
			 this.mName.setText(name);
			 if (TextUtils.isEmpty(adWord)){
		        this.mAdWord.setVisibility(View.GONE);
		        return;
		     }
			 SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(adWord);
			 localSpannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0, localSpannableStringBuilder.length(), 17);
	         //localSpannableString.setSpan(new ForegroundColorSpan(-7303024), 0, localSpannableString.length(), 33);
	         //this.mAdWord.setMovementMethod(LinkMovementMethod.getInstance());
	         this.mAdWord.setText(localSpannableStringBuilder);
		 }
		 
	 }
	 
	 private class PromotionHolder{
		 //ImageView mArrow;
		 LinearLayout mProIconsLayout;
		 TextView mPromotion;
		 View mRootView;
		 View mScrollView;
		 
		 PromotionHolder(View view){
			 this.mRootView = view;
		     this.mPromotion = ((TextView)view.findViewById(R.id.product_detail_promotion_info));
		     //this.mArrow = ((ImageView)view.findViewById(R.id.img_5));
		     this.mScrollView = view.findViewById(R.id.hScrollView);
		     this.mProIconsLayout = ((LinearLayout)view.findViewById(R.id.l_layout_1));
		     
		     this.mRootView.setOnClickListener(new View.OnClickListener()
		      {
		        public void onClick(View paramView)
		        {
		        	/*
		          com.jingdong.common.constant.Constants.packMainProdId = ProductDetailNewLastActivity.this.id;
		          Intent localIntent = new Intent(ProductDetailNewLastActivity.this, ProductPacksListActivity.class);
		          localIntent.putExtra("source", ProductDetailNewLastActivity.this.mSource);
		          ProductDetailNewLastActivity.this.startActivity(localIntent);
		          NewJLogUtil.onJMAEvent(ProductDetailNewLastActivity.this, "JD_Promote_ProductDetail", "");
		          */
		        	Toast.makeText(ProductDetailActivity.this,"应该跳转到促销说明", 1).show();
		        }
		      });
		 }
		 
		 void show(){
			 this.mPromotion.setText("促销信息");
			 String [] proms=ProductDetailActivity.this.product.getPromotions();
			 if(proms==null||proms.length<=0){
				 this.mRootView.setVisibility(View.GONE);
				 return;
			 }
			 showIcons(proms);
		 }
		 void showIcons(String[] proms){
			 this.mProIconsLayout.removeAllViews();
			 for(int i=0;i<proms.length;i++){
				 View localView=LayoutInflater.from(ProductDetailActivity.this).inflate(R.layout.product_detail_pro_icon, null);
				 ((TextView)localView.findViewById(R.id.txt_1)).setText(proms[i]);
			     this.mProIconsLayout.addView(localView);
			 }
		 }
		 
	 }
	 
	 //skc 数量选择等
	 class StockColorSizeHolder{
		 boolean isStrecth = false;
		 private LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
		 View mBottomView;
		 LinearLayout mColorLayout;
		 View mColorSizeView;
		 ImageButton mImageButton;
		 View mRootView;
		 TextView mBuyNum;
		 TextView mSizeColor;
		 TextView mSizeText;
		 TextView mNumText;
		 LinearLayout mSizeLayout;
		 TextView mStock;
		 TextView mStockTextView;
		 View mStockView;
		 View mTopView;
		 
		 ImageButton mNumAddButton;
		 ImageButton mNumReduceButton;
		 TextView mNumTextView;
		 TextView mPriceView;
		 
		 //Button[] colorsButtons;
		 //Button[] sizesButtons;
		 Map<String,Button> colorButtonMap=new HashMap<String,Button>();
	     Map<String,Button> sizeButtonMap=new HashMap<String,Button>();
	     List<String> colors;
	     List<String> sizes;
		 LinearLayout optionsContainer = null;
		 
		 
		 private String productColor="";
		 private String productSize="";
		 private int productNum=1;
		 
		 StockColorSizeHolder(View localObject){
			 this.mRootView = localObject;
		     this.mTopView = localObject.findViewById(R.id.r_layout_1);
		     //this.mBuyNum = ((TextView)localObject.findViewById(R.id.l_layout_3).findViewById(R.id.txt_2));
		     this.mStockView = localObject.findViewById(R.id.l_layout_1);
		     this.mStock = ((TextView)this.mStockView.findViewById(R.id.txt_2));
		     this.mColorSizeView = localObject.findViewById(R.id.l_layout_2);
		     this.mSizeColor = ((TextView)this.mColorSizeView.findViewById(R.id.txt_2));
		     this.mSizeText = ((TextView)this.mColorSizeView.findViewById(R.id.txt_3));
		     this.mNumText = ((TextView)this.mColorSizeView.findViewById(R.id.txt_4));
		     this.mImageButton = ((ImageButton)this.mTopView.findViewById(R.id.imgBtn_1));
		     this.mBottomView = localObject.findViewById(R.id.r_layout_2);
		     //this.mStockTextView = ((TextView)this.mBottomView.findViewById(R.id.));
		     this.mColorLayout = ((LinearLayout)this.mBottomView.findViewById(R.id.product_options_color));
		     this.mSizeLayout = ((LinearLayout)this.mBottomView.findViewById(R.id.product_options_size));
		     this.mNumAddButton=(ImageButton)this.mBottomView.findViewById(R.id.product_num_add);
		     this.mNumReduceButton=(ImageButton)this.mBottomView.findViewById(R.id.product_num_reduce);
		     this.mNumTextView=(TextView)this.mBottomView.findViewById(R.id.product_et_num);
		     this.mPriceView=(TextView)this.mBottomView.findViewById(R.id.product_price);
		     handleFoldAndUnfold();
		 }
		 
		 //处理选择skc是否展开。
		 void handleFoldAndUnfold(){
			 this.mTopView.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(StockColorSizeHolder.this.isStrecth){
						StockColorSizeHolder.this.mBottomView.setVisibility(View.GONE);
						StockColorSizeHolder.this.mImageButton.setBackgroundResource(R.drawable.pdn_icon_arrow_down);
						StockColorSizeHolder.this.isStrecth=false;
					}else{
						StockColorSizeHolder.this.mBottomView.setVisibility(View.VISIBLE);
						StockColorSizeHolder.this.mImageButton.setBackgroundResource(R.drawable.pdn_icon_arrow_up);
						StockColorSizeHolder.this.isStrecth=true;
					}
				}
				 
			 });
		 }
		 
		 void show(){
			 ProductSKC cheapest=ProductDetailActivity.this.product.getFirstSKC();
			 this.productColor=cheapest.getSkcColor();
			 this.productSize=cheapest.getSkcSize();
			 this.productNum=1;
			 colors=ProductDetailActivity.this.product.getAllColors();
		     sizes=ProductDetailActivity.this.product.getAllSizes();
		     //System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+sizes.size()+sizes.get(0));
			 showColorAndSize();
			 showStock();
			 showBottomInfo();
		 }
		 
		 //显示选择区
		 private void showBottomInfo(){
			 DisplayMetrics metrics = new DisplayMetrics();
			 ProductDetailActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
			 int i=metrics.widthPixels;
			 int j = ProductDetailActivity.this.getResources().getDimensionPixelOffset(R.dimen.pdn_top_space_content_height);
			 int k = ProductDetailActivity.this.getResources().getDimensionPixelOffset(R.dimen.pdn_card_space_height) / 2;
			 int m = i - 2 * ProductDetailActivity.this.getResources().getDimensionPixelOffset(R.dimen.pdn_left_right_space_width) - k * 3;
			 LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
			 localLayoutParams.rightMargin = k;
		     localLayoutParams.topMargin = j;
		     
		     		     
		     //颜色选项布局
		     if(colors.size()<=0){
		    	 this.mColorLayout.setVisibility(View.GONE);
		    	 this.mSizeColor.setVisibility(View.GONE);
		     }else{
		    	 this.mColorLayout.setVisibility(View.VISIBLE);
		    	 //removeAllLinearLayout(this.mColorLayout);
		    	 LinearLayout colorOption = new LinearLayout(ProductDetailActivity.this);
		    	 colorOption.setOrientation(1);
		         this.mColorLayout.addView(colorOption, new LinearLayout.LayoutParams(-1, -2));
		         for(int n=0;n<colors.size();n++){
		        	 Button option=new Button(ProductDetailActivity.this);
		        	 option.setText(colors.get(n));
		        	 option.setTextColor(ProductDetailActivity.this.getResources().getColor(R.color.pdn_balck_title));
		        	 option.setTextSize(0, ProductDetailActivity.this.getResources().getDimensionPixelSize(R.dimen.pdn_txt_size_middle));
		        	 option.setGravity(17);
		        	 option.setEllipsize(TextUtils.TruncateAt.END);
		        	 option.setSingleLine(true);
		        	 option.setWidth(m / 3);
		        	 option.setBackgroundResource(R.drawable.pdn_btn_product_bg);
		        	 if(colors.get(n).equals(this.productColor)){
		        		 option.setSelected(true);
		        	 }
		        	 option.setOnClickListener(colorListener);
		        	 //colorsButtons[n]=option;
		        	 colorButtonMap.put(colors.get(n), option);
		        	 
		        	 int tmp=(n+1)%3;
		        	 if(tmp==1){
		        		 optionsContainer=new LinearLayout(ProductDetailActivity.this);	
		        		 optionsContainer.setOrientation(LinearLayout.HORIZONTAL);//水平布局
		        	 }
		        	 optionsContainer.addView(option);
		        	 if(tmp==0||n==colors.size()-1){
		        		 colorOption.addView(optionsContainer);
		        	 }
		         }
		     }
		     //尺寸选项布局
		     if(sizes.size()<=0){
		    	 this.mSizeLayout.setVisibility(View.GONE);
		    	 this.mSizeText.setVisibility(View.GONE);
		     }else{
		    	 this.mSizeLayout.setVisibility(View.VISIBLE);
		    	 //removeAllLinearLayout(this.mColorLayout);
		    	 LinearLayout sizeOption = new LinearLayout(ProductDetailActivity.this);
		    	 sizeOption.setOrientation(1);
		         this.mSizeLayout.addView(sizeOption, new LinearLayout.LayoutParams(-1, -2));
		         for(int n=0;n<sizes.size();n++){
		        	 Button option=new Button(ProductDetailActivity.this);
		        	 option.setText(sizes.get(n));
		        	 option.setTextColor(ProductDetailActivity.this.getResources().getColor(R.color.pdn_balck_title));
		        	 option.setTextSize(0, ProductDetailActivity.this.getResources().getDimensionPixelSize(R.dimen.pdn_txt_size_middle));
		        	 option.setGravity(17);
		        	 option.setEllipsize(TextUtils.TruncateAt.END);
		        	 option.setSingleLine(true);
		        	 option.setWidth(m / 3);
		        	 option.setBackgroundResource(R.drawable.pdn_btn_product_bg);
		        	 if(sizes.get(n).equals(this.productSize)){
		        		 option.setSelected(true);
		        	 }
		        	 option.setOnClickListener(sizeListener);
		        	 //sizesButtons[n]=option;
		        	 sizeButtonMap.put(sizes.get(n), option);
		        	 
		        	 int tmp=(n+1)%3;
		        	 if(tmp==1){
		        		 optionsContainer=new LinearLayout(ProductDetailActivity.this);	
		        		 optionsContainer.setOrientation(LinearLayout.HORIZONTAL);//水平布局
		        	 }
		        	 optionsContainer.addView(option);
		        	 if(tmp==0||n==sizes.size()-1){
		        		 sizeOption.addView(optionsContainer);
		        	 }
		         }
		     }
		     showNumChange();
		 }
		 
		 View.OnClickListener colorListener=new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				List<String> availableSizes;
				if(StockColorSizeHolder.this.productColor.equals(((Button)arg0).getText())){
					colorButtonMap.get(StockColorSizeHolder.this.productColor).setSelected(false);
					StockColorSizeHolder.this.productColor="";
					Iterator<Entry<String, Button>> iter = sizeButtonMap.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry<String,Button> entry = (Map.Entry<String,Button>) iter.next();
						Button val = entry.getValue();
						val.setEnabled(true);
					}
				}else{
					if(!StockColorSizeHolder.this.productColor.isEmpty()){
						colorButtonMap.get(StockColorSizeHolder.this.productColor).setSelected(false);
					}
					
					((Button)arg0).setSelected(true);
					StockColorSizeHolder.this.productColor=((Button)arg0).getText().toString();
					if(StockColorSizeHolder.this.sizes.size()>0){
						availableSizes=ProductDetailActivity.this.product.getSizes(StockColorSizeHolder.this.productColor);
						Iterator<Entry<String, Button>> iter = sizeButtonMap.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry<String,Button> entry = (Map.Entry<String,Button>) iter.next();
							String key = entry.getKey();
							Button val = entry.getValue();
							if(availableSizes.contains(key)){
								val.setEnabled(true);
							}else{
								if(val.isSelected()){
									StockColorSizeHolder.this.productSize="";
									val.setSelected(false);
								}
								val.setEnabled(false);
							}
							
						}
					}
					
				}
				
				checkSKC();
			}
		 };
		 void checkSKC(){
			 if(this.productColor.isEmpty()&&this.colors.size()>0||this.productSize.isEmpty()&&this.sizes.size()>0){
				 ProductDetailActivity.this.selectedSKC=null;
				 //更新显示
				 showColorAndSize();
				 return;
			 }
			 ProductDetailActivity.this.selectedSKC=ProductDetailActivity.this.product.getProductSKC(this.productColor, this.productSize);
			 //更新显示
			 showColorAndSize();
			 
		 }
		 View.OnClickListener sizeListener=new View.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					List<String> availableColors;
					if(StockColorSizeHolder.this.productSize.equals(((Button)arg0).getText())){
						sizeButtonMap.get(StockColorSizeHolder.this.productSize).setSelected(false);
						StockColorSizeHolder.this.productSize="";
						Iterator<Entry<String, Button>> iter = colorButtonMap.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry<String,Button> entry = (Map.Entry<String,Button>) iter.next();
							Button val = entry.getValue();
							val.setEnabled(true);
						}
					}else{
						if(!StockColorSizeHolder.this.productSize.isEmpty()){
							sizeButtonMap.get(StockColorSizeHolder.this.productSize).setSelected(false);
						}
						
						((Button)arg0).setSelected(true);
						StockColorSizeHolder.this.productSize=((Button)arg0).getText().toString();
						if(StockColorSizeHolder.this.colors.size()>0){
							availableColors=ProductDetailActivity.this.product.getColors(StockColorSizeHolder.this.productSize);
							Iterator<Entry<String, Button>> iter = colorButtonMap.entrySet().iterator();
							while (iter.hasNext()) {
								Map.Entry<String,Button> entry = (Map.Entry<String,Button>) iter.next();
								String key = entry.getKey();
								Button val = entry.getValue();
								if(availableColors.contains(key)){
									val.setEnabled(true);
								}else{
									if(val.isSelected()){
										StockColorSizeHolder.this.productColor="";
										val.setSelected(false);
									}
									val.setEnabled(false);
								}
								
							}
						}
						
					}
					
					checkSKC();
				}
		 };
		 
		 void showNumChange(){
			 this.mNumAddButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					StockColorSizeHolder.this.productNum++;
					if(StockColorSizeHolder.this.productNum>=2){
						StockColorSizeHolder.this.mNumReduceButton.setEnabled(true);
					}
					StockColorSizeHolder.this.mNumTextView.setText(StockColorSizeHolder.this.productNum+"");
					StockColorSizeHolder.this.mPriceView.setText("￥"+StockColorSizeHolder.this.productNum*ProductDetailActivity.this.product.getHjPrice());
					StockColorSizeHolder.this.showColorAndSize();
				}
				 
			 });
			 
			 this.mNumReduceButton.setEnabled(false);
			 this.mNumReduceButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					StockColorSizeHolder.this.productNum--;
					if(StockColorSizeHolder.this.productNum<=1){
						StockColorSizeHolder.this.mNumReduceButton.setEnabled(false);
					}
						
					StockColorSizeHolder.this.mNumTextView.setText(StockColorSizeHolder.this.productNum+"");
					StockColorSizeHolder.this.mPriceView.setText("￥"+StockColorSizeHolder.this.productNum*ProductDetailActivity.this.product.getHjPrice());
					StockColorSizeHolder.this.showColorAndSize();
					
				}
			});
			 this.mNumTextView.setText(this.productNum+"");
			 this.mPriceView.setText("￥"+this.productNum*ProductDetailActivity.this.product.getHjPrice());
		 }
		 
		 //显示是否有库存，是否精确到每个汇金店待定！
		 void showStock(){
			 String str="现货";
			 SpannableString localSpannableString = new SpannableString(str);
		     localSpannableString.setSpan(new ForegroundColorSpan(-65536), 0, 2, 34);
		     localSpannableString.setSpan(new RelativeSizeSpan(1.2F), 0, 2, 34);
		     this.mStock.setText(localSpannableString);
		 }
		 
		 //显示颜色尺寸和数量
		 void showColorAndSize(){
			 String cs="";
			 if(this.productColor!=null&&!this.productColor.isEmpty()){
				 cs+="颜色 - " + this.productColor + "   ";
			 }else{
				 cs+="颜色 - " + "-" + "   ";
			 }
			 this.mSizeColor.setText(cs);
			 if(this.productSize!=null&&!this.productSize.isEmpty()){
				 cs="尺寸 - " + this.productSize + "   ";
			 }else{
				 cs="尺寸 - " + "-" + "   ";
			 }
			 this.mSizeText.setText(cs);
			 cs="数量 - "+this.productNum;
			 this.mNumText.setText(cs);
		 }
		 
		 void showTopInfo(){
		     showStock();
		     showColorAndSize();
		 }
		 
	 }
	
	 //显示评价等信息。
	 class CommentServerHolder{
		 View mArrowComment;
		 View mArrowConsulation;
		 TextView mCommentCountAndRate;
		 View mCommentView;
		 View mConsulationView;
		 TextView mConsultCount;
		 View mIMView;
		 RatingBar mRatingBar;
		 View mStartIm;
		 View mToShop;
		 ServerIconHolder mServerIconHolder;
		 
		 CommentServerHolder(View localObject){
			 this.mCommentView = localObject.findViewById(R.id.product_detail_evaluate_advice_layout);
		     this.mArrowComment = this.mCommentView.findViewById(R.id.img_5);
		     this.mRatingBar = ((RatingBar)this.mCommentView.findViewById(R.id.product_item_score));
		     this.mCommentCountAndRate = ((TextView)this.mCommentView.findViewById(R.id.product_item_commentNumber));
		     this.mConsulationView = localObject.findViewById(R.id.product_detail_show_advice_layout);
		     this.mConsultCount = ((TextView)this.mConsulationView.findViewById(R.id.product_consultation_count_text_new));
		     this.mArrowConsulation = this.mConsulationView.findViewById(R.id.img_5);
		     this.mServerIconHolder = new ServerIconHolder(localObject.findViewById(R.id.product_detail_server_info_layout));
		     this.mIMView = localObject.findViewById(R.id.product_detail_im_layout);
		     this.mToShop = this.mIMView.findViewById(R.id.product_detail_im_access_shop);
		     this.mStartIm = this.mIMView.findViewById(R.id.product_detail_contact_customer_service);
		     
		     initIMView();
		     this.mCommentView.setEnabled(true);
		     this.mCommentView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(productDetailActivity, "跳转到评论页面", Toast.LENGTH_SHORT).show();
				}
			});
		 }
		 
		 private void initIMView(){
			 this.mStartIm.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(productDetailActivity, "跳转到营业员实时聊天", Toast.LENGTH_SHORT).show();
				}
			});
			 
			 this.mToShop.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(productDetailActivity, "跳转到同品牌商品列表", Toast.LENGTH_SHORT).show();
					}
			});
		 }
		 
		 void show(){
			 showComment();
			 showConsultationCount();
			 this.mServerIconHolder.show();
		 }
		 
		 void showComment(){
			 int commentsNum=productDetailActivity.product.getCommentsNum();
			 float rating=productDetailActivity.product.getRating();//得分
			 String good="100%";//好评率
			 commentsNum=34;
			 rating=(float) 4.5;
			 if(commentsNum<=0){
				 this.mCommentCountAndRate.setText("暂无评论");
				 this.mArrowComment.setVisibility(View.INVISIBLE);
				 this.mCommentView.setEnabled(false);
			 }else{
				 this.mRatingBar.setRating(rating);
				 this.mArrowComment.setVisibility(View.VISIBLE);
				 this.mCommentView.setEnabled(true);
				 //展示评论数据
				 this.mCommentCountAndRate.setText(good+" ("+commentsNum+")");
			 }
		 }
		 
		 //展示咨询数目
		 void showConsultationCount(){
			 //this.mConsulationView.setEnabled(true);
			 this.mConsulationView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(productDetailActivity, "跳到咨询页面", Toast.LENGTH_SHORT).show();
				}
			});
			 //咨询的数目
			 int num=15;
			 if(num>0){
				 this.mConsulationView.setEnabled(true);
				 this.mArrowConsulation.setVisibility(View.VISIBLE);
			 }else{
				 this.mConsulationView.setEnabled(false);
				 this.mArrowConsulation.setVisibility(View.INVISIBLE);
			 }
			 this.mConsultCount.setText("(15)");
		 }
		 
	 }
	 
	 //显示该商品享有的服务。
	 class ServerIconHolder{
		 int height = 36;
		 boolean isOpen = false;
		 ImageButton mArrow;
		 ArrayList<String> mIcons;
		 View mLayout;
		 LinearLayout mLayout1;
		 LinearLayout mLayout2;
		 View mRootView;
		 TextView mTextView;
		 //TextView mTransContent;
		 //View mTransView;
		 
		 ServerIconHolder(View view){
			//应该是通过服务器获取
			 this.mIcons=new ArrayList<String>();
			 mIcons.add("店铺自提");
			 mIcons.add("满99包邮");
			 this.mRootView = view;
		     this.mRootView.setOnClickListener(new View.OnClickListener()
		     {
		       public void onClick(View paramView)
		       {
		         ServerIconHolder.this.onViewClick();
		       }
		     });
		     //this.mTransView = this.mRootView.findViewById(2131167605);
		     //this.mTransContent = ((TextView)this.mTransView.findViewById(2131165235));
		     this.mTextView = ((TextView)view.findViewById(R.id.txt_2));
		     this.mArrow = ((ImageButton)view.findViewById(R.id.imgbtn_1));
		     this.mArrow.setBackgroundResource(R.drawable.pdn_icon_arrow_down);
		     this.mLayout1 = ((LinearLayout)view.findViewById(R.id.l_layout_1));
		     this.mLayout2 = ((LinearLayout)view.findViewById(R.id.l_layout_2));
		     this.mLayout = view.findViewById(R.id.r_layout_1);
		     this.mLayout1.setOnClickListener(new View.OnClickListener(){
		        public void onClick(View paramView)
		        {
		          ServerIconHolder.this.onViewClick();
		        }
		     });
		 }
		 
		 //展示
		 void show(){
			 this.mTextView.setText("此商品享受 汇金 线上线下服务！");
			 if(mIcons.size()>0){
				 showIcons();
				 showIconSimple();
				 this.mLayout2.setVisibility(View.GONE);
			 }else{
				 this.mLayout.setVisibility(View.GONE);
				 this.mArrow.setVisibility(View.INVISIBLE);
			 }
		 }
		 
		 void onViewClick(){
			 if(this.mIcons.size()>0){
				 if(this.isOpen){
					 this.mArrow.setBackgroundResource(R.drawable.pdn_icon_arrow_down);
					 this.mLayout1.setVisibility(View.VISIBLE);
					 this.mLayout2.setVisibility(View.GONE);
					 this.isOpen=false;
				 }else{
					 this.mArrow.setBackgroundResource(R.drawable.pdn_icon_arrow_up);
					 this.mLayout1.setVisibility(View.GONE);
					 this.mLayout2.setVisibility(View.VISIBLE);
					 this.isOpen=true;
				 }
			 }
		 }
		 
		 void showIcons(){
			 //this.mLayout1.removeAllViews();
			 this.mLayout2.removeAllViews();
			 int i=0;
			 while(i<this.mIcons.size()){
				 ViewGroup viewGroup = (ViewGroup)LayoutInflater.from(ProductDetailActivity.this).inflate(R.layout.product_icon_details, null);
			     TextView localTextView1 = (TextView)viewGroup.findViewById(R.id.txt_1);
			     ImageView localImageView = (ImageView)viewGroup.findViewById(R.id.img_1);
			     TextView localTextView2 = (TextView)viewGroup.findViewById(R.id.txt_2);
			     localTextView1.setText(this.mIcons.get(i));
			     if(i%2==0){
			    	 localTextView2.setText("可以到汇金门店自提，亲身去试一试，效果更好！");
			     }else{
			    	 localTextView2.setText("全场满99元包邮！");
			     }
			    	 
			     localImageView.setImageResource(R.drawable.server_icon);
			     ViewGroup.LayoutParams localLayoutParams = localImageView.getLayoutParams();
			     localLayoutParams.height = this.height;
			     localLayoutParams.width = this.height;
			     localImageView.setLayoutParams(localLayoutParams);
			     RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)localTextView1.getLayoutParams();
			     localLayoutParams1.height = (-4 + this.height);
			     localLayoutParams1.leftMargin = -2;
			     localTextView1.setLayoutParams(localLayoutParams1);
			     
			     if (i < -1 + this.mIcons.size())
			    	 viewGroup.setPadding(0, 0, 0, 20);
			     i++;
			     this.mLayout2.addView(viewGroup);
			     
			 }
		 }
		 
		 void showIconSimple(){
			 this.mLayout1.removeAllViews();
		     int i = 0;
		     //Iterator<String> localIterator = this.mIcons.iterator();
		     while(i<this.mIcons.size()){
		    	 ViewGroup localViewGroup = (ViewGroup)LayoutInflater.from(ProductDetailActivity.this).inflate(R.layout.product_icon, null);
			     TextView localTextView = (TextView)localViewGroup.findViewById(R.id.txt_1);
			     ImageView localImageView = (ImageView)localViewGroup.findViewById(R.id.img_1);
			     localTextView.setText(this.mIcons.get(i));
			     localImageView.setImageResource(R.drawable.server_icon); 
			     if (i < -1 + this.mIcons.size())
			    	 localViewGroup.setPadding(0, 0, 20, 0);
			     i++;
			     this.mLayout1.addView(localViewGroup);
			     RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)localImageView.getLayoutParams();
			     localLayoutParams1.height = this.height;
			     localLayoutParams1.width = this.height;
			     localImageView.setLayoutParams(localLayoutParams1);
			     RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)localTextView.getLayoutParams();
			     localLayoutParams2.height = (-4 + this.height);
			     localLayoutParams2.leftMargin = -2;
			     localTextView.setLayoutParams(localLayoutParams2);
		     }		     
		 }
	 }
	 
	 //显示推荐的商品
	 class RecommendHolder{
		 View rootView;
		 View mClickView;
		 @SuppressWarnings("deprecation")
		Gallery gallery;
		 
		 RecommendHolder(View view){
			 this.rootView=view;
			 this.gallery=(Gallery)view.findViewById(R.id.gallery_1);
			 setMarginLeft();
		 }
		 
		 void show(){
			 List<ProductRecommend> list=productDetailActivity.recommends;
			 System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^"+list.size());
			 if(list.size()<=0){
				 this.rootView.setVisibility(View.GONE);
				 return;
			 }
			 RecommendGalleryAdapter rga=new RecommendGalleryAdapter(productDetailActivity,list);
			 this.gallery.setAdapter(rga);
			 this.gallery.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					if(RecommendHolder.this.mClickView!=null){
						System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
						//RecommendHolder.this.mClickView.performClick();
						ItemHolder holder=(ItemHolder)RecommendHolder.this.mClickView.getTag();
						Toast.makeText(productDetailActivity, "点击了"+holder.txtTitle.getText(), Toast.LENGTH_SHORT).show();
						RecommendHolder.this.mClickView=null;
					}
				}
				 
			 });
			 int select=(Integer.MAX_VALUE/2)/productDetailActivity.recommends.size()*productDetailActivity.recommends.size()+1;
			 this.gallery.setSelection(select);
		 }
		 
		 //
		 void setMarginLeft()
		    {
			 DisplayMetrics metrics = new DisplayMetrics();
			 ProductDetailActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
			 int i=metrics.widthPixels;
		      int j = ProductDetailActivity.this.getResources().getDimensionPixelSize(R.dimen.pdn_recommend_width);
		      int k = ProductDetailActivity.this.getResources().getDimensionPixelSize(R.dimen.pdn_recommend_space);
		      int m = i - ProductDetailActivity.this.getResources().getDimensionPixelSize(R.dimen.pdn_left_right_space_width) - 3 * (j + k);
		      if (m > k)
		      {
		        int n = k + m / 2;
		        RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)this.gallery.getLayoutParams();
		        localLayoutParams.leftMargin = (-n);		       
		        ProductDetailActivity.RecommendHolder.this.gallery.setLayoutParams(localLayoutParams);
		      }
		    }
		 
		 //适配器
		 class RecommendGalleryAdapter extends BaseAdapter{
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
						//if(views[arg0]==null){
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
							
							top.setOnTouchListener(touch);
							
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
							bottom.setOnTouchListener(touch);
							//views[arg0]=arg1;
						//}else{
							//arg1=views[arg0];
						//}
						
					}
					return arg1;
				}
				
				//点击事件处理。
				View.OnClickListener listener=new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ItemHolder holder=(ItemHolder)v.getTag();
						Toast.makeText(RecommendGalleryAdapter.this.context, "点击了"+holder.txtTitle.getText(), Toast.LENGTH_SHORT).show();
					}
				};
				//触摸事件
				View.OnTouchListener touch=new View.OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction()==0)
							RecommendHolder.this.mClickView=v;
						return false;
					}
				};
			}
		 
		 class ItemHolder{
			ImageView imageView;
	        View rootView;
	        TextView txtPrice;
	        TextView txtTitle;
		}
		 
		 
	 }
}
