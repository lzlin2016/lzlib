package com.lib.lzlin.api.commonAdapter.recyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: RecyclerViewHelper 辅助类, 简单使用该类
     mRecyclerView = findView(R.id.id_recyclerview);
    //设置布局管理器
    mRecyclerView.setLayoutManager(layout);
    //设置adapter
    mRecyclerView.setAdapter(adapter)
    //设置Item增加、移除动画
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    //添加分割线
     mRecyclerView.addItemDecoration(new DividerItemDecoration(
    getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/25 17:53
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class RecyclerViewHelper {
    public static final int LISTVIEW_LAYOUT = 1; // listView 布局:   LISTVIEW_LAYOUT
    public static final int GRIDVIEW_LAYOUT = 2; // GridVIew 布局:   GRIDVIEW_LAYOUT
    public static final int STAGGER_LAYOUT = 3;  // 瀑布流   布局:   STAGGER_LAYOUT
    public static final int VERTICAL = OrientationHelper.VERTICAL;   // 竖直方向
    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;// 水平方向

    /**
     * 线性布局
     * @param recyclerView   RecyclerView 对象
     * @param context       上下文context
     * @param orientation   方向orientation
     * @param reverseLayout 是否逆序reverseLayout
     */
    public static void setListManager(RecyclerView recyclerView, Context context, int orientation, boolean reverseLayout) {
        RecyclerView.LayoutManager layoutList = new LinearLayoutManager(context, orientation, reverseLayout);
        recyclerView.setLayoutManager(layoutList);
    }

    /**
     * GridView布局
     * @param recyclerView  RecyclerView 对象
     * @param context       上下文context
     * @param spanCount     每行 / 每列 有几个 item , 行数spanCount
     * @param orientation   方向orientation
     * @param reverseLayout 是否逆序reverseLayout
     */
    public static void setGridManager(RecyclerView recyclerView, Context context, int spanCount, int orientation, boolean reverseLayout) {
        // GridView布局(山下文context, 行数spanCount, 方向orientation, 是否逆序reverseLayout)
        GridLayoutManager layoutGrid = new GridLayoutManager(context, spanCount, orientation, reverseLayout);
        recyclerView.setLayoutManager(layoutGrid);
    }

    /**
     * 瀑布流
     * @param recyclerView  RecyclerView 对象
     * @param spanCount     每行 / 每列 有几个 item , 行数spanCount
     * @param orientation   方向orientation
     */
    public void setRecyclerManager(RecyclerView recyclerView, int spanCount, int orientation) {
        RecyclerView.LayoutManager layoutStagger = new StaggeredGridLayoutManager(spanCount, orientation);
        recyclerView.setLayoutManager(layoutStagger);
    }

    /**
     * 修改权重
     * @param recyclerView          RecyclerView 适配器对象
     * @param viewtype_spancount    viewType - spanCount 权重分配数组
     */
    public static void setSpanSizeLookup4Grid(final RecyclerView recyclerView,
                                         final ViewType_SpanCount... viewtype_spancount) {
        ((GridLayoutManager)recyclerView.getLayoutManager())
                .setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        for (int i = 0; i < viewtype_spancount.length; i++) {
                            if (recyclerView.getAdapter().getItemViewType(position) == viewtype_spancount[i].viewType) {
                                return viewtype_spancount[i].spanCount;
                            }
                        }
                        return 1;
                    }
                });
    }

    /**
     * 设置Item增加、移除动画
     * @param recyclerView  RecyclerView 对象
     */
    public static void setItemAnimator(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 设置Item增加、移除动画
     * @param recyclerView  RecyclerView 对象
     * @param animator      RecyclerView.ItemAnimator ,Item 增加、移除动画
     */
    public static void setItemAnimator(RecyclerView recyclerView, RecyclerView.ItemAnimator animator) {
        recyclerView.setItemAnimator(animator);
    }

    /**
     * 添加分割线
     * @param context        上下文对象
     * @param recyclerView   RecyclerView 对象
     */
    public static void addItemDecoration(Context context, RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
    }
}

