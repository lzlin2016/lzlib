package com.lib.lzlin.api.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 多彩的, 炫酷的textVIew, 在一个TextView 中使用多种颜色
 * 创建人: lz - Administrator
 * 创建时间:  2017/6/6 17:17
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class ColorfulUtils extends SpannableStringBuilder {
    private static SpannableStringBuilder builder;
    private static String info;
    private static Context mContext;

    /**
     * 初始化
     * @param ctx   上下文对象
     * @param info  内容
     * @return
     */
    public static SpannableStringBuilder init(Context ctx, String info) {
        mContext = ctx;
        ColorfulUtils.info = info;
        builder = new SpannableStringBuilder(info);
        return builder;
    }

    /**
     * 设置颜色
     * @param start 起始位置
     * @param end   终点位置
     * @param colorId 颜色, 采用res
     * @return
     */
    public static SpannableStringBuilder setSpan(int start, int end, int colorId) {
        ForegroundColorSpan themeSpan = new ForegroundColorSpan(mContext.getResources().getColor(colorId));
        builder.setSpan(themeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 设置颜色
     * @param strStart 起始字段
     * @param strEnd   终点字段
     * @param colorId  颜色, 采用res
     * @return
     */
    public static SpannableStringBuilder setSpan(String strStart, String strEnd, int colorId) {
        int start = info.indexOf(strStart);
        int end = info.indexOf(strEnd) + 1 > info.length() ? info.length() : info.indexOf(strEnd) + 1;
        return setSpan(start, end, colorId);
    }
}
