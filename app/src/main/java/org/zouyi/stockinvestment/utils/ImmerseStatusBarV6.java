package org.zouyi.stockinvestment.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 设置沉浸状态栏
 * 由于M以上才支持改状态栏颜色，故M（6）以上版本才支持全屏设置沉浸状态栏
 */
public class ImmerseStatusBarV6 {

    //设置沉浸状态栏
    public static void setImmerseStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
