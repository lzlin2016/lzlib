package com.lib.lzlin.api.utils.statusBaeUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 渲染 / 沉浸式  状态栏
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class StateBarTranslucentUtils {
    public static void setStateBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            // activity.getWindow().setStatusBarColor(Color.TRANSPARENT);  //直接用这个方法会有兼容性问题
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }

    /**
     * 设置状态栏颜色
     * @param activity                  activity 对象
     * @param setFitsSystemWindows      是否设置适应状态栏, 即状态栏不铺满activity 的内容
     * @param ColorId                   颜色ID, 默认取白色
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void setStateBarColor(Activity activity, boolean setFitsSystemWindows,int ColorId) {
        // 设置状态栏颜色
        ViewGroup contentLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        setupStatusBarView(activity, contentLayout, (activity.getResources().getColor(ColorId))); // 设置渲染为主题颜色

        // 设置Activity layout的fitsSystemWindows
        View contentChild = contentLayout.getChildAt(0);
//        contentChild.setBackgroundColor(activity.getResources().getColor(R.color.white));
        contentChild.setFitsSystemWindows(setFitsSystemWindows);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void setStateBarColor(Activity activity) {
        setStateBarColor (activity, true, Color.WHITE);
    }

    private static void setupStatusBarView(Activity activity, ViewGroup contentLayout, int color) {

        View mStatusBarView = null;

        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        contentLayout.addView(statusBarView, lp);

        mStatusBarView = statusBarView;

        mStatusBarView.setBackgroundColor(color);
    }

    /**
     * 获得状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
