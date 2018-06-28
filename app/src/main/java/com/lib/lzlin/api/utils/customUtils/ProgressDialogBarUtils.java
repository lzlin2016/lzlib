package com.lib.lzlin.api.utils.customUtils;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 自定义进度条 播放加载动画  单实例, 饿汉模式
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注: 2018/6/19 15:11 实现一个界面多个请求时逻辑处理
 */

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.lib.lzlin.api.R;

public class ProgressDialogBarUtils extends Dialog {
	private static ProgressDialogBarUtils mProgressDialogBar = null;
	private static int count = 0; // 初始化速度

	public ProgressDialogBarUtils(Context context) {
		super(context);
	}

	public ProgressDialogBarUtils(Context context, int theme) {
		super(context, theme);
	}

	private static ProgressDialogBarUtils createDialog(Context context) {
		++ count; // 显示次数+1, 做一个界面多个请求时逻辑处理
		synchronized (ProgressDialogBarUtils.class) { // 同步
			if (mProgressDialogBar == null) {	// 单实例, 饿汉模式
				mProgressDialogBar = new ProgressDialogBarUtils(context, R.style.CustomProgressDialog);
				mProgressDialogBar.setContentView(R.layout.progress_layout);
			}
			return mProgressDialogBar;
		}
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
	public static void dismissProgressDialogBar () {
		-- count; // 显示次数-1, 做一个界面多个请求时逻辑处理
		if (mProgressDialogBar != null && mProgressDialogBar.isShowing() && count == 0) {
			mProgressDialogBar.dismiss();
		}
	}

	/**
	 * 置空加载动画
	 */
	public static void clearProgressDialogBar () {
		count = 0;
		if (mProgressDialogBar != null && mProgressDialogBar.isShowing()) {
			mProgressDialogBar.dismiss();
		}
		if (mProgressDialogBar != null) {
			mProgressDialogBar = null;
		}
	}


	/**
	 * [Summary] setTitle 标题
	 * @param title
	 * @return
	 */
	public ProgressDialogBarUtils setTitle(String title) {
		TextView tvMsg = (TextView) mProgressDialogBar.findViewById(R.id.text_progress);
		if (tvMsg != null) {
			tvMsg.setText(title);
		}
		return mProgressDialogBar;
	}
}
