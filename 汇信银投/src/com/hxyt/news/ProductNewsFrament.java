package com.hxyt.news;

import com.hxyt.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/** 
 * @author  作者 陈修园: 
 * @date 创建时间：2015-9-22 上午9:19:28 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */

public class ProductNewsFrament extends Fragment {

    
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
	if (savedInstanceState != null) {
	    return super.onCreateView(inflater, container, savedInstanceState);
	} else {
	return inflater.inflate(R.layout.fragment_product_news, container, false);
    
	}
    }
}
