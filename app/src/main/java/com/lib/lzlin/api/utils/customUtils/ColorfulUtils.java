package com.lib.lzlin.api.utils.customUtils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
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
    private static ColorfulUtils builder;
    private static String info;
    private static Context mContext;

    private ColorfulUtils(CharSequence text) {
       super(text);
    }

    /**
     * 初始化
     * @param ctx   上下文对象
     * @param info  内容
     * @return
     */
    public static ColorfulUtils init(Context ctx, String info) {
        mContext = ctx;
        ColorfulUtils.info = info;
        builder = new ColorfulUtils(info);
        return builder;
    }

    /**
     * 设置颜色, 指定起点位置和终点位置之间, 且包含在内的全部内容
     * @param start 起始位置
     * @param end   终点位置
     * @param colorId 颜色, 采用res
     * @return
     */
    public static SpannableStringBuilder setSpan (int start, int end, int colorId) {
        // 校验, 检测合法性
        if (TextUtils.isEmpty(info)) {
            throw new RuntimeException("ColorfulUtils  --> setSpan  Fail\n"
                    + "info == null, please check your info");
        }
        check(start); check(end); end = end > start ? end : start;

        ForegroundColorSpan themeSpan = new ForegroundColorSpan(mContext.getResources().getColor(colorId));
        builder.setSpan(themeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
    /**
     * 设置颜色, 指定起点位置和终点位置之间, 且包含在内的全部内容
     * @param strStart 起始字段
     * @param strEnd   终点字段
     * @param colorId  颜色, 采用res
     * @return
     */
    public static SpannableStringBuilder setSpan (String strStart, String strEnd, int colorId) {
        int start = info.indexOf(strStart);
        int end = info.indexOf(strEnd) + 1 > info.length() ? info.length() : info.indexOf(strEnd) + 1;
        return setSpan (start, end, colorId);
    }

    /**
     * 不包含 起点和终点
     */
    public static SpannableStringBuilder setSpanBetween (int start, int end, int colorId) {
        return setSpan(start +1, end -1, colorId);
    }
    public static SpannableStringBuilder setSpanBetween (String strStart, String strEnd, int colorId) {
        int start = info.indexOf(strStart);
        int end = info.indexOf(strEnd) + 1 > info.length() ? info.length() : info.indexOf(strEnd) + 1;
        return setSpan (start + 1, end -1, colorId);
    }

    /**
     * 包含起点之后的所有内容
     */
    public static SpannableStringBuilder setSpanStartWith (String strStart, int colorId) {
        int start = info.indexOf(strStart);
        return setSpan (start, info.length(), colorId);
    }
    public static SpannableStringBuilder setSpanStartWith (int start, int colorId) {
        return setSpan (start, info.length(), colorId);
    }

    /**
     * 包含终点之前的所有内容
     */
    public static SpannableStringBuilder setSpanEndWith (String strEnd, int colorId) {
        int end = info.indexOf(strEnd);
        return setSpan (0, end, colorId);
    }
    public static SpannableStringBuilder setSpanEndWith  (int end, int colorId) {
        return setSpan (0, end, colorId);
    }

    /**
     * 不包含起点之后的所有内容
     */
    public static SpannableStringBuilder setSpanAfter(String strStart, int colorId) {
        int start = info.indexOf(strStart);
        return setSpan (start + 1, info.length(), colorId);
    }
    public static SpannableStringBuilder setSpanAfter (int start, int colorId) {
        return setSpan (start + 1, info.length(), colorId);
    }

    /**
     * 不包含终点之前的所有内容
     */
    public static SpannableStringBuilder setSpanBefore (String strEnd, int colorId) {
        int end = info.indexOf(strEnd);
        return setSpan (0, end - 1, colorId);
    }
    public static SpannableStringBuilder setSpanBefore (int end, int colorId) {
        return setSpan (0, end - 1, colorId);
    }

    /**
     * 检查参数合法性
     * @param value 传入参数
     * @return     返回合法范围内的参数
     */
    private static int check(int value) {
        value = value == -1 ? 0 : value;
        value = value > info.length() - 1 ? info.length() - 1: value;
        return value;
    }
}
