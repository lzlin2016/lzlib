package com.lib.lzlin.api.utils.backHandleFramentUtils;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 宿主FragmentActivity需要继承BackHandledIntegerface，子Fragment会通过该接口告诉宿主FragmentActivity自己是当前屏幕可见的Fragment。
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/15 16:06
 * 修改人: lz - Administrator
 * 修改备注:
 */

public interface BackHandledInterface {
        public abstract void setSelectedFragment(BackHandledFragment selectedFragment);
}
