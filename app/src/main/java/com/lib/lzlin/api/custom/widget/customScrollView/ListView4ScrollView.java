package com.lib.lzlin.api.custom.widget.customScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * 项目名称:  Lib-lz
 * <p>
 * 类的描述: ListView4ScrollView, 解决ScrollView 中嵌套ListVIew, 滑动冲突只显示一行的情况, 其他同理
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/29 18:05
 * 修改人:
 * 修改备注:
 */

public class ListView4ScrollView extends ListView {
	public ListView4ScrollView(Context context) {
		super(context);
	}

	public ListView4ScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListView4ScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	/**
	 * 重写该方法，重新计算ListView高度
	 * 累计所有行，计算出ListView的最大高度
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				View.MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		super.setOnItemSelectedListener(listener);
	}
	
}
