package com.lib.lzlin.api.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.lib.lzlin.api.R;
import com.lib.lzlin.api.commonAdapter.recyclerViewAdapter.CommonRecyclerAdapter;
import com.lib.lzlin.api.commonAdapter.recyclerViewAdapter.CommonRecyclerViewHolder;
import com.lib.lzlin.api.commonAdapter.recyclerViewAdapter.RecyclerViewHelper;
import com.lib.lzlin.api.commonAdapter.recyclerViewAdapter.ViewType_SpanCount;
import com.lib.lzlin.api.model.TempBean;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerActivity extends AppCompatActivity {
    private List<TempBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler);

        initData();
        initUI();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mData.add(new TempBean());
        }
    }

    private void initUI() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        Log.e("initUI", "mRecyclerView == null ? " + (mRecyclerView == null));
        // TODO lz setGridManager 可以调节权重比
        RecyclerViewHelper.setGridManager(mRecyclerView, this, 3, RecyclerViewHelper.VERTICAL, false);
        RecyclerViewHelper.setSpanSizeLookup4Grid(mRecyclerView, new ViewType_SpanCount[]{new ViewType_SpanCount(1, 1), new ViewType_SpanCount(3, 3)});
        // TODO lz setListManager 可以增加默认行间距
//        RecyclerViewHelper.setListManager(mRecyclerView, this,  RecyclerViewHelper.VERTICAL, false);
//        RecyclerViewHelper.addItemDecoration(this, mRecyclerView);
        mRecyclerView.setAdapter(new CommonRecyclerAdapter<TempBean>(TestRecyclerActivity.this, R.layout.vp_item, mData) {
            // TODO lz 如果需要修改权重, 则需要重写 getItemViewType 方法
            @Override
            public int getItemViewType(int position) {
                if (position % (3 + 1) == 0) {
                    return 3;
                }
                return super.getItemViewType(position);
            }

            @Override
            public void initItemData(CommonRecyclerViewHolder holder, int position, TempBean bean) {
                holder.setText4Tv(R.id.tvTitle, "卡片 " + position);
                holder.getConvertView().setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 650));
            }
        });
    }
}
