package com.lib.lzlin.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 所有自定义控件的基类
 * 创建人: lz - Administrator
 * 创建时间:  2017/6/6 18:04
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class BaseWidget {
    private SparseArray<View> mViews; 	// 控件集合列表, 避免重复查找控件
    private View mConvertView; 			// 当前项的convertView

    /**
     * 通过viewId 获取控件
     *
     * @param viewId	控件ID
     * @return			查找到的控件
     */
    public <T extends View>T getViewById(int viewId) {
        View view = mViews.get(viewId);	// 先通过控件集合列表获取, 避免重复查找控件

        if (null == view) {				// 若是获取不到, 就冲新查找控件
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);	// 将查找到的控件放入控件集合列表, 以便再次获取
        }

        return (T) view;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字text ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param text			设置的文本内容
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setText4Tv (int textViewId, String text)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throw new RuntimeException("CommonRecyclerViewHolder  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(null == text ? "" : text);
        }

        return this;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字text ，并返回 this
     *
     * @param context		上下文对象
     * @param textViewId	TextView 的控件 ID
     * @param resId		设置的文本内容资源ID, R.string.XXX
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setText4Tv (Context context, int textViewId, int resId)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throw new RuntimeException("CommonRecyclerViewHolder  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(context.getResources().getString(resId));
        }

        return this;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字颜色 ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param color		设置的颜色
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setColor4Tv (int textViewId, int color)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throw new RuntimeException("CommonRecyclerViewHolder  --> setColor4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setTextColor(color);
        }

        return this;
    }

    /**
     * 给ID为 imageViewId 的 ImageView 设置 图片 ，并返回 this
     *
     * @param imageViewId	ImageView 的id, 例如R.id.my_imageview
     * @param drawableId	Drawable图片的id, 例如R.drawable.my_photo
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setImage4Img (int imageViewId, int drawableId)	{
        ImageView img = getViewById(imageViewId);
        if (null == img) {		// 判空
            throw new RuntimeException("CommonRecyclerViewHolder  --> setImage4Img  Fail\n"
                    + "img == null, please check your imageViewId");
        } else {
            img.setImageResource(drawableId);
        }

        return this;
    }

    /**
     * 给ID为 imageViewId 的 ImageView 设置 图片 ，并返回 this
     *
     * @param imageViewId	ImageView 的id, 例如R.id.my_imageview
     * @param bitmap       Bitmap图片
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setImage4Img (int imageViewId, Bitmap bitmap)	{
        ImageView img = getViewById(imageViewId);
        if (null == img) {        // 判空
            throw new RuntimeException("CommonRecyclerViewHolder  --> setImage4Img  Fail\n"
                    + "img == null, please check your imageViewId");
        } else if (null == bitmap) {	// 判空
            throw new RuntimeException("CommonRecyclerViewHolder  --> setImage4Img  Fail\n"
                    + "bitmap == null, please check your bitmap resource");
        } else {
            img.setImageBitmap(bitmap);
        }

        return this;
    }

    // TODO lz 根据实际需要, 添加 UIL 和 Fresco 等方法, 加载网络图片, 或者其他类型的图片

    /**
     * 给ID为 checkViewId 的 CheckBox 设置 选中选中状态
     *
     * @param checkViewId 	CheckBox 的id
     * @param isCheck    	 是否选中
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setCheck4CheckBox(int checkViewId, boolean isCheck) {
        CheckBox checkBox = getViewById(checkViewId);
        if (null == checkBox) {        // 判空
            throw new RuntimeException("CommonRecyclerViewHolder  --> setCheck4CheckBox  Fail\n"
                    + "checkBox == null, please check your checkViewId");
        } else {
            checkBox.setChecked(isCheck);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置是否选中
     *
     * @param viewId		View 的id
     * @param visibility	是否可见
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setVisibility (int viewId, int visibility) {
        View view = getViewById(viewId);
        if (null == view) {
            throw new RuntimeException("CommonRecyclerViewHolder  --> setVisibility  Fail\n"
                    + "view == null, please check your viewId");
        } else {	// View 的可见状态有且仅有三种 VISIBLE, INVISIBLE, GONE , 默认设置 VISIBLE
            view.setVisibility(visibility == View.VISIBLE ? View.VISIBLE :
                    (visibility == View.INVISIBLE ? View.INVISIBLE :
                            (visibility == View.GONE ? View.GONE : View.VISIBLE) ) );
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置是否可点击
     *
     * @param viewId		View 的id
     * @param clickAble	是否可点击
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setClickEnablility (int viewId, boolean clickAble) {
        View view = getViewById(viewId);
        if (null == view) {
            throw new RuntimeException("CommonRecyclerViewHolder  --> setClickEnablility  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setClickable(clickAble);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置 监听事件
     *
     * @param viewId		View 的id
     * @param listener		点击监听事件
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setOnClickListener (int viewId, View.OnClickListener listener) {
        View view = getViewById(viewId);
        if (null == view) {
            throw new RuntimeException("CommonRecyclerViewHolder  --> setOnClickListener  Fail\n"
                    + "view == null, please check your viewId");
        } else if (null == listener) {
            throw new RuntimeException("CommonRecyclerViewHolder  --> setOnClickListener  Fail\n"
                    + "listener == null, please check your listener");
        } else {
            view.setOnClickListener(listener);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置 触摸事件
     *
     * @param viewId		View 的id
     * @param listener		触摸监听事件
     * @return 			CommonRecyclerViewHolder 本身
     */
    public BaseWidget setOnTouchListener (int viewId, View.OnTouchListener listener) {
        View view = getViewById(viewId);
        if (null == view) {
            throw new RuntimeException("CommonRecyclerViewHolder  --> setOnTouchListener  Fail\n"
                    + "view == null, please check your viewId");
        } else if (null == listener) {
            throw new RuntimeException("CommonRecyclerViewHolder  --> setOnTouchListener  Fail\n"
                    + "listener == null, please check your listener");
        } else {
            view.setOnTouchListener(listener);
        }

        return this;
    }

}
