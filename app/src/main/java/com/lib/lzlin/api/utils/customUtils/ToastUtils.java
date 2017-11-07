package com.lib.lzlin.api.utils.customUtils;

import android.widget.Toast;

import com.lib.lzlin.api.application.BaseApp;

/**
 * ToastUtils
 *
 * 类的描述: toast 辅助工具类
 * 创建时间: time: 2017/11/7  16:11
 * 修改备注:
 */

public class ToastUtils {
    private final static boolean SHOW_NULL = true; // 是否显示空toast
    private static Toast mToast;

    private ToastUtils() {}

    /**
     * 初始化, 单例
     */
    public static void init() {
        synchronized (ToastUtils.class) {
            if (mToast == null) {
                mToast = Toast.makeText(BaseApp.getApplication(), "", Toast.LENGTH_SHORT);
            }
        }
    }

    /**
     * 显示toast
     * @param info
     */
    public static void show(String info) {
        if (!SHOW_NULL && info == null) {
            return;
        }
        if (mToast == null) {
            init();
        }
        mToast.setText(info == null ? "NULL" : info);
        mToast.show();
    }

    /**
     * 显示toast
     * @param resId
     */
    public static void show(int resId) {
        if (resId <= 0) {
            return;
        }
        if (mToast == null) {
            init();
        }
        mToast.setText(resId);
        mToast.show();
    }

    /**
     * 取消toast
     */
    public static void dimiss() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
