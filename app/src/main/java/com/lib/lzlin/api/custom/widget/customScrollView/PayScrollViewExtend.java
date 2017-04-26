package com.lib.lzlin.api.custom.widget.customScrollView;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 支付悬停
 * 创建人: Administrator
 * 创建时间:  2017/3/23 13:59
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class PayScrollViewExtend extends ScrollViewExtend {

	// 两个参数的构造方法，便于可以直接拖拽使用
	public PayScrollViewExtend(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	// 4. 滑动时触发，l左边的偏移量，t上边的偏移量
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if (listener != null) {
			listener.onPayScroll(t);
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	// 1. 创建接口监听类
	public interface onPayScrollChangeListener {
		void onPayScroll(int height);
	}
	// 2. 实例化一个私有的监听成员变量
	private onPayScrollChangeListener listener;
	// 3. 设置监听
	public void setOnPayScrollChangeListener(onPayScrollChangeListener listener) {
		this.listener = listener;
	}
	
}
