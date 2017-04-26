package com.lib.lzlin.api.commonAdapter.recyclerViewAdapter;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: viewType - spanCount 权重分配数组
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/26 10:59
 * 修改人: lz - Administrator
 * 修改备注:
 */

public  class ViewType_SpanCount {
    public int viewType;
    public int spanCount;

    /**
     * 构造方法
     * @param viewType  item 的类型
     * @param spanCount 权重比
     */
    public ViewType_SpanCount(int viewType, int spanCount) {
        this.viewType = viewType;
        this.spanCount = spanCount;
    }
}
