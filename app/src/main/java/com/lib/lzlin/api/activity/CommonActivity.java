package com.lib.lzlin.api.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lib.lzlin.api.R;
import com.lib.lzlin.api.application.MyAppUtil;
import com.lib.lzlin.api.constance.LzConstant;
import com.lib.lzlin.api.test.TestFragment;
import com.lib.lzlin.api.utils.commonUtils.FragmentUtils;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 公共类, 中转站
 * 创建人: Administrator
 * 创建时间:  2017/3/23 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class CommonActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "CommonActivity";
    private Activity activity;
    private LayoutInflater mInflater;
    private View toolbarView;
    private Toolbar toolbar;
    private TextView tvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        initOnCreate(); //  始化 OnCreate
    }

    /**
     * 初始化 OnCreate
     */
    private void initOnCreate() {
        initUI();
        replaceFragment();
    }

    /**
     * 替换片段
     */
    private void replaceFragment() {
        Intent intent = getIntent();
        int type = intent.getIntExtra(LzConstant.Constans.TYPE, LzConstant.Constans.DEFAULT_TYPE); // 默认标识
        Fragment fragment = null;
        switch (type) {
            // TODO lz  根据每个 Fragment 的唯一标识, 来加载 fragment
            case TestFragment.TYPE:
                fragment = new TestFragment();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentUtils.replace(this, fragment);
        }
    }

    /**
     * 初始化toolbar控件, 采用直接在布局内添加 toolbar , 然后添加布局到toolbar
     */
    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarView = mInflater.inflate(R.layout.toolbar, null);
        View imgBack = toolbarView.findViewById(R.id.imgBack);
        tvToolbar = (TextView) toolbarView.findViewById(R.id.tvToolbar);
        imgBack.setOnClickListener(this);
        toolbar.addView(toolbarView);
    }

    /**
     * 初始化toolbar控件, 采用 include Toolbar 布局
     */
    protected void initToolbarInclude() {
        View imgBack = findViewById(R.id.imgBack);
        TextView tvTitle = (TextView) findViewById(R.id.tvToolbar);
        imgBack.setOnClickListener(this);
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        activity = this;
        mInflater = getLayoutInflater();
        MyAppUtil.addActivity(activity);

        initToolbar();
        initToolbarInclude();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAppUtil.deleteActivity(activity);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
