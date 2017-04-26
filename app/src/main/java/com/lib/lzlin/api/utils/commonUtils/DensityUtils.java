package com.lib.lzlin.api.utils.commonUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 计算屏幕的宽度和高度, 状态栏的高度和虚拟按键的高度, 以及进行单位转换
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 15:45
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class DensityUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context   上下文对象
     * @param dpValue   dp 元数据
     * @return          px 目标结果
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param context   上下文对象
     * @param pxValue   px 元数据
     * @return          dp 目标结果
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     * @param context   上下文对象
     * @return          屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        int width = getWindowManager(context).getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕高度
     * @param context   上下文对象
     * @return          屏幕高度
     */
    public static int getScreenHeight(Context context) {
        int height = getWindowManager(context).getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 获取状态栏高度, 参考资料 : http://blog.csdn.net/a_running_wolf/article/details/50477965
     * @param context   context 上线文对象
     * @return          状态栏高度
     */
    public static int getStatuesHeight(Context context) {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 获取虚拟键盘的高度
     * @param context   上线文对象
     * @return          虚拟键盘高度
     */
    public static int getVirtualKeyHeight(Context context) {
        return checkDeviceHasNavigationBar(context) ?
                getHeightWithVirtualKey(context) - getHeightWithoutVirtualKey(context) : 0;
    }

    /**
     * 获取屏幕尺寸，但是不包括虚拟功能 高度
     * @param context   上下文对象
     * @return          显示内容区域高度 = 总高度 - 虚拟键盘高度
     */
    public static int getHeightWithoutVirtualKey(Context context) {
        int height = getWindowManager(context).getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 通过反射，获取包含虚拟键的整体屏幕 高度
     * @param context   上下文对象
     * @return          总高度
     */
    public static int getHeightWithVirtualKey(Context context) {
        int dpi = 0;
        Display display = getWindowManager(context).getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 WindowManager 管理器
     * @param context   上线爱我呢对象
     * @return          WindowManager 管理器
     */
    public static WindowManager getWindowManager(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return manager;
    }

    /**
     * 获取是否存在 NavigationBar 虚拟按键
     * @param context   上下文对象
     * @return          是否存在虚拟键
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    /**
     * 隐藏虚拟键, 虚拟按键从3.0版本以后才出现, 需要做版本判断
     * @param activity   activity 对象
     */
    public static void hideNaviationBar (Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {// 3.0 版本号为: HONEYCOMB
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }
}
