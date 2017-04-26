package com.lib.lzlin.api.utils.animationsUtils;

import android.view.View;
import android.view.animation.Animation;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 动画辅助工具
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/15 16:06
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class AnimationsUtils {

    /**
     * @param view       控件, 承载动画内容
     * @param animation  动画
     */
    public static void showInfoAnimations (View view, Animation animation) {
        view.startAnimation(animation);
    }

}
