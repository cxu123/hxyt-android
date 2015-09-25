package com.hxyt.home;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.hxyt.BaseActivity;
import com.hxyt.BaseFragmentActivity;
import com.hxyt.R;
import com.hxyt.news.ProductNewsFrament;
import com.hxyt.order.OrderFragment;
import com.hxyt.user.UserFragment;
import com.hxyt.utils.L;

public class Home_activity extends BaseFragmentActivity {

    private LinearLayout first;
    private LinearLayout secend;
    private LinearLayout threed;
    private LinearLayout four;
    private FragmentManager fragmentManager;
    // 现在变色的item
    // 上一个点击的按钮
    private int nowButtonNo = 0;

    private OrderFragment orderFragment;
    private HomeFrament homeFrament;
    private ProductNewsFrament productNewsFrament;
    private UserFragment userFragment;

    private ImageButton one, two, three, image_four;
    // 点击下的颜色
    private String checkColor = "#e3a05d";

    @Override
    protected void findViews() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activit_home);
	first = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
	secend = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
	threed = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
	four = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);
	one = (ImageButton) findViewById(R.id.btn_tab_bottom_weixin);
	two = (ImageButton) findViewById(R.id.btn_tab_bottom_friend);
	three = (ImageButton) findViewById(R.id.btn_tab_bottom_contact);
	image_four = (ImageButton) findViewById(R.id.btn_tab_bottom_setting);
	fragmentManager = Home_activity.this.getSupportFragmentManager();

    }

    @Override
    protected void init(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	if (savedInstanceState == null) {
	    hideFragmentsAndChangeImage(1);
	} else {
	    L.e("数据已经被恢复不用");
//	    nowButtonNo = savedInstanceState.getInt("nowButtonNo");
//	    hideFragmentsAndChangeImage(0);
//	    nowButtonNo = 0;
	    hideFragmentsAndChangeImage(savedInstanceState
		    .getInt("nowButtonNo"));
	    nowButtonNo = savedInstanceState.getInt("nowButtonNo");
	    hideFragmentAll();
	}

    }

    @Override
    protected void setViewOnlister() {
	// TODO Auto-generated method stub
	first.setOnClickListener(new bottomViewClick());
	secend.setOnClickListener(new bottomViewClick());
	threed.setOnClickListener(new bottomViewClick());
	four.setOnClickListener(new bottomViewClick());

    }

    private void hideFragmentAll() {
	FragmentTransaction transaction = fragmentManager.beginTransaction();
	if (homeFrament != null) {
	    // 开启一个Fragment事务
	    transaction.hide(homeFrament);

	}
	if (orderFragment != null) {
	    transaction.hide(orderFragment);
	}
	if (userFragment != null) {
	    transaction.hide(userFragment);
	}
	if (productNewsFrament != null) {
	    transaction.hide(productNewsFrament);
	}
    }

    private void hideFragmentsAndChangeImage(int id) {
	// if (fragmentManager==null) {
	//
	// }
	//
	FragmentTransaction transaction = fragmentManager.beginTransaction();
	// 隐藏上一个frament 和改变图标颜色
	if (id != nowButtonNo) {

	    switch (nowButtonNo) {
	    case 1:
		if (homeFrament != null) {
		    // 开启一个Fragment事务
		    transaction.hide(homeFrament);

		}
		// one.setImageDrawable(new Drawable(checkColor));#FF5654
		one.setBackgroundColor(Color.parseColor("#FF5654"));
		break;
	    case 2:
		if (orderFragment != null) {
		    transaction.hide(orderFragment);
		}
		two.setBackgroundColor(Color.parseColor("#7836FF"));
		break;
	    case 3:
		if (userFragment != null) {
		    transaction.hide(userFragment);
		}
		three.setBackgroundColor(Color.parseColor("#7AA6DD"));
		break;
	    case 4:
		if (productNewsFrament != null) {
		    transaction.hide(productNewsFrament);
		}
		image_four.setBackgroundColor(Color.parseColor("#BB33DD"));
		break;
	    }

	    // 显示frament
	    switch (id) {
	    case 1:
		if (homeFrament != null) {
		    // 开启一个Fragment事务
		    transaction.show(homeFrament);
		} else {
		    homeFrament = new HomeFrament();
		    L.v("创建首页");
		    transaction.add(R.id.id_content, homeFrament);
		    // transaction.addToBackStack(null);

		}
		one.setBackgroundColor(Color.parseColor(checkColor));
		break;
	    case 2:
		if (orderFragment != null) {
		    transaction.show(orderFragment);
		} else {
		    orderFragment = new OrderFragment();
		    transaction.add(R.id.id_content, orderFragment);
		}
		two.setBackgroundColor(Color.parseColor(checkColor));
		break;
	    case 3:
		if (userFragment != null) {
		    transaction.show(userFragment);
		} else {
		    userFragment = new UserFragment();
		    transaction.add(R.id.id_content, userFragment);

		}
		three.setBackgroundColor(Color.parseColor(checkColor));
		break;
	    case 4:
		if (productNewsFrament != null) {
		    transaction.show(productNewsFrament);
		} else {
		    productNewsFrament = new ProductNewsFrament();
		    transaction.add(R.id.id_content, productNewsFrament);
		}
		image_four.setBackgroundColor(Color.parseColor(checkColor));
		break;
	    }
	    transaction.commit();
	    nowButtonNo = id;
	}
    }

    private class bottomViewClick implements OnClickListener {

	@Override
	public void onClick(View v) {
	    // TODO Auto-generated method stub
	    switch (v.getId()) {
	    case R.id.id_tab_bottom_weixin:
		hideFragmentsAndChangeImage(1);
		L.v("首页");
		break;
	    case R.id.id_tab_bottom_friend:
		hideFragmentsAndChangeImage(2);
		L.v("产品");
		break;

	    case R.id.id_tab_bottom_contact:
		hideFragmentsAndChangeImage(3);
		L.v("用户");
		break;

	    case R.id.id_tab_bottom_setting:
		hideFragmentsAndChangeImage(4);
		L.v("新闻");
		break;

	    }
	}

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
	// TODO Auto-generated method stub
	// 保存当前的点击的是哪一个
	outState.putInt("nowButtonNo", nowButtonNo);

	super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestart() {
	// TODO Auto-generated method stub
	super.onRestart();
	L.e("Home Restart");
    }

    @Override
    protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	L.e("Home stop");
    }

    @Override
    protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();

	L.e("home destroy");
    }
}
