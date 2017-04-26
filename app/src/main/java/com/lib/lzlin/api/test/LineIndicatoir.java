package com.lib.lzlin.api.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 水平ListView 模拟tab 实现 下划线指示器功能
 * 创建人: Administrator
 * 创建时间:  2017/3/23 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class LineIndicatoir extends View {
    public final static int DIRECTION_LEFT = 0;    // 移动的方向 左
    public final static int DIRECTION_RIGHT = 1;   // 移动的方向 右
    public final static int DIRECTION_UP = 2;      // 移动的方向 上
    public final static int DIRECTION_DOWN = 3;    // 移动的方向 下
    private Context mContext;
    private int lineColor = Color.BLACK; // 指示线颜色
    private int durationTime = 1000; // 移动时间
    private int amount = 4; // tab 数量

    public LineIndicatoir(Context context) {
        super(context);
        this.mContext = context;
    }

    public LineIndicatoir(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineIndicatoir(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LineIndicatoir(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(lineColor);
        canvas.drawLine(0, 0, getMeasuredWidth(), 0, paint);
    }

    public void startMoveAnimation(int direction, float distance) {
        Animation anim = null;
        switch (direction) {
            case DIRECTION_LEFT:
                anim = new TranslateAnimation (direction, 0, 0, 0); // 左 右 上 下
                break;
            case DIRECTION_RIGHT:
                anim = new TranslateAnimation (0, direction, 0, 0); // 左 右 上 下
                break;
            case DIRECTION_UP:
                anim = new TranslateAnimation (0, 0, direction, 0); // 左 右 上 下
                break;
            case DIRECTION_DOWN:
                anim = new TranslateAnimation (0, 0, 0, direction); // 左 右 上 下
                break;
        }
        anim.setDuration(durationTime);
        LineIndicatoir.this.startAnimation(anim);
    }
}
