package com.lib.lzlin.api.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.lzlin.api.R;

import org.androidannotations.annotations.EFragment;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 基类
 * 创建人: Administrator
 * 创建时间:  2017/3/23 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

@EFragment
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private final static String TAG = "BaseFragment";
    private LayoutInflater mInfalte;
    private View mLayout;

    public BaseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mLayout == null) {
            mInfalte = inflater;
            mLayout = inflater.inflate(R.layout.fragment_base, container, false);
        }
        return mLayout;
    }

    protected abstract void initIntentData();  // 初始化上一个界面传值
    protected abstract void initUI();  // 初始化UI
    protected abstract void initData();  // 初始化数据

    @Override
    public void onClick(View view) {
    }
}
