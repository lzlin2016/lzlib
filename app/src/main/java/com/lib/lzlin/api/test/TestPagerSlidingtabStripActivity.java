package com.lib.lzlin.api.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lib.lzlin.api.R;
import com.lib.lzlin.api.custom.pagerSlidingTabStrip.PagerSlidingTabStrip;

public class TestPagerSlidingtabStripActivity extends AppCompatActivity {

    private PagerSlidingTabStrip mTabs;
    private ViewPager mViewPager;
    private final String[] TITLES = {
            "这是tab0"
            , "这是tab1"
            , "这是tab2"
            , "这是tab3"
            , "这是tab4"
            , "这是tab5"
            , "这是tab6"
            , "这是tab7"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_slidingtab_strip);

        intiViewPager();
        initPsts();
    }

    /**
     * 初始化 ViewPager 控件
     */
    private void intiViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        // FragmentManager fm = getActivity().getSupportFragmentManager();
        // Fragment嵌套时，必须改用getChildFragmentManager()
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new MyAdapter(fm));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化 PagerSlidingTabStrip 控件
     */
    private void initPsts() {
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mTabs.setTextSize((int) 20);// 设置字体大小
        mTabs.setTextColor(Color.GRAY);// 设置文本默认颜色
        mTabs.setSelectedTabTextColor(Color.BLACK);// 设置文本选中颜色
        // 注意要在ViewPager设置适配器之后调用
        mTabs.setViewPager(mViewPager);
    }

    private final class MyAdapter extends FragmentPagerAdapter {
        private MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return new TestpagerSlidingTabStripFragment(position);
        }
    }
}
