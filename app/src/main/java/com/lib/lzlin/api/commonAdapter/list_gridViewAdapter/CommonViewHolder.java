package com.lib.lzlin.api.commonAdapter.list_gridViewAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 项目名称: Lib-lz
 * <p>
 * 		类的描述:这是一个通用的ViewHolder, 将会装载AbsListView子类的item View,
 * 并且将item view中的子视图进行缓存和索引，使得用户能够方便的获取这些子view, 减少了代码重复。
 * 		创建人: lz - Administrator
 * 		创建时间:  2017/3/27 9:54
 * 		修改人:
 * 		修改备注:
 */

public class CommonViewHolder {

	/**
	 * SparseArray 简介: Key - Value 键对, 内部通过两个数组来进行数据存储
	 * 满足下面两个条件我们可以使用SparseArray代替HashMap：
	 *
	 * 	1) 数据量不大，最好在千级以内
	 * 	2) key必须为int类型，这中情况下的HashMap可以用SparseArray代替：
	 */
	private SparseArray<View> mViews; 	// 控件集合列表, 避免重复查找控件
	private int mPosition; 				// 下标索引
	private View mConvertView; 			// 当前项的convertView

	/**
	 * 带参构造方法
	 *
	 * @param context  	上下文对象
	 * @param layoutId 	ListView、GridView或者其他AbsListVew子类的 Item View的资源布局ID
	 * @param position 	下标索引
	 */
	protected CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.mViews = new SparseArray<View>();
		this.mPosition = position;
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		this.mConvertView.setTag(this);
	}

	/**
	 * 获取CommonViewHolder，当convertView为空的时候从布局xml装载item view,
	 * 并且将该CommonViewHolder设置为convertView的tag, 便于复用convertView.
	 *
	 * @param context     	Context
	 * @param convertView 	Item view
	 * @param layoutId    	布局资源id, 例如R.layout.my_listview_item.
	 * @param position    	下标索引
	 * @return 			通用的CommonViewHolder实例
	 */
	public static CommonViewHolder getViewHolder(Context context, View convertView,
												 ViewGroup parent, int layoutId, int position) {
		if (null == convertView) {		// 空则新建
			return new CommonViewHolder(context, parent, layoutId, position);
		} else {						// 非空则获取
			CommonViewHolder holder = (CommonViewHolder) convertView.getTag();
			holder.mPosition = position;// 设置位置

			return holder;
		}
	}

	/**
	 * 当前项的convertView, 在构造函数中装载
	 *
	 * @return 		当前项的convertView
	 */
	public View getConvertView() {
		return mConvertView;
	}

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
	public CommonViewHolder setText4Tv (int textViewId, String text)	{
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
	 * 给ID为 textViewId 的 TextView 设置 文字 多彩text ，并返回 this
	 *
	 * @param textViewId				TextView 的控件 ID
	 * @param spannableStringBuilder	设置的文本内容
	 * @return 						UIHelper 本身
	 */
	public CommonViewHolder setText4Tv (int textViewId, SpannableStringBuilder spannableStringBuilder)	{
		TextView tv = getViewById(textViewId);
		if (null == tv) {		// 判空
			throw new RuntimeException("UIHelper  --> setText4Tv  Fail\n"
					+ "tv == null, please check your textViewId");
		} else {
			tv.setText(null == spannableStringBuilder ? "" : spannableStringBuilder);
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
	public CommonViewHolder setText4Tv (Context context, int textViewId, int resId)	{
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
	 * 给ID为 textViewId 的 TextView 设置 文字text ，并返回 this
	 *
	 * @param textViewId	TextView 的控件 ID
	 * @param text			设置的文本内容
	 * @param defaultText	当text == null 时设置的文本内容
	 * @return 			CommonRecyclerViewHolder 本身
	 */
	public CommonViewHolder setText4Tv (int textViewId, String text, String defaultText)	{
		TextView tv = getViewById(textViewId);
		if (null == tv) {		// 判空
			throw new RuntimeException("CommonRecyclerViewHolder  --> setText4Tv  Fail\n"
					+ "tv == null, please check your textViewId");
		} else {
			tv.setText(null == text ? defaultText : text);
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
	public CommonViewHolder setColor4Tv (int textViewId, int color)	{
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
	public CommonViewHolder setImage4Img (int imageViewId, int drawableId)	{
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
	public CommonViewHolder setImage4Img (int imageViewId, Bitmap bitmap)	{
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
	public CommonViewHolder setCheck4CheckBox(int checkViewId, boolean isCheck) {
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
	public CommonViewHolder setVisibility (int viewId, int visibility) {
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
	public CommonViewHolder setClickEnablility (int viewId, boolean clickAble) {
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
	 * 给每个 itemlayout 设置 监听事件
	 *
	 * @param listener		点击监听事件
	 * @return 			CommonRecyclerViewHolder 本身
	 */
	public CommonViewHolder setOnItemClickListener (View.OnClickListener listener) {
		if (null == mConvertView) {
			throw new RuntimeException("CommonRecyclerViewHolder  --> setOnItemClickListener  Fail\n"
					+ "view == null, please check your viewId");
		} else if (null == listener) {
			throw new RuntimeException("CommonRecyclerViewHolder  --> setOnItemClickListener  Fail\n"
					+ "listener == null, please check your listener");
		} else {
			mConvertView.setOnClickListener(listener);
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
	public CommonViewHolder setOnClickListener (int viewId, View.OnClickListener listener) {
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
	public CommonViewHolder setOnTouchListener (int viewId, View.OnTouchListener listener) {
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

	// TODO lz 根据实际需要, 添加其他方法
}
