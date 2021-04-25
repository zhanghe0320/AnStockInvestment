package org.zouyi.stockinvestment.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 关于安卓app设置沉浸状态栏提供了两种方法：（本通用工程可采用这两种方法！！！）
 * 1、在AndroidManifest.xml需要设置的activity中配置styles属性即可，无需写任何代码
 *    实现原理是将状态栏背景色设置成和界面颜色一样，整个界面不会上移到状态栏顶部
 *    优点：系统方式，效果稳定，简单，不存在键盘遮挡输入框的问题
 *    缺点：6.0版本之前无法设置状态栏文字颜色，非纯色的背景有拼接痕迹
 *
 * 2、通过代码设置实现，实现原理是整个界面上移到状态栏顶部，就像本工具类
 *    优点：效果稳定，非纯色的背景能很好适应，无拼接痕迹，可以用在图片广告、界面无输入框的场景！！！
 *    缺点：6.0版本之前无法设置状态栏文字颜色，存在键盘挡住输入框的问题，很不好解决
 * 设置沉浸状态栏
 * isLightTheme:app是否是浅色主题，如果是,将状态栏设置成浅色背景深色文字；如果app是深色主题，设置状态栏深色背景，浅色文字
 */
public class ImmerseStatusBar {

    //设置沉浸状态栏
    public static void setImmerseStatusBar(Activity activity,boolean isLightTheme){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0及其以上
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            if(isLightTheme){
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else{
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4及其以上
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
