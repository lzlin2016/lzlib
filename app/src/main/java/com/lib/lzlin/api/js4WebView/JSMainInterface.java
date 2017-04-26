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

    private Context cxt;
    private SharedPreferences preference;

    public JSMainInterface(Context context)
    {
        this.cxt = context;
        this.preference = PreferenceManager.getDefaultSharedPreferences(this.cxt);
    }

    @JavascriptInterface
    public String getToken()
    {
        String token = this.preference.getString("token","");
        String userid = this.preference.getString("userid","");

        return "{\"userid\":\"" + userid +"\",\"token\":\""+token+"\"}";
    }

    @JavascriptInterface
    public boolean isLogined()
    {
        System.out.println("token:"+this.preference.getString("token",null));
        return !(this.preference.getString("token",null) == null);
    }

    @JavascriptInterface
    public boolean setToken (String userid,String token)
    {
        SharedPreferences.Editor editor=this.preference.edit();
        editor.putString("userid",userid);
        editor.putString("token",token);
        editor.commit();
        return true;
    }

}
