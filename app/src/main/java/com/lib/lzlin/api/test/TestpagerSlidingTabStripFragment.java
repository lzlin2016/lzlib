package com.lib.lzlin.api.test;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.lzlin.api.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestpagerSlidingTabStripFragment extends Fragment {
    private int type = 0;
    private View mLyaout;

    public TestpagerSlidingTabStripFragment() {
    }

    @SuppressLint({"NewApi", "ValidFragment"})  // 解决Fragment 重构问题, 此种方法不推荐, 详见lz 笔记
    public TestpagerSlidingTabStripFragment(int type) {
        this.type = type;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLyaout = inflater.inflate(R.layout.fragment_testpager_sliding_tab_strip, container, false);
        TextView tv = (TextView) mLyaout.findViewById(R.id.tv);
        tv.setText("This is the " + type + " Fragment");
        return mLyaout;
    }

}
