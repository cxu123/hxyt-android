package com.hxyt.user;

import com.hxyt.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/** 
 * @author  作者 陈修园: 
 * @date 创建时间：2015-9-22 上午9:18:06 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */

public class UserFragment extends Fragment {

    
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
	if (savedInstanceState != null) {
	    return super.onCreateView(inflater, container, savedInstanceState);
	} else {
	return inflater.inflate(R.layout.fragment_user, container, false);
    
	}
    }
    
}
