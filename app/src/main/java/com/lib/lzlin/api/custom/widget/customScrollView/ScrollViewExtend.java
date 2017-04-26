package com.lib.lzlin.api.custom.widget.customScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 增加了滚动监听事件,  兼容低版本API < 19
 * 创建人: Administrator
 * 创建时间:  2017/3/23 13:59
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class ScrollViewExtend extends ScrollView {
	public interface OnScrollChangedListener {
		void onScrollChange(int offset);
	}

	private OnScrollChangedListener mListener;

	public void setOnScrollChangedListener(OnScrollChangedListener l) {
		this.mListener = l;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mListener != null) {
			mListener.onScrollChange(t);
		}
	}

	public ScrollViewExtend(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

}
