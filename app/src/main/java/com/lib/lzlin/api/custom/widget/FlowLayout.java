package com.lib.lzlin.api.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lib.lzlin.api.R;

/**
 * 项目名称:  Lib-lz
 * <p>
 * 类的描述: 流式布局, 用法示例
 private FlowLayout mFlow;// 价格
 private void intSizeFlowShow() {
    mFlow.removeAllViews( );
    for (int i =0 ; i < n; i ++ ) {
        View layoutItem = mInflate.inflate(R.layout.item_flowlayout, null);
        mFlow.addView(layoutItemSize);
    }
 }

 // 在布局中使用
 <yundoubei.llz.ibenhong.com.yungoubei.CustomView.FlowLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/mFlow"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:horizontalSpacing="@dimen/goods_detail_size_space"
    app:verticalSpacing="@dimen/goods_detail_size_space">
 * 创建人: lz - Administrator
 * 创建时间:  2017/3/29 18:05
 * 修改人:
 * 修改备注:
 */

public class FlowLayout extends ViewGroup {
    private int mHorizontalSpacing;
    private int mVerticalSpacing;
    int line_height = 0;

    public FlowLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
            try {
                    mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontalSpacing, 0);
                    mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_verticalSpacing, 0);
            } finally {
                    a.recycle();
            }
            
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	 assert (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);
    	   
         final int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
         int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
         final int count = getChildCount();  
         int line_height = 0;  
    
         int xpos = getPaddingLeft();  
         int ypos = getPaddingTop();  
    
         int childHeightMeasureSpec;  
         if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
             childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
         } else {  
             childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
         }  
    
         for (int i = 0; i < count; i++) {  
             final View child = getChildAt(i);
             if (child.getVisibility() != GONE) {  
                 child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), childHeightMeasureSpec);
                 final int childw = child.getMeasuredWidth();  
                 line_height = Math.max(line_height, child.getMeasuredHeight() + mVerticalSpacing);
    
                 if (xpos + childw > width) {  
                     xpos = getPaddingLeft();  
                     ypos += line_height;  
                 }  
    
                 xpos += childw + mHorizontalSpacing;  
             }  
         }  
         this.line_height = line_height;  
    
         if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
             height = ypos + line_height;  
    
         } else if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
             if (ypos + line_height < height) {  
                 height = ypos + line_height;  
             }  
         }  
         setMeasuredDimension(width, height);  
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    	final int count = getChildCount();  
        final int width = r - l;  
        int xpos = getPaddingLeft();  
        int ypos = getPaddingTop();  
   
        for (int i = 0; i < count; i++) {  
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {  
                final int childw = child.getMeasuredWidth();  
                final int childh = child.getMeasuredHeight();  
                if (xpos + childw > width) {  
                    xpos = getPaddingLeft();  
                    ypos += line_height;  
                }  
                child.layout(xpos, ypos, xpos + childw, ypos + childh);  
                xpos += childw + mHorizontalSpacing;  
            }  
        }  
    }
    
    
    

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
            return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
            return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
            return new LayoutParams(getContext(), attrs);
    }
    
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
            return new LayoutParams(p.width, p.height);
    }

}