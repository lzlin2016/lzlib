package com.lib.lzlin.api.js4WebView;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.webkit.JavascriptInterface;

/**
 * Created by Hamking on 2017/2/24.
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

    @JavascriptInterface
    public void openCustormerService(String url){

        String title = "这是什么鬼";
        // 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入三个参数分别为来源页面的url，来源页面标题，来源页面额外信息（可自由定义）
        // 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
//        ConsultSource source = new ConsultSource(url, "安卓客户端", "custom information string");
//        // 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable(), 如果返回为false，该接口不会有任何动作
//        Unicorn.openServiceActivity(this.cxt, // 上下文
//                title, // 聊天窗口的标题
//                source // 咨询的发起来源，包括发起咨询的url，title，描述信息等
//        );
    }

}
