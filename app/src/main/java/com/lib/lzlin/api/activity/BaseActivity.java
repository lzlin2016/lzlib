package com.lib.lzlin.api.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lib.lzlin.api.BaseWidget;
import com.lib.lzlin.api.R;
import com.lib.lzlin.api.application.MyAppUtil;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 基类
 * 创建人: Administrator
 * 创建时间:  2017/3/23 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "BaseActivity";
    private Activity activity;
    private LayoutInflater mInflater;
    BaseWidget baseWidget = new BaseWidget();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initOnCreate(); //  始化 OnCreate
    }

    /**
     * 初始化 OnCreate
     */
    private void initOnCreate() {
        activity = this;
        mInflater = getLayoutInflater();
        MyAppUtil.addActivity(activity);

        initIntentData();   // 初始化上个界面传值
        initUI();   // 初始化UI 控件
        initData();     // 初始化数据
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    /**
     * 初始化UI 控件
     */
    private void initUI() {
        initToolbar(R.layout.toolbar);
    }

    /**
     * 初始化toolbar控件
     * @param ToolbarTitleId    toolbar title 显示内容文字的ID
     */
    private void initToolbar(int ToolbarTitleId) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        View toolbarView = getLayoutInflater( ).inflate(ToolbarTitleId, null);
        View imgBack = toolbarView.findViewById(R.id.imgBack);
        TextView tvToolbar = (TextView) toolbarView.findViewById(R.id.tvToolbar);
        tvToolbar.setText(getResources().getString(ToolbarTitleId));
        imgBack.setOnClickListener(view -> finish());
        toolbar.addView(toolbarView);
    }

    @Override public void onClick(View view) {  }

    /**
     * 初始化上个界面传值
     */
    private void initIntentData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAppUtil.deleteActivity(activity); // 移除
    }
}
