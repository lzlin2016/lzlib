package com.lib.lzlin.api.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * 【Android】getColor(int id)在API23时过时
 *
 * @author lz
 * @created at 2017/2/12 16:13
 */

public class ResUtils {
    /**
     * getColor(int id)在API23时过时
     *
     * @param context
     * @param id
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return context.getColor(id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    /**
     * 获取string.xml文件里面的方法
     *
     * @param context
     * @param id
     * @return
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }
}
