package com.lib.lzlin.api.utils.uiUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lib.lzlin.api.application.BaseApp;
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
    private Context mContext;
    private SparseArray<View> mViews;
    private View mConvertView;

    private UIHelper(Context ctx, View mLayout) {
        init(ctx, mLayout);
    }

    private UIHelper(Context ctx, int resId) {
        init(ctx, LayoutInflater.from(ctx).inflate(resId, null));
    }

    /**
     * 获取BaseHelper 实例
     *
     * @param ctx
     * @param mLayout
     * @return
     */
    public static UIHelper getInstance(Context ctx, View mLayout) {
        return new UIHelper(ctx, mLayout);
    }
    public static UIHelper getInstance(Context ctx, int resId) {
        return new UIHelper(ctx, resId);
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
     * @return			查找到的控件
     */
    public <T extends View>T getViewById(int viewId) {
        View view = mViews.get(viewId);	// 先通过控件集合列表获取, 避免重复查找控件

        if (null == view) {				// 若是获取不到, 就冲新查找控件
            view = mConvertView.findViewById(viewId);
            if (view == null) {
                throw new RuntimeException("UIHelper  --> getViewById  Fail\n"
                        + "view == null, please check your viewId");
            }
            mViews.put(viewId, view);	// 将查找到的控件放入控件集合列表, 以便再次获取
        }

        return (T) view;
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
            throw new RuntimeException("UIHelper  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(null == text ? "" : text);
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
            throw new RuntimeException("UIHelper  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(null == text ? "" : text);
        }

        return this;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字text ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param resId		设置的文本内容资源ID, R.string.XXX
     * @return 			UIHelper 本身
     */
    public UIHelper setText4Tv (int textViewId, int resId)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throw new RuntimeException("UIHelper  --> setText4Tv  Fail\n"
                    + "tv == null, please check your textViewId");
        } else {
            tv.setText(CommonUtils.getResources().getString(resId));
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 最大可输入长度 ，并返回 this
     *
     * @param edtId	    EditText 的控件 ID
     * @param length		设置的文本内容, 最大可输入长度
     * @return 			UIHelper 本身
     */
    public UIHelper setMaxLength4Edt (int edtId, int length)	{
        EditText edt = getViewById(edtId);
        if (null == edt) {		// 判空
            throw new RuntimeException("UIHelper  --> setMaxLength4Edt  Fail\n"
                    + "edt == null, please check your edtId");
        } else if(length <= 0) {
            throw new RuntimeException("UIHelper  --> setMaxLength4Edt  Fail\n"
                    + "length <= 0, please check your length");
        } else {
            edt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 hint ，并返回 this
     *
     * @param edtId	    EditText 的控件 ID
     * @param resId		设置的文本内容资源ID, R.string.XXX
     * @return 			UIHelper 本身
     */
    public UIHelper setHint4Edt (int edtId, int resId)	{
        EditText edt = getViewById(edtId);
        String hint = CommonUtils.getResources().getString(resId);
        if (null == edt) {		// 判空
            throw new RuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "edt == null, please check your edtId");
        } else if(null == hint) {
            throw new RuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "hint == null, please check your resId");
        } else {
            edt.setHint(hint);
        }

        return this;
    }

    /**
     * 给ID为 edtId 的 EditText 设置 hint ，并返回 this
     *
     * @param edtId	EditText 的控件 ID
     * @param hint		设置的文本内容
     * @return 		UIHelper 本身
     */
    public UIHelper setHint4Edt (int edtId, String hint)	{
        EditText edt = getViewById(edtId);
        if (null == edt) {		// 判空
            throw new RuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "edt == null, please check your edtId");
        } else if(null == hint) {
            throw new RuntimeException("UIHelper  --> setHint4Edt  Fail\n"
                    + "hint == null, please check your hint");
        }else {
            edt.setHint(hint);
        }

        return this;
    }

    /**
     * 给ID为 textViewId 的 TextView 设置 文字颜色 ，并返回 this
     *
     * @param textViewId	TextView 的控件 ID
     * @param color		设置的颜色
     * @return 			UIHelper 本身
     */
    public UIHelper setColor4Tv (int textViewId, int color)	{
        TextView tv = getViewById(textViewId);
        if (null == tv) {		// 判空
            throw new RuntimeException("UIHelper  --> setColor4Tv  Fail\n"
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
            throw new RuntimeException("UIHelper  --> setImage4Img  Fail\n"
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
     * @return 			UIHelper 本身
     */
    public UIHelper setImage4Img (int imageViewId, Bitmap bitmap)	{
        ImageView img = getViewById(imageViewId);
        if (null == img) {        // 判空
            throw new RuntimeException("UIHelper  --> setImage4Img  Fail\n"
                    + "img == null, please check your imageViewId");
        } else if (null == bitmap) {	// 判空
            throw new RuntimeException("UIHelper  --> setImage4Img  Fail\n"
                    + "bitmap == null, please check your bitmap resource");
        } else {
            img.setImageBitmap(bitmap);
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
            throw new RuntimeException("UIHelper  --> setUrl4Img  Fail\n"
                    + "img == null, please check your imageViewId");
        } else if (null == url) {	// 判空
            throw new RuntimeException("UIHelper  --> setUrl4Img  Fail\n"
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
            throw new RuntimeException("UIHelper  --> setCheck4CheckBox  Fail\n"
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
            throw new RuntimeException("UIHelper  --> setVisibility  Fail\n"
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
     * @return 			UIHelper 本身
     */
    public UIHelper setClickEnablility (int viewId, boolean clickAble) {
        View view = getViewById(viewId);
        if (null == view) {
            throw new RuntimeException("UIHelper  --> setClickEnablility  Fail\n"
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
            throw new RuntimeException("UIHelper  --> setSelected  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setSelected(isSelected);
        }

        return this;
    }


    /**
     * 给ID为 viewId 的 控件 设置 背景颜色
     *
     * @param viewId		View 的id
     * @param color	    颜色 ID
     * @return 			UIHelper 本身
     */
    public UIHelper setColor4BackGround (int viewId, int color) {
        View view = getViewById(viewId);
        if (null == view) {
            throw new RuntimeException("UIHelper  --> setClickBackGround  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setBackground(CommonUtils.getResources().getDrawable(color));
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
            throw new RuntimeException("UIHelper  --> setOnClickListener  Fail\n"
                    + "view == null, please check your viewId");
        } else if (null == listener) {
            throw new RuntimeException("UIHelper  --> setOnClickListener  Fail\n"
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
     * @return 			UIHelper 本身
     */
    public UIHelper setOnTouchListener (int viewId, View.OnTouchListener listener) {
        View view = getViewById(viewId);
        if (null == view) {
            throw new RuntimeException("UIHelper  --> setOnTouchListener  Fail\n"
                    + "view == null, please check your viewId");
        } else if (null == listener) {
            throw new RuntimeException("UIHelper  --> setOnTouchListener  Fail\n"
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
            throw new RuntimeException("UIHelper  --> setOnTouchListener  Fail\n"
                    + "view == null, please check your viewId");
        } else {
            view.setFocusable(true);
        }

        return this;
    }

}
