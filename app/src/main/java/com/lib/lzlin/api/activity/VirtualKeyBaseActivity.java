package com.lib.lzlin.api.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lib.lzlin.api.R;


/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 对于像华为 P8 那种带有虚拟按键的, 在全屏时虚拟按键遮住屏幕内容的解决方案
 *  1. 设置根部局的 setFitsSystemWindows 属性为 TRUE ( android:fitsSystemWindows="true" )
 *  2. 重写 onWindowFocusChanged 方法, 隐藏虚拟按键, 侧边滑动弹出虚拟按键
 * 创建人: Administrator
 * 创建时间:  2017/3/23 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class VirtualKeyBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_key_base);
    }

    /**
     * 重写 onWindowFocusChanged , 解决全屏虚拟按键问题
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
