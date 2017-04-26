package com.lib.lzlin.api.custom.scrollerlayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by 王松 on 2016/8/28.
 */
public class ScaleTransformer implements ViewPager.PageTransformer {
    private Context context;
    private float elevation;

    public ScaleTransformer(Context context) {
        this.context = context;
        //TODO 取消z轴阴影 - @liang
        elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                0, context.getResources().getDisplayMetrics());
//        elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                20, context.getResources().getDisplayMetrics());
    }

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {

        } else {
            if (position < 0) {
                ((CardView) page).setCardElevation((1 + position) * elevation);
            } else {
                ((CardView) page).setCardElevation((1 - position) * elevation);
            }
        }
    }
}
