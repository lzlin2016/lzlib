package com.lib.lzlin.api.utils.netUtils;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: ResponseListener, 辅助工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public interface ResponseListener extends ErrorListener, Listener<String> {
//    baseCommon void showProgressBar(); // 播放加载动画
//    baseCommon void dismissProgressBar(); // 停止加载动画
}