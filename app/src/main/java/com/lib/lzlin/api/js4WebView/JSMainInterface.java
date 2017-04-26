package com.lib.lzlin.api.js4WebView;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.webkit.JavascriptInterface;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: JSMainInterface 接口
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 18:12
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class JSMainInterface {
    private Context mContext;
    private SharedPreferences preference;

    public JSMainInterface(Context context) {
        this.mContext = context;
        this.preference = PreferenceManager.getDefaultSharedPreferences(this. mContext);
    }

    // 被JS 调用的方法, 需要添加 @JavascriptInterface 注解
    @JavascriptInterface
    public boolean isLogined() {
        System.out.println("token:"+this.preference.getString("token", null));
        return !(this.preference.getString("token",null) == null);
    }


}
