package com.lib.lzlin.api.utils.languageUtils;

/**
 * Created by lz on 2017/2/24.
 * 语言切换工具类
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.lib.lzlin.api.utils.commonUtils.SPUtils;

import java.util.Locale;


public class LanguageSwitchUtils {
    // 设置界面
    public static final String LANGUE = "Langue"; // 语言
    public static final int LANGUE_AUTO = -1; // 自动
    public static final int LANGUE_ENGLISH = 0; // 英文
    public static final int LANGUE_CHINESE = 1; // 简体中文
    public static final int LANGUE_CHINESE_TW = 2; // 繁体中文

    /**
     * 描述  ： 切换语言, 切换的是本应用的语言, 而不是系统的语言
     * 作者  ： lz - Administrator
     * 时间  ： 2017/3/24  13:5
     *
     * @describe a
     */
    public static void switchLanguage(Activity activity) {
        Configuration config = activity.getResources().getConfiguration();// 获得设置对象
        int langue = (int) SPUtils.get(activity, LANGUE, LANGUE_AUTO);
        switch (langue) {
            case LANGUE_CHINESE:
                config.locale = Locale.CHINESE; // 中文
                break;
            case LANGUE_CHINESE_TW:
                config.locale = Locale.TAIWAN; // 台湾, 繁体字
                break;
            case LANGUE_ENGLISH:
                config.locale = Locale.US; // 英文
                break;
            case LANGUE_AUTO:
                // TODO lz 获取系统的语言, 可以设置, 直接采用系统默认
                // config.locale = getLocalLanguage(activity);
                return;
        }
        configLanguage(activity, config.locale);    // 切换应用的语言
    }

    /**
     *  配置语言, 切换的是本应用的语言, 而不是系统的语言
     *  @param context   上下文对象
     *  @param locale    语言
     */
    public static void configLanguage(Context context, Locale locale) {
        Configuration config = context.getResources().getConfiguration();// 获得设置对象
        Resources resources = context.getResources();// 获得res资源对象
        DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
        config.locale = locale; // 切换语言
        resources.updateConfiguration(config, dm);
    }

    /**
     * 获取本地的语言(系统语言)
     * @param context   上下文对象
     * @return          返回本地的语言
     */
    public static Locale getLocalLanguage(Context context) {
        Configuration config = context.getResources().getConfiguration();// 获得设置对象
        return config.locale;
    }
}
