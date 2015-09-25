package com.hxyt.user;

import junit.framework.Test;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.hxyt.BaseActivity;
import com.hxyt.R;
import com.hxyt.home.Home_activity;
import com.hxyt.utils.Base64Util;
import com.hxyt.utils.SPUtils;
import com.hxyt.utils.T;

/**
 * @author 陈修园
 * 
 */
public class User_login_Activity extends BaseActivity {

	UserModel userModel = new UserModel();

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_user_login);
		userModel.userAUTOLogin(this, new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what==0) {
					T.show(User_login_Activity.this, "登录成功");
					openActivity(Home_activity.class);
				}else {
					T.show(User_login_Activity.this, "登录失败");
				}
			}
		});
	}

	

	@Override
	protected void setViewOnlister() {
		// TODO Auto-generated method stub
		//设置监听事件
		
	}

	
	/**
	 * 用户登录
	 */
	private void userLogin(){
		userModel.setUserLoginListener(new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 0) {
					T.show(User_login_Activity.this, "登录成功");
					userModel.savePassWord("","",User_login_Activity.this);
				} else {
					T.show(User_login_Activity.this, msg.obj.toString());
					openActivity(Home_activity.class);
				}
			}
		});
		userModel.userLogin("", "", User_login_Activity.this);		
	}



	@Override
	protected void init(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    
	}
	
	
	
}
