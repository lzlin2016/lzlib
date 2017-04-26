package com.lib.lzlin.api.appclient;

import android.content.Context;

import com.lib.lzlin.api.constance.LzConstant;
import com.lib.lzlin.api.utils.netUtils.HTTPUtils;
import com.lib.lzlin.api.utils.netUtils.ResponseListener;

import java.util.Map;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 客户端请求服务端, 发送请求
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/25 15:05
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class AppClient {

    /**
     * 用户请求登陆操作,请求数据
     *
     * @param context  上下文对象
     * @param params   传递参数
     * @param listener 返回结果监听
     */
    public static void userSignIn(Context context, Map<String, String> params, ResponseListener listener) {
        String URL = LzConstant.Url4AppClient.BASE_URL + LzConstant.Url4AppClient.userSignInAuthor;
        HTTPUtils.post(context, URL, params, listener);
    }
}
