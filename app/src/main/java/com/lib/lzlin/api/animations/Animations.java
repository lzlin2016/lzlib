package com.lib.lzlin.api.animations;

import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 动画集合
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */
public class Animations {

    /**
     * EditText 格式错误动画, 用法
     *    AnimationsUtils.showInfoAnimations(editText, Animations.showEditTextErrorInfo());
     * @return  动画
     */
    public static Animation showEditTextErrorInfo() {
        Animation showError = new TranslateAnimation(0, 10, 0, 0); // framXDelta, toXdelta, framYDelta, toYdelta
        showError.setDuration(300); // 设置动画时间
        showError.setInterpolator(new CycleInterpolator(5f));
        return showError;
    }

}
