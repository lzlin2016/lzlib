package com.lib.lzlin.api.custom.slipViewPager.helperUtils;

import android.content.Context;
import android.view.View;

import com.lib.lzlin.api.custom.slipViewPager.custom.SlipViewPager;
import com.lib.lzlin.api.utils.commonUtils.DensityUtils;

/**
 * 项目名称: SlipViewPager
 * <p>
 * 类的描述: SlipViewPagerHelper帮助类, 若是简单的应用, 只需调用此方法初始化即可
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/6 12:03
 * 修改人:
 * 修改备注:
 */

public  class SlipViewPagerHelper {
    /**
     * SlipViewPagerHelper帮助类, 若是简单的应用, 只需调用此方法初始化即可, 默认不可滑动, 没有指示器, item 个数为3, 页面间距为 10 dp
     * @param mContext          上下文对象
     * @param slipViewPager     SlipViewPager 对象
     */
    public static void init(Context mContext, SlipViewPager slipViewPager) {
        init(mContext, slipViewPager, true, 3, 10, null, null);
    }
    /**
     * SlipViewPagerHelper帮助类, 若是简单的应用, 只需调用此方法初始化即可, 默认不可滑动, 没有指示器
     * @param mContext          上下文对象
     * @param slipViewPager     SlipViewPager 对象
     * @param showItemCount     显示的item 个数
     * @param padding           页面间距
     */
    public static void init(Context mContext, SlipViewPager slipViewPager, int showItemCount, int padding) {
        init(mContext, slipViewPager, true, showItemCount, padding, null, null);
    }
    /**
     * SlipViewPagerHelper帮助类, 若是简单的应用, 只需调用此方法初始化即可, 默认不可滑动
     * @param mContext          上下文对象
     * @param slipViewPager     SlipViewPager 对象
     * @param showItemCount     显示的item 个数
     * @param padding           页面间距
     * @param btnPre            控制指示器, 向前
     * @param btnNext           控制指示器, 向后
     */
    public static void init(Context mContext, SlipViewPager slipViewPager, int showItemCount, int padding, View btnPre, View btnNext) {
        init(mContext, slipViewPager, true, showItemCount, padding, btnPre, btnNext);
    }

    /**
     * SlipViewPagerHelper帮助类, 若是简单的应用, 只需调用此方法初始化即可
     * @param mContext          上下文对象
     * @param slipViewPager     SlipViewPager 对象
     * @param isScrollAble      是否可以滑动
     * @param showItemCount     显示的item 个数
     * @param padding           页面间距
     * @param btnPre            控制指示器, 向前
     * @param btnNext           控制指示器, 向后
     */
    public static void init(Context mContext, SlipViewPager slipViewPager, boolean isScrollAble, int showItemCount, int padding, View btnPre, View btnNext) {
        slipViewPager.init(showItemCount, padding, isScrollAble);

        if (slipViewPager.getShowItemCount() % 2 == 0) {
            initViewPagerPaddingOushu(mContext, slipViewPager, padding);
        } else {
            initViewPagerPaddingJishu(mContext, slipViewPager, padding);
        }

        if (btnPre == null || btnNext == null) return;
        slipViewPager.initIndicator(btnPre, btnNext);
    }

    /**
     * 初始化 奇数 的内边距设置
     *        奇数, 显示在中间, 控制两边的内间距相等, 通过公式计算
     * @param mContext       上下文对象
     * @param slipViewPager  SlipViewPager 对象
     * @param padding        页面间距
     */
    private static void initViewPagerPaddingJishu (Context mContext, SlipViewPager slipViewPager, int padding) {
        int showItemCount = slipViewPager.getShowItemCount();
        int tempDowm = showItemCount / 2; // 向下整除
        int tempUp = (showItemCount + 1) / 2; // 向上整除
        slipViewPager.setPadding(
                (tempDowm * (DensityUtils.getScreenWidth(mContext)-(showItemCount+1) * padding )  / showItemCount) + tempUp * padding ,
                0,
                (tempDowm * (DensityUtils.getScreenWidth(mContext)-(showItemCount+1) * padding )  / showItemCount) + tempUp * padding ,
                0
        );
    }

    /**
     * 初始化 偶数 的内边距设置
     *        偶数, 显示在左侧, 左边间距固定为 padding , 右边内间距固定为 n * p + (n-1) * M (M为item 的大小)
     * @param mContext       上下文对象
     * @param slipViewPager  SlipViewPager 对象
     * @param padding        页面间距
     */
    private static void initViewPagerPaddingOushu (Context mContext, SlipViewPager slipViewPager, int padding) {
        int showItemCount = slipViewPager.getShowItemCount();
        int pLeft = padding;// 左边距
        int pRight = showItemCount * padding + (showItemCount - 1) *( DensityUtils.getScreenWidth(mContext)-(showItemCount+1)*padding ) / showItemCount;// 右边距
        slipViewPager.setPadding(pLeft, 0, pRight, 0); // 左  上  右  下
    }
}