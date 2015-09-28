package com.hxyt.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hxyt.BaseActivity;
import com.hxyt.R;
import com.hxyt.other.GuidanceActivity;
import com.hxyt.utils.ActivityGesture;

public class MainActivity extends BaseActivity {

    Button button;
    Button start_Guidance_Activity;
    ActivityGesture gesture;
    @SuppressLint("NewApi")
    @Override
    protected void findViews() {
	// TODO Auto-generated method stub
	setContentView(R.layout.activity_main);
	View view = findViewById(android.R.id.content);
	// view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	view.setSystemUiVisibility(View.INVISIBLE);
	button = (Button) findViewById(R.id.test);
	start_Guidance_Activity = (Button) findViewById(R.id.start_Guidance_Activity);
	gesture=new ActivityGesture(this);
    }

    @Override
    protected void setViewOnlister() {
	// TODO Auto-generated method stub

	button.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View arg0) {
		// TODO Auto-generated method stub
		openActivity(Home_activity.class);
	    }
	});

	start_Guidance_Activity.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		openActivity(GuidanceActivity.class);
	    }
	});
    }

    @Override
    protected void init(Bundle savedInstanceState) {
	// TODO Auto-generated method stub

    }

    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return gesture.setOnTouch(event);
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
	gesture.setTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
