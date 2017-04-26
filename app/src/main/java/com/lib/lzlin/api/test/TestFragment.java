package com.lib.lzlin.api.test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.lzlin.api.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    public static final int TYPE = 0001;  // 唯一标识


    public TestFragment() {
        // Required empty baseCommon constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

}
