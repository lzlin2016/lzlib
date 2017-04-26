package com.lib.lzlin.api.custom.scrollerlayout.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.lzlin.api.R;

import java.util.List;

/**
 * Created by 王松 on 2016/8/28.
 */
public class ScrollerAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context context;
    private LayoutInflater inflater;

    public ScrollerAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.vp_item, container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
