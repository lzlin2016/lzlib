package com.lib.lzlin.api.custom.slipViewPager.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 项目名称: SlipViewPager
 * <p>
 * 类的描述: 滑动可控制 ViewPager
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/5 10:58
 * 修改人:
 * 修改备注:
 */

public class SlipViewPager extends ViewPager {
    private Context mContext;
    private boolean isScrollAble = true; // 滑动能力
    private boolean isFilledContent = true; // 是否填充首位空白区域, 即是否将内容铺满整个界面
    private int showItemCount = 3; // 屏幕上同时显示的 item 个数
    private int pageMargin = 10; // 每个item 页面之间的间隔
    private static View preIndicator = null;      // 上一个item的指示器
    private static View nextIndicator = null;     // 下一个item的指示器

    public SlipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        SlipViewPager.this.mContext = context;
    }

    public SlipViewPager(Context context) {
        super(context);
        SlipViewPager.this.mContext = context;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    /**
     * 是否禁止滑动切换, 禁止即消耗触摸事件
     * @param event     触摸事件
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isScrollAble) {
            // TODO lz 待实现首个和最后一个无法移动
//            if (getCurrentItem() == getMinIndex()) { // 滑到最左侧
//                float x = event.getX();
//                float x2 = x;
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        x = event.getX();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        try {
//                            Thread.sleep(10);
//                            if (x2 < x) return false;
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                }
//                Log.e("onTouchEvent", "x =" + x);
//                Log.e("onTouchEvent", "x2 =" + x2);
//                Log.e("onTouchEvent", "pre =" + event.getXPrecision());
//            } else if (getCurrentItem() == getMinIndex()) {  // 滑到最右侧
//                float x = event.getX();
//                float x2 = x;
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        x = event.getX();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (x2 > x) return false;
//                        break;
//                }
//                Log.e("onTouchEvent", "x =" + x);
//                Log.e("onTouchEvent", "x2 =" + x2);
//            }
            return super.onTouchEvent(event);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isScrollAble) {
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setPageTransformer(boolean reverseDrawingOrder, PageTransformer transformer) {
        super.setPageTransformer(reverseDrawingOrder, transformer);
    }

    /**
     * 初始化 屏幕上同时显示多个 item 的配置
     * @param showItemCount item 个数
     * @param pageMargin    每个item 页面之间的间隔
     */
    public void init(int  showItemCount, int pageMargin) {
        init(showItemCount, pageMargin, true);
    }

    /**
     * 初始化 屏幕上同时显示多个 item 的配置
     * @param showItemCount item 个数
     * @param isScrollAble  滑动能力, 是否可以滑动
     */
    public void init(int  showItemCount, int pageMargin, boolean isScrollAble) {
        init(showItemCount, pageMargin, isScrollAble, true);
    }

    /**
     * 初始化 屏幕上同时显示多个 item 的配置
     * @param showItemCount item 个数
     * @param isScrollAble  滑动能力, 是否可以滑动
     * @param isFilledContent  是否填充首位空白区域, 即是否将内容铺满整个界面
     */
    public void init(int  showItemCount, int pageMargin, boolean isScrollAble, boolean isFilledContent) {
        setPageMargin(pageMargin);
        // TODO lz 显示的 item 数必须大于1, 即至少显示一个 item
        if (showItemCount < 1) {
            throw new RuntimeException("SlipViewPager  --> init Fail\n"
                    + "The widget require showItemCount >= 1 ");
        }
//        else  if (showItemCount % 2 == 0 && isFilledContent) {
//            throw new RuntimeException("SlipViewPager  --> init Fail\n"
//                    + "The widget require showItemCount % 2 == 0 ");
//        }
        else {
            this.showItemCount = showItemCount;
            this.isScrollAble = isScrollAble;
            this.isFilledContent = isFilledContent;
        }
        // 符合要求, 且铺满
        if (isFilledContent) {
            setCurrentItem(getMinIndex()); // 显示居中项
            setCustomOffset();
        }
    }

    /**
     * 绑定指示器
     * @param preIndicator   上一个item的指示器
     * @param nextIndicator  下一个item的指示器
     */
    public void initIndicator(View preIndicator, View nextIndicator) {
        initIndicator(preIndicator, nextIndicator, true);
    }

    /**
     * 绑定指示器
     * @param preIndicator   上一个item的指示器
     * @param nextIndicator  下一个item的指示器
     * @param setIndicatorVisiable  是否显示指示器
     */
    public void initIndicator(View preIndicator, View nextIndicator, boolean setIndicatorVisiable) {
        preIndicator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = SlipViewPager.this.getCurrentItem() - 1;
                setCurrentItem(position);
            }
        });

        nextIndicator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =  SlipViewPager.this.getCurrentItem() + 1;
                setCurrentItem(position);
            }
        });

        if (setIndicatorVisiable) {
            setIndicatorVisiable(preIndicator, nextIndicator);
            addPageChangeListener();
        }
    }

    /**
     * 获取第一一个item 的下标, 从 1 开始
     * @return
     */
    private int getMinIndex() {
        int minIndex = 0;
        if (showItemCount % 2 == 1) { // 奇数, 显示在中间, 控制两边的间距相等
            minIndex =  showItemCount / 2; // 首个item
        } else {                    // 偶数, 显示在左侧, 左边间距固定为padding, 右边间距固定为 n * p + (n-1) * M (M为item 的大小)
            minIndex = 0;   // 最小下标索引
        }
        return minIndex;
    }

    /**
     * 获取最后一个item 的下标, 从 1 开始
     * @return
     */
    private int getMaxIndex() {
        int minIndex = getMinIndex();
        int maxIndex = 0;
        if (showItemCount % 2 == 1) { // 奇数, 显示在中间, 控制两边的间距相等
            maxIndex = getAdapter().getCount()- 1 - minIndex; // 最后一个item
        } else {                    // 偶数, 显示在左侧, 左边间距固定为padding, 右边间距固定为 n * p + (n-1) * M (M为item 的大小)
            maxIndex = getAdapter().getCount() - showItemCount;// 最大下标所以
        }
        return maxIndex > 0 ? maxIndex : 0;
    }

    /**
     * 更新指示器, 是否可见
     */
    private void updateIndicatorVisiable() {
        int minIndex = getMinIndex();
        int maxIndex = getMaxIndex();

        // 不是首个item, preIndicator 才显示
        // TODO lz 注意: 获取VIewPager 的个数 不应该是 getChildCount(), 而要采用获取适配器的的个数 getAdapter().getCount()
        if (getAdapter().getCount() > showItemCount && getCurrentItem()  > minIndex ) {
            preIndicator.setVisibility(View.VISIBLE);
        } else {
            preIndicator.setVisibility(View.GONE);
        }
        // 不是最后一个item, nextIndicator 才显示
        if (getAdapter().getCount() > showItemCount && getCurrentItem() < maxIndex) {
            nextIndicator.setVisibility(View.VISIBLE);
        } else {
            nextIndicator.setVisibility(View.GONE);
        }
    }

    /**
     * 设置指示器的显示隐藏
     * @param preIndicator   上一个item的指示器
     * @param nextIndicator  下一个item的指示器
     */
    private void setIndicatorVisiable(View preIndicator, View nextIndicator) {
        SlipViewPager.this.preIndicator = preIndicator;
        SlipViewPager.this.nextIndicator = nextIndicator;
        updateIndicatorVisiable();
    }

    private void addPageChangeListener() {
        SlipViewPager.this.addOnPageChangeListener(new OnPageChangeListener() {
            /**
             * 滑动时触发
             * @param position              当前页面，即你点击滑动的页面
             * @param positionOffset        当前页面偏移的百分比
             * @param positionOffsetPixels  当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels % 10 == 0) {
                    Log.e("onPageScrolled", "position = " + position);
                    Log.e("onPageScrolled", "positionOffset = " + positionOffset);
                    Log.e("onPageScrolled", "positionOffsetPixels = " + positionOffsetPixels);
                }
            }

            /**
             * 选中时触发
             * @param position               选中下标
             */
            @Override
            public void onPageSelected(int position) {
                setItemSelect(position);
            }

            /**
             * 滑动状态改变时触发
             * @param state                 选中状态
             */
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 设置定制的 偏移量
     */
    private void setCustomOffset() {
        setPageMargin(pageMargin);
        setClipToPadding(false); // 设置可以在padding 区域绘制页面, 即同时显示多个item
    }

    /**
     * 设置选中item 逻辑
     * @param position
     */
    private void setItemSelect(int position) {
        int minIndex = getMinIndex();
        int maxIndex = getMaxIndex();

        if (position <= minIndex) {
            setCurrentItem(minIndex);
        } else if(position >= maxIndex) {
            setCurrentItem(maxIndex);
        } else {
            setCurrentItem(position);
        }

        Log.e("setItemSelect", "minIndex =" + minIndex);
        Log.e("setItemSelect", "maxIndex =" + maxIndex);
        Log.e("setItemSelect", "position =" + position);
        updateIndicatorVisiable();
    }

    public boolean isScrollAble() {
        return isScrollAble;
    }

    public void setScrollAble(boolean scrollAble) {
        isScrollAble = scrollAble;
    }

    public boolean isFilledContent() {
        return isFilledContent;
    }

    public void setFilledContent(boolean filledContent) {
        isFilledContent = filledContent;
    }

    public int getShowItemCount() {
        return showItemCount;
    }

    public void setShowItemCount(int showItemCount) {
        this.showItemCount = showItemCount;
    }

    // TODO lz 根据实际需要, 判断是都需要转换单位 CommonUtils.dp2px(this, pageMargin)
    // TODO lz 注意此处需要继承原来的方法, 否则会出错, 因为此处改变的是变量的值
    @Override
    public void setPageMargin(int pageMargin) {
        super.setPageMargin(pageMargin);
        this.pageMargin = pageMargin;
    }
}