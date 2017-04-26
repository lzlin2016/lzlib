package com.lib.lzlin.api.custom.widget.customScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 项目名称:  Lib-lz
 * <p>
 * 类的描述: GridView4ScrollView, 解决ScrollView 中嵌套 GrtidVIew, 滑动冲突只显示一行的情况, 其他同理
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/29 18:05
 * 修改人:
 * 修改备注:
 */

public class GridView4ScrollView extends GridView {
	public GridView4ScrollView(Context context) {
		super(context);
	}

	public GridView4ScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GridView4ScrollView(Context context, AttributeSet attrs,
							   int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	/**
	 * 重写该方法，重新计算GridView高度
	 * 累计所有行，计算出GridView的最大高度
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(
			Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	
	
	
	
	
	
	
	
	
	
}
