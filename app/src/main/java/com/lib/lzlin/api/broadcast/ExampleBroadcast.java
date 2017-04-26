package com.lib.lzlin.api.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 广播示例,
    // 在mainfest.xml 文件中注册广播
    <receiver
        android:name=".Broadcast.WXPayResultReceiver"
        android:enabled="false"
        android:exported="false" />

     // 需要先注册后使用, 初始化广播,  建议在 onCreate 中注册
    private void initReceive() {
        ExampleBroadcast mReceiver = new ExampleBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("action"); // 设置过滤
        registerReceiver(mReceiver, filter);
    }

    // 注销广播,  建议在 onDestroy 中注册
    private void unRegisterReceiver() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

 * 创建人: lz - Administrator
 * 创建时间:  2017/4/25 9:54
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class ExampleBroadcast extends BroadcastReceiver{
    public ExampleBroadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //得到广播中得到的数据，并在此处自定义方法, 进行相应的处理
        example();
    }

    public void example() {
        // balabalabalabala ... 自定义方法, 进行相应的处理
    }
}
