package com.lib.lzlin.api.custom.widget.numberButton;


import android.content.Context;
import android.util.TypedValue;


/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: Android大小单位转换工具类
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/23 13:59
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class DensityUtil {

    private DensityUtil() {
        throw new UnsupportedOperationException("DensityUtil cannot be instantiated");
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2sp(Context context, float pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, int spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }
}
