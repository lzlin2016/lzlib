package com.lib.lzlin.api.commonAdapter.recyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * 项目名称: Lib-lz
 *
 *      类的描述:这是一个通用、抽象的适配器类,覆写了BaseAdapter的
 * getCount, getItem, getItemId, getView方法,在getView方法中通过
 * 通用的CommonViewHolder来对convertView的进行处理,并且缓存convertView中的其他View元素，
 * 降低了ListView、GridView 等组件的Adapter和ViewHolder的代码量.
 * 用户只需要在fillItemData函数中将第position位置里的数据填充到listview
 * 或者gridview的第position的view中即可
 *      创建人:  lz - Administrator
 *      创建时间:  2017/3/27 9:54
 *      修改人:
 *      修改备注:
 *  @version 1.0
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext; 		// 上下文对象
    protected List<T> mDatas;			// 要展示的数据列表
    protected int mlayoutId; 			// 每个 item_layout 的布局ID

    /**
     * 带参构造方法
     *
     * @param context			上下文对象
     * @param itemLayoutResId	item_layout 布局资源ID, 例如R.layout.XXX
     * @param dataSource		要展示的数据列表, 注意数据源是一个对象, 对象传值, 不能为空
     */
    public CommonRecyclerAdapter(Context context, int itemLayoutResId, List<T> dataSource) {
        checkParams(context, itemLayoutResId, dataSource);
        this.mContext = context;
        this.mDatas = dataSource;
        this.mlayoutId = itemLayoutResId;
    }

    /**
     * 检查参数的有效性
     *
     * @param context			上下文对象
     * @param itemLayoutResId	item_layout 布局资源ID, 例如R.layout.XXX
     * @param dataSource	 	要展示的数据列表
     */
    private void checkParams(Context context, int itemLayoutResId, List<T> dataSource) {
        if (context == null || itemLayoutResId < 0 || dataSource == null) {
            throw new RuntimeException(
                    "CommonRecyclerAdapter  --> checkParams  Fail\n" +
                            "context == null || itemLayoutResId < 0 || dataSource == null" +
                            " please check your params");
        }
    }

    /**
     * 返回数据的总数
     *
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getItemCount() { // 判空
        return mDatas.size();
    }

    /**
     * 返回position位置的数据
     *
     * @see android.widget.Adapter#getItem(int)
     */
    public T getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 获取当前item 的位置, 即下标索引
     * @param holder
     * @return
     */
    private int getPosition(CommonRecyclerViewHolder holder) {
        return holder.getAdapterPosition();
    }

    /**
     * 渲染具体的ViewHolder  // TODO lz viewType 决定元素的布局使用哪种类型, 若是有多种布局时, 需要重写该方法
     *
     * @param parent   ViewHolder的容器
     * @param viewType 一个标志，我们根据该标志可以实现渲染不同类型的ViewHolder
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonRecyclerViewHolder holder = CommonRecyclerViewHolder
                // newInstance(Context context, View convertView, ViewGroup parent, int layoutId, int viewType)
                .newInstance(mContext, null, parent, mlayoutId, viewType);
        return holder;
    }

    /**
     * 初始化数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        initItemData((CommonRecyclerViewHolder)holder, position, getItem(position));
    }

    /**
     * 用户必须覆写该方法来讲数据填充到视图中
     *
     * @param holder 			通用的ViewHolder, 里面会装载listview, gridview等组件的每一项的视图，并且缓存其子view
     * @param position 		下标索引
     * @param bean  			数据源的第position项数据
     */
    public abstract void initItemData(CommonRecyclerViewHolder holder, int position, T bean);
}
