package com.lib.lzlin.api.commonAdapter.viewpagerAdapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.lzlin.api.commonAdapter.list_gridViewAdapter.CommonViewHolder;

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

public abstract class CommonViewPagerAdapter<T> extends PagerAdapter {
    protected Context mContext; 		// 上下文对象
    protected List<T> mDatas;			// 要展示的数据列表
    protected LayoutInflater mInflater; // 布局转换器
    protected int mlayoutId; 			// 每个 item_layout 的布局ID

    /**
     * 带参构造方法
     *
     * @param context			上下文对象
     * @param itemLayoutResId	item_layout 布局资源ID, 例如R.layout.XXX
     * @param dataSource		要展示的数据列表, 注意数据源是一个对象, 对象传值, 不能为空
     */
    public CommonViewPagerAdapter(Context context, int itemLayoutResId, List<T> dataSource) {
        checkParams(context, itemLayoutResId, dataSource);
        this.mContext = context;
        this.mDatas = dataSource;
        this.mlayoutId = itemLayoutResId;
        mInflater = LayoutInflater.from(context);
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
                    "CommonViewPagerAdapter  --> checkParams  Fail\n" +
                            "context == null || itemLayoutResId < 0 || dataSource == null" +
                            " please check your params");
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 返回数据的总数
     *
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() { // 判空
        return mDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(container, position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 返回position位置的数据
     */
    public T getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 返回position位置的view, 即listview、gridview的第postion个view
     */
    public View getView(ViewGroup parent, int position) {
        CommonViewHolder holder = CommonViewHolder
                .getViewHolder(mContext, null, parent, mlayoutId, position);

        initItemData(holder,position, getItem(position));

        return holder.getConvertView();
    }

    /**
     * 用户必须覆写该方法来讲数据填充到视图中
     *
     * @param holder 			通用的ViewHolder, 里面会装载listview, gridview等组件的每一项的视图，并且缓存其子view
     * @param bean  			数据源的第position项数据
     */
    public abstract void initItemData(CommonViewHolder holder, int posiiton, T bean);
}
