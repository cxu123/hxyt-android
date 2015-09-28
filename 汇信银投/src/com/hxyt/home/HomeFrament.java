package com.hxyt.home;



import java.lang.reflect.Field;

import com.hxyt.R;
import com.hxyt.order.OrderInfoActivity;
import com.hxyt.utils.L;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


/** 
 * @author  作者 陈修园: 
 * @date 创建时间：2015-9-22 上午9:20:02 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */

public class HomeFrament extends Fragment {

    private Button strart_ActiButton;
    
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
	L.v("首页");
	if (savedInstanceState!=null) {
	    return super.onCreateView(inflater, container, savedInstanceState);
	}else {
	    View view = inflater.inflate(R.layout.fragment_home, container, false);
	    strart_ActiButton=(Button) view.findViewById(R.id.button1);
	    iniClick();
	    return view;  
	}
	 
	
    }
    
    private void iniClick(){
	strart_ActiButton.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(getActivity(),OrderInfoActivity.class);
		getActivity().startActivity(intent);
		
	    }
	});
    }

}
