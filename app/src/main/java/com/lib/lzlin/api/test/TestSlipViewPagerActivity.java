package com.lib.lzlin.api.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lib.lzlin.api.R;
import com.lib.lzlin.api.commonAdapter.list_gridViewAdapter.CommonViewHolder;
import com.lib.lzlin.api.commonAdapter.viewpagerAdapter.CommonViewPagerAdapter;
import com.lib.lzlin.api.custom.slipViewPager.custom.SlipViewPager;
import com.lib.lzlin.api.custom.slipViewPager.helperUtils.SlipViewPagerHelper;

import java.util.ArrayList;

/**
 * 项目名称: SlipViewPager 测试类
 * <p>
 * 类的描述: 测试类
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/5 10:58
 * 修改人:
 * 修改备注:
 */

public class TestSlipViewPagerActivity extends AppCompatActivity {
    ArrayList<Integer> mData=new ArrayList<>();
    private SlipViewPager viewPager;
    private Button btnPre;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initData();
        initUI();
    }

    private void initUI() {
        btnPre = (Button) findViewById(R.id.btnPre);
        btnNext = (Button) findViewById(R.id.btnNext);

        viewPager = (SlipViewPager) findViewById(R.id.mViewpager);
        viewPager.setAdapter(new CommonViewPagerAdapter<Integer>(this, R.layout.cardview_item, mData) {
            @Override
            public void initItemData(CommonViewHolder holder, int position, final Integer bean) {
                holder.setText4Tv(R.id.tv_name, "item" + bean)
                        .setOnClickListener(R.id.itemView, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext,  "item" + bean, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        // 注意必须在设置适配器之后才调用, 而且使用该控件的父布局必须是 wrap_content, 否则无法准确计算
        SlipViewPagerHelper.init(this, viewPager, false, 3, 16, btnPre, btnNext); // 不可滑动, 带指示器, 3个item, 间距为16dp
    }

    private void initData() {
        for(int i=0;i<5;i++){
            mData.add(i);
        }
    }
}
