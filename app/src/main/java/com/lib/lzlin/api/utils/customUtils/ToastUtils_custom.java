package com.lib.lzlin.api.utils.customUtils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.lzlin.api.R;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: ToastUtils_custom 封装
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public final class ToastUtils_custom {

	private static Toast toast;
	public  static void showToast(Context context, String text) {
		if (toast != null){
			toast.cancel();
		}
		toast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
		TextView tvText = (TextView) view.findViewById(R.id.tvToask);
		tvText.setText(text);
		// toast.setBackground();
		toast.setView(view);
		toast.setGravity(Gravity.BOTTOM, 0, 120);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}
	
	private ToastUtils_custom(){}
}
