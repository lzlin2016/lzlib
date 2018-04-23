package com.lib.lzlin.api.utils.uiUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lib.lzlin.api.application.BaseApp;
import com.lib.lzlin.api.commonAdapter.list_gridViewAdapter.CommonViewHolder;
import com.lib.lzlin.api.utils.commonUtils.CommonUtils;
import com.lib.lzlin.api.utils.netUtils.GlideUtils;

/**
 * UIHelper
 *
 * 类的描述: UI 控件 辅助类
 * 创建人: lz -  Administrator
 * 创建时间: 2017/6/13  16:53
 * 修改人: lz -  Administrator
 * 修改备注:
 */

public class UIHelper {
    /**
     * SparseArray 简介: Key - Value 键对, 内部通过两个数组来进行数据存储
     * 满足下面两个条件我们可以使用SparseArray代替HashMap：
     *
     * 	1) 数据量不大，最好在千级以内
     * 	2) key必须为int类型，这中情况下的HashMap可以用SparseArray代替：
     */
    private Context mContext; 			// 上下文对象
    private View mConvertView; 			// 当前项的convertView
    private SparseArray<View> mViews; 	// 控件集合列表, 避免重复查找控件
    private final boolean debug = true; // 是否是调试模式, 当且仅当调试模式才会抛异常
    private final String NULL = debug ? "NULL" : ""; // 当数据为null时, 显示的内容, 统一配置

    /**
     * 直接传入父布局
     *
     * @param ctx       上下文对象
     * @param mLayout   父布局
     */
    private UIHelper(Context ctx, View mLayout) {
        init(ctx, mLayout);
    }

    /**
     * 使用父布局ID获取更布局
     *
     * @param ctx       上下文对象
     * @param resId     父布局布局ID
     */
    private UIHelper(Context ctx, int resId) {
        init(ctx, LayoutInflater.from(ctx).inflate(resId, null));
    }

    /**
     * 直接传入Activity 对象, 会通过获取Activity根布局作为父布局
     *
     * @param act       Activity 对象
     */
    private UIHelper(Activity act) {
        init(act, (ViewGroup) act.findViewById(android.R.id.content));
    }

    /**
     * 直接传入Fragment 对象, 会通过获取Fragment 根布局作为父布局
     *
     * @param fragment  Fragment 对象
     */
    private UIHelper(Fragment fragment) {
        init(fragment.getContext(), (ViewGroup) fragment.getView().getParent());
    }

    /**
     * 获取BaseHelper 实例
     *
     * @return  UIHelper 应用实例
     */
    public static UIHelper getInstance(Context ctx, View mLayout) {
        return new UIHelper(ctx, mLayout);
    }
    public static UIHelper getInstance(Context ctx, int resId) {
        return new UIHelper(ctx, resId);
    }
    public static UIHelper getInstance(Activity activity) {
        return new UIHelper(activity);
    }
    public static UIHelper getInstance(Fragment faragment) {
        return new UIHelper(faragment);
    }

    /**
     * 初始化 配置
     *
     * @param ctx
     * @param mLayout
     */
    private void init(Context ctx, View mLayout) {
        this.mContext = ctx;
        this.mViews = new SparseArray<>();
        this.mConvertView = mLayout;
    }

    /**
     * 获取 上下文对象
     *
     * @return
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取到根目录
     *
     * @return
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过viewId 获取控件
     *
     * @param viewId	控件ID
     * @return		查找到的控件
     */
    public <T extends View>T getViewById(int viewId) {
        View view = mViews.get(viewId);	// 先通过控件集合列表获取, 避免重复查找控件

        if (null == view) {				// 若是获取不到, 就冲新查找控件
            view = mConvertView.findViewById(viewId);
            if (view == null) {
                throwRuntimeException("UIHelper  --> getViewById  Fail\n"
                        + "view == null, please check your viewId");
            } else {
                mViews.put(viewId, view);	// 将查找到的控件放入控件集合列表, 以便再次获取
            }
        }

        return (T) view;
    }

    /**
     * 抛出异常, 根据是否是调试模式, 当且仅当调试模式才会抛异常, 异常统一管理
     *
     * @param execption 异常内容
     */
    private void throwRuntimeException(String execption) {
        if (debug) {
            throw new RuntimeException(execption);
        } else {
            // TODO 异常统一处理
        }
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字text ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param text			设置的文本内容
     * @return 			UIHelper 本身
     */
    public UIHelper setText4Tv (int textViewId, String text)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throwRuntimeException("UIHelper  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(null == text ? NULL : text);
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
    public UIHelper setText4Tv (int textViewId, String text, String defaultText)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throwRuntimeException("UIHelper  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(null == text ? defaultText : text);
        }

        return this;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字 多彩text ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param text			设置的文本内容
     * @return 			UIHelper 本身
     */
    public UIHelper setText4Tv (int textViewId, SpannableStringBuilder text)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throwRuntimeException("UIHelper  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(null == text ? NULL : text);
        }

        return this;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字text ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param resId		    设置的文本内容资源ID, R.string.XXX
     * @return 			UIHelper 本身
     */
    public UIHelper setText4Tv (int textViewId, int resId)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throwRuntimeException("UIHelper  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(CommonUtils.getResources().getString(resId));
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 最大可输入长度 ，并返回 this
     *
     * @param edtId	        EditText 的控件 ID
     * @param length		设置的文本内容, 最大可输入长度
     * @return 			UIHelper 本身
     */
    public UIHelper setMaxLength4Edt (int edtId, int length)	{
        EditText edt = getViewById(edtId);
        if (null == edt) {		// 判空
            throwRuntimeException("UIHelper  --> setMaxLength4Edt  Fail\n"
                    + "edt == null, please check your edtId");
        } else if(length <= 0) {
            throwRuntimeException("UIHelper  --> setMaxLength4Edt  Fail\n"
                    + "length <= 0, please check your length");
        } else {
            edt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 hint ，并返回 this
     *
     * @param edtId	        EditText 的控件 ID
     * @param resId		    设置的文本内容资源ID, R.string.XXX
     * @return 			UIHelper 本身
     */
    public UIHelper setHintResId4Edt (int edtId, int resId)	{
        EditText edt = getViewById(edtId);
        String hint = CommonUtils.getResources().getString(resId);
        if (null == edt) {		// 判空
            throwRuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "edt == null, please check your edtId");
        } else if(null == hint) {
            throwRuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "hint == null, please check your resId");
        } else {
            edt.setHint(hint);
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 hint ，并返回 this
     *
     * @param edtId	        EditText 的控件 ID
     * @param hint		    设置的文本内容
     * @return 			UIHelper 本身
     */
    public UIHelper setHint4Edt (int edtId, String hint)	{
        EditText edt = getViewById(edtId);
        if (null == edt) {		// 判空
            throwRuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "edt == null, please check your edtId");
        } else if(null == hint) {
            throwRuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "hint == null, please check your hint");
        }else {
            edt.setHint(hint);
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 文本 ，并返回 this
     *
     * @param edtId 	    EditText 的控件 ID
     * @param text		    设置的文本内容
     * @return 			UIHelper 本身
     */
    public UIHelper setText4Edt (int edtId, String text)	{
        EditText edt = getViewById(edtId);
        if (null == edt) {		// 判空
            throwRuntimeException("UIHelper  --> setText4Edt  Fail\n"
                    + "edt == null, please check your edtId");
        } else if(null == text) {
            throwRuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "hint == null, please check your text");
        }else {
            edt.setText(text);
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 是否可编辑 ，并返回 this
     *
     * @param edtId 	    EditText 的控件 ID
     * @param editable	    是否可编辑
     * @return 		   UIHelper 本身
     */
    public UIHelper setEnabled (int edtId, boolean editable)	{
        EditText edt = getViewById(edtId);
        if (null == edt) {		// 判空
            throwRuntimeException("UIHelper  --> setEnabled  Fail\n"
                    + "edt == null, please check your edtId");
        }else {
            edt.setEnabled(editable);
        }

        return this;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字颜色 ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param colorRes 	    设置的颜色res
     * @return 			UIHelper 本身
     */
    public UIHelper setColorRes4Tv (int textViewId, int colorRes)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throwRuntimeException("UIHelper  --> setColorRes4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setTextColor(CommonUtils.getColor(colorRes));
        }

        return this;
    }


    /**
     * 给ID为 textViewId 的 TextView 设置 文字颜色 ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param color 	    设置的颜色
     * @return 			UIHelper 本身
     */
    public UIHelper setColor4Tv (int textViewId, int color)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throwRuntimeException("UIHelper  --> setColor4Tv  Fail\n"
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
     * @return 			UIHelper 本身
     */
    public UIHelper setImage4Img (int imageViewId, int drawableId)	{
        ImageView img = getViewById(imageViewId);
        if (null == img) {		// 判空
            throwRuntimeException("UIHelper  --> setImage4Img  Fail\n"
                    + "img == null, please check your imageViewId");
        } else {
            img.setImageResource(drawableId);
        }

        return this;
    }

    /**
     * 给ID为 imageViewId 的 ImageView 设置 图片 ，并返回 this
     *
     * @param imageViewId   ImageView 的id, 例如R.id.my_imageview
     * @param bitmap        Bitmap图片
     * @return 			UIHelper 本身
     */
    public UIHelper setImage4Img (int imageViewId, Bitmap bitmap)	{
        ImageView img = getViewById(imageViewId);
        if (null == img) {        // 判空
            throwRuntimeException("UIHelper  --> setImage4Img  Fail\n"
                    + "img == null, please check your imageViewId");
        } else if (null == bitmap) {	// 判空
            throwRuntimeException("UIHelper  --> setImage4Img  Fail\n"
                    + "bitmap == null, please check your bitmap resource");
        } else {
            img.setImageBitmap(bitmap);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 View 设置 透明度 ，并返回 this
     *
     * @param viewId        控件ID
     * @param alpha         透明度
     * @return          UIHelper 本身
     */
    public UIHelper setAlpha (int viewId, float alpha)	{
        View view = getViewById(viewId);
        if (null == view) {        // 判空
            throwRuntimeException("UIHelper  --> setAlpha  Fail\n"
                    + "img == null, please check your viewId");
        } else {
            view.setAlpha(alpha);
        }

        return this;
    }

    // TODO lz 根据实际需要, 添加 UIL 和 Fresco 等方法, 加载网络图片, 或者其他类型的图片

    /**
     * 给ID为 imageViewId 的 ImageView 设置 图片 ，并返回 this
     *
     * @param imageViewId	ImageView 的id, 例如R.id.my_imageview
     * @param url          图片地址
     * @return 			UIHelper 本身
     */
    public UIHelper setUrl4Img(int imageViewId, String url) {
        ImageView img = getViewById(imageViewId);
        if (null == img) {        // 判空
            throwRuntimeException("UIHelper  --> setUrl4Img  Fail\n"
                    + "img == null, please check your imageViewId");
        } else if (null == url) {	// 判空
            throwRuntimeException("UIHelper  --> setUrl4Img  Fail\n"
                    + "bitmap == null, please check your url resource");
        } else {
            GlideUtils.getInstance().GlideImage(BaseApp.getApplication(), url, img);
        }

        return this;
    }

    /**
     * 给ID为 checkViewId 的 CheckBox 设置 选中选中状态
     *
     * @param checkViewId 	CheckBox 的id
     * @param isCheck    	 是否选中
     * @return 			UIHelper 本身
     */
    public UIHelper setCheck4CheckBox(int checkViewId, boolean isCheck) {
        CheckBox checkBox = getViewById(checkViewId);
        if (null == checkBox) {        // 判空
            throwRuntimeException("UIHelper  --> setCheck4CheckBox  Fail\n"
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
     * @return 			UIHelper 本身
     */
    public UIHelper setVisibility (int viewId, int visibility) {
        View view = getViewById(viewId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> setVisibility  Fail\n"
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
     * @param clickAble	    是否可点击
     * @return 			UIHelper 本身
     */
    public UIHelper setClickEnablility (int viewId, boolean clickAble) {
        View view = getViewById(viewId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> setClickEnablility  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setClickable(clickAble);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置是否选中
     *
     * @param viewId		View 的id
     * @param isSelected	是否选中
     * @return 			UIHelper 本身
     */
    public UIHelper setSelected (int viewId, boolean isSelected) {
        View view = getViewById(viewId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> setSelected  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setSelected(isSelected);
        }

        return this;
    }


    /**
     * 给ID为 viewId 的 控件 设置 背景
     *
     * @param viewId		View 的id
     * @param resId	        背景资源 ID
     * @return 			UIHelper 本身
     */
    public UIHelper setBackGround (int viewId, int resId) {
        View view = getViewById(viewId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> setClickBackGround  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setBackgroundResource(resId);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置 监听事件
     *
     * @param viewId		View 的id
     * @param listener		点击监听事件
     * @return 			UIHelper 本身
     */
    public UIHelper setOnClickListener (int viewId, View.OnClickListener listener) {
        View view = getViewById(viewId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> setOnClickListener  Fail\n"
                    + "view == null, please check your viewId");
        } else if (null == listener) {
            throwRuntimeException("UIHelper  --> setOnClickListener  Fail\n"
                    + "listener == null, please check your listener");
        } else {
            view.setOnClickListener(listener);
        }

        return this;
    }

    /**
     * 给整个item布局 设置 监听事件
     *
     * @param listener		点击监听事件
     * @return 			UIHelper 本身
     */
    public UIHelper setOnItemClickListener (View.OnClickListener listener) {
        if (null == mConvertView) {
            throwRuntimeException("UIHelper  --> setOnItemClickListener  Fail\n"
                    + "convertView == null, please check your convertView");
        } else if (null == listener) {
            throwRuntimeException("UIHelper  --> setOnItemClickListener  Fail\n"
                    + "listener == null, please check your listener");
        } else {
            getConvertView().setOnClickListener(listener);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置 触摸事件
     *
     * @param viewId		View 的id
     * @param listener		触摸监听事件
     * @return 			UIHelper 本身
     */
    public UIHelper setOnTouchListener (int viewId, View.OnTouchListener listener) {
        View view = getViewById(viewId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> setOnTouchListener  Fail\n"
                    + "view == null, please check your viewId");
        } else if (null == listener) {
            throwRuntimeException("UIHelper  --> setOnTouchListener  Fail\n"
                    + "listener == null, please check your listener");
        } else {
            view.setOnTouchListener(listener);
        }

        return this;
    }

    /**
     * 给ID为 viewId 的 控件 设置 焦点
     *
     * @param viewId		View 的id
     * @return 			UIHelper 本身
     */
    public UIHelper setFocusable (int viewId) {
        View view = getViewById(viewId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> setOnTouchListener  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setFocusable(true);
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 控件 获取文本
     *
     * @param edtId		    EditText 的id
     * @return 			UIHelper 本身
     */
    public String getEdtText (int edtId) {
        View view = getViewById(edtId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> getEdtText  Fail\n"
                    + "view == null, please check your edtId");
        } else if (view instanceof EditText) {
            return ((EditText) view).getText().toString();
        } else {
            throwRuntimeException("UIHelper  --> getEdtText  Fail\n"
                    + "view is no an EditText, please check your edtId");
        }

        return NULL;
    }

    /**
     * 给ID为 edtId 的 控件 获取文本, 并取消头部空格
     *
     * @param edtId		    EditText 的id
     * @return 			UIHelper 本身
     */
    public String getEdtTrimText(int edtId) {
        return getEdtText(edtId).trim();
    }

    /**
     * 给ID为 viewId 的 TextView 控件 获取文本
     *
     * @param tvId		    TextView 的id
     * @return 			UIHelper 本身
     */
    public String getTvText (int tvId) {
        View view = getViewById(tvId);
        if (null == view) {
            throwRuntimeException("UIHelper  --> getTvText  Fail\n"
                    + "view == null, please check your tvId");
        } else if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        } else {
            throwRuntimeException("UIHelper  --> getTvText  Fail\n"
                    + "view is no an EditText, please check your tvId");
        }

        return NULL;
    }

    /**
     * 给ID为 viewId 的 TextView 控件 获取文本, 并取消头部空格
     *
     * @param tvId		    TextView 的id
     * @return 			UIHelper 本身
     */
    public String getTvTrimText(int tvId) {
        return getTvText(tvId).trim();
    }

    /**
     * 设置TextView 渐变
     * @param textViewId        tv 控件ID
     * @param startColor        开始颜色
     * @param endColor      结束颜色
     * @return
     */
    public UIHelper setText4TvGradient(int textViewId, String startColor, String endColor) {
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throwRuntimeException("UIHelper  --> setText4TvGradient  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            LinearGradient mLinearGradient = new LinearGradient(0, 0, 0, tv.getPaint().getTextSize(),
                    Color.parseColor(startColor), Color.parseColor(endColor),
                    Shader.TileMode.CLAMP);
            tv.getPaint().setShader(mLinearGradient);
        }

        return this;
    }

    /**
     * 设置TextView 渐变
     * @param textViewId            tv 控件ID
     * @param startColorRes         开始颜色 资源ID
     * @param endColorRes       结束颜色 资源ID
     * @return
     */
    public UIHelper setText4TvGradient(int textViewId, int startColorRes, int endColorRes) {
        return setText4TvGradient(textViewId, CommonUtils.getColor(startColorRes), CommonUtils.getColor(endColorRes));
    }
}
