package com.lib.lzlin.api.utils.uiUtils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.lzlin.api.application.BaseApp;

import org.loader.autohideime.AutoHideIMEFrameLayout;

/**
 * WrapUtils
 *
 * 类的描述: 嵌套工具类
 * 创建人: lz -  Administrator
 * 创建时间: 2017/6/13  16:54
 * 修改人: lz -  Administrator
 * 修改备注:
 */

public class WrapUtils {
    /**
     * 获取 activity 根部局
     *
     * @param activity
     */
    public static void wrap(Activity activity, View mLayout) {
        ViewGroup contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        wrap(contentParent, mLayout);
    }

    /**
     * 获取 fragment 根部局
     *
     * @param fragment
     */
    public static void wrap(Fragment fragment, View mLayout) {
        ViewGroup contentParent = (ViewGroup) fragment.getView().getParent();
        wrap(contentParent, mLayout);
    }

    /**
     * 嵌套根部局
     *
     * @param contentParent 所需要的最外层布局, 根部局
     */
    public static void wrap(ViewGroup contentParent, View mLayout) {
        View content = contentParent.getChildAt(0);
        contentParent.removeView(content);

        ViewGroup.LayoutParams params = content.getLayoutParams();

        contentParent.addView(mLayout, new ViewGroup.LayoutParams(params.width, params.height));
    }

    /**
     * 嵌套根部局
     *
     * @param contentParent 所需要的最外层布局, 根部局
     */
    public static boolean wrapSelf(ViewGroup contentParent, View mLayout) {
        boolean needWrap = contentParent.getChildCount() == 0; // 已有parent ,则无需嵌套
        if(needWrap) {
            ViewGroup.LayoutParams params = contentParent.getLayoutParams();
            contentParent.addView(mLayout, new ViewGroup.LayoutParams(params.width, params.height));
        }
        return needWrap;
    }

    /**
     * 嵌套根部局
     *
     * @param contentParent 所需要的最外层布局, 根部局
     */
    public static boolean wrapSelf(ViewGroup contentParent, int layoutID) {
        View mLayout = LayoutInflater.from(BaseApp.getApplication()).inflate(layoutID, null);
        if (null != mLayout) {
            return wrapSelf(contentParent, mLayout);
        } else {
            return false;
        }
    }

    /**
     * 获取 activity 根部局
     *
     * @param activity
     */
    public static void wrap(Activity activity) {
        ViewGroup contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        wrap(contentParent);
    }

    /**
     * 获取 fragment 根部局
     *
     * @param fragment
     */
    public static void wrap(Fragment fragment) {
        ViewGroup contentParent = (ViewGroup) fragment.getView().getParent();
        wrap(contentParent);
    }

    /**
     * 嵌套根部局
     *
     * @param contentParent 所需要的最外层布局, 根部局
     */
    public static void wrap(ViewGroup contentParent) {
        View content = contentParent.getChildAt(0);
        contentParent.removeView(content);

        ViewGroup.LayoutParams params = content.getLayoutParams();
        AutoHideIMEFrameLayout layout = new AutoHideIMEFrameLayout(content.getContext());
        layout.addView(content);

        contentParent.addView(layout, new ViewGroup.LayoutParams(params.width, params.height));
    }
}
