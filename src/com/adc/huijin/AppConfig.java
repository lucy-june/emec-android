package com.adc.huijin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;


/**
 * 应用程序配置类：用于保存用户相关信息及设置
 * @version 1.0
 * @created 2012-3-21
 */
public class AppConfig {
	public static boolean isFinish=true;//主要针对 弹出dialog 是否返回
	private final static String APP_CONFIG = "config";
	
	
	public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
	/**
	 ******************************************* 参数设置信息开始 ******************************************
	 */

	public final static String HOST = "192.168.0.189:3000"; 
	public final static String HTTPSHOST="https://202.120.40.140:3001/";
	// 应用名称
	public static String APP_NAME = "";

	// 图片路径
	public static final String IMAGE_URL = "http://58.211.5.34:8080/studioms/staticmedia/images/#";

	// 视频路径
	public static final String VIDEO_URL = "http://58.211.5.34:8080/studioms/staticmedia/video/#";

	// 保存参数文件夹名称
	public static final String SHARED_PREFERENCE_NAME = "itau_jingdong_prefs";

	// SDCard路径
	public static final String SD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	// 图片存储路径
	public static final String BASE_PATH = SD_PATH + "/iTau/jingdong/";

	// 缓存图片路径
	public static final String BASE_IMAGE_CACHE = BASE_PATH + "cache/images/";

	// 需要分享的图片
	public static final String SHARE_FILE = BASE_PATH + "QrShareImage.png";

	// 手机IMEI号码
	public static String IMEI = "";

	// 手机号码
	public static String TEL = "";

	// 屏幕高度
	public static int SCREEN_HEIGHT = 800;

	// 屏幕宽度
	public static int SCREEN_WIDTH = 480;

	// 屏幕密度
	public static float SCREEN_DENSITY = 1.5f;

	// 分享成功
	public static final int SHARE_SUCCESS = 0X1000;

	// 分享取消
	public static final int SHARE_CANCEL = 0X2000;

	// 分享失败
	public static final int SHARE_ERROR = 0X3000;

	// 开始执行
	public static final int EXECUTE_LOADING = 0X4000;

	// 正在执行
	public static final int EXECUTE_SUCCESS = 0X5000;

	// 执行完成
	public static final int EXECUTE_FAILED = 0X6000;

	// 加载数据成功
	public static final int LOAD_DATA_SUCCESS = 0X7000;

	// 加载数据失败
	public static final int LOAD_DATA_ERROR = 0X8000;

	// 动态加载数据
	public static final int SET_DATA = 0X9000;

	// 未登录
	public static final int NONE_LOGIN = 0X10000;

	/**
	 ******************************************* 参数设置信息结束 ******************************************
	 */
	
	private Context mContext;
	private static AppConfig appConfig;
	
	public static AppConfig getAppConfig(Context context)
	{
		if(appConfig == null){
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}
	
	/**
	 * 获取Preference设置
	 */
	public static SharedPreferences getSharedPreferences(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	
	public String get(String key)
	{
		Properties props = get();
		return (props!=null)?props.getProperty(key):null;
	}
	
	public Properties get() {
		FileInputStream fis = null;
		Properties props = new Properties();
		try{
			//读取files目录下的config
			//fis = activity.openFileInput(APP_CONFIG);
			
			//读取app_config目录下的config
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);
			
			props.load(fis);
		}catch(Exception e){
		}finally{
			try {
				fis.close();
			} catch (Exception e) {}
		}
		return props;
	}
	
	private void setProps(Properties p) {
		FileOutputStream fos = null;
		try{
			//把config建在files目录下
			//fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);
			
			//把config建在(自定义)app_config的目录下
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			File conf = new File(dirConf, APP_CONFIG);
			fos = new FileOutputStream(conf);
			
			p.store(fos, null);
			fos.flush();
		}catch(Exception e){	
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (Exception e) {}
		}
	}

	public void set(Properties ps)
	{
		Properties props = get();
		props.putAll(ps);
		setProps(props);
	}
	
	public void set(String key,String value)
	{
		Properties props = get();
		props.setProperty(key, value);
		setProps(props);
	}
	
	public void remove(String...key)
	{
		Properties props = get();
		for(String k : key)
			props.remove(k);
		setProps(props);
	}
}
