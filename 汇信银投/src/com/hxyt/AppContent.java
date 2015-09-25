package com.hxyt;

import com.hxyt.utils.AppManager;
import com.lidroid.xutils.util.PreferencesCookieStore;

import android.R.string;
import android.app.Application;

/** 
 * @author  陈修元
 * @date 2015-7-17 11:20:29 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class AppContent extends Application {
	/**
	 * activity 保存
	 */
	public AppManager appManager;
	/**
	 * 用户信息保存
	 */
	//public UserInfoBeen userInfoBeen;
	
	/**
	 * 登录会话
	 */
	public static PreferencesCookieStore preferencesCookieStore;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		appManager=AppManager.getAppManager();
	}
	
	public static void setPreferencesCookieStore(
			PreferencesCookieStore preferencesCookieStore) {
		AppContent.preferencesCookieStore = preferencesCookieStore;
	}
	
	public static PreferencesCookieStore getPreferencesCookieStore(){
		return AppContent.preferencesCookieStore;
	}
	
}
