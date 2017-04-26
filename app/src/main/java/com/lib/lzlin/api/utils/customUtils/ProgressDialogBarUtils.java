package com.lib.lzlin.api.utils.customUtils;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述:  * 播放加载动画	 单实例, 饿汉模式
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.lib.lzlin.api.R;

/**
 * 自定义进度条
 *
 * @author Administrator
 *
 */
public class ProgressDialogBarUtils extends Dialog {
	public static ProgressDialogBarUtils mProgressDialogBar = null;

	public ProgressDialogBarUtils(Context context) {
		super(context);
	}

	public ProgressDialogBarUtils(Context context, int theme) {
		super(context, theme);
	}

	private static ProgressDialogBarUtils createDialog(Context context) {
		if (mProgressDialogBar == null) {	// 单实例, 饿汉模式
			mProgressDialogBar = new ProgressDialogBarUtils(context, R.style.CustomProgressDialog);
			mProgressDialogBar.setContentView(R.layout.progress_layout);
		}
		return mProgressDialogBar;
	}

	/**
	 * 显示加载动画
	 */
	public static void showProgressDialogBar (Context context) {
		mProgressDialogBar = createDialog(context);
		if (!mProgressDialogBar.isShowing()) {
			mProgressDialogBar.show();
		}
	}

	/**
	 * 停止加载动画
	 */
	public static void dismissProgressDialogBar (Context context) {
		if (mProgressDialogBar != null && mProgressDialogBar.isShowing()) {
			mProgressDialogBar.dismiss();
		}
	}

	/**
	 * 置空加载动画
	 */
	public static void clearProgressDialogBar (Context context) {
		if (mProgressDialogBar != null && mProgressDialogBar.isShowing()) {
			mProgressDialogBar.dismiss();
		}
		if (mProgressDialogBar != null) {
			mProgressDialogBar = null;
		}
	}


	/**
	 * [Summary] setTitile 标题
	 * @param strTitle
	 * @return
	 */
	public ProgressDialogBarUtils setTitile(String strTitle) {
		return mProgressDialogBar;
	}

	/**
	 * [Summary] setMessage 提示内容
	 * @param strMessage
	 * @return
	 */
	public void setProgressMsg(String strMessage) {
		TextView tvMsg = (TextView) mProgressDialogBar.findViewById(R.id.text_progress);
		if (tvMsg != null)
			tvMsg.setText(strMessage);
	}
}
