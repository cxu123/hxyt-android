package com.hxyt.home;



import java.lang.reflect.Field;

import com.hxyt.R;
import com.hxyt.utils.L;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/** 
 * @author  作者 陈修园: 
 * @date 创建时间：2015-9-22 上午9:20:02 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */

public class HomeFrament extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
	L.v("首页");
	if (savedInstanceState!=null) {
	    return super.onCreateView(inflater, container, savedInstanceState);
	}else {
	    return inflater.inflate(R.layout.fragment_home, container, false);  
	}
	 
	
    }
    
    
    @Override
    public void onDetach()
    {
        super.onDetach();
        try
        {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}
