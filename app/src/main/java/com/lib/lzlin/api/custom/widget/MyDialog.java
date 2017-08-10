package com.lib.lzlin.api.custom.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: TODO
 * 创建人: lz - Administrator
 * 创建时间:  2017/6/6 17:54
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class MyDialog extends Dialog  {

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
        View mConvertView = getLayoutInflater().inflate(themeResId, null);
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
