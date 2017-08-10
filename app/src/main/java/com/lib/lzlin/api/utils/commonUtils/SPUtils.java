package com.lib.lzlin.api.utils.commonUtils;

import android.content.Context;
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
     * @param context context 对象
     */
    private static void initSP(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(FILE_NAME, 0);//创建Sp文件,为私有模式
            edit = sp.edit();// 初始化edit
        }
    }

    /**
     * 清空所有数据 - SP
     * @param context context 对象
     */
    private static void clearSP(Context context) {
        initSP(context);
        edit.clear();
        edit.commit();
    }

    /**
     * 设置SP 值
     * @param context context 对象
     * @param key      key
     * @param value    value
     */
    public static void put(Context context, String key, Object value) {
        initSP(context);
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else {
            edit.putString(key, value.toString());
        }
        edit.commit();
    }

    /**
     * 获取SP 值
     * @param context context 对象
     * @param key      key
     * @param defaultValue    默认值
     * @return        f返回结果
     */
    public static Object get(Context context, String key, Object defaultValue) {
        initSP(context);
        if (defaultValue instanceof String) {
            defaultValue = sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            defaultValue = sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            defaultValue = sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            defaultValue = sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            defaultValue = sp.getLong(key, (Long) defaultValue);
        } else {
            defaultValue = sp.getString(key, defaultValue.toString());
        }
        return defaultValue;
    }

}
