package org.zouyi.stockinvestment.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

/**
 * 安卓4.4的一般做法
 */
public class ImmerseStatusBarV4_4 {
	
	private Activity activity;
	
	public ImmerseStatusBarV4_4(Activity activity){
		this.activity=activity;
	}
	
	 /**
     * 将状态栏沉浸制定视图上
     * @param view
     */
	public void setImmerseLayout( View view,boolean isadd) {
		
		//如果Anroid当前版本大于等于4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = activity.getWindow();
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = ScreenUtil.getStatusBarHeight(activity.getBaseContext());
            if(isadd){
            	//测量view的宽高,并且重新设置高度=view原来高度 + statusBarHeight
                measureView(view);
                LayoutParams params= (LayoutParams) view.getLayoutParams();
    			params.height=view.getMeasuredHeight() + statusBarHeight;
    			view.setLayoutParams(params);
            }
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }
    
	
	/**
	 * 通知父布局，占用的宽，高；
	 * 
	 * @param view
	 */
	private void measureView(View view) {
		//当前控件的父布局的布局参数
		LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
		}
		//宽度
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		//高度
		int height;
		int tempHeight = p.height;
		//如果高度值不等于0
		if (tempHeight > 0) {
			height = MeasureSpec.makeMeasureSpec(tempHeight,
					MeasureSpec.EXACTLY);
		//如果高度值等于0,即是之前还没有测量
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		//进行测量
		view.measure(width, height);
	}


}
