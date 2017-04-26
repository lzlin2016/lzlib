package com.lib.lzlin.api.application;

import android.app.Activity;
import android.app.Application;
import android.os.Build;

import com.lib.lzlin.api.constance.LzConstant;
import com.lib.lzlin.api.utils.statusBaeUtils.StateBarTranslucentUtils;

import java.util.ArrayList;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: application 基类
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 18:11
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class BaseApp extends Application {
    ArrayList<Activity> list = new ArrayList<Activity>();//保存已启动的Activity

    public void addActivty(Activity activity) {//添加Activity
        if (LzConstant.OpenSwitch2Client.isOpenStatebarColor				 // 是否使用渲染 / 沉浸式  状态栏
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT	// 4.4 全透明状态栏, sdk版本必须大于 4.4, 否则不渲染
                ) {
            //设置状态栏透明
            StateBarTranslucentUtils.setStateBarTranslucent(activity);
            //状态栏着色
            StateBarTranslucentUtils.setStateBarColor(activity);
        }
        list.add(activity);
    }

    public void deleteActivity(Activity activity) {//移除Activity
        list.remove(activity);
    }

    public void exit() {
        for (int i = 0; i < list.size(); i++) {//关闭所有启动的Activity
            Activity activity = list.get(i);
            activity.finish();
        }
    }
}
