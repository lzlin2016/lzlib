package com.lib.lzlin.api.utils.commonUtils;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述:  SP 工具类
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 15:45
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class SPUtils {
    private static SharedPreferences sp;
    private static SharedPreferences.Editor edit;
    private static final String FILE_NAME = "SHARE_DATE"; // 保存在手机里面的文件名

    /**
     * 初始化-SP
     * @param activity activity 对象
     */
    private static void initSP(Activity activity) {
        if (sp == null) {
            sp = activity.getSharedPreferences(FILE_NAME, 0);//创建Sp文件,为私有模式
            edit = sp.edit();// 初始化edit
        }
    }

    /**
     * 清空所有数据 - SP
     * @param activity activity 对象
     */
    private static void clearSP(Activity activity) {
        initSP(activity);
        edit.clear();
        edit.commit();
    }

    /**
     * 设置SP 值
     * @param activity activity 对象
     * @param key      key
     * @param value    value
     */
    public static void setIntegerSP(Activity activity, String key, Integer value) {
            initSP(activity);
            edit.putInt(key, value);
            edit.commit();
    }
    public static void setFloatSP(Activity activity, String key, Float value) {
            initSP(activity);
            edit.putFloat(key, value);
            edit.commit();
    }
    public static void setBooleanSP(Activity activity, String key, Boolean value) {
            initSP(activity);
            edit.putBoolean(key, value);
            edit.commit();
    }
    public static void setStrSP(Activity activity, String key, String value) {
        initSP(activity);
        edit.putString(key, value);
        edit.commit();
    }
    public static void setLongSP(Activity activity, String key, Long value) {
            initSP(activity);
            edit.putLong(key, value);
            edit.commit();
    }

    /**
     * 获取SP 值
     * @param activity activity 对象
     * @param key      key
     * @param defaultValue    默认值
     * @return        f返回结果
     */
    public static Integer getIntegerSP(Activity activity, String key, Integer defaultValue) {
        initSP(activity);
        return sp.getInt(key, defaultValue);
    }
    public static Float getFloatSP(Activity activity, String key, Float defaultValue) {
        initSP(activity);
        return sp.getFloat(key, defaultValue);
    }
    public static Boolean getBooleanSP(Activity activity, String key, Boolean defaultValue) {
        initSP(activity);
        return sp.getBoolean(key, defaultValue);
    }
    public static String getStrSP(Activity activity, String key, String defaultValue) {
        initSP(activity);
        return sp.getString(key, defaultValue);
    }
    public static Long getLongSP(Activity activity, String key, Long defaultValue) {
        initSP(activity);
        return sp.getLong(key, defaultValue);
    }

}
