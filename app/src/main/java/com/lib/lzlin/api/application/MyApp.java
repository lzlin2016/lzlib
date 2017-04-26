package com.lib.lzlin.api.application;

import android.app.Application;

import com.lib.lzlin.api.test.MainActivity;
import com.lib.lzlin.api.test.TestFragment;
import com.usepropeller.routable.Router;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 单独例子的application
 * 创建人: Administrator
 * 创建时间:  2017/3/23 18:08
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Router.sharedRouter().setContext(getApplicationContext());
        Router.sharedRouter().map("rapaq://rapaq.com/member/order/:id", MainActivity.class);
        Router.sharedRouter().map4Fragment("rapaq://rapaq.com/member/testfragment/:id", TestFragment.class);
    }
}
